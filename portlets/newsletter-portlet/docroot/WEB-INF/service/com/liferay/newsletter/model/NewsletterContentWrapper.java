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
 * This class is a wrapper for {@link NewsletterContent}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       NewsletterContent
 * @generated
 */
public class NewsletterContentWrapper implements NewsletterContent {
	public NewsletterContentWrapper(NewsletterContent newsletterContent) {
		_newsletterContent = newsletterContent;
	}

	public Class<?> getModelClass() {
		return NewsletterContent.class;
	}

	public String getModelClassName() {
		return NewsletterContent.class.getName();
	}

	/**
	* Returns the primary key of this newsletter content.
	*
	* @return the primary key of this newsletter content
	*/
	public long getPrimaryKey() {
		return _newsletterContent.getPrimaryKey();
	}

	/**
	* Sets the primary key of this newsletter content.
	*
	* @param primaryKey the primary key of this newsletter content
	*/
	public void setPrimaryKey(long primaryKey) {
		_newsletterContent.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this newsletter content.
	*
	* @return the uuid of this newsletter content
	*/
	public java.lang.String getUuid() {
		return _newsletterContent.getUuid();
	}

	/**
	* Sets the uuid of this newsletter content.
	*
	* @param uuid the uuid of this newsletter content
	*/
	public void setUuid(java.lang.String uuid) {
		_newsletterContent.setUuid(uuid);
	}

	/**
	* Returns the content ID of this newsletter content.
	*
	* @return the content ID of this newsletter content
	*/
	public long getContentId() {
		return _newsletterContent.getContentId();
	}

	/**
	* Sets the content ID of this newsletter content.
	*
	* @param contentId the content ID of this newsletter content
	*/
	public void setContentId(long contentId) {
		_newsletterContent.setContentId(contentId);
	}

	/**
	* Returns the group ID of this newsletter content.
	*
	* @return the group ID of this newsletter content
	*/
	public long getGroupId() {
		return _newsletterContent.getGroupId();
	}

	/**
	* Sets the group ID of this newsletter content.
	*
	* @param groupId the group ID of this newsletter content
	*/
	public void setGroupId(long groupId) {
		_newsletterContent.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this newsletter content.
	*
	* @return the company ID of this newsletter content
	*/
	public long getCompanyId() {
		return _newsletterContent.getCompanyId();
	}

	/**
	* Sets the company ID of this newsletter content.
	*
	* @param companyId the company ID of this newsletter content
	*/
	public void setCompanyId(long companyId) {
		_newsletterContent.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this newsletter content.
	*
	* @return the user ID of this newsletter content
	*/
	public long getUserId() {
		return _newsletterContent.getUserId();
	}

	/**
	* Sets the user ID of this newsletter content.
	*
	* @param userId the user ID of this newsletter content
	*/
	public void setUserId(long userId) {
		_newsletterContent.setUserId(userId);
	}

	/**
	* Returns the user uuid of this newsletter content.
	*
	* @return the user uuid of this newsletter content
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _newsletterContent.getUserUuid();
	}

	/**
	* Sets the user uuid of this newsletter content.
	*
	* @param userUuid the user uuid of this newsletter content
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_newsletterContent.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this newsletter content.
	*
	* @return the user name of this newsletter content
	*/
	public java.lang.String getUserName() {
		return _newsletterContent.getUserName();
	}

	/**
	* Sets the user name of this newsletter content.
	*
	* @param userName the user name of this newsletter content
	*/
	public void setUserName(java.lang.String userName) {
		_newsletterContent.setUserName(userName);
	}

	/**
	* Returns the create date of this newsletter content.
	*
	* @return the create date of this newsletter content
	*/
	public java.util.Date getCreateDate() {
		return _newsletterContent.getCreateDate();
	}

	/**
	* Sets the create date of this newsletter content.
	*
	* @param createDate the create date of this newsletter content
	*/
	public void setCreateDate(java.util.Date createDate) {
		_newsletterContent.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this newsletter content.
	*
	* @return the modified date of this newsletter content
	*/
	public java.util.Date getModifiedDate() {
		return _newsletterContent.getModifiedDate();
	}

	/**
	* Sets the modified date of this newsletter content.
	*
	* @param modifiedDate the modified date of this newsletter content
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_newsletterContent.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the title of this newsletter content.
	*
	* @return the title of this newsletter content
	*/
	public java.lang.String getTitle() {
		return _newsletterContent.getTitle();
	}

	/**
	* Sets the title of this newsletter content.
	*
	* @param title the title of this newsletter content
	*/
	public void setTitle(java.lang.String title) {
		_newsletterContent.setTitle(title);
	}

	/**
	* Returns the content of this newsletter content.
	*
	* @return the content of this newsletter content
	*/
	public java.lang.String getContent() {
		return _newsletterContent.getContent();
	}

	/**
	* Sets the content of this newsletter content.
	*
	* @param content the content of this newsletter content
	*/
	public void setContent(java.lang.String content) {
		_newsletterContent.setContent(content);
	}

	/**
	* Returns the article ID of this newsletter content.
	*
	* @return the article ID of this newsletter content
	*/
	public long getArticleId() {
		return _newsletterContent.getArticleId();
	}

	/**
	* Sets the article ID of this newsletter content.
	*
	* @param articleId the article ID of this newsletter content
	*/
	public void setArticleId(long articleId) {
		_newsletterContent.setArticleId(articleId);
	}

	public boolean isNew() {
		return _newsletterContent.isNew();
	}

	public void setNew(boolean n) {
		_newsletterContent.setNew(n);
	}

	public boolean isCachedModel() {
		return _newsletterContent.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_newsletterContent.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _newsletterContent.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_newsletterContent.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _newsletterContent.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_newsletterContent.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _newsletterContent.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_newsletterContent.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new NewsletterContentWrapper((NewsletterContent)_newsletterContent.clone());
	}

	public int compareTo(
		com.liferay.newsletter.model.NewsletterContent newsletterContent) {
		return _newsletterContent.compareTo(newsletterContent);
	}

	@Override
	public int hashCode() {
		return _newsletterContent.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.newsletter.model.NewsletterContent> toCacheModel() {
		return _newsletterContent.toCacheModel();
	}

	public com.liferay.newsletter.model.NewsletterContent toEscapedModel() {
		return new NewsletterContentWrapper(_newsletterContent.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _newsletterContent.toString();
	}

	public java.lang.String toXmlString() {
		return _newsletterContent.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_newsletterContent.persist();
	}

	public java.util.List<com.liferay.newsletter.model.NewsletterCampaign> getCampaigns()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _newsletterContent.getCampaigns();
	}

	public NewsletterContent getWrappedNewsletterContent() {
		return _newsletterContent;
	}

	public void resetOriginalValues() {
		_newsletterContent.resetOriginalValues();
	}

	private NewsletterContent _newsletterContent;
}