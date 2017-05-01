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

/**
 * @author Brian Wing Shun Chan
 */
public interface NewsletterContactFinder {
	public int countByC_G_C_E_N_S(long companyId, long groupId,
		long campaignId, java.lang.String email, java.lang.String name,
		boolean sent, int start, int end, boolean andOperator,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByC_G_N_E(long companyId, long groupId,
		java.lang.String name, java.lang.String email, int start, int end,
		boolean andOperator,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByCampaign(long campaignId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByCampaignContent(long campaignContentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKeywords(long companyId, long groupId,
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKeywords(long companyId, long groupId, long campaignId,
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.newsletter.model.NewsletterContact> findByC_G_C_E_N_S(
		long companyId, long groupId, long campaignId, java.lang.String email,
		java.lang.String name, boolean sent, int start, int end,
		boolean andOperator,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.newsletter.model.NewsletterContact> findByC_G_N_E(
		long companyId, long groupId, java.lang.String name,
		java.lang.String email, int start, int end, boolean andOperator,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.newsletter.model.NewsletterContact> findByEmail(
		long companyId, long groupId, java.lang.String email, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.newsletter.model.NewsletterContact> findByKeywords(
		long companyId, long groupId, long campaignId,
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.newsletter.model.NewsletterContact> findByKeywords(
		long companyId, long groupId, java.lang.String keywords, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.newsletter.model.NewsletterContact> findByNameAndCampaign(
		long companyId, long groupId, java.lang.String name, long campaignId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;
}