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

<portlet:actionURL name="configure" var="configureUrl" />

<liferay-ui:error key="preferences-added" message="preferences-added" />

<aui:form action="<%= configureUrl %>" method="POST" name="fm">
	<aui:fieldset>
		<aui:input type="hidden" name="cmd" value="<%= NewsletterConstants.CONFIGURE %>" />
		<aui:input name="senderName" label="Sender Name" value='<%= PortletProps.get(NewsletterConstants.SENDER_NAME) %>' size="45" type="text" />
		<aui:input name="senderEmail" label="Sender Email" value='<%= PortletProps.get(NewsletterConstants.SENDER_EMAIL) %>' size="45" type="text" />
		<aui:input name="smtpHost" label="SMTP Host" value='<%= PortletProps.get(PropsKeys.MAIL_SESSION_MAIL_SMTP_HOST) %>' size="45" type="text" />
		<aui:input name="smtpPort" label="SMTP Port" value='<%= PortletProps.get(PropsKeys.MAIL_SESSION_MAIL_SMTP_PORT) %>' size="45" type="text" />
		<aui:input name="smtpUser" label="SMTP User" value='<%= PortletProps.get(PropsKeys.MAIL_SESSION_MAIL_SMTP_USER) %>' size="45" type="text" />
		<aui:input name="smtpPassword" label="SMTP Password" value='<%= PortletProps.get(PropsKeys.MAIL_SESSION_MAIL_SMTP_PASSWORD) %>' size="45" type="text" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>