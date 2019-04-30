function WarehouseItemBoxService() {
	this._serviceUrl = "/api/warehouse/box";
};
WarehouseItemBoxService.prototype = {
	getListByLocationCode : function(locationCode) {
		
	},
	add: function(data, completeEvt) {
		$this = this;
		var levelId = data.levelId;
		var numberOfBoxes = data.numberOfBoxes;
		var boxPrefix = data.boxPrefix
		
		if(levelId != null && numberOfBoxes != null && boxPrefix != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url: $this._serviceUrl + "/level/" + levelId + "?boxPrefix=" + boxPrefix + "&numberOfBoxes=" + numberOfBoxes,
				dataType:'json',
				type: 'POST',
				error:function(e){
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			})
		}
	},
	removeByIds: function(itemBoxIds, completeEvt) {
		var $this = this;
		if(itemBoxIds != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/ids/" + itemBoxIds,
				dataType:'json',
				type: 'DELETE',
				data: itemBoxIds,
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	addItem: function(itemBoxId, barcode, completeEvt) {
		var $this = this;
		if(itemBoxId != null) {
			$.ajax({
				contentType: "application/json; charset-utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/" + itemBoxId + "/item/productBarcode/" + barcode,
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
	}
}