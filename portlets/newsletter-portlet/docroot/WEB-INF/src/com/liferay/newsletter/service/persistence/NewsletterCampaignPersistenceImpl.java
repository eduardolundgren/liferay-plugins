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

import com.liferay.newsletter.NoSuchCampaignException;
import com.liferay.newsletter.model.NewsletterCampaign;
import com.liferay.newsletter.model.impl.NewsletterCampaignImpl;
import com.liferay.newsletter.model.impl.NewsletterCampaignModelImpl;

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
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the newsletter campaign service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsletterCampaignPersistence
 * @see NewsletterCampaignUtil
 * @generated
 */
public class NewsletterCampaignPersistenceImpl extends BasePersistenceImpl<NewsletterCampaign>
	implements NewsletterCampaignPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NewsletterCampaignUtil} to access the newsletter campaign persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NewsletterCampaignImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(NewsletterCampaignModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterCampaignModelImpl.FINDER_CACHE_ENABLED,
			NewsletterCampaignImpl.class, FINDER_CLASS_NAME_LIST, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(NewsletterCampaignModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterCampaignModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(NewsletterCampaignModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterCampaignModelImpl.FINDER_CACHE_ENABLED,
			NewsletterCampaignImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(NewsletterCampaignModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterCampaignModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_CONTENTID = new FinderPath(NewsletterCampaignModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterCampaignModelImpl.FINDER_CACHE_ENABLED,
			NewsletterCampaignImpl.class, FINDER_CLASS_NAME_LIST,
			"findByContentId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_CONTENTID = new FinderPath(NewsletterCampaignModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterCampaignModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByContentId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_SD_S = new FinderPath(NewsletterCampaignModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterCampaignModelImpl.FINDER_CACHE_ENABLED,
			NewsletterCampaignImpl.class, FINDER_CLASS_NAME_LIST, "findBySD_S",
			new String[] {
				Date.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_SD_S = new FinderPath(NewsletterCampaignModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterCampaignModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countBySD_S",
			new String[] { Date.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(NewsletterCampaignModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterCampaignModelImpl.FINDER_CACHE_ENABLED,
			NewsletterCampaignImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NewsletterCampaignModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterCampaignModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the newsletter campaign in the entity cache if it is enabled.
	 *
	 * @param newsletterCampaign the newsletter campaign
	 */
	public void cacheResult(NewsletterCampaign newsletterCampaign) {
		EntityCacheUtil.putResult(NewsletterCampaignModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterCampaignImpl.class, newsletterCampaign.getPrimaryKey(),
			newsletterCampaign);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				newsletterCampaign.getUuid(),
				Long.valueOf(newsletterCampaign.getGroupId())
			}, newsletterCampaign);

		newsletterCampaign.resetOriginalValues();
	}

	/**
	 * Caches the newsletter campaigns in the entity cache if it is enabled.
	 *
	 * @param newsletterCampaigns the newsletter campaigns
	 */
	public void cacheResult(List<NewsletterCampaign> newsletterCampaigns) {
		for (NewsletterCampaign newsletterCampaign : newsletterCampaigns) {
			if (EntityCacheUtil.getResult(
						NewsletterCampaignModelImpl.ENTITY_CACHE_ENABLED,
						NewsletterCampaignImpl.class,
						newsletterCampaign.getPrimaryKey(), this) == null) {
				cacheResult(newsletterCampaign);
			}
		}
	}

	/**
	 * Clears the cache for all newsletter campaigns.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(NewsletterCampaignImpl.class.getName());
		}

		EntityCacheUtil.clearCache(NewsletterCampaignImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the newsletter campaign.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NewsletterCampaign newsletterCampaign) {
		EntityCacheUtil.removeResult(NewsletterCampaignModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterCampaignImpl.class, newsletterCampaign.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				newsletterCampaign.getUuid(),
				Long.valueOf(newsletterCampaign.getGroupId())
			});
	}

	/**
	 * Creates a new newsletter campaign with the primary key. Does not add the newsletter campaign to the database.
	 *
	 * @param campaignId the primary key for the new newsletter campaign
	 * @return the new newsletter campaign
	 */
	public NewsletterCampaign create(long campaignId) {
		NewsletterCampaign newsletterCampaign = new NewsletterCampaignImpl();

		newsletterCampaign.setNew(true);
		newsletterCampaign.setPrimaryKey(campaignId);

		String uuid = PortalUUIDUtil.generate();

		newsletterCampaign.setUuid(uuid);

		return newsletterCampaign;
	}

	/**
	 * Removes the newsletter campaign with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the newsletter campaign
	 * @return the newsletter campaign that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a newsletter campaign with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NewsletterCampaign remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the newsletter campaign with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param campaignId the primary key of the newsletter campaign
	 * @return the newsletter campaign that was removed
	 * @throws com.liferay.newsletter.NoSuchCampaignException if a newsletter campaign with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterCampaign remove(long campaignId)
		throws NoSuchCampaignException, SystemException {
		Session session = null;

		try {
			session = openSession();

			NewsletterCampaign newsletterCampaign = (NewsletterCampaign)session.get(NewsletterCampaignImpl.class,
					Long.valueOf(campaignId));

			if (newsletterCampaign == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + campaignId);
				}

				throw new NoSuchCampaignException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					campaignId);
			}

			return newsletterCampaignPersistence.remove(newsletterCampaign);
		}
		catch (NoSuchCampaignException nsee) {
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
	 * Removes the newsletter campaign from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsletterCampaign the newsletter campaign
	 * @return the newsletter campaign that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NewsletterCampaign remove(NewsletterCampaign newsletterCampaign)
		throws SystemException {
		return super.remove(newsletterCampaign);
	}

	@Override
	protected NewsletterCampaign removeImpl(
		NewsletterCampaign newsletterCampaign) throws SystemException {
		newsletterCampaign = toUnwrappedModel(newsletterCampaign);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, newsletterCampaign);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		NewsletterCampaignModelImpl newsletterCampaignModelImpl = (NewsletterCampaignModelImpl)newsletterCampaign;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				newsletterCampaignModelImpl.getUuid(),
				Long.valueOf(newsletterCampaignModelImpl.getGroupId())
			});

		EntityCacheUtil.removeResult(NewsletterCampaignModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterCampaignImpl.class, newsletterCampaign.getPrimaryKey());

		return newsletterCampaign;
	}

	@Override
	public NewsletterCampaign updateImpl(
		com.liferay.newsletter.model.NewsletterCampaign newsletterCampaign,
		boolean merge) throws SystemException {
		newsletterCampaign = toUnwrappedModel(newsletterCampaign);

		boolean isNew = newsletterCampaign.isNew();

		NewsletterCampaignModelImpl newsletterCampaignModelImpl = (NewsletterCampaignModelImpl)newsletterCampaign;

		if (Validator.isNull(newsletterCampaign.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			newsletterCampaign.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, newsletterCampaign, merge);

			newsletterCampaign.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(NewsletterCampaignModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterCampaignImpl.class, newsletterCampaign.getPrimaryKey(),
			newsletterCampaign);

		if (!isNew &&
				(!Validator.equals(newsletterCampaign.getUuid(),
					newsletterCampaignModelImpl.getOriginalUuid()) ||
				(newsletterCampaign.getGroupId() != newsletterCampaignModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					newsletterCampaignModelImpl.getOriginalUuid(),
					Long.valueOf(
						newsletterCampaignModelImpl.getOriginalGroupId())
				});
		}

		if (isNew ||
				(!Validator.equals(newsletterCampaign.getUuid(),
					newsletterCampaignModelImpl.getOriginalUuid()) ||
				(newsletterCampaign.getGroupId() != newsletterCampaignModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					newsletterCampaign.getUuid(),
					Long.valueOf(newsletterCampaign.getGroupId())
				}, newsletterCampaign);
		}

		return newsletterCampaign;
	}

	protected NewsletterCampaign toUnwrappedModel(
		NewsletterCampaign newsletterCampaign) {
		if (newsletterCampaign instanceof NewsletterCampaignImpl) {
			return newsletterCampaign;
		}

		NewsletterCampaignImpl newsletterCampaignImpl = new NewsletterCampaignImpl();

		newsletterCampaignImpl.setNew(newsletterCampaign.isNew());
		newsletterCampaignImpl.setPrimaryKey(newsletterCampaign.getPrimaryKey());

		newsletterCampaignImpl.setUuid(newsletterCampaign.getUuid());
		newsletterCampaignImpl.setCampaignId(newsletterCampaign.getCampaignId());
		newsletterCampaignImpl.setGroupId(newsletterCampaign.getGroupId());
		newsletterCampaignImpl.setCompanyId(newsletterCampaign.getCompanyId());
		newsletterCampaignImpl.setUserId(newsletterCampaign.getUserId());
		newsletterCampaignImpl.setUserName(newsletterCampaign.getUserName());
		newsletterCampaignImpl.setCreateDate(newsletterCampaign.getCreateDate());
		newsletterCampaignImpl.setModifiedDate(newsletterCampaign.getModifiedDate());
		newsletterCampaignImpl.setContentId(newsletterCampaign.getContentId());
		newsletterCampaignImpl.setEmailSubject(newsletterCampaign.getEmailSubject());
		newsletterCampaignImpl.setSenderEmail(newsletterCampaign.getSenderEmail());
		newsletterCampaignImpl.setSenderName(newsletterCampaign.getSenderName());
		newsletterCampaignImpl.setSent(newsletterCampaign.isSent());
		newsletterCampaignImpl.setSendDate(newsletterCampaign.getSendDate());

		return newsletterCampaignImpl;
	}

	/**
	 * Returns the newsletter campaign with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the newsletter campaign
	 * @return the newsletter campaign
	 * @throws com.liferay.portal.NoSuchModelException if a newsletter campaign with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NewsletterCampaign findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the newsletter campaign with the primary key or throws a {@link com.liferay.newsletter.NoSuchCampaignException} if it could not be found.
	 *
	 * @param campaignId the primary key of the newsletter campaign
	 * @return the newsletter campaign
	 * @throws com.liferay.newsletter.NoSuchCampaignException if a newsletter campaign with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterCampaign findByPrimaryKey(long campaignId)
		throws NoSuchCampaignException, SystemException {
		NewsletterCampaign newsletterCampaign = fetchByPrimaryKey(campaignId);

		if (newsletterCampaign == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + campaignId);
			}

			throw new NoSuchCampaignException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				campaignId);
		}

		return newsletterCampaign;
	}

	/**
	 * Returns the newsletter campaign with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the newsletter campaign
	 * @return the newsletter campaign, or <code>null</code> if a newsletter campaign with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NewsletterCampaign fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the newsletter campaign with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param campaignId the primary key of the newsletter campaign
	 * @return the newsletter campaign, or <code>null</code> if a newsletter campaign with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterCampaign fetchByPrimaryKey(long campaignId)
		throws SystemException {
		NewsletterCampaign newsletterCampaign = (NewsletterCampaign)EntityCacheUtil.getResult(NewsletterCampaignModelImpl.ENTITY_CACHE_ENABLED,
				NewsletterCampaignImpl.class, campaignId, this);

		if (newsletterCampaign == _nullNewsletterCampaign) {
			return null;
		}

		if (newsletterCampaign == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				newsletterCampaign = (NewsletterCampaign)session.get(NewsletterCampaignImpl.class,
						Long.valueOf(campaignId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (newsletterCampaign != null) {
					cacheResult(newsletterCampaign);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(NewsletterCampaignModelImpl.ENTITY_CACHE_ENABLED,
						NewsletterCampaignImpl.class, campaignId,
						_nullNewsletterCampaign);
				}

				closeSession(session);
			}
		}

		return newsletterCampaign;
	}

	/**
	 * Returns all the newsletter campaigns where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching newsletter campaigns
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsletterCampaign> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	public List<NewsletterCampaign> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

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
	public List<NewsletterCampaign> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				uuid,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<NewsletterCampaign> list = (List<NewsletterCampaign>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_NEWSLETTERCAMPAIGN_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(NewsletterCampaignModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<NewsletterCampaign>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_UUID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_UUID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

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
	public NewsletterCampaign findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchCampaignException, SystemException {
		List<NewsletterCampaign> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchCampaignException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

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
	public NewsletterCampaign findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchCampaignException, SystemException {
		int count = countByUuid(uuid);

		List<NewsletterCampaign> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchCampaignException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

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
	public NewsletterCampaign[] findByUuid_PrevAndNext(long campaignId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchCampaignException, SystemException {
		NewsletterCampaign newsletterCampaign = findByPrimaryKey(campaignId);

		Session session = null;

		try {
			session = openSession();

			NewsletterCampaign[] array = new NewsletterCampaignImpl[3];

			array[0] = getByUuid_PrevAndNext(session, newsletterCampaign, uuid,
					orderByComparator, true);

			array[1] = newsletterCampaign;

			array[2] = getByUuid_PrevAndNext(session, newsletterCampaign, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected NewsletterCampaign getByUuid_PrevAndNext(Session session,
		NewsletterCampaign newsletterCampaign, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_NEWSLETTERCAMPAIGN_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
		}

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

		else {
			query.append(NewsletterCampaignModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(newsletterCampaign);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<NewsletterCampaign> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the newsletter campaign where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.newsletter.NoSuchCampaignException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching newsletter campaign
	 * @throws com.liferay.newsletter.NoSuchCampaignException if a matching newsletter campaign could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterCampaign findByUUID_G(String uuid, long groupId)
		throws NoSuchCampaignException, SystemException {
		NewsletterCampaign newsletterCampaign = fetchByUUID_G(uuid, groupId);

		if (newsletterCampaign == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchCampaignException(msg.toString());
		}

		return newsletterCampaign;
	}

	/**
	 * Returns the newsletter campaign where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching newsletter campaign, or <code>null</code> if a matching newsletter campaign could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterCampaign fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the newsletter campaign where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching newsletter campaign, or <code>null</code> if a matching newsletter campaign could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterCampaign fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_NEWSLETTERCAMPAIGN_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			query.append(NewsletterCampaignModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<NewsletterCampaign> list = q.list();

				result = list;

				NewsletterCampaign newsletterCampaign = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					newsletterCampaign = list.get(0);

					cacheResult(newsletterCampaign);

					if ((newsletterCampaign.getUuid() == null) ||
							!newsletterCampaign.getUuid().equals(uuid) ||
							(newsletterCampaign.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, newsletterCampaign);
					}
				}

				return newsletterCampaign;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
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
				return (NewsletterCampaign)result;
			}
		}
	}

	/**
	 * Returns all the newsletter campaigns where contentId = &#63;.
	 *
	 * @param contentId the content ID
	 * @return the matching newsletter campaigns
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsletterCampaign> findByContentId(long contentId)
		throws SystemException {
		return findByContentId(contentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

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
	public List<NewsletterCampaign> findByContentId(long contentId, int start,
		int end) throws SystemException {
		return findByContentId(contentId, start, end, null);
	}

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
	public List<NewsletterCampaign> findByContentId(long contentId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				contentId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<NewsletterCampaign> list = (List<NewsletterCampaign>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CONTENTID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_NEWSLETTERCAMPAIGN_WHERE);

			query.append(_FINDER_COLUMN_CONTENTID_CONTENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(NewsletterCampaignModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contentId);

				list = (List<NewsletterCampaign>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_CONTENTID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CONTENTID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

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
	public NewsletterCampaign findByContentId_First(long contentId,
		OrderByComparator orderByComparator)
		throws NoSuchCampaignException, SystemException {
		List<NewsletterCampaign> list = findByContentId(contentId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("contentId=");
			msg.append(contentId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchCampaignException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

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
	public NewsletterCampaign findByContentId_Last(long contentId,
		OrderByComparator orderByComparator)
		throws NoSuchCampaignException, SystemException {
		int count = countByContentId(contentId);

		List<NewsletterCampaign> list = findByContentId(contentId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("contentId=");
			msg.append(contentId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchCampaignException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

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
	public NewsletterCampaign[] findByContentId_PrevAndNext(long campaignId,
		long contentId, OrderByComparator orderByComparator)
		throws NoSuchCampaignException, SystemException {
		NewsletterCampaign newsletterCampaign = findByPrimaryKey(campaignId);

		Session session = null;

		try {
			session = openSession();

			NewsletterCampaign[] array = new NewsletterCampaignImpl[3];

			array[0] = getByContentId_PrevAndNext(session, newsletterCampaign,
					contentId, orderByComparator, true);

			array[1] = newsletterCampaign;

			array[2] = getByContentId_PrevAndNext(session, newsletterCampaign,
					contentId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected NewsletterCampaign getByContentId_PrevAndNext(Session session,
		NewsletterCampaign newsletterCampaign, long contentId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_NEWSLETTERCAMPAIGN_WHERE);

		query.append(_FINDER_COLUMN_CONTENTID_CONTENTID_2);

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

		else {
			query.append(NewsletterCampaignModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(contentId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(newsletterCampaign);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<NewsletterCampaign> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the newsletter campaigns where sendDate &le; &#63; and sent = &#63;.
	 *
	 * @param sendDate the send date
	 * @param sent the sent
	 * @return the matching newsletter campaigns
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsletterCampaign> findBySD_S(Date sendDate, boolean sent)
		throws SystemException {
		return findBySD_S(sendDate, sent, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

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
	public List<NewsletterCampaign> findBySD_S(Date sendDate, boolean sent,
		int start, int end) throws SystemException {
		return findBySD_S(sendDate, sent, start, end, null);
	}

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
	public List<NewsletterCampaign> findBySD_S(Date sendDate, boolean sent,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				sendDate, sent,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<NewsletterCampaign> list = (List<NewsletterCampaign>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_SD_S,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_NEWSLETTERCAMPAIGN_WHERE);

			if (sendDate == null) {
				query.append(_FINDER_COLUMN_SD_S_SENDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_SD_S_SENDDATE_2);
			}

			query.append(_FINDER_COLUMN_SD_S_SENT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(NewsletterCampaignModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (sendDate != null) {
					qPos.add(CalendarUtil.getTimestamp(sendDate));
				}

				qPos.add(sent);

				list = (List<NewsletterCampaign>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_SD_S,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_SD_S,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

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
	public NewsletterCampaign findBySD_S_First(Date sendDate, boolean sent,
		OrderByComparator orderByComparator)
		throws NoSuchCampaignException, SystemException {
		List<NewsletterCampaign> list = findBySD_S(sendDate, sent, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("sendDate=");
			msg.append(sendDate);

			msg.append(", sent=");
			msg.append(sent);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchCampaignException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

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
	public NewsletterCampaign findBySD_S_Last(Date sendDate, boolean sent,
		OrderByComparator orderByComparator)
		throws NoSuchCampaignException, SystemException {
		int count = countBySD_S(sendDate, sent);

		List<NewsletterCampaign> list = findBySD_S(sendDate, sent, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("sendDate=");
			msg.append(sendDate);

			msg.append(", sent=");
			msg.append(sent);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchCampaignException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

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
	public NewsletterCampaign[] findBySD_S_PrevAndNext(long campaignId,
		Date sendDate, boolean sent, OrderByComparator orderByComparator)
		throws NoSuchCampaignException, SystemException {
		NewsletterCampaign newsletterCampaign = findByPrimaryKey(campaignId);

		Session session = null;

		try {
			session = openSession();

			NewsletterCampaign[] array = new NewsletterCampaignImpl[3];

			array[0] = getBySD_S_PrevAndNext(session, newsletterCampaign,
					sendDate, sent, orderByComparator, true);

			array[1] = newsletterCampaign;

			array[2] = getBySD_S_PrevAndNext(session, newsletterCampaign,
					sendDate, sent, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected NewsletterCampaign getBySD_S_PrevAndNext(Session session,
		NewsletterCampaign newsletterCampaign, Date sendDate, boolean sent,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_NEWSLETTERCAMPAIGN_WHERE);

		if (sendDate == null) {
			query.append(_FINDER_COLUMN_SD_S_SENDDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_SD_S_SENDDATE_2);
		}

		query.append(_FINDER_COLUMN_SD_S_SENT_2);

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

		else {
			query.append(NewsletterCampaignModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (sendDate != null) {
			qPos.add(CalendarUtil.getTimestamp(sendDate));
		}

		qPos.add(sent);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(newsletterCampaign);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<NewsletterCampaign> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the newsletter campaigns.
	 *
	 * @return the newsletter campaigns
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsletterCampaign> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<NewsletterCampaign> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

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
	public List<NewsletterCampaign> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<NewsletterCampaign> list = (List<NewsletterCampaign>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_NEWSLETTERCAMPAIGN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NEWSLETTERCAMPAIGN.concat(NewsletterCampaignModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<NewsletterCampaign>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<NewsletterCampaign>)QueryUtil.list(q,
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
	 * Removes all the newsletter campaigns where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (NewsletterCampaign newsletterCampaign : findByUuid(uuid)) {
			newsletterCampaignPersistence.remove(newsletterCampaign);
		}
	}

	/**
	 * Removes the newsletter campaign where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchCampaignException, SystemException {
		NewsletterCampaign newsletterCampaign = findByUUID_G(uuid, groupId);

		newsletterCampaignPersistence.remove(newsletterCampaign);
	}

	/**
	 * Removes all the newsletter campaigns where contentId = &#63; from the database.
	 *
	 * @param contentId the content ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByContentId(long contentId) throws SystemException {
		for (NewsletterCampaign newsletterCampaign : findByContentId(contentId)) {
			newsletterCampaignPersistence.remove(newsletterCampaign);
		}
	}

	/**
	 * Removes all the newsletter campaigns where sendDate &le; &#63; and sent = &#63; from the database.
	 *
	 * @param sendDate the send date
	 * @param sent the sent
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySD_S(Date sendDate, boolean sent)
		throws SystemException {
		for (NewsletterCampaign newsletterCampaign : findBySD_S(sendDate, sent)) {
			newsletterCampaignPersistence.remove(newsletterCampaign);
		}
	}

	/**
	 * Removes all the newsletter campaigns from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (NewsletterCampaign newsletterCampaign : findAll()) {
			newsletterCampaignPersistence.remove(newsletterCampaign);
		}
	}

	/**
	 * Returns the number of newsletter campaigns where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching newsletter campaigns
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_NEWSLETTERCAMPAIGN_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of newsletter campaigns where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching newsletter campaigns
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_NEWSLETTERCAMPAIGN_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of newsletter campaigns where contentId = &#63;.
	 *
	 * @param contentId the content ID
	 * @return the number of matching newsletter campaigns
	 * @throws SystemException if a system exception occurred
	 */
	public int countByContentId(long contentId) throws SystemException {
		Object[] finderArgs = new Object[] { contentId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTENTID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_NEWSLETTERCAMPAIGN_WHERE);

			query.append(_FINDER_COLUMN_CONTENTID_CONTENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contentId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTENTID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of newsletter campaigns where sendDate &le; &#63; and sent = &#63;.
	 *
	 * @param sendDate the send date
	 * @param sent the sent
	 * @return the number of matching newsletter campaigns
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySD_S(Date sendDate, boolean sent)
		throws SystemException {
		Object[] finderArgs = new Object[] { sendDate, sent };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SD_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_NEWSLETTERCAMPAIGN_WHERE);

			if (sendDate == null) {
				query.append(_FINDER_COLUMN_SD_S_SENDDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_SD_S_SENDDATE_2);
			}

			query.append(_FINDER_COLUMN_SD_S_SENT_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (sendDate != null) {
					qPos.add(CalendarUtil.getTimestamp(sendDate));
				}

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SD_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of newsletter campaigns.
	 *
	 * @return the number of newsletter campaigns
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

				Query q = session.createQuery(_SQL_COUNT_NEWSLETTERCAMPAIGN);

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
	 * Initializes the newsletter campaign persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.newsletter.model.NewsletterCampaign")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<NewsletterCampaign>> listenersList = new ArrayList<ModelListener<NewsletterCampaign>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<NewsletterCampaign>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(NewsletterCampaignImpl.class.getName());
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
	private static final String _SQL_SELECT_NEWSLETTERCAMPAIGN = "SELECT newsletterCampaign FROM NewsletterCampaign newsletterCampaign";
	private static final String _SQL_SELECT_NEWSLETTERCAMPAIGN_WHERE = "SELECT newsletterCampaign FROM NewsletterCampaign newsletterCampaign WHERE ";
	private static final String _SQL_COUNT_NEWSLETTERCAMPAIGN = "SELECT COUNT(newsletterCampaign) FROM NewsletterCampaign newsletterCampaign";
	private static final String _SQL_COUNT_NEWSLETTERCAMPAIGN_WHERE = "SELECT COUNT(newsletterCampaign) FROM NewsletterCampaign newsletterCampaign WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "newsletterCampaign.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "newsletterCampaign.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(newsletterCampaign.uuid IS NULL OR newsletterCampaign.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "newsletterCampaign.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "newsletterCampaign.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(newsletterCampaign.uuid IS NULL OR newsletterCampaign.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "newsletterCampaign.groupId = ?";
	private static final String _FINDER_COLUMN_CONTENTID_CONTENTID_2 = "newsletterCampaign.contentId = ?";
	private static final String _FINDER_COLUMN_SD_S_SENDDATE_1 = "newsletterCampaign.sendDate <= NULL AND ";
	private static final String _FINDER_COLUMN_SD_S_SENDDATE_2 = "newsletterCampaign.sendDate <= ? AND ";
	private static final String _FINDER_COLUMN_SD_S_SENT_2 = "newsletterCampaign.sent = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "newsletterCampaign.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NewsletterCampaign exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No NewsletterCampaign exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(NewsletterCampaignPersistenceImpl.class);
	private static NewsletterCampaign _nullNewsletterCampaign = new NewsletterCampaignImpl() {
			public Object clone() {
				return this;
			}

			public CacheModel<NewsletterCampaign> toCacheModel() {
				return _nullNewsletterCampaignCacheModel;
			}
		};

	private static CacheModel<NewsletterCampaign> _nullNewsletterCampaignCacheModel =
		new CacheModel<NewsletterCampaign>() {
			public NewsletterCampaign toEntityModel() {
				return _nullNewsletterCampaign;
			}
		};
}