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

package com.liferay.newsletter.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import javax.portlet.PortletRequest;

/**
 * @author Bruno Pinheiro
 */
public class NewsletterContactDisplayTerms extends DisplayTerms {

	public static final String EMAIL = "email";

	public static final String FAILED = "failed";

	public static final String GROUP_ID = "groupId";

	public static final String NAME = "name";

	public static final String SENT = "sent";

	public static final String STATUS = "status";

	public NewsletterContactDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		email = ParamUtil.getString(portletRequest, EMAIL);
		name = ParamUtil.getString(portletRequest, NAME);
		status = ParamUtil.getBoolean(portletRequest, STATUS);
		groupId = setGroupId(portletRequest);
	}

	public String getEmail() {
		return email;
	}

	public long getGroupId() {
		return groupId;
	}

	public String getName() {
		return name;
	}

	public boolean isSent() {
		return status;
	}

	public long setGroupId(PortletRequest portletRequest) {
		groupId = ParamUtil.getLong(portletRequest, GROUP_ID);

		if (groupId == 0) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)portletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			groupId = themeDisplay.getScopeGroupId();
		}

		return groupId;
	}

	protected String email;
	protected long groupId;
	protected String name;
	protected boolean status;

}