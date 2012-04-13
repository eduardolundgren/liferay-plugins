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

<%@ page import="com.liferay.portal.kernel.util.Time" %>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

java.util.Calendar defaultStartDateJCalendar = CalendarFactoryUtil.getCalendar(timeZone);
java.util.Calendar defaultEndDateJCalendar = CalendarFactoryUtil.getCalendar(timeZone);

defaultEndDateJCalendar.add(java.util.Calendar.HOUR, 1);

String title = ParamUtil.getString(request, "title");
long calendarId = ParamUtil.getLong(request, "calendarId", userDefaultCalendar.getCalendarId());
long startDate = ParamUtil.getLong(request, "startDate", defaultStartDateJCalendar.getTimeInMillis());
long endDate = ParamUtil.getLong(request, "endDate", defaultEndDateJCalendar.getTimeInMillis());

java.util.Calendar startDateJCalendar = CalendarFactoryUtil.getCalendar(timeZone, locale);

startDateJCalendar.setTimeInMillis(startDate);

java.util.Calendar endDateJCalendar = CalendarFactoryUtil.getCalendar(timeZone, locale);

endDateJCalendar.setTimeInMillis(endDate);

CalendarBooking calendarBooking = (CalendarBooking)request.getAttribute(WebKeys.CALENDAR_BOOKING);

long calendarBookingId = 0;
int firstReminder = 0;
int secondReminder = 0;

List<CalendarBooking> acceptedCalendarBookings = null;
List<CalendarBooking> declinedCalendarBookings = null;
List<CalendarBooking> pendingCalendarBookings = null;

JSONArray acceptedCalendarsJSONArray = JSONFactoryUtil.createJSONArray();
JSONArray declinedCalendarsJSONArray = JSONFactoryUtil.createJSONArray();
JSONArray pendingCalendarsJSONArray = JSONFactoryUtil.createJSONArray();

if (calendarBooking != null) {
	calendarBookingId = calendarBooking.getCalendarBookingId();
	firstReminder = calendarBooking.getFirstReminder();
	secondReminder = calendarBooking.getSecondReminder();
	title = calendarBooking.getTitle(locale);

	acceptedCalendarBookings = CalendarBookingServiceUtil.getCalendarBookings(calendarBookingId, CalendarBookingWorkflowConstants.STATUS_APPROVED);

	for (CalendarBooking acceptedCalendarBooking : acceptedCalendarBookings) {
		acceptedCalendarsJSONArray.put(CalendarUtil.toJSON(acceptedCalendarBooking.getCalendar(), locale));
	}

	declinedCalendarBookings = CalendarBookingServiceUtil.getCalendarBookings(calendarBookingId, CalendarBookingWorkflowConstants.STATUS_DENIED);

	for (CalendarBooking declinedCalendarBooking : declinedCalendarBookings) {
		declinedCalendarsJSONArray.put(CalendarUtil.toJSON(declinedCalendarBooking.getCalendar(), locale));
	}

	pendingCalendarBookings = CalendarBookingServiceUtil.getCalendarBookings(calendarBookingId, CalendarBookingWorkflowConstants.STATUS_PENDING);

	for (CalendarBooking pendingCalendarBooking : pendingCalendarBookings) {
		pendingCalendarsJSONArray.put(CalendarUtil.toJSON(pendingCalendarBooking.getCalendar(), locale));
	}
}

if ((userDefaultCalendar != null) && (acceptedCalendarsJSONArray.length() == 0)) {
	acceptedCalendarsJSONArray.put(CalendarUtil.toJSON(userDefaultCalendar, locale));
}
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (Validator.isNotNull(title)) ? title : "new-calendar-booking" %>'
/>

<liferay-portlet:actionURL name="updateCalendarBooking" var="updateCalendarBookingURL">
	<liferay-portlet:param name="mvcPath" value="/edit_calendar_booking.jsp" />
	<liferay-portlet:param name="redirect" value="<%= redirect %>" />
	<liferay-portlet:param name="calendarBookingId" value="<%= String.valueOf(calendarBookingId) %>" />
</liferay-portlet:actionURL>

