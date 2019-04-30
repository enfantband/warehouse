function WoonseokService() {
	this._serviceUrl = "/api/woonseok";
} 
WoonseokService.prototype = {
	process : function(requestId, completeEvt) {
		if(requestId != null) {
			$.ajax({
				dataType: 'json',
				headers: {
					"X-Authorization": "Bearer " + getToken()
				},
				url: this._serviceUrl + "/" + requestId + "/process",
				type: 'GET',
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	processMulti: function(requestIds, completeEvt) {
		if(requestIds != null) {
			$.ajax({
				dataType: 'json',
				headers: {
					"X-Authorization": "Bearer " + getToken()
				},
				url: this._serviceUrl + "/ids/" + requestIds + "/process",
				type: 'PUT',
				error: function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	
	complete : function(requestId, completeEvt) {
		if(requestId != null) {
			$.ajax({
				dataType: 'json',
				headers: {
					"X-Authorization": "Bearer " + getToken()
				},
				url: this._serviceUrl + "/" + requestId + "/complete",
				type: 'PUT',
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	completeMulti: function(requestIds, completeEvt) {
		if(requestIds != null) {
			$.ajax({
				dataType: 'json',
				headers: {
					"X-Authorization": "Bearer " + getToken()
				},
				url: this._serviceUrl + "/ids/" + requestIds + "/complete",
				type: 'PUT',
				error: function(e) {
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