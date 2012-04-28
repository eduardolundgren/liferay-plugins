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
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.calendar.service.CalendarResourceServiceUtil;
import com.liferay.calendar.service.CalendarServiceUtil;
import com.liferay.calendar.util.WebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

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

			CalendarResource calendarResource = null;

			long calendarResourceId = ParamUtil.getLong(
				renderRequest, "calendarResourceId");
			long classNameId = ParamUtil.getLong(renderRequest, "classNameId");
			long classPK = ParamUtil.getLong(renderRequest, "classPK");

			if (calendarResourceId > 0) {
				calendarResource =
					CalendarResourceLocalServiceUtil.getCalendarResource(
						calendarResourceId);
			}
			else if (classNameId > 0 && classPK > 0) {
				calendarResource =
					CalendarResourceLocalServiceUtil.fetchCalendarResource(
						classNameId, classPK);

				if ((calendarResource == null) &&
						(classNameId == PortalUtil.getClassNameId(
							User.class.getName()))) {

					ServiceContext serviceContext =
						ServiceContextFactory.getInstance(
							CalendarResource.class.getName(), renderRequest);

					calendarResource = _createUserCalendarResource(
						classPK, serviceContext);
				}
				else if ((calendarResource == null) &&
							(classNameId == PortalUtil.getClassNameId(
								Group.class.getName()))) {

					ServiceContext serviceContext =
						ServiceContextFactory.getInstance(
							CalendarResource.class.getName(), renderRequest);

					calendarResource = _createGroupCalendarResource(
						classPK, serviceContext);
				}
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

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof DuplicateCalendarResourceException ||
			cause instanceof PrincipalException) {

			return true;
		}

		return false;
	}

	private CalendarResource _createGroupCalendarResource(
			long classPK, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(classPK);

		Company company = CompanyLocalServiceUtil.getCompany(
			serviceContext.getCompanyId());

		User user = UserLocalServiceUtil.getDefaultUser(company.getCompanyId());

		Map<Locale, String> nameMap = new HashMap<Locale, String>();
		nameMap.put(LocaleUtil.getDefault(), group.getName());

		Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
		descriptionMap.put(LocaleUtil.getDefault(), group.getDescription());

		return CalendarResourceLocalServiceUtil.addCalendarResource(
			user.getUserId(), company.getGroup().getGroupId(),
			Group.class.getName(), classPK, user.getUuid(), 0,
			user.getScreenName(), nameMap, descriptionMap, null, true,
			serviceContext);
	}

	private CalendarResource _createUserCalendarResource(
			long classPK, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(classPK);

		Company company = CompanyLocalServiceUtil.getCompany(
			serviceContext.getCompanyId());

		Map<Locale, String> nameMap = new HashMap<Locale, String>();
		nameMap.put(LocaleUtil.getDefault(), user.getFullName());

		Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
		descriptionMap.put(LocaleUtil.getDefault(), user.getEmailAddress());

		return CalendarResourceLocalServiceUtil.addCalendarResource(
			user.getUserId(), company.getGroup().getGroupId(),
			User.class.getName(), classPK, user.getUuid(), 0,
			user.getScreenName(), nameMap, descriptionMap, null, true,
			serviceContext);
	}

}