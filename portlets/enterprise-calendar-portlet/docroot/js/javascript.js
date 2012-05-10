(function() {
	var STR_BLANK = '';
	var STR_DASH = '-';
	var STR_SPACE = ' ';

	var toNumber = function(val) {
		return parseInt(val, 10) || 0;
	};

	var Workflow = Liferay.Workflow;

	var COMPANY_GROUP_ID = toNumber(themeDisplay.getCompanyGroupId());
	var COMPANY_ID = toNumber(themeDisplay.getCompanyId());
	var GROUP_ID = toNumber(themeDisplay.getScopeGroupId());
	var USER_ID = toNumber(themeDisplay.getUserId());

	AUI.add(
		'liferay-scheduler',
		function(A) {

		var DateMath = A.DataType.DateMath;

		var Lang = A.Lang;

		var isArray = Lang.isArray;
		var isBoolean = Lang.isBoolean;
		var isDate = Lang.isDate;
		var isObject = Lang.isObject;
		var isString = Lang.isString;

		var jsonParse = function(val) {
			var jsonObj = null;

			try {
				jsonObj = A.JSON.parse(val);
			}
			catch(e) {
			}

			return jsonObj;
		};

		var CalendarUtil = {
			PORTLET_NAMESPACE: STR_BLANK,
			USER_TIMEZONE_OFFSET: 0,

			dataSource: null,

			invokerURL: '/api/secure/jsonws/invoke',

			visibleCalendars: {},

			addEvent: function(evt) {
				var instance = this;

				instance.invoke(
					{
						"/enterprise-calendar-portlet/calendarbooking/add-calendar-booking": {
							allDay: evt.get('allDay'),
							calendarId: evt.get('calendarId'),
							descriptionMap: instance.getLocalizationMap(evt.get('description')),
							endDate: CalendarUtil.toUTCTimeZone(evt.get('endDate')).getTime(),
							firstReminder: 0,
							location: evt.get('location'),
							recurrence: evt.get('repeat'),
							secondReminder: 0,
							startDate: CalendarUtil.toUTCTimeZone(evt.get('startDate')).getTime(),
							titleMap: instance.getLocalizationMap(evt.get('content')),
							userId: USER_ID
						}
					},
					{
						failure: function() {
							CalendarUtil.destroyEvent(evt);
						},

						start: function() {
							evt.set('loading', true);
						},

						success: function(data) {
							evt.set('loading', false);

							if (data) {
								if (data.exception) {
									CalendarUtil.destroyEvent(evt);
									return;
								}

								evt.set('calendarBookingId', data.calendarBookingId);
								evt.set('calendarResourceId', data.calendarResourceId);
								evt.set('parentCalendarBookingId', data.parentCalendarBookingId);
								evt.set('status', data.status);

								var calendar = CalendarUtil.visibleCalendars[evt.get('calendarId')];

								if (calendar) {
									calendar.addEvent(evt);
								}
							}
						}
					}
				);
			},

			collectCalendarEvents: function(calendarBookings, calendarId) {
				var instance = this;

				var events = [];

				A.Array.each(
					calendarBookings,
					function(item, index, collection) {
						if (calendarId === item.calendarId) {
							events[index] = instance.toSchedulerEvent(item);
						}
					}
				);

				return events;
			},

			createCalendarListAutoComplete: function(resourceURL, calendarList, input) {
				var instance = this;

				input.plug(
					A.Plugin.AutoComplete,
					{
						activateFirstItem: true,
						after: {
							select: function(event) {
								calendarList.add(event.result.raw);

								input.val(STR_BLANK);
							}
						},
						maxResults: 20,
						requestTemplate: '&' + Liferay.CalendarUtil.PORTLET_NAMESPACE + 'keywords={query}',
						resultFilters: function(query, results) {
							return A.Array.filter(
								results,
								function(item, index, collection) {
									return !instance.visibleCalendars[item.raw.calendarId];
								}
							);
						},
						resultFormatter: function(query, results) {
							return A.Array.map(
								results, function (result) {
									var calendar = result.raw;
									var name = calendar.name;
									var calendarResourceName = calendar.calendarResourceName;

									if (name !== calendarResourceName) {
										name = [calendarResourceName, STR_DASH, name].join(STR_SPACE);
									}

									return A.Highlight.words(name, query);
								}
							);
						},
						resultHighlighter: 'wordMatch',
						resultTextLocator: 'calendarResourceName',
						source: resourceURL
					}
				);
			},

			deleteEvent: function(evt) {
				var instance = this;

				instance.invoke(
					{
						'/enterprise-calendar-portlet/calendarbooking/delete-calendar-booking': {
							calendarBookingId: evt.get('calendarBookingId')
						}
					},
					{
						success: function() {
							evt.get('scheduler').loadCalendarBookings();
						}
					}
				);
			},

			destroyEvent: function(evt) {
				var instance = this;

				var scheduler = evt.get('scheduler');

				scheduler.removeEvent(evt);
				scheduler.syncEventsUI();
			},

			getDataSource: function() {
				var instance = this;

				var dataSource = instance.dataSource;

				if (!dataSource) {
					dataSource = new A.DataSource.IO(
						{
							source: instance.invokerURL,
							on: {
								request: function(e) {
									var callback = e.callback && e.callback.start;

									if (callback) {
										var payload = e.details[0];

										callback.apply(this, [payload, e]);
									}
								}
							}
						}
					);

					dataSource.plug(
						A.Plugin.DataSourceCache,
						{
							max: 100
						}
					);

					instance.dataSource = dataSource;
				}

				return instance.dataSource;
			},

			getStatusLabel: function(statusId) {
				var status = String.valueOf(statusId);

				if (Liferay.Workflow.STATUS_APPROVED === statusId) {
					status = Liferay.Language.get('accepted');
				}
				else if (Liferay.Workflow.STATUS_DRAFT === statusId) {
					status = Liferay.Language.get('draft');
				}
				else if (Liferay.Workflow.STATUS_PENDING === statusId) {
					status = Liferay.Language.get('pending');
				}
				else if (Liferay.Workflow.STATUS_DENIED === statusId) {
					status = Liferay.Language.get('declined');
				}

				return status;
			},

			getEvents: function(startDate, endDate, status, success, failure) {
				var instance = this;

				var calendarIds = A.Object.keys(CalendarUtil.visibleCalendars);

				instance.invoke(
					{
						"$booking = /enterprise-calendar-portlet/calendarbooking/search": {
							calendarIds: calendarIds.join(','),
							calendarResourceIds: '',
							companyId: COMPANY_ID,
							end: -1,
							endDate: endDate.getTime(),
							groupIds: [0, COMPANY_GROUP_ID, GROUP_ID].join(','),
							keywords: null,
							orderByComparator: null,
							parentCalendarBookingId: -1,
							start: Workflow.STATUS_APPROVED,
							startDate: startDate.getTime(),
							status: status.join(',')
						}
					},
					{
						cache: true,
						failure: failure,
						success: success
					}
				);
			},

			getLocalizationMap: function(value) {
				var instance = this;

				var map = {};
				map[themeDisplay.getLanguageId()] = value;

				return A.JSON.stringify(map);
			},

			invokeTransition: function(evt, status) {
				var instance = this;

				var scheduler = evt.get('scheduler');

				instance.invoke(
					{
						"/enterprise-calendar-portlet/calendarbooking/invoke-transition": {
							calendarBookingId: evt.get('calendarBookingId'),
							transitionName: CalendarUtil.getStatusLabel(status).toLowerCase(),
							userId: USER_ID
						}
					},
					{
						start: function() {
							evt.set('loading', true);
						},

						success: function(data) {
							evt.set('loading', false);

							if (data) {
								if (data.exception) {
									return;
								}

								if (scheduler) {
									var eventRecorder = scheduler.get('eventRecorder');

									eventRecorder.hideOverlay();

									scheduler.loadCalendarBookings();
								}
							}
						}
					}
				);
			},

			message: function(msg) {
				var instance = this;

				A.one('#' + instance.PORTLET_NAMESPACE + 'message').html(msg);
			},

			invoke: function(service, callback) {
				var instance = this;

				var dataSource = instance.getDataSource();

				callback = A.merge(
					{
						cache: false
					},
					callback
				);

				if (callback.cache === false) {
					dataSource.cache.flush();
				}

				dataSource.sendRequest(
					{
						callback: {
							failure: callback.failure,
							start: callback.start,
							success: function(e) {
								var xhr = e && e.response.results && e.response.results[0];

								if (xhr && callback.success) {
									var data = jsonParse(xhr.responseText);

									callback.success.apply(this, [data, e]);
								}
							}
						},
						request: '?cmd=' + A.JSON.stringify(service)
					}
				);
			},

			toUserTimeZone: function(utc) {
				var instance = this;

				if (!isDate(utc)) {
					utc = new Date(utc);
				}

				return DateMath.add(utc, DateMath.MINUTES, utc.getTimezoneOffset() + instance.USER_TIMEZONE_OFFSET/DateMath.ONE_MINUTE_MS);
			},

			toUTCTimeZone: function(date) {
				var instance = this;

				if (!isDate(date)) {
					date = new Date(date);
				}

				return DateMath.subtract(date, DateMath.MINUTES, date.getTimezoneOffset());
			},

			toSchedulerEvent: function(calendarBooking) {
				var instance = this;

				return new Liferay.SchedulerEvent(
					{
						allDay: calendarBooking.allDay,
						calendarBookingId: calendarBooking.calendarBookingId,
						calendarId: calendarBooking.calendarId,
						content: calendarBooking.titleCurrentValue,
						description: calendarBooking.descriptionCurrentValue,
						endDate: CalendarUtil.toUserTimeZone(calendarBooking.endDate),
						location: calendarBooking.location,
						parentCalendarBookingId: calendarBooking.parentCalendarBookingId,
						startDate: CalendarUtil.toUserTimeZone(calendarBooking.startDate),
						status: calendarBooking.status
					}
				);
			},

			syncVisibleCalendarsMap: function() {
				var instance = this;

				var visibleCalendars = instance.visibleCalendars = {};

				A.Array.each(
					arguments,
					function(calendarList) {
						var calendars = calendarList.get('calendars');

						A.each(
							calendars,
							function(item, index, collection) {
								visibleCalendars[item.get('calendarId')] = item;
							}
						);
					}
				);

				return visibleCalendars;
			},

			updateEvent: function(evt) {
				var instance = this;

				instance.invoke(
					{
						"/enterprise-calendar-portlet/calendarbooking/update-calendar-booking": {
							allDay: evt.get('allDay'),
							calendarBookingId: evt.get('calendarBookingId'),
							calendarId: evt.get('calendarId'),
							descriptionMap: instance.getLocalizationMap(evt.get('description')),
							endDate: CalendarUtil.toUTCTimeZone(evt.get('endDate')).getTime(),
							firstReminder: 0,
							location: evt.get('location'),
							recurrence: evt.get('repeat'),
							secondReminder: 0,
							startDate: CalendarUtil.toUTCTimeZone(evt.get('startDate')).getTime(),
							status: evt.get('status'),
							titleMap: instance.getLocalizationMap(evt.get('content')),
							userId: USER_ID
						}
					},
					{
						start: function() {
							evt.set('loading', true);
						},

						success: function(data) {
							evt.set('loading', false);

							if (data) {
								if (data.exception) {
									return;
								}
							}
						}
					}
				);
			}

		};

		Liferay.CalendarUtil = CalendarUtil;

		var Scheduler = A.Component.create(
			{
				NAME: 'scheduler-base',

				ATTRS: {
					currentMonth: {
						setter: toNumber,
						valueFn: function(val) {
							var instance = this;

							return instance.get('currentDate').getMonth();
						}
					},

					portletNamespace: {
						value: '',
						validator: isString
					}
				},

				EXTENDS: A.Scheduler,

				UI_ATTRS: ['currentMonth'],

				prototype: {
					bindUI: function() {
						var instance = this;

						instance.after(
							{
								'scheduler-base:currentDateChange': instance._afterCurrentDateChange,
								'scheduler-event:startDateChange': instance._afterStartDateChange
							}
						);

						instance.on(
							{
								'scheduler-event-recorder:delete': instance._onDeleteEvent,
								'scheduler-event-recorder:save': instance._onSaveEvent
							}
						);

						Scheduler.superclass.bindUI.apply(this, arguments);
					},

					loadCalendarBookings: function() {
						var instance = this;

						CalendarUtil.message(Liferay.Language.get('loading') + '...');

						var currentDate = instance.get('currentDate');
						var firstDayOfWeek = instance.get('firstDayOfWeek');

						var startDate = DateMath.getFirstDayOfWeek(DateMath.findMonthStart(currentDate), firstDayOfWeek);
						var endDate = DateMath.add(DateMath.getFirstDayOfWeek(DateMath.findMonthEnd(currentDate), firstDayOfWeek), DateMath.DAY, 7);

						CalendarUtil.getEvents(
							startDate,
							endDate,
							[Workflow.STATUS_APPROVED, Workflow.STATUS_PENDING],
							A.bind(instance.loadCalendarBookingsJSON, instance)
						);
					},

					loadCalendarBookingsJSON: function(calendarBookings) {
						var instance = this;

						var visibleCalendarsMap = Liferay.CalendarUtil.visibleCalendars;

						A.each(
							visibleCalendarsMap,
							function(item, index, collection) {
								var events = CalendarUtil.collectCalendarEvents(calendarBookings, toNumber(index, collection));

								item.set('events', events);
							}
						);

						instance.set('events', A.Object.values(visibleCalendarsMap));

						instance.syncEventsUI();

						CalendarUtil.message('');
					},

					_afterCurrentDateChange: function(event) {
						var instance = this;

						var currentMonth = event.newVal.getMonth();

						if (currentMonth !== instance.get('currentMonth')) {
							instance.set('currentMonth', currentMonth);
						}
					},

					_afterStartDateChange: function(event) {
						var instance = this;

						setTimeout(function() {
							CalendarUtil.updateEvent(event.target);
						}, 0);
					},

					_onDeleteEvent: function(event) {
						var instance = this;

						var evt = event.schedulerEvent;

						if (evt.isMasterBooking() && !confirm(Liferay.Language.get('deleting-this-event-will-cancel-the-meeting-with-your-guests-would-you-like-to-delete'))) {
							event.preventDefault();
							return;
						}

						CalendarUtil.deleteEvent(evt);
					},

					_onSaveEvent: function(event) {
						var instance = this;

						CalendarUtil.addEvent(event.newSchedulerEvent);
					},

					_uiSetCurrentMonth: function(val) {
						var instance = this;

						instance.loadCalendarBookings();
					}
				}
			}
		);

		Liferay.Scheduler = Scheduler;


		var SchedulerEvent = A.Component.create({
			NAME: 'scheduler-event',

			ATTRS: {
				allDay: {
					setter: A.DataType.Boolean.parse,
					value: false
				},

				calendarBookingId: {
					setter: toNumber,
					value: 0
				},

				calendarId: {
					setter: toNumber,
					value: 0
				},

				description: {
					validator: isString,
					value: STR_BLANK
				},

				loading: {
					validator: isBoolean,
					value: false
				},

				location: {
					validator: isString,
					value: STR_BLANK
				},

				parentCalendarBookingId: {
					setter: toNumber,
					value: 0
				},

				status: {
					setter: toNumber,
					value: 0
				}
			},

			EXTENDS: A.SchedulerEvent,

			prototype: {
				initializer: function() {
					var instance = this;

					instance._uiSetLoading(instance.get('loading'));
					instance._uiSetStatus(instance.get('status'));

					instance.on('loadingChange', instance._onLoadingChange);
					instance.on('statusChange', instance._onStatusChange);
				},

				isMasterBooking: function() {
					var instance = this;

					return (instance.get('parentCalendarBookingId') === instance.get('calendarBookingId'));
				},

				syncNodeColorUI: function() {
					var instance = this;

					Liferay.SchedulerEvent.superclass.syncNodeColorUI.apply(instance, arguments);

					var node = instance.get('node');
					var scheduler = instance.get('scheduler');

					if (scheduler) {
						var activeViewName = scheduler.get('activeView').get('name');

						if ((activeViewName === 'month') && !instance.get('allDay')) {
							node.setStyles(
								{
									backgroundColor: '#f8f8f8',
									border: 'none',
									color: instance.get('color')
								}
							);
						}
					}
				},

				_onLoadingChange: function(event) {
					var instance = this;

					instance._uiSetLoading(event.newVal);
				},

				_onStatusChange: function(event) {
					var instance = this;

					instance._uiSetStatus(event.newVal);
				},

				_uiSetLoading: function(val) {
					var instance = this;

					instance.get('node').toggleClass('calendar-portlet-event-loading', val);
				},

				_uiSetStatus: function(val) {
					var instance = this;

					var node = instance.get('node');

					node.toggleClass('calendar-portlet-event-pending', (val === Workflow.STATUS_PENDING));
					node.toggleClass('calendar-portlet-event-approved', (val === Workflow.STATUS_APPROVED));
				}
			}
		});

		Liferay.SchedulerEvent = SchedulerEvent;

		var SchedulerEventRecorder = A.Component.create(
			{
				NAME: 'scheduler-event-recorder',

				ATTRS: {
					allDay: {
						setter: A.DataType.Boolean.parse,
						value: false
					},

					editCalendarBookingURL: {
						validator: isString,
						value: STR_BLANK
					},

					portletNamespace: {
						validator: isString,
						value: STR_BLANK
					},

					status: {
						setter: toNumber,
						value: Liferay.Workflow.STATUS_DRAFT
					}
				},

				EXTENDS: A.SchedulerEventRecorder,

				prototype: {
					getEventCopy: function() {
						var instance = this;
						var newEvt = instance.get('event');

						if (!newEvt) {
							newEvt = new (instance.get('eventClass'))();
						}

						newEvt.setAttrs(instance.serializeForm());

						return newEvt;
					},

					getTemplateData: function() {
						var instance = this;

						var evt = instance.get('event') || instance;

						return A.merge(
							SchedulerEventRecorder.superclass.getTemplateData.apply(this, arguments),
							{
								allDay: evt.get('allDay'),
								status: CalendarUtil.getStatusLabel(evt.get('status'))
							}
						);
					},

					isMasterBooking: function() {
						var instance = this;

						return false;
					},

					populateForm: function() {
						var instance = this;

						instance._syncViewDefData();

						SchedulerEventRecorder.superclass.populateForm.apply(this, arguments);
					},

					_handleAcceptEvent: function(event) {
						var instance = this;

						var evt = instance.get('event');

						if (evt) {
							CalendarUtil.invokeTransition(evt, Liferay.Workflow.STATUS_APPROVED);
						}
					},

					_handleDeclineEvent: function(event) {
						var instance = this;

						var evt = instance.get('event');

						if (evt) {
							CalendarUtil.invokeTransition(evt, Liferay.Workflow.STATUS_DENIED);
						}
					},

					_handleEditDetailsEvent: function(event) {
						var instance = this;

						var scheduler = instance.get('scheduler');
						var activeViewName = scheduler.get('activeView').get('name');
						var currentDate = scheduler.get('currentDate');

						var evt = instance.get('event');
						var editCalendarBookingURL = decodeURIComponent(instance.get('editCalendarBookingURL'));
						var data = instance.serializeForm();

						data.activeView = activeViewName;
						data.currentDate = currentDate.getTime();
						data.endDate = CalendarUtil.toUTCTimeZone(data.endDate).getTime();
						data.startDate = CalendarUtil.toUTCTimeZone(data.startDate).getTime();
						data.titleCurrentValue = encodeURIComponent(data.content);

						if (evt) {
							data.allDay = evt.get('allDay');
							data.calendarBookingId = evt.get('calendarBookingId');
						}

						window.location.href = A.Lang.sub(editCalendarBookingURL, data);
					},

					_onOverlayVisibleChange: function(event) {
						var instance = this;

						SchedulerEventRecorder.superclass._onOverlayVisibleChange.apply(this, arguments);

						var evt = instance.get('event');
						var overlayBB = instance.overlay.get('boundingBox');
						var portletNamespace = instance.get('portletNamespace');

						overlayBB.toggleClass('calendar-portlet-event-recorder-editing', !!evt);

						var eventRecorderCalendar = A.one('#' + portletNamespace + 'eventRecorderCalendar');

						if (eventRecorderCalendar) {
							var calendarId = CalendarUtil.DEFAULT_CALENDAR.calendarId;

							if (evt) {
								calendarId = evt.get('calendarId');
							}

							eventRecorderCalendar.val(calendarId);
						}

						instance._syncToolbarButtons(event.newVal);
					},

					_syncViewDefData: function() {
						var instance = this;

						var scheduler = instance.get('scheduler');
						var activeViewName = scheduler.get('activeView').get('name');

						if (activeViewName === 'month') {
							instance.set('allDay', true);
						}
						else {
							instance.set('allDay', false);
						}
					},

					_syncToolbarButtons: function(overlayVisible) {
						var instance = this;

						if (!overlayVisible) {
							return;
						}

						var evt = instance.get('event') || instance;
						var status = evt.get('status');

						var overlay = instance.overlay;
						var toolbar = instance.toolbar;

						toolbar.add(
							{
								handler: A.bind(instance._handleEditDetailsEvent, instance),
								id: 'editDetailsBtn',
								label: Liferay.Language.get('edit-details')
							}
						);

						if (status === Liferay.Workflow.STATUS_DRAFT) {
							toolbar.remove('declineBtn');
						}

						if (status === Liferay.Workflow.STATUS_APPROVED ||
							status === Liferay.Workflow.STATUS_DRAFT) {

							toolbar.remove('acceptBtn');
						}

						if (status === Liferay.Workflow.STATUS_PENDING) {
							toolbar.add(
								{
									handler: A.bind(instance._handleAcceptEvent, instance),
									icon: 'circle-check',
									id: 'acceptBtn',
									label: Liferay.Language.get('accept')
								}
							);
						}

						if (status === Liferay.Workflow.STATUS_PENDING ||
							status === Liferay.Workflow.STATUS_APPROVED) {

							toolbar.add(
								{
									handler: A.bind(instance._handleDeclineEvent, instance),
									icon: 'circle-close',
									id: 'declineBtn',
									label: Liferay.Language.get('decline')
								}
							);
						}

						var estimatedOverlayWidth = 50 + toolbar.get('boundingBox').get('offsetWidth');

						overlay.set('width', Math.max(300, estimatedOverlayWidth));
					}
				}
			}
		);

		Liferay.SchedulerEventRecorder = SchedulerEventRecorder;

		var Calendar = A.Component.create({
			NAME: 'scheduler-calendar',

			ATTRS: {
				calendarId: {
					value: 0,
					setter: toNumber
				},

				calendarResourceId: {
					value: 0,
					setter: toNumber
				},

				calendarResourceName: {
					value: '',
					validator: isString
				},

				classNameId: {
					value: 0,
					setter: toNumber
				},

				classPK: {
					value: 0,
					setter: toNumber
				},

				defaultCalendar: {
					setter: A.DataType.Boolean.parse,
					value: false
				},

				global: {
					setter: A.DataType.Boolean.parse,
					value: false
				}
			},

			prototype: {
				getDisplayName: function() {
					var instance = this;

					var displayName = instance.get('name');
					var calendarResourceName = instance.get('calendarResourceName');

					if (displayName !== calendarResourceName) {
						displayName = [calendarResourceName, STR_DASH, displayName].join(STR_SPACE);
					}

					return displayName;
				},

				_afterColorChange: function(event) {
					var instance = this;

					Calendar.superclass._afterColorChange.apply(instance, arguments);

					CalendarUtil.invoke(
						{
							"/enterprise-calendar-portlet/calendar/update-calendar-color": {
								calendarId: instance.get('calendarId'),
								color: parseInt(event.newVal.substr(1), 16)
							}
						}
					);
				}
			},

			EXTENDS: A.SchedulerCalendar
		});

		Liferay.SchedulerCalendar = Calendar;

	},
	'' ,
	{
		requires: ['aui-scheduler', 'aui-io', 'autocomplete', 'autocomplete-highlighters', 'datasource-get', 'datasource-cache', 'liferay-portlet-url', 'liferay-store']
	}
);

}());