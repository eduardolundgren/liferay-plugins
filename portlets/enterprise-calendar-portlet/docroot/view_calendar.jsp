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
List<Calendar> groupCalendars = null;

if (groupCalendarResource != null) {
	groupCalendars = CalendarServiceUtil.search(themeDisplay.getCompanyId(), null, new long[] {groupCalendarResource.getCalendarResourceId()}, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, (OrderByComparator)null);
}

List<Calendar> userCalendars = null;

if (userCalendarResource != null) {
	userCalendars = CalendarServiceUtil.search(themeDisplay.getCompanyId(), null, new long[] {userCalendarResource.getCalendarResourceId()}, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, (OrderByComparator)null);
}

List<Calendar> otherCalendars = new ArrayList<Calendar>();

long[] calendarIds = StringUtil.split(SessionClicks.get(request, "otherCalendars", StringPool.BLANK), 0L);

for (long calendarId : calendarIds) {
	Calendar calendar = CalendarServiceUtil.fetchCalendar(calendarId);

	if (calendar != null) {
		otherCalendars.add(calendar);
	}
}

JSONArray groupCalendarsJSON = CalendarUtil.toCalendarsJSON(groupCalendars, locale);
JSONArray userCalendarsJSON = CalendarUtil.toCalendarsJSON(userCalendars, locale);
JSONArray otherCalendarsJSON = CalendarUtil.toCalendarsJSON(otherCalendars, locale);
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
				<input class="calendar-portlet-add-calendars-input" id="<portlet:namespace />addOtherCalendar" placeholder="<liferay-ui:message key="add-other-calendars" />" type="text" />
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

		<div id="<portlet:namespace />message"></div>
	</aui:column>

	<aui:column columnWidth="100">
		<div class="calendar-portlet-wrapper" id="<portlet:namespace />scheduler"></div>
	</aui:column>
</aui:fieldset>

<script id="<portlet:namespace />eventRecorderTpl" type="text/x-alloy-template">
	<%@ include file="/event_recorder.jspf" %>
</script>

<aui:script use="aui-toggler,liferay-calendar-list,liferay-calendar-simple-menu,liferay-calendar-simple-color-picker,liferay-scheduler,liferay-store,json">
	Liferay.CalendarUtil.DEFAULT_CALENDAR = <%= CalendarUtil.toCalendarJSON(userCalendars.get(0), locale) %>;
	Liferay.CalendarUtil.PORTLET_NAMESPACE = '<portlet:namespace />';
	Liferay.CalendarUtil.USER_TIMEZONE_OFFSET = <%= CalendarUtil.getTimeZoneOffset(timeZone) %>;

	var syncVisibleCalendarsMap = function() {
		Liferay.CalendarUtil.syncVisibleCalendarsMap(
			window.<portlet:namespace />myCalendarList,
			window.<portlet:namespace />otherCalendarList,
			window.<portlet:namespace />siteCalendarList
		);
	}

	window.<portlet:namespace />myCalendarList = new Liferay.CalendarList(
		{
			after: {
				calendarsChange: syncVisibleCalendarsMap
			},
			boundingBox: '#<portlet:namespace />myCalendarList',
			calendars: <%= userCalendarsJSON %>
		}
	).render();

	window.<portlet:namespace />otherCalendarList = new Liferay.CalendarList(
		{
			after: {
				calendarsChange: function(event) {
					syncVisibleCalendarsMap();
					window.<portlet:namespace />scheduler.loadCalendarBookings();

					var calendarIds = A.Array.invoke(event.newVal, 'get', 'calendarId');

					Liferay.Store('otherCalendars', calendarIds.join());
				}
			},
			boundingBox: '#<portlet:namespace />otherCalendarList',

			<%
			for (int i = 0; i < otherCalendarsJSON.length(); i++) {
				JSONObject jsonObject = otherCalendarsJSON.getJSONObject(i);

				long calendarId = jsonObject.getLong("calendarId");

				jsonObject.put("visible", GetterUtil.getBoolean(SessionClicks.get(request, "calendar" + calendarId, "true")));
			}
			%>

			calendars: <%= otherCalendarsJSON.toString() %>,
			simpleMenu: {
				items: [
					{
						caption: '<liferay-ui:message key="remove" />',
						fn: function(event) {
							var instance = this;

							instance.set('visible', false);

							<portlet:namespace />otherCalendarList.remove(<portlet:namespace />otherCalendarList.activeItem);
						}
					}
				]
			}
		}
	).render();

	window.<portlet:namespace />siteCalendarList = new Liferay.CalendarList(
		{
			after: {
				calendarsChange: syncVisibleCalendarsMap
			},
			boundingBox: '#<portlet:namespace />siteCalendarList',
			calendars: <%= groupCalendarsJSON %>
		}
	).render();

	syncVisibleCalendarsMap();

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

	<portlet:renderURL var="editCalendarBookingURL">
		<portlet:param name="jspPage" value="/edit_calendar_booking.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="calendarBookingId" value="{calendarBookingId}" />
		<portlet:param name="calendarId" value="{calendarId}" />
		<portlet:param name="startDate" value="{startDate}" />
		<portlet:param name="endDate" value="{endDate}" />
		<portlet:param name="titleCurrentValue" value="{titleCurrentValue}" />
	</portlet:renderURL>

	window.<portlet:namespace />recorder = new Liferay.SchedulerEventRecorder({
		duration: 30,
		editCalendarBookingURL: '<%= editCalendarBookingURL %>',
		portletNamespace: '<portlet:namespace />',
		template: new A.Template(A.one('#<portlet:namespace />eventRecorderTpl').text())
	});

	window.<portlet:namespace />scheduler = new Liferay.Scheduler(
		{
			boundingBox: '#<portlet:namespace />scheduler',
			eventClass: Liferay.SchedulerEvent,
			eventRecorder: window.<portlet:namespace />recorder,
			events: A.Object.values(Liferay.CalendarUtil.visibleCalendars),
			portletNamespace: '<portlet:namespace />',
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

	/* Auto Complete */

	<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="calendarResources" var="calendarResourcesURL"></liferay-portlet:resourceURL>

	var addOtherCalendarInput = A.one('#<portlet:namespace />addOtherCalendar');

	Liferay.CalendarUtil.createCalendarListAutoComplete('<%= calendarResourcesURL %>', <portlet:namespace />otherCalendarList, addOtherCalendarInput);
</aui:script>