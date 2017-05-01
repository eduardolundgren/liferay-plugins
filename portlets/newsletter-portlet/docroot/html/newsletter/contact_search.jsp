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
NewsletterContactDisplayTerms displayTerms = new NewsletterContactDisplayTerms(renderRequest);
%>

<liferay-ui:search-toggle
	id="toggle_id_contact_search"
	displayTerms="<%= displayTerms %>"
	buttonLabel="search"
>
	<aui:fieldset>
		<aui:column>
			<aui:input label="name" name="<%= displayTerms.NAME %>" size="20" type="text" value="<%= displayTerms.getName() %>" />

			<aui:input label="email" name="<%= displayTerms.EMAIL %>" size="20" type="text" value="<%= displayTerms.getEmail() %>" />

			<aui:select name="<%= displayTerms.STATUS %>">
				<aui:option value=""></aui:option>
				<aui:option label="<%= displayTerms.SENT %>" selected='<%= displayTerms.isSent() %>' value="1" />
				<aui:option label="<%= displayTerms.FAILED %>" selected='<%= !displayTerms.isSent() %>' value="0" />
			</aui:select>
		</aui:column>
	</aui:fieldset>
</liferay-ui:search-toggle>