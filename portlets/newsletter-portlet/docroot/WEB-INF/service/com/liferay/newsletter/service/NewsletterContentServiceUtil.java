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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the newsletter content remote service. This utility wraps {@link com.liferay.newsletter.service.impl.NewsletterContentServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsletterContentService
 * @see com.liferay.newsletter.service.base.NewsletterContentServiceBaseImpl
 * @see com.liferay.newsletter.service.impl.NewsletterContentServiceImpl
 * @generated
 */
public class NewsletterContentServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.newsletter.service.impl.NewsletterContentServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.newsletter.model.NewsletterContent addContent(
		long groupId, long articleId, java.lang.String title,
		java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addContent(groupId, articleId, title, content,
			serviceContext);
	}

	public static void deleteContent(long groupId, long contentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteContent(groupId, contentId);
	}

	public static void updateContent(long groupId, long contentId,
		long articleId, java.lang.String title, java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateContent(groupId, contentId, articleId, title, content,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static NewsletterContentService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					NewsletterContentService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					NewsletterContentService.class.getName(), portletClassLoader);

			_service = new NewsletterContentServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(NewsletterContentServiceUtil.class,
				"_service");
			MethodCache.remove(NewsletterContentService.class);
		}

		return _service;
	}

	public void setService(NewsletterContentService service) {
		MethodCache.remove(NewsletterContentService.class);

		_service = service;

		ReferenceRegistry.registerReference(NewsletterContentServiceUtil.class,
			"_service");
		MethodCache.remove(NewsletterContentService.class);
	}

	private static NewsletterContentService _service;
}