function DateTableRenderer() {
	
}

DateTableRenderer.prototype = {
	imageRenderer: function(data, type, full, meta) {
		if(type=='display') {
			if(data != null) {
				return '<img width="50" src="' + data + '" />';	
			}
		}
		return data;
	},
	listLabelInfo: function(data, type, full, meta) {
		if(type=='display') {
			var list = data;
			var field = '';
			
			for(var i=0; i<list.length; i++) {
				var item = list[i];
				
				if(meta.settings.oInit.columns[meta.col].nameField !== undefined) {
					field += "<span class='label label-info'>" + item[meta.settings.oInit.columns[meta.col].nameField] + "</span> ";
				} else {
					field += "<span class='label label-info'>" + item + "</span> ";	
				}
			}
			return field;
		}
		return data;
	},
	buttonRenderer : function(data, type, full, meta) {
		if(type=="display") {
			var btclass = meta.settings.oInit.columns[meta.col].buttonClass;
			var faicon = meta.settings.oInit.columns[meta.col].buttonIcon;
			var eventId = meta.settings.oInit.columns[meta.col].eventId;
			return "<button class='" + btclass + "' buttonEventId='" + eventId + "'><span class='widget-icon'> <i class='fa " + faicon + "'></i></span></button>"; 
		}
		return data;
	},
	inputRenderer : function(data, type, full, meta) {
		if(type=='display'){
			
		}
	},
	dateRenderer : function(data, type, full, meta){
		if(type=="display") {
			var date = new Date(data);
			if(meta.settings.oInit.columns[meta.col].dateFormat !== undefined) {
				var dateFormat = meta.settings.oInit.columns[meta.col].dateFormat;
				var formats = dateFormat.split("|");

				var str = "{month}/{day}/{year}";
				for(var i=0; i<formats.length; i++) {
					if(formats[i] == "month") {
						var month = date.getMonth();
						var strMonth = "";
						if(month < 10) {
							strMonth += "0" + month;
						} else {
							strMonth = month;
						}
						str = str.replace("{month}", strMonth);
					}
					if(formats[i] == "year") {
						str = str.replace("{year}", date.getFullYear());
					}
					if(formats[i] == "day") {
						var day = date.getDate();
						var strDay = "";
						if(day < 10) {
							strDay += "0" + day;
						} else {
							strDay = day;
						}
						str = str.replace("{day}", strDay);
					}
				}
				str = str.replace("{month}/", "");
				str = str.replace("{day}/", "");
				str = str.replace("/{year}","");
				str = str.replace("{year}","");
				return str;
				
			} else {

		        return date.toLocaleDateString('en-US') + ' ' + date.toLocaleTimeString('en-US');	
			}
		}
		return data;
	},
	booleanRenderer : function(data, type, full, meta) {
		if(type=="display"){
			if(data == true) {
				return 'Yes';
			} else {
				return 'No';
			}
		}
		return data;
	},
	iconRenderer : function(data, type, full, meta) {
		if(type=="display") {
			return "<i class='fa fa-lg fa-fw " + data + " text-muted'></i>";
		}
		return data;
	}
}

