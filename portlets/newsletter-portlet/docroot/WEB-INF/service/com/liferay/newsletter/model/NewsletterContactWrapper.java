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
 * This class is a wrapper for {@link NewsletterContact}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       NewsletterContact
 * @generated
 */
public class NewsletterContactWrapper implements NewsletterContact {
	public NewsletterContactWrapper(NewsletterContact newsletterContact) {
		_newsletterContact = newsletterContact;
	}

	public Class<?> getModelClass() {
		return NewsletterContact.class;
	}

	public String getModelClassName() {
		return NewsletterContact.class.getName();
	}

	/**
	* Returns the primary key of this newsletter contact.
	*
	* @return the primary key of this newsletter contact
	*/
	public long getPrimaryKey() {
		return _newsletterContact.getPrimaryKey();
	}

	/**
	* Sets the primary key of this newsletter contact.
	*
	* @param primaryKey the primary key of this newsletter contact
	*/
	public void setPrimaryKey(long primaryKey) {
		_newsletterContact.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the contact ID of this newsletter contact.
	*
	* @return the contact ID of this newsletter contact
	*/
	public long getContactId() {
		return _newsletterContact.getContactId();
	}

	/**
	* Sets the contact ID of this newsletter contact.
	*
	* @param contactId the contact ID of this newsletter contact
	*/
	public void setContactId(long contactId) {
		_newsletterContact.setContactId(contactId);
	}

	/**
	* Returns the group ID of this newsletter contact.
	*
	* @return the group ID of this newsletter contact
	*/
	public long getGroupId() {
		return _newsletterContact.getGroupId();
	}

	/**
	* Sets the group ID of this newsletter contact.
	*
	* @param groupId the group ID of this newsletter contact
	*/
	public void setGroupId(long groupId) {
		_newsletterContact.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this newsletter contact.
	*
	* @return the company ID of this newsletter contact
	*/
	public long getCompanyId() {
		return _newsletterContact.getCompanyId();
	}

	/**
	* Sets the company ID of this newsletter contact.
	*
	* @param companyId the company ID of this newsletter contact
	*/
	public void setCompanyId(long companyId) {
		_newsletterContact.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this newsletter contact.
	*
	* @return the user ID of this newsletter contact
	*/
	public long getUserId() {
		return _newsletterContact.getUserId();
	}

	/**
	* Sets the user ID of this newsletter contact.
	*
	* @param userId the user ID of this newsletter contact
	*/
	public void setUserId(long userId) {
		_newsletterContact.setUserId(userId);
	}

	/**
	* Returns the user uuid of this newsletter contact.
	*
	* @return the user uuid of this newsletter contact
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _newsletterContact.getUserUuid();
	}

	/**
	* Sets the user uuid of this newsletter contact.
	*
	* @param userUuid the user uuid of this newsletter contact
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_newsletterContact.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this newsletter contact.
	*
	* @return the user name of this newsletter contact
	*/
	public java.lang.String getUserName() {
		return _newsletterContact.getUserName();
	}

	/**
	* Sets the user name of this newsletter contact.
	*
	* @param userName the user name of this newsletter contact
	*/
	public void setUserName(java.lang.String userName) {
		_newsletterContact.setUserName(userName);
	}

	/**
	* Returns the create date of this newsletter contact.
	*
	* @return the create date of this newsletter contact
	*/
	public java.util.Date getCreateDate() {
		return _newsletterContact.getCreateDate();
	}

	/**
	* Sets the create date of this newsletter contact.
	*
	* @param createDate the create date of this newsletter contact
	*/
	public void setCreateDate(java.util.Date createDate) {
		_newsletterContact.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this newsletter contact.
	*
	* @return the modified date of this newsletter contact
	*/
	public java.util.Date getModifiedDate() {
		return _newsletterContact.getModifiedDate();
	}

	/**
	* Sets the modified date of this newsletter contact.
	*
	* @param modifiedDate the modified date of this newsletter contact
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_newsletterContact.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the email of this newsletter contact.
	*
	* @return the email of this newsletter contact
	*/
	public java.lang.String getEmail() {
		return _newsletterContact.getEmail();
	}

	/**
	* Sets the email of this newsletter contact.
	*
	* @param email the email of this newsletter contact
	*/
	public void setEmail(java.lang.String email) {
		_newsletterContact.setEmail(email);
	}

	/**
	* Returns the name of this newsletter contact.
	*
	* @return the name of this newsletter contact
	*/
	public java.lang.String getName() {
		return _newsletterContact.getName();
	}

	/**
	* Sets the name of this newsletter contact.
	*
	* @param name the name of this newsletter contact
	*/
	public void setName(java.lang.String name) {
		_newsletterContact.setName(name);
	}

	public boolean isNew() {
		return _newsletterContact.isNew();
	}

	public void setNew(boolean n) {
		_newsletterContact.setNew(n);
	}

	public boolean isCachedModel() {
		return _newsletterContact.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_newsletterContact.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _newsletterContact.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_newsletterContact.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _newsletterContact.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_newsletterContact.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _newsletterContact.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_newsletterContact.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new NewsletterContactWrapper((NewsletterContact)_newsletterContact.clone());
	}

	public int compareTo(
		com.liferay.newsletter.model.NewsletterContact newsletterContact) {
		return _newsletterContact.compareTo(newsletterContact);
	}

	@Override
	public int hashCode() {
		return _newsletterContact.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.newsletter.model.NewsletterContact> toCacheModel() {
		return _newsletterContact.toCacheModel();
	}

	public com.liferay.newsletter.model.NewsletterContact toEscapedModel() {
		return new NewsletterContactWrapper(_newsletterContact.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _newsletterContact.toString();
	}

	public java.lang.String toXmlString() {
		return _newsletterContact.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_newsletterContact.persist();
	}

	public NewsletterContact getWrappedNewsletterContact() {
		return _newsletterContact;
	}

	public void resetOriginalValues() {
		_newsletterContact.resetOriginalValues();
	}

	private NewsletterContact _newsletterContact;
}