/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.calendar.util;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.util.comparator.CalendarNameComparator;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.util.UniqueList;

import java.util.List;
import java.util.Locale;

/**
 * @author Eduardo Lundgren
 * @author Peter Shin
 * @author Fabio Pezzutto
 */
public class CalendarUtil {

	public static final String AM_SUFFIX = "am";

	public static final String HOUR_SUFFIX = "h";

	public static final String PM_SUFFIX = "pm";

	public static String formatHour(int isoHour) {
		return formatHour(isoHour, true, true, true);
	}

	public static String formatHour(int isoHour, boolean isoFormat) {
		return formatHour(isoHour, isoFormat, true, true);
	}

	public static String formatHour(
		int isoHour, boolean isoFormat, boolean padHours) {

		return formatHour(isoHour, isoFormat, padHours, true);
	}

	public static String formatHour(
		int isoHour, boolean isoFormat, boolean padHours, boolean showSuffix) {

		int hour = isoHour;
		String formatted = StringPool.BLANK;
		String suffix = StringPool.BLANK;

		if (isoFormat) {
			if (showSuffix) {
				suffix = CalendarUtil.HOUR_SUFFIX;
			}
		}
		else {
			if (isoHour == 0) {
				hour = 12;
			}
			else if (isoHour > 12) {
				hour -= 12;
			}

			if (showSuffix) {
				if (isoHour < 12) {
					suffix = CalendarUtil.AM_SUFFIX;
				}
				else {
					suffix = CalendarUtil.PM_SUFFIX;
				}
			}
		}

		if (padHours) {
			formatted = String.format("%02d", hour);
		}
		else {
			formatted = String.valueOf(hour);
		}

		return formatted.concat(suffix);
	}

	public static OrderByComparator getOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator orderByComparator = new CalendarNameComparator(
			orderByAsc);

		return orderByComparator;
	}

	public static String[] splitKeywords(String keywords) {
		List<String> keywordsList = new UniqueList<String>();

		StringBundler sb = new StringBundler();

		for (char c : keywords.toCharArray()) {
			if (Character.isWhitespace(c)) {
				if (sb.length() > 0) {
					keywordsList.add(sb.toString());

					sb = new StringBundler();
				}
			}
			else if (Character.isLetterOrDigit(c)) {
				sb.append(c);
			}
			else {
				return new String[] {keywords};
			}
		}

		if (sb.length() > 0) {
			keywordsList.add(sb.toString());
		}

		return StringUtil.split(StringUtil.merge(keywordsList));
	}

	public static JSONObject toJSON(Calendar calendar, Locale locale) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("name", calendar.getName(locale));
		jsonObject.put("color", ColorUtil.toHexString(calendar.getColor()));

		return jsonObject;
	}

	public static JSONArray toJSON(List<Calendar> calendars, Locale locale) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		if (calendars != null) {
			for (Calendar calendar : calendars) {
				jsonArray.put(toJSON(calendar, locale));
			}
		}

		return jsonArray;
	}

}