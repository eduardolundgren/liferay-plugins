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

package com.liferay.calendar.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * @author Eduardo Lundgren
 */
public class CalendarBookingServiceClp implements CalendarBookingService {
	public CalendarBookingServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;

		_addCalendarBookingMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
				"addCalendarBooking", long.class, long.class, long.class,
				java.util.Map.class, java.util.Map.class,
				java.lang.String.class, java.util.Date.class,
				java.util.Date.class, boolean.class, java.lang.String.class,
				int.class, int.class,
				com.liferay.portal.service.ServiceContext.class);

		_deleteCalendarBookingMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteCalendarBooking", long.class);

		_fetchByC_PMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
				"fetchByC_P", long.class, long.class);

		_findByP_SMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
				"findByP_S", long.class, int.class);

		_getByParentCalendarBookingIdMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
				"getByParentCalendarBookingId", long.class);

		_getCalendarBookingMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarBooking", long.class);

		_getCalendarBookingsMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarBookings", long.class, java.util.Date.class,
				java.util.Date.class);

		_invokeTransitionMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
				"invokeTransition", long.class, long.class,
				java.lang.String.class,
				com.liferay.portal.service.ServiceContext.class);

		_searchMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
				"search", long.class, long[].class, long[].class, long[].class,
				long.class, java.lang.String.class, java.util.Date.class,
				java.util.Date.class, int[].class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_searchMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
				"search", long.class, long[].class, long[].class, long[].class,
				long.class, java.lang.String.class, java.lang.String.class,
				java.lang.String.class, java.util.Date.class,
				java.util.Date.class, int[].class, boolean.class, int.class,
				int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_searchCountMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
				"searchCount", long.class, long[].class, long[].class,
				long[].class, long.class, java.lang.String.class,
				java.util.Date.class, java.util.Date.class, int[].class);

		_searchCountMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
				"searchCount", long.class, long[].class, long[].class,
				long[].class, long.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class,
				java.util.Date.class, java.util.Date.class, int[].class,
				boolean.class);

		_updateCalendarBookingMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateCalendarBooking", long.class, long.class, long.class,
				java.util.Map.class, java.util.Map.class,
				java.lang.String.class, int.class, java.util.Date.class,
				java.util.Date.class, boolean.class, java.lang.String.class,
				int.class, int.class,
				com.liferay.portal.service.ServiceContext.class);
	}

	public com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		long userId, long calendarId, long parentCalendarBookingId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, java.util.Date startDate,
		java.util.Date endDate, boolean allDay, java.lang.String recurrence,
		int firstReminder, int secondReminder,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addCalendarBookingMethodKey0,
				userId, calendarId, parentCalendarBookingId,
				ClpSerializer.translateInput(titleMap),
				ClpSerializer.translateInput(descriptionMap),
				ClpSerializer.translateInput(location),
				ClpSerializer.translateInput(startDate),
				ClpSerializer.translateInput(endDate), allDay,
				ClpSerializer.translateInput(recurrence), firstReminder,
				secondReminder, ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.calendar.model.CalendarBooking)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.calendar.model.CalendarBooking deleteCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_deleteCalendarBookingMethodKey1,
				calendarBookingId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.calendar.model.CalendarBooking)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.calendar.model.CalendarBooking fetchByC_P(
		long calendarId, long parentCalendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_fetchByC_PMethodKey2,
				calendarId, parentCalendarBookingId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.calendar.model.CalendarBooking)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> findByP_S(
		long parentCalendarBookingId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_findByP_SMethodKey3,
				parentCalendarBookingId, status);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.calendar.model.CalendarBooking>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> getByParentCalendarBookingId(
		long parentCalendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getByParentCalendarBookingIdMethodKey4,
				parentCalendarBookingId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.calendar.model.CalendarBooking>)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarBookingMethodKey5,
				calendarBookingId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.calendar.model.CalendarBooking)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId, java.util.Date startDate, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarBookingsMethodKey6,
				calendarId, ClpSerializer.translateInput(startDate),
				ClpSerializer.translateInput(endDate));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.calendar.model.CalendarBooking>)ClpSerializer.translateOutput(returnObj);
	}

	public void invokeTransition(long userId, long calendarBookingId,
		java.lang.String transitionName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_invokeTransitionMethodKey7,
				userId, calendarBookingId,
				ClpSerializer.translateInput(transitionName),
				ClpSerializer.translateInput(serviceContext));

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String keywords, java.util.Date startDate,
		java.util.Date endDate, int[] status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchMethodKey8,
				companyId, ClpSerializer.translateInput(groupIds),
				ClpSerializer.translateInput(calendarIds),
				ClpSerializer.translateInput(calendarResourceIds),
				parentCalendarBookingId,
				ClpSerializer.translateInput(keywords),
				ClpSerializer.translateInput(startDate),
				ClpSerializer.translateInput(endDate),
				ClpSerializer.translateInput(status), start, end,
				ClpSerializer.translateInput(orderByComparator));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.calendar.model.CalendarBooking>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String title, java.lang.String description,
		java.lang.String location, java.util.Date startDate,
		java.util.Date endDate, int[] status, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchMethodKey9,
				companyId, ClpSerializer.translateInput(groupIds),
				ClpSerializer.translateInput(calendarIds),
				ClpSerializer.translateInput(calendarResourceIds),
				parentCalendarBookingId, ClpSerializer.translateInput(title),
				ClpSerializer.translateInput(description),
				ClpSerializer.translateInput(location),
				ClpSerializer.translateInput(startDate),
				ClpSerializer.translateInput(endDate),
				ClpSerializer.translateInput(status), andOperator, start, end,
				ClpSerializer.translateInput(orderByComparator));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.calendar.model.CalendarBooking>)ClpSerializer.translateOutput(returnObj);
	}

	public int searchCount(long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String keywords, java.util.Date startDate,
		java.util.Date endDate, int[] status)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchCountMethodKey10,
				companyId, ClpSerializer.translateInput(groupIds),
				ClpSerializer.translateInput(calendarIds),
				ClpSerializer.translateInput(calendarResourceIds),
				parentCalendarBookingId,
				ClpSerializer.translateInput(keywords),
				ClpSerializer.translateInput(startDate),
				ClpSerializer.translateInput(endDate),
				ClpSerializer.translateInput(status));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public int searchCount(long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String title, java.lang.String description,
		java.lang.String location, java.util.Date startDate,
		java.util.Date endDate, int[] status, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchCountMethodKey11,
				companyId, ClpSerializer.translateInput(groupIds),
				ClpSerializer.translateInput(calendarIds),
				ClpSerializer.translateInput(calendarResourceIds),
				parentCalendarBookingId, ClpSerializer.translateInput(title),
				ClpSerializer.translateInput(description),
				ClpSerializer.translateInput(location),
				ClpSerializer.translateInput(startDate),
				ClpSerializer.translateInput(endDate),
				ClpSerializer.translateInput(status), andOperator);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		long userId, long calendarBookingId, long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, int status, java.util.Date startDate,
		java.util.Date endDate, boolean allDay, java.lang.String recurrence,
		int firstReminder, int secondReminder,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateCalendarBookingMethodKey12,
				userId, calendarBookingId, calendarId,
				ClpSerializer.translateInput(titleMap),
				ClpSerializer.translateInput(descriptionMap),
				ClpSerializer.translateInput(location), status,
				ClpSerializer.translateInput(startDate),
				ClpSerializer.translateInput(endDate), allDay,
				ClpSerializer.translateInput(recurrence), firstReminder,
				secondReminder, ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.calendar.model.CalendarBooking)ClpSerializer.translateOutput(returnObj);
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private ClassLoaderProxy _classLoaderProxy;
	private MethodKey _addCalendarBookingMethodKey0;
	private MethodKey _deleteCalendarBookingMethodKey1;
	private MethodKey _fetchByC_PMethodKey2;
	private MethodKey _findByP_SMethodKey3;
	private MethodKey _getByParentCalendarBookingIdMethodKey4;
	private MethodKey _getCalendarBookingMethodKey5;
	private MethodKey _getCalendarBookingsMethodKey6;
	private MethodKey _invokeTransitionMethodKey7;
	private MethodKey _searchMethodKey8;
	private MethodKey _searchMethodKey9;
	private MethodKey _searchCountMethodKey10;
	private MethodKey _searchCountMethodKey11;
	private MethodKey _updateCalendarBookingMethodKey12;
}