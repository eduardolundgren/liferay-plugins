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

import com.liferay.newsletter.NoSuchContactException;
import com.liferay.newsletter.model.NewsletterContact;
import com.liferay.newsletter.model.impl.NewsletterContactImpl;
import com.liferay.newsletter.model.impl.NewsletterContactModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the newsletter contact service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsletterContactPersistence
 * @see NewsletterContactUtil
 * @generated
 */
public class NewsletterContactPersistenceImpl extends BasePersistenceImpl<NewsletterContact>
	implements NewsletterContactPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NewsletterContactUtil} to access the newsletter contact persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NewsletterContactImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_EMAIL = new FinderPath(NewsletterContactModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContactModelImpl.FINDER_CACHE_ENABLED,
			NewsletterContactImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByEmail", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_EMAIL = new FinderPath(NewsletterContactModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContactModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByEmail",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(NewsletterContactModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContactModelImpl.FINDER_CACHE_ENABLED,
			NewsletterContactImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NewsletterContactModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContactModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the newsletter contact in the entity cache if it is enabled.
	 *
	 * @param newsletterContact the newsletter contact
	 */
	public void cacheResult(NewsletterContact newsletterContact) {
		EntityCacheUtil.putResult(NewsletterContactModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContactImpl.class, newsletterContact.getPrimaryKey(),
			newsletterContact);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EMAIL,
			new Object[] { newsletterContact.getEmail() }, newsletterContact);

		newsletterContact.resetOriginalValues();
	}

	/**
	 * Caches the newsletter contacts in the entity cache if it is enabled.
	 *
	 * @param newsletterContacts the newsletter contacts
	 */
	public void cacheResult(List<NewsletterContact> newsletterContacts) {
		for (NewsletterContact newsletterContact : newsletterContacts) {
			if (EntityCacheUtil.getResult(
						NewsletterContactModelImpl.ENTITY_CACHE_ENABLED,
						NewsletterContactImpl.class,
						newsletterContact.getPrimaryKey(), this) == null) {
				cacheResult(newsletterContact);
			}
		}
	}

	/**
	 * Clears the cache for all newsletter contacts.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(NewsletterContactImpl.class.getName());
		}

		EntityCacheUtil.clearCache(NewsletterContactImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the newsletter contact.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NewsletterContact newsletterContact) {
		EntityCacheUtil.removeResult(NewsletterContactModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContactImpl.class, newsletterContact.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_EMAIL,
			new Object[] { newsletterContact.getEmail() });
	}

	/**
	 * Creates a new newsletter contact with the primary key. Does not add the newsletter contact to the database.
	 *
	 * @param contactId the primary key for the new newsletter contact
	 * @return the new newsletter contact
	 */
	public NewsletterContact create(long contactId) {
		NewsletterContact newsletterContact = new NewsletterContactImpl();

		newsletterContact.setNew(true);
		newsletterContact.setPrimaryKey(contactId);

		return newsletterContact;
	}

	/**
	 * Removes the newsletter contact with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the newsletter contact
	 * @return the newsletter contact that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a newsletter contact with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NewsletterContact remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the newsletter contact with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contactId the primary key of the newsletter contact
	 * @return the newsletter contact that was removed
	 * @throws com.liferay.newsletter.NoSuchContactException if a newsletter contact with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterContact remove(long contactId)
		throws NoSuchContactException, SystemException {
		Session session = null;

		try {
			session = openSession();

			NewsletterContact newsletterContact = (NewsletterContact)session.get(NewsletterContactImpl.class,
					Long.valueOf(contactId));

			if (newsletterContact == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + contactId);
				}

				throw new NoSuchContactException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					contactId);
			}

			return newsletterContactPersistence.remove(newsletterContact);
		}
		catch (NoSuchContactException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Removes the newsletter contact from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsletterContact the newsletter contact
	 * @return the newsletter contact that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NewsletterContact remove(NewsletterContact newsletterContact)
		throws SystemException {
		return super.remove(newsletterContact);
	}

	@Override
	protected NewsletterContact removeImpl(NewsletterContact newsletterContact)
		throws SystemException {
		newsletterContact = toUnwrappedModel(newsletterContact);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, newsletterContact);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		NewsletterContactModelImpl newsletterContactModelImpl = (NewsletterContactModelImpl)newsletterContact;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_EMAIL,
			new Object[] { newsletterContactModelImpl.getEmail() });

		EntityCacheUtil.removeResult(NewsletterContactModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContactImpl.class, newsletterContact.getPrimaryKey());

		return newsletterContact;
	}

	@Override
	public NewsletterContact updateImpl(
		com.liferay.newsletter.model.NewsletterContact newsletterContact,
		boolean merge) throws SystemException {
		newsletterContact = toUnwrappedModel(newsletterContact);

		boolean isNew = newsletterContact.isNew();

		NewsletterContactModelImpl newsletterContactModelImpl = (NewsletterContactModelImpl)newsletterContact;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, newsletterContact, merge);

			newsletterContact.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(NewsletterContactModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContactImpl.class, newsletterContact.getPrimaryKey(),
			newsletterContact);

		if (!isNew &&
				(!Validator.equals(newsletterContact.getEmail(),
					newsletterContactModelImpl.getOriginalEmail()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_EMAIL,
				new Object[] { newsletterContactModelImpl.getOriginalEmail() });
		}

		if (isNew ||
				(!Validator.equals(newsletterContact.getEmail(),
					newsletterContactModelImpl.getOriginalEmail()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EMAIL,
				new Object[] { newsletterContact.getEmail() }, newsletterContact);
		}

		return newsletterContact;
	}

	protected NewsletterContact toUnwrappedModel(
		NewsletterContact newsletterContact) {
		if (newsletterContact instanceof NewsletterContactImpl) {
			return newsletterContact;
		}

		NewsletterContactImpl newsletterContactImpl = new NewsletterContactImpl();

		newsletterContactImpl.setNew(newsletterContact.isNew());
		newsletterContactImpl.setPrimaryKey(newsletterContact.getPrimaryKey());

		newsletterContactImpl.setContactId(newsletterContact.getContactId());
		newsletterContactImpl.setGroupId(newsletterContact.getGroupId());
		newsletterContactImpl.setCompanyId(newsletterContact.getCompanyId());
		newsletterContactImpl.setUserId(newsletterContact.getUserId());
		newsletterContactImpl.setUserName(newsletterContact.getUserName());
		newsletterContactImpl.setCreateDate(newsletterContact.getCreateDate());
		newsletterContactImpl.setModifiedDate(newsletterContact.getModifiedDate());
		newsletterContactImpl.setEmail(newsletterContact.getEmail());
		newsletterContactImpl.setName(newsletterContact.getName());

		return newsletterContactImpl;
	}

	/**
	 * Returns the newsletter contact with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the newsletter contact
	 * @return the newsletter contact
	 * @throws com.liferay.portal.NoSuchModelException if a newsletter contact with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NewsletterContact findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the newsletter contact with the primary key or throws a {@link com.liferay.newsletter.NoSuchContactException} if it could not be found.
	 *
	 * @param contactId the primary key of the newsletter contact
	 * @return the newsletter contact
	 * @throws com.liferay.newsletter.NoSuchContactException if a newsletter contact with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterContact findByPrimaryKey(long contactId)
		throws NoSuchContactException, SystemException {
		NewsletterContact newsletterContact = fetchByPrimaryKey(contactId);

		if (newsletterContact == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + contactId);
			}

			throw new NoSuchContactException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				contactId);
		}

		return newsletterContact;
	}

	/**
	 * Returns the newsletter contact with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the newsletter contact
	 * @return the newsletter contact, or <code>null</code> if a newsletter contact with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NewsletterContact fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the newsletter contact with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contactId the primary key of the newsletter contact
	 * @return the newsletter contact, or <code>null</code> if a newsletter contact with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterContact fetchByPrimaryKey(long contactId)
		throws SystemException {
		NewsletterContact newsletterContact = (NewsletterContact)EntityCacheUtil.getResult(NewsletterContactModelImpl.ENTITY_CACHE_ENABLED,
				NewsletterContactImpl.class, contactId, this);

		if (newsletterContact == _nullNewsletterContact) {
			return null;
		}

		if (newsletterContact == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				newsletterContact = (NewsletterContact)session.get(NewsletterContactImpl.class,
						Long.valueOf(contactId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (newsletterContact != null) {
					cacheResult(newsletterContact);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(NewsletterContactModelImpl.ENTITY_CACHE_ENABLED,
						NewsletterContactImpl.class, contactId,
						_nullNewsletterContact);
				}

				closeSession(session);
			}
		}

		return newsletterContact;
	}

	/**
	 * Returns the newsletter contact where email = &#63; or throws a {@link com.liferay.newsletter.NoSuchContactException} if it could not be found.
	 *
	 * @param email the email
	 * @return the matching newsletter contact
	 * @throws com.liferay.newsletter.NoSuchContactException if a matching newsletter contact could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterContact findByEmail(String email)
		throws NoSuchContactException, SystemException {
		NewsletterContact newsletterContact = fetchByEmail(email);

		if (newsletterContact == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("email=");
			msg.append(email);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchContactException(msg.toString());
		}

		return newsletterContact;
	}

	/**
	 * Returns the newsletter contact where email = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param email the email
	 * @return the matching newsletter contact, or <code>null</code> if a matching newsletter contact could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterContact fetchByEmail(String email)
		throws SystemException {
		return fetchByEmail(email, true);
	}

	/**
	 * Returns the newsletter contact where email = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param email the email
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching newsletter contact, or <code>null</code> if a matching newsletter contact could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterContact fetchByEmail(String email,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { email };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_EMAIL,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_NEWSLETTERCONTACT_WHERE);

			if (email == null) {
				query.append(_FINDER_COLUMN_EMAIL_EMAIL_1);
			}
			else {
				if (email.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EMAIL_EMAIL_3);
				}
				else {
					query.append(_FINDER_COLUMN_EMAIL_EMAIL_2);
				}
			}

			query.append(NewsletterContactModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (email != null) {
					qPos.add(email);
				}

				List<NewsletterContact> list = q.list();

				result = list;

				NewsletterContact newsletterContact = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EMAIL,
						finderArgs, list);
				}
				else {
					newsletterContact = list.get(0);

					cacheResult(newsletterContact);

					if ((newsletterContact.getEmail() == null) ||
							!newsletterContact.getEmail().equals(email)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_EMAIL,
							finderArgs, newsletterContact);
					}
				}

				return newsletterContact;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_EMAIL,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (NewsletterContact)result;
			}
		}
	}

	/**
	 * Returns all the newsletter contacts.
	 *
	 * @return the newsletter contacts
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsletterContact> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the newsletter contacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of newsletter contacts
	 * @param end the upper bound of the range of newsletter contacts (not inclusive)
	 * @return the range of newsletter contacts
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsletterContact> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the newsletter contacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of newsletter contacts
	 * @param end the upper bound of the range of newsletter contacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of newsletter contacts
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsletterContact> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<NewsletterContact> list = (List<NewsletterContact>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_NEWSLETTERCONTACT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NEWSLETTERCONTACT.concat(NewsletterContactModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<NewsletterContact>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<NewsletterContact>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs,
						list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes the newsletter contact where email = &#63; from the database.
	 *
	 * @param email the email
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEmail(String email)
		throws NoSuchContactException, SystemException {
		NewsletterContact newsletterContact = findByEmail(email);

		newsletterContactPersistence.remove(newsletterContact);
	}

	/**
	 * Removes all the newsletter contacts from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (NewsletterContact newsletterContact : findAll()) {
			newsletterContactPersistence.remove(newsletterContact);
		}
	}

	/**
	 * Returns the number of newsletter contacts where email = &#63;.
	 *
	 * @param email the email
	 * @return the number of matching newsletter contacts
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEmail(String email) throws SystemException {
		Object[] finderArgs = new Object[] { email };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_EMAIL,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_NEWSLETTERCONTACT_WHERE);

			if (email == null) {
				query.append(_FINDER_COLUMN_EMAIL_EMAIL_1);
			}
			else {
				if (email.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EMAIL_EMAIL_3);
				}
				else {
					query.append(_FINDER_COLUMN_EMAIL_EMAIL_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (email != null) {
					qPos.add(email);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_EMAIL,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of newsletter contacts.
	 *
	 * @return the number of newsletter contacts
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NEWSLETTERCONTACT);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the newsletter contact persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.newsletter.model.NewsletterContact")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<NewsletterContact>> listenersList = new ArrayList<ModelListener<NewsletterContact>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<NewsletterContact>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(NewsletterContactImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = NewsletterCampaignPersistence.class)
	protected NewsletterCampaignPersistence newsletterCampaignPersistence;
	@BeanReference(type = NewsletterContactPersistence.class)
	protected NewsletterContactPersistence newsletterContactPersistence;
	@BeanReference(type = NewsletterContentPersistence.class)
	protected NewsletterContentPersistence newsletterContentPersistence;
	@BeanReference(type = NewsletterLogPersistence.class)
	protected NewsletterLogPersistence newsletterLogPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_NEWSLETTERCONTACT = "SELECT newsletterContact FROM NewsletterContact newsletterContact";
	private static final String _SQL_SELECT_NEWSLETTERCONTACT_WHERE = "SELECT newsletterContact FROM NewsletterContact newsletterContact WHERE ";
	private static final String _SQL_COUNT_NEWSLETTERCONTACT = "SELECT COUNT(newsletterContact) FROM NewsletterContact newsletterContact";
	private static final String _SQL_COUNT_NEWSLETTERCONTACT_WHERE = "SELECT COUNT(newsletterContact) FROM NewsletterContact newsletterContact WHERE ";
	private static final String _FINDER_COLUMN_EMAIL_EMAIL_1 = "newsletterContact.email IS NULL";
	private static final String _FINDER_COLUMN_EMAIL_EMAIL_2 = "newsletterContact.email = ?";
	private static final String _FINDER_COLUMN_EMAIL_EMAIL_3 = "(newsletterContact.email IS NULL OR newsletterContact.email = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "newsletterContact.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NewsletterContact exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No NewsletterContact exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(NewsletterContactPersistenceImpl.class);
	private static NewsletterContact _nullNewsletterContact = new NewsletterContactImpl() {
			public Object clone() {
				return this;
			}

			public CacheModel<NewsletterContact> toCacheModel() {
				return _nullNewsletterContactCacheModel;
			}
		};

	private static CacheModel<NewsletterContact> _nullNewsletterContactCacheModel =
		new CacheModel<NewsletterContact>() {
			public NewsletterContact toEntityModel() {
				return _nullNewsletterContact;
			}
		};
}