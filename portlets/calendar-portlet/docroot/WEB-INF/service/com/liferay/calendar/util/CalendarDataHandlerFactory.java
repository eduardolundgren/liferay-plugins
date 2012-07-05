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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class CalendarDataHandlerFactory {

	public static CalendarDataHandler getCalendarDataHandler(
			CalendarDataFormat calendarDataFormat)
		throws PortalException {

		CalendarDataHandler calendarDataHandler = _dataHandlers.get(
			calendarDataFormat);

		if (calendarDataHandler == null) {
			throw new PortalException(
				"Invalid format type " + calendarDataFormat);
		}

		return calendarDataHandler;
	}

	public void setCalendarDataHandlers(
		Map<String, CalendarDataHandler> calendarDataHandlers) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_dataHandlers = new HashMap<CalendarDataFormat, CalendarDataHandler>();

		for (Map.Entry<String, CalendarDataHandler> entry :
				calendarDataHandlers.entrySet()) {

			CalendarDataFormat calendarDataFormat = CalendarDataFormat.parse(
				entry.getKey());

			_dataHandlers.put(calendarDataFormat, entry.getValue());
		}
	}

	private static Map<CalendarDataFormat, CalendarDataHandler> _dataHandlers;

}