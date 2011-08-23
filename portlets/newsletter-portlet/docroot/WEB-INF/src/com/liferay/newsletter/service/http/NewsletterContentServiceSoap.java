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

import com.liferay.newsletter.service.NewsletterContentServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.liferay.newsletter.service.NewsletterContentServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.newsletter.model.NewsletterContentSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.newsletter.model.NewsletterContent}, that is translated to a
 * {@link com.liferay.newsletter.model.NewsletterContentSoap}. Methods that SOAP cannot
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
 * @see       NewsletterContentServiceHttp
 * @see       com.liferay.newsletter.model.NewsletterContentSoap
 * @see       com.liferay.newsletter.service.NewsletterContentServiceUtil
 * @generated
 */
public class NewsletterContentServiceSoap {
	public static com.liferay.newsletter.model.NewsletterContentSoap addContent(
		long groupId, long articleId, java.lang.String title,
		java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.newsletter.model.NewsletterContent returnValue = NewsletterContentServiceUtil.addContent(groupId,
					articleId, title, content, serviceContext);

			return com.liferay.newsletter.model.NewsletterContentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteContent(long groupId, long contentId)
		throws RemoteException {
		try {
			NewsletterContentServiceUtil.deleteContent(groupId, contentId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void updateContent(long groupId, long contentId,
		long articleId, java.lang.String title, java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			NewsletterContentServiceUtil.updateContent(groupId, contentId,
				articleId, title, content, serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(NewsletterContentServiceSoap.class);
}