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
String redirect = PortalUtil.getCurrentURL(renderRequest);
%>

<aui:button-row>
	<c:if test='<%= NewsletterPermission.contains(permissionChecker, scopeGroupId, NewsletterKeys.ADD_CONTENT) %>'>
		<portlet:renderURL var="addContentURL">
			<portlet:param name="jspPage" value="/html/newsletter/edit_content.jsp" />
			<portlet:param name="redirect" value="<%= redirect %>" />
		</portlet:renderURL>

		<aui:button value="add-content" onClick="<%= addContentURL.toString() %>" />
	</c:if>
</aui:button-row>

<liferay-ui:search-container emptyResultsMessage="newsletter-empty-results-message">
	<liferay-ui:search-container-results
		results="<%= NewsletterContentLocalServiceUtil.getNewsletterContents(searchContainer.getStart(), searchContainer.getEnd()) %>"
		total="<%= NewsletterContentLocalServiceUtil.getNewsletterContentsCount() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.newsletter.model.NewsletterContent"
		keyProperty="contentId"
		modelVar="content"
	>
		<liferay-ui:search-container-column-text
			name="title"
			value="<%= content.getTitle() %>"
		/>

		<liferay-ui:search-container-column-text
			name="creation-date"
			value="<%= dateFormat.format(content.getCreateDate()) %>"
		/>

		<liferay-ui:search-container-column-text
			name="num-of-campaigns"
			value='<%= String.valueOf(NewsletterCampaignLocalServiceUtil.getCampaignsCount(content.getContentId())) %>'
		/>

		<liferay-ui:search-container-column-text
			name="num-of-emails"
			value='<%= String.valueOf(NewsletterContactLocalServiceUtil.getContactCountByContent(content.getContentId())) %>'
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/html/newsletter/content_actions.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>