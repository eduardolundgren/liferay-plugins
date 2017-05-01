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

import com.liferay.newsletter.model.NewsletterLog;
import com.liferay.newsletter.model.NewsletterLogModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

/**
 * The base model implementation for the NewsletterLog service. Represents a row in the &quot;Newsletter_NewsletterLog&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.newsletter.model.NewsletterLogModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NewsletterLogImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsletterLogImpl
 * @see com.liferay.newsletter.model.NewsletterLog
 * @see com.liferay.newsletter.model.NewsletterLogModel
 * @generated
 */
public class NewsletterLogModelImpl extends BaseModelImpl<NewsletterLog>
	implements NewsletterLogModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a newsletter log model instance should use the {@link com.liferay.newsletter.model.NewsletterLog} interface instead.
	 */
	public static final String TABLE_NAME = "Newsletter_NewsletterLog";
	public static final Object[][] TABLE_COLUMNS = {
			{ "logId", Types.BIGINT },
			{ "campaignId", Types.BIGINT },
			{ "contactId", Types.BIGINT },
			{ "sent", Types.BOOLEAN }
		};
	public static final String TABLE_SQL_CREATE = "create table Newsletter_NewsletterLog (logId LONG not null primary key,campaignId LONG,contactId LONG,sent BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table Newsletter_NewsletterLog";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.newsletter.model.NewsletterLog"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.newsletter.model.NewsletterLog"),
			true);

	public Class<?> getModelClass() {
		return NewsletterLog.class;
	}

	public String getModelClassName() {
		return NewsletterLog.class.getName();
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.newsletter.model.NewsletterLog"));

	public NewsletterLogModelImpl() {
	}

	public long getPrimaryKey() {
		return _logId;
	}

	public void setPrimaryKey(long primaryKey) {
		setLogId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_logId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getLogId() {
		return _logId;
	}

	public void setLogId(long logId) {
		_logId = logId;
	}

	public long getCampaignId() {
		return _campaignId;
	}

	public void setCampaignId(long campaignId) {
		if (!_setOriginalCampaignId) {
			_setOriginalCampaignId = true;

			_originalCampaignId = _campaignId;
		}

		_campaignId = campaignId;
	}

	public long getOriginalCampaignId() {
		return _originalCampaignId;
	}

	public long getContactId() {
		return _contactId;
	}

	public void setContactId(long contactId) {
		if (!_setOriginalContactId) {
			_setOriginalContactId = true;

			_originalContactId = _contactId;
		}

		_contactId = contactId;
	}

	public long getOriginalContactId() {
		return _originalContactId;
	}

	public boolean getSent() {
		return _sent;
	}

	public boolean isSent() {
		return _sent;
	}

	public void setSent(boolean sent) {
		_sent = sent;
	}

	@Override
	public NewsletterLog toEscapedModel() {
		if (isEscapedModel()) {
			return (NewsletterLog)this;
		}
		else {
			if (_escapedModelProxy == null) {
				_escapedModelProxy = (NewsletterLog)Proxy.newProxyInstance(_classLoader,
						_escapedModelProxyInterfaces,
						new AutoEscapeBeanHandler(this));
			}

			return _escapedModelProxy;
		}
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(0,
					NewsletterLog.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	@Override
	public Object clone() {
		NewsletterLogImpl newsletterLogImpl = new NewsletterLogImpl();

		newsletterLogImpl.setLogId(getLogId());
		newsletterLogImpl.setCampaignId(getCampaignId());
		newsletterLogImpl.setContactId(getContactId());
		newsletterLogImpl.setSent(getSent());

		newsletterLogImpl.resetOriginalValues();

		return newsletterLogImpl;
	}

	public int compareTo(NewsletterLog newsletterLog) {
		long primaryKey = newsletterLog.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		NewsletterLog newsletterLog = null;

		try {
			newsletterLog = (NewsletterLog)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = newsletterLog.getPrimaryKey();

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
		NewsletterLogModelImpl newsletterLogModelImpl = this;

		newsletterLogModelImpl._originalCampaignId = newsletterLogModelImpl._campaignId;

		newsletterLogModelImpl._setOriginalCampaignId = false;

		newsletterLogModelImpl._originalContactId = newsletterLogModelImpl._contactId;

		newsletterLogModelImpl._setOriginalContactId = false;
	}

	@Override
	public CacheModel<NewsletterLog> toCacheModel() {
		NewsletterLogCacheModel newsletterLogCacheModel = new NewsletterLogCacheModel();

		newsletterLogCacheModel.logId = getLogId();

		newsletterLogCacheModel.campaignId = getCampaignId();

		newsletterLogCacheModel.contactId = getContactId();

		newsletterLogCacheModel.sent = getSent();

		return newsletterLogCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{logId=");
		sb.append(getLogId());
		sb.append(", campaignId=");
		sb.append(getCampaignId());
		sb.append(", contactId=");
		sb.append(getContactId());
		sb.append(", sent=");
		sb.append(getSent());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.liferay.newsletter.model.NewsletterLog");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>logId</column-name><column-value><![CDATA[");
		sb.append(getLogId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>campaignId</column-name><column-value><![CDATA[");
		sb.append(getCampaignId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>contactId</column-name><column-value><![CDATA[");
		sb.append(getContactId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sent</column-name><column-value><![CDATA[");
		sb.append(getSent());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = NewsletterLog.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			NewsletterLog.class
		};
	private long _logId;
	private long _campaignId;
	private long _originalCampaignId;
	private boolean _setOriginalCampaignId;
	private long _contactId;
	private long _originalContactId;
	private boolean _setOriginalContactId;
	private boolean _sent;
	private transient ExpandoBridge _expandoBridge;
	private NewsletterLog _escapedModelProxy;
}