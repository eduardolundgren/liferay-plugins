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

import com.liferay.newsletter.model.NewsletterCampaign;
import com.liferay.newsletter.service.base.NewsletterCampaignServiceBaseImpl;
import com.liferay.newsletter.service.permission.CampaignPermission;
import com.liferay.newsletter.service.permission.NewsletterPermission;
import com.liferay.newsletter.util.NewsletterKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

/**
 * @author Bruno Pinheiro
 */
public class NewsletterCampaignServiceImpl
	extends NewsletterCampaignServiceBaseImpl {

	public NewsletterCampaign addCampaign(
			long groupId, long contentId, String emailSubject,
			String senderEmail,	String senderName, int sendDateDay,
			int sendDateMonth, int sendDateYear, ServiceContext serviceContext)
		throws PortalException, SystemException {

		NewsletterPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			NewsletterKeys.ADD_CAMPAIGN);

		return newsletterCampaignLocalService.addCampaign(
			getUserId(), groupId, contentId, emailSubject, senderEmail,
			senderName, sendDateDay, sendDateMonth, sendDateYear,
			serviceContext);
	}

	public void deleteCampaign(long groupId, long campaignId)
		throws PortalException, SystemException {

		CampaignPermission.check(
			getPermissionChecker(), groupId, campaignId, NewsletterKeys.DELETE);

		newsletterCampaignLocalService.deleteCampaign(campaignId);
	}

	public void sendCampaign(long groupId, long campaignId)
		throws PortalException, SystemException {

		CampaignPermission.check(
			getPermissionChecker(), groupId, campaignId, NewsletterKeys.RESEND);

		newsletterCampaignLocalService.sendCampaign(campaignId);
	}

	public void resendCampaignToFailedContacts(long groupId, long campaignId)
		throws PortalException, SystemException {

		CampaignPermission.check(
			getPermissionChecker(), groupId, campaignId, NewsletterKeys.RESEND);

		newsletterCampaignLocalService.
			resendCampaignToFailedContacts(campaignId);
	}

}