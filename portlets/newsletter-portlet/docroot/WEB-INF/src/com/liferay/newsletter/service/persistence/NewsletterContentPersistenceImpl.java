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

import com.liferay.newsletter.NoSuchContentException;
import com.liferay.newsletter.model.NewsletterContent;
import com.liferay.newsletter.model.impl.NewsletterContentImpl;
import com.liferay.newsletter.model.impl.NewsletterContentModelImpl;

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
import java.util.List;

/**
 * The persistence implementation for the newsletter content service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsletterContentPersistence
 * @see NewsletterContentUtil
 * @generated
 */
public class NewsletterContentPersistenceImpl extends BasePersistenceImpl<NewsletterContent>
	implements NewsletterContentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NewsletterContentUtil} to access the newsletter content persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NewsletterContentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(NewsletterContentModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContentModelImpl.FINDER_CACHE_ENABLED,
			NewsletterContentImpl.class, FINDER_CLASS_NAME_LIST, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(NewsletterContentModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(NewsletterContentModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContentModelImpl.FINDER_CACHE_ENABLED,
			NewsletterContentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(NewsletterContentModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(NewsletterContentModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContentModelImpl.FINDER_CACHE_ENABLED,
			NewsletterContentImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NewsletterContentModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the newsletter content in the entity cache if it is enabled.
	 *
	 * @param newsletterContent the newsletter content
	 */
	public void cacheResult(NewsletterContent newsletterContent) {
		EntityCacheUtil.putResult(NewsletterContentModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContentImpl.class, newsletterContent.getPrimaryKey(),
			newsletterContent);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				newsletterContent.getUuid(),
				Long.valueOf(newsletterContent.getGroupId())
			}, newsletterContent);

		newsletterContent.resetOriginalValues();
	}

	/**
	 * Caches the newsletter contents in the entity cache if it is enabled.
	 *
	 * @param newsletterContents the newsletter contents
	 */
	public void cacheResult(List<NewsletterContent> newsletterContents) {
		for (NewsletterContent newsletterContent : newsletterContents) {
			if (EntityCacheUtil.getResult(
						NewsletterContentModelImpl.ENTITY_CACHE_ENABLED,
						NewsletterContentImpl.class,
						newsletterContent.getPrimaryKey(), this) == null) {
				cacheResult(newsletterContent);
			}
		}
	}

	/**
	 * Clears the cache for all newsletter contents.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(NewsletterContentImpl.class.getName());
		}

		EntityCacheUtil.clearCache(NewsletterContentImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the newsletter content.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NewsletterContent newsletterContent) {
		EntityCacheUtil.removeResult(NewsletterContentModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContentImpl.class, newsletterContent.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				newsletterContent.getUuid(),
				Long.valueOf(newsletterContent.getGroupId())
			});
	}

	/**
	 * Creates a new newsletter content with the primary key. Does not add the newsletter content to the database.
	 *
	 * @param contentId the primary key for the new newsletter content
	 * @return the new newsletter content
	 */
	public NewsletterContent create(long contentId) {
		NewsletterContent newsletterContent = new NewsletterContentImpl();

		newsletterContent.setNew(true);
		newsletterContent.setPrimaryKey(contentId);

		String uuid = PortalUUIDUtil.generate();

		newsletterContent.setUuid(uuid);

		return newsletterContent;
	}

	/**
	 * Removes the newsletter content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the newsletter content
	 * @return the newsletter content that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a newsletter content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NewsletterContent remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the newsletter content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contentId the primary key of the newsletter content
	 * @return the newsletter content that was removed
	 * @throws com.liferay.newsletter.NoSuchContentException if a newsletter content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterContent remove(long contentId)
		throws NoSuchContentException, SystemException {
		Session session = null;

		try {
			session = openSession();

			NewsletterContent newsletterContent = (NewsletterContent)session.get(NewsletterContentImpl.class,
					Long.valueOf(contentId));

			if (newsletterContent == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + contentId);
				}

				throw new NoSuchContentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					contentId);
			}

			return newsletterContentPersistence.remove(newsletterContent);
		}
		catch (NoSuchContentException nsee) {
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
	 * Removes the newsletter content from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsletterContent the newsletter content
	 * @return the newsletter content that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NewsletterContent remove(NewsletterContent newsletterContent)
		throws SystemException {
		return super.remove(newsletterContent);
	}

	@Override
	protected NewsletterContent removeImpl(NewsletterContent newsletterContent)
		throws SystemException {
		newsletterContent = toUnwrappedModel(newsletterContent);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, newsletterContent);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		NewsletterContentModelImpl newsletterContentModelImpl = (NewsletterContentModelImpl)newsletterContent;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				newsletterContentModelImpl.getUuid(),
				Long.valueOf(newsletterContentModelImpl.getGroupId())
			});

		EntityCacheUtil.removeResult(NewsletterContentModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContentImpl.class, newsletterContent.getPrimaryKey());

		return newsletterContent;
	}

	@Override
	public NewsletterContent updateImpl(
		com.liferay.newsletter.model.NewsletterContent newsletterContent,
		boolean merge) throws SystemException {
		newsletterContent = toUnwrappedModel(newsletterContent);

		boolean isNew = newsletterContent.isNew();

		NewsletterContentModelImpl newsletterContentModelImpl = (NewsletterContentModelImpl)newsletterContent;

		if (Validator.isNull(newsletterContent.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			newsletterContent.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, newsletterContent, merge);

			newsletterContent.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(NewsletterContentModelImpl.ENTITY_CACHE_ENABLED,
			NewsletterContentImpl.class, newsletterContent.getPrimaryKey(),
			newsletterContent);

		if (!isNew &&
				(!Validator.equals(newsletterContent.getUuid(),
					newsletterContentModelImpl.getOriginalUuid()) ||
				(newsletterContent.getGroupId() != newsletterContentModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					newsletterContentModelImpl.getOriginalUuid(),
					Long.valueOf(
						newsletterContentModelImpl.getOriginalGroupId())
				});
		}

		if (isNew ||
				(!Validator.equals(newsletterContent.getUuid(),
					newsletterContentModelImpl.getOriginalUuid()) ||
				(newsletterContent.getGroupId() != newsletterContentModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					newsletterContent.getUuid(),
					Long.valueOf(newsletterContent.getGroupId())
				}, newsletterContent);
		}

		return newsletterContent;
	}

	protected NewsletterContent toUnwrappedModel(
		NewsletterContent newsletterContent) {
		if (newsletterContent instanceof NewsletterContentImpl) {
			return newsletterContent;
		}

		NewsletterContentImpl newsletterContentImpl = new NewsletterContentImpl();

		newsletterContentImpl.setNew(newsletterContent.isNew());
		newsletterContentImpl.setPrimaryKey(newsletterContent.getPrimaryKey());

		newsletterContentImpl.setUuid(newsletterContent.getUuid());
		newsletterContentImpl.setContentId(newsletterContent.getContentId());
		newsletterContentImpl.setGroupId(newsletterContent.getGroupId());
		newsletterContentImpl.setCompanyId(newsletterContent.getCompanyId());
		newsletterContentImpl.setUserId(newsletterContent.getUserId());
		newsletterContentImpl.setUserName(newsletterContent.getUserName());
		newsletterContentImpl.setCreateDate(newsletterContent.getCreateDate());
		newsletterContentImpl.setModifiedDate(newsletterContent.getModifiedDate());
		newsletterContentImpl.setTitle(newsletterContent.getTitle());
		newsletterContentImpl.setContent(newsletterContent.getContent());
		newsletterContentImpl.setArticleId(newsletterContent.getArticleId());

		return newsletterContentImpl;
	}

	/**
	 * Returns the newsletter content with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the newsletter content
	 * @return the newsletter content
	 * @throws com.liferay.portal.NoSuchModelException if a newsletter content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NewsletterContent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the newsletter content with the primary key or throws a {@link com.liferay.newsletter.NoSuchContentException} if it could not be found.
	 *
	 * @param contentId the primary key of the newsletter content
	 * @return the newsletter content
	 * @throws com.liferay.newsletter.NoSuchContentException if a newsletter content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterContent findByPrimaryKey(long contentId)
		throws NoSuchContentException, SystemException {
		NewsletterContent newsletterContent = fetchByPrimaryKey(contentId);

		if (newsletterContent == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + contentId);
			}

			throw new NoSuchContentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				contentId);
		}

		return newsletterContent;
	}

	/**
	 * Returns the newsletter content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the newsletter content
	 * @return the newsletter content, or <code>null</code> if a newsletter content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public NewsletterContent fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the newsletter content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contentId the primary key of the newsletter content
	 * @return the newsletter content, or <code>null</code> if a newsletter content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterContent fetchByPrimaryKey(long contentId)
		throws SystemException {
		NewsletterContent newsletterContent = (NewsletterContent)EntityCacheUtil.getResult(NewsletterContentModelImpl.ENTITY_CACHE_ENABLED,
				NewsletterContentImpl.class, contentId, this);

		if (newsletterContent == _nullNewsletterContent) {
			return null;
		}

		if (newsletterContent == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				newsletterContent = (NewsletterContent)session.get(NewsletterContentImpl.class,
						Long.valueOf(contentId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (newsletterContent != null) {
					cacheResult(newsletterContent);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(NewsletterContentModelImpl.ENTITY_CACHE_ENABLED,
						NewsletterContentImpl.class, contentId,
						_nullNewsletterContent);
				}

				closeSession(session);
			}
		}

		return newsletterContent;
	}

	/**
	 * Returns all the newsletter contents where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching newsletter contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsletterContent> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the newsletter contents where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of newsletter contents
	 * @param end the upper bound of the range of newsletter contents (not inclusive)
	 * @return the range of matching newsletter contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsletterContent> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the newsletter contents where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of newsletter contents
	 * @param end the upper bound of the range of newsletter contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching newsletter contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsletterContent> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				uuid,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<NewsletterContent> list = (List<NewsletterContent>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
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

			query.append(_SQL_SELECT_NEWSLETTERCONTENT_WHERE);

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

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<NewsletterContent>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first newsletter content in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching newsletter content
	 * @throws com.liferay.newsletter.NoSuchContentException if a matching newsletter content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterContent findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		List<NewsletterContent> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchContentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last newsletter content in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching newsletter content
	 * @throws com.liferay.newsletter.NoSuchContentException if a matching newsletter content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterContent findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		int count = countByUuid(uuid);

		List<NewsletterContent> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchContentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the newsletter contents before and after the current newsletter content in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param contentId the primary key of the current newsletter content
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next newsletter content
	 * @throws com.liferay.newsletter.NoSuchContentException if a newsletter content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterContent[] findByUuid_PrevAndNext(long contentId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchContentException, SystemException {
		NewsletterContent newsletterContent = findByPrimaryKey(contentId);

		Session session = null;

		try {
			session = openSession();

			NewsletterContent[] array = new NewsletterContentImpl[3];

			array[0] = getByUuid_PrevAndNext(session, newsletterContent, uuid,
					orderByComparator, true);

			array[1] = newsletterContent;

			array[2] = getByUuid_PrevAndNext(session, newsletterContent, uuid,
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

	protected NewsletterContent getByUuid_PrevAndNext(Session session,
		NewsletterContent newsletterContent, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_NEWSLETTERCONTENT_WHERE);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(newsletterContent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<NewsletterContent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the newsletter content where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.newsletter.NoSuchContentException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching newsletter content
	 * @throws com.liferay.newsletter.NoSuchContentException if a matching newsletter content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterContent findByUUID_G(String uuid, long groupId)
		throws NoSuchContentException, SystemException {
		NewsletterContent newsletterContent = fetchByUUID_G(uuid, groupId);

		if (newsletterContent == null) {
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

			throw new NoSuchContentException(msg.toString());
		}

		return newsletterContent;
	}

	/**
	 * Returns the newsletter content where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching newsletter content, or <code>null</code> if a matching newsletter content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterContent fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the newsletter content where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching newsletter content, or <code>null</code> if a matching newsletter content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public NewsletterContent fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_NEWSLETTERCONTENT_WHERE);

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

				List<NewsletterContent> list = q.list();

				result = list;

				NewsletterContent newsletterContent = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					newsletterContent = list.get(0);

					cacheResult(newsletterContent);

					if ((newsletterContent.getUuid() == null) ||
							!newsletterContent.getUuid().equals(uuid) ||
							(newsletterContent.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, newsletterContent);
					}
				}

				return newsletterContent;
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
				return (NewsletterContent)result;
			}
		}
	}

	/**
	 * Returns all the newsletter contents.
	 *
	 * @return the newsletter contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsletterContent> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the newsletter contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of newsletter contents
	 * @param end the upper bound of the range of newsletter contents (not inclusive)
	 * @return the range of newsletter contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsletterContent> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the newsletter contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of newsletter contents
	 * @param end the upper bound of the range of newsletter contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of newsletter contents
	 * @throws SystemException if a system exception occurred
	 */
	public List<NewsletterContent> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<NewsletterContent> list = (List<NewsletterContent>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_NEWSLETTERCONTENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NEWSLETTERCONTENT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<NewsletterContent>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<NewsletterContent>)QueryUtil.list(q,
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
	 * Removes all the newsletter contents where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (NewsletterContent newsletterContent : findByUuid(uuid)) {
			newsletterContentPersistence.remove(newsletterContent);
		}
	}

	/**
	 * Removes the newsletter content where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchContentException, SystemException {
		NewsletterContent newsletterContent = findByUUID_G(uuid, groupId);

		newsletterContentPersistence.remove(newsletterContent);
	}

	/**
	 * Removes all the newsletter contents from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (NewsletterContent newsletterContent : findAll()) {
			newsletterContentPersistence.remove(newsletterContent);
		}
	}

	/**
	 * Returns the number of newsletter contents where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching newsletter contents
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_NEWSLETTERCONTENT_WHERE);

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
	 * Returns the number of newsletter contents where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching newsletter contents
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_NEWSLETTERCONTENT_WHERE);

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
	 * Returns the number of newsletter contents.
	 *
	 * @return the number of newsletter contents
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

				Query q = session.createQuery(_SQL_COUNT_NEWSLETTERCONTENT);

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
	 * Initializes the newsletter content persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.newsletter.model.NewsletterContent")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<NewsletterContent>> listenersList = new ArrayList<ModelListener<NewsletterContent>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<NewsletterContent>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(NewsletterContentImpl.class.getName());
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
	private static final String _SQL_SELECT_NEWSLETTERCONTENT = "SELECT newsletterContent FROM NewsletterContent newsletterContent";
	private static final String _SQL_SELECT_NEWSLETTERCONTENT_WHERE = "SELECT newsletterContent FROM NewsletterContent newsletterContent WHERE ";
	private static final String _SQL_COUNT_NEWSLETTERCONTENT = "SELECT COUNT(newsletterContent) FROM NewsletterContent newsletterContent";
	private static final String _SQL_COUNT_NEWSLETTERCONTENT_WHERE = "SELECT COUNT(newsletterContent) FROM NewsletterContent newsletterContent WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "newsletterContent.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "newsletterContent.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(newsletterContent.uuid IS NULL OR newsletterContent.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "newsletterContent.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "newsletterContent.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(newsletterContent.uuid IS NULL OR newsletterContent.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "newsletterContent.groupId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "newsletterContent.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NewsletterContent exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No NewsletterContent exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(NewsletterContentPersistenceImpl.class);
	private static NewsletterContent _nullNewsletterContent = new NewsletterContentImpl() {
			public Object clone() {
				return this;
			}

			public CacheModel<NewsletterContent> toCacheModel() {
				return _nullNewsletterContentCacheModel;
			}
		};

	private static CacheModel<NewsletterContent> _nullNewsletterContentCacheModel =
		new CacheModel<NewsletterContent>() {
			public NewsletterContent toEntityModel() {
				return _nullNewsletterContent;
			}
		};
}