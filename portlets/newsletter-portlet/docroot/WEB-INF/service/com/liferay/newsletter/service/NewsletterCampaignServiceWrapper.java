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

package com.liferay.newsletter.service;

/**
 * <p>
 * This class is a wrapper for {@link NewsletterCampaignService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       NewsletterCampaignService
 * @generated
 */
public class NewsletterCampaignServiceWrapper
	implements NewsletterCampaignService {
	public NewsletterCampaignServiceWrapper(
		NewsletterCampaignService newsletterCampaignService) {
		_newsletterCampaignService = newsletterCampaignService;
	}

	public com.liferay.newsletter.model.NewsletterCampaign addCampaign(
		long groupId, long contentId, java.lang.String emailSubject,
		java.lang.String senderEmail, java.lang.String senderName,
		int sendDateDay, int sendDateMonth, int sendDateYear,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _newsletterCampaignService.addCampaign(groupId, contentId,
			emailSubject, senderEmail, senderName, sendDateDay, sendDateMonth,
			sendDateYear, serviceContext);
	}

	public void deleteCampaign(long groupId, long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_newsletterCampaignService.deleteCampaign(groupId, campaignId);
	}

	public void sendCampaign(long groupId, long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_newsletterCampaignService.sendCampaign(groupId, campaignId);
	}

	public void resendCampaignToFailedContacts(long groupId, long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_newsletterCampaignService.resendCampaignToFailedContacts(groupId,
			campaignId);
	}

	public NewsletterCampaignService getWrappedNewsletterCampaignService() {
		return _newsletterCampaignService;
	}

	public void setWrappedNewsletterCampaignService(
		NewsletterCampaignService newsletterCampaignService) {
		_newsletterCampaignService = newsletterCampaignService;
	}

	private NewsletterCampaignService _newsletterCampaignService;
}