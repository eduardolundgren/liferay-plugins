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

package com.liferay.calendar.portlet;

import com.liferay.calendar.DuplicateCalendarResourceException;
import com.liferay.calendar.NoSuchResourceException;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarBookingServiceUtil;
import com.liferay.calendar.service.CalendarResourceServiceUtil;
import com.liferay.calendar.service.CalendarServiceUtil;
import com.liferay.calendar.util.CalendarResourceUtil;
import com.liferay.calendar.util.WebKeys;
import com.liferay.calendar.util.comparator.CalendarResourceNameComparator;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.GroupServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.comparator.UserFirstNameComparator;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 * @author Andrea Di Giorgi
 */
public class CalendarPortlet extends MVCPortlet {

	public void deleteCalendar(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long calendarId = ParamUtil.getLong(actionRequest, "calendarId");

		CalendarServiceUtil.deleteCalendar(calendarId);
	}

	public void deleteCalendarResource(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long calendarResourceId = ParamUtil.getLong(
			actionRequest, "calendarResourceId");

		CalendarResourceServiceUtil.deleteCalendarResource(calendarResourceId);
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException, IOException {

		try {
			Calendar calendar = null;

			long calendarId = ParamUtil.getLong(renderRequest, "calendarId");

			if (calendarId > 0) {
				calendar = CalendarServiceUtil.getCalendar(calendarId);
			}

			renderRequest.setAttribute(WebKeys.CALENDAR, calendar);

			CalendarBooking calendarBooking = null;

			long calendarBookingId = ParamUtil.getLong(
				renderRequest, "calendarBookingId");

			if (calendarBookingId > 0) {
				calendarBooking = CalendarBookingServiceUtil.getCalendarBooking(
					calendarBookingId);
			}

			renderRequest.setAttribute(
				WebKeys.CALENDAR_BOOKING, calendarBooking);

			CalendarResource calendarResource = null;

			long calendarResourceId = ParamUtil.getLong(
				renderRequest, "calendarResourceId");

			if (calendarResourceId > 0) {
				calendarResource =
					CalendarResourceServiceUtil.getCalendarResource(
						calendarResourceId);
			}

			renderRequest.setAttribute(
				WebKeys.CALENDAR_RESOURCE, calendarResource);
		}
		catch (Exception e) {
			if (e instanceof NoSuchResourceException) {
				SessionErrors.add(renderRequest, e.getClass().getName());
			}
			else {
				throw new PortletException(e);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	public void serveCalendarResources(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Locale locale = themeDisplay.getLocale();

		long companyId = PortalUtil.getCompanyId(resourceRequest);
		String keywords = ParamUtil.getString(resourceRequest, "keywords");

		long calendarResourceClassNameId = PortalUtil.getClassNameId(
			CalendarResource.class);

		List<CalendarResource> calendarResources =
			CalendarResourceServiceUtil.search(companyId, new long[] {},
			new long[] {calendarResourceClassNameId}, keywords, true, true, 0,
			SearchContainer.DEFAULT_DELTA,
			new CalendarResourceNameComparator());

		JSONArray resourcesJSONArray = JSONFactoryUtil.createJSONArray();

		for (CalendarResource calendarResource : calendarResources) {
			JSONObject resourceJSONObject = JSONFactoryUtil.createJSONObject();

			resourceJSONObject.put("classNameId", calendarResourceClassNameId);
			resourceJSONObject.put("classPK", calendarResource.getClassPK());
			resourceJSONObject.put("name", calendarResource.getName(locale));

			resourcesJSONArray.put(resourceJSONObject);
		}

		List<User> users = UserLocalServiceUtil.search(
			companyId, keywords, 0, null, 0, SearchContainer.DEFAULT_DELTA,
			new UserFirstNameComparator());

		long userClassNameId = PortalUtil.getClassNameId(User.class);

		for (User user : users) {
			JSONObject resourceJSONObject = JSONFactoryUtil.createJSONObject();

			resourceJSONObject.put("classNameId", userClassNameId);
			resourceJSONObject.put("classPK", user.getUserId());
			resourceJSONObject.put("name", user.getFullName());

			resourcesJSONArray.put(resourceJSONObject);
		}

		List<Group> groups = GroupServiceUtil.search(
			companyId, keywords, keywords, new String[] {}, 0,
			SearchContainer.DEFAULT_DELTA);

		long groupClassNameId = PortalUtil.getClassNameId(User.class);

		for (Group group : groups) {
			JSONObject resourceJSONObject = JSONFactoryUtil.createJSONObject();

			resourceJSONObject.put("classNameId", groupClassNameId);
			resourceJSONObject.put("classPK", group.getGroupId());
			resourceJSONObject.put("name", group.getName());

			resourcesJSONArray.put(resourceJSONObject);
		}

		writeJSON(resourceRequest, resourceResponse, resourcesJSONArray);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("calendarResources")) {
				serveCalendarResources(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void updateCalendar(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long calendarId = ParamUtil.getLong(actionRequest, "calendarId");

		long calendarResourceId = ParamUtil.getLong(
			actionRequest, "calendarResourceId");
		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");
		int color = ParamUtil.getInteger(actionRequest, "color");
		boolean defaultCalendar = ParamUtil.getBoolean(
			actionRequest, "defaultCalendar", false);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CalendarResource.class.getName(), actionRequest);

		if (calendarId <= 0) {
			CalendarServiceUtil.addCalendar(
				serviceContext.getScopeGroupId(), calendarResourceId, nameMap,
				descriptionMap, color, defaultCalendar, serviceContext);
		}
		else {
			CalendarServiceUtil.updateCalendar(
				calendarId, nameMap, descriptionMap, color, defaultCalendar,
				serviceContext);
		}
	}

	public void updateCalendarBooking(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long userId = PortalUtil.getUserId(actionRequest);

		long calendarId = ParamUtil.getLong(actionRequest, "calendarId");
		long calendarBookingId = ParamUtil.getLong(
			actionRequest, "calendarBookingId");
		long parentCalendarBookingId = ParamUtil.getLong(
			actionRequest, "parentCalendarBookingId");
		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "title");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");
		String location = ParamUtil.getString(actionRequest, "location");
		int firstReminder = ParamUtil.getInteger(
			actionRequest, "firstReminderValue");
		int firstReminderUnits = ParamUtil.getInteger(
			actionRequest, "firstReminderUnits");
		int secondReminder = ParamUtil.getInteger(
			actionRequest, "secondReminderValue");
		int secondReminderUnits = ParamUtil.getInteger(
			actionRequest, "secondReminderUnits");
		boolean allDay = ParamUtil.getBoolean(actionRequest, "allDay");
		String recurrence = ParamUtil.getString(actionRequest, "recurrence");
		int status = ParamUtil.getInteger(actionRequest, "status");

		int startDateMonth = ParamUtil.getInteger(
			actionRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(actionRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			actionRequest, "startDateYear");
		int startDateHour = ParamUtil.getInteger(
			actionRequest, "startDateHour");
		int startDateMinute = ParamUtil.getInteger(
			actionRequest, "startDateMinute");
		int startDateAmPm = ParamUtil.getInteger(
			actionRequest, "startDateAmPm");

		if (startDateAmPm == java.util.Calendar.PM) {
			startDateHour += 12;
		}

		java.util.Calendar startDate = CalendarFactoryUtil.getCalendar(
			startDateYear, startDateMonth, startDateDay, startDateHour,
			startDateMinute);

		int endDateMonth = ParamUtil.getInteger(actionRequest, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(actionRequest, "endDateDay");
		int endDateYear = ParamUtil.getInteger(actionRequest, "endDateYear");
		int endDateHour = ParamUtil.getInteger(actionRequest, "endDateHour");
		int endDateMinute = ParamUtil.getInteger(
			actionRequest, "endDateMinute");
		int endDateAmPm = ParamUtil.getInteger(actionRequest, "endDateAmPm");

		if (endDateAmPm == java.util.Calendar.PM) {
			endDateHour += 12;
		}

		java.util.Calendar endDate = CalendarFactoryUtil.getCalendar(
			endDateYear, endDateMonth, endDateDay, endDateHour, endDateMinute);

		firstReminder =
			(int)((firstReminder * firstReminderUnits) / Time.MINUTE);
		secondReminder =
			(int)((secondReminder * secondReminderUnits) / Time.MINUTE);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CalendarResource.class.getName(), actionRequest);

		String resourcesJSON = ParamUtil.getString(
			actionRequest, "resourcesJSON");

		JSONObject resourcesJSONObject = JSONFactoryUtil.createJSONObject(
			resourcesJSON);

		CalendarBooking calendarBooking = null;

		if (calendarBookingId <= 0) {
			calendarBooking =
				CalendarBookingServiceUtil.addCalendarBooking(
					userId, calendarId, parentCalendarBookingId, titleMap,
					descriptionMap, location, startDate.getTime(),
					endDate.getTime(), allDay, recurrence, firstReminder,
					secondReminder, serviceContext);
		}
		else {
			calendarBooking =
				CalendarBookingServiceUtil.updateCalendarBooking(
					userId, calendarBookingId, calendarId, titleMap,
					descriptionMap, location, status, startDate.getTime(),
					endDate.getTime(), allDay, recurrence, firstReminder,
					secondReminder, serviceContext);
		}

		updateCalendarBookingResources(
			actionRequest, actionResponse, calendarBooking, resourcesJSONObject,
			serviceContext);
	}

	public void updateCalendarResource(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long calendarResourceId = ParamUtil.getLong(
			actionRequest, "calendarResourceId");

		long defaultCalendarId = ParamUtil.getLong(
			actionRequest, "defaultCalendarId");
		String code = ParamUtil.getString(actionRequest, "code");
		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");
		String type = ParamUtil.getString(actionRequest, "type");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CalendarResource.class.getName(), actionRequest);

		if (calendarResourceId <= 0) {
			CalendarResourceServiceUtil.addCalendarResource(
				serviceContext.getScopeGroupId(), null, 0,
				PortalUUIDUtil.generate(), defaultCalendarId, code, nameMap,
				descriptionMap, type, active, serviceContext);
		}
		else {
			CalendarResourceServiceUtil.updateCalendarResource(
				calendarResourceId, defaultCalendarId, code, nameMap,
				descriptionMap, type, active, serviceContext);
		}
	}

	protected String getResourceKey(CalendarBooking calendarBooking)
		throws PortalException, SystemException {

		StringBundler sb = new StringBundler(3);

		CalendarResource calendarResource =
			calendarBooking.getCalendarResource();

		sb.append(calendarResource.getClassNameId());
		sb.append(StringPool.POUND);
		sb.append(calendarResource.getClassPK());

		return sb.toString();
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof DuplicateCalendarResourceException ||
			cause instanceof PrincipalException) {

			return true;
		}

		return false;
	}

	protected void updateCalendarBookingResources(
			ActionRequest actionRequest, ActionResponse actionResponse,
			CalendarBooking parentCalendarBooking,
			JSONObject resourcesJSONObject, ServiceContext serviceContext)
		throws PortalException, SystemException {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			actionRequest);

		List<CalendarBooking> calendarBookings =
			CalendarBookingServiceUtil.getByParentCalendarBookingId(
				parentCalendarBooking.getCalendarBookingId());

		for (CalendarBooking curCalendarBooking : calendarBookings) {
			String resourceKey = getResourceKey(curCalendarBooking);

			if (!resourcesJSONObject.has(resourceKey)) {
				CalendarBookingServiceUtil.deleteCalendarBooking(
					curCalendarBooking.getCalendarBookingId());
			}
		}

		Iterator<String> keys = resourcesJSONObject.keys();

		long userClassNameId = PortalUtil.getClassNameId(User.class);
		long groupClassNameId = PortalUtil.getClassNameId(Group.class);

		while (keys.hasNext()) {
			JSONObject resourceJSONObject = resourcesJSONObject.getJSONObject(
				keys.next());

			long classNameId = resourceJSONObject.getLong("classNameId");
			long classPK = resourceJSONObject.getLong("classPK");

			CalendarResource calendarResource = null;

			if (classNameId == userClassNameId) {
				calendarResource =
					CalendarResourceUtil.fetchOrCreateUserResource(
						request, classPK);
			}
			else if (classNameId == groupClassNameId) {
				calendarResource =
					CalendarResourceUtil.fetchOrCreateGroupResource(
						request, classPK);
			}
			else {
				calendarResource =
					CalendarResourceServiceUtil.fetchCalendarResource(
						classNameId, classPK);
			}

			if (calendarResource == null) {
				continue;
			}

			CalendarBooking calendarBooking =
				CalendarBookingServiceUtil.fetchByC_P(
					calendarResource.getDefaultCalendarId(),
					parentCalendarBooking.getCalendarBookingId());

			if (calendarBooking == null) {
				CalendarBookingServiceUtil.addCalendarBooking(
					parentCalendarBooking.getUserId(),
					calendarResource.getDefaultCalendarId(),
					parentCalendarBooking.getCalendarBookingId(),
					parentCalendarBooking.getTitleMap(),
					parentCalendarBooking.getDescriptionMap(),
					parentCalendarBooking.getLocation(),
					parentCalendarBooking.getStartDate(),
					parentCalendarBooking.getEndDate(),
					parentCalendarBooking.getAllDay(),
					parentCalendarBooking.getRecurrence(),
					parentCalendarBooking.getFirstReminder(),
					parentCalendarBooking.getSecondReminder(), serviceContext);
			}
			else {
				CalendarBookingServiceUtil.updateCalendarBooking(
					parentCalendarBooking.getUserId(),
					calendarBooking.getCalendarBookingId(),
					calendarResource.getDefaultCalendarId(),
					parentCalendarBooking.getTitleMap(),
					parentCalendarBooking.getDescriptionMap(),
					parentCalendarBooking.getLocation(),
					calendarBooking.getStatus(),
					parentCalendarBooking.getStartDate(),
					parentCalendarBooking.getEndDate(),
					parentCalendarBooking.getAllDay(),
					parentCalendarBooking.getRecurrence(),
					parentCalendarBooking.getFirstReminder(),
					parentCalendarBooking.getSecondReminder(), serviceContext);
			}

		}

	}

}