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

import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.model.CalendarResourceModel;
import com.liferay.calendar.model.CalendarResourceSoap;

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

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * The base model implementation for the CalendarResource service. Represents a row in the &quot;CalendarResource&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.calendar.model.CalendarResourceModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CalendarResourceImpl}.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarResourceImpl
 * @see com.liferay.calendar.model.CalendarResource
 * @see com.liferay.calendar.model.CalendarResourceModel
 * @generated
 */
@JSON(strict = true)
public class CalendarResourceModelImpl extends BaseModelImpl<CalendarResource>
	implements CalendarResourceModel {
	public CalendarResourceModelImpl() {
	}
	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CalendarResource toModel(CalendarResourceSoap soapModel) {
		CalendarResource model = new CalendarResourceImpl();

		model.setUuid(soapModel.getUuid());
		model.setCalendarResourceId(soapModel.getCalendarResourceId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setResourceBlockId(soapModel.getResourceBlockId());
		model.setClassName(soapModel.getClassName());
		model.setClassPK(soapModel.getClassPK());
		model.setClassUuid(soapModel.getClassUuid());
		model.setDefaultCalendarId(soapModel.getDefaultCalendarId());
		model.setCode(soapModel.getCode());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setType(soapModel.getType());
		model.setActive(soapModel.getActive());

		return model;
	}
	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CalendarResource> toModels(
		CalendarResourceSoap[] soapModels) {
		List<CalendarResource> models = new ArrayList<CalendarResource>(soapModels.length);

		for (CalendarResourceSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}
	@Override
	public Object clone() {
		CalendarResourceImpl calendarResourceImpl = new CalendarResourceImpl();

		calendarResourceImpl.setUuid(getUuid());
		calendarResourceImpl.setCalendarResourceId(getCalendarResourceId());
		calendarResourceImpl.setGroupId(getGroupId());
		calendarResourceImpl.setCompanyId(getCompanyId());
		calendarResourceImpl.setUserId(getUserId());
		calendarResourceImpl.setUserName(getUserName());
		calendarResourceImpl.setCreateDate(getCreateDate());
		calendarResourceImpl.setModifiedDate(getModifiedDate());
		calendarResourceImpl.setResourceBlockId(getResourceBlockId());
		calendarResourceImpl.setClassName(getClassName());
		calendarResourceImpl.setClassPK(getClassPK());
		calendarResourceImpl.setClassUuid(getClassUuid());
		calendarResourceImpl.setDefaultCalendarId(getDefaultCalendarId());
		calendarResourceImpl.setCode(getCode());
		calendarResourceImpl.setName(getName());
		calendarResourceImpl.setDescription(getDescription());
		calendarResourceImpl.setType(getType());
		calendarResourceImpl.setActive(getActive());

		calendarResourceImpl.resetOriginalValues();

		return calendarResourceImpl;
	}
	public int compareTo(CalendarResource calendarResource) {
		int value = 0;

		value = getCode().toLowerCase()
					.compareTo(calendarResource.getCode().toLowerCase());

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

		CalendarResource calendarResource = null;

		try {
			calendarResource = (CalendarResource)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = calendarResource.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}
	@JSON
	public boolean getActive() {
		return _active;
	}
	@JSON
	public long getCalendarResourceId() {
		return _calendarResourceId;
	}
	@JSON
	public String getClassName() {
		if (_className == null) {
			return StringPool.BLANK;
		}
		else {
			return _className;
		}
	}
	@JSON
	public long getClassPK() {
		return _classPK;
	}
	@JSON
	public String getClassUuid() {
		if (_classUuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _classUuid;
		}
	}
	@JSON
	public String getCode() {
		if (_code == null) {
			return StringPool.BLANK;
		}
		else {
			return _code;
		}
	}
	public long getColumnBitmask() {
		return _columnBitmask;
	}
	@JSON
	public long getCompanyId() {
		return _companyId;
	}
	@JSON
	public Date getCreateDate() {
		return _createDate;
	}
	@JSON
	public long getDefaultCalendarId() {
		return _defaultCalendarId;
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

	@Override
	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					CalendarResource.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	@JSON
	public long getGroupId() {
		return _groupId;
	}

	public Class<?> getModelClass() {
		return CalendarResource.class;
	}

	public String getModelClassName() {
		return CalendarResource.class.getName();
	}

	@JSON
	public Date getModifiedDate() {
		return _modifiedDate;
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

	public boolean getOriginalActive() {
		return _originalActive;
	}

	public String getOriginalClassName() {
		return GetterUtil.getString(_originalClassName);
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	public String getOriginalCode() {
		return GetterUtil.getString(_originalCode);
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	public long getOriginalResourceBlockId() {
		return _originalResourceBlockId;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	public long getPrimaryKey() {
		return _calendarResourceId;
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_calendarResourceId);
	}

	@JSON
	public long getResourceBlockId() {
		return _resourceBlockId;
	}

	@JSON
	public String getType() {
		if (_type == null) {
			return StringPool.BLANK;
		}
		else {
			return _type;
		}
	}

	@JSON
	public long getUserId() {
		return _userId;
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

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
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

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public boolean isActive() {
		return _active;
	}

	@Override
	public void resetOriginalValues() {
		CalendarResourceModelImpl calendarResourceModelImpl = this;

		calendarResourceModelImpl._originalUuid = calendarResourceModelImpl._uuid;

		calendarResourceModelImpl._originalGroupId = calendarResourceModelImpl._groupId;

		calendarResourceModelImpl._setOriginalGroupId = false;

		calendarResourceModelImpl._originalCompanyId = calendarResourceModelImpl._companyId;

		calendarResourceModelImpl._setOriginalCompanyId = false;

		calendarResourceModelImpl._originalResourceBlockId = calendarResourceModelImpl._resourceBlockId;

		calendarResourceModelImpl._setOriginalResourceBlockId = false;

		calendarResourceModelImpl._originalClassName = calendarResourceModelImpl._className;

		calendarResourceModelImpl._originalClassPK = calendarResourceModelImpl._classPK;

		calendarResourceModelImpl._setOriginalClassPK = false;

		calendarResourceModelImpl._originalCode = calendarResourceModelImpl._code;

		calendarResourceModelImpl._originalName = calendarResourceModelImpl._name;

		calendarResourceModelImpl._originalActive = calendarResourceModelImpl._active;

		calendarResourceModelImpl._setOriginalActive = false;

		calendarResourceModelImpl._columnBitmask = 0;
	}

	public void setActive(boolean active) {
		_columnBitmask |= ACTIVE_COLUMN_BITMASK;

		if (!_setOriginalActive) {
			_setOriginalActive = true;

			_originalActive = _active;
		}

		_active = active;
	}

	public void setCalendarResourceId(long calendarResourceId) {
		_calendarResourceId = calendarResourceId;
	}

	public void setClassName(String className) {
		_columnBitmask |= CLASSNAME_COLUMN_BITMASK;

		if (_originalClassName == null) {
			_originalClassName = _className;
		}

		_className = className;
	}

	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public void setClassUuid(String classUuid) {
		_classUuid = classUuid;
	}

	public void setCode(String code) {
		_columnBitmask = -1L;

		if (_originalCode == null) {
			_originalCode = _code;
		}

		_code = code;
	}

	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public void setDefaultCalendarId(long defaultCalendarId) {
		_defaultCalendarId = defaultCalendarId;
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

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public void setName(String name) {
		_columnBitmask |= NAME_COLUMN_BITMASK;

		if (_originalName == null) {
			_originalName = _name;
		}

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

	public void setPrimaryKey(long primaryKey) {
		setCalendarResourceId(primaryKey);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public void setResourceBlockId(long resourceBlockId) {
		_columnBitmask |= RESOURCEBLOCKID_COLUMN_BITMASK;

		if (!_setOriginalResourceBlockId) {
			_setOriginalResourceBlockId = true;

			_originalResourceBlockId = _resourceBlockId;
		}

		_resourceBlockId = resourceBlockId;
	}

	public void setType(String type) {
		_type = type;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	@Override
	public CacheModel<CalendarResource> toCacheModel() {
		CalendarResourceCacheModel calendarResourceCacheModel = new CalendarResourceCacheModel();

		calendarResourceCacheModel.uuid = getUuid();

		String uuid = calendarResourceCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			calendarResourceCacheModel.uuid = null;
		}

		calendarResourceCacheModel.calendarResourceId = getCalendarResourceId();

		calendarResourceCacheModel.groupId = getGroupId();

		calendarResourceCacheModel.companyId = getCompanyId();

		calendarResourceCacheModel.userId = getUserId();

		calendarResourceCacheModel.userName = getUserName();

		String userName = calendarResourceCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			calendarResourceCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			calendarResourceCacheModel.createDate = createDate.getTime();
		}
		else {
			calendarResourceCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			calendarResourceCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			calendarResourceCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		calendarResourceCacheModel.resourceBlockId = getResourceBlockId();

		calendarResourceCacheModel.className = getClassName();

		String className = calendarResourceCacheModel.className;

		if ((className != null) && (className.length() == 0)) {
			calendarResourceCacheModel.className = null;
		}

		calendarResourceCacheModel.classPK = getClassPK();

		calendarResourceCacheModel.classUuid = getClassUuid();

		String classUuid = calendarResourceCacheModel.classUuid;

		if ((classUuid != null) && (classUuid.length() == 0)) {
			calendarResourceCacheModel.classUuid = null;
		}

		calendarResourceCacheModel.defaultCalendarId = getDefaultCalendarId();

		calendarResourceCacheModel.code = getCode();

		String code = calendarResourceCacheModel.code;

		if ((code != null) && (code.length() == 0)) {
			calendarResourceCacheModel.code = null;
		}

		calendarResourceCacheModel.name = getName();

		String name = calendarResourceCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			calendarResourceCacheModel.name = null;
		}

		calendarResourceCacheModel.description = getDescription();

		String description = calendarResourceCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			calendarResourceCacheModel.description = null;
		}

		calendarResourceCacheModel.type = getType();

		String type = calendarResourceCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			calendarResourceCacheModel.type = null;
		}

		calendarResourceCacheModel.active = getActive();

		return calendarResourceCacheModel;
	}

	@Override
	public CalendarResource toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (CalendarResource)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", calendarResourceId=");
		sb.append(getCalendarResourceId());
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
		sb.append(", className=");
		sb.append(getClassName());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", classUuid=");
		sb.append(getClassUuid());
		sb.append(", defaultCalendarId=");
		sb.append(getDefaultCalendarId());
		sb.append(", code=");
		sb.append(getCode());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", active=");
		sb.append(getActive());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("com.liferay.calendar.model.CalendarResource");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>calendarResourceId</column-name><column-value><![CDATA[");
		sb.append(getCalendarResourceId());
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
			"<column><column-name>className</column-name><column-value><![CDATA[");
		sb.append(getClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classUuid</column-name><column-value><![CDATA[");
		sb.append(getClassUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>defaultCalendarId</column-name><column-value><![CDATA[");
		sb.append(getDefaultCalendarId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>code</column-name><column-value><![CDATA[");
		sb.append(getCode());
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
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(getActive());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a calendar resource model instance should use the {@link com.liferay.calendar.model.CalendarResource} interface instead.
	 */
	public static final String TABLE_NAME = "CalendarResource";

	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "calendarResourceId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "resourceBlockId", Types.BIGINT },
			{ "className", Types.VARCHAR },
			{ "classPK", Types.BIGINT },
			{ "classUuid", Types.VARCHAR },
			{ "defaultCalendarId", Types.BIGINT },
			{ "code_", Types.VARCHAR },
			{ "name", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "type_", Types.VARCHAR },
			{ "active_", Types.BOOLEAN }
		};

	public static final String TABLE_SQL_CREATE = "create table CalendarResource (uuid_ VARCHAR(75) null,calendarResourceId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,resourceBlockId LONG,className VARCHAR(75) null,classPK LONG,classUuid VARCHAR(75) null,defaultCalendarId LONG,code_ VARCHAR(75) null,name STRING null,description STRING null,type_ VARCHAR(75) null,active_ BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table CalendarResource";

	public static final String ORDER_BY_JPQL = " ORDER BY calendarResource.code ASC";

	public static final String ORDER_BY_SQL = " ORDER BY CalendarResource.code_ ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.calendar.model.CalendarResource"),
			true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.calendar.model.CalendarResource"),
			true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.calendar.model.CalendarResource"),
			true);

	public static long ACTIVE_COLUMN_BITMASK = 1L;

	public static long CLASSNAME_COLUMN_BITMASK = 2L;

	public static long CLASSPK_COLUMN_BITMASK = 4L;

	public static long CODE_COLUMN_BITMASK = 8L;

	public static long COMPANYID_COLUMN_BITMASK = 16L;

	public static long GROUPID_COLUMN_BITMASK = 32L;

	public static long NAME_COLUMN_BITMASK = 64L;

	public static long RESOURCEBLOCKID_COLUMN_BITMASK = 128L;

	public static long UUID_COLUMN_BITMASK = 256L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.calendar.model.CalendarResource"));

	private static ClassLoader _classLoader = CalendarResource.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			CalendarResource.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _calendarResourceId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _resourceBlockId;
	private long _originalResourceBlockId;
	private boolean _setOriginalResourceBlockId;
	private String _className;
	private String _originalClassName;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private String _classUuid;
	private long _defaultCalendarId;
	private String _code;
	private String _originalCode;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _originalName;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private String _type;
	private boolean _active;
	private boolean _originalActive;
	private boolean _setOriginalActive;
	private transient ExpandoBridge _expandoBridge;
	private long _columnBitmask;
	private CalendarResource _escapedModelProxy;
}