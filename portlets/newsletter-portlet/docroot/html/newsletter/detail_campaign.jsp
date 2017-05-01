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
long campaignId = ParamUtil.getLong(request, "campaignId");

NewsletterCampaign campaign = NewsletterCampaignLocalServiceUtil.getNewsletterCampaign(campaignId);

String redirect = ParamUtil.getString(request, "redirect");

String contentTitle = NewsletterContentLocalServiceUtil.getNewsletterContent(campaign.getContentId()).getTitle();
%>

<aui:model-context bean="<%= campaign %>" model="<%= NewsletterCampaign.class %>" />

<liferay-ui:header
	backURL="<%= redirect %>"
	title='Campaign Detail'
/>

<aui:fieldset>
	<aui:field-wrapper name="emailSubject" label="email-subject">
			<%= campaign.getEmailSubject() %>
	</aui:field-wrapper>

	<aui:field-wrapper name="senderName" label="sender-name">
			<%= campaign.getSenderName() %>
	</aui:field-wrapper>

	<aui:field-wrapper name="senderEmail" label="sender-email">
			<%= campaign.getSenderEmail() %>
	</aui:field-wrapper>

	<aui:field-wrapper name="sendDate" label="send-date">
			<%= dateHourFormat.format(campaign.getSendDate()) %>
	</aui:field-wrapper>

	<aui:field-wrapper name="campaignContent" label="campaign-content">
			<%= campaign.getContent().getContent() %>
	</aui:field-wrapper>
</aui:fieldset>

<aui:field-wrapper name="contacts" label="contacts" />

<liferay-portlet:renderURL varImpl="portletURL">
	<portlet:param name="jspPage" value="/html/newsletter/detail_campaign.jsp" />
	<portlet:param name="campaignId" value="<%= String.valueOf(campaignId) %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="tabs1" value="<%= NewsletterConstants.TABS_CAMPAIGN %>" />
</liferay-portlet:renderURL>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
	<liferay-ui:search-form
		page="/html/newsletter/contact_search.jsp"
		servletContext="<%= application %>"
	/>
</aui:form>

<liferay-ui:search-container
	searchContainer="<%= new NewsletterContactSearch(renderRequest, portletURL) %>"
>
 	<%@ include file="contact_search_results.jspf" %>

	<liferay-ui:search-container-results
		results="<%= resultList %>"
		total="<%= totalCount %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.newsletter.model.NewsletterContact"
		keyProperty="contactId"
		modelVar="contactNewsletter"
	>
		<liferay-ui:search-container-column-text
			name="name"
			value="<%= contactNewsletter.getName() %>"
		/>

		<liferay-ui:search-container-column-text
			name="email"
			value="<%= contactNewsletter.getEmail() %>"
		/>

		<liferay-ui:search-container-column-text
			name="status"
			value='<%= NewsletterLogLocalServiceUtil.getLog(campaignId, contactNewsletter.getContactId()).isSent() ? "Sent" : "Failed" %>'
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<portlet:actionURL name="resendCampaign" var="resendURL">
	<portlet:param name="cmd" value="<%= NewsletterConstants.RESEND %>" />
	<portlet:param name="resendType" value="<%= NewsletterConstants.RESEND_ALL %>" />
	<portlet:param name="campaignId" value="<%= String.valueOf(campaignId) %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="tabs1" value="<%= NewsletterConstants.TABS_CAMPAIGN %>" />
</portlet:actionURL>

<portlet:actionURL name="resendFailed" var="resendFailedURL">
	<portlet:param name="cmd" value="<%= NewsletterConstants.RESEND %>" />
	<portlet:param name="resendType" value="<%= NewsletterConstants.RESEND_FAILED %>" />
	<portlet:param name="campaignId" value="<%= String.valueOf(campaignId) %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="tabs1" value="<%= NewsletterConstants.TABS_CAMPAIGN %>" />
</portlet:actionURL>

<aui:button-row>
	<c:if test='<%= CampaignPermission.contains(permissionChecker, scopeGroupId, campaignId, NewsletterKeys.RESEND) %>'>
			<aui:button value="resend-all" onClick="<%= resendURL.toString() %>" />

			<aui:button value="resend-failed" onClick="<%= resendFailedURL.toString() %>" />
	</c:if>
</aui:button-row>