<aui:form action="<%= updateCalendarBookingURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateCalendarBooking();" %>'>
	<aui:model-context bean="<%= calendarBooking %>" model="<%= CalendarBooking.class %>" />

	<aui:input name="calendarId" type="hidden" value="<%= calendarId %>" />
	<aui:input name="resourcesJSON" type="hidden" />

	<aui:fieldset>
		<aui:input name="title" value="<%= title %>" />

		<c:choose>
			<c:when test="<%= calendarBooking != null %>">
				<aui:input name="startDate" />

				<aui:input name="endDate" />
			</c:when>
			<c:otherwise>
				<aui:input name="startDate" value="<%= startDateJCalendar %>" />

				<aui:input name="endDate" value="<%= endDateJCalendar %>" />
			</c:otherwise>
		</c:choose>

		<aui:input name="allDay" />
	</aui:fieldset>

	<aui:fieldset>
		<liferay-ui:tabs
			names="details"
			refresh="<%= false %>"
		>
			<liferay-ui:section>
				<aui:input name="description" />

				<aui:input name="location" />

				<aui:field-wrapper label="first-reminder">

					<%
					String firstReminderDescription = Time.getDescription(firstReminder * Time.MINUTE);

					int firstReminderValue = GetterUtil.getInteger(firstReminderDescription.split(StringPool.SPACE)[0]);
					%>

					<aui:input inlineField="true" label="" name="firstReminderValue" type="text" value="<%= String.valueOf(firstReminderValue) %>" />

					<aui:select inlineField="true" label="" name="firstReminderUnits">
						<aui:option selected='<%= firstReminderDescription.contains("Minute") %>' value="<%= Time.MINUTE %>"><liferay-ui:message key="minutes" /></aui:option>
						<aui:option selected='<%= firstReminderDescription.contains("Hour") %>' value="<%= Time.HOUR %>"><liferay-ui:message key="hours" /></aui:option>
						<aui:option selected='<%= firstReminderDescription.contains("Day") %>' value="<%= Time.DAY %>"><liferay-ui:message key="days" /></aui:option>
						<aui:option selected='<%= firstReminderDescription.contains("Week") %>' value="<%= Time.WEEK %>"><liferay-ui:message key="weeks" /></aui:option>
					</aui:select>
				</aui:field-wrapper>

				<aui:field-wrapper label="second-reminder">

					<%
					String secondReminderDescription = Time.getDescription(secondReminder * Time.MINUTE);

					int secondReminderValue = GetterUtil.getInteger(secondReminderDescription.split(StringPool.SPACE)[0]);
					%>

					<aui:input inlineField="true" label="" name="secondReminderValue" type="text" value="<%= String.valueOf(secondReminderValue) %>" />

					<aui:select inlineField="true" label="" name="secondReminderUnits">
						<aui:option selected='<%= secondReminderDescription.contains("Minute") %>' value="<%= Time.MINUTE %>"><liferay-ui:message key="minutes" /></aui:option>
						<aui:option selected='<%= secondReminderDescription.contains("Hour") %>' value="<%= Time.HOUR %>"><liferay-ui:message key="hours" /></aui:option>
						<aui:option selected='<%= secondReminderDescription.contains("Day") %>' value="<%= Time.DAY %>"><liferay-ui:message key="days" /></aui:option>
						<aui:option selected='<%= secondReminderDescription.contains("Week") %>' value="<%= Time.WEEK %>"><liferay-ui:message key="weeks" /></aui:option>
					</aui:select>
				</aui:field-wrapper>
			</liferay-ui:section>
		</liferay-ui:tabs>

		<liferay-ui:tabs
			names="invitations"
			refresh="<%= false %>"
		>
			<liferay-ui:section>
				<aui:input class="calendar-portlet-list-input-text" label="invite-resource" name="inviteResource" type="text" />

				<div class="separator"><!-- --></div>

				<aui:layout cssClass="calendar-booking-invitations">
					<aui:column columnWidth="33" first="true">
						<label class="aui-field-label"><liferay-ui:message key="pending" /> (<span id="<portlet:namespace />pendingCounter"><%= pendingCalendarsJSONArray.length() %></span>)</label>

						<div class="calendar-portlet-calendar-list" id="<portlet:namespace />calendarListPending"></div>
					</aui:column>
					<aui:column columnWidth="33">
						<label class="aui-field-label"><liferay-ui:message key="accepted" /> (<span id="<portlet:namespace />acceptedCounter"><%= acceptedCalendarsJSONArray.length() %></span>)</label>

						<div class="calendar-portlet-calendar-list" id="<portlet:namespace />calendarListAccepted"></div>
					</aui:column>
					<aui:column columnWidth="33" last="true">
						<label class="aui-field-label"><liferay-ui:message key="declined" /> (<span id="<portlet:namespace />declinedCounter"><%= declinedCalendarsJSONArray.length() %></span>)</label>

						<div class="calendar-portlet-calendar-list" id="<portlet:namespace />calendarListDeclined"></div>
					</aui:column>
				</aui:layout>
			</liferay-ui:section>
		</liferay-ui:tabs>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>

