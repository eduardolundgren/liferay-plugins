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

<%-- checar se tudo esta sendo usado --%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.newsletter.ContactException" %>
<%@ page import="com.liferay.newsletter.ContentException" %>
<%@ page import="com.liferay.newsletter.EmailException" %>
<%@ page import="com.liferay.newsletter.NameException" %>
<%@ page import="com.liferay.newsletter.SubjectException" %>
<%@ page import="com.liferay.newsletter.TitleException" %>
<%@ page import="com.liferay.newsletter.model.NewsletterCampaign" %>
<%@ page import="com.liferay.newsletter.model.NewsletterContact" %>
<%@ page import="com.liferay.newsletter.model.NewsletterContent" %>
<%@ page import="com.liferay.newsletter.search.NewsletterContactSearchTerms" %>
<%@ page import="com.liferay.newsletter.search.NewsletterContactDisplayTerms" %>
<%@ page import="com.liferay.newsletter.search.NewsletterContactSearch" %>
<%@ page import="com.liferay.newsletter.service.NewsletterCampaignLocalServiceUtil" %>
<%@ page import="com.liferay.newsletter.service.NewsletterContactLocalServiceUtil" %>
<%@ page import="com.liferay.newsletter.service.NewsletterContentLocalServiceUtil" %>
<%@ page import="com.liferay.newsletter.service.NewsletterLogLocalServiceUtil" %>
<%@ page import="com.liferay.newsletter.service.permission.CampaignPermission" %>
<%@ page import="com.liferay.newsletter.service.permission.ContentPermission" %>
<%@ page import="com.liferay.newsletter.service.permission.NewsletterPermission" %>
<%@ page import="com.liferay.newsletter.util.NewsletterConstants" %>
<%@ page import="com.liferay.newsletter.util.NewsletterKeys" %>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>
<%@ page import="com.liferay.portal.kernel.servlet.SessionErrors" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PropsKeys" %>
<%@ page import="com.liferay.portal.kernel.util.StringBundler" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.portlet.journal.model.JournalArticle" %>
<%@ page import="com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil" %>
<%@ page import="com.liferay.util.portlet.PortletProps" %>

<%@ page import="java.text.SimpleDateFormat" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.PortletPreferences" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
PortletPreferences prefs = renderRequest.getPreferences();
String currentUrl = PortalUtil.getCurrentURL(request);
SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
SimpleDateFormat dateHourFormat = new SimpleDateFormat("dd/MM/yyyy h:mm a");
%>