function SbDataTable(obj) {
	var $this = this;
	this._renderer = new DateTableRenderer();

	this._customParam = [];
	this._customValue = []
	
	if(obj == null) {
		throw "Parameter cannot be null";
	}
	
	if(obj.id == null) {
		throw "Please specify the id";
	} else {
		this._id = obj.id;
	}
	
	if(obj.rowClick != null) {
		this._rowClick = obj.rowClick;
	} else {
		this._rowClick = null;
	}
	
	if(obj.rowUnclick != null) {
		this._rowUnclick = obj.rowUnclick;
	} else {
		this._rowUnclick = null;
	}
	if(obj.initLoad != null) {
		this._initLoad = obj.initLoad;
	} else {
		this._initLoad = true;
	}
		
	if(obj.viewId == null) {
		throw "Please specify the layer where table will be added";
	} else {
		this._viewId = obj.viewId;
	}
	if(obj.searchable == false) {
		this._searchable = false;
	} else {
		this._searchable = true;
	}
	if(obj.buttonEvents == null) {
		this._buttonEvents = {};
	} else {
		this._buttonEvents = obj.buttonEvents;
	}
	if(obj.textInputEvents == null) {
		this._textInputEvents = {};
	} else {
		this._textInputEvents = obj.textInputEvents;
	}
	
	this._scrollY = obj.scrollY;
	this._scrollCollapse = obj.scrollCollapse;
	this._paging = obj.paging;
	
	if(obj.headers == null) {
		throw "Please specify headers";
	} else {
		var table = createTableGlobal(obj.headers);
		table.attr("id", this._id);
		$("#" + this._viewId).append(table);
		
		var colLen = obj.headers.length;
		this._columns = [];
		for(var i=0; i<colLen; i++) {
			var item = {};
			
			if(obj.headers[i].value != null) {
				item.defaultContent = obj.headers[i].value;
				item.data = null;
			} else {
				item.data = obj.headers[i].name;
			}
			
			if(obj.headers[i].type != null && obj.headers[i].type.toLowerCase() == 'image') {
				item.render = this._renderer.imageRenderer;
			} else if(obj.headers[i].type != null && obj.headers[i].type.toLowerCase() == 'date') {
				if(obj.headers[i].dateFormat != null) {
					item.dateFormat = obj.headers[i].dateFormat;
					item.render = this._renderer.dateRenderer;
				} else {
					item.render = this._renderer.dateRenderer;
				}
			} else if(obj.headers[i].type != null && obj.headers[i].type.toLowerCase() == 'boolean') {
				item.render = this._renderer.booleanRenderer;
			} else if(obj.headers[i].type != null && obj.headers[i].type.toLowerCase() == 'icon') {
				item.render = this._renderer.iconRenderer;
			} else if(obj.headers[i].type != null && obj.headers[i].type.toLowerCase() == 'list-label') {
				if(obj.headers[i].nameField != null){
					item.nameField = obj.headers[i].nameField;
				}

				item.render = this._renderer.listLabelInfo;					
			} else if(obj.headers[i].type != null && obj.headers[i].type.toLowerCase() == 'button') {
				item.buttonClass = obj.headers[i].buttonClass;
				item.buttonIcon = obj.headers[i].buttonIcon;
				item.eventId = obj.headers[i].eventId;
				item.render = this._renderer.buttonRenderer;	
			}
			
			item.orderable = obj.headers[i].orderable;
			item.visible = obj.headers[i].visible;
			
			this._columns.push(item);
		}
	}
	
	if(obj.url == null) {
		throw "please specify the url";
	} else {
		this._url = obj.url;
	}
	if(obj.service != null) {
		this._service = obj.service;
	} else {
		throw "Please specify the service";
	}

	
	
	if(obj.responsiveHelper != null) {
		this._responsiveHelper = obj.responsiveHelper;
	}
	
	if(obj.breakpointDefinition != null) {
		this._breakpointDefinition = obj.breakpointDefinition;
	}
	this._table;
	this._selectedItem = {};	
}

