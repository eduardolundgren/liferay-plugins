(function() {

var _DOT = '.',
	_EMPTY_STR = '',
	_SPACE = ' ',
	_PLUS = '+',
	_DASH = '-',
	_EMPY_STR = '',

	ACTIVE = 'active',
	ARROW = 'arrow',
	BLANK = 'blank',
	CALENDAR_LIST = 'calendar-list',
	CALENDARS = 'calendars',
	CLICK = 'click',
	COLOR = 'color',
	CONTENT_BOX = 'contentBox',
	DATA_ID = 'data-id',
	HIDDEN = 'hidden',
	HIDDEN_ITEMS = 'hiddenItems',
	HOVER = 'hover',
	ITEM = 'item',
	ITEMS = 'items',
	LABEL = 'label',
	RENDER = 'render',
	RENDERED = 'rendered',
	SEPARATOR = 'separator',
	SIMPLEMENU = 'simple-menu',
	SIMPLE_MENU = 'simpleMenu',
	STYLE = 'style',
	CLICK = 'click',
	COLOR = 'color',
	CONTENT_BOX = 'contentBox',
	COLOR_CHANGE = 'colorChange',
	ITEM = 'item',
	PALLETE = 'pallete',
	RENDERED = 'rendered',
	SELECTED = 'selected',
	SIMPLE_COLOR_PICKER = 'simple-color-picker',
	VISIBLE = 'visible';

AUI.add('liferay-calendar-simple-menu', function(A) {

var L = A.Lang,
	isArray = L.isArray,
	isBoolean = L.isBoolean,

	AArray = A.Array,

	getCN = A.getClassName,

	CSS_SIMPLE_MENU_ITEM = getCN(SIMPLEMENU, ITEM),
	CSS_SIMPLE_MENU_ITEM_HIDDEN = getCN(SIMPLEMENU, ITEM, HIDDEN),
	CSS_SIMPLE_MENU_SEPARATOR = getCN(SIMPLEMENU, SEPARATOR),

	TPL_SIMPLE_MENU_ITEM = '<li class="{cssClass}" data-id="{id}">{caption}</li>',

	_getItemFn = A.cached(function(id, items) {
		var item = A.Array.filter(items, function(item) {
			return item.id === id;
		});

		return item[0] && item[0].fn;
	});

var SimpleMenu = A.Component.create(
	{
		NAME: SIMPLEMENU,

		ATTRS: {
			items: {
				validator: isArray,
				value: []
			},

			hiddenItems: {
				validator: isArray,
				value: []
			},

			host: {
				value: null
			}
		},

		UI_ATTRS: [ITEMS, HIDDEN_ITEMS],

		AUGMENTS: [A.WidgetStdMod, A.WidgetPosition, A.WidgetStack, A.WidgetPositionAlign, A.WidgetPositionConstrain],

		prototype: {
			CONTENT_TEMPLATE: '<ul></ul>',

			bindUI: function() {
				var instance = this;
				var contentBox = instance.get(CONTENT_BOX);

				contentBox.delegate(CLICK, A.bind(instance._onClickItems, instance), _DOT + CSS_SIMPLE_MENU_ITEM);
			},

			renderUI: function() {
				var instance = this;

				instance._renderItems(instance.get(ITEMS));
			},

			_onClickItems: function(event) {
				var instance = this;
				var items = instance.get(ITEMS);
				var target = event.currentTarget;

				var id = target.attr(DATA_ID);
				var fn = _getItemFn(id, items);

				if (fn) {
					fn.apply(instance, arguments);
				}

				event.stopPropagation();
			},

			_renderItems: function(items) {
				var instance = this;
				var contentBox = instance.get(CONTENT_BOX);
				var hiddenItems = instance.get(HIDDEN_ITEMS);

				instance.items = A.NodeList.create();

				AArray.each(items, function(item) {
					var caption = item.caption;
					var id = item.id;

					var cssClass = caption === _DASH ? CSS_SIMPLE_MENU_SEPARATOR : CSS_SIMPLE_MENU_ITEM;

					if (AArray.indexOf(hiddenItems, id) > -1)  {
						cssClass += _SPACE + CSS_SIMPLE_MENU_ITEM_HIDDEN;
					}

					var li = A.Node.create(
						L.sub(TPL_SIMPLE_MENU_ITEM, {
							cssClass: cssClass,
							id: id
						})
					);

					li.setContent(caption);

					instance.items.push(li);
				});

				contentBox.setContent(instance.items);
			},

			_uiSetItems: function(val) {
				var instance = this;

				if (instance.get(RENDERED)) {
					instance._renderItems(val);
				}
			},

			_uiSetHiddenItems: function(val) {
				var instance = this;

				if (instance.get(RENDERED)) {
					instance.items.each(function(item) {
						var id = item.attr(DATA_ID);

						item.toggleClass(CSS_SIMPLE_MENU_ITEM_HIDDEN, AArray.indexOf(val, id) > -1);
					});
				}
			}
		}
	}
);

Liferay.SimpleMenu = SimpleMenu;

}, '@VERSION@' ,{ requires: ['aui-base', 'aui-template', 'widget-position', 'widget-stack', 'widget-position-align', 'widget-position-constrain', 'widget-stdmod'] });

AUI.add('liferay-calendar-list', function(A) {
var L = A.Lang,
	isArray = L.isArray,
	isString = L.isString,
	isObject = L.isObject,

	AArray = A.Array,

	getCN = A.getClassName,

	CSS_CALENDAR_LIST_ITEM = getCN(CALENDAR_LIST, ITEM),
	CSS_CALENDAR_LIST_ITEM_ACTIVE = getCN(CALENDAR_LIST, ITEM, ACTIVE),
	CSS_CALENDAR_LIST_ITEM_ARROW = getCN(CALENDAR_LIST, ITEM, ARROW),
	CSS_CALENDAR_LIST_ITEM_COLOR = getCN(CALENDAR_LIST, ITEM, COLOR),
	CSS_CALENDAR_LIST_ITEM_HOVER = getCN(CALENDAR_LIST, ITEM, HOVER),
	CSS_CALENDAR_LIST_ITEM_LABEL = getCN(CALENDAR_LIST, ITEM, LABEL),

	TPL_CALENDAR_LIST_ITEM = new A.Template(
		'<tpl for="calendars">',
			'<div class="', CSS_CALENDAR_LIST_ITEM, '">',
				'<div class="', CSS_CALENDAR_LIST_ITEM_COLOR, '" {[ parent.calendars[$i].get("visible") ? ', '\'style="background-color:\'', _PLUS, 'parent.calendars[$i].get("color")', _PLUS, '";border-color:"', _PLUS, 'parent.calendars[$i].get("color")', _PLUS, '";\\""', ' : \'', _EMPTY_STR, '\' ]}></div>',
				'<span class="', CSS_CALENDAR_LIST_ITEM_LABEL, '">{[ parent.calendars[$i].get("name") ]}</span>',
				'<div href="javascript:;" class="', CSS_CALENDAR_LIST_ITEM_ARROW, '"></div>',
			'</div>',
		'</tpl>'
	);

var CalendarList = A.Component.create(
	{
		NAME: CALENDAR_LIST,

		ATTRS: {
			calendars: {
				setter: '_setCalendars',
				validator: isArray,
				value: []
			},

			simpleMenu: {
				setter: '_setSimpleMenu',
				validator: isObject,
				value: null
			}
		},

		UI_ATTRS: [ CALENDARS ],

		prototype: {
			initializer: function() {
				var instance = this;

				instance.simpleMenu = new Liferay.SimpleMenu(instance.get(SIMPLE_MENU));
			},

			bindUI: function() {
				var instance = this;
				var contentBox = instance.get(CONTENT_BOX);

				instance.on('scheduler-calendar:colorChange', A.bind(instance._onCalendarColorChange, instance));
				instance.on('scheduler-calendar:visibleChange', A.bind(instance._onCalendarVisibleChange, instance));
				instance.on('simple-menu:visibleChange', A.bind(instance._onSimpleMenuVisibleChange, instance));

				contentBox.delegate(CLICK, A.bind(instance._onClick, instance), _DOT + CSS_CALENDAR_LIST_ITEM);

				contentBox.delegate(
					HOVER,
					A.bind(instance._onHoverOver, instance),
					A.bind(instance._onHoverOut, instance),
					_DOT + CSS_CALENDAR_LIST_ITEM
				);
			},

			renderUI: function() {
				var instance = this;

				instance._renderCalendars();

				instance.simpleMenu.render();
			},

			_clearCalendarColor: function(calendar) {
				var instance = this;

				var node = instance._getCalendarNode(calendar);
				var colorNode = node.one(_DOT + CSS_CALENDAR_LIST_ITEM_COLOR);

				colorNode.setAttribute(STYLE, _EMPTY_STR);
			},

			_getCalendarByNode: function(node) {
				var instance = this;
				var calendars = instance.get(CALENDARS);

				return calendars[instance.items.indexOf(node)];
			},

			_getCalendarNode: function(calendar) {
				var instance = this;
				var calendars = instance.get(CALENDARS);

				return instance.items.item(AArray.indexOf(calendars, calendar));
			},

			_onCalendarColorChange: function(event) {
				var instance = this;
				var target = event.target;

				if (target.get(VISIBLE)) {
					instance._setCalendarColor(target, event.newVal);
				}
			},

			_onCalendarVisibleChange: function(event) {
				var instance = this;
				var target = event.target;

				if (event.newVal) {
					instance._setCalendarColor(target, target.get(COLOR));
				}
				else {
					instance._clearCalendarColor(target);
				}
			},

			_onClick: function(event) {
				var instance = this;
				var target = event.target;

				if (target.hasClass(CSS_CALENDAR_LIST_ITEM_ARROW)) {
					if (instance.activeNode) {
						instance.activeNode.removeClass(CSS_CALENDAR_LIST_ITEM_ACTIVE);
					}

					instance.activeNode = event.currentTarget;
					instance.activeItem = instance._getCalendarByNode(instance.activeNode);

					instance.activeNode.addClass(CSS_CALENDAR_LIST_ITEM_ACTIVE);

					var simpleMenu = instance.simpleMenu;

					simpleMenu.setAttrs({
						'align.node': target,
						visible: simpleMenu.get('align.node') !== target || !simpleMenu.get(VISIBLE)
					});

					event.stopPropagation();
				}
				else {
					var calendar = instance._getCalendarByNode(event.currentTarget);

					calendar.set(VISIBLE, !calendar.get(VISIBLE));
				}
			},

			_onHoverOver: function(event) {
				var instance = this;
				var target = event.currentTarget;

				var calendar = instance._getCalendarByNode(target);

				instance._setCalendarColor(calendar, calendar.get(COLOR));

				target.addClass(CSS_CALENDAR_LIST_ITEM_HOVER);
			},

			_onHoverOut: function(event) {
				var instance = this;
				var target = event.currentTarget;

				var calendar = instance._getCalendarByNode(target);

				if (!calendar.get(VISIBLE)) {
					instance._clearCalendarColor(calendar);
				}

				target.removeClass(CSS_CALENDAR_LIST_ITEM_HOVER);
			},

			_onSimpleMenuVisibleChange: function(event) {
				var instance = this;

				if (instance.activeNode && !event.newVal) {
					instance.activeNode.removeClass(CSS_CALENDAR_LIST_ITEM_ACTIVE);
				}
			},

			_renderCalendars: function() {
				var instance = this;

				instance.items = A.NodeList.create(
					TPL_CALENDAR_LIST_ITEM.parse({
						calendars: instance.get(CALENDARS)
					})
				);

				instance.get(CONTENT_BOX).setContent(instance.items);
			},

			_setCalendarColor: function(calendar, val) {
				var instance = this;

				var node = instance._getCalendarNode(calendar);
				var colorNode = node.one(_DOT + CSS_CALENDAR_LIST_ITEM_COLOR);

				colorNode.setStyles(
					{
						backgroundColor: val,
						borderColor: val
					}
				);
			},

			_setCalendars: function(val) {
				var instance = this;

				AArray.each(val, function(item, i) {
					if (!A.instanceOf(item, A.SchedulerCalendar)) {
						val[i] = new A.SchedulerCalendar(item);
					}

					val[i].addTarget(instance);
				});

				return val;
			},

			_setSimpleMenu: function(val) {
				var instance = this;

				return A.merge(
					{
						align: {
							points: [ A.WidgetPositionAlign.TL, A.WidgetPositionAlign.BL ]
						},
						items: [],
						plugins: [ A.Plugin.OverlayAutohide ],
						bubbleTargets: [ instance ],
						visible: false,
						width: 290,
						zIndex: 500
					},
					val || {}
				);
			},

			_uiSetCalendars: function(val) {
				var instance = this;

				if (instance.get(RENDERED)) {
					instance._renderCalendars();
				}
			}
		}
	}
);

Liferay.CalendarList = CalendarList;

}, '@VERSION@' ,{ requires: ['aui-scheduler', 'aui-template'] });

AUI.add('liferay-calendar-simple-color-picker', function(A) {
var L = A.Lang,
	isArray = L.isArray,
	isBoolean = L.isBoolean,
	isString = L.isString,

	AArray = A.Array,

	getCN = A.getClassName,

	CSS_SIMPLE_COLOR_PICKER_ITEM = getCN(SIMPLE_COLOR_PICKER, ITEM),
	CSS_SIMPLE_COLOR_PICKER_ITEM_SELECTED = getCN(SIMPLE_COLOR_PICKER, ITEM, SELECTED),

	TPL_SIMPLE_COLOR_PICKER_ITEM = new A.Template(
		'<tpl for="pallete">',
			'<div class="', CSS_SIMPLE_COLOR_PICKER_ITEM, '" style="background-color: {[ parent.pallete[$i] ]}', '; border-color:', '{[ parent.pallete[$i] ]};','"></div>',
		'</tpl>'
	);

var SimpleColorPicker = A.Component.create(
	{
		NAME: SIMPLE_COLOR_PICKER,

		ATTRS: {
			color: {
				validator: isString,
				value: _EMPY_STR
			},

			host: {
				value: null
			},

			pallete: {
				setter: AArray,
				validator: isArray,
				value: ['#d96666', '#e67399', '#b373b3', '#8c66d9', '#668cb3', '#668cd9', '#59bfb3', '#65ad89', '#4cb052', '#8cbf40', '#bfbf4d', '#e0c240', '#f2a640', '#e6804d', '#be9494', '#a992a9', '#8997a5', '#94a2be', '#85aaa5', '#a7a77d', '#c4a883', '#c7561e', '#b5515d', '#c244ab']
			}
		},

		UI_ATTRS: [ COLOR, PALLETE ],

		prototype: {
			bindUI: function() {
				var instance = this;
				var contentBox = instance.get(CONTENT_BOX);

				contentBox.delegate(CLICK, A.bind(instance._onClickColor, instance), _DOT + CSS_SIMPLE_COLOR_PICKER_ITEM);
			},

			renderUI: function() {
				var instance = this;

				instance._renderPallete();
			},

			_onClickColor: function(event) {
				var instance = this;
				var pallete = instance.get(PALLETE);

				instance.set(COLOR, pallete[instance.items.indexOf(event.currentTarget)]);
			},

			_renderPallete: function() {
				var instance = this;

				instance.items = A.NodeList.create(
					TPL_SIMPLE_COLOR_PICKER_ITEM.parse({
						pallete: instance.get(PALLETE)
					})
				);

				instance.get(CONTENT_BOX).setContent(instance.items);
			},

			_uiSetColor: function(val) {
				var instance = this;
				var pallete = instance.get(PALLETE);

				instance.items.removeClass(CSS_SIMPLE_COLOR_PICKER_ITEM_SELECTED);

				var newNode = instance.items.item(pallete.indexOf(val));

				if (newNode) {
					newNode.addClass(CSS_SIMPLE_COLOR_PICKER_ITEM_SELECTED);
				}
			},

			_uiSetPallete: function(val) {
				var instance = this;

				if (instance.get(RENDERED)) {
					instance._renderPallete();
				}
			}
		}
	}
);

Liferay.SimpleColorPicker = SimpleColorPicker;

}, '@VERSION@' ,{ requires: ['aui-base', 'aui-template'] });

})();
