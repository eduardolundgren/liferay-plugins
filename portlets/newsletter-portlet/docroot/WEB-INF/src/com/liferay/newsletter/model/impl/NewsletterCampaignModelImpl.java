/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.newsletter.model.impl;

import com.liferay.newsletter.model.NewsletterCampaign;
import com.liferay.newsletter.model.NewsletterCampaignModel;
import com.liferay.newsletter.model.NewsletterCampaignSoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The base model implementation for the NewsletterCampaign service. Represents a row in the &quot;Newsletter_NewsletterCampaign&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.newsletter.model.NewsletterCampaignModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NewsletterCampaignImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsletterCampaignImpl
 * @see com.liferay.newsletter.model.NewsletterCampaign
 * @see com.liferay.newsletter.model.NewsletterCampaignModel
 * @generated
 */
@JSON(strict = true)
public class NewsletterCampaignModelImpl extends BaseModelImpl<NewsletterCampaign>
	implements NewsletterCampaignModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a newsletter campaign model instance should use the {@link com.liferay.newsletter.model.NewsletterCampaign} interface instead.
	 */
	public static final String TABLE_NAME = "Newsletter_NewsletterCampaign";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "campaignId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "contentId", Types.BIGINT },
			{ "emailSubject", Types.VARCHAR },
			{ "senderEmail", Types.VARCHAR },
			{ "senderName", Types.VARCHAR },
			{ "sent", Types.BOOLEAN },
			{ "sendDate", Types.TIMESTAMP }
		};
	public static final String TABLE_SQL_CREATE = "create table Newsletter_NewsletterCampaign (uuid_ VARCHAR(75) null,campaignId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,contentId LONG,emailSubject VARCHAR(75) null,senderEmail VARCHAR(75) null,senderName VARCHAR(75) null,sent BOOLEAN,sendDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table Newsletter_NewsletterCampaign";
	public static final String ORDER_BY_JPQL = " ORDER BY newsletterCampaign.sendDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY Newsletter_NewsletterCampaign.sendDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.newsletter.model.NewsletterCampaign"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.newsletter.model.NewsletterCampaign"),
			true);

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static NewsletterCampaign toModel(NewsletterCampaignSoap soapModel) {
		NewsletterCampaign model = new NewsletterCampaignImpl();

		model.setUuid(soapModel.getUuid());
		model.setCampaignId(soapModel.getCampaignId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setContentId(soapModel.getContentId());
		model.setEmailSubject(soapModel.getEmailSubject());
		model.setSenderEmail(soapModel.getSenderEmail());
		model.setSenderName(soapModel.getSenderName());
		model.setSent(soapModel.getSent());
		model.setSendDate(soapModel.getSendDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<NewsletterCampaign> toModels(
		NewsletterCampaignSoap[] soapModels) {
		List<NewsletterCampaign> models = new ArrayList<NewsletterCampaign>(soapModels.length);

		for (NewsletterCampaignSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public Class<?> getModelClass() {
		return NewsletterCampaign.class;
	}

	public String getModelClassName() {
		return NewsletterCampaign.class.getName();
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.newsletter.model.NewsletterCampaign"));

	public NewsletterCampaignModelImpl() {
	}

	public long getPrimaryKey() {
		return _campaignId;
	}

	public void setPrimaryKey(long primaryKey) {
		setCampaignId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_campaignId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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
	public long getCampaignId() {
		return _campaignId;
	}

	public void setCampaignId(long campaignId) {
		_campaignId = campaignId;
	}

	@JSON
	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
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
	public long getContentId() {
		return _contentId;
	}

	public void setContentId(long contentId) {
		_contentId = contentId;
	}

	@JSON
	public String getEmailSubject() {
		if (_emailSubject == null) {
			return StringPool.BLANK;
		}
		else {
			return _emailSubject;
		}
	}

	public void setEmailSubject(String emailSubject) {
		_emailSubject = emailSubject;
	}

	@JSON
	public String getSenderEmail() {
		if (_senderEmail == null) {
			return StringPool.BLANK;
		}
		else {
			return _senderEmail;
		}
	}

	public void setSenderEmail(String senderEmail) {
		_senderEmail = senderEmail;
	}

	@JSON
	public String getSenderName() {
		if (_senderName == null) {
			return StringPool.BLANK;
		}
		else {
			return _senderName;
		}
	}

	public void setSenderName(String senderName) {
		_senderName = senderName;
	}

	@JSON
	public boolean getSent() {
		return _sent;
	}

	public boolean isSent() {
		return _sent;
	}

	public void setSent(boolean sent) {
		_sent = sent;
	}

	@JSON
	public Date getSendDate() {
		return _sendDate;
	}

	public void setSendDate(Date sendDate) {
		_sendDate = sendDate;
	}

	@Override
	public NewsletterCampaign toEscapedModel() {
		if (isEscapedModel()) {
			return (NewsletterCampaign)this;
		}
		else {
			if (_escapedModelProxy == null) {
				_escapedModelProxy = (NewsletterCampaign)Proxy.newProxyInstance(_classLoader,
						_escapedModelProxyInterfaces,
						new AutoEscapeBeanHandler(this));
			}

			return _escapedModelProxy;
		}
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					NewsletterCampaign.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	@Override
	public Object clone() {
		NewsletterCampaignImpl newsletterCampaignImpl = new NewsletterCampaignImpl();

		newsletterCampaignImpl.setUuid(getUuid());
		newsletterCampaignImpl.setCampaignId(getCampaignId());
		newsletterCampaignImpl.setGroupId(getGroupId());
		newsletterCampaignImpl.setCompanyId(getCompanyId());
		newsletterCampaignImpl.setUserId(getUserId());
		newsletterCampaignImpl.setUserName(getUserName());
		newsletterCampaignImpl.setCreateDate(getCreateDate());
		newsletterCampaignImpl.setModifiedDate(getModifiedDate());
		newsletterCampaignImpl.setContentId(getContentId());
		newsletterCampaignImpl.setEmailSubject(getEmailSubject());
		newsletterCampaignImpl.setSenderEmail(getSenderEmail());
		newsletterCampaignImpl.setSenderName(getSenderName());
		newsletterCampaignImpl.setSent(getSent());
		newsletterCampaignImpl.setSendDate(getSendDate());

		newsletterCampaignImpl.resetOriginalValues();

		return newsletterCampaignImpl;
	}

	public int compareTo(NewsletterCampaign newsletterCampaign) {
		int value = 0;

		value = DateUtil.compareTo(getSendDate(),
				newsletterCampaign.getSendDate());

		value = value * -1;

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

		NewsletterCampaign newsletterCampaign = null;

		try {
			newsletterCampaign = (NewsletterCampaign)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = newsletterCampaign.getPrimaryKey();

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
		NewsletterCampaignModelImpl newsletterCampaignModelImpl = this;

		newsletterCampaignModelImpl._originalUuid = newsletterCampaignModelImpl._uuid;

		newsletterCampaignModelImpl._originalGroupId = newsletterCampaignModelImpl._groupId;

		newsletterCampaignModelImpl._setOriginalGroupId = false;
	}

	@Override
	public CacheModel<NewsletterCampaign> toCacheModel() {
		NewsletterCampaignCacheModel newsletterCampaignCacheModel = new NewsletterCampaignCacheModel();

		newsletterCampaignCacheModel.uuid = getUuid();

		String uuid = newsletterCampaignCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			newsletterCampaignCacheModel.uuid = null;
		}

		newsletterCampaignCacheModel.campaignId = getCampaignId();

		newsletterCampaignCacheModel.groupId = getGroupId();

		newsletterCampaignCacheModel.companyId = getCompanyId();

		newsletterCampaignCacheModel.userId = getUserId();

		newsletterCampaignCacheModel.userName = getUserName();

		String userName = newsletterCampaignCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			newsletterCampaignCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			newsletterCampaignCacheModel.createDate = createDate.getTime();
		}
		else {
			newsletterCampaignCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			newsletterCampaignCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			newsletterCampaignCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		newsletterCampaignCacheModel.contentId = getContentId();

		newsletterCampaignCacheModel.emailSubject = getEmailSubject();

		String emailSubject = newsletterCampaignCacheModel.emailSubject;

		if ((emailSubject != null) && (emailSubject.length() == 0)) {
			newsletterCampaignCacheModel.emailSubject = null;
		}

		newsletterCampaignCacheModel.senderEmail = getSenderEmail();

		String senderEmail = newsletterCampaignCacheModel.senderEmail;

		if ((senderEmail != null) && (senderEmail.length() == 0)) {
			newsletterCampaignCacheModel.senderEmail = null;
		}

		newsletterCampaignCacheModel.senderName = getSenderName();

		String senderName = newsletterCampaignCacheModel.senderName;

		if ((senderName != null) && (senderName.length() == 0)) {
			newsletterCampaignCacheModel.senderName = null;
		}

		newsletterCampaignCacheModel.sent = getSent();

		Date sendDate = getSendDate();

		if (sendDate != null) {
			newsletterCampaignCacheModel.sendDate = sendDate.getTime();
		}
		else {
			newsletterCampaignCacheModel.sendDate = Long.MIN_VALUE;
		}

		return newsletterCampaignCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", campaignId=");
		sb.append(getCampaignId());
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
		sb.append(", contentId=");
		sb.append(getContentId());
		sb.append(", emailSubject=");
		sb.append(getEmailSubject());
		sb.append(", senderEmail=");
		sb.append(getSenderEmail());
		sb.append(", senderName=");
		sb.append(getSenderName());
		sb.append(", sent=");
		sb.append(getSent());
		sb.append(", sendDate=");
		sb.append(getSendDate());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.liferay.newsletter.model.NewsletterCampaign");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>campaignId</column-name><column-value><![CDATA[");
		sb.append(getCampaignId());
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
			"<column><column-name>contentId</column-name><column-value><![CDATA[");
		sb.append(getContentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>emailSubject</column-name><column-value><![CDATA[");
		sb.append(getEmailSubject());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>senderEmail</column-name><column-value><![CDATA[");
		sb.append(getSenderEmail());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>senderName</column-name><column-value><![CDATA[");
		sb.append(getSenderName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sent</column-name><column-value><![CDATA[");
		sb.append(getSent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sendDate</column-name><column-value><![CDATA[");
		sb.append(getSendDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = NewsletterCampaign.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			NewsletterCampaign.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _campaignId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _contentId;
	private String _emailSubject;
	private String _senderEmail;
	private String _senderName;
	private boolean _sent;
	private Date _sendDate;
	private transient ExpandoBridge _expandoBridge;
	private NewsletterCampaign _escapedModelProxy;
}