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

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Bruno Pinheiro
 */
public class NewsletterContactSearchTerms
	extends NewsletterContactDisplayTerms {

	public NewsletterContactSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		email = DAOParamUtil.getString(portletRequest, EMAIL);
		name = DAOParamUtil.getString(portletRequest, NAME);
		status = ParamUtil.getBoolean(portletRequest, STATUS);
	}

	public String getEmail(String email) {
		return this.email;
	}

	public String getName(String name) {
		return this.name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

}