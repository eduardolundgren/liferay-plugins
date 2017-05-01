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
String tabs1 = ParamUtil.getString(request, "tabs1", NewsletterConstants.TABS_CONTENT);
String tabNames = NewsletterConstants.TABS_CONTENT + StringPool.COMMA + NewsletterConstants.TABS_CAMPAIGN;
%>

<portlet:renderURL var="renderURL">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
</portlet:renderURL>

<aui:button-row>
<c:if test='<%= NewsletterPermission.contains(permissionChecker, scopeGroupId, NewsletterKeys.PERMISSIONS) %>'>
	<liferay-security:permissionsURL
		modelResource="com.liferay.newsletter.model"
		modelResourceDescription="Permission"
		resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
		var="permissionsURL" />

	<aui:button value="permissions" onClick="<%= permissionsURL %>" />
</c:if>
</aui:button-row>

<liferay-ui:tabs
   names="<%= tabNames %>"
   url="<%= renderURL %>"
/>

<c:if test='<%= tabs1.equals(NewsletterConstants.TABS_CONTENT) %>'>
  <liferay-util:include servletContext="<%= application %>" page="/html/newsletter/view_content.jsp" />
</c:if>
<c:if test='<%= tabs1.equals(NewsletterConstants.TABS_CAMPAIGN) %>'>
  <liferay-util:include servletContext="<%= application %>" page="/html/newsletter/view_campaign.jsp" />
</c:if>