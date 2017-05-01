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

import com.liferay.newsletter.model.NewsletterContact;
import com.liferay.portal.kernel.dao.search.SearchContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Bruno Pinheiro
 */
public class NewsletterContactSearch
	extends SearchContainer<NewsletterContact> {

	static List<String> headerNames = new ArrayList<String>();
	static Map<String, String> orderableHeaders = new HashMap<String, String>();

	static {
		headerNames.add("name");
		headerNames.add("email");
		headerNames.add("status");

		orderableHeaders.put("name", "name");
		orderableHeaders.put("email", "email");
		orderableHeaders.put("status", "status");
	}

	public static final String EMPTY_RESULTS_MESSAGE =
		"newsletter-empty-results-message";

	public NewsletterContactSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new NewsletterContactDisplayTerms(portletRequest),
			new NewsletterContactSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		NewsletterContactDisplayTerms displayTerms =
			(NewsletterContactDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			NewsletterContactDisplayTerms.EMAIL, displayTerms.getEmail());
		iteratorURL.setParameter(
			NewsletterContactDisplayTerms.NAME, displayTerms.getName());
		iteratorURL.setParameter(
			NewsletterContactDisplayTerms.STATUS, String.valueOf(
				displayTerms.isSent()));

		setOrderableHeaders(orderableHeaders);
	}

}