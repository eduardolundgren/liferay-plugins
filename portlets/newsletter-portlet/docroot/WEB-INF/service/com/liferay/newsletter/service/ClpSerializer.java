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

package com.liferay.newsletter.service;

import com.liferay.newsletter.model.NewsletterCampaignClp;
import com.liferay.newsletter.model.NewsletterContactClp;
import com.liferay.newsletter.model.NewsletterContentClp;
import com.liferay.newsletter.model.NewsletterLogClp;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ClpSerializer {
	public static String getServletContextName() {
		if (Validator.isNotNull(_servletContextName)) {
			return _servletContextName;
		}

		synchronized (ClpSerializer.class) {
			if (Validator.isNotNull(_servletContextName)) {
				return _servletContextName;
			}

			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Class<?> portletPropsClass = classLoader.loadClass(
						"com.liferay.util.portlet.PortletProps");

				Method getMethod = portletPropsClass.getMethod("get",
						new Class<?>[] { String.class });

				String portletPropsServletContextName = (String)getMethod.invoke(null,
						"newsletter-portlet-deployment-context");

				if (Validator.isNotNull(portletPropsServletContextName)) {
					_servletContextName = portletPropsServletContextName;
				}
			}
			catch (Throwable t) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to locate deployment context from portlet properties",
						t);
				}
			}

			if (Validator.isNull(_servletContextName)) {
				try {
					String propsUtilServletContextName = PropsUtil.get(
							"newsletter-portlet-deployment-context");

					if (Validator.isNotNull(propsUtilServletContextName)) {
						_servletContextName = propsUtilServletContextName;
					}
				}
				catch (Throwable t) {
					if (_log.isWarnEnabled()) {
						_log.warn("Unable to locate deployment context from portal properties",
							t);
					}
				}
			}

			if (Validator.isNull(_servletContextName)) {
				_servletContextName = "newsletter-portlet";
			}

			return _servletContextName;
		}
	}

	public static void setClassLoader(ClassLoader classLoader) {
		_classLoader = classLoader;
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(NewsletterCampaignClp.class.getName())) {
			return translateInputNewsletterCampaign(oldModel);
		}

		if (oldModelClassName.equals(NewsletterContactClp.class.getName())) {
			return translateInputNewsletterContact(oldModel);
		}

		if (oldModelClassName.equals(NewsletterContentClp.class.getName())) {
			return translateInputNewsletterContent(oldModel);
		}

		if (oldModelClassName.equals(NewsletterLogClp.class.getName())) {
			return translateInputNewsletterLog(oldModel);
		}

		return oldModel;
	}

	public static Object translateInput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	public static Object translateInputNewsletterCampaign(BaseModel<?> oldModel) {
		NewsletterCampaignClp oldCplModel = (NewsletterCampaignClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.newsletter.model.impl.NewsletterCampaignImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setUuid",
						new Class[] { String.class });

				String value0 = oldCplModel.getUuid();

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setCampaignId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getCampaignId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getGroupId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getCompanyId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value4 = new Long(oldCplModel.getUserId());

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value5 = oldCplModel.getUserName();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getCreateDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value7 = oldCplModel.getModifiedDate();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setContentId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getContentId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setEmailSubject",
						new Class[] { String.class });

				String value9 = oldCplModel.getEmailSubject();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setSenderEmail",
						new Class[] { String.class });

				String value10 = oldCplModel.getSenderEmail();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setSenderName",
						new Class[] { String.class });

				String value11 = oldCplModel.getSenderName();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setSent",
						new Class[] { Boolean.TYPE });

				Boolean value12 = new Boolean(oldCplModel.getSent());

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setSendDate",
						new Class[] { Date.class });

				Date value13 = oldCplModel.getSendDate();

				method13.invoke(newModel, value13);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputNewsletterContact(BaseModel<?> oldModel) {
		NewsletterContactClp oldCplModel = (NewsletterContactClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.newsletter.model.impl.NewsletterContactImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setContactId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getContactId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value4 = oldCplModel.getUserName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getCreateDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getModifiedDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setEmail",
						new Class[] { String.class });

				String value7 = oldCplModel.getEmail();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value8 = oldCplModel.getName();

				method8.invoke(newModel, value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputNewsletterContent(BaseModel<?> oldModel) {
		NewsletterContentClp oldCplModel = (NewsletterContentClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.newsletter.model.impl.NewsletterContentImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setUuid",
						new Class[] { String.class });

				String value0 = oldCplModel.getUuid();

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setContentId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getContentId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getGroupId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getCompanyId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value4 = new Long(oldCplModel.getUserId());

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setUserName",
						new Class[] { String.class });

				String value5 = oldCplModel.getUserName();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setCreateDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getCreateDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setModifiedDate",
						new Class[] { Date.class });

				Date value7 = oldCplModel.getModifiedDate();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setTitle",
						new Class[] { String.class });

				String value8 = oldCplModel.getTitle();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setContent",
						new Class[] { String.class });

				String value9 = oldCplModel.getContent();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setArticleId",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getArticleId());

				method10.invoke(newModel, value10);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputNewsletterLog(BaseModel<?> oldModel) {
		NewsletterLogClp oldCplModel = (NewsletterLogClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.newsletter.model.impl.NewsletterLogImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setLogId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getLogId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setCampaignId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getCampaignId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setContactId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getContactId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setSent",
						new Class[] { Boolean.TYPE });

				Boolean value3 = new Boolean(oldCplModel.getSent());

				method3.invoke(newModel, value3);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateInput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateInput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(
					"com.liferay.newsletter.model.impl.NewsletterCampaignImpl")) {
			return translateOutputNewsletterCampaign(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.newsletter.model.impl.NewsletterContactImpl")) {
			return translateOutputNewsletterContact(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.newsletter.model.impl.NewsletterContentImpl")) {
			return translateOutputNewsletterContent(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.newsletter.model.impl.NewsletterLogImpl")) {
			return translateOutputNewsletterLog(oldModel);
		}

		return oldModel;
	}

	public static Object translateOutput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	public static Object translateOutput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateOutput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutputNewsletterCampaign(
		BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				NewsletterCampaignClp newModel = new NewsletterCampaignClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getUuid");

				String value0 = (String)method0.invoke(oldModel, (Object[])null);

				newModel.setUuid(value0);

				Method method1 = oldModelClass.getMethod("getCampaignId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setCampaignId(value1);

				Method method2 = oldModelClass.getMethod("getGroupId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value2);

				Method method3 = oldModelClass.getMethod("getCompanyId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value3);

				Method method4 = oldModelClass.getMethod("getUserId");

				Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

				newModel.setUserId(value4);

				Method method5 = oldModelClass.getMethod("getUserName");

				String value5 = (String)method5.invoke(oldModel, (Object[])null);

				newModel.setUserName(value5);

				Method method6 = oldModelClass.getMethod("getCreateDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value6);

				Method method7 = oldModelClass.getMethod("getModifiedDate");

				Date value7 = (Date)method7.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value7);

				Method method8 = oldModelClass.getMethod("getContentId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setContentId(value8);

				Method method9 = oldModelClass.getMethod("getEmailSubject");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setEmailSubject(value9);

				Method method10 = oldModelClass.getMethod("getSenderEmail");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setSenderEmail(value10);

				Method method11 = oldModelClass.getMethod("getSenderName");

				String value11 = (String)method11.invoke(oldModel,
						(Object[])null);

				newModel.setSenderName(value11);

				Method method12 = oldModelClass.getMethod("getSent");

				Boolean value12 = (Boolean)method12.invoke(oldModel,
						(Object[])null);

				newModel.setSent(value12);

				Method method13 = oldModelClass.getMethod("getSendDate");

				Date value13 = (Date)method13.invoke(oldModel, (Object[])null);

				newModel.setSendDate(value13);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputNewsletterContact(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				NewsletterContactClp newModel = new NewsletterContactClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getContactId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setContactId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getEmail");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setEmail(value7);

				Method method8 = oldModelClass.getMethod("getName");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setName(value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputNewsletterContent(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				NewsletterContentClp newModel = new NewsletterContentClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getUuid");

				String value0 = (String)method0.invoke(oldModel, (Object[])null);

				newModel.setUuid(value0);

				Method method1 = oldModelClass.getMethod("getContentId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setContentId(value1);

				Method method2 = oldModelClass.getMethod("getGroupId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value2);

				Method method3 = oldModelClass.getMethod("getCompanyId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value3);

				Method method4 = oldModelClass.getMethod("getUserId");

				Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

				newModel.setUserId(value4);

				Method method5 = oldModelClass.getMethod("getUserName");

				String value5 = (String)method5.invoke(oldModel, (Object[])null);

				newModel.setUserName(value5);

				Method method6 = oldModelClass.getMethod("getCreateDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value6);

				Method method7 = oldModelClass.getMethod("getModifiedDate");

				Date value7 = (Date)method7.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value7);

				Method method8 = oldModelClass.getMethod("getTitle");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setTitle(value8);

				Method method9 = oldModelClass.getMethod("getContent");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setContent(value9);

				Method method10 = oldModelClass.getMethod("getArticleId");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setArticleId(value10);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputNewsletterLog(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				NewsletterLogClp newModel = new NewsletterLogClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getLogId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setLogId(value0);

				Method method1 = oldModelClass.getMethod("getCampaignId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setCampaignId(value1);

				Method method2 = oldModelClass.getMethod("getContactId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setContactId(value2);

				Method method3 = oldModelClass.getMethod("getSent");

				Boolean value3 = (Boolean)method3.invoke(oldModel,
						(Object[])null);

				newModel.setSent(value3);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static ClassLoader _classLoader;
	private static String _servletContextName;
}