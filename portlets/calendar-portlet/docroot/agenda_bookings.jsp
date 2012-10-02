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

java.util.Calendar startDateCalendar = JCalendarUtil.getJCalendar(ParamUtil.getLong(renderRequest, "startDate"), timeZone);
startDateCalendar = JCalendarUtil.toMidnightJCalendar(startDateCalendar);
java.util.Calendar endDateCalendar = JCalendarUtil.getJCalendar(ParamUtil.getLong(renderRequest, "endDate"), timeZone);
endDateCalendar = JCalendarUtil.toLastHourJCalendar(endDateCalendar);

long[] calendarIds = ParamUtil.getLongValues(renderRequest, "calendarIds");

boolean checkPendingRequests = ParamUtil.getBoolean(renderRequest, "checkPendingRequests");

String keywords = ParamUtil.getString(renderRequest, "keywords");

int[] statuses;
if (checkPendingRequests) {
	statuses = new int[] { CalendarBookingWorkflowConstants.STATUS_PENDING };
}
else {
	statuses = new int[] { CalendarBookingWorkflowConstants.STATUS_APPROVED, CalendarBookingWorkflowConstants.STATUS_PENDING, CalendarBookingWorkflowConstants.STATUS_MAYBE };
}

List<CalendarBooking> bookings = CalendarBookingServiceUtil.search(
	themeDisplay.getCompanyId(), new long[] {0, company.getGroup().getGroupId(), themeDisplay.getScopeGroupId()},
	calendarIds, new long[0], -1, keywords, startDateCalendar.getTimeInMillis(), endDateCalendar.getTimeInMillis(), true,
	statuses, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new CalendarBookingStartDateComparator(true));

bookings = ListUtil.sort(bookings, new CalendarBookingStartDateComparator(true));
%>
<liferay-portlet:renderURL var="backURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
	<liferay-portlet:param name="tabs1" value="agenda" />
	<liferay-portlet:param name="startDate" value="<%= String.valueOf(startDateCalendar.getTimeInMillis()) %>" />
	<liferay-portlet:param name="endDate" value="<%= String.valueOf(endDateCalendar.getTimeInMillis()) %>" />
	<liferay-portlet:param name="checkPendingRequests" value="<%= String.valueOf(checkPendingRequests) %>" />
	<liferay-portlet:param name="keywords" value="<%= keywords %>" />
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
	<aui:button href='<%= createBookingURL %>' name='new-calendar-booking' value='<%= LanguageUtil.get(pageContext, "new-calendar-booking") %>' />
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

		<liferay-ui:search-container-column-text buffer="buffer" cssClass="agenda-row-date aui-w10" name="date" valign="top" >
			<%
			String eventDate = dateFormatLongDate.format(calendarBooking.getStartDate());

			if (!eventDate.equals(previousDate)) {
				buffer.append(eventDate);
			}

			previousDate = eventDate;
			%>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text buffer="buffer" cssClass="aui-w10" name="time" valign="top">
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
		<liferay-ui:search-container-column-text name="event" valign="top">
			<%
			Calendar calendar = CalendarLocalServiceUtil.getCalendar(calendarBooking.getCalendarId());
			String calendarColor = GetterUtil.getString(SessionClicks.get(request, "calendar-portlet-calendar-" + calendar.getCalendarId() + "-color", ColorUtil.toHexString(calendar.getColor())));

			List<CalendarBooking> acceptedCalendarBookings = CalendarBookingServiceUtil.getChildCalendarBookings(calendarBooking.getParentCalendarBookingId(), CalendarBookingWorkflowConstants.STATUS_APPROVED);
			%>
			<liferay-util:buffer var="htmlTitle">
				<span style="color:<%= calendarColor %>;">
					<%= calendarBooking.getTitle(locale) %>
				</span>

				<c:if test="<%= ((calendarBooking.getFirstReminder() > 0) || (calendarBooking.getSecondReminder() > 0)) && CalendarPermission.contains(permissionChecker, calendar, ActionKeys.VIEW_BOOKING_DETAILS) %>">
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
						localizeMessage="<%= false %>"
						message="<%= reminderMessage %>"
						src="<%= iconPath %>"
					/>
				</c:if>
			</liferay-util:buffer>

			<c:choose>
				<c:when test="<%= !CalendarPermission.contains(permissionChecker, calendar, ActionKeys.VIEW_BOOKING_DETAILS) %>">
					<p class="lfr-panel-titlebar"><span class="lfr-panel-title"><%= htmlTitle %></span></p>
				</c:when>
				<c:otherwise>
					<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" id='<%= "agenda-panel-id-" + String.valueOf(row.getPos()) %>' title="<%= htmlTitle %>">
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
							<c:if test="<%= (acceptedCalendarBookings != null) && (!acceptedCalendarBookings.isEmpty()) %>">
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

							<portlet:renderURL var="viewCalendarBookingURL" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
								<liferay-portlet:param name="mvcPath" value="/view_calendar_booking.jsp"/>
								<liferay-portlet:param name="calendarBookingId" value="<%= String.valueOf(calendarBooking.getCalendarBookingId()) %>"/>
								<liferay-portlet:param name="redirect" value="<%= backURL %>" />
							</portlet:renderURL>

							<aui:a href="<%= viewCalendarBookingURL %>" label="view-more" />
						</div>
					</liferay-ui:panel>
				</c:otherwise>
			</c:choose>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-jsp align="top" cssClass="aui-w10" path="/calendar_booking_action.jsp" />
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator paginate="<%= false %>" />
</liferay-ui:search-container>