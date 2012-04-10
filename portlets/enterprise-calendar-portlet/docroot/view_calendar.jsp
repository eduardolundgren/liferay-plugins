<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
--%>

<%@ include file="/init.jsp" %>

<%
CalendarResource groupCalendarResource = CalendarResourceUtil.fetchOrCreateGroupResource(request, scopeGroupId);

CalendarResource userCalendarResource = null;

if (themeDisplay.isSignedIn()) {
	userCalendarResource = CalendarResourceUtil.fetchOrCreateUserResource(request, themeDisplay.getUserId());
}

List<Calendar> groupCalendars = null;

if (groupCalendarResource != null) {
	groupCalendars = CalendarServiceUtil.search(themeDisplay.getCompanyId(), null, new long[] {groupCalendarResource.getCalendarResourceId()}, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, (OrderByComparator)null);
}

List<Calendar> userCalendars = null;

if (userCalendarResource != null) {
	userCalendars = CalendarServiceUtil.search(themeDisplay.getCompanyId(), null, new long[] {userCalendarResource.getCalendarResourceId()}, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, (OrderByComparator)null);
}

JSONArray groupCalendarsJSON = CalendarUtil.toJSON(groupCalendars, locale);
JSONArray userCalendarsJSON = CalendarUtil.toJSON(userCalendars, locale);
%>

<aui:fieldset cssClass="calendar-portlet-column-parent">
	<aui:column cssClass="calendar-portlet-column-options">
		<div id="<portlet:namespace />calendarListContainer">
			<a class="calendar-portlet-list-header aui-toggler-header-expanded" href="javascript:void(0);">
				<span class="calendar-portlet-list-arrow"></span>
				<span class="calendar-portlet-list-text"><liferay-ui:message key="my-calendars" /></span>
				<span class="aui-calendar-list-item-arrow" tabindex="0"></span>
			</a>
			<div class="calendar-portlet-calendar-list" id="<portlet:namespace />myCalendarList"></div>

			<a class="calendar-portlet-list-header aui-toggler-header-expanded" href="javascript:void(0);">
				<span class="calendar-portlet-list-arrow"></span>
				<span class="calendar-portlet-list-text"><liferay-ui:message key="other-calendars" /></span>
				<span class="aui-calendar-list-item-arrow" tabindex="0"></span>
			</a>
			<div class="calendar-portlet-calendar-list" id="<portlet:namespace />otherCalendarList">
				<input class="calendar-portlet-list-input-text" id="<portlet:namespace />addOtherCalendar" placeholder="<liferay-ui:message key="add-other-calendars" />" type="text" />
			</div>

			<c:if test="<%= groupCalendarResource != null %>">
				<a class="calendar-portlet-list-header aui-toggler-header-expanded" href="javascript:void(0);">
					<span class="calendar-portlet-list-arrow"></span>
					<span class="calendar-portlet-list-text"><%= LanguageUtil.format(pageContext, "x-calendars", groupCalendarResource.getName(locale)) %></span>
					<span class="aui-calendar-list-item-arrow" tabindex="0"></span>
				</a>
				<div class="calendar-portlet-calendar-list" id="<portlet:namespace />siteCalendarList"></div>
			</c:if>
		</div>
	</aui:column>

	<aui:column columnWidth="100">
		<div class="calendar-portlet-wrapper" id="<portlet:namespace />scheduler"></div>
	</aui:column>
</aui:fieldset>

<script id="<portlet:namespace />eventRecorderTpl" type="text/x-alloy-template">
	<%@ include file="/event_recorder.jspf" %>
</script>

<button id="serviceTest">Service</button>

