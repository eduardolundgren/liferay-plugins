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

package com.liferay.calendar.service.impl;

import com.liferay.calendar.DuplicateCalendarResourceException;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.base.CalendarResourceLocalServiceBaseImpl;
import com.liferay.calendar.util.CalendarResourceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarResourceLocalServiceImpl
	extends CalendarResourceLocalServiceBaseImpl {

	public CalendarResource addCalendarResource(
			long userId, long groupId, String className, long classPK,
			String classUuid, String code, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type, boolean active,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		long classNameId = PortalUtil.getClassNameId(className);
		Date now = new Date();

		validate(classNameId, classPK);

		long globalUserId = 0;

		if (CalendarResourceUtil.isGlobalResource(className, classPK)) {
			globalUserId = CalendarResourceUtil.getGlobalResourceUserId(
				className, classPK);
		}

		if (globalUserId > 0) {
			userId = globalUserId;
		}

		long calendarResourceId = counterLocalService.increment();

		CalendarResource calendarResource =
			calendarResourcePersistence.create(calendarResourceId);

		calendarResource.setUuid(serviceContext.getUuid());
		calendarResource.setResourceBlockId(0);
		calendarResource.setCompanyId(user.getCompanyId());
		calendarResource.setUserId(user.getUserId());
		calendarResource.setUserName(user.getFullName());
		calendarResource.setCreateDate(serviceContext.getCreateDate(now));
		calendarResource.setModifiedDate(serviceContext.getModifiedDate(now));
		calendarResource.setGroupId(groupId);
		calendarResource.setClassNameId(classNameId);
		calendarResource.setClassPK(classPK);
		calendarResource.setClassUuid(classUuid);
		calendarResource.setCode(code);
		calendarResource.setNameMap(nameMap);
		calendarResource.setDescriptionMap(descriptionMap);
		calendarResource.setType(type);
		calendarResource.setActive(active);

		calendarResourcePersistence.update(calendarResource, false);

		// Resources

		if (serviceContext.isAddGroupPermissions() ||
			serviceContext.isAddGuestPermissions()) {

			addCalendarResourceResources(
				calendarResource, serviceContext.isAddGroupPermissions(),
				serviceContext.isAddGuestPermissions());
		}
		else {
			addCalendarResourceResources(
				calendarResource, serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		return calendarResource;
	}

	public void addCalendarResourceResources(
			CalendarResource calendarResource, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			calendarResource.getCompanyId(), calendarResource.getGroupId(),
			calendarResource.getUserId(), CalendarResource.class.getName(),
			calendarResource.getCalendarResourceId(), false,
			addGroupPermissions, addGuestPermissions);
	}

	public void addCalendarResourceResources(
		CalendarResource calendarResource, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			calendarResource.getCompanyId(), calendarResource.getGroupId(),
			calendarResource.getUserId(), CalendarResource.class.getName(),
			calendarResource.getCalendarResourceId(), groupPermissions,
			guestPermissions);
	}

	public void addCalendarResourceResources(
			long calendarResourceId, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		CalendarResource calendarResource =
			calendarResourcePersistence.findByPrimaryKey(calendarResourceId);

		addCalendarResourceResources(
			calendarResource, addGroupPermissions, addGuestPermissions);
	}

	public void addCalendarResourceResources(
			long calendarResourceId, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		CalendarResource calendarResource =
			calendarResourcePersistence.findByPrimaryKey(calendarResourceId);

		addCalendarResourceResources(
			calendarResource, groupPermissions, guestPermissions);
	}

	public void deleteCalendarResource(CalendarResource calendarResource)
		throws PortalException, SystemException {

		// CalendarResource

		calendarResourcePersistence.remove(calendarResource);

		// TODO: delete all related calendars.

		// Resources

		resourceLocalService.deleteResource(
			calendarResource.getCompanyId(), CalendarResource.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			calendarResource.getCalendarResourceId());
	}

	public void deleteCalendarResource(long calendarResourceId)
		throws PortalException, SystemException {

		CalendarResource calendarResource =
			calendarResourcePersistence.findByPrimaryKey(calendarResourceId);

		deleteCalendarResource(calendarResource);
	}

	public CalendarResource updateCalendarResource(
			long calendarResourceId, String code, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type, boolean active,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		CalendarResource calendarResource =
			calendarResourcePersistence.findByPrimaryKey(calendarResourceId);

		calendarResource.setModifiedDate(serviceContext.getModifiedDate(null));
		calendarResource.setCode(code);
		calendarResource.setNameMap(nameMap);
		calendarResource.setDescriptionMap(descriptionMap);
		calendarResource.setType(type);
		calendarResource.setActive(active);

		calendarResourcePersistence.update(calendarResource, false);

		// Resources

		if ((serviceContext.getGroupPermissions() != null) ||
			(serviceContext.getGuestPermissions() != null)) {

			updateCalendarResourceResources(
				calendarResource, serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		return calendarResource;
	}

	public void updateCalendarResourceResources(
			CalendarResource calendarResource, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.updateResources(
			calendarResource.getCompanyId(), calendarResource.getGroupId(),
			CalendarResource.class.getName(),
			calendarResource.getCalendarResourceId(), groupPermissions,
			guestPermissions);
	}

	protected void validate(long classNameId, long classPK)
		throws PortalException, SystemException {

		CalendarResource calendarResource =
			calendarResourcePersistence.fetchByC_C(classNameId, classPK);

		if (Validator.isNotNull(calendarResource)) {
			throw new DuplicateCalendarResourceException();
		}
	}

}