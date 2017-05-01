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

package com.liferay.newsletter.service.http;

import com.liferay.newsletter.service.NewsletterCampaignServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.liferay.newsletter.service.NewsletterCampaignServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.newsletter.model.NewsletterCampaignSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.newsletter.model.NewsletterCampaign}, that is translated to a
 * {@link com.liferay.newsletter.model.NewsletterCampaignSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/tunnel-web/secure/axis. Set the property
 * <b>tunnel.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       NewsletterCampaignServiceHttp
 * @see       com.liferay.newsletter.model.NewsletterCampaignSoap
 * @see       com.liferay.newsletter.service.NewsletterCampaignServiceUtil
 * @generated
 */
public class NewsletterCampaignServiceSoap {
	public static com.liferay.newsletter.model.NewsletterCampaignSoap addCampaign(
		long groupId, long contentId, java.lang.String emailSubject,
		java.lang.String senderEmail, java.lang.String senderName,
		int sendDateDay, int sendDateMonth, int sendDateYear,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.newsletter.model.NewsletterCampaign returnValue = NewsletterCampaignServiceUtil.addCampaign(groupId,
					contentId, emailSubject, senderEmail, senderName,
					sendDateDay, sendDateMonth, sendDateYear, serviceContext);

			return com.liferay.newsletter.model.NewsletterCampaignSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteCampaign(long groupId, long campaignId)
		throws RemoteException {
		try {
			NewsletterCampaignServiceUtil.deleteCampaign(groupId, campaignId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void sendCampaign(long groupId, long campaignId)
		throws RemoteException {
		try {
			NewsletterCampaignServiceUtil.sendCampaign(groupId, campaignId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void resendCampaignToFailedContacts(long groupId,
		long campaignId) throws RemoteException {
		try {
			NewsletterCampaignServiceUtil.resendCampaignToFailedContacts(groupId,
				campaignId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(NewsletterCampaignServiceSoap.class);
}