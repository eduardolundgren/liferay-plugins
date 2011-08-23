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

import com.liferay.newsletter.model.NewsletterContact;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing NewsletterContact in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see NewsletterContact
 * @generated
 */
public class NewsletterContactCacheModel implements CacheModel<NewsletterContact> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{contactId=");
		sb.append(contactId);
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
		sb.append(", email=");
		sb.append(email);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	public NewsletterContact toEntityModel() {
		NewsletterContactImpl newsletterContactImpl = new NewsletterContactImpl();

		newsletterContactImpl.setContactId(contactId);
		newsletterContactImpl.setGroupId(groupId);
		newsletterContactImpl.setCompanyId(companyId);
		newsletterContactImpl.setUserId(userId);

		if (userName == null) {
			newsletterContactImpl.setUserName(StringPool.BLANK);
		}
		else {
			newsletterContactImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			newsletterContactImpl.setCreateDate(null);
		}
		else {
			newsletterContactImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			newsletterContactImpl.setModifiedDate(null);
		}
		else {
			newsletterContactImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (email == null) {
			newsletterContactImpl.setEmail(StringPool.BLANK);
		}
		else {
			newsletterContactImpl.setEmail(email);
		}

		if (name == null) {
			newsletterContactImpl.setName(StringPool.BLANK);
		}
		else {
			newsletterContactImpl.setName(name);
		}

		newsletterContactImpl.resetOriginalValues();

		return newsletterContactImpl;
	}

	public long contactId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String email;
	public String name;
}