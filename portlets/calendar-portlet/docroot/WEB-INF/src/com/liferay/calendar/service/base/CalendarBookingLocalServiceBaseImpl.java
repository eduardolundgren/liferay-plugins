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

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalService;
import com.liferay.calendar.service.CalendarBookingService;
import com.liferay.calendar.service.CalendarLocalService;
import com.liferay.calendar.service.CalendarResourceLocalService;
import com.liferay.calendar.service.CalendarResourceService;
import com.liferay.calendar.service.CalendarService;
import com.liferay.calendar.service.persistence.CalendarBookingFinder;
import com.liferay.calendar.service.persistence.CalendarBookingPersistence;
import com.liferay.calendar.service.persistence.CalendarFinder;
import com.liferay.calendar.service.persistence.CalendarPersistence;
import com.liferay.calendar.service.persistence.CalendarResourceFinder;
import com.liferay.calendar.service.persistence.CalendarResourcePersistence;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.mail.service.MailService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.UserPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the calendar booking local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.calendar.service.impl.CalendarBookingLocalServiceImpl}.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see com.liferay.calendar.service.impl.CalendarBookingLocalServiceImpl
 * @see com.liferay.calendar.service.CalendarBookingLocalServiceUtil
 * @generated
 */
public abstract class CalendarBookingLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements CalendarBookingLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.calendar.service.CalendarBookingLocalServiceUtil} to access the calendar booking local service.
	 */

	/**
	 * Adds the calendar booking to the database. Also notifies the appropriate model listeners.
	 *
	 * @param calendarBooking the calendar booking
	 * @return the calendar booking that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CalendarBooking addCalendarBooking(CalendarBooking calendarBooking)
		throws SystemException {
		calendarBooking.setNew(true);

		return calendarBookingPersistence.update(calendarBooking, false);
	}

	/**
	 * Creates a new calendar booking with the primary key. Does not add the calendar booking to the database.
	 *
	 * @param calendarBookingId the primary key for the new calendar booking
	 * @return the new calendar booking
	 */
	public CalendarBooking createCalendarBooking(long calendarBookingId) {
		return calendarBookingPersistence.create(calendarBookingId);
	}

	/**
	 * Deletes the calendar booking with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param calendarBookingId the primary key of the calendar booking
	 * @return the calendar booking that was removed
	 * @throws PortalException if a calendar booking with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public CalendarBooking deleteCalendarBooking(long calendarBookingId)
		throws PortalException, SystemException {
		return calendarBookingPersistence.remove(calendarBookingId);
	}

	/**
	 * Deletes the calendar booking from the database. Also notifies the appropriate model listeners.
	 *
	 * @param calendarBooking the calendar booking
	 * @return the calendar booking that was removed
	 * @throws PortalException
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public CalendarBooking deleteCalendarBooking(
		CalendarBooking calendarBooking)
		throws PortalException, SystemException {
		return calendarBookingPersistence.remove(calendarBooking);
	}

	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(CalendarBooking.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return calendarBookingPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return calendarBookingPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return calendarBookingPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return calendarBookingPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public CalendarBooking fetchCalendarBooking(long calendarBookingId)
		throws SystemException {
		return calendarBookingPersistence.fetchByPrimaryKey(calendarBookingId);
	}

	/**
	 * Returns the calendar booking with the primary key.
	 *
	 * @param calendarBookingId the primary key of the calendar booking
	 * @return the calendar booking
	 * @throws PortalException if a calendar booking with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking getCalendarBooking(long calendarBookingId)
		throws PortalException, SystemException {
		return calendarBookingPersistence.findByPrimaryKey(calendarBookingId);
	}

	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return calendarBookingPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the calendar booking with the UUID in the group.
	 *
	 * @param uuid the UUID of calendar booking
	 * @param groupId the group id of the calendar booking
	 * @return the calendar booking
	 * @throws PortalException if a calendar booking with the UUID in the group could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking getCalendarBookingByUuidAndGroupId(String uuid,
		long groupId) throws PortalException, SystemException {
		return calendarBookingPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the calendar bookings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> getCalendarBookings(int start, int end)
		throws SystemException {
		return calendarBookingPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of calendar bookings.
	 *
	 * @return the number of calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public int getCalendarBookingsCount() throws SystemException {
		return calendarBookingPersistence.countAll();
	}

	/**
	 * Updates the calendar booking in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param calendarBooking the calendar booking
	 * @return the calendar booking that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CalendarBooking updateCalendarBooking(
		CalendarBooking calendarBooking) throws SystemException {
		return updateCalendarBooking(calendarBooking, true);
	}

	/**
	 * Updates the calendar booking in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param calendarBooking the calendar booking
	 * @param merge whether to merge the calendar booking with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the calendar booking that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CalendarBooking updateCalendarBooking(
		CalendarBooking calendarBooking, boolean merge)
		throws SystemException {
		calendarBooking.setNew(false);

		return calendarBookingPersistence.update(calendarBooking, merge);
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
	 * Sets the calendar local service.
	 *
	 * @param calendarLocalService the calendar local service
	 */
	public void setCalendarLocalService(
		CalendarLocalService calendarLocalService) {
		this.calendarLocalService = calendarLocalService;
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
	 * Sets the calendar remote service.
	 *
	 * @param calendarService the calendar remote service
	 */
	public void setCalendarService(CalendarService calendarService) {
		this.calendarService = calendarService;
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
	 * Sets the calendar persistence.
	 *
	 * @param calendarPersistence the calendar persistence
	 */
	public void setCalendarPersistence(CalendarPersistence calendarPersistence) {
		this.calendarPersistence = calendarPersistence;
	}

	/**
	 * Returns the calendar finder.
	 *
	 * @return the calendar finder
	 */
	public CalendarFinder getCalendarFinder() {
		return calendarFinder;
	}

	/**
	 * Sets the calendar finder.
	 *
	 * @param calendarFinder the calendar finder
	 */
	public void setCalendarFinder(CalendarFinder calendarFinder) {
		this.calendarFinder = calendarFinder;
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
	 * Sets the calendar booking local service.
	 *
	 * @param calendarBookingLocalService the calendar booking local service
	 */
	public void setCalendarBookingLocalService(
		CalendarBookingLocalService calendarBookingLocalService) {
		this.calendarBookingLocalService = calendarBookingLocalService;
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
	 * Sets the calendar booking remote service.
	 *
	 * @param calendarBookingService the calendar booking remote service
	 */
	public void setCalendarBookingService(
		CalendarBookingService calendarBookingService) {
		this.calendarBookingService = calendarBookingService;
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
	 * Sets the calendar booking persistence.
	 *
	 * @param calendarBookingPersistence the calendar booking persistence
	 */
	public void setCalendarBookingPersistence(
		CalendarBookingPersistence calendarBookingPersistence) {
		this.calendarBookingPersistence = calendarBookingPersistence;
	}

	/**
	 * Returns the calendar booking finder.
	 *
	 * @return the calendar booking finder
	 */
	public CalendarBookingFinder getCalendarBookingFinder() {
		return calendarBookingFinder;
	}

	/**
	 * Sets the calendar booking finder.
	 *
	 * @param calendarBookingFinder the calendar booking finder
	 */
	public void setCalendarBookingFinder(
		CalendarBookingFinder calendarBookingFinder) {
		this.calendarBookingFinder = calendarBookingFinder;
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
	 * Sets the calendar resource local service.
	 *
	 * @param calendarResourceLocalService the calendar resource local service
	 */
	public void setCalendarResourceLocalService(
		CalendarResourceLocalService calendarResourceLocalService) {
		this.calendarResourceLocalService = calendarResourceLocalService;
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
	 * Sets the calendar resource remote service.
	 *
	 * @param calendarResourceService the calendar resource remote service
	 */
	public void setCalendarResourceService(
		CalendarResourceService calendarResourceService) {
		this.calendarResourceService = calendarResourceService;
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
	 * Sets the calendar resource persistence.
	 *
	 * @param calendarResourcePersistence the calendar resource persistence
	 */
	public void setCalendarResourcePersistence(
		CalendarResourcePersistence calendarResourcePersistence) {
		this.calendarResourcePersistence = calendarResourcePersistence;
	}

	/**
	 * Returns the calendar resource finder.
	 *
	 * @return the calendar resource finder
	 */
	public CalendarResourceFinder getCalendarResourceFinder() {
		return calendarResourceFinder;
	}

	/**
	 * Sets the calendar resource finder.
	 *
	 * @param calendarResourceFinder the calendar resource finder
	 */
	public void setCalendarResourceFinder(
		CalendarResourceFinder calendarResourceFinder) {
		this.calendarResourceFinder = calendarResourceFinder;
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
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the mail remote service.
	 *
	 * @return the mail remote service
	 */
	public MailService getMailService() {
		return mailService;
	}

	/**
	 * Sets the mail remote service.
	 *
	 * @param mailService the mail remote service
	 */
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
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
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
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
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
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
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
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
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.liferay.calendar.model.CalendarBooking",
			calendarBookingLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.calendar.model.CalendarBooking");
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
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return CalendarBooking.class;
	}

	protected String getModelClassName() {
		return CalendarBooking.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = calendarBookingPersistence.getDataSource();

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
	@BeanReference(type = CalendarFinder.class)
	protected CalendarFinder calendarFinder;
	@BeanReference(type = CalendarBookingLocalService.class)
	protected CalendarBookingLocalService calendarBookingLocalService;
	@BeanReference(type = CalendarBookingService.class)
	protected CalendarBookingService calendarBookingService;
	@BeanReference(type = CalendarBookingPersistence.class)
	protected CalendarBookingPersistence calendarBookingPersistence;
	@BeanReference(type = CalendarBookingFinder.class)
	protected CalendarBookingFinder calendarBookingFinder;
	@BeanReference(type = CalendarResourceLocalService.class)
	protected CalendarResourceLocalService calendarResourceLocalService;
	@BeanReference(type = CalendarResourceService.class)
	protected CalendarResourceService calendarResourceService;
	@BeanReference(type = CalendarResourcePersistence.class)
	protected CalendarResourcePersistence calendarResourcePersistence;
	@BeanReference(type = CalendarResourceFinder.class)
	protected CalendarResourceFinder calendarResourceFinder;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = MailService.class)
	protected MailService mailService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private CalendarBookingLocalServiceClpInvoker _clpInvoker = new CalendarBookingLocalServiceClpInvoker();
}