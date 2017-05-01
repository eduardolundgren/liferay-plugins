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

/**
 * <p>
 * This class is a wrapper for {@link NewsletterCampaign}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       NewsletterCampaign
 * @generated
 */
public class NewsletterCampaignWrapper implements NewsletterCampaign {
	public NewsletterCampaignWrapper(NewsletterCampaign newsletterCampaign) {
		_newsletterCampaign = newsletterCampaign;
	}

	public Class<?> getModelClass() {
		return NewsletterCampaign.class;
	}

	public String getModelClassName() {
		return NewsletterCampaign.class.getName();
	}

	/**
	* Returns the primary key of this newsletter campaign.
	*
	* @return the primary key of this newsletter campaign
	*/
	public long getPrimaryKey() {
		return _newsletterCampaign.getPrimaryKey();
	}

	/**
	* Sets the primary key of this newsletter campaign.
	*
	* @param primaryKey the primary key of this newsletter campaign
	*/
	public void setPrimaryKey(long primaryKey) {
		_newsletterCampaign.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this newsletter campaign.
	*
	* @return the uuid of this newsletter campaign
	*/
	public java.lang.String getUuid() {
		return _newsletterCampaign.getUuid();
	}

	/**
	* Sets the uuid of this newsletter campaign.
	*
	* @param uuid the uuid of this newsletter campaign
	*/
	public void setUuid(java.lang.String uuid) {
		_newsletterCampaign.setUuid(uuid);
	}

	/**
	* Returns the campaign ID of this newsletter campaign.
	*
	* @return the campaign ID of this newsletter campaign
	*/
	public long getCampaignId() {
		return _newsletterCampaign.getCampaignId();
	}

	/**
	* Sets the campaign ID of this newsletter campaign.
	*
	* @param campaignId the campaign ID of this newsletter campaign
	*/
	public void setCampaignId(long campaignId) {
		_newsletterCampaign.setCampaignId(campaignId);
	}

	/**
	* Returns the group ID of this newsletter campaign.
	*
	* @return the group ID of this newsletter campaign
	*/
	public long getGroupId() {
		return _newsletterCampaign.getGroupId();
	}

	/**
	* Sets the group ID of this newsletter campaign.
	*
	* @param groupId the group ID of this newsletter campaign
	*/
	public void setGroupId(long groupId) {
		_newsletterCampaign.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this newsletter campaign.
	*
	* @return the company ID of this newsletter campaign
	*/
	public long getCompanyId() {
		return _newsletterCampaign.getCompanyId();
	}

	/**
	* Sets the company ID of this newsletter campaign.
	*
	* @param companyId the company ID of this newsletter campaign
	*/
	public void setCompanyId(long companyId) {
		_newsletterCampaign.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this newsletter campaign.
	*
	* @return the user ID of this newsletter campaign
	*/
	public long getUserId() {
		return _newsletterCampaign.getUserId();
	}

	/**
	* Sets the user ID of this newsletter campaign.
	*
	* @param userId the user ID of this newsletter campaign
	*/
	public void setUserId(long userId) {
		_newsletterCampaign.setUserId(userId);
	}

	/**
	* Returns the user uuid of this newsletter campaign.
	*
	* @return the user uuid of this newsletter campaign
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _newsletterCampaign.getUserUuid();
	}

	/**
	* Sets the user uuid of this newsletter campaign.
	*
	* @param userUuid the user uuid of this newsletter campaign
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_newsletterCampaign.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this newsletter campaign.
	*
	* @return the user name of this newsletter campaign
	*/
	public java.lang.String getUserName() {
		return _newsletterCampaign.getUserName();
	}

	/**
	* Sets the user name of this newsletter campaign.
	*
	* @param userName the user name of this newsletter campaign
	*/
	public void setUserName(java.lang.String userName) {
		_newsletterCampaign.setUserName(userName);
	}

	/**
	* Returns the create date of this newsletter campaign.
	*
	* @return the create date of this newsletter campaign
	*/
	public java.util.Date getCreateDate() {
		return _newsletterCampaign.getCreateDate();
	}

	/**
	* Sets the create date of this newsletter campaign.
	*
	* @param createDate the create date of this newsletter campaign
	*/
	public void setCreateDate(java.util.Date createDate) {
		_newsletterCampaign.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this newsletter campaign.
	*
	* @return the modified date of this newsletter campaign
	*/
	public java.util.Date getModifiedDate() {
		return _newsletterCampaign.getModifiedDate();
	}

	/**
	* Sets the modified date of this newsletter campaign.
	*
	* @param modifiedDate the modified date of this newsletter campaign
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_newsletterCampaign.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the content ID of this newsletter campaign.
	*
	* @return the content ID of this newsletter campaign
	*/
	public long getContentId() {
		return _newsletterCampaign.getContentId();
	}

	/**
	* Sets the content ID of this newsletter campaign.
	*
	* @param contentId the content ID of this newsletter campaign
	*/
	public void setContentId(long contentId) {
		_newsletterCampaign.setContentId(contentId);
	}

	/**
	* Returns the email subject of this newsletter campaign.
	*
	* @return the email subject of this newsletter campaign
	*/
	public java.lang.String getEmailSubject() {
		return _newsletterCampaign.getEmailSubject();
	}

	/**
	* Sets the email subject of this newsletter campaign.
	*
	* @param emailSubject the email subject of this newsletter campaign
	*/
	public void setEmailSubject(java.lang.String emailSubject) {
		_newsletterCampaign.setEmailSubject(emailSubject);
	}

	/**
	* Returns the sender email of this newsletter campaign.
	*
	* @return the sender email of this newsletter campaign
	*/
	public java.lang.String getSenderEmail() {
		return _newsletterCampaign.getSenderEmail();
	}

	/**
	* Sets the sender email of this newsletter campaign.
	*
	* @param senderEmail the sender email of this newsletter campaign
	*/
	public void setSenderEmail(java.lang.String senderEmail) {
		_newsletterCampaign.setSenderEmail(senderEmail);
	}

	/**
	* Returns the sender name of this newsletter campaign.
	*
	* @return the sender name of this newsletter campaign
	*/
	public java.lang.String getSenderName() {
		return _newsletterCampaign.getSenderName();
	}

	/**
	* Sets the sender name of this newsletter campaign.
	*
	* @param senderName the sender name of this newsletter campaign
	*/
	public void setSenderName(java.lang.String senderName) {
		_newsletterCampaign.setSenderName(senderName);
	}

	/**
	* Returns the sent of this newsletter campaign.
	*
	* @return the sent of this newsletter campaign
	*/
	public boolean getSent() {
		return _newsletterCampaign.getSent();
	}

	/**
	* Returns <code>true</code> if this newsletter campaign is sent.
	*
	* @return <code>true</code> if this newsletter campaign is sent; <code>false</code> otherwise
	*/
	public boolean isSent() {
		return _newsletterCampaign.isSent();
	}

	/**
	* Sets whether this newsletter campaign is sent.
	*
	* @param sent the sent of this newsletter campaign
	*/
	public void setSent(boolean sent) {
		_newsletterCampaign.setSent(sent);
	}

	/**
	* Returns the send date of this newsletter campaign.
	*
	* @return the send date of this newsletter campaign
	*/
	public java.util.Date getSendDate() {
		return _newsletterCampaign.getSendDate();
	}

	/**
	* Sets the send date of this newsletter campaign.
	*
	* @param sendDate the send date of this newsletter campaign
	*/
	public void setSendDate(java.util.Date sendDate) {
		_newsletterCampaign.setSendDate(sendDate);
	}

	public boolean isNew() {
		return _newsletterCampaign.isNew();
	}

	public void setNew(boolean n) {
		_newsletterCampaign.setNew(n);
	}

	public boolean isCachedModel() {
		return _newsletterCampaign.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_newsletterCampaign.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _newsletterCampaign.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_newsletterCampaign.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _newsletterCampaign.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_newsletterCampaign.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _newsletterCampaign.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_newsletterCampaign.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new NewsletterCampaignWrapper((NewsletterCampaign)_newsletterCampaign.clone());
	}

	public int compareTo(
		com.liferay.newsletter.model.NewsletterCampaign newsletterCampaign) {
		return _newsletterCampaign.compareTo(newsletterCampaign);
	}

	@Override
	public int hashCode() {
		return _newsletterCampaign.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.newsletter.model.NewsletterCampaign> toCacheModel() {
		return _newsletterCampaign.toCacheModel();
	}

	public com.liferay.newsletter.model.NewsletterCampaign toEscapedModel() {
		return new NewsletterCampaignWrapper(_newsletterCampaign.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _newsletterCampaign.toString();
	}

	public java.lang.String toXmlString() {
		return _newsletterCampaign.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_newsletterCampaign.persist();
	}

	public com.liferay.newsletter.model.NewsletterContent getContent()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _newsletterCampaign.getContent();
	}

	public java.util.List<com.liferay.newsletter.model.NewsletterLog> getLogs()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _newsletterCampaign.getLogs();
	}

	public NewsletterCampaign getWrappedNewsletterCampaign() {
		return _newsletterCampaign;
	}

	public void resetOriginalValues() {
		_newsletterCampaign.resetOriginalValues();
	}

	private NewsletterCampaign _newsletterCampaign;
}