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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class NewsletterContactFinderUtil {
	public static int countByC_G_C_E_N_S(long companyId, long groupId,
		long campaignId, java.lang.String email, java.lang.String name,
		boolean sent, int start, int end, boolean andOperator,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_G_C_E_N_S(companyId, groupId, campaignId, email,
			name, sent, start, end, andOperator, orderByComparator);
	}

	public static int countByC_G_N_E(long companyId, long groupId,
		java.lang.String name, java.lang.String email, int start, int end,
		boolean andOperator,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_G_N_E(companyId, groupId, name, email, start, end,
			andOperator, orderByComparator);
	}

	public static int countByCampaign(long campaignId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByCampaign(campaignId);
	}

	public static int countByCampaignContent(long campaignContentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByCampaignContent(campaignContentId);
	}

	public static int countByKeywords(long companyId, long groupId,
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByKeywords(companyId, groupId, keywords, start, end,
			orderByComparator);
	}

	public static int countByKeywords(long companyId, long groupId,
		long campaignId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByKeywords(companyId, groupId, campaignId, keywords,
			start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.newsletter.model.NewsletterContact> findByC_G_C_E_N_S(
		long companyId, long groupId, long campaignId, java.lang.String email,
		java.lang.String name, boolean sent, int start, int end,
		boolean andOperator,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_C_E_N_S(companyId, groupId, campaignId, email,
			name, sent, start, end, andOperator, orderByComparator);
	}

	public static java.util.List<com.liferay.newsletter.model.NewsletterContact> findByC_G_N_E(
		long companyId, long groupId, java.lang.String name,
		java.lang.String email, int start, int end, boolean andOperator,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_N_E(companyId, groupId, name, email, start, end,
			andOperator, orderByComparator);
	}

	public static java.util.List<com.liferay.newsletter.model.NewsletterContact> findByEmail(
		long companyId, long groupId, java.lang.String email, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByEmail(companyId, groupId, email, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.newsletter.model.NewsletterContact> findByKeywords(
		long companyId, long groupId, long campaignId,
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByKeywords(companyId, groupId, campaignId, keywords,
			start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.newsletter.model.NewsletterContact> findByKeywords(
		long companyId, long groupId, java.lang.String keywords, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByKeywords(companyId, groupId, keywords, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.newsletter.model.NewsletterContact> findByNameAndCampaign(
		long companyId, long groupId, java.lang.String name, long campaignId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByNameAndCampaign(companyId, groupId, name, campaignId,
			start, end, orderByComparator);
	}

	public static NewsletterContactFinder getFinder() {
		if (_finder == null) {
			_finder = (NewsletterContactFinder)PortletBeanLocatorUtil.locate(com.liferay.newsletter.service.ClpSerializer.getServletContextName(),
					NewsletterContactFinder.class.getName());

			ReferenceRegistry.registerReference(NewsletterContactFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(NewsletterContactFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(NewsletterContactFinderUtil.class,
			"_finder");
	}

	private static NewsletterContactFinder _finder;
}