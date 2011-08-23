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
 * The utility for the newsletter campaign remote service. This utility wraps {@link com.liferay.newsletter.service.impl.NewsletterCampaignServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsletterCampaignService
 * @see com.liferay.newsletter.service.base.NewsletterCampaignServiceBaseImpl
 * @see com.liferay.newsletter.service.impl.NewsletterCampaignServiceImpl
 * @generated
 */
public class NewsletterCampaignServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.newsletter.service.impl.NewsletterCampaignServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.newsletter.model.NewsletterCampaign addCampaign(
		long groupId, long contentId, java.lang.String emailSubject,
		java.lang.String senderEmail, java.lang.String senderName,
		int sendDateDay, int sendDateMonth, int sendDateYear,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addCampaign(groupId, contentId, emailSubject, senderEmail,
			senderName, sendDateDay, sendDateMonth, sendDateYear, serviceContext);
	}

	public static void deleteCampaign(long groupId, long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCampaign(groupId, campaignId);
	}

	public static void sendCampaign(long groupId, long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().sendCampaign(groupId, campaignId);
	}

	public static void resendCampaignToFailedContacts(long groupId,
		long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().resendCampaignToFailedContacts(groupId, campaignId);
	}

	public static void clearService() {
		_service = null;
	}

	public static NewsletterCampaignService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					NewsletterCampaignService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					NewsletterCampaignService.class.getName(),
					portletClassLoader);

			_service = new NewsletterCampaignServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(NewsletterCampaignServiceUtil.class,
				"_service");
			MethodCache.remove(NewsletterCampaignService.class);
		}

		return _service;
	}

	public void setService(NewsletterCampaignService service) {
		MethodCache.remove(NewsletterCampaignService.class);

		_service = service;

		ReferenceRegistry.registerReference(NewsletterCampaignServiceUtil.class,
			"_service");
		MethodCache.remove(NewsletterCampaignService.class);
	}

	private static NewsletterCampaignService _service;
}