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

package com.liferay.calendar.service.base;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.service.CalendarBookingLocalService;
import com.liferay.calendar.service.CalendarBookingService;
import com.liferay.calendar.service.CalendarLocalService;
import com.liferay.calendar.service.CalendarResourceLocalService;
import com.liferay.calendar.service.CalendarResourceService;
import com.liferay.calendar.service.CalendarService;
import com.liferay.calendar.service.persistence.CalendarBookingPersistence;
import com.liferay.calendar.service.persistence.CalendarPersistence;
import com.liferay.calendar.service.persistence.CalendarResourcePersistence;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import javax.sql.DataSource;

/**
 * The base implementation of the calendar remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.calendar.service.impl.CalendarServiceImpl}.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see com.liferay.calendar.service.impl.CalendarServiceImpl
 * @see com.liferay.calendar.service.CalendarServiceUtil
 * @generated
 */
public abstract class CalendarServiceBaseImpl extends PrincipalBean
	implements CalendarService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.calendar.service.CalendarServiceUtil} to access the calendar remote service.
	 */

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Returns the calendar booking local service.
	 *
	 * @return the calendar booking local service
	 */
	public CalendarBookingLocalService getCalendarBookingLocalService() {
		return calendarBookingLocalService;
	}

	/**
	 * Returns the calendar booking persistence.
	 *
	 * @return the calendar booking persistence
	 */
	public CalendarBookingPersistence getCalendarBookingPersistence() {
		return calendarBookingPersistence;
	}

	/**
	 * Returns the calendar booking remote service.
	 *
	 * @return the calendar booking remote service
	 */
	public CalendarBookingService getCalendarBookingService() {
		return calendarBookingService;
	}

	/**
	 * Returns the calendar local service.
	 *
	 * @return the calendar local service
	 */
	public CalendarLocalService getCalendarLocalService() {
		return calendarLocalService;
	}

	/**
	 * Returns the calendar persistence.
	 *
	 * @return the calendar persistence
	 */
	public CalendarPersistence getCalendarPersistence() {
		return calendarPersistence;
	}

	/**
	 * Returns the calendar resource local service.
	 *
	 * @return the calendar resource local service
	 */
	public CalendarResourceLocalService getCalendarResourceLocalService() {
		return calendarResourceLocalService;
	}

	/**
	 * Returns the calendar resource persistence.
	 *
	 * @return the calendar resource persistence
	 */
	public CalendarResourcePersistence getCalendarResourcePersistence() {
		return calendarResourcePersistence;
	}

	/**
	 * Returns the calendar resource remote service.
	 *
	 * @return the calendar resource remote service
	 */
	public CalendarResourceService getCalendarResourceService() {
		return calendarResourceService;
	}

	/**
	 * Returns the calendar remote service.
	 *
	 * @return the calendar remote service
	 */
	public CalendarService getCalendarService() {
		return calendarService;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Returns the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Returns the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	/**
	 * Sets the calendar booking local service.
	 *
	 * @param calendarBookingLocalService the calendar booking local service
	 */
	public void setCalendarBookingLocalService(
		CalendarBookingLocalService calendarBookingLocalService) {
		this.calendarBookingLocalService = calendarBookingLocalService;
	}

	/**
	 * Sets the calendar booking persistence.
	 *
	 * @param calendarBookingPersistence the calendar booking persistence
	 */
	public void setCalendarBookingPersistence(
		CalendarBookingPersistence calendarBookingPersistence) {
		this.calendarBookingPersistence = calendarBookingPersistence;
	}

	/**
	 * Sets the calendar booking remote service.
	 *
	 * @param calendarBookingService the calendar booking remote service
	 */
	public void setCalendarBookingService(
		CalendarBookingService calendarBookingService) {
		this.calendarBookingService = calendarBookingService;
	}

	/**
	 * Sets the calendar local service.
	 *
	 * @param calendarLocalService the calendar local service
	 */
	public void setCalendarLocalService(
		CalendarLocalService calendarLocalService) {
		this.calendarLocalService = calendarLocalService;
	}

	/**
	 * Sets the calendar persistence.
	 *
	 * @param calendarPersistence the calendar persistence
	 */
	public void setCalendarPersistence(CalendarPersistence calendarPersistence) {
		this.calendarPersistence = calendarPersistence;
	}

	/**
	 * Sets the calendar resource local service.
	 *
	 * @param calendarResourceLocalService the calendar resource local service
	 */
	public void setCalendarResourceLocalService(
		CalendarResourceLocalService calendarResourceLocalService) {
		this.calendarResourceLocalService = calendarResourceLocalService;
	}

	/**
	 * Sets the calendar resource persistence.
	 *
	 * @param calendarResourcePersistence the calendar resource persistence
	 */
	public void setCalendarResourcePersistence(
		CalendarResourcePersistence calendarResourcePersistence) {
		this.calendarResourcePersistence = calendarResourcePersistence;
	}

	/**
	 * Sets the calendar resource remote service.
	 *
	 * @param calendarResourceService the calendar resource remote service
	 */
	public void setCalendarResourceService(
		CalendarResourceService calendarResourceService) {
		this.calendarResourceService = calendarResourceService;
	}

	/**
	 * Sets the calendar remote service.
	 *
	 * @param calendarService the calendar remote service
	 */
	public void setCalendarService(CalendarService calendarService) {
		this.calendarService = calendarService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	protected Class<?> getModelClass() {
		return Calendar.class;
	}

	protected String getModelClassName() {
		return Calendar.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = calendarPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = CalendarLocalService.class)
	protected CalendarLocalService calendarLocalService;
	@BeanReference(type = CalendarService.class)
	protected CalendarService calendarService;
	@BeanReference(type = CalendarPersistence.class)
	protected CalendarPersistence calendarPersistence;
	@BeanReference(type = CalendarBookingLocalService.class)
	protected CalendarBookingLocalService calendarBookingLocalService;
	@BeanReference(type = CalendarBookingService.class)
	protected CalendarBookingService calendarBookingService;
	@BeanReference(type = CalendarBookingPersistence.class)
	protected CalendarBookingPersistence calendarBookingPersistence;
	@BeanReference(type = CalendarResourceLocalService.class)
	protected CalendarResourceLocalService calendarResourceLocalService;
	@BeanReference(type = CalendarResourceService.class)
	protected CalendarResourceService calendarResourceService;
	@BeanReference(type = CalendarResourcePersistence.class)
	protected CalendarResourcePersistence calendarResourcePersistence;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
}