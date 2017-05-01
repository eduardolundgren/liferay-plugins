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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing NewsletterContent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see NewsletterContent
 * @generated
 */
public class NewsletterContentCacheModel implements CacheModel<NewsletterContent> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", contentId=");
		sb.append(contentId);
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
		sb.append(", title=");
		sb.append(title);
		sb.append(", content=");
		sb.append(content);
		sb.append(", articleId=");
		sb.append(articleId);
		sb.append("}");

		return sb.toString();
	}

	public NewsletterContent toEntityModel() {
		NewsletterContentImpl newsletterContentImpl = new NewsletterContentImpl();

		if (uuid == null) {
			newsletterContentImpl.setUuid(StringPool.BLANK);
		}
		else {
			newsletterContentImpl.setUuid(uuid);
		}

		newsletterContentImpl.setContentId(contentId);
		newsletterContentImpl.setGroupId(groupId);
		newsletterContentImpl.setCompanyId(companyId);
		newsletterContentImpl.setUserId(userId);

		if (userName == null) {
			newsletterContentImpl.setUserName(StringPool.BLANK);
		}
		else {
			newsletterContentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			newsletterContentImpl.setCreateDate(null);
		}
		else {
			newsletterContentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			newsletterContentImpl.setModifiedDate(null);
		}
		else {
			newsletterContentImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			newsletterContentImpl.setTitle(StringPool.BLANK);
		}
		else {
			newsletterContentImpl.setTitle(title);
		}

		if (content == null) {
			newsletterContentImpl.setContent(StringPool.BLANK);
		}
		else {
			newsletterContentImpl.setContent(content);
		}

		newsletterContentImpl.setArticleId(articleId);

		newsletterContentImpl.resetOriginalValues();

		return newsletterContentImpl;
	}

	public String uuid;
	public long contentId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public String content;
	public long articleId;
}