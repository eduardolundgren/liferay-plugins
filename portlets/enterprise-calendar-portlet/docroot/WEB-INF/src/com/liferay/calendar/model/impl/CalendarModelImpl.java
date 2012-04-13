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

package com.liferay.calendar.model.impl;

import java.io.Serializable;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarModel;
import com.liferay.calendar.model.CalendarSoap;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

/**
 * The base model implementation for the Calendar service. Represents a row in the &quot;Calendar&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.calendar.model.CalendarModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CalendarImpl}.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarImpl
 * @see com.liferay.calendar.model.Calendar
 * @see com.liferay.calendar.model.CalendarModel
 * @generated
 */
@JSON(strict = true)
public class CalendarModelImpl extends BaseModelImpl<Calendar>
	implements CalendarModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a calendar model instance should use the {@link com.liferay.calendar.model.Calendar} interface instead.
	 */
	public static final String TABLE_NAME = "Calendar";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "calendarId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "resourceBlockId", Types.BIGINT },
			{ "calendarResourceId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "color", Types.INTEGER },
			{ "defaultCalendar", Types.BOOLEAN }
		};
	public static final String TABLE_SQL_CREATE = "create table Calendar (uuid_ VARCHAR(75) null,calendarId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,resourceBlockId LONG,calendarResourceId LONG,name STRING null,description STRING null,color INTEGER,defaultCalendar BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table Calendar";
	public static final String ORDER_BY_JPQL = " ORDER BY calendar.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Calendar.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.calendar.model.Calendar"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.calendar.model.Calendar"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.calendar.model.Calendar"),
			true);
	public static long CALENDARRESOURCEID_COLUMN_BITMASK = 1L;
	public static long DEFAULTCALENDAR_COLUMN_BITMASK = 2L;
	public static long GROUPID_COLUMN_BITMASK = 4L;
	public static long RESOURCEBLOCKID_COLUMN_BITMASK = 8L;
	public static long UUID_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Calendar toModel(CalendarSoap soapModel) {
		Calendar model = new CalendarImpl();

		model.setUuid(soapModel.getUuid());
		model.setCalendarId(soapModel.getCalendarId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setResourceBlockId(soapModel.getResourceBlockId());
		model.setCalendarResourceId(soapModel.getCalendarResourceId());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setColor(soapModel.getColor());
		model.setDefaultCalendar(soapModel.getDefaultCalendar());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Calendar> toModels(CalendarSoap[] soapModels) {
		List<Calendar> models = new ArrayList<Calendar>(soapModels.length);

		for (CalendarSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.calendar.model.Calendar"));

	public CalendarModelImpl() {
	}

	public long getPrimaryKey() {
		return _calendarId;
	}

	public void setPrimaryKey(long primaryKey) {
		setCalendarId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_calendarId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return Calendar.class;
	}

	public String getModelClassName() {
		return Calendar.class.getName();
	}

	@JSON
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	public long getCalendarId() {
		return _calendarId;
	}

	public void setCalendarId(long calendarId) {
		_calendarId = calendarId;
	}

	@JSON
	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@JSON
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@JSON
	public long getResourceBlockId() {
		return _resourceBlockId;
	}

	public void setResourceBlockId(long resourceBlockId) {
		_columnBitmask |= RESOURCEBLOCKID_COLUMN_BITMASK;

		if (!_setOriginalResourceBlockId) {
			_setOriginalResourceBlockId = true;

			_originalResourceBlockId = _resourceBlockId;
		}

		_resourceBlockId = resourceBlockId;
	}

	public long getOriginalResourceBlockId() {
		return _originalResourceBlockId;
	}

	@JSON
	public long getCalendarResourceId() {
		return _calendarResourceId;
	}

	public void setCalendarResourceId(long calendarResourceId) {
		_columnBitmask |= CALENDARRESOURCEID_COLUMN_BITMASK;

		if (!_setOriginalCalendarResourceId) {
			_setOriginalCalendarResourceId = true;

			_originalCalendarResourceId = _calendarResourceId;
		}

		_calendarResourceId = calendarResourceId;
	}

	public long getOriginalCalendarResourceId() {
		return _originalCalendarResourceId;
	}

	@JSON
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getName(), languageId,
			useDefault);
	}

	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@JSON
	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	public void setName(String name) {
		_columnBitmask = -1L;

		_name = name;
	}

	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getDefault());
	}

	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(LocalizationUtil.updateLocalization(getName(), "Name",
					name, languageId, defaultLanguageId));
		}
		else {
			setName(LocalizationUtil.removeLocalization(getName(), "Name",
					languageId));
		}
	}

	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getDefault());
	}

	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		Locale[] locales = LanguageUtil.getAvailableLocales();

		for (Locale locale : locales) {
			String name = nameMap.get(locale);

			setName(name, locale, defaultLocale);
		}
	}

	@JSON
	public String getDescription() {
		if (_description == null) {
			return StringPool.BLANK;
		}
		else {
			return _description;
		}
	}

	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getDescription(), languageId,
			useDefault);
	}

	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	@JSON
	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getDefault());
	}

	public void setDescription(String description, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(LocalizationUtil.removeLocalization(
					getDescription(), "Description", languageId));
		}
	}

	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getDefault());
	}

	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale) {
		if (descriptionMap == null) {
			return;
		}

		Locale[] locales = LanguageUtil.getAvailableLocales();

		for (Locale locale : locales) {
			String description = descriptionMap.get(locale);

			setDescription(description, locale, defaultLocale);
		}
	}

	@JSON
	public int getColor() {
		return _color;
	}

	public void setColor(int color) {
		_color = color;
	}

	@JSON
	public boolean getDefaultCalendar() {
		return _defaultCalendar;
	}

	public boolean isDefaultCalendar() {
		return _defaultCalendar;
	}

	public void setDefaultCalendar(boolean defaultCalendar) {
		_columnBitmask |= DEFAULTCALENDAR_COLUMN_BITMASK;

		if (!_setOriginalDefaultCalendar) {
			_setOriginalDefaultCalendar = true;

			_originalDefaultCalendar = _defaultCalendar;
		}

		_defaultCalendar = defaultCalendar;
	}

	public boolean getOriginalDefaultCalendar() {
		return _originalDefaultCalendar;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public Calendar toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (Calendar)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					Calendar.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	@Override
	public Object clone() {
		CalendarImpl calendarImpl = new CalendarImpl();

		calendarImpl.setUuid(getUuid());
		calendarImpl.setCalendarId(getCalendarId());
		calendarImpl.setGroupId(getGroupId());
		calendarImpl.setCompanyId(getCompanyId());
		calendarImpl.setUserId(getUserId());
		calendarImpl.setUserName(getUserName());
		calendarImpl.setCreateDate(getCreateDate());
		calendarImpl.setModifiedDate(getModifiedDate());
		calendarImpl.setResourceBlockId(getResourceBlockId());
		calendarImpl.setCalendarResourceId(getCalendarResourceId());
		calendarImpl.setName(getName());
		calendarImpl.setDescription(getDescription());
		calendarImpl.setColor(getColor());
		calendarImpl.setDefaultCalendar(getDefaultCalendar());

		calendarImpl.resetOriginalValues();

		return calendarImpl;
	}

	public int compareTo(Calendar calendar) {
		int value = 0;

		value = getName().compareTo(calendar.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		Calendar calendar = null;

		try {
			calendar = (Calendar)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = calendar.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		CalendarModelImpl calendarModelImpl = this;

		calendarModelImpl._originalUuid = calendarModelImpl._uuid;

		calendarModelImpl._originalGroupId = calendarModelImpl._groupId;

		calendarModelImpl._setOriginalGroupId = false;

		calendarModelImpl._originalResourceBlockId = calendarModelImpl._resourceBlockId;

		calendarModelImpl._setOriginalResourceBlockId = false;

		calendarModelImpl._originalCalendarResourceId = calendarModelImpl._calendarResourceId;

		calendarModelImpl._setOriginalCalendarResourceId = false;

		calendarModelImpl._originalDefaultCalendar = calendarModelImpl._defaultCalendar;

		calendarModelImpl._setOriginalDefaultCalendar = false;

		calendarModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Calendar> toCacheModel() {
		CalendarCacheModel calendarCacheModel = new CalendarCacheModel();

		calendarCacheModel.uuid = getUuid();

		String uuid = calendarCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			calendarCacheModel.uuid = null;
		}

		calendarCacheModel.calendarId = getCalendarId();

		calendarCacheModel.groupId = getGroupId();

		calendarCacheModel.companyId = getCompanyId();

		calendarCacheModel.userId = getUserId();

		calendarCacheModel.userName = getUserName();

		String userName = calendarCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			calendarCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			calendarCacheModel.createDate = createDate.getTime();
		}
		else {
			calendarCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			calendarCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			calendarCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		calendarCacheModel.resourceBlockId = getResourceBlockId();

		calendarCacheModel.calendarResourceId = getCalendarResourceId();

		calendarCacheModel.name = getName();

		String name = calendarCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			calendarCacheModel.name = null;
		}

		calendarCacheModel.description = getDescription();

		String description = calendarCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			calendarCacheModel.description = null;
		}

		calendarCacheModel.color = getColor();

		calendarCacheModel.defaultCalendar = getDefaultCalendar();

		return calendarCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", calendarId=");
		sb.append(getCalendarId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", resourceBlockId=");
		sb.append(getResourceBlockId());
		sb.append(", calendarResourceId=");
		sb.append(getCalendarResourceId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", color=");
		sb.append(getColor());
		sb.append(", defaultCalendar=");
		sb.append(getDefaultCalendar());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.liferay.calendar.model.Calendar");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>calendarId</column-name><column-value><![CDATA[");
		sb.append(getCalendarId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>resourceBlockId</column-name><column-value><![CDATA[");
		sb.append(getResourceBlockId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>calendarResourceId</column-name><column-value><![CDATA[");
		sb.append(getCalendarResourceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>color</column-name><column-value><![CDATA[");
		sb.append(getColor());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>defaultCalendar</column-name><column-value><![CDATA[");
		sb.append(getDefaultCalendar());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Calendar.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			Calendar.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _calendarId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _resourceBlockId;
	private long _originalResourceBlockId;
	private boolean _setOriginalResourceBlockId;
	private long _calendarResourceId;
	private long _originalCalendarResourceId;
	private boolean _setOriginalCalendarResourceId;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private int _color;
	private boolean _defaultCalendar;
	private boolean _originalDefaultCalendar;
	private boolean _setOriginalDefaultCalendar;
	private transient ExpandoBridge _expandoBridge;
	private long _columnBitmask;
	private Calendar _escapedModelProxy;
}