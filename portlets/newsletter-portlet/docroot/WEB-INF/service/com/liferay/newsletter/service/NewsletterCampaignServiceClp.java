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

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * @author Brian Wing Shun Chan
 */
public class NewsletterCampaignServiceClp implements NewsletterCampaignService {
	public NewsletterCampaignServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;

		_addCampaignMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
				"addCampaign", long.class, long.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class, int.class,
				int.class, int.class,
				com.liferay.portal.service.ServiceContext.class);

		_deleteCampaignMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteCampaign", long.class, long.class);

		_sendCampaignMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
				"sendCampaign", long.class, long.class);

		_resendCampaignToFailedContactsMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
				"resendCampaignToFailedContacts", long.class, long.class);
	}

	public com.liferay.newsletter.model.NewsletterCampaign addCampaign(
		long groupId, long contentId, java.lang.String emailSubject,
		java.lang.String senderEmail, java.lang.String senderName,
		int sendDateDay, int sendDateMonth, int sendDateYear,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addCampaignMethodKey0,
				groupId, contentId, ClpSerializer.translateInput(emailSubject),
				ClpSerializer.translateInput(senderEmail),
				ClpSerializer.translateInput(senderName), sendDateDay,
				sendDateMonth, sendDateYear,
				ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.newsletter.model.NewsletterCampaign)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteCampaign(long groupId, long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteCampaignMethodKey1,
				groupId, campaignId);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void sendCampaign(long groupId, long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_sendCampaignMethodKey2,
				groupId, campaignId);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void resendCampaignToFailedContacts(long groupId, long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_resendCampaignToFailedContactsMethodKey3,
				groupId, campaignId);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private ClassLoaderProxy _classLoaderProxy;
	private MethodKey _addCampaignMethodKey0;
	private MethodKey _deleteCampaignMethodKey1;
	private MethodKey _sendCampaignMethodKey2;
	private MethodKey _resendCampaignToFailedContactsMethodKey3;
}