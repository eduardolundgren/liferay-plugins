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

import com.liferay.newsletter.NoSuchLogException;
import com.liferay.newsletter.model.NewsletterLog;
import com.liferay.newsletter.model.impl.NewsletterLogImpl;
import com.liferay.newsletter.model.impl.NewsletterLogModelImpl;

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
 * The persistence implementation for the newsletter log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsletterLogPersistence
 * @see NewsletterLogUtil
 * @generated
 */
public class NewsletterLogPersistenceImpl extends BasePersistenceImpl<NewsletterLog>
	implements NewsletterLogPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NewsletterLogUtil} to access the newsletter log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NewsletterLogImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_CAMPAIGNID = new FinderPath(NewsletterLogModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterLogModelImpl.FINDER_CACHE_ENABLED,
			NewsletterLogImpl.class, FINDER_CLASS_NAME_LIST,
			"findByCampaignId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_CAMPAIGNID = new FinderPath(NewsletterLogModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByCampaignId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_CONTACTID = new FinderPath(NewsletterLogModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterLogModelImpl.FINDER_CACHE_ENABLED,
			NewsletterLogImpl.class, FINDER_CLASS_NAME_LIST, "findByContactId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_CONTACTID = new FinderPath(NewsletterLogModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByContactId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_C_C = new FinderPath(NewsletterLogModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterLogModelImpl.FINDER_CACHE_ENABLED,
			NewsletterLogImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(NewsletterLogModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_C_S = new FinderPath(NewsletterLogModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterLogModelImpl.FINDER_CACHE_ENABLED,
			NewsletterLogImpl.class, FINDER_CLASS_NAME_LIST, "findByC_S",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_S = new FinderPath(NewsletterLogModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByC_S",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(NewsletterLogModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterLogModelImpl.FINDER_CACHE_ENABLED,
			NewsletterLogImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NewsletterLogModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the newsletter log in the entity cache if it is enabled.
	 *
	 * @param newsletterLog the newsletter log
	 */
	public void cacheResult(NewsletterLog newsletterLog) {
		EntityCacheUtil.putResult(NewsletterLogModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterLogImpl.class, newsletterLog.getPrimaryKey(),
			newsletterLog);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
			new Object[] {
				Long.valueOf(newsletterLog.getCampaignId()),
				Long.valueOf(newsletterLog.getContactId())
			}, newsletterLog);

		newsletterLog.resetOriginalValues();
	}

	/**
	 * Caches the newsletter logs in the entity cache if it is enabled.
	 *
	 * @param newsletterLogs the newsletter logs
	 */
	public void cacheResult(List<NewsletterLog> newsletterLogs) {
		for (NewsletterLog newsletterLog : newsletterLogs) {
			if (EntityCacheUtil.getResult(
						NewsletterLogModelImpl.ENTITY_CACHE_ENABLED,
						NewsletterLogImpl.class, newsletterLog.getPrimaryKey(),
						this) == null) {
				cacheResult(newsletterLog);
			}
		}
	}

	/**
	 * Clears the cache for all newsletter logs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(NewsletterLogImpl.class.getName());
		}

		EntityCacheUtil.clearCache(NewsletterLogImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the newsletter log.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NewsletterLog newsletterLog) {
		EntityCacheUtil.removeResult(NewsletterLogModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterLogImpl.class, newsletterLog.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C,
			new Object[] {
				Long.valueOf(newsletterLog.getCampaignId()),
				Long.valueOf(newsletterLog.getContactId())
			});
	}

	/**
	 * Creates a new newsletter log with the primary key. Does not add the newsletter log to the database.
	 *
	 * @param logId the primary key for the new newsletter log
	 * @return the new newsletter log
	 */
	public NewsletterLog create(long logId) {
		NewsletterLog newsletterLog = new NewsletterLogImpl();

		newsletterLog.setNew(true);
		newsletterLog.setPrimaryKey(logId);

		return newsletterLog;
	}

	/**
	 * Removes the newsletter log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the newsletter log
	 * @return the newsletter log that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a newsletter log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NewsletterLog remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the newsletter log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param logId the primary key of the newsletter log
	 * @return the newsletter log that was removed
	 * @throws com.liferay.newsletter.NoSuchLogException if a newsletter log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterLog remove(long logId)
		throws NoSuchLogException, SystemException {
		Session session = null;

		try {
			session = openSession();

			NewsletterLog newsletterLog = (NewsletterLog)session.get(NewsletterLogImpl.class,
					Long.valueOf(logId));

			if (newsletterLog == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + logId);
				}

				throw new NoSuchLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					logId);
			}

			return newsletterLogPersistence.remove(newsletterLog);
		}
		catch (NoSuchLogException nsee) {
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
	 * Removes the newsletter log from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsletterLog the newsletter log
	 * @return the newsletter log that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NewsletterLog remove(NewsletterLog newsletterLog)
		throws SystemException {
		return super.remove(newsletterLog);
	}

	@Override
	protected NewsletterLog removeImpl(NewsletterLog newsletterLog)
		throws SystemException {
		newsletterLog = toUnwrappedModel(newsletterLog);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, newsletterLog);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		NewsletterLogModelImpl newsletterLogModelImpl = (NewsletterLogModelImpl)newsletterLog;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C,
			new Object[] {
				Long.valueOf(newsletterLogModelImpl.getCampaignId()),
				Long.valueOf(newsletterLogModelImpl.getContactId())
			});

		EntityCacheUtil.removeResult(NewsletterLogModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterLogImpl.class, newsletterLog.getPrimaryKey());

		return newsletterLog;
	}

	@Override
	public NewsletterLog updateImpl(
		com.liferay.newsletter.model.NewsletterLog newsletterLog, boolean merge)
		throws SystemException {
		newsletterLog = toUnwrappedModel(newsletterLog);

		boolean isNew = newsletterLog.isNew();

		NewsletterLogModelImpl newsletterLogModelImpl = (NewsletterLogModelImpl)newsletterLog;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, newsletterLog, merge);

			newsletterLog.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(NewsletterLogModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterLogImpl.class, newsletterLog.getPrimaryKey(),
			newsletterLog);

		if (!isNew &&
				((newsletterLog.getCampaignId() != newsletterLogModelImpl.getOriginalCampaignId()) ||
				(newsletterLog.getContactId() != newsletterLogModelImpl.getOriginalContactId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C,
				new Object[] {
					Long.valueOf(newsletterLogModelImpl.getOriginalCampaignId()),
					Long.valueOf(newsletterLogModelImpl.getOriginalContactId())
				});
		}

		if (isNew ||
				((newsletterLog.getCampaignId() != newsletterLogModelImpl.getOriginalCampaignId()) ||
				(newsletterLog.getContactId() != newsletterLogModelImpl.getOriginalContactId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
				new Object[] {
					Long.valueOf(newsletterLog.getCampaignId()),
					Long.valueOf(newsletterLog.getContactId())
				}, newsletterLog);
		}

		return newsletterLog;
	}

	protected NewsletterLog toUnwrappedModel(NewsletterLog newsletterLog) {
		if (newsletterLog instanceof NewsletterLogImpl) {
			return newsletterLog;
		}

		NewsletterLogImpl newsletterLogImpl = new NewsletterLogImpl();

		newsletterLogImpl.setNew(newsletterLog.isNew());
		newsletterLogImpl.setPrimaryKey(newsletterLog.getPrimaryKey());

		newsletterLogImpl.setLogId(newsletterLog.getLogId());
		newsletterLogImpl.setCampaignId(newsletterLog.getCampaignId());
		newsletterLogImpl.setContactId(newsletterLog.getContactId());
		newsletterLogImpl.setSent(newsletterLog.isSent());

		return newsletterLogImpl;
	}

	/**
	 * Returns the newsletter log with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the newsletter log
	 * @return the newsletter log
	 * @throws com.liferay.portal.NoSuchModelException if a newsletter log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NewsletterLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the newsletter log with the primary key or throws a {@link com.liferay.newsletter.NoSuchLogException} if it could not be found.
	 *
	 * @param logId the primary key of the newsletter log
	 * @return the newsletter log
	 * @throws com.liferay.newsletter.NoSuchLogException if a newsletter log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterLog findByPrimaryKey(long logId)
		throws NoSuchLogException, SystemException {
		NewsletterLog newsletterLog = fetchByPrimaryKey(logId);

		if (newsletterLog == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + logId);
			}

			throw new NoSuchLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				logId);
		}

		return newsletterLog;
	}

	/**
	 * Returns the newsletter log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the newsletter log
	 * @return the newsletter log, or <code>null</code> if a newsletter log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NewsletterLog fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the newsletter log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param logId the primary key of the newsletter log
	 * @return the newsletter log, or <code>null</code> if a newsletter log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterLog fetchByPrimaryKey(long logId)
		throws SystemException {
		NewsletterLog newsletterLog = (NewsletterLog)EntityCacheUtil.getResult(NewsletterLogModelImpl.ENTITY_CACHE_ENABLED,
				NewsletterLogImpl.class, logId, this);

		if (newsletterLog == _nullNewsletterLog) {
			return null;
		}

		if (newsletterLog == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				newsletterLog = (NewsletterLog)session.get(NewsletterLogImpl.class,
						Long.valueOf(logId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (newsletterLog != null) {
					cacheResult(newsletterLog);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(NewsletterLogModelImpl.ENTITY_CACHE_ENABLED,
						NewsletterLogImpl.class, logId, _nullNewsletterLog);
				}

				closeSession(session);
			}
		}

		return newsletterLog;
	}

	/**
	 * Returns all the newsletter logs where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching newsletter logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsletterLog> findByCampaignId(long campaignId)
		throws SystemException {
		return findByCampaignId(campaignId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

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
	public List<NewsletterLog> findByCampaignId(long campaignId, int start,
		int end) throws SystemException {
		return findByCampaignId(campaignId, start, end, null);
	}

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
	public List<NewsletterLog> findByCampaignId(long campaignId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				campaignId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<NewsletterLog> list = (List<NewsletterLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CAMPAIGNID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_NEWSLETTERLOG_WHERE);

			query.append(_FINDER_COLUMN_CAMPAIGNID_CAMPAIGNID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(campaignId);

				list = (List<NewsletterLog>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_CAMPAIGNID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CAMPAIGNID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

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
	public NewsletterLog findByCampaignId_First(long campaignId,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		List<NewsletterLog> list = findByCampaignId(campaignId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("campaignId=");
			msg.append(campaignId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

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
	public NewsletterLog findByCampaignId_Last(long campaignId,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		int count = countByCampaignId(campaignId);

		List<NewsletterLog> list = findByCampaignId(campaignId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("campaignId=");
			msg.append(campaignId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

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
	public NewsletterLog[] findByCampaignId_PrevAndNext(long logId,
		long campaignId, OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		NewsletterLog newsletterLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			NewsletterLog[] array = new NewsletterLogImpl[3];

			array[0] = getByCampaignId_PrevAndNext(session, newsletterLog,
					campaignId, orderByComparator, true);

			array[1] = newsletterLog;

			array[2] = getByCampaignId_PrevAndNext(session, newsletterLog,
					campaignId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected NewsletterLog getByCampaignId_PrevAndNext(Session session,
		NewsletterLog newsletterLog, long campaignId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_NEWSLETTERLOG_WHERE);

		query.append(_FINDER_COLUMN_CAMPAIGNID_CAMPAIGNID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(campaignId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(newsletterLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<NewsletterLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the newsletter logs where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @return the matching newsletter logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsletterLog> findByContactId(long contactId)
		throws SystemException {
		return findByContactId(contactId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

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
	public List<NewsletterLog> findByContactId(long contactId, int start,
		int end) throws SystemException {
		return findByContactId(contactId, start, end, null);
	}

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
	public List<NewsletterLog> findByContactId(long contactId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				contactId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<NewsletterLog> list = (List<NewsletterLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CONTACTID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_NEWSLETTERLOG_WHERE);

			query.append(_FINDER_COLUMN_CONTACTID_CONTACTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contactId);

				list = (List<NewsletterLog>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_CONTACTID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CONTACTID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

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
	public NewsletterLog findByContactId_First(long contactId,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		List<NewsletterLog> list = findByContactId(contactId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("contactId=");
			msg.append(contactId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

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
	public NewsletterLog findByContactId_Last(long contactId,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		int count = countByContactId(contactId);

		List<NewsletterLog> list = findByContactId(contactId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("contactId=");
			msg.append(contactId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

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
	public NewsletterLog[] findByContactId_PrevAndNext(long logId,
		long contactId, OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		NewsletterLog newsletterLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			NewsletterLog[] array = new NewsletterLogImpl[3];

			array[0] = getByContactId_PrevAndNext(session, newsletterLog,
					contactId, orderByComparator, true);

			array[1] = newsletterLog;

			array[2] = getByContactId_PrevAndNext(session, newsletterLog,
					contactId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected NewsletterLog getByContactId_PrevAndNext(Session session,
		NewsletterLog newsletterLog, long contactId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_NEWSLETTERLOG_WHERE);

		query.append(_FINDER_COLUMN_CONTACTID_CONTACTID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(contactId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(newsletterLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<NewsletterLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the newsletter log where campaignId = &#63; and contactId = &#63; or throws a {@link com.liferay.newsletter.NoSuchLogException} if it could not be found.
	 *
	 * @param campaignId the campaign ID
	 * @param contactId the contact ID
	 * @return the matching newsletter log
	 * @throws com.liferay.newsletter.NoSuchLogException if a matching newsletter log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterLog findByC_C(long campaignId, long contactId)
		throws NoSuchLogException, SystemException {
		NewsletterLog newsletterLog = fetchByC_C(campaignId, contactId);

		if (newsletterLog == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("campaignId=");
			msg.append(campaignId);

			msg.append(", contactId=");
			msg.append(contactId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchLogException(msg.toString());
		}

		return newsletterLog;
	}

	/**
	 * Returns the newsletter log where campaignId = &#63; and contactId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param campaignId the campaign ID
	 * @param contactId the contact ID
	 * @return the matching newsletter log, or <code>null</code> if a matching newsletter log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterLog fetchByC_C(long campaignId, long contactId)
		throws SystemException {
		return fetchByC_C(campaignId, contactId, true);
	}

	/**
	 * Returns the newsletter log where campaignId = &#63; and contactId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param campaignId the campaign ID
	 * @param contactId the contact ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching newsletter log, or <code>null</code> if a matching newsletter log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterLog fetchByC_C(long campaignId, long contactId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { campaignId, contactId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_C,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_NEWSLETTERLOG_WHERE);

			query.append(_FINDER_COLUMN_C_C_CAMPAIGNID_2);

			query.append(_FINDER_COLUMN_C_C_CONTACTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(campaignId);

				qPos.add(contactId);

				List<NewsletterLog> list = q.list();

				result = list;

				NewsletterLog newsletterLog = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
						finderArgs, list);
				}
				else {
					newsletterLog = list.get(0);

					cacheResult(newsletterLog);

					if ((newsletterLog.getCampaignId() != campaignId) ||
							(newsletterLog.getContactId() != contactId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
							finderArgs, newsletterLog);
					}
				}

				return newsletterLog;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C,
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
				return (NewsletterLog)result;
			}
		}
	}

	/**
	 * Returns all the newsletter logs where campaignId = &#63; and sent = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param sent the sent
	 * @return the matching newsletter logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsletterLog> findByC_S(long campaignId, boolean sent)
		throws SystemException {
		return findByC_S(campaignId, sent, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

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
	public List<NewsletterLog> findByC_S(long campaignId, boolean sent,
		int start, int end) throws SystemException {
		return findByC_S(campaignId, sent, start, end, null);
	}

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
	public List<NewsletterLog> findByC_S(long campaignId, boolean sent,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				campaignId, sent,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<NewsletterLog> list = (List<NewsletterLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_S,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_NEWSLETTERLOG_WHERE);

			query.append(_FINDER_COLUMN_C_S_CAMPAIGNID_2);

			query.append(_FINDER_COLUMN_C_S_SENT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(campaignId);

				qPos.add(sent);

				list = (List<NewsletterLog>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_C_S,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_S,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

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
	public NewsletterLog findByC_S_First(long campaignId, boolean sent,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		List<NewsletterLog> list = findByC_S(campaignId, sent, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("campaignId=");
			msg.append(campaignId);

			msg.append(", sent=");
			msg.append(sent);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

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
	public NewsletterLog findByC_S_Last(long campaignId, boolean sent,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		int count = countByC_S(campaignId, sent);

		List<NewsletterLog> list = findByC_S(campaignId, sent, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("campaignId=");
			msg.append(campaignId);

			msg.append(", sent=");
			msg.append(sent);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

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
	public NewsletterLog[] findByC_S_PrevAndNext(long logId, long campaignId,
		boolean sent, OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		NewsletterLog newsletterLog = findByPrimaryKey(logId);

		Session session = null;

		try {
			session = openSession();

			NewsletterLog[] array = new NewsletterLogImpl[3];

			array[0] = getByC_S_PrevAndNext(session, newsletterLog, campaignId,
					sent, orderByComparator, true);

			array[1] = newsletterLog;

			array[2] = getByC_S_PrevAndNext(session, newsletterLog, campaignId,
					sent, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected NewsletterLog getByC_S_PrevAndNext(Session session,
		NewsletterLog newsletterLog, long campaignId, boolean sent,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_NEWSLETTERLOG_WHERE);

		query.append(_FINDER_COLUMN_C_S_CAMPAIGNID_2);

		query.append(_FINDER_COLUMN_C_S_SENT_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(campaignId);

		qPos.add(sent);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(newsletterLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<NewsletterLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the newsletter logs.
	 *
	 * @return the newsletter logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsletterLog> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<NewsletterLog> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

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
	public List<NewsletterLog> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<NewsletterLog> list = (List<NewsletterLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_NEWSLETTERLOG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NEWSLETTERLOG;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<NewsletterLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<NewsletterLog>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the newsletter logs where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCampaignId(long campaignId) throws SystemException {
		for (NewsletterLog newsletterLog : findByCampaignId(campaignId)) {
			newsletterLogPersistence.remove(newsletterLog);
		}
	}

	/**
	 * Removes all the newsletter logs where contactId = &#63; from the database.
	 *
	 * @param contactId the contact ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByContactId(long contactId) throws SystemException {
		for (NewsletterLog newsletterLog : findByContactId(contactId)) {
			newsletterLogPersistence.remove(newsletterLog);
		}
	}

	/**
	 * Removes the newsletter log where campaignId = &#63; and contactId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @param contactId the contact ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C(long campaignId, long contactId)
		throws NoSuchLogException, SystemException {
		NewsletterLog newsletterLog = findByC_C(campaignId, contactId);

		newsletterLogPersistence.remove(newsletterLog);
	}

	/**
	 * Removes all the newsletter logs where campaignId = &#63; and sent = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @param sent the sent
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_S(long campaignId, boolean sent)
		throws SystemException {
		for (NewsletterLog newsletterLog : findByC_S(campaignId, sent)) {
			newsletterLogPersistence.remove(newsletterLog);
		}
	}

	/**
	 * Removes all the newsletter logs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (NewsletterLog newsletterLog : findAll()) {
			newsletterLogPersistence.remove(newsletterLog);
		}
	}

	/**
	 * Returns the number of newsletter logs where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching newsletter logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCampaignId(long campaignId) throws SystemException {
		Object[] finderArgs = new Object[] { campaignId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CAMPAIGNID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_NEWSLETTERLOG_WHERE);

			query.append(_FINDER_COLUMN_CAMPAIGNID_CAMPAIGNID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(campaignId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CAMPAIGNID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of newsletter logs where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @return the number of matching newsletter logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByContactId(long contactId) throws SystemException {
		Object[] finderArgs = new Object[] { contactId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTACTID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_NEWSLETTERLOG_WHERE);

			query.append(_FINDER_COLUMN_CONTACTID_CONTACTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contactId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTACTID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of newsletter logs where campaignId = &#63; and contactId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param contactId the contact ID
	 * @return the number of matching newsletter logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C(long campaignId, long contactId)
		throws SystemException {
		Object[] finderArgs = new Object[] { campaignId, contactId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_NEWSLETTERLOG_WHERE);

			query.append(_FINDER_COLUMN_C_C_CAMPAIGNID_2);

			query.append(_FINDER_COLUMN_C_C_CONTACTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(campaignId);

				qPos.add(contactId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of newsletter logs where campaignId = &#63; and sent = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param sent the sent
	 * @return the number of matching newsletter logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_S(long campaignId, boolean sent)
		throws SystemException {
		Object[] finderArgs = new Object[] { campaignId, sent };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_NEWSLETTERLOG_WHERE);

			query.append(_FINDER_COLUMN_C_S_CAMPAIGNID_2);

			query.append(_FINDER_COLUMN_C_S_SENT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(campaignId);

				qPos.add(sent);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_S, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of newsletter logs.
	 *
	 * @return the number of newsletter logs
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

				Query q = session.createQuery(_SQL_COUNT_NEWSLETTERLOG);

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
	 * Initializes the newsletter log persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.newsletter.model.NewsletterLog")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<NewsletterLog>> listenersList = new ArrayList<ModelListener<NewsletterLog>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<NewsletterLog>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(NewsletterLogImpl.class.getName());
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
	private static final String _SQL_SELECT_NEWSLETTERLOG = "SELECT newsletterLog FROM NewsletterLog newsletterLog";
	private static final String _SQL_SELECT_NEWSLETTERLOG_WHERE = "SELECT newsletterLog FROM NewsletterLog newsletterLog WHERE ";
	private static final String _SQL_COUNT_NEWSLETTERLOG = "SELECT COUNT(newsletterLog) FROM NewsletterLog newsletterLog";
	private static final String _SQL_COUNT_NEWSLETTERLOG_WHERE = "SELECT COUNT(newsletterLog) FROM NewsletterLog newsletterLog WHERE ";
	private static final String _FINDER_COLUMN_CAMPAIGNID_CAMPAIGNID_2 = "newsletterLog.campaignId = ?";
	private static final String _FINDER_COLUMN_CONTACTID_CONTACTID_2 = "newsletterLog.contactId = ?";
	private static final String _FINDER_COLUMN_C_C_CAMPAIGNID_2 = "newsletterLog.campaignId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CONTACTID_2 = "newsletterLog.contactId = ?";
	private static final String _FINDER_COLUMN_C_S_CAMPAIGNID_2 = "newsletterLog.campaignId = ? AND ";
	private static final String _FINDER_COLUMN_C_S_SENT_2 = "newsletterLog.sent = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "newsletterLog.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NewsletterLog exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No NewsletterLog exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(NewsletterLogPersistenceImpl.class);
	private static NewsletterLog _nullNewsletterLog = new NewsletterLogImpl() {
			public Object clone() {
				return this;
			}

			public CacheModel<NewsletterLog> toCacheModel() {
				return _nullNewsletterLogCacheModel;
			}
		};

	private static CacheModel<NewsletterLog> _nullNewsletterLogCacheModel = new CacheModel<NewsletterLog>() {
			public NewsletterLog toEntityModel() {
				return _nullNewsletterLog;
			}
		};
}