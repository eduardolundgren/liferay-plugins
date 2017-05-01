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

package com.liferay.newsletter.service.impl;

import com.liferay.newsletter.model.NewsletterLog;
import com.liferay.newsletter.service.base.NewsletterLogLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * @author Bruno Pinheiro
 */
public class NewsletterLogLocalServiceImpl
	extends NewsletterLogLocalServiceBaseImpl {

	public NewsletterLog addLog(
			long campaignId, long contactId, boolean sent,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		long logId = counterLocalService.increment();

		NewsletterLog log = newsletterLogPersistence.create(logId);

		log.setCampaignId(campaignId);
		log.setContactId(contactId);
		log.setSent(sent);

		return newsletterLogPersistence.update(log, false);
	}

	public void deleteLog(NewsletterLog log) throws SystemException {
		newsletterLogPersistence.remove(log);
	}

	public NewsletterLog getLog(long campaignId, long contactId)
		throws PortalException, SystemException {

		return newsletterLogPersistence.findByC_C(campaignId, contactId);
	}

	public List<NewsletterLog> getLogsByCampaignId(long campaignId)
		throws SystemException {

		return newsletterLogPersistence.findByCampaignId(campaignId);
	}

	public List<NewsletterLog> getLogsByContactId(long contactId)
		throws SystemException {

		return newsletterLogPersistence.findByContactId(contactId);
	}

	public List<NewsletterLog> getLogsByC_S(long campaignId, boolean sent)
		throws SystemException {

		return newsletterLogPersistence.findByC_S(campaignId, sent);
	}

}