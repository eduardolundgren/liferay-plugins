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
Format shortTimeFormat = FastDateFormatFactoryUtil.getTime(locale, timeZone);

int startDateDay = ParamUtil.getInteger(renderRequest, "startDateDay");
int startDateMonth = ParamUtil.getInteger(renderRequest, "startDateMonth");
int startDateYear = ParamUtil.getInteger(renderRequest, "startDateYear");

int endDateDay = ParamUtil.getInteger(renderRequest, "endDateDay");
int endDateMonth = ParamUtil.getInteger(renderRequest, "endDateMonth");
int endDateYear = ParamUtil.getInteger(renderRequest, "endDateYear");

long[] calendarIds = ParamUtil.getLongValues(renderRequest, "calendarIds");

boolean checkPendingRequests = ParamUtil.getBoolean(renderRequest, "checkPendingRequests");

java.util.Calendar startDateCalendar = JCalendarUtil.getJCalendar(startDateYear, startDateMonth, startDateDay, 0, 0, 0, 0, user.getTimeZone());
java.util.Calendar endDateCalendar = JCalendarUtil.getJCalendar(endDateYear, endDateMonth, endDateDay, 23, 59, 59, 99, user.getTimeZone());

int[] statuses;
if (checkPendingRequests) {
	statuses = new int[] { CalendarBookingWorkflowConstants.STATUS_PENDING };
}
else {
	statuses = new int[] { CalendarBookingWorkflowConstants.STATUS_APPROVED, CalendarBookingWorkflowConstants.STATUS_PENDING, CalendarBookingWorkflowConstants.STATUS_MAYBE };
}