SbDataTable.prototype = {
	setCustomParam: function(customParam) {
		this._customParam = customParam;
	},
	setCustomValue: function(customValue) {
		this._customValue = customValue;
	},
	getSortingFields : function(columns, order) {
		if(order == null) return '';
		var fields = [];
		for(var i=0; i<order.length; i++){ 
			fields.push(columns[order[i].column].data)
		}
		return fields.join();
	},
	getSortingDirections : function(order) {
		if(order == null) return '';
		var directions = [];
		
		for(var i=0; i<order.length; i++) {
			directions.push(order[i].dir);
		}
		return directions.join();
	},
	returnRequestParam: function(data) {
		var $this = this;
		var obj ={
			search: data.search.value,
			pageSize: data.length,
			page: (data.start / (data.length)) + 1,
			sortingFields: $this.getSortingFields(data.columns, data.order),
			sortingDirections: $this.getSortingDirections(data.order)
		};
		if(data.customParam != null && data.customParam.length > 0 && data.customValue != null && data.customValue.length == data.customParam.length) {
			for(var i=0; i<data.customParam.length; i++) {
				obj[data.customParam[i]] = data.customValue[i];
			}
		}
		return obj;
			
	},
	parsingRequestParam: function(type, data) {
		var $this = this;
		if(type == 'Server') {
			return $this.returnRequestParam(data);
		} else {
			return data;
		}
	},
	createTable : function(type, completeEvent) {
		var $this = this;
		var serverSide = false;
		if(type == "Server") {
			serverSide = true;
		}
		var sDom = "<'col-sm-6 col-xs-12 hidden-xs'l>r>t<'dt-toolbar-footer'<'col-sm-8 col-xs-6 hidden-xs'i><'col-xs-12 col-sm-12'p>>";
		if($this._searchable) {
			sDom = "<'dt-toolbar'<'col-xs-12 col-sm-6'f>" + sDom;
		} else {
			sDom = "<'dt-toolbar'" + sDom;
		}
		var options = {
			"sDom": sDom,
			"oLanguage": {
				"sSearch": '<span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>'
			},
			responsive: true,
			"processing": true,
			"serverSide": serverSide,
			"ajax": function(data, callback, settings) {
				data.customParam = $this._customParam;
				data.customValue = $this._customValue;
				$.ajax({
					url: $this._url,
					data: $this.parsingRequestParam(type, data),
					headers: {
						"X-Authorization": "Bearer " + getToken(),
					},
					error: function(e) {
						if(e.status == '401') {
							location.href = "/loginpage";
						} else {
							showMessage("error", e.responseJSON.error);
						}
					},
					success: function(res) {
						if(serverSide == false) {
							callback({
								recordsTotal: res.length,
								data: res
							})	
						} else {
							callback({
								recordsFiltered: res.totalElements,
								recordsTotal:res.totalElements,
								data: res.content
							})
						}
						
					}
				})
			},
			select: true,
			columns: $this._columns,
			"autoWidth": true,
			"preDrawCallback" : function() {
				if($this._responsiveHelper != null && $this._breakpointDefinition != null) {
					responsiveHelper = new ResponsiveDatatablesHelper($('#' + $this._id), $this._breakpointDefinition);
				}
			},
			"rowCallback" : function(nRow) {
				if($this._responsiveHelper!=null)
					$this._responsiveHelper.createExpandIcon(nRow);
			},
			"drawCallback" : function(oSettings) {
				if($this._responsiveHelper!=null)
					$this._responsiveHelper.respond();
			},
			"initComplete": function(oSettings, json) {
				completeEvent();
			}
		};
		if($this._initLoad == false) {
			options.deferLoading = 0;
		}
		if($this._scrollY != null) {
			options.scrollY = $this._scrollY;
		}
		if($this._scrollCollapse != null) {
			options.scrollCollapse = $this._scrollCollapse;
		}
		if($this._paging != null) {
			options.paging = $this._paging;
		}
		$this._table = $('#' + $this._id).DataTable(options);
		
		if($this._initLoad == false) {
			// for the first time it will not load data so just call draw here.
			$this._table.draw();
		}
		
		// if row has any button then give an event to the button
		$('#' + $this._id + ' tbody').on('click', 'button', function(){
			if($(this).attr('buttonEventId') != null) {
				var data = $this._table.row($(this).parents('tr')).data();
				$this._buttonEvents[$(this).attr('buttonEventId')](data);	
			}
		});
		
		// unbind search key event when it is server side request
		if(serverSide) {
			$('#' + $this._id + '_filter input').unbind();
			$('#' + $this._id + "_filter input").keyup( function(e) {
				if(e.keyCode == 13) {
					$this._table.search(this.value).draw();
				}
			});
		}
		
		if($this._rowClick != null) {
			$('#' + $this._id + ' tbody').on('click', 'tr', function(){
				if($(this).hasClass('selected')) {
					$this._rowClick($this._table.row(this).data());
				} else {
					if($this._rowUnclick != null) {
						$this._rowUnclick($this._table.row(this).data());
					}
				}
			});
		}
	},
	add : function(model, completeEvt) {
		var $this = this;
		$this._service.add(model, function(){
			$this._table.ajax.reload();
			completeEvt();
		});
	},
	update: function(model, completeEvt) {
		var $this = this;
		$this._service.update(model, function(){
			$this._table.ajax.reload();
			completeEvt();
		});
	},
	remove : function(id, completeEvt) {
		var $this = this;
		$this._service.remove(id, function(){
			$this._table.ajax.reload();
			completeEvt();
		});
	},
	removeByIds : function(ids, completeEvt) {
		var $this = this;
		$this._service.removeByIds(ids, function() {
			$this._table.ajax.reload();
			completeEvt();
		});
	},
	reload : function() {
		var $this = this;
		$this._table.ajax.reload();
	},
	getTable: function() {
		var $this = this;
		return $this._table;
	},
	search: function(searchTxt) {
		this._table.search(searchTxt).draw();
	},
	getSelectedItems: function() {
		return this._table.rows('.selected').data();
	},
	getAllRows: function() {
		return this._table.rows();
	},
	getSelectedItem: function() {
		return this._table.row('.selected').data();
	},
	selectAll: function() {
		this._table.rows().select();
	}
}