function PrinterService() {
	this._serviceUrl = '/api/printer';
};
PrinterService.prototype = {
	getListAll : function() {
		var $this = this;
		var result = {};
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken()
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
	doPrintLocation : function(data, completeEvt) {
		var $this = this;
		var printerId = data.printerId;
		var levelIds = data.levelIds;
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken()
			},
			url:$this._serviceUrl + "/" + printerId + "/levelIds/" + levelIds,
			dataType: 'json',
			type: 'POST',
			error: function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			async: false,
			success: function(data) {
				completeEvt();
			}
		});
	},
	doPrintBox : function(data, completeEvt) {
		var $this = this;
		var printerId = data.printerId;
		var boxIds = data.boxIds;
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken()
			},
			url:$this._serviceUrl + "/" + printerId + "/boxIds/" + boxIds,
			dataType: 'json',
			type: 'POST',
			error: function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			async: false,
			success: function(data) {
				completeEvt();
			}
		});
	},
	add: function(item , completeEvt) {
		var $this = this;
		if(item != null) {
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl,
				dataType: 'json',
				type: 'POST',
				data: JSON.stringify(item),
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	update: function(item, completeEvt) {
		var $this = this;
		if(item != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl,
				dataType:'json',
				type: 'PUT',
				data: JSON.stringify(item),
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	removeByIds: function(ids, completeEvt) {
		var $this = this;
		if(ids != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/ids/" + ids,
				dataType:'json',
				type: 'DELETE',
				data: ids,
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