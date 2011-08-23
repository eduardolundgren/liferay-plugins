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

package com.liferay.newsletter.model;

import com.liferay.newsletter.service.NewsletterLogLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

/**
 * @author Brian Wing Shun Chan
 */
public class NewsletterLogClp extends BaseModelImpl<NewsletterLog>
	implements NewsletterLog {
	public NewsletterLogClp() {
	}

	public Class<?> getModelClass() {
		return NewsletterLog.class;
	}

	public String getModelClassName() {
		return NewsletterLog.class.getName();
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
		_campaignId = campaignId;
	}

	public long getContactId() {
		return _contactId;
	}

	public void setContactId(long contactId) {
		_contactId = contactId;
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

	public void persist() throws SystemException {
		NewsletterLogLocalServiceUtil.updateNewsletterLog(this);
	}

	@Override
	public NewsletterLog toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (NewsletterLog)Proxy.newProxyInstance(NewsletterLog.class.getClassLoader(),
				new Class[] { NewsletterLog.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	@Override
	public Object clone() {
		NewsletterLogClp clone = new NewsletterLogClp();

		clone.setLogId(getLogId());
		clone.setCampaignId(getCampaignId());
		clone.setContactId(getContactId());
		clone.setSent(getSent());

		return clone;
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

		NewsletterLogClp newsletterLog = null;

		try {
			newsletterLog = (NewsletterLogClp)obj;
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

	private long _logId;
	private long _campaignId;
	private long _contactId;
	private boolean _sent;
}