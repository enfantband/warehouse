function WarehouseItemService() {
	this._serviceUrl = "/api/warehouse/item";
	
};

WarehouseItemService.prototype = {
	inbound : function(item, quantity, completedEvt) {
		var $this = this;
		if(item != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url: $this._serviceUrl + "/inbound/" + item.itemId + "?quantity=" + quantity,
				type: 'POST',
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completedEvt();
				}
			});
		} else {
			throw 'Item is null';
		}
	},
	outbound : function(item, quantity, completedEvt) {
		var $this = this;
		if(item != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/outbound/" + item.itemId + "?quantity=" + quantity,
				type: 'POST',
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completedEvt();
				}
			});
		} else {
			throw 'Item is null';
		}
	},
	add : function(item, completedEvt) {
		var barcode = item.barcode;		
		if(item != null) {
			var $this = this;
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/barcode/" + barcode + "?locationBarcode=" + item.locationBarcode + "&quantity=" + item.quantity,
				type: 'POST',
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completedEvt();
				}
			});
		} else {
			throw 'Item is null';
		}
	},
	removeByIds: function(itemIds, completeEvt) {
		var $this = this;
		if(itemIds != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/ids/" + itemIds,
				dataType:'json',
				type: 'DELETE',
				data: itemIds,
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