<aui:script use="aui-scheduler,aui-toggler,liferay-calendar-list,liferay-calendar-simple-menu,liferay-calendar-simple-color-picker,json">

	A.one('#serviceTest').on('click', function(e) {
		var service1 = [{
			"$user[defaultUser,greeting] = /user/get-user-by-id": {
				"userId": 10158,
			}
		},
		{
			"$user[screenName,emailAddress] = /user/get-user-by-id": {
				"userId": 10196,
				"$group[active,friendlyURL] = /group/get-user-group": {
					"@userId": "$user.userId"
				}
			}
		}];

		var service2 = [
			{
				"$var = /enterprise-calendar-portlet/calendarresource/get-calendar-resource": {
					"calendarResourceId": 10701
				}
			},
			{
				"$user[defaultUser,greeting] = /user/get-user-by-id": {
					"userId": 10158,
				}
			}
		];

		// console.log(service1);
		console.log('IGOR', service2);

		// A.io('http://localhost:8080/api/secure/jsonws/invoke', { method: 'post', data: { cmd: JSON.stringify(service1) } });
		A.io('http://localhost:8080/api/secure/jsonws/invoke', { method: 'post', data: { cmd: JSON.stringify(service2) } });
	});

	/* Calendar list */

	window.<portlet:namespace />myCalendarList = new Liferay.CalendarList(
		{
			boundingBox: '#<portlet:namespace />myCalendarList',
			calendars: <%= userCalendarsJSON %>,
			simpleMenu: {
				items: [
					{ id: 'm1', caption: 'Display only this Calendar', fn: function(event) { this.set('visible', false); } },
					{ id: 'm2', caption: 'Calendar settings', fn: function() { this.set('visible', false); } },
					{ id: 'm3', caption: 'Create event on this calendar', fn: function() { this.set('visible', false); } },
					{ id: 'm6', caption: '-', fn: function() { this.set('visible', false); } },
					{ id: 'm7', caption: '<div id="colorPicker"></div>', fn: function() { } }
				]
			}
		}
	).render();

	window.<portlet:namespace />otherCalendarList = new Liferay.CalendarList(
		{
			boundingBox: '#<portlet:namespace />otherCalendarList',
			calendars: [ {name:'test'} ]
		}
	).render();

	window.<portlet:namespace />siteCalendarList = new Liferay.CalendarList(
		{
			boundingBox: '#<portlet:namespace />siteCalendarList',
			calendars: <%= groupCalendarsJSON %>,
			simpleMenu: {
				items: [
					{ id: 'm1', caption: 'Display only this Calendar', fn: function(event) { this.set('visible', false); } },
					{ id: 'm2', caption: 'Calendar settings', fn: function() { this.set('visible', false); } },
					{ id: 'm3', caption: 'Create event on this calendar', fn: function() { this.set('visible', false); } },
					{ id: 'm4', caption: 'Share this calendar', fn: function() { this.set('visible', false); } },
					{ id: 'm5', caption: 'Notifications', fn: function() { this.set('visible', false); } },
					{ id: 'm6', caption: '-', fn: function() { this.set('visible', false); } },
					{ id: 'm7', caption: '<div id="colorPicker"></div>', fn: function() { } }
				]
			}
		}
	).render();

	/* Scheduler */

	window.<portlet:namespace />dayView = new A.SchedulerDayView({
		headerDateFormat: '<%= dayViewHeaderDateFormat %>',
		height: 700,
		isoTime: <%= isoTimeFormat %>,
		navigationDateFormat: '<%= navigationHeaderDateFormat %>'
	});

	window.<portlet:namespace />weekView = new A.SchedulerWeekView({
		headerDateFormat: '<%= dayViewHeaderDateFormat %>',
		height: 700,
		isoTime: <%= isoTimeFormat %>,
		navigationDateFormat: '<%= navigationHeaderDateFormat %>'
	});

	window.<portlet:namespace />monthView = new A.SchedulerMonthView({
		height: 700,
		navigationDateFormat: '<%= navigationHeaderDateFormat %>'
	});

	window.<portlet:namespace />recorder = new A.SchedulerEventRecorder({
		duration: 30,
		template: new A.Template(A.one('#<portlet:namespace />eventRecorderTpl').text())
	});

	var calendars = [];
	calendars = calendars.concat(window.<portlet:namespace />myCalendarList.get('calendars'));
	calendars = calendars.concat(window.<portlet:namespace />otherCalendarList.get('calendars'));
	calendars = calendars.concat(window.<portlet:namespace />siteCalendarList.get('calendars'));

	window.<portlet:namespace />scheduler = new A.Scheduler(
		{
			boundingBox: '#<portlet:namespace />scheduler',
			eventRecorder: window.<portlet:namespace />recorder,
			events: calendars,
			render: true,
			views: [
				window.<portlet:namespace />weekView,
				window.<portlet:namespace />dayView,
				window.<portlet:namespace />monthView
			]
		}
	);

	/* Toggler */

	window.<portlet:namespace />toggler = new A.TogglerDelegate({
		animated: true,
		container: '#<portlet:namespace />calendarListContainer',
		content: '.calendar-portlet-calendar-list',
		header: '.calendar-portlet-list-header'
	});
</aui:script>