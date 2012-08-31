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

long currentDate = ParamUtil.getLong(request, "currentDate", now.getTimeInMillis());

java.util.Calendar startDateJCalendar = JCalendarUtil.getJCalendar(currentDate, timeZone);

java.util.Calendar endDateJCalendar = JCalendarUtil.getJCalendar(currentDate, timeZone);
endDateJCalendar.add(java.util.Calendar.WEEK_OF_MONTH, 1);

int startDateDay = ParamUtil.getInteger(renderRequest, "startDateDay", startDateJCalendar.get(java.util.Calendar.DAY_OF_MONTH));
int startDateMonth = ParamUtil.getInteger(renderRequest, "startDateMonth", startDateJCalendar.get(java.util.Calendar.MONTH));
int startDateYear = ParamUtil.getInteger(renderRequest, "startDateYear", startDateJCalendar.get(java.util.Calendar.YEAR));

int endDateDay = ParamUtil.getInteger(renderRequest, "endDateDay", endDateJCalendar.get(java.util.Calendar.DAY_OF_MONTH));
int endDateMonth = ParamUtil.getInteger(renderRequest, "endDateMonth", endDateJCalendar.get(java.util.Calendar.MONTH));
int endDateYear = ParamUtil.getInteger(renderRequest, "endDateYear", endDateJCalendar.get(java.util.Calendar.YEAR));

String keywords = ParamUtil.getString(renderRequest, "keywords");

boolean checkPendingRequests = ParamUtil.getBoolean(renderRequest, "checkPendingRequests");

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
	Calendar calendar = CalendarLocalServiceUtil.fetchCalendar(calendarId);

	if ((calendar != null) && (CalendarPermission.contains(permissionChecker, calendar, ActionKeys.VIEW))) {
		otherCalendars.add(calendar);
	}
}

JSONArray groupCalendarsJSONArray = CalendarUtil.toCalendarsJSONArray(themeDisplay, groupCalendars);
JSONArray userCalendarsJSONArray = CalendarUtil.toCalendarsJSONArray(themeDisplay, userCalendars);
JSONArray otherCalendarsJSONArray = CalendarUtil.toCalendarsJSONArray(themeDisplay, otherCalendars);
%>

