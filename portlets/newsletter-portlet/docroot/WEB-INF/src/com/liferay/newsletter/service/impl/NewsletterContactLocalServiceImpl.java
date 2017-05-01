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

import com.liferay.newsletter.NoSuchContactException;
import com.liferay.newsletter.model.NewsletterContact;
import com.liferay.newsletter.service.base.NewsletterContactLocalServiceBaseImpl;
import com.liferay.portal.EmailAddressException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.List;

/**
 * @author Bruno Pinheiro
 * @author Marcellus Tavares
 */
public class NewsletterContactLocalServiceImpl
	extends NewsletterContactLocalServiceBaseImpl {

	public NewsletterContact addContact(
			long userId, long groupId, String email, String name,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		validate(email);

		long contactId = counterLocalService.increment();

		NewsletterContact contact =
			newsletterContactPersistence.create(contactId);

		contact.setGroupId(groupId);
		contact.setCompanyId(user.getCompanyId());
		contact.setUserId(user.getUserId());
		contact.setUserName(user.getFullName());
		contact.setCreateDate(serviceContext.getCreateDate(now));
		contact.setModifiedDate(serviceContext.getModifiedDate(now));
		contact.setEmail(email);
		contact.setName(name);

		return newsletterContactPersistence.update(contact, false);
	}

	public NewsletterContact getContact(long contactId)
		throws PortalException, SystemException {

		return newsletterContactPersistence.findByPrimaryKey(contactId);
	}

	public int getContactCountByContent(long contentId)
		throws PortalException, SystemException {

		return newsletterContactFinder.countByCampaignContent(contentId);
	}

	public int getContactCountByCampaign(long campaignId)
		throws PortalException, SystemException {

		return newsletterContactFinder.countByCampaign(campaignId);
	}

	public NewsletterContact getContact(
			long userId, long scopeGroupId, String email,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		NewsletterContact contact = null;

		try {
			contact = newsletterContactPersistence.findByEmail(email);
		}
		catch (NoSuchContactException e) {
			contact = addContact(
				userId,scopeGroupId, email, StringPool.BLANK,
				serviceContext);
		}

		return contact;
	}

	public List<NewsletterContact> search(
			long companyId, long groupId, String keywords, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return newsletterContactFinder.findByKeywords(
			companyId, groupId, keywords, start, end, orderByComparator);
	}

	public List<NewsletterContact> search(
			long companyId, long groupId, long campaignId, String keywords,
			int start, int end,	OrderByComparator orderByComparator)
		throws SystemException {

		return newsletterContactFinder.findByKeywords(
			companyId, groupId, campaignId, keywords, start, end,
			orderByComparator);
	}

	public List<NewsletterContact> search(
			long companyId, long groupId, long campaignId, String name,
			String email, boolean sent, int start, int end,
			boolean isAndOperator, OrderByComparator orderByComparator)
		throws SystemException {

		return newsletterContactFinder.findByC_G_C_E_N_S(
			companyId, groupId, campaignId, email, name, sent, start, end,
			isAndOperator, orderByComparator);
	}

	public int searchCount(
			long companyId, long groupId, long campaignId, String keywords,
			int start, int end,	OrderByComparator orderByComparator)
		throws SystemException {

		return newsletterContactFinder.countByKeywords(
			companyId, groupId, campaignId, keywords, start, end,
			orderByComparator);
	}

	public int searchCount(
			long companyId, long groupId, String keywords, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return newsletterContactFinder.countByKeywords(
			companyId, groupId, keywords, start, end, orderByComparator);
	}

	public int searchCount(
			long companyId, long groupId, long campaignId, String name,
			String email, boolean sent, int start, int end,
			boolean isAndOperator, OrderByComparator orderByComparator)
		throws SystemException {

		return newsletterContactFinder.countByC_G_C_E_N_S(
			companyId, groupId, campaignId, email, name, sent, start, end,
			isAndOperator, orderByComparator);
	}

	protected void validate(String email) throws PortalException {
		if (!Validator.isEmailAddress(email)) {
			throw new EmailAddressException();
		}
	}

}