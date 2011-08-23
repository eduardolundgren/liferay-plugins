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

import com.liferay.newsletter.model.NewsletterContact;
import com.liferay.newsletter.model.impl.NewsletterContactImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Bruno Pinheiro
 */
public class NewsletterContactFinderImpl
	extends BasePersistenceImpl<NewsletterContact>
	implements NewsletterContactFinder {

	public static String COUNT_BY_C_G_C_E_N =
		NewsletterContactFinder.class.getName() + ".countBy_C_G_C_E_N";

	public static String COUNT_BY_C_G_C_E_N_S =
		NewsletterContactFinder.class.getName() + ".countBy_C_G_C_E_N_S";

	public static String COUNT_BY_C_G_N_E =
		NewsletterContactFinder.class.getName() + ".countBy_C_G_N_E";

	public static String COUNT_BY_CAMPAIGN =
		NewsletterContactFinder.class.getName() + ".countByCampaign";

	public static String COUNT_BY_CAMPAIGN_CONTENT =
		NewsletterContactFinder.class.getName() + ".countByCampaignContent";

	public static String FIND_BY_C_G_C_E_N =
		NewsletterContactFinder.class.getName() + ".findBy_C_G_C_E_N";

	public static String FIND_BY_C_G_C_E_N_S =
		NewsletterContactFinder.class.getName() + ".findBy_C_G_C_E_N_S";

	public static String FIND_BY_C_G_N_E =
		NewsletterContactFinder.class.getName() + ".findBy_C_G_N_E";

	public static String FIND_BY_EMAIL =
		NewsletterContactFinder.class.getName() + ".findByEmail";

	public static String FIND_BY_KEYWORDS =
		NewsletterContactFinder.class.getName() + ".findByKeywords";

	public static String FIND_BY_NAME_CAMPAIGN =
		NewsletterContactFinder.class.getName() + ".findByNameAndCampaign";

	public int countByC_G_C_E_N_S(
			long companyId, long groupId, long campaignId, String email,
			String name, boolean sent, int start, int end, boolean andOperator,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] names = null;
		String[] emails = null;

		names = CustomSQLUtil.keywords(name);
		emails = CustomSQLUtil.keywords(email);

		return doCountByC_G_C_E_N_S(
			companyId, groupId, campaignId, emails, names, sent, andOperator,
			start, end, orderByComparator);
	}

	public int countByC_G_N_E(
			long companyId, long groupId, String name,String email, int start,
			int end, boolean andOperator, OrderByComparator orderByComparator)
		throws SystemException {

		String[] names = null;
		String[] emails = null;

		names = CustomSQLUtil.keywords(name);
		emails = CustomSQLUtil.keywords(email);

		return doCountByC_G_N_E(
			companyId, groupId, names, emails, andOperator,	start, end,
			orderByComparator);
	}

	public int countByCampaign(long campaignId)	throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_CAMPAIGN);

			SQLQuery q = session.createSQLQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			qPos.add(campaignId);

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public int countByCampaignContent(long campaignContentId)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_CAMPAIGN_CONTENT);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(campaignContentId);

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public int countByKeywords(
			long companyId, long groupId, String keywords,
			int start, int end, OrderByComparator orderByComparator)
		throws SystemException {

		String[] names = null;
		String[] emails = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			emails = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return doCountByC_G_N_E(
			companyId, groupId, names, emails, andOperator, start, end,
			orderByComparator);
	}

	public int countByKeywords(
			long companyId, long groupId, long campaignId, String keywords,
			int start, int end, OrderByComparator orderByComparator)
		throws SystemException {

		String[] names = null;
		String[] emails = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			emails = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return doCountByC_G_C_E_N(
			companyId, groupId, campaignId, emails, names, andOperator, start,
			end, orderByComparator);
	}

	public List<NewsletterContact> findByC_G_C_E_N_S(
			long companyId, long groupId, long campaignId, String email,
			String name, boolean sent, int start, int end, boolean andOperator,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] names = null;
		String[] emails = null;

		names = CustomSQLUtil.keywords(name);
		emails = CustomSQLUtil.keywords(email);

		return doFindByC_G_C_E_N_S(
			companyId, groupId, campaignId, emails, names, sent, andOperator,
			start, end, orderByComparator);
	}

	public List<NewsletterContact> findByC_G_N_E(
			long companyId, long groupId, String name, String email, int start,
			int end, boolean andOperator, OrderByComparator orderByComparator)
		throws SystemException {

		String[] names = null;
		String[] emails = null;

		names = CustomSQLUtil.keywords(name);
		emails = CustomSQLUtil.keywords(email);

		return doFindByC_G_N_E(
			companyId, groupId, names, emails, andOperator, start, end,
			orderByComparator);
	}

	public List<NewsletterContact> findByEmail(
			long companyId, long groupId, String email, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] emails = null;

		emails = CustomSQLUtil.keywords(email);

		return dofindByEmail(
			companyId, groupId, emails, start, end, orderByComparator);
	}

	public List<NewsletterContact> findByKeywords(
			long companyId, long groupId, long campaignId, String keywords,
			int start, int end, OrderByComparator orderByComparator)
		throws SystemException {

		String[] names = null;
		String[] emails = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			emails = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return doFindByC_G_C_E_N(
			companyId, groupId, campaignId, emails, names, andOperator, start,
			end, orderByComparator);
	}

	public List<NewsletterContact> findByKeywords(
			long companyId, long groupId, String keywords, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] names = null;
		String[] emails = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			emails = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return doFindByC_G_N_E(
			companyId, groupId, names, emails, andOperator,	start, end,
			orderByComparator);
	}

	public List<NewsletterContact> findByNameAndCampaign(
			long companyId, long groupId, String name, long campaignId,
			int start, int end, OrderByComparator orderByComparator)
		throws SystemException {

		String[] names = null;
		boolean andOperator = false;

		if (Validator.isNotNull(name)) {
			names = CustomSQLUtil.keywords(name);
		}
		else {
			andOperator = true;
		}

		return doFindByNameAndCampaign(
			companyId, groupId, names, campaignId, andOperator, start, end,
			orderByComparator);
	}

	protected int doCountByC_G_C_E_N(
			long companyId, long groupId, long campaignId, String[] emails,
			String[] names, boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		names = CustomSQLUtil.keywords(names);
		emails = CustomSQLUtil.keywords(emails);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_C_G_C_E_N);

			if (groupId <= 0) {
				sql = StringUtil.replace(sql, "(groupId = ?) AND", "");
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(email)", StringPool.LIKE, false, emails);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(campaignId);
			qPos.add(emails, 2);
			qPos.add(names, 2);

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected int doCountByC_G_C_E_N_S(
			long companyId, long groupId, long campaignId, String[] emails,
			String[] names, boolean sent, boolean andOperator, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		names = CustomSQLUtil.keywords(names);
		emails = CustomSQLUtil.keywords(emails);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_C_G_C_E_N_S);

			if (groupId <= 0) {
				sql = StringUtil.replace(sql, "(groupId = ?) AND", "");
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(email)", StringPool.LIKE, false, emails);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(campaignId);
			qPos.add(emails);
			qPos.add(names);
			qPos.add(sent);

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected int doCountByC_G_N_E(
			long companyId, long groupId, String[] names, String[] emails,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		names = CustomSQLUtil.keywords(names);
		emails = CustomSQLUtil.keywords(emails);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_C_G_N_E);

			if (groupId <= 0) {
				sql = StringUtil.replace(sql, "(groupId = ?) AND", "");
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(email)", StringPool.LIKE, false, emails);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(emails);
			qPos.add(names);

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<NewsletterContact> doFindByC_G_C_E_N(
			long companyId, long groupId, long campaignId, String[] emails,
			String[] names,	boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		names = CustomSQLUtil.keywords(names);
		emails = CustomSQLUtil.keywords(emails);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_G_C_E_N);

			if (groupId <= 0) {
				sql = StringUtil.replace(sql, "(groupId = ?) AND", "");
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(email)", StringPool.LIKE, false, emails);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
			sql = CustomSQLUtil.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("NewsletterContact", NewsletterContactImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(campaignId);
			qPos.add(emails, 2);
			qPos.add(names, 2);

			return (List<NewsletterContact>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<NewsletterContact> doFindByC_G_C_E_N_S(
			long companyId, long groupId, long campaignId, String[] emails,
			String[] names, boolean sent, boolean andOperator, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		names = CustomSQLUtil.keywords(names);
		emails = CustomSQLUtil.keywords(emails);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_G_C_E_N_S);

			if (groupId <= 0) {
				sql = StringUtil.replace(sql, "(groupId = ?) AND", "");
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(email)", StringPool.LIKE, false, emails);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
			sql = CustomSQLUtil.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("NewsletterContact", NewsletterContactImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(campaignId);
			qPos.add(emails, 2);
			qPos.add(names, 2);
			qPos.add(sent);

			return (List<NewsletterContact>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<NewsletterContact> doFindByC_G_N_E(
			long companyId, long groupId, String[] names, String[] emails,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		names = CustomSQLUtil.keywords(names);
		emails = CustomSQLUtil.keywords(emails);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_G_N_E);

			if (groupId <= 0) {
				sql = StringUtil.replace(sql, "(groupId = ?) AND", "");
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(email)", StringPool.LIKE, false, emails);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
			sql = CustomSQLUtil.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("NewsletterContact", NewsletterContactImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(emails);
			qPos.add(names);

			return (List<NewsletterContact>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<NewsletterContact> dofindByEmail(
			long companyId, long groupId, String[] emails, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		emails = CustomSQLUtil.keywords(emails);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_EMAIL);

			if (groupId <= 0) {
				sql = StringUtil.replace(sql, "(groupId = ?) AND", "");
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(email)", StringPool.LIKE, false, emails);

			sql = CustomSQLUtil.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("NewsletterContact", NewsletterContactImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(emails, 2);

			return (List<NewsletterContact>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<NewsletterContact> doFindByNameAndCampaign(
			long companyId, long groupId, String[] names, long campaignId,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		names = CustomSQLUtil.keywords(names);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_NAME_CAMPAIGN);

			if (groupId <= 0) {
				sql = StringUtil.replace(sql, "(groupId = ?) AND", "");
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
			sql = CustomSQLUtil.replaceOrderBy(sql, orderByComparator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("NewsletterContact", NewsletterContact.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(names, 2);
			qPos.add(campaignId);

			return (List<NewsletterContact>)QueryUtil.list(
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