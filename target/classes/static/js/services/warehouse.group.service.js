function WarehouseGroupService() {
	this._serviceUrl = "/api/warehouse/group";
};

WarehouseGroupService.prototype = {
	getList : function() {
		var $this = this;
		var result = {};
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url:$this._serviceUrl,
			dataType: 'json',
			type: 'GET',
			error:function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			async:false,
			success: function(data) {
				result = data;
			}
		});

		return result;
	},
	getListAllByAisleId: function(aisleId) {
		var $this = this;
		var result = {};
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url:$this._serviceUrl + "/aisle/" + aisleId,
			dataType: 'json',
			data: aisleId,
			type: 'GET',
			error: function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			async: false,
			success: function(data) {
				result = data;
			}
		});
		
		return result;
	},
	getListByAisleId : function(aisleId) {
		var $this = this;
		var result = {};
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url:$this._serviceUrl,
			dataType: 'json',
			data: aisleId,
			type: 'GET',
			error: function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			async: false,
			success: function(data) {
				result = data;
			}
		});
		
		return result;
	},
	getListByAisleCode : function(aisleCode) {
		var $this = this;var result = {};
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url:$this._serviceUrl + '/warehouse/aisle/code/' + aisleCode,
			dataType: 'json',
			type: 'GET',
			error: function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			async: false,
			success: function(data) {
				result = data;
			}
		});
		
		return result;
	},
	add : function(data, completeEvt) {
		/*
		 * data { 
		 *  aisleId: aisleId,
		 *  warehouseGroup: warehouseGroup
		 * }
		 */
		var warehouseGroup = data.warehouseGroup;
		var aisleId = data.aisleId;
		var $this = this;
		if(warehouseGroup != null && aisleId != null) {
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url: $this._serviceUrl + "/aisle/" + aisleId,
				data: JSON.stringify(warehouseGroup),
				dataType: 'json',
				type: 'POST',
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	update: function(data, completeEvt) {
		var $this = this;
		var warehouseGroup = data.warehouseGroup;
		var aisleId = data.aisleId;
		
		if(warehouseGroup != null && aisleId != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/aisle/" + aisleId,
				dataType:'json',
				type: 'PUT',
				data: JSON.stringify(warehouseGroup),
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	remove: function(warehouseGroupId, completeEvt) {
		var $this = this;
		if(warehouseGroupId != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/" + warehouseGroupId,
				dataType:'json',
				type: 'DELETE',
				data: warehouseGroupId,
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	removeByIds: function(warehouseGroupIds, completeEvt) {
		var $this = this;
		if(warehouseGroupIds != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/ids/" + warehouseGroupIds,
				dataType:'json',
				type: 'DELETE',
				data: warehouseGroupIds,
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	}
}