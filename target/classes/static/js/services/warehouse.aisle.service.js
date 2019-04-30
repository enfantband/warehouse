function WarehouseAisleService() {
	this._serviceUrl = "/api/warehouse/aisle";
};

WarehouseAisleService.prototype = {
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
	getListByWarehouseId : function(warehouseId) {
		var $this = this;
		var result = {};
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url:$this._serviceUrl + '/warehouse/' + warehouseId,
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
	getListByWarehouseCode : function(warehouseCode) {
		var $this = this;
		var result = {};
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url:$this._serviceUrl + '/warehouse/code/' + warehouseCode,
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
	add : function(warehouseAisle, completeEvt) {
		if(warehouseAisle.warehouse.warehouseId != null && warehouseAisle != null) {
			var $this = this;
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/warehouse/" + warehouseAisle.warehouse.warehouseId + "/aisle",
				data: JSON.stringify(warehouseAisle),
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
	update: function(warehouseAisle, completeEvt) {
		var $this = this;
		if(warehouseAisle != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/warehouse/" + warehouseAisle.warehouse.warehouseId + "/aisle",
				dataType:'json',
				type: 'PUT',
				data: JSON.stringify(warehouseAisle),
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	remove: function(warehouseAisleId, completeEvt) {
		var $this = this;
		if(warehouseAisleId != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/" + warehouseAisleId,
				dataType:'json',
				type: 'DELETE',
				data: warehouseAisleId,
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	removeByIds: function(warehouseAisleIds, completeEvt) {
		var $this = this;
		if(warehouseAisleIds != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/ids/" + warehouseAisleIds,
				dataType:'json',
				type: 'DELETE',
				data: warehouseAisleIds,
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