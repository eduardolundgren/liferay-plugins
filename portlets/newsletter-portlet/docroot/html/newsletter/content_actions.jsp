<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/html/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

NewsletterContent content = (NewsletterContent)row.getObject();

long contentId = content.getContentId();

String redirect = PortalUtil.getCurrentURL(renderRequest);
%>

<liferay-ui:icon-menu>
	<c:if test="<%= ContentPermission.contains(permissionChecker, scopeGroupId, contentId, NewsletterKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="jspPage" value="/html/newsletter/edit_content.jsp" />
			<portlet:param name="contentId" value="<%= String.valueOf(contentId) %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%=editURL.toString() %>"
		/>
	</c:if>

	<c:if test="<%= ContentPermission.contains(permissionChecker, scopeGroupId, contentId, NewsletterKeys.DELETE) %>">
		<portlet:actionURL name="deleteContent" var="deleteURL">
			<portlet:param name="cmd" value="<%= NewsletterConstants.DELETE_CONTENT %>" />
			<portlet:param name="contentId" value="<%= String.valueOf(contentId) %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			image="delete"
			url="<%=deleteURL.toString() %>"
		/>
	</c:if>

	<c:if test="<%= ContentPermission.contains(permissionChecker, scopeGroupId, contentId, NewsletterKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= NewsletterContent.class.getName() %>"
			modelResourceDescription="<%= content.getTitle() %>"
			resourcePrimKey="<%= String.valueOf(contentId) %>"
			var="permissionsURL"
		/>

		<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
	</c:if>

</liferay-ui:icon-menu>