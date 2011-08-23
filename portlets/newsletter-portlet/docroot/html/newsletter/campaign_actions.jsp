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
NewsletterCampaign campaign = (NewsletterCampaign)row.getObject();

String name = NewsletterCampaign.class.getName();
long campaignId = campaign.getCampaignId();

String redirect = PortalUtil.getCurrentURL(renderRequest);
%>

<liferay-ui:icon-menu>
	<c:if test='<%= CampaignPermission.contains(permissionChecker, scopeGroupId, campaignId, NewsletterKeys.RESEND) %>'>
		<portlet:actionURL name="resendCampaign" var="resendURL">
			<portlet:param name="cmd" value="<%= NewsletterConstants.RESEND %>" />
			<portlet:param name="campaignId" value="<%= String.valueOf(campaignId) %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="tabs1" value="<%= NewsletterConstants.TABS_CAMPAIGN %>" />
		</portlet:actionURL>

		<liferay-ui:icon message="resend" label="true" image="forward" url="<%=resendURL.toString() %>" />
	</c:if>

	<c:if test='<%= CampaignPermission.contains(permissionChecker, scopeGroupId, campaignId, NewsletterKeys.DETAIL) %>'>
		<portlet:renderURL var="detailURL">
			<portlet:param name="jspPage" value="/html/newsletter/detail_campaign.jsp" />
			<portlet:param name="campaignId" value="<%= String.valueOf(campaignId) %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="tabs1" value="<%= NewsletterConstants.TABS_CAMPAIGN %>" />
		</portlet:renderURL>

		<liferay-ui:icon message="detail" label="true" image="view" url="<%=detailURL.toString() %>" />
	</c:if>

	<c:if test="<%= CampaignPermission.contains(permissionChecker, scopeGroupId, campaignId, NewsletterKeys.DELETE) %>">
		<portlet:actionURL name="deleteCampaign" var="deleteURL">
			<portlet:param name="cmd" value="<%= NewsletterConstants.DELETE_CAMPAIGN %>" />
			<portlet:param name="campaignId" value="<%= String.valueOf(campaignId) %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="tabs1" value="<%= NewsletterConstants.TABS_CAMPAIGN %>" />
		</portlet:actionURL>

		<liferay-ui:icon image="delete" url="<%=deleteURL.toString() %>" />
	</c:if>

	<c:if test="<%= CampaignPermission.contains(permissionChecker, scopeGroupId, campaignId, NewsletterKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= NewsletterCampaign.class.getName() %>"
			modelResourceDescription="<%= campaign.getEmailSubject() %>"
			resourcePrimKey="<%= String.valueOf(campaignId) %>"
			var="permissionsURL"
		/>

		<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
	</c:if>

</liferay-ui:icon-menu>