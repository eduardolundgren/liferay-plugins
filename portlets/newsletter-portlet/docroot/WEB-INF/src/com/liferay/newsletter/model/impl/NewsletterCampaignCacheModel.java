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

import com.liferay.newsletter.model.NewsletterCampaign;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing NewsletterCampaign in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see NewsletterCampaign
 * @generated
 */
public class NewsletterCampaignCacheModel implements CacheModel<NewsletterCampaign> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", campaignId=");
		sb.append(campaignId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", contentId=");
		sb.append(contentId);
		sb.append(", emailSubject=");
		sb.append(emailSubject);
		sb.append(", senderEmail=");
		sb.append(senderEmail);
		sb.append(", senderName=");
		sb.append(senderName);
		sb.append(", sent=");
		sb.append(sent);
		sb.append(", sendDate=");
		sb.append(sendDate);
		sb.append("}");

		return sb.toString();
	}

	public NewsletterCampaign toEntityModel() {
		NewsletterCampaignImpl newsletterCampaignImpl = new NewsletterCampaignImpl();

		if (uuid == null) {
			newsletterCampaignImpl.setUuid(StringPool.BLANK);
		}
		else {
			newsletterCampaignImpl.setUuid(uuid);
		}

		newsletterCampaignImpl.setCampaignId(campaignId);
		newsletterCampaignImpl.setGroupId(groupId);
		newsletterCampaignImpl.setCompanyId(companyId);
		newsletterCampaignImpl.setUserId(userId);

		if (userName == null) {
			newsletterCampaignImpl.setUserName(StringPool.BLANK);
		}
		else {
			newsletterCampaignImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			newsletterCampaignImpl.setCreateDate(null);
		}
		else {
			newsletterCampaignImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			newsletterCampaignImpl.setModifiedDate(null);
		}
		else {
			newsletterCampaignImpl.setModifiedDate(new Date(modifiedDate));
		}

		newsletterCampaignImpl.setContentId(contentId);

		if (emailSubject == null) {
			newsletterCampaignImpl.setEmailSubject(StringPool.BLANK);
		}
		else {
			newsletterCampaignImpl.setEmailSubject(emailSubject);
		}

		if (senderEmail == null) {
			newsletterCampaignImpl.setSenderEmail(StringPool.BLANK);
		}
		else {
			newsletterCampaignImpl.setSenderEmail(senderEmail);
		}

		if (senderName == null) {
			newsletterCampaignImpl.setSenderName(StringPool.BLANK);
		}
		else {
			newsletterCampaignImpl.setSenderName(senderName);
		}

		newsletterCampaignImpl.setSent(sent);

		if (sendDate == Long.MIN_VALUE) {
			newsletterCampaignImpl.setSendDate(null);
		}
		else {
			newsletterCampaignImpl.setSendDate(new Date(sendDate));
		}

		newsletterCampaignImpl.resetOriginalValues();

		return newsletterCampaignImpl;
	}

	public String uuid;
	public long campaignId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long contentId;
	public String emailSubject;
	public String senderEmail;
	public String senderName;
	public boolean sent;
	public long sendDate;
}