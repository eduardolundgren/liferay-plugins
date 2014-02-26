/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.TimeZoneUtil;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author Eduardo Lundgren
 * @author Peter Shin
 * @author Fabio Pezzutto
 */
public class JCalendarUtil {

	public static final long DAY = Time.HOUR * 24;

	public static final long HOUR = Time.MINUTE * 60;

	public static final long MINUTE = Time.SECOND * 60;

	public static final long MONTH = Time.DAY * 30;

	public static final long SECOND = 1000;

	public static Calendar fromDisplayCalendar(
		Calendar calendar, TimeZone simulatedTimeZone) {

		Calendar displayCalendar = getJCalendar(
			calendar.getTimeInMillis(), TimeZoneUtil.GMT);

		Calendar realJCalendar = getJCalendar(
			displayCalendar.get(Calendar.YEAR),
			displayCalendar.get(Calendar.MONTH),
			displayCalendar.get(Calendar.DAY_OF_MONTH),
			displayCalendar.get(Calendar.HOUR_OF_DAY),
			displayCalendar.get(Calendar.MINUTE),
			displayCalendar.get(Calendar.SECOND),
			displayCalendar.get(Calendar.MILLISECOND), simulatedTimeZone);

		return getJCalendar(realJCalendar.getTimeInMillis(), TimeZoneUtil.GMT);
	}

	public static Calendar fromDisplayCalendar(
		long time, TimeZone simulatedTimeZone) {

		return fromDisplayCalendar(getJCalendar(time), simulatedTimeZone);
	}

	public static long getDaysBetween(
		Calendar startTimeJCalendar, Calendar endTimeJCalendar) {

		startTimeJCalendar = toMidnightJCalendar(startTimeJCalendar);
		endTimeJCalendar = toMidnightJCalendar(endTimeJCalendar);

		long startTime = startTimeJCalendar.getTimeInMillis();
		long endTime = endTimeJCalendar.getTimeInMillis();

		return (endTime - startTime) / DAY;
	}

	public static Calendar getJCalendar(
		int year, int month, int day, int hour, int minutes, int seconds,
		int milliseconds, TimeZone timeZone) {

		Calendar jCalendar = CalendarFactoryUtil.getCalendar(timeZone);

		jCalendar.set(Calendar.YEAR, year);
		jCalendar.set(Calendar.MONTH, month);
		jCalendar.set(Calendar.DATE, day);
		jCalendar.set(Calendar.HOUR_OF_DAY, hour);
		jCalendar.set(Calendar.MINUTE, minutes);
		jCalendar.set(Calendar.SECOND, seconds);
		jCalendar.set(Calendar.MILLISECOND, milliseconds);

		return jCalendar;
	}

	public static Calendar getJCalendar(long time) {
		return getJCalendar(time, TimeZoneUtil.GMT);
	}

	public static Calendar getJCalendar(long time, TimeZone timeZone) {
		Calendar jCalendar = CalendarFactoryUtil.getCalendar(timeZone);

		jCalendar.setTimeInMillis(time);

		return jCalendar;
	}

	public static Calendar toDisplayCalendar(
		Calendar calendar, TimeZone simulatedTimeZone) {

		Calendar localizedJCalendar = getJCalendar(
			calendar.getTimeInMillis(), simulatedTimeZone);

		return getJCalendar(
			localizedJCalendar.get(Calendar.YEAR),
			localizedJCalendar.get(Calendar.MONTH),
			localizedJCalendar.get(Calendar.DAY_OF_MONTH),
			localizedJCalendar.get(Calendar.HOUR_OF_DAY),
			localizedJCalendar.get(Calendar.MINUTE),
			localizedJCalendar.get(Calendar.SECOND),
			localizedJCalendar.get(Calendar.MILLISECOND), TimeZoneUtil.GMT);
	}

	public static Calendar toDisplayCalendar(long time, TimeZone timeZone) {
		return toDisplayCalendar(getJCalendar(time), timeZone);
	}

	public static Calendar toLastHourJCalendar(Calendar jCalendar) {
		Calendar lastHourJCalendar = (Calendar)jCalendar.clone();

		lastHourJCalendar.set(Calendar.HOUR_OF_DAY, 23);
		lastHourJCalendar.set(Calendar.MINUTE, 59);
		lastHourJCalendar.set(Calendar.SECOND, 59);
		lastHourJCalendar.set(Calendar.MILLISECOND, 999);

		return lastHourJCalendar;
	}

	public static Calendar toMidnightJCalendar(Calendar jCalendar) {
		Calendar midnightJCalendar = (Calendar)jCalendar.clone();

		midnightJCalendar.set(Calendar.HOUR_OF_DAY, 0);
		midnightJCalendar.set(Calendar.MINUTE, 0);
		midnightJCalendar.set(Calendar.SECOND, 0);
		midnightJCalendar.set(Calendar.MILLISECOND, 0);

		return midnightJCalendar;
	}

}