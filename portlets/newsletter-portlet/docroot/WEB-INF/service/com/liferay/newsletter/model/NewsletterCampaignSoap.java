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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.newsletter.service.http.NewsletterCampaignServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.newsletter.service.http.NewsletterCampaignServiceSoap
 * @generated
 */
public class NewsletterCampaignSoap implements Serializable {
	public static NewsletterCampaignSoap toSoapModel(NewsletterCampaign model) {
		NewsletterCampaignSoap soapModel = new NewsletterCampaignSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCampaignId(model.getCampaignId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setContentId(model.getContentId());
		soapModel.setEmailSubject(model.getEmailSubject());
		soapModel.setSenderEmail(model.getSenderEmail());
		soapModel.setSenderName(model.getSenderName());
		soapModel.setSent(model.getSent());
		soapModel.setSendDate(model.getSendDate());

		return soapModel;
	}

	public static NewsletterCampaignSoap[] toSoapModels(
		NewsletterCampaign[] models) {
		NewsletterCampaignSoap[] soapModels = new NewsletterCampaignSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NewsletterCampaignSoap[][] toSoapModels(
		NewsletterCampaign[][] models) {
		NewsletterCampaignSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NewsletterCampaignSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NewsletterCampaignSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NewsletterCampaignSoap[] toSoapModels(
		List<NewsletterCampaign> models) {
		List<NewsletterCampaignSoap> soapModels = new ArrayList<NewsletterCampaignSoap>(models.size());

		for (NewsletterCampaign model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NewsletterCampaignSoap[soapModels.size()]);
	}

	public NewsletterCampaignSoap() {
	}

	public long getPrimaryKey() {
		return _campaignId;
	}

	public void setPrimaryKey(long pk) {
		setCampaignId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCampaignId() {
		return _campaignId;
	}

	public void setCampaignId(long campaignId) {
		_campaignId = campaignId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getContentId() {
		return _contentId;
	}

	public void setContentId(long contentId) {
		_contentId = contentId;
	}

	public String getEmailSubject() {
		return _emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		_emailSubject = emailSubject;
	}

	public String getSenderEmail() {
		return _senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		_senderEmail = senderEmail;
	}

	public String getSenderName() {
		return _senderName;
	}

	public void setSenderName(String senderName) {
		_senderName = senderName;
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

	public Date getSendDate() {
		return _sendDate;
	}

	public void setSendDate(Date sendDate) {
		_sendDate = sendDate;
	}

	private String _uuid;
	private long _campaignId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _contentId;
	private String _emailSubject;
	private String _senderEmail;
	private String _senderName;
	private boolean _sent;
	private Date _sendDate;
}