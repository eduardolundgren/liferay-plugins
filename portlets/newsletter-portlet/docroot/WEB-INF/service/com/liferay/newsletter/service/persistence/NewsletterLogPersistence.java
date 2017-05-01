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

import com.liferay.newsletter.model.NewsletterLog;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the newsletter log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsletterLogPersistenceImpl
 * @see NewsletterLogUtil
 * @generated
 */
public interface NewsletterLogPersistence extends BasePersistence<NewsletterLog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NewsletterLogUtil} to access the newsletter log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the newsletter log in the entity cache if it is enabled.
	*
	* @param newsletterLog the newsletter log
	*/
	public void cacheResult(
		com.liferay.newsletter.model.NewsletterLog newsletterLog);

	/**
	* Caches the newsletter logs in the entity cache if it is enabled.
	*
	* @param newsletterLogs the newsletter logs
	*/
	public void cacheResult(
		java.util.List<com.liferay.newsletter.model.NewsletterLog> newsletterLogs);

	/**
	* Creates a new newsletter log with the primary key. Does not add the newsletter log to the database.
	*
	* @param logId the primary key for the new newsletter log
	* @return the new newsletter log
	*/
	public com.liferay.newsletter.model.NewsletterLog create(long logId);

	/**
	* Removes the newsletter log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param logId the primary key of the newsletter log
	* @return the newsletter log that was removed
	* @throws com.liferay.newsletter.NoSuchLogException if a newsletter log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterLog remove(long logId)
		throws com.liferay.newsletter.NoSuchLogException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.newsletter.model.NewsletterLog updateImpl(
		com.liferay.newsletter.model.NewsletterLog newsletterLog, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the newsletter log with the primary key or throws a {@link com.liferay.newsletter.NoSuchLogException} if it could not be found.
	*
	* @param logId the primary key of the newsletter log
	* @return the newsletter log
	* @throws com.liferay.newsletter.NoSuchLogException if a newsletter log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterLog findByPrimaryKey(
		long logId)
		throws com.liferay.newsletter.NoSuchLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the newsletter log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param logId the primary key of the newsletter log
	* @return the newsletter log, or <code>null</code> if a newsletter log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterLog fetchByPrimaryKey(
		long logId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the newsletter logs where campaignId = &#63;.
	*
	* @param campaignId the campaign ID
	* @return the matching newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterLog> findByCampaignId(
		long campaignId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the newsletter logs where campaignId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param campaignId the campaign ID
	* @param start the lower bound of the range of newsletter logs
	* @param end the upper bound of the range of newsletter logs (not inclusive)
	* @return the range of matching newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterLog> findByCampaignId(
		long campaignId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the newsletter logs where campaignId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param campaignId the campaign ID
	* @param start the lower bound of the range of newsletter logs
	* @param end the upper bound of the range of newsletter logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterLog> findByCampaignId(
		long campaignId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first newsletter log in the ordered set where campaignId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param campaignId the campaign ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching newsletter log
	* @throws com.liferay.newsletter.NoSuchLogException if a matching newsletter log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterLog findByCampaignId_First(
		long campaignId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last newsletter log in the ordered set where campaignId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param campaignId the campaign ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching newsletter log
	* @throws com.liferay.newsletter.NoSuchLogException if a matching newsletter log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterLog findByCampaignId_Last(
		long campaignId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the newsletter logs before and after the current newsletter log in the ordered set where campaignId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param logId the primary key of the current newsletter log
	* @param campaignId the campaign ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next newsletter log
	* @throws com.liferay.newsletter.NoSuchLogException if a newsletter log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterLog[] findByCampaignId_PrevAndNext(
		long logId, long campaignId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the newsletter logs where contactId = &#63;.
	*
	* @param contactId the contact ID
	* @return the matching newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterLog> findByContactId(
		long contactId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the newsletter logs where contactId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contactId the contact ID
	* @param start the lower bound of the range of newsletter logs
	* @param end the upper bound of the range of newsletter logs (not inclusive)
	* @return the range of matching newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterLog> findByContactId(
		long contactId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the newsletter logs where contactId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contactId the contact ID
	* @param start the lower bound of the range of newsletter logs
	* @param end the upper bound of the range of newsletter logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterLog> findByContactId(
		long contactId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first newsletter log in the ordered set where contactId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contactId the contact ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching newsletter log
	* @throws com.liferay.newsletter.NoSuchLogException if a matching newsletter log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterLog findByContactId_First(
		long contactId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last newsletter log in the ordered set where contactId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contactId the contact ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching newsletter log
	* @throws com.liferay.newsletter.NoSuchLogException if a matching newsletter log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterLog findByContactId_Last(
		long contactId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the newsletter logs before and after the current newsletter log in the ordered set where contactId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param logId the primary key of the current newsletter log
	* @param contactId the contact ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next newsletter log
	* @throws com.liferay.newsletter.NoSuchLogException if a newsletter log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterLog[] findByContactId_PrevAndNext(
		long logId, long contactId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the newsletter log where campaignId = &#63; and contactId = &#63; or throws a {@link com.liferay.newsletter.NoSuchLogException} if it could not be found.
	*
	* @param campaignId the campaign ID
	* @param contactId the contact ID
	* @return the matching newsletter log
	* @throws com.liferay.newsletter.NoSuchLogException if a matching newsletter log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterLog findByC_C(
		long campaignId, long contactId)
		throws com.liferay.newsletter.NoSuchLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the newsletter log where campaignId = &#63; and contactId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param campaignId the campaign ID
	* @param contactId the contact ID
	* @return the matching newsletter log, or <code>null</code> if a matching newsletter log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterLog fetchByC_C(
		long campaignId, long contactId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the newsletter log where campaignId = &#63; and contactId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param campaignId the campaign ID
	* @param contactId the contact ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching newsletter log, or <code>null</code> if a matching newsletter log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterLog fetchByC_C(
		long campaignId, long contactId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the newsletter logs where campaignId = &#63; and sent = &#63;.
	*
	* @param campaignId the campaign ID
	* @param sent the sent
	* @return the matching newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterLog> findByC_S(
		long campaignId, boolean sent)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the newsletter logs where campaignId = &#63; and sent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param campaignId the campaign ID
	* @param sent the sent
	* @param start the lower bound of the range of newsletter logs
	* @param end the upper bound of the range of newsletter logs (not inclusive)
	* @return the range of matching newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterLog> findByC_S(
		long campaignId, boolean sent, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the newsletter logs where campaignId = &#63; and sent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param campaignId the campaign ID
	* @param sent the sent
	* @param start the lower bound of the range of newsletter logs
	* @param end the upper bound of the range of newsletter logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterLog> findByC_S(
		long campaignId, boolean sent, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first newsletter log in the ordered set where campaignId = &#63; and sent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param campaignId the campaign ID
	* @param sent the sent
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching newsletter log
	* @throws com.liferay.newsletter.NoSuchLogException if a matching newsletter log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterLog findByC_S_First(
		long campaignId, boolean sent,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last newsletter log in the ordered set where campaignId = &#63; and sent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param campaignId the campaign ID
	* @param sent the sent
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching newsletter log
	* @throws com.liferay.newsletter.NoSuchLogException if a matching newsletter log could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterLog findByC_S_Last(
		long campaignId, boolean sent,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the newsletter logs before and after the current newsletter log in the ordered set where campaignId = &#63; and sent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param logId the primary key of the current newsletter log
	* @param campaignId the campaign ID
	* @param sent the sent
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next newsletter log
	* @throws com.liferay.newsletter.NoSuchLogException if a newsletter log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.newsletter.model.NewsletterLog[] findByC_S_PrevAndNext(
		long logId, long campaignId, boolean sent,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.newsletter.NoSuchLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the newsletter logs.
	*
	* @return the newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterLog> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.newsletter.model.NewsletterLog> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the newsletter logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of newsletter logs
	* @param end the upper bound of the range of newsletter logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.newsletter.model.NewsletterLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the newsletter logs where campaignId = &#63; from the database.
	*
	* @param campaignId the campaign ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCampaignId(long campaignId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the newsletter logs where contactId = &#63; from the database.
	*
	* @param contactId the contact ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByContactId(long contactId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the newsletter log where campaignId = &#63; and contactId = &#63; from the database.
	*
	* @param campaignId the campaign ID
	* @param contactId the contact ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C(long campaignId, long contactId)
		throws com.liferay.newsletter.NoSuchLogException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the newsletter logs where campaignId = &#63; and sent = &#63; from the database.
	*
	* @param campaignId the campaign ID
	* @param sent the sent
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_S(long campaignId, boolean sent)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the newsletter logs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of newsletter logs where campaignId = &#63;.
	*
	* @param campaignId the campaign ID
	* @return the number of matching newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public int countByCampaignId(long campaignId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of newsletter logs where contactId = &#63;.
	*
	* @param contactId the contact ID
	* @return the number of matching newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public int countByContactId(long contactId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of newsletter logs where campaignId = &#63; and contactId = &#63;.
	*
	* @param campaignId the campaign ID
	* @param contactId the contact ID
	* @return the number of matching newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C(long campaignId, long contactId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of newsletter logs where campaignId = &#63; and sent = &#63;.
	*
	* @param campaignId the campaign ID
	* @param sent the sent
	* @return the number of matching newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_S(long campaignId, boolean sent)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of newsletter logs.
	*
	* @return the number of newsletter logs
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public NewsletterLog remove(NewsletterLog newsletterLog)
		throws SystemException;
}