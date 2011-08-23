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

import com.liferay.newsletter.model.NewsletterLog;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

/**
 * The cache model class for representing NewsletterLog in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see NewsletterLog
 * @generated
 */
public class NewsletterLogCacheModel implements CacheModel<NewsletterLog> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{logId=");
		sb.append(logId);
		sb.append(", campaignId=");
		sb.append(campaignId);
		sb.append(", contactId=");
		sb.append(contactId);
		sb.append(", sent=");
		sb.append(sent);
		sb.append("}");

		return sb.toString();
	}

	public NewsletterLog toEntityModel() {
		NewsletterLogImpl newsletterLogImpl = new NewsletterLogImpl();

		newsletterLogImpl.setLogId(logId);
		newsletterLogImpl.setCampaignId(campaignId);
		newsletterLogImpl.setContactId(contactId);
		newsletterLogImpl.setSent(sent);

		newsletterLogImpl.resetOriginalValues();

		return newsletterLogImpl;
	}

	public long logId;
	public long campaignId;
	public long contactId;
	public boolean sent;
}