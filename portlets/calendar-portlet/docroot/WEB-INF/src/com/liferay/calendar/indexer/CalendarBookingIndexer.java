package com.liferay.calendar.indexer;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.persistence.CalendarBookingActionableDynamicQuery;
import com.liferay.calendar.util.PortletKeys;
import com.liferay.calendar.workflow.CalendarBookingWorkflowConstants;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletURL;

/**
 * @author Adam Victor Brandizzi
 */
public class CalendarBookingIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES =
		{CalendarBooking.class.getName()};

	public static final String PORTLET_ID = PortletKeys.CALENDAR;

	@Override
	public void addRelatedEntryFields(Document document, Object obj) {
		document.addKeyword(Field.RELATED_ENTRY, true);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getPortletId() {
		return PORTLET_ID;
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		CalendarBooking calendarBooking = (CalendarBooking)obj;

		deleteDocument(
				calendarBooking.getCompanyId(),
				calendarBooking.getCalendarBookingId());
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		CalendarBooking calendarBooking = (CalendarBooking)obj;

		Document document = getBaseModelDocument(PORTLET_ID, calendarBooking);
		Locale defaultLocale = LocaleUtil.getSiteDefault();

		addLocalizedFieldText(
			document, Field.DESCRIPTION, calendarBooking.getDescriptionMap(),
			defaultLocale);
		addLocalizedFieldText(
			document, Field.TITLE, calendarBooking.getTitleMap(),
			defaultLocale);
		document.addText("location", calendarBooking.getLocation());

		document.addDate(Field.CREATE_DATE, calendarBooking.getCreateDate());
		document.addDate(
			Field.MODIFIED_DATE, calendarBooking.getModifiedDate());
		document.addDate("startDate", new Date(calendarBooking.getStartTime()));
		document.addDate("endDate", new Date(calendarBooking.getEndTime()));

		document.addKeyword(Field.STATUS, calendarBooking.getStatus());
		document.addKeyword(
			"calendarBookingId", calendarBooking.getCalendarBookingId());

		addLocalizedFieldText(
			document, "calendarName",
			calendarBooking.getCalendar().getNameMap(), defaultLocale);

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletURL portletURL) {

		String title = getLocalizedFieldText(document, Field.TITLE, locale);
		String description = getLocalizedFieldText(
			document, Field.DESCRIPTION, locale);

		if (description.length() > 200) {
			description = StringUtil.shorten(description, 200);
		}

		String calendarBookingId = document.get("calendarBookingId");

		portletURL.setParameter("mvcPath", "/view_calendar_booking.jsp");
		portletURL.setParameter("calendarBookingId", calendarBookingId);

		return new Summary(locale, title, description, portletURL);
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		CalendarBooking calendarBooking = (CalendarBooking)obj;

		Document document = getDocument(calendarBooking);

		SearchEngineUtil.deleteDocument(
			getSearchEngineId(), calendarBooking.getCompanyId(),
			document.get(Field.UID));

		SearchEngineUtil.updateDocument(
				getSearchEngineId(), calendarBooking.getCompanyId(), document);
	}

	@Override
	protected void doReindex(String className, long classPK)
	throws Exception {

		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.getCalendarBooking(classPK);

		doReindex(calendarBooking);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexCalendarBookings(companyId);
	}

	protected String getLocalizedFieldText(
			Document document, String fieldName, Locale defaultLocale) {

		String languageId = defaultLocale.toString();

		return document.get(
				fieldName+StringPool.UNDERLINE+languageId, fieldName);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected void reindexCalendarBookings(long companyId)
	throws PortalException, SystemException {

		final Collection<Document> documents = new ArrayList<Document>();

		ActionableDynamicQuery actionableDynamicQuery =
			new CalendarBookingActionableDynamicQuery() {

			@Override
			protected void addCriteria(DynamicQuery dynamicQuery) {
				Property statusProperty = PropertyFactoryUtil.forName("status");

				Integer[] statuses = {
					CalendarBookingWorkflowConstants.STATUS_APPROVED,
					CalendarBookingWorkflowConstants.STATUS_MAYBE,
					CalendarBookingWorkflowConstants.STATUS_IN_TRASH,
				};

				dynamicQuery.add(statusProperty.in(statuses));
			}

			@Override
			protected void performAction(Object object)
			throws PortalException {

				CalendarBooking calendarBooking = (CalendarBooking)object;

				Document document = getDocument(calendarBooking);

				documents.add(document);
			}

		};

		actionableDynamicQuery.setCompanyId(companyId);

		actionableDynamicQuery.performActions();

		SearchEngineUtil.updateDocuments(
			getSearchEngineId(), companyId, documents);
	}

	private void addLocalizedFieldText(
			Document document, String fieldName, Map<Locale, String> fieldMap,
			Locale defaultLocale) {

		Set<Locale> locales = new HashSet<Locale>(fieldMap.keySet());

		locales.add(defaultLocale);

		for (Locale locale : locales) {
			String languageId = locale.toString();
			String localizedFieldName = fieldName.concat(
				StringPool.UNDERLINE).concat(languageId);
			String fieldValue = fieldMap.get(locale);

			document.addText(localizedFieldName, fieldValue);

			if (locale.equals(defaultLocale)) {
				document.addText(fieldName, fieldValue);
			}
		}
	}

}