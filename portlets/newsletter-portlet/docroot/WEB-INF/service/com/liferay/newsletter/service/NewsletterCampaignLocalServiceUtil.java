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
 * The utility for the newsletter campaign local service. This utility wraps {@link com.liferay.newsletter.service.impl.NewsletterCampaignLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsletterCampaignLocalService
 * @see com.liferay.newsletter.service.base.NewsletterCampaignLocalServiceBaseImpl
 * @see com.liferay.newsletter.service.impl.NewsletterCampaignLocalServiceImpl
 * @generated
 */
public class NewsletterCampaignLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.newsletter.service.impl.NewsletterCampaignLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the newsletter campaign to the database. Also notifies the appropriate model listeners.
	*
	* @param newsletterCampaign the newsletter campaign
	* @return the newsletter campaign that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.newsletter.model.NewsletterCampaign addNewsletterCampaign(
		com.liferay.newsletter.model.NewsletterCampaign newsletterCampaign)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addNewsletterCampaign(newsletterCampaign);
	}

	/**
	* Creates a new newsletter campaign with the primary key. Does not add the newsletter campaign to the database.
	*
	* @param campaignId the primary key for the new newsletter campaign
	* @return the new newsletter campaign
	*/
	public static com.liferay.newsletter.model.NewsletterCampaign createNewsletterCampaign(
		long campaignId) {
		return getService().createNewsletterCampaign(campaignId);
	}

	/**
	* Deletes the newsletter campaign with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param campaignId the primary key of the newsletter campaign
	* @throws PortalException if a newsletter campaign with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteNewsletterCampaign(long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteNewsletterCampaign(campaignId);
	}

	/**
	* Deletes the newsletter campaign from the database. Also notifies the appropriate model listeners.
	*
	* @param newsletterCampaign the newsletter campaign
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteNewsletterCampaign(
		com.liferay.newsletter.model.NewsletterCampaign newsletterCampaign)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteNewsletterCampaign(newsletterCampaign);
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
	* Returns the newsletter campaign with the primary key.
	*
	* @param campaignId the primary key of the newsletter campaign
	* @return the newsletter campaign
	* @throws PortalException if a newsletter campaign with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.newsletter.model.NewsletterCampaign getNewsletterCampaign(
		long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getNewsletterCampaign(campaignId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the newsletter campaign with the UUID in the group.
	*
	* @param uuid the UUID of newsletter campaign
	* @param groupId the group id of the newsletter campaign
	* @return the newsletter campaign
	* @throws PortalException if a newsletter campaign with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.newsletter.model.NewsletterCampaign getNewsletterCampaignByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getNewsletterCampaignByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the newsletter campaigns.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of newsletter campaigns
	* @param end the upper bound of the range of newsletter campaigns (not inclusive)
	* @return the range of newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.newsletter.model.NewsletterCampaign> getNewsletterCampaigns(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getNewsletterCampaigns(start, end);
	}

	/**
	* Returns the number of newsletter campaigns.
	*
	* @return the number of newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public static int getNewsletterCampaignsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getNewsletterCampaignsCount();
	}

	/**
	* Updates the newsletter campaign in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param newsletterCampaign the newsletter campaign
	* @return the newsletter campaign that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.newsletter.model.NewsletterCampaign updateNewsletterCampaign(
		com.liferay.newsletter.model.NewsletterCampaign newsletterCampaign)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateNewsletterCampaign(newsletterCampaign);
	}

	/**
	* Updates the newsletter campaign in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param newsletterCampaign the newsletter campaign
	* @param merge whether to merge the newsletter campaign with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the newsletter campaign that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.newsletter.model.NewsletterCampaign updateNewsletterCampaign(
		com.liferay.newsletter.model.NewsletterCampaign newsletterCampaign,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateNewsletterCampaign(newsletterCampaign, merge);
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

	public static com.liferay.newsletter.model.NewsletterCampaign addCampaign(
		long userId, long groupId, long contentId,
		java.lang.String emailSubject, java.lang.String senderEmail,
		java.lang.String senderName, int sendDateDay, int sendDateMonth,
		int sendDateYear,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addCampaign(userId, groupId, contentId, emailSubject,
			senderEmail, senderName, sendDateDay, sendDateMonth, sendDateYear,
			serviceContext);
	}

	public static void checkCampaigns()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().checkCampaigns();
	}

	public static void deleteCampaign(
		com.liferay.newsletter.model.NewsletterCampaign campaign)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCampaign(campaign);
	}

	public static void deleteCampaign(long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCampaign(campaignId);
	}

	public static com.liferay.newsletter.model.NewsletterCampaign getCampaign(
		long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCampaign(campaignId);
	}

	public static java.util.List<com.liferay.newsletter.model.NewsletterCampaign> getCampaigns(
		long contentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCampaigns(contentId);
	}

	public static java.util.List<com.liferay.newsletter.model.NewsletterCampaign> getCampaignsBySendDate(
		java.util.Date sentDateLT, boolean sent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCampaignsBySendDate(sentDateLT, sent);
	}

	public static int getCampaignsCount(long contentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCampaignsCount(contentId);
	}

	public static void sendCampaign(long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().sendCampaign(campaignId);
	}

	public static void sendCampaign(
		com.liferay.newsletter.model.NewsletterCampaign campaign)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().sendCampaign(campaign);
	}

	public static void resendCampaignToFailedContacts(long campaignId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().resendCampaignToFailedContacts(campaignId);
	}

	public static void resendCampaignToFailedContacts(
		com.liferay.newsletter.model.NewsletterCampaign campaign)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().resendCampaignToFailedContacts(campaign);
	}

	public static void clearService() {
		_service = null;
	}

	public static NewsletterCampaignLocalService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					NewsletterCampaignLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					NewsletterCampaignLocalService.class.getName(),
					portletClassLoader);

			_service = new NewsletterCampaignLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(NewsletterCampaignLocalServiceUtil.class,
				"_service");
			MethodCache.remove(NewsletterCampaignLocalService.class);
		}

		return _service;
	}

	public void setService(NewsletterCampaignLocalService service) {
		MethodCache.remove(NewsletterCampaignLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(NewsletterCampaignLocalServiceUtil.class,
			"_service");
		MethodCache.remove(NewsletterCampaignLocalService.class);
	}

	private static NewsletterCampaignLocalService _service;
}