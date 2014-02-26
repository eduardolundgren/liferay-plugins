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

package com.liferay.calendar.model.impl;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.notification.NotificationType;
import com.liferay.calendar.recurrence.Recurrence;
import com.liferay.calendar.recurrence.RecurrenceSerializer;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.calendar.util.JCalendarUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.List;
import java.util.TimeZone;

/**
 * @author Eduardo Lundgren
 */
public class CalendarBookingImpl extends CalendarBookingBaseImpl {

	public CalendarBookingImpl() {
	}

	@Override
	public Calendar getCalendar() throws PortalException, SystemException {
		return CalendarLocalServiceUtil.getCalendar(getCalendarId());
	}

	@Override
	public CalendarResource getCalendarResource()
		throws PortalException, SystemException {

		return CalendarResourceLocalServiceUtil.getCalendarResource(
			getCalendarResourceId());
	}

	@Override
	public List<CalendarBooking> getChildCalendarBookings()
		throws SystemException {

		return CalendarBookingLocalServiceUtil.getChildCalendarBookings(
			getCalendarBookingId());
	}

	@JSON
	@Override
	public long getDisplayEndTime() {
		return getDisplayTime(getEndTime());
	}

	@JSON
	@Override
	public long getDisplayStartTime() {
		return getDisplayTime(getStartTime());
	}

	@Override
	public long getDuration() {
		return getEndTime() - getStartTime();
	}

	@Override
	public NotificationType getFirstReminderNotificationType() {
		return NotificationType.parse(getFirstReminderType());
	}

	@Override
	public CalendarBooking getParentCalendarBooking()
		throws PortalException, SystemException {

		return CalendarBookingLocalServiceUtil.getCalendarBooking(
			getParentCalendarBookingId());
	}

	@Override
	public Recurrence getRecurrenceObj() {
		if ((_recurrenceObj == null) && isRecurring()) {
			_recurrenceObj = RecurrenceSerializer.deserialize(getRecurrence());
		}

		return _recurrenceObj;
	}

	@Override
	public NotificationType getSecondReminderNotificationType() {
		return NotificationType.parse(getSecondReminderType());
	}

	@Override
	public boolean isMasterBooking() {
		if (getParentCalendarBookingId() == getCalendarBookingId()) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isRecurring() {
		if (Validator.isNotNull(getRecurrence())) {
			return true;
		}

		return false;
	}

	protected long getDisplayTime(long time) {
		if (isAllDay()) {
			return time;
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext == null) {
			return time;
		}

		long userId = serviceContext.getUserId();

		if (userId <= 0) {
			return time;
		}

		try {
			User user = UserLocalServiceUtil.getUser(userId);
			TimeZone timeZone = TimeZone.getTimeZone(user.getTimeZoneId());

			java.util.Calendar simulatingJCalendar =
				JCalendarUtil.toDisplayCalendar(time, timeZone);

			time = simulatingJCalendar.getTimeInMillis();
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("User with id " + userId + " not found", e);
			}
		}

		return time;
	}

	private static Log _log = LogFactoryUtil.getLog(
		CalendarBooking.class.getName());

	private Recurrence _recurrenceObj;

}