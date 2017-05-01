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

package com.liferay.newsletter.service.persistence;

import com.liferay.newsletter.model.NewsletterCampaign;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the newsletter campaign service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsletterCampaignPersistenceImpl
 * @see NewsletterCampaignUtil
 * @generated
 */
public interface NewsletterCampaignPersistence extends BasePersistence<NewsletterCampaign> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NewsletterCampaignUtil} to access the newsletter campaign persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the newsletter campaign in the entity cache if it is enabled.
	*
	* @param newsletterCampaign the newsletter campaign
	*/
	public void cacheResult(
		com.liferay.newsletter.model.NewsletterCampaign newsletterCampaign);

	/**
	* Caches the newsletter campaigns in the entity cache if it is enabled.
	*
	* @param newsletterCampaigns the newsletter campaigns
	*/
	public void cacheResult(
		java.util.List<com.liferay.newsletter.model.NewsletterCampaign> newsletterCampaigns);

	/**
	* Creates a new newsletter campaign with the primary key. Does not add the newsletter campaign to the database.
	*
	* @param campaignId the primary key for the new newsletter campaign
	* @return the new newsletter campaign
	*/
	public com.liferay.newsletter.model.NewsletterCampaign create(
		long campaignId);

	/**
	* Removes the newsletter campaign with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param campaignId the primary key of the newsletter campaign
	* @return the newsletter campaign that was removed
	* @throws com.liferay.newsletter.NoSuchCampaignException if a newsletter campaign with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterCampaign remove(
		long campaignId)
		throws com.liferay.newsletter.NoSuchCampaignException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.newsletter.model.NewsletterCampaign updateImpl(
		com.liferay.newsletter.model.NewsletterCampaign newsletterCampaign,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the newsletter campaign with the primary key or throws a {@link com.liferay.newsletter.NoSuchCampaignException} if it could not be found.
	*
	* @param campaignId the primary key of the newsletter campaign
	* @return the newsletter campaign
	* @throws com.liferay.newsletter.NoSuchCampaignException if a newsletter campaign with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterCampaign findByPrimaryKey(
		long campaignId)
		throws com.liferay.newsletter.NoSuchCampaignException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the newsletter campaign with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param campaignId the primary key of the newsletter campaign
	* @return the newsletter campaign, or <code>null</code> if a newsletter campaign with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterCampaign fetchByPrimaryKey(
		long campaignId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the newsletter campaigns where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterCampaign> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the newsletter campaigns where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of newsletter campaigns
	* @param end the upper bound of the range of newsletter campaigns (not inclusive)
	* @return the range of matching newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterCampaign> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the newsletter campaigns where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of newsletter campaigns
	* @param end the upper bound of the range of newsletter campaigns (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterCampaign> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first newsletter campaign in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching newsletter campaign
	* @throws com.liferay.newsletter.NoSuchCampaignException if a matching newsletter campaign could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterCampaign findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchCampaignException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last newsletter campaign in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching newsletter campaign
	* @throws com.liferay.newsletter.NoSuchCampaignException if a matching newsletter campaign could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterCampaign findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchCampaignException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the newsletter campaigns before and after the current newsletter campaign in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param campaignId the primary key of the current newsletter campaign
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next newsletter campaign
	* @throws com.liferay.newsletter.NoSuchCampaignException if a newsletter campaign with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterCampaign[] findByUuid_PrevAndNext(
		long campaignId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchCampaignException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the newsletter campaign where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.newsletter.NoSuchCampaignException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching newsletter campaign
	* @throws com.liferay.newsletter.NoSuchCampaignException if a matching newsletter campaign could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterCampaign findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.newsletter.NoSuchCampaignException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the newsletter campaign where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching newsletter campaign, or <code>null</code> if a matching newsletter campaign could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterCampaign fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the newsletter campaign where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching newsletter campaign, or <code>null</code> if a matching newsletter campaign could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterCampaign fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the newsletter campaigns where contentId = &#63;.
	*
	* @param contentId the content ID
	* @return the matching newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterCampaign> findByContentId(
		long contentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the newsletter campaigns where contentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contentId the content ID
	* @param start the lower bound of the range of newsletter campaigns
	* @param end the upper bound of the range of newsletter campaigns (not inclusive)
	* @return the range of matching newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterCampaign> findByContentId(
		long contentId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the newsletter campaigns where contentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contentId the content ID
	* @param start the lower bound of the range of newsletter campaigns
	* @param end the upper bound of the range of newsletter campaigns (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterCampaign> findByContentId(
		long contentId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first newsletter campaign in the ordered set where contentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contentId the content ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching newsletter campaign
	* @throws com.liferay.newsletter.NoSuchCampaignException if a matching newsletter campaign could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterCampaign findByContentId_First(
		long contentId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchCampaignException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last newsletter campaign in the ordered set where contentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contentId the content ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching newsletter campaign
	* @throws com.liferay.newsletter.NoSuchCampaignException if a matching newsletter campaign could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterCampaign findByContentId_Last(
		long contentId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchCampaignException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the newsletter campaigns before and after the current newsletter campaign in the ordered set where contentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param campaignId the primary key of the current newsletter campaign
	* @param contentId the content ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next newsletter campaign
	* @throws com.liferay.newsletter.NoSuchCampaignException if a newsletter campaign with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterCampaign[] findByContentId_PrevAndNext(
		long campaignId, long contentId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchCampaignException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the newsletter campaigns where sendDate &le; &#63; and sent = &#63;.
	*
	* @param sendDate the send date
	* @param sent the sent
	* @return the matching newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterCampaign> findBySD_S(
		java.util.Date sendDate, boolean sent)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the newsletter campaigns where sendDate &le; &#63; and sent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param sendDate the send date
	* @param sent the sent
	* @param start the lower bound of the range of newsletter campaigns
	* @param end the upper bound of the range of newsletter campaigns (not inclusive)
	* @return the range of matching newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterCampaign> findBySD_S(
		java.util.Date sendDate, boolean sent, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the newsletter campaigns where sendDate &le; &#63; and sent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param sendDate the send date
	* @param sent the sent
	* @param start the lower bound of the range of newsletter campaigns
	* @param end the upper bound of the range of newsletter campaigns (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterCampaign> findBySD_S(
		java.util.Date sendDate, boolean sent, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first newsletter campaign in the ordered set where sendDate &le; &#63; and sent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param sendDate the send date
	* @param sent the sent
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching newsletter campaign
	* @throws com.liferay.newsletter.NoSuchCampaignException if a matching newsletter campaign could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterCampaign findBySD_S_First(
		java.util.Date sendDate, boolean sent,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchCampaignException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last newsletter campaign in the ordered set where sendDate &le; &#63; and sent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param sendDate the send date
	* @param sent the sent
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching newsletter campaign
	* @throws com.liferay.newsletter.NoSuchCampaignException if a matching newsletter campaign could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterCampaign findBySD_S_Last(
		java.util.Date sendDate, boolean sent,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchCampaignException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the newsletter campaigns before and after the current newsletter campaign in the ordered set where sendDate &le; &#63; and sent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param campaignId the primary key of the current newsletter campaign
	* @param sendDate the send date
	* @param sent the sent
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next newsletter campaign
	* @throws com.liferay.newsletter.NoSuchCampaignException if a newsletter campaign with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterCampaign[] findBySD_S_PrevAndNext(
		long campaignId, java.util.Date sendDate, boolean sent,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchCampaignException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the newsletter campaigns.
	*
	* @return the newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterCampaign> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.newsletter.model.NewsletterCampaign> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the newsletter campaigns.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of newsletter campaigns
	* @param end the upper bound of the range of newsletter campaigns (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterCampaign> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the newsletter campaigns where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the newsletter campaign where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.newsletter.NoSuchCampaignException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the newsletter campaigns where contentId = &#63; from the database.
	*
	* @param contentId the content ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByContentId(long contentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the newsletter campaigns where sendDate &le; &#63; and sent = &#63; from the database.
	*
	* @param sendDate the send date
	* @param sent the sent
	* @throws SystemException if a system exception occurred
	*/
	public void removeBySD_S(java.util.Date sendDate, boolean sent)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the newsletter campaigns from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of newsletter campaigns where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of newsletter campaigns where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of newsletter campaigns where contentId = &#63;.
	*
	* @param contentId the content ID
	* @return the number of matching newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public int countByContentId(long contentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of newsletter campaigns where sendDate &le; &#63; and sent = &#63;.
	*
	* @param sendDate the send date
	* @param sent the sent
	* @return the number of matching newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public int countBySD_S(java.util.Date sendDate, boolean sent)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of newsletter campaigns.
	*
	* @return the number of newsletter campaigns
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public NewsletterCampaign remove(NewsletterCampaign newsletterCampaign)
		throws SystemException;
}