</aui:form>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="calendarResources" var="calendarResourcesURL"></liferay-portlet:resourceURL>

<aui:script use="autocomplete,autocomplete-highlighters,json,liferay-calendar-list,liferay-calendar-simple-menu">
	/* Calendar List */

	var getResourceKey = function(classNameId, classPK) {
		return [classNameId, classPK].join('<%= StringPool.POUND %>');
	}

	var removeResource = function(event) {
		var instance = this;
		var calendarList = instance.calendarList;

		calendarList.remove(calendarList.activeItem);

		instance.set('visible', false);
	}

	var invitedResources = {};

	var updateInvitedResources = function() {
		invitedResources = {};

		var addResource = function(item) {
			var classNameId = item.get('classNameId');
			var classPK = item.get('classPK');

			invitedResources[getResourceKey(classNameId, classPK)] = {
				classNameId: classNameId,
				classPK: classPK
			}
		}

		A.Array.each(window.<portlet:namespace />calendarListAccepted.get('calendars'), addResource);
		A.Array.each(window.<portlet:namespace />calendarListDeclined.get('calendars'), addResource);
		A.Array.each(window.<portlet:namespace />calendarListPending.get('calendars'), addResource);
	}

	window.<portlet:namespace />calendarListPending = new Liferay.CalendarList(
		{
			after: {
				calendarsChange: function(event) {
					updateInvitedResources();

					A.one('#<portlet:namespace />pendingCounter').html(event.newVal.length);
				}
			},
			boundingBox: '#<portlet:namespace />calendarListPending',
			calendars: <%= pendingCalendarsJSONArray %>,
			simpleMenu: {
				items: [
					{ id: 'm1', caption: '<liferay-ui:message key="remove" />', fn: removeResource }
				]
			}
		}
	).render();

	window.<portlet:namespace />calendarListAccepted = new Liferay.CalendarList(
		{
			after: {
				calendarsChange: function(event) {
					updateInvitedResources();

					A.one('#<portlet:namespace />acceptedCounter').html(event.newVal.length);
				}
			},
			boundingBox: '#<portlet:namespace />calendarListAccepted',
			calendars: <%= acceptedCalendarsJSONArray %>,
			simpleMenu: {
				items: [
					{ id: 'm1', caption: '<liferay-ui:message key="remove" />', fn: removeResource }
				]
			}
		}
	).render();

	window.<portlet:namespace />calendarListDeclined = new Liferay.CalendarList(
		{
			after: {
				calendarsChange: function(event) {
					updateInvitedResources();

					A.one('#<portlet:namespace />declinedCounter').html(event.newVal.length);
				}
			},
			boundingBox: '#<portlet:namespace />calendarListDeclined',
			calendars: <%= declinedCalendarsJSONArray %>,
			simpleMenu: {
				items: [
					{ id: 'm1', caption: '<liferay-ui:message key="remove" />', fn: removeResource }
				]
			}
		}
	).render();

	updateInvitedResources();

	/* Auto Complete */

	var inviteResourceInput = A.one('#<portlet:namespace />inviteResource');

	inviteResourceInput.plug(
		A.Plugin.AutoComplete,
		{
			activateFirstItem: true,
			after: {
				select: function(event) {
					<portlet:namespace />calendarListPending.add(event.result.raw);

					inviteResourceInput.val('');
				}
			},
			maxResults: 20,
			requestTemplate: '&<portlet:namespace />keywords={query}',
			resultFilters: function(query, results) {
				return A.Array.filter(
					results,
					function(item) {
						var classNameId = item.raw.classNameId;
						var classPK = item.raw.classPK;

						return !invitedResources[getResourceKey(classNameId, classPK)];
					}
				)
			},
			resultHighlighter: 'wordMatch',
			resultTextLocator: 'name',
			source: '<%= calendarResourcesURL %>'
		}
	);

	window.<portlet:namespace />updateCalendarBooking = function() {
		document.<portlet:namespace />fm.<portlet:namespace />resourcesJSON.value = A.JSON.stringify(invitedResources);

		submitForm(document.<portlet:namespace />fm);
	}

	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />title);
</aui:script>