<aui:fieldset cssClass="calendar-portlet-column-parent">
	<aui:column cssClass="calendar-portlet-column-options">
		<div class="calendar-portlet-mini-calendar" id="<portlet:namespace />miniCalendarContainer"></div>

		<div id="<portlet:namespace />calendarListContainer">
			<a class="aui-toggler-header-expanded calendar-portlet-list-header" href="javascript:void(0);">
				<span class="calendar-portlet-list-arrow"></span>

				<span class="calendar-portlet-list-text"><liferay-ui:message key="my-calendars" /></span>

				<c:if test="<%= userCalendarResource != null %>">
					<span class="aui-calendar-list-item-arrow" data-calendarResourceId="<%= userCalendarResource.getCalendarResourceId() %>" tabindex="0"></span>
				</c:if>
			</a>

			<div class="calendar-portlet-calendar-list" id="<portlet:namespace />myCalendarList"></div>

			<a class="calendar-portlet-list-header aui-toggler-header-expanded" href="javascript:void(0);">
				<span class="calendar-portlet-list-arrow"></span>

				<span class="calendar-portlet-list-text"><liferay-ui:message key="other-calendars" /></span>
			</a>

			<div class="calendar-portlet-calendar-list" id="<portlet:namespace />otherCalendarList">
				<input class="calendar-portlet-add-calendars-input" id="<portlet:namespace />addOtherCalendar" placeholder="<liferay-ui:message key="add-other-calendars" />" type="text" />
			</div>

			<c:if test="<%= groupCalendarResource != null %>">
				<a class="aui-toggler-header-expanded calendar-portlet-list-header" href="javascript:void(0);">
					<span class="calendar-portlet-list-arrow"></span>

					<span class="calendar-portlet-list-text"><liferay-ui:message key="current-site-calendars" /></span>

					<c:if test="<%= CalendarResourcePermission.contains(permissionChecker, groupCalendarResource, ActionKeys.VIEW) %>">
						<span class="aui-calendar-list-item-arrow" data-calendarResourceId="<%= groupCalendarResource.getCalendarResourceId() %>" tabindex="0"></span>
					</c:if>
				</a>

				<div class="calendar-portlet-calendar-list" id="<portlet:namespace />siteCalendarList"></div>
			</c:if>
		</div>

		<div id="<portlet:namespace />message"></div>
	</aui:column>

	<aui:column columnWidth="100">
		<liferay-portlet:renderURL varImpl="changeDateRangeURL" />

		<aui:form action="" method="GET" onSubmit="event.preventDefault();" cssClass="agenda-search-form">
			<liferay-portlet:renderURLParams varImpl="changeDateRangeURL" />

			<aui:fieldset cssClass="calendar-portlet-date-filtes">
				<aui:column cssClass="aui-w70">
					<aui:column>

						<div id="<portlet:namespace />startDateWrapper">
							<liferay-ui:input-date
								dayParam="startDateDay"
								dayValue="<%= startDateDay %>"
								disabled="<%= false %>"
								firstDayOfWeek="<%= startDateJCalendar.getFirstDayOfWeek() - 1 %>"
								monthParam="startDateMonth"
								monthValue="<%= startDateMonth %>"
								yearParam="startDateYear"
								yearRangeEnd="<%= startDateJCalendar.get(java.util.Calendar.YEAR) + 100 %>"
								yearRangeStart="<%= startDateJCalendar.get(java.util.Calendar.YEAR) - 100 %>"
								yearValue="<%= startDateYear %>"
							/>
						</div>

					</aui:column>
					<aui:column>
						-
					</aui:column>
					<aui:column>

						<div id="<portlet:namespace />endDateWrapper">
							<liferay-ui:input-date
								dayParam="endDateDay"
								dayValue="<%= endDateDay %>"
								disabled="<%= false %>"
								firstDayOfWeek="<%= endDateJCalendar.getFirstDayOfWeek() - 1 %>"
								monthParam="endDateMonth"
								monthValue="<%= endDateMonth %>"
								yearParam="endDateYear"
								yearRangeEnd="<%= endDateJCalendar.get(java.util.Calendar.YEAR) + 100 %>"
								yearRangeStart="<%= endDateJCalendar.get(java.util.Calendar.YEAR) - 100 %>"
								yearValue="<%= endDateYear %>"
							/>
						</div>

					</aui:column>

					<aui:column cssClass="search-inline-field">
						<aui:input id="checkPendingRequests" name="checkPendingRequests" type="checkbox" value="<%= checkPendingRequests %>" />
					</aui:column>
				</aui:column>

				<aui:column cssClass="aui-w30 right-field search-inline-field">
					<aui:input id="keywords" inlineLabel="left" label="keywords" name="keywords" type="text" value="<%= keywords %>" />
				</aui:column>
			</aui:fieldset>
		</aui:form>

		<div id="agenda-events">
		</div>
	</aui:column>
</aui:fieldset>

<liferay-portlet:renderURL var="getAgendaBookingsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<liferay-portlet:param name="mvcPath" value="/agenda_bookings.jsp" />
</liferay-portlet:renderURL>

<%@ include file="/view_calendar_menus.jspf" %>

