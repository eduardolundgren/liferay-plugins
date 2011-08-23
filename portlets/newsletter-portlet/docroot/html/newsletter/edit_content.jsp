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

<%@include file="/html/init.jsp" %>

<%
NewsletterContent newsletterContent = null;
String content = StringPool.BLANK;
String contentEditorClass = "contentEditor-div";
String displayingArticleContentClass = "displaying-article-content";

long contentId = ParamUtil.getLong(request, "contentId");

if (contentId > 0) {
	newsletterContent = NewsletterContentLocalServiceUtil.getNewsletterContent(contentId);
	content = newsletterContent.getContent();

	if (newsletterContent.getArticleId() > 0) {
		contentEditorClass = contentEditorClass + " aui-helper-hidden";
	}
	else {
		displayingArticleContentClass = displayingArticleContentClass + " aui-helper-hidden";
	}
}

String redirect = ParamUtil.getString(request, "redirect");
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (newsletterContent != null) ? newsletterContent.getTitle() : "New Campaign Content" %>'
/>

<aui:model-context bean="<%= newsletterContent %>" model="<%= NewsletterContent.class %>" />

<portlet:actionURL var="editContentURL" />

<liferay-portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" varImpl="selectContentPopupURL">
	<portlet:param name="tabs1" value="CampaignContent" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="import" value="false" />
	<portlet:param name="jspPage" value="/html/newsletter/popup.jsp" />
	<portlet:param name="resourceNamespace" value="<%= renderResponse.getNamespace() %>" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" varImpl="importContentPopupURL">
	<portlet:param name="tabs1" value="CampaignContent" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="import" value="true" />
	<portlet:param name="jspPage" value="/html/newsletter/popup.jsp" />
	<portlet:param name="resourceNamespace" value="<%= renderResponse.getNamespace() %>" />
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL varImpl="getArticleContentURL">
	<portlet:param name="cmd" value="<%= NewsletterConstants.GET_ARTICLE_CONTENT %>" />
	<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
	<portlet:param name="portletResource" value="<portlet:namespace/>" />
</liferay-portlet:resourceURL>

<aui:form action="<%= editContentURL %>" method="POST" name="fm">
	<aui:fieldset>
		<aui:input type="hidden" name="cmd" value='<%= newsletterContent == null ? NewsletterConstants.ADD_CONTENT : NewsletterConstants.UPDATE_CONTENT %>' />
		<aui:input type="hidden" name="redirect" value="<%= redirect %>" />
		<aui:input type="hidden" name="currentUrl" value="<%= currentUrl %>" />
		<aui:input type="hidden" name="contentId" value="<%= contentId %>" />
		<aui:input type="hidden" name="articleId" />
		<aui:input type="hidden" name="content" id="content" value="<%= content %>" />

		<aui:input name="title" label="Title">
			<aui:validator name="required" />
		 </aui:input>

		<liferay-ui:error exception="<%= TitleException.class %>">
			<liferay-ui:message arguments='<%= new Object[] {"title"} %>' key="x-is-required" />
		</liferay-ui:error>

		<div class="separator article-separator"></div>
		<% String classMsgInfo = "portlet-msg-info " + (newsletterContent == null ? "aui-helper-hidden" : ""); %>

		<div class="<%= classMsgInfo %>">
			<span class="displaying-article-id-holder">
				<liferay-ui:message key="displaying-content" />: <span class="displaying-article-id"> </span>
			</span>
		</div>

		<div class="<%= contentEditorClass %>">
			<liferay-ui:input-editor name="contentEditor" toolbarSet="liferay-article" width="100%" onChangeMethod='changeContent' />
		</div>

		<span class="<%= displayingArticleContentClass %>">  <%= content %> </span>

		<liferay-ui:error exception="<%= ContentException.class %>">
			<liferay-ui:message arguments='<%= new Object[] {"content"} %>' key="x-is-required" />
		</liferay-ui:error>

		<div class="separator article-separator"></div>

		<%
		StringBundler sb = new StringBundler(3);

		sb.append("javascript:Liferay.Util.openWindow({id: '',title: 'Web Content',uri: '");
		sb.append(importContentPopupURL);
		sb.append("'});");

		String importWebContentPopUpURL = sb.toString();
		%>
		<aui:button value="import-webcontent" onClick="<%= importWebContentPopUpURL %>" />

		<%
		sb = new StringBundler(3);

		sb.append("javascript:Liferay.Util.openWindow({id: '',title: 'Web Content',uri: '");
		sb.append(selectContentPopupURL);
		sb.append("'});");

		String webContentPopUpURL = sb.toString();
		%>
		<aui:button value="select-webcontent" onClick="<%= webContentPopUpURL %>" />

	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button type="cancel"  onClick="<%= redirect %>" />
	</aui:button-row>
</aui:form>

<aui:script>

function <portlet:namespace />initEditor() {
	return document.getElementById('<portlet:namespace/>content').value;
}

function <portlet:namespace />changeContent() {
	document.getElementById('<portlet:namespace/>content').value = window.frames['<portlet:namespace/>contentEditor'].getHTML();
}

Liferay.provide(
	window,
	'<portlet:namespace/>setCampaignContentValue',
	function(articleId, articleTitle, import) {
		var A = AUI();
		A.io.request('<%= getArticleContentURL %>',
			{
				data: {
					articleId: articleId
				},

				on: {
					success: function() {
						var instance = this;
						var data = instance.get('responseData');
						var displayArticleContent = A.one('.displaying-article-content');
						var fckeditor = A.one('.contentEditor-div');

						if (import) {
							fckeditor.hide();
							displayArticleContent.set('innerHTML', data);
							document.getElementById('<portlet:namespace/>content').value = data;
							document.getElementById('<portlet:namespace/>articleId').value = articleId;
							displayArticleContent.show();
						}
						else {
							displayArticleContent.hide();
							window.frames['<portlet:namespace/>contentEditor'].setHTML(data);
							document.getElementById('<portlet:namespace/>articleId').value = 0;
							fckeditor.show();
						}

						A.one('.portlet-msg-info').show();
						var displayArticleId = A.one('.displaying-article-id');
						displayArticleId.set('innerHTML', articleTitle);
					}
				}
			}
		);
	},
	['aui-io']
);
</aui:script>