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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class NewsletterLogSoap implements Serializable {
	public static NewsletterLogSoap toSoapModel(NewsletterLog model) {
		NewsletterLogSoap soapModel = new NewsletterLogSoap();

		soapModel.setLogId(model.getLogId());
		soapModel.setCampaignId(model.getCampaignId());
		soapModel.setContactId(model.getContactId());
		soapModel.setSent(model.getSent());

		return soapModel;
	}

	public static NewsletterLogSoap[] toSoapModels(NewsletterLog[] models) {
		NewsletterLogSoap[] soapModels = new NewsletterLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NewsletterLogSoap[][] toSoapModels(NewsletterLog[][] models) {
		NewsletterLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NewsletterLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NewsletterLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NewsletterLogSoap[] toSoapModels(List<NewsletterLog> models) {
		List<NewsletterLogSoap> soapModels = new ArrayList<NewsletterLogSoap>(models.size());

		for (NewsletterLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NewsletterLogSoap[soapModels.size()]);
	}

	public NewsletterLogSoap() {
	}

	public long getPrimaryKey() {
		return _logId;
	}

	public void setPrimaryKey(long pk) {
		setLogId(pk);
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

	private long _logId;
	private long _campaignId;
	private long _contactId;
	private boolean _sent;
}