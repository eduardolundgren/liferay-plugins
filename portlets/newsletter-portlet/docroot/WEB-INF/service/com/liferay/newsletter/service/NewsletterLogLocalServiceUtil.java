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
 * The utility for the newsletter log local service. This utility wraps {@link com.liferay.newsletter.service.impl.NewsletterLogLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsletterLogLocalService
 * @see com.liferay.newsletter.service.base.NewsletterLogLocalServiceBaseImpl
 * @see com.liferay.newsletter.service.impl.NewsletterLogLocalServiceImpl
 * @generated
 */
public class NewsletterLogLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.newsletter.service.impl.NewsletterLogLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the newsletter log to the database. Also notifies the appropriate model listeners.
	*
	* @param newsletterLog the newsletter log
	* @return the newsletter log that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.newsletter.model.NewsletterLog addNewsletterLog(
		com.liferay.newsletter.model.NewsletterLog newsletterLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addNewsletterLog(newsletterLog);
	}

	/**
	* Creates a new newsletter log with the primary key. Does not add the newsletter log to the database.
	*
	* @param logId the primary key for the new newsletter log
	* @return the new newsletter log
	*/
	public static com.liferay.newsletter.model.NewsletterLog createNewsletterLog(
		long logId) {
		return getService().createNewsletterLog(logId);
	}

	/**
	* Deletes the newsletter log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param logId the primary key of the newsletter log
	* @throws PortalException if a newsletter log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteNewsletterLog(long logId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteNewsletterLog(logId);
	}

	/**
	* Deletes the newsletter log from the database. Also notifies the appropriate model listeners.
	*
	* @param newsletterLog the newsletter log
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteNewsletterLog(
		com.liferay.newsletter.model.NewsletterLog newsletterLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteNewsletterLog(newsletterLog);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the newsletter log with the primary key.
	*
	* @param logId the primary key of the newsletter log
	* @return the newsletter log
	* @throws PortalException if a newsletter log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.newsletter.model.NewsletterLog getNewsletterLog(
		long logId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getNewsletterLog(logId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the newsletter logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of newsletter logs
	* @param end the upper bound of the range of newsletter logs (not inclusive)
	* @return the range of newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.newsletter.model.NewsletterLog> getNewsletterLogs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getNewsletterLogs(start, end);
	}

	/**
	* Returns the number of newsletter logs.
	*
	* @return the number of newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public static int getNewsletterLogsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getNewsletterLogsCount();
	}

	/**
	* Updates the newsletter log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param newsletterLog the newsletter log
	* @return the newsletter log that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.newsletter.model.NewsletterLog updateNewsletterLog(
		com.liferay.newsletter.model.NewsletterLog newsletterLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateNewsletterLog(newsletterLog);
	}

	/**
	* Updates the newsletter log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param newsletterLog the newsletter log
	* @param merge whether to merge the newsletter log with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the newsletter log that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.newsletter.model.NewsletterLog updateNewsletterLog(
		com.liferay.newsletter.model.NewsletterLog newsletterLog, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateNewsletterLog(newsletterLog, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static com.liferay.newsletter.model.NewsletterLog addLog(
		long campaignId, long contactId, boolean sent,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addLog(campaignId, contactId, sent, serviceContext);
	}

	public static void deleteLog(com.liferay.newsletter.model.NewsletterLog log)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteLog(log);
	}

	public static com.liferay.newsletter.model.NewsletterLog getLog(
		long campaignId, long contactId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLog(campaignId, contactId);
	}

	public static java.util.List<com.liferay.newsletter.model.NewsletterLog> getLogsByCampaignId(
		long campaignId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLogsByCampaignId(campaignId);
	}

	public static java.util.List<com.liferay.newsletter.model.NewsletterLog> getLogsByContactId(
		long contactId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLogsByContactId(contactId);
	}

	public static java.util.List<com.liferay.newsletter.model.NewsletterLog> getLogsByC_S(
		long campaignId, boolean sent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLogsByC_S(campaignId, sent);
	}

	public static void clearService() {
		_service = null;
	}

	public static NewsletterLogLocalService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					NewsletterLogLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					NewsletterLogLocalService.class.getName(),
					portletClassLoader);

			_service = new NewsletterLogLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(NewsletterLogLocalServiceUtil.class,
				"_service");
			MethodCache.remove(NewsletterLogLocalService.class);
		}

		return _service;
	}

	public void setService(NewsletterLogLocalService service) {
		MethodCache.remove(NewsletterLogLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(NewsletterLogLocalServiceUtil.class,
			"_service");
		MethodCache.remove(NewsletterLogLocalService.class);
	}

	private static NewsletterLogLocalService _service;
}