<aui:script use="aui-base,aui-toggler,liferay-calendar-list,liferay-scheduler,liferay-store,json,widget,aui-io-plugin">
	var startDateNode = A.one('#<portlet:namespace />startDateWrapper .aui-datepicker-display');
	var endDateNode = A.one('#<portlet:namespace />endDateWrapper .aui-datepicker-display');

	var eventsContainer = A.one("#p_p_id<portlet:namespace /> #agenda-events");

	function <portlet:namespace />getCalendarIds(calendarList) {

		var calendars = calendarList.get("calendars");
		var calendarIds = Array();

		var calendar;
		for (var i=0; i < calendars.length; i++) {
			calendar = calendars[i];
			if (calendar.get("visible")) {
				calendarIds.push(calendar.get("calendarId"));
			}
		}

		return calendarIds;
	}

	var updateAgenda = function updateAgenda() {

		var myCalendarIds = <portlet:namespace />getCalendarIds(window.<portlet:namespace />myCalendarList);
		var siteCalendarIds = <portlet:namespace />getCalendarIds(window.<portlet:namespace />siteCalendarList);
		var otherCalendarIds = <portlet:namespace />getCalendarIds(window.<portlet:namespace />otherCalendarList);
		var calendarIds = myCalendarIds.concat(siteCalendarIds).concat(otherCalendarIds);

		var options = {
				<portlet:namespace />startDateDay: A.one("#<portlet:namespace />startDateDay").val(),
				<portlet:namespace />startDateMonth: parseInt(A.one("#<portlet:namespace />startDateMonth").val()) + 1,
				<portlet:namespace />startDateYear: A.one("#<portlet:namespace />startDateYear").val(),
				<portlet:namespace />endDateDay: A.one("#<portlet:namespace />endDateDay").val(),
				<portlet:namespace />endDateMonth: parseInt(A.one("#<portlet:namespace />endDateMonth").val()) + 1,
				<portlet:namespace />endDateYear: A.one("#<portlet:namespace />endDateYear").val(),
				<portlet:namespace />calendarIds: calendarIds,
				<portlet:namespace />checkPendingRequests: A.one("#<portlet:namespace />checkPendingRequests").val(),
				<portlet:namespace />keywords: A.one("#<portlet:namespace />keywords").val()
			}

		if (!eventsContainer.io) {
			eventsContainer.plug(
				A.Plugin.IO,
				{
					data: options,
					showLoading: true,
					uri: '<%= getAgendaBookingsURL %>'
				}
			);
		}
		else {
			eventsContainer.io.set('data', options)
			eventsContainer.io.start();
		}
	}

	var actOnChange = A.debounce(
		updateAgenda,
		1000
	);

	A.each(
		[startDateNode, endDateNode],
		function (target) {
			target.onceAfter(
				[ 'click', 'mousemove' ],
				function () {
					var datePicker = A.Widget.getByNode(target);

					datePicker.on(
						'calendar:select',
						actOnChange
					);
				}
			);
		}
	);

	A.one("#<portlet:namespace />checkPendingRequestsCheckbox").on(
		'change',
		actOnChange
	);

	var searchKeyworsText = '<%= keywords %>';
	A.one("#<portlet:namespace />keywords").on(
		['change', 'keyup', 'keypress'],
		function(e) {

			var keywords = e.target.val();

			if (keywords === searchKeyworsText) {
				return;
			}

			actOnChange();

			searchKeyworsText = keywords;
		}
	);

	Liferay.CalendarUtil.RENDERING_RULES_URL = '<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="calendarRenderingRules" />';

	<c:if test="<%= userCalendars != null %>">
		Liferay.CalendarUtil.DEFAULT_CALENDAR = <%= CalendarUtil.toCalendarJSONObject(themeDisplay, userCalendars.get(0)) %>;
	</c:if>

	var syncCalendarsMap = function() {
		Liferay.CalendarUtil.syncCalendarsMap(
			window.<portlet:namespace />myCalendarList,
			window.<portlet:namespace />otherCalendarList,
			window.<portlet:namespace />siteCalendarList
		);
	}

	window.<portlet:namespace />myCalendarList = new Liferay.CalendarList(
		{
			after: {
				calendarsChange: syncCalendarsMap,
				'scheduler-calendar:visibleChange': function(event) {
					syncCalendarsMap();

					<portlet:namespace />refreshVisibleCalendarRenderingRules();
				}
			},
			boundingBox: '#<portlet:namespace />myCalendarList',

			<%
			updateCalendarsJSONArray(request, userCalendarsJSONArray);
			%>

			calendars: <%= userCalendarsJSONArray %>,
			simpleMenu: window.<portlet:namespace />calendarsMenu
		}
	).render();

	window.<portlet:namespace />otherCalendarList = new Liferay.CalendarList(
		{
			after: {
				calendarsChange: function(event) {
					syncCalendarsMap();

					var calendarIds = A.Array.invoke(event.newVal, 'get', 'calendarId');

					Liferay.Store('otherCalendars', calendarIds.join());
				}
			},
			boundingBox: '#<portlet:namespace />otherCalendarList',

			<%
			updateCalendarsJSONArray(request, otherCalendarsJSONArray);
			%>

			calendars: <%= otherCalendarsJSONArray %>,
			simpleMenu: window.<portlet:namespace />calendarsMenu
		}
	).render();

	window.<portlet:namespace />siteCalendarList = new Liferay.CalendarList(
		{
			after: {
				calendarsChange: syncCalendarsMap
			},
			boundingBox: '#<portlet:namespace />siteCalendarList',

			<%
			updateCalendarsJSONArray(request, groupCalendarsJSONArray);
			%>

			calendars: <%= groupCalendarsJSONArray %>,
			simpleMenu: window.<portlet:namespace />calendarsMenu
		}
	).render();

	syncCalendarsMap();

	window.<portlet:namespace />toggler = new A.TogglerDelegate(
		{
			animated: true,
			container: '#<portlet:namespace />calendarListContainer',
			content: '.calendar-portlet-calendar-list',
			header: '.calendar-portlet-list-header'
		}
	);

	<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="calendarResources" var="calendarResourcesURL" />

	var addOtherCalendarInput = A.one('#<portlet:namespace />addOtherCalendar');

	Liferay.CalendarUtil.createCalendarsAutoComplete(
		'<%= calendarResourcesURL %>',
		addOtherCalendarInput,
		function(event) {
			window.<portlet:namespace />otherCalendarList.add(event.result.raw);

			<portlet:namespace />refreshVisibleCalendarRenderingRules();

			addOtherCalendarInput.val('');
		}
	);

	window.<portlet:namespace />myCalendarList.on(
		'scheduler-calendar:visibleChange',
		A.debounce(updateAgenda, 400)
	);

	window.<portlet:namespace />siteCalendarList.on(
		'scheduler-calendar:visibleChange',
		A.debounce(updateAgenda, 400)
	);

	window.<portlet:namespace />otherCalendarList.on(
		'scheduler-calendar:visibleChange',
		A.debounce(updateAgenda, 400)
	);

	updateAgenda();

	AUI().use('aui-datatype', 'calendar', function(A) {
		var DateMath = A.DataType.DateMath;

		window.<portlet:namespace />refreshVisibleCalendarRenderingRules = function() {
			var miniCalendarStartDate = window.<portlet:namespace />miniCalendar.get('date');
			var miniCalendarEndDate = DateMath.add(miniCalendarStartDate, DateMath.MONTH, 1);

			Liferay.CalendarUtil.getCalendarRenderingRules(
				A.Object.keys(Liferay.CalendarUtil.visibleCalendars),
				miniCalendarStartDate,
				miniCalendarEndDate,
				'busy',
				function(rulesDefinition) {
					window.<portlet:namespace />miniCalendar.set(
						'customRenderer',
						{
							filterFunction: function(date, node, rules) {
								if (rules.indexOf("busy">= 0)) {
									node.addClass("lfr-busy-day");
								}
							},
							rules: rulesDefinition
						}
					);
				}
			);
		};

		window.<portlet:namespace />miniCalendar = new A.Calendar(
			{
				after: {
					dateChange: <portlet:namespace />refreshVisibleCalendarRenderingRules,
					dateClick: function(event) {
						A.one("#<portlet:namespace />startDateDay").val(event.date.getDate());
						A.one("#<portlet:namespace />startDateMonth").val(event.date.getMonth());
						A.one("#<portlet:namespace />startDateYear").val(event.date.getFullYear());

						var endDate = new Date(event.date.getTime() + (7 * 24 * 60 * 60 * 1000));

						A.one("#<portlet:namespace />endDateDay").val(endDate.getDate());
						A.one("#<portlet:namespace />endDateMonth").val(endDate.getMonth());
						A.one("#<portlet:namespace />endDateYear").val(endDate.getFullYear());

						updateAgenda();
					}
				},
				date: new Date(<%= String.valueOf(currentDate) %>),
				locale: 'en'
			}
		).render('#<portlet:namespace />miniCalendarContainer');

		<portlet:namespace />refreshVisibleCalendarRenderingRules();

	});
</aui:script>

<%!
protected void updateCalendarsJSONArray(HttpServletRequest request, JSONArray calendarsJSONArray) {
	for (int i = 0; i < calendarsJSONArray.length(); i++) {
		JSONObject jsonObject = calendarsJSONArray.getJSONObject(i);

		long calendarId = jsonObject.getLong("calendarId");

		jsonObject.put("color", GetterUtil.getString(SessionClicks.get(request, "calendar-portlet-calendar-" + calendarId + "-color", jsonObject.getString("color"))));
		jsonObject.put("visible", GetterUtil.getBoolean(SessionClicks.get(request, "calendar-portlet-calendar-" + calendarId + "-visible", "true")));
	}
}
%>