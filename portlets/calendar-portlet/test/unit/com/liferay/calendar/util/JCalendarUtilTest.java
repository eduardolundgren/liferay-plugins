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
import com.liferay.portal.kernel.util.TimeZoneUtil;
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
		Calendar dateToDisplay = JCalendarUtil.getJCalendar(
			2013, Calendar.JULY, 1, 5, 0, 0, 0, TimeZoneUtil.GMT);

		Calendar simulatedDate = JCalendarUtil.fromDisplayCalendar(
			dateToDisplay, LOS_ANGELES_TIME_ZONE);

		Assert.assertEquals(TimeZoneUtil.GMT, simulatedDate.getTimeZone());
		Assert.assertEquals(12, simulatedDate.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, simulatedDate.get(Calendar.MINUTE));
		Assert.assertEquals(2013, simulatedDate.get(Calendar.YEAR));
		Assert.assertEquals(Calendar.JULY, simulatedDate.get(Calendar.MONTH));
		Assert.assertEquals(1, simulatedDate.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testFromDisplayTimeosAngelesNoDST() {
		Calendar dateToDisplay = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 1, 4, 0, 0, 0, TimeZoneUtil.GMT);

		Calendar simulatedDate = JCalendarUtil.fromDisplayCalendar(
			dateToDisplay, LOS_ANGELES_TIME_ZONE);

		Assert.assertEquals(TimeZoneUtil.GMT, simulatedDate.getTimeZone());
		Assert.assertEquals(12, simulatedDate.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, simulatedDate.get(Calendar.MINUTE));
		Assert.assertEquals(2013, simulatedDate.get(Calendar.YEAR));
		Assert.assertEquals(
			Calendar.JANUARY, simulatedDate.get(Calendar.MONTH));
		Assert.assertEquals(1, simulatedDate.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testFromDisplayTimeSaoPauloDST() {
		Calendar dateToDisplay = JCalendarUtil.getJCalendar(
			2013, Calendar.JULY, 1, 9, 0, 0, 0, TimeZoneUtil.GMT);

		Calendar displayDate = JCalendarUtil.fromDisplayCalendar(
			dateToDisplay, SAO_PAULO_TIME_ZONE);

		Assert.assertEquals(TimeZoneUtil.GMT, displayDate.getTimeZone());
		Assert.assertEquals(12, displayDate.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, displayDate.get(Calendar.MINUTE));
		Assert.assertEquals(2013, displayDate.get(Calendar.YEAR));
		Assert.assertEquals(Calendar.JULY, displayDate.get(Calendar.MONTH));
		Assert.assertEquals(1, displayDate.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testFromDisplayTimeSaoPauloNoDST() {
		Calendar dateToDisplay = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 1, 10, 0, 0, 0, TimeZoneUtil.GMT);

		Calendar displayDate = JCalendarUtil.fromDisplayCalendar(
			dateToDisplay, SAO_PAULO_TIME_ZONE);

		Assert.assertEquals(TimeZoneUtil.GMT, displayDate.getTimeZone());
		Assert.assertEquals(12, displayDate.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, displayDate.get(Calendar.MINUTE));
		Assert.assertEquals(2013, displayDate.get(Calendar.YEAR));
		Assert.assertEquals(Calendar.JANUARY, displayDate.get(Calendar.MONTH));
		Assert.assertEquals(1, displayDate.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testSimulatingTimeLosAngelesDST() {
		Calendar dateToDisplay = JCalendarUtil.getJCalendar(
			2013, Calendar.JULY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);

		Calendar displayDate = JCalendarUtil.toDisplayCalendar(
			dateToDisplay, LOS_ANGELES_TIME_ZONE);

		Assert.assertEquals(TimeZoneUtil.GMT, displayDate.getTimeZone());
		Assert.assertEquals(5, displayDate.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, displayDate.get(Calendar.MINUTE));
		Assert.assertEquals(2013, displayDate.get(Calendar.YEAR));
		Assert.assertEquals(Calendar.JULY, displayDate.get(Calendar.MONTH));
		Assert.assertEquals(1, displayDate.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testSimulatingTimeLosAngelesNoDST() {
		Calendar dateToDisplay = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);

		Calendar displayDate = JCalendarUtil.toDisplayCalendar(
			dateToDisplay, LOS_ANGELES_TIME_ZONE);

		Assert.assertEquals(TimeZoneUtil.GMT, displayDate.getTimeZone());
		Assert.assertEquals(4, displayDate.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, displayDate.get(Calendar.MINUTE));
		Assert.assertEquals(2013, displayDate.get(Calendar.YEAR));
		Assert.assertEquals(Calendar.JANUARY, displayDate.get(Calendar.MONTH));
		Assert.assertEquals(1, displayDate.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testSimulatingTimeSaoPauloDST() {
		Calendar dateToDisplay = JCalendarUtil.getJCalendar(
			2013, Calendar.JULY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);

		Calendar displayDate = JCalendarUtil.toDisplayCalendar(
			dateToDisplay, SAO_PAULO_TIME_ZONE);

		Assert.assertEquals(TimeZoneUtil.GMT, displayDate.getTimeZone());
		Assert.assertEquals(9, displayDate.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, displayDate.get(Calendar.MINUTE));
		Assert.assertEquals(2013, displayDate.get(Calendar.YEAR));
		Assert.assertEquals(Calendar.JULY, displayDate.get(Calendar.MONTH));
		Assert.assertEquals(1, displayDate.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testSimulatingTimeSaoPauloNoDST() {
		Calendar dateToDisplay = JCalendarUtil.getJCalendar(
			2013, Calendar.JANUARY, 1, 12, 0, 0, 0, TimeZoneUtil.GMT);

		Calendar displayDate = JCalendarUtil.toDisplayCalendar(
			dateToDisplay, SAO_PAULO_TIME_ZONE);

		Assert.assertEquals(TimeZoneUtil.GMT, displayDate.getTimeZone());
		Assert.assertEquals(10, displayDate.get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, displayDate.get(Calendar.MINUTE));
		Assert.assertEquals(2013, displayDate.get(Calendar.YEAR));
		Assert.assertEquals(Calendar.JANUARY, displayDate.get(Calendar.MONTH));
		Assert.assertEquals(1, displayDate.get(Calendar.DAY_OF_MONTH));
	}

	private static final TimeZone LOS_ANGELES_TIME_ZONE = TimeZone.getTimeZone(
		"America/Los_Angeles");

	private static final TimeZone SAO_PAULO_TIME_ZONE = TimeZone.getTimeZone(
			"America/Sao_Paulo");

}