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

import com.liferay.newsletter.ContentException;
import com.liferay.newsletter.TitleException;
import com.liferay.newsletter.model.NewsletterCampaign;
import com.liferay.newsletter.model.NewsletterContent;
import com.liferay.newsletter.service.base.NewsletterContentLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.List;

/**
 * @author Bruno Pinheiro
 */
public class NewsletterContentLocalServiceImpl
	extends NewsletterContentLocalServiceBaseImpl {

	public NewsletterContent addContent(
			long userId, long groupId, long articleId, String title,
			String content, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		validate(title, content);

		long contentId = counterLocalService.increment();

		NewsletterContent newsletterContent =
			newsletterContentPersistence.create(contentId);

		newsletterContent.setUuid(serviceContext.getUuid());
		newsletterContent.setGroupId(groupId);
		newsletterContent.setCompanyId(user.getCompanyId());
		newsletterContent.setUserId(user.getUserId());
		newsletterContent.setUserName(user.getFullName());
		newsletterContent.setCreateDate(serviceContext.getCreateDate(now));
		newsletterContent.setModifiedDate(serviceContext.getModifiedDate(now));
		newsletterContent.setTitle(title);
		newsletterContent.setContent(content);

		newsletterContentPersistence.update(newsletterContent, false);

		// Resources

		resourceLocalService.addResources(
			newsletterContent.getCompanyId(), newsletterContent.getGroupId(),
			newsletterContent.getUserId(), NewsletterContent.class.getName(),
			newsletterContent.getPrimaryKey(), false, true, true);

		return newsletterContent;
	}

	public void addContentResources(
			NewsletterContent content, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			content.getCompanyId(), content.getGroupId(), content.getUserId(),
			NewsletterContent.class.getName(), content.getPrimaryKey(), false,
			addGroupPermissions, addGuestPermissions);
	}

	public void addContentResources(
			NewsletterContent content, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			content.getCompanyId(), content.getGroupId(), content.getUserId(),
			NewsletterContent.class.getName(), content.getPrimaryKey(),
			groupPermissions, guestPermissions);
	}

	public void deleteContent(NewsletterContent content)
		throws PortalException, SystemException {

		// Resources

		resourceLocalService.deleteResource(
			content.getCompanyId(),	NewsletterContent.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,	content.getPrimaryKey());

		// Campaigns

		List<NewsletterCampaign> campaigns = content.getCampaigns();

		for (NewsletterCampaign campaign : campaigns) {
			newsletterCampaignLocalService.deleteCampaign(campaign);
		}

		// Content

		newsletterContentPersistence.remove(content);

	}

	public void deleteContent(long contentId)
		throws PortalException, SystemException {

		NewsletterContent newsletterContent =
			newsletterContentPersistence.findByPrimaryKey(contentId);

		deleteContent(newsletterContent);
	}

	public NewsletterContent getContent(long contentId)
		throws PortalException, SystemException {

		return newsletterContentPersistence.findByPrimaryKey(contentId);
	}

	public List<NewsletterContent> search(
			long companyId, long groupId, String keywords, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException{

		return newsletterContentFinder.findByKeywords(
			companyId, groupId, keywords, start, end, orderByComparator);
	}

	public NewsletterContent updateContent(
			long contentId, long articleId, String title, String content,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		validate(title, content);

		NewsletterContent newsletterContent =
			newsletterContentPersistence.findByPrimaryKey(contentId);

		Date modifiedDate = serviceContext.getModifiedDate(null);

		newsletterContent.setModifiedDate(modifiedDate);
		newsletterContent.setArticleId(articleId);
		newsletterContent.setTitle(title);
		newsletterContent.setContent(content);

		// Resources

		resourceLocalService.updateResources(
			newsletterContent.getCompanyId(), newsletterContent.getGroupId(),
			NewsletterContent.class.getName(), newsletterContent.getContentId(),
			serviceContext.getGroupPermissions(),
			serviceContext.getGuestPermissions());

		return newsletterContentPersistence.update(newsletterContent, false);
	}

	public void validate(String title, String content) throws PortalException {
		if (Validator.isNull(title)) {
			throw new TitleException();
		}
		else if (Validator.isNull(content)) {
			throw new ContentException();
		}
	}

}