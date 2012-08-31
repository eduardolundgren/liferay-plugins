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
long calendarBookingId = ParamUtil.getLong(request, "calendarBookingId");
CalendarBooking calendarBooking = CalendarBookingServiceUtil.getCalendarBooking(calendarBookingId);
Calendar calendar = CalendarServiceUtil.getCalendar(calendarBooking.getCalendarId());
String redirect = ParamUtil.getString(request, "redirect");

Date startDate = JCalendarUtil.getJCalendar(calendarBooking.getStartDate(), user.getTimeZone()).getTime();
Date endDate = JCalendarUtil.getJCalendar(calendarBooking.getEndDate(), user.getTimeZone()).getTime();
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= calendarBooking.getTitle(locale) %>'
/>
<aui:layout cssClass="event-view">
	<aui:column columnWidth="<%= 75 %>" cssClass="folder-column folder-column-first" first="<%= true %>">
		<dl class="property-list">
			<dt>
				<liferay-ui:icon
					image="../common/date"
					message=""
				/>
				<span class="aui-field-label-inline-label"><liferay-ui:message key="start-date" /></span>: <%= dateFormatLongDate.format(startDate) %>
				<span class="spacing"> - </span>
				<c:choose>
					<c:when test="<%= calendarBooking.isAllDay() %>">
						<span class="aui-field-label-inline-label"><liferay-ui:message key="all-day-event" /></span>
					</c:when>
					<c:otherwise>
						<liferay-ui:icon
							image="../common/date"
							message=""
						/>
						<span class="aui-field-label-inline-label"><liferay-ui:message key="end-date" /></span>: <%= dateFormatLongDate.format(endDate) %>
					</c:otherwise>
				</c:choose>
			</dt>
			<c:if test="<%= CalendarPermission.contains(permissionChecker, calendar, ActionKeys.VIEW_BOOKING_DETAILS) %>">
				<c:if test="<%= Validator.isNotNull(calendarBooking.getRecurrence()) %>">
					<dt>
						<liferay-ui:icon
							image="../common/time"
							message=""
						/>
						<span class="aui-field-label-inline-label"><liferay-ui:message key="recurrence" /></span>: <%= _getRecurrencyDescription(calendarBooking.getRecurrenceObj(), pageContext, dateFormatLongDate, user.getTimeZone()) %>
					</dt>
				</c:if>
				<dt>
					<span class="aui-field-label-inline-label"><liferay-ui:message key="calendar" /></span>: <%=  HtmlUtil.escape(calendar.getName(locale)) %>
				</dt>
				<dt class="calendar-description">
					<%= HtmlUtil.escape(calendarBooking.getDescription(locale)) %>
				</dt>
				<c:if test="<%= (calendarBooking.getFirstReminder() > 0) && (calendarBooking.getSecondReminder() > 0) %>">
					<dt class="remindes-box">
						<aui:field-wrapper label="reminders">
							<%
							String iconPath = request.getContextPath() + "/images/bell.png";
							%>
							<c:if test="<%= calendarBooking.getFirstReminder() > 0 %>">
								<p>
									<liferay-ui:icon
										message="first-reminder"
										src="<%= iconPath %>"
									/>
									<%= _getReminderDescription(calendarBooking.getFirstReminder(), calendarBooking.getFirstReminderType(), pageContext) %>
								</p>
							</c:if>
							<c:if test="<%= calendarBooking.getSecondReminder() > 0 %>">
								<p>
									<liferay-ui:icon
										message="second-reminder"
										src="<%= iconPath %>"
									/>
									<%= _getReminderDescription(calendarBooking.getSecondReminder(), calendarBooking.getFirstReminderType(), pageContext) %>
								</p>
							</c:if>
						</aui:field-wrapper>
					</dt>
				</c:if>
				<%
				List<CalendarBooking> acceptedCalendarBookings = CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_APPROVED);
				%>
				<c:if test="<%= ((acceptedCalendarBookings != null) && (!acceptedCalendarBookings.isEmpty())) %>">
					<dt>
						<aui:field-wrapper label="accepted-invitation">
							<%
							Calendar acceptedCalendar;
							for (CalendarBooking acceptedCalendarBooking : acceptedCalendarBookings) {
								acceptedCalendar = CalendarServiceUtil.getCalendar(acceptedCalendarBooking.getCalendarId());
							%>
								<div class="aui-calendar-list-item">
									<div class="aui-calendar-list-item-color"
										style="background-color: <%= ColorUtil.toHexString(acceptedCalendar.getColor()) %>; border-color: <%= ColorUtil.toHexString(acceptedCalendar.getColor()) %>; " >
									</div>
									<span class="aui-calendar-list-item-label" ><%= acceptedCalendar.getName(locale) %></span>
								</div>
							<%
							}
							%>
						</aui:field-wrapper>
					</dt>
				</c:if>
				<dt>
					<div class="lfr-asset-categories">
						<liferay-ui:asset-categories-summary
							className="<%= CalendarBooking.class.getName() %>"
							classPK="<%= calendarBookingId %>"
						/>
					</div>

					<div class="lfr-asset-tags">
						<liferay-ui:asset-tags-summary
							className="<%= CalendarBooking.class.getName() %>"
							classPK="<%= calendarBookingId %>"
							message="tags"
						/>
					</div>
				</dt>

				<dt>
					<liferay-ui:custom-attributes-available className="<%= CalendarBooking.class.getName() %>">
						<liferay-ui:custom-attribute-list
							className="<%= CalendarBooking.class.getName() %>"
							classPK="<%= (calendarBooking != null) ? calendarBooking.getCalendarBookingId() : 0 %>"
							editable="<%= false %>"
							label="<%= true %>"
						/>
					</liferay-ui:custom-attributes-available>
				</dt>
			</c:if>
		</dl>
	</aui:column>
	<aui:column columnWidth="<%= 25 %>" cssClass="detail-column detail-column-last" last="<%= true %>">
		<div class="folder-icon">
			<liferay-ui:icon
				cssClass="folder-avatar"
				image="../file_system/large/calendar"
				message=""
			/>

			<div class="event-name">
				<h4><%= HtmlUtil.escape(calendarBooking.getTitle(locale)) %></h4>
			</div>
		</div>

		<%
		request.removeAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
		request.setAttribute(WebKeys.CALENDAR_BOOKING, calendarBooking);
		request.setAttribute("show-expanded-actions", true);
		request.setAttribute("redirect", redirect);
		%>
		<div class="event-action">
			<liferay-util:include page="/calendar_booking_action.jsp" servletContext="<%= application %>" />
		</div>
	</aui:column>