List<CalendarBooking> bookings = CalendarBookingServiceUtil.search(
	themeDisplay.getCompanyId(), new long[] {0, company.getGroup().getGroupId(), themeDisplay.getScopeGroupId()},
	calendarIds, new long[0], -1, null, startDateCalendar.getTimeInMillis(), endDateCalendar.getTimeInMillis(), true,
	statuses, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

List<String> headers = new ArrayList<String>();
%>
<liferay-portlet:renderURL var="backURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
	<liferay-portlet:param name="tabs1" value="agenda" />
	<liferay-portlet:param name="startDateDay" value="<%= String.valueOf(startDateDay) %>" />
	<liferay-portlet:param name="startDateMonth" value="<%= String.valueOf(startDateMonth) %>" />
	<liferay-portlet:param name="startDateYear" value="<%= String.valueOf(startDateYear) %>" />
	<liferay-portlet:param name="endDateDay" value="<%= String.valueOf(endDateDay) %>" />
	<liferay-portlet:param name="endDateMonth" value="<%= String.valueOf(endDateMonth) %>" />
	<liferay-portlet:param name="endDateYear" value="<%= String.valueOf(endDateYear) %>" />
	<liferay-portlet:param name="checkPendingRequests" value="<%= String.valueOf(checkPendingRequests) %>" />
</liferay-portlet:renderURL>
<%
renderRequest.setAttribute("redirect", backURL);
String previousDate = StringPool.BLANK;
%>

<liferay-portlet:renderURL var="createBookingURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
	<liferay-portlet:param name="mvcPath" value="/edit_calendar_booking.jsp"/>
	<liferay-portlet:param name="redirect" value="<%= backURL %>" />
</liferay-portlet:renderURL>


<aui:button-row>
	<aui:button href='<%= createBookingURL %>' name='create-new-booking' value='<%= LanguageUtil.get(locale, "create-new-booking") %>' />
</aui:button-row>

<liferay-ui:search-container emptyResultsMessage="no-calendar-bookings-were-found">
	<liferay-ui:search-container-results
		results="<%= bookings %>"
		total="<%= bookings.size() %>"
	>
	</liferay-ui:search-container-results>
	<liferay-ui:search-container-row
		className="com.liferay.calendar.model.CalendarBooking"
		keyProperty="calendarBookingId"
		modelVar="calendarBooking">

		<liferay-ui:search-container-column-text buffer="buffer" valign="top" name="date" cssClass="agenda-row-date aui-w10" >
			<%
			String eventDate = dateFormatLongDate.format(calendarBooking.getStartDate());

			if (!eventDate.equals(previousDate)) {
				buffer.append(eventDate);
			}

			previousDate = eventDate;
			%>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text buffer="buffer" valign="top" name="time" cssClass="aui-w10">
			<c:choose>
				<c:when test="<%= calendarBooking.isAllDay() %>">
					<%
					buffer.append(LanguageUtil.get(locale, "all-day"));
					%>
				</c:when>
				<c:otherwise>
					<%
					buffer.append(shortTimeFormat.format(calendarBooking.getStartDate()));
					buffer.append(" - ");
					buffer.append(shortTimeFormat.format(calendarBooking.getEndDate()));
					%>
				</c:otherwise>
			</c:choose>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text valign="top" name="event">
			<%
			Calendar calendar = CalendarLocalServiceUtil.getCalendar(calendarBooking.getCalendarId());
			String calendarColor = GetterUtil.getString(SessionClicks.get(request, "calendar-portlet-calendar-" + calendar.getCalendarId() + "-color", ColorUtil.toHexString(calendar.getColor())));

			List<CalendarBooking> acceptedCalendarBookings = CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_APPROVED);
			%>
			<liferay-util:buffer var="htmlTitle">
				<span style="color:<%= calendarColor %>;">
					<%= calendarBooking.getTitle(locale) %>
				</span>

				<c:if test="<%= ( (calendarBooking.getFirstReminder() > 0) || (calendarBooking.getSecondReminder() > 0) ) %>">
					<%
					String firstReminder = LanguageUtil.getTimeDescription(pageContext, calendarBooking.getFirstReminder());
					String secondReminder = LanguageUtil.getTimeDescription(pageContext, calendarBooking.getSecondReminder());
					String reminderMessage;

					if ((calendarBooking.getFirstReminder() > 0) && (calendarBooking.getSecondReminder() > 0)) {
						reminderMessage = LanguageUtil.format(pageContext, "remind-x-before-and-again-x-before-the-event", new String[] { firstReminder, secondReminder });
					}
					else if (calendarBooking.getFirstReminder() > 0) {
						reminderMessage = LanguageUtil.format(pageContext, "remind-x-before-the-event", new String[] { firstReminder });
					}
					else {
						reminderMessage = LanguageUtil.format(pageContext, "remind-x-before-the-event", new String[] { secondReminder });
					}

					String iconPath = request.getContextPath() + "/images/bell.png";
					%>
					<liferay-ui:icon
						message="<%= reminderMessage %>"
						localizeMessage="<%= false %>"
						src="<%= iconPath %>"
					/>
				</c:if>
			</liferay-util:buffer>

			<liferay-ui:panel defaultState="closed" collapsible="<%= true %>" id='<%= "panelId" + String.valueOf(calendarBooking.getCalendarBookingId()) %>' title="<%= htmlTitle %>">
				<div class="event-details">
					<aui:field-wrapper label="calendar">
						<%= HtmlUtil.escape(calendar.getName(locale)) %>
					</aui:field-wrapper>
					<c:if test="<%= Validator.isNotNull(calendarBooking.getDescription(locale)) %>">
						<aui:field-wrapper label="description">
							<%= HtmlUtil.escape(calendarBooking.getDescription(locale)) %>
						</aui:field-wrapper>
					</c:if>
					<c:if test="<%= Validator.isNotNull(calendarBooking.getLocation()) %>">
						<aui:field-wrapper label="location">
							<%= HtmlUtil.escape(calendarBooking.getLocation()) %>
						</aui:field-wrapper>
					</c:if>
					<c:if test="<%= ((acceptedCalendarBookings != null) && (!acceptedCalendarBookings.isEmpty())) %>">
						<aui:field-wrapper label="accepted-invitation">
							<%
							Calendar acceptedCalendar;
							String acceptedCalendarColor;
							for (CalendarBooking acceptedCalendarBooking : acceptedCalendarBookings) {

								if (!CalendarPermission.contains(themeDisplay.getPermissionChecker(), acceptedCalendarBooking.getCalendarId(), ActionKeys.VIEW)) {
									continue;
								}

								acceptedCalendar = CalendarServiceUtil.getCalendar(acceptedCalendarBooking.getCalendarId());
								acceptedCalendarColor = GetterUtil.getString(SessionClicks.get(request, "calendar-portlet-calendar-" + acceptedCalendar.getCalendarId() + "-color", ColorUtil.toHexString(acceptedCalendar.getColor())));
							%>
								<div class="aui-calendar-list-item">
									<div class="aui-calendar-list-item-color"
										style="background-color: <%= acceptedCalendarColor %>; border-color: <%= acceptedCalendarColor %>;" >
									</div>
									<span class="aui-calendar-list-item-label" ><%= acceptedCalendar.getName(locale) %></span>
								</div>
							<%
							}
							%>
						</aui:field-wrapper>
					</c:if>
				</div>
			</liferay-ui:panel>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-jsp path="/calendar_booking_action.jsp" cssClass="aui-w10" align="top" />
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator paginate="<%= false %>" />
</liferay-ui:search-container>
