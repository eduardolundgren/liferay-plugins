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
 * This class is a wrapper for {@link NewsletterContentService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       NewsletterContentService
 * @generated
 */
public class NewsletterContentServiceWrapper implements NewsletterContentService {
	public NewsletterContentServiceWrapper(
		NewsletterContentService newsletterContentService) {
		_newsletterContentService = newsletterContentService;
	}

	public com.liferay.newsletter.model.NewsletterContent addContent(
		long groupId, long articleId, java.lang.String title,
		java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _newsletterContentService.addContent(groupId, articleId, title,
			content, serviceContext);
	}

	public void deleteContent(long groupId, long contentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_newsletterContentService.deleteContent(groupId, contentId);
	}

	public void updateContent(long groupId, long contentId, long articleId,
		java.lang.String title, java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_newsletterContentService.updateContent(groupId, contentId, articleId,
			title, content, serviceContext);
	}

	public NewsletterContentService getWrappedNewsletterContentService() {
		return _newsletterContentService;
	}

	public void setWrappedNewsletterContentService(
		NewsletterContentService newsletterContentService) {
		_newsletterContentService = newsletterContentService;
	}

	private NewsletterContentService _newsletterContentService;
}