</aui:layout>
<%!
	public static final Map<Frequency, String> INTERVAL_LABELS = new HashMap<Frequency, String>();

	static {
		INTERVAL_LABELS.put(Frequency.DAILY, "days");
		INTERVAL_LABELS.put(Frequency.WEEKLY, "weeks");
		INTERVAL_LABELS.put(Frequency.MONTHLY, "months");
		INTERVAL_LABELS.put(Frequency.YEARLY, "years");
	}

	private static String _getRecurrencyDescription(Recurrence recurrence, PageContext pageContext, Format dateFormat, TimeZone timeZone) {
		StringBuilder sb = new StringBuilder();

		if (recurrence.getInterval() == 1) {
			sb.append(LanguageUtil.get(pageContext, recurrence.getFrequency().toString()));
		}
		else {
			sb.append(LanguageUtil.format(pageContext, "every-x-x", new Object[] {recurrence.getInterval(), INTERVAL_LABELS.get(recurrence.getFrequency())}));
		}

		if ((recurrence.getFrequency().equals(Frequency.WEEKLY)) && (recurrence.getWeekdays().size() > 0)) {
			sb.append(StringPool.SPACE);
			sb.append(LanguageUtil.get(pageContext, "on"));
			sb.append(StringPool.SPACE);

			Weekday weekday;
			for (int i=0; i < recurrence.getWeekdays().size(); i++) {
				weekday = recurrence.getWeekdays().get(i);
				if (i > 0) {
					sb.append(StringPool.COMMA);
					sb.append(StringPool.SPACE);
				}
				sb.append(LanguageUtil.get(pageContext, weekday.name().toLowerCase()));
			}
		}

		if (recurrence.getCount() > 0) {
			sb.append(StringPool.COMMA);
			sb.append(StringPool.SPACE);
			sb.append(LanguageUtil.format(pageContext, "x-times", recurrence.getCount()));
		}
		else if (Validator.isNotNull(recurrence.getUntil())) {
			sb.append(StringPool.COMMA);
			sb.append(StringPool.SPACE);
			Date untilDate = JCalendarUtil.getJCalendar(recurrence.getUntil(), timeZone).getTime();
			sb.append(LanguageUtil.format(pageContext, "until-x", dateFormat.format(untilDate), false));
		}

		return sb.toString();
	}

	private static String _getReminderDescription(long reminderTime, String reminderType, PageContext pageContext) {

		String description = StringPool.BLANK;
		String duration;
		long value;

		if (reminderTime % 604800 == 0) {
			value = reminderTime/604800/1000;
			duration = value > 1 ? "weeks" : "week";
			description = LanguageUtil.format(pageContext, "remind-x-x-before-the-event", new Object[] { value, duration, reminderType});
		}
		else if (reminderTime % 86400 == 0) {
			value = reminderTime/86400/1000;
			duration = value > 1 ? "days" : "day";
			description = LanguageUtil.format(pageContext, "remind-x-x-before-the-event", new Object[] { value, duration, reminderType});
		}
		else if (reminderTime % 3600 == 0) {
			value = reminderTime/3600/1000;
			duration = value > 1 ? "hours" : "hour";
			description = LanguageUtil.format(pageContext, "remind-x-x-before-the-event", new Object[] { value, duration, reminderType});
		}
		else {
			value = reminderTime/60/1000;
			duration = value > 1 ? "minutes" : "minute";
			description = LanguageUtil.format(pageContext, "remind-x-x-before-the-event", new Object[] { value, duration, reminderType});
		}

		return description;
	}
%>