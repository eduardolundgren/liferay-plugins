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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.CalendarFactoryImpl;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Adam Brandizzi
 */
public class JCalendarUtilTest {

	@BeforeClass
	public static void setUpClass() {
		new CalendarFactoryUtil().setCalendarFactory(new CalendarFactoryImpl());
	}

	@Test
	public void testFromDisplayTimeosAngelesDST() {
		Calendar jCalendar = JCalendarUtil.getJCalendar(
			2013, Calendar.JULY, 1, 5, 0, 0, 0, _utcTimeZone);

		long time = JCalendarUtil.fromDisplayCalendar(
			jCalendar.getTimeInMillis(), _losAngelesTimeZone);

		Calendar displayJCalendar = JCalendarUtil.getJCalendar(time);

		Assert.assertEquals(_utcTimeZone, displayJCalendar.getTimeZone());
		Assert.assertEquals(12, displayJCalendar.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, displayJCalendar.get(Calendar.MINUTE));
		Assert.assertEquals(2013, displayJCalendar.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JULY, displayJCalendar.get(Calendar.MONTH));
		Assert.assertEquals(1, displayJCalendar.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testFromDisplayTimeosAngelesNoDST() {
		Calendar jCalendar = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 1, 4, 0, 0, 0, _utcTimeZone);

		long time = JCalendarUtil.fromDisplayCalendar(
			jCalendar.getTimeInMillis(), _losAngelesTimeZone);

		Calendar displayJCalendar = JCalendarUtil.getJCalendar(time);

		Assert.assertEquals(_utcTimeZone, displayJCalendar.getTimeZone());
		Assert.assertEquals(12, displayJCalendar.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, displayJCalendar.get(Calendar.MINUTE));
		Assert.assertEquals(2013, displayJCalendar.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JANUARY, displayJCalendar.get(Calendar.MONTH));
		Assert.assertEquals(1, displayJCalendar.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testFromDisplayTimeSaoPauloDST() {
		Calendar jCalendar = JCalendarUtil.getJCalendar(
			2013, Calendar.JULY, 1, 9, 0, 0, 0, _utcTimeZone);

		long time = JCalendarUtil.fromDisplayCalendar(
			jCalendar.getTimeInMillis(), _saoPauloTimeZone);

		Calendar displayJCalendar = JCalendarUtil.getJCalendar(time);

		Assert.assertEquals(_utcTimeZone, displayJCalendar.getTimeZone());
		Assert.assertEquals(12, displayJCalendar.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, displayJCalendar.get(Calendar.MINUTE));
		Assert.assertEquals(2013, displayJCalendar.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JULY, displayJCalendar.get(Calendar.MONTH));
		Assert.assertEquals(1, displayJCalendar.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testFromDisplayTimeSaoPauloNoDST() {
		Calendar jCalendar = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 1, 10, 0, 0, 0, _utcTimeZone);

		long time = JCalendarUtil.fromDisplayCalendar(
			jCalendar.getTimeInMillis(), _saoPauloTimeZone);

		Calendar displayJCalendar = JCalendarUtil.getJCalendar(time);

		Assert.assertEquals(_utcTimeZone, displayJCalendar.getTimeZone());
		Assert.assertEquals(12, displayJCalendar.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, displayJCalendar.get(Calendar.MINUTE));
		Assert.assertEquals(2013, displayJCalendar.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JANUARY, displayJCalendar.get(Calendar.MONTH));
		Assert.assertEquals(1, displayJCalendar.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testSimulatingTimeLosAngelesDST() {
		Calendar jCalendar = JCalendarUtil.getJCalendar(
			2013, Calendar.JULY, 1, 12, 0, 0, 0, _utcTimeZone);

		long time = JCalendarUtil.toDisplayCalendar(
			jCalendar.getTimeInMillis(), _losAngelesTimeZone);

		Calendar displayJCalendar = JCalendarUtil.getJCalendar(time);

		Assert.assertEquals(_utcTimeZone, displayJCalendar.getTimeZone());
		Assert.assertEquals(5, displayJCalendar.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, displayJCalendar.get(Calendar.MINUTE));
		Assert.assertEquals(2013, displayJCalendar.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JULY, displayJCalendar.get(Calendar.MONTH));
		Assert.assertEquals(1, displayJCalendar.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testSimulatingTimeLosAngelesNoDST() {
		Calendar jCalendar = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 1, 12, 0, 0, 0, _utcTimeZone);

		long time = JCalendarUtil.toDisplayCalendar(
			jCalendar.getTimeInMillis(), _losAngelesTimeZone);

		Calendar displayJCalendar = JCalendarUtil.getJCalendar(time);

		Assert.assertEquals(_utcTimeZone, displayJCalendar.getTimeZone());
		Assert.assertEquals(4, displayJCalendar.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, displayJCalendar.get(Calendar.MINUTE));
		Assert.assertEquals(2013, displayJCalendar.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JANUARY, displayJCalendar.get(Calendar.MONTH));
		Assert.assertEquals(1, displayJCalendar.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testSimulatingTimeSaoPauloDST() {
		Calendar jCalendar = JCalendarUtil.getJCalendar(
			2013, Calendar.JULY, 1, 12, 0, 0, 0, _utcTimeZone);

		long time = JCalendarUtil.toDisplayCalendar(
			jCalendar.getTimeInMillis(), _saoPauloTimeZone);

		Calendar displayJCalendar = JCalendarUtil.getJCalendar(time);

		Assert.assertEquals(_utcTimeZone, displayJCalendar.getTimeZone());
		Assert.assertEquals(9, displayJCalendar.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, displayJCalendar.get(Calendar.MINUTE));
		Assert.assertEquals(2013, displayJCalendar.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JULY, displayJCalendar.get(Calendar.MONTH));
		Assert.assertEquals(1, displayJCalendar.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testSimulatingTimeSaoPauloNoDST() {
		Calendar jCalendar = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 1, 12, 0, 0, 0, _utcTimeZone);

		long time = JCalendarUtil.toDisplayCalendar(
			jCalendar.getTimeInMillis(), _saoPauloTimeZone);

		Calendar displayJCalendar = JCalendarUtil.getJCalendar(time);

		Assert.assertEquals(_utcTimeZone, displayJCalendar.getTimeZone());
		Assert.assertEquals(10, displayJCalendar.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, displayJCalendar.get(Calendar.MINUTE));
		Assert.assertEquals(2013, displayJCalendar.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JANUARY, displayJCalendar.get(Calendar.MONTH));
		Assert.assertEquals(1, displayJCalendar.get(Calendar.DAY_OF_MONTH));
	}

	private static final TimeZone _losAngelesTimeZone = TimeZone.getTimeZone(
		"America/Los_Angeles");
	private static final TimeZone _saoPauloTimeZone = TimeZone.getTimeZone(
		"America/Sao_Paulo");
	private static final TimeZone _utcTimeZone = TimeZone.getTimeZone(
		StringPool.UTC);

}