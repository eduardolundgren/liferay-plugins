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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
boolean showExpandedActions = GetterUtil.getBoolean(request.getAttribute("show-expanded-actions"), false);

CalendarBooking calendarBooking = null;

if (row != null) {
	calendarBooking = (CalendarBooking)row.getObject();
}
else {
	calendarBooking = (CalendarBooking)request.getAttribute(WebKeys.CALENDAR_BOOKING);
}

String redirect = (String)renderRequest.getAttribute("redirect");
%>
<liferay-ui:icon-menu showExpanded="<%= showExpandedActions %>" showWhenSingleIcon="<%= showExpandedActions %>">
	<c:if test="<%= CalendarPermission.contains(permissionChecker, calendarBooking.getCalendarId(), ActionKeys.MANAGE_BOOKINGS) %>">
		<portlet:renderURL var="editCalendarBookingURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
			<portlet:param name="mvcPath" value="/edit_calendar_booking.jsp" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="calendarBookingId" value="<%= String.valueOf(calendarBooking.getCalendarBookingId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editCalendarBookingURL %>"
		/>

		<c:choose>
			<c:when test="<%= calendarBooking.isPending() || (calendarBooking.getStatus() == CalendarBookingWorkflowConstants.STATUS_MAYBE) %>">
				<portlet:actionURL name="updateCalendarBookingStatus" var="approveURL">
					<portlet:param name="calendarBookingId" value="<%= String.valueOf(calendarBooking.getCalendarBookingId()) %>"/>
					<portlet:param name="statusName" value="<%= CalendarBookingWorkflowConstants.LABEL_ACCEPTED %>" />
					<portlet:param name="redirect" value="<%= redirect %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					image="check"
					message="accept"
					url="<%= approveURL %>"
				/>

				<portlet:actionURL name="updateCalendarBookingStatus" var="declineURL">
					<portlet:param name="calendarBookingId" value="<%= String.valueOf(calendarBooking.getCalendarBookingId()) %>"/>
					<portlet:param name="statusName" value="<%= CalendarBookingWorkflowConstants.LABEL_DECLINED %>" />
					<portlet:param name="redirect" value="<%= redirect %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					image="close"
					message="decline"
					url="<%= declineURL %>"
				/>

				<c:if test="<%= calendarBooking.getStatus() != CalendarBookingWorkflowConstants.STATUS_MAYBE %>">
					<portlet:actionURL name="updateCalendarBookingStatus" var="maybeURL">
						<portlet:param name="calendarBookingId" value="<%= String.valueOf(calendarBooking.getCalendarBookingId()) %>"/>
						<portlet:param name="statusName" value="<%= CalendarBookingWorkflowConstants.LABEL_MAYBE %>" />
						<portlet:param name="redirect" value="<%= redirect %>" />
					</portlet:actionURL>

					<liferay-ui:icon
						image="help"
						message="maybe"
						url="<%= maybeURL %>"
					/>
				</c:if>
			</c:when>
			<c:otherwise>
				<portlet:actionURL name="deleteCalendarBooking" var="deleteURL">
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
					<portlet:param name="redirect" value="<%= redirect %>" />
					<portlet:param name="calendarBookingId" value="<%= String.valueOf(calendarBooking.getCalendarBookingId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon-delete
					url="<%= deleteURL %>"
				/>
			</c:otherwise>
		</c:choose>
	</c:if>
</liferay-ui:icon-menu>