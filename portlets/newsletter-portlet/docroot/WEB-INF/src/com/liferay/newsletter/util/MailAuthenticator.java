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

package com.liferay.newsletter.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author Bruno Pinheiro
 */
public class MailAuthenticator extends Authenticator {

	public MailAuthenticator(String username, String password) {
		_user = username;
		_password = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(_user, _password);
	}

	private String _user;
	private String _password;

}