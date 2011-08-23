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

import com.liferay.newsletter.model.NewsletterContent;
import com.liferay.newsletter.service.base.NewsletterContentServiceBaseImpl;
import com.liferay.newsletter.service.permission.ContentPermission;
import com.liferay.newsletter.service.permission.NewsletterPermission;
import com.liferay.newsletter.util.NewsletterKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

/**
 * @author Bruno Pinheiro
 */
public class NewsletterContentServiceImpl
	extends NewsletterContentServiceBaseImpl {

	public NewsletterContent addContent(
			long groupId, long articleId, String title,	String content,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		NewsletterPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			NewsletterKeys.ADD_CONTENT);

		return newsletterContentLocalService.addContent(
			getUserId(), groupId, articleId, title, content, serviceContext);
	}

	public void deleteContent(long groupId, long contentId)
		throws PortalException, SystemException {

		ContentPermission.check(
			getPermissionChecker(), groupId, contentId, NewsletterKeys.DELETE);

		newsletterContentLocalService.deleteContent(contentId);
	}

	public void updateContent(
			long groupId, long contentId, long articleId, String title,
			String content,	ServiceContext serviceContext)
		throws PortalException, SystemException {

		ContentPermission.check(
			getPermissionChecker(), groupId, contentId, NewsletterKeys.UPDATE);

		newsletterContentLocalService.updateContent(
			contentId, articleId, title, content, serviceContext);
	}

}