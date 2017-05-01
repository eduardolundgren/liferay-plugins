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

package com.liferay.newsletter.model.impl;

import com.liferay.newsletter.model.NewsletterContent;
import com.liferay.newsletter.model.NewsletterLog;
import com.liferay.newsletter.service.NewsletterContentLocalServiceUtil;
import com.liferay.newsletter.service.NewsletterLogLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Bruno Pinheiro
 * @author Marcellus Tavares
 */
public class NewsletterCampaignImpl extends NewsletterCampaignBaseImpl {

	public NewsletterContent getContent()
		throws PortalException, SystemException {

		return NewsletterContentLocalServiceUtil.getContent(getContentId());
	}

	public List<NewsletterLog> getLogs()
		throws PortalException, SystemException {

		return NewsletterLogLocalServiceUtil.getLogsByCampaignId(
			getCampaignId());
	}

}