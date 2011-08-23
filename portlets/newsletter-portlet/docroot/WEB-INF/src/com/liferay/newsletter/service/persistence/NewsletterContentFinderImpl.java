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

import com.liferay.newsletter.model.NewsletterContent;
import com.liferay.newsletter.model.impl.NewsletterContentImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Bruno Pinheiro
 * @author Marcellus Tavares
 */
public class NewsletterContentFinderImpl
	extends BasePersistenceImpl<NewsletterContent>
	implements NewsletterContentFinder {

	public static String FIND_BY_T_C =
		NewsletterContentFinder.class.getName() + ".findBy_T_C";

	public static String FIND_BY_TITLE =
		NewsletterContentFinder.class.getName() + ".findByTitle";

	public List<NewsletterContent> findByKeywords(
			long companyId, long groupId, String keywords, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] titles = null;
		String[] contents = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			titles = CustomSQLUtil.keywords(keywords);
			contents = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return doFindByT_C(
			companyId, groupId, titles, contents, andOperator, start, end,
			orderByComparator);
	}

	public List<NewsletterContent> findByTitle(
			long companyId, long groupId, String title, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] titles = null;
		boolean andOperator = false;

		if (Validator.isNotNull(title)) {
			titles = CustomSQLUtil.keywords(title);
		}
		else {
			andOperator = true;
		}

		return doFindByTitle(
			companyId, groupId, titles, andOperator, start, end,
			orderByComparator);
	}

	protected List<NewsletterContent> doFindByT_C(
			long companyId, long groupId, String[] titles, String[] contents,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		titles = CustomSQLUtil.keywords(titles);
		contents = CustomSQLUtil.keywords(contents);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_T_C);

			if (groupId <= 0) {
				sql = StringUtil.replace(sql, "(groupId = ?) AND", "");
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(title)", StringPool.LIKE, false, titles);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(content)", StringPool.LIKE, false, contents);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
			sql = CustomSQLUtil.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("NewsletterContent", NewsletterContentImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(titles);
			qPos.add(contents);

			return (List<NewsletterContent>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<NewsletterContent> doFindByTitle(
			long companyId, long groupId, String[] titles, boolean andOperator,
			int start, int end,	OrderByComparator orderByComparator)
		throws SystemException {

		titles = CustomSQLUtil.keywords(titles);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_TITLE);

			if (groupId <= 0) {
				sql = StringUtil.replace(sql, "(groupId = ?) AND", "");
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(title)", StringPool.LIKE, false, titles);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
			sql = CustomSQLUtil.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("NewsletterContent", NewsletterContentImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(titles, 2);

			return (List<NewsletterContent>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}