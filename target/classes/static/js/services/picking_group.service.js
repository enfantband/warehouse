function PickingGroupService() {
	this._serviceUrl = '/api/picking/group';
}

PickingGroupService.prototype = {
	getListAll: function() {
		var returnData = {};
		$.ajax({
			dataType: 'json',
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url: this._serviceUrl,
			type: 'GET',
			async: false,
			data: {
				pageSize: 1000
			},
			error:function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			success: function(data) {
				
				returnData = data;
			}
		});
		return returnData;
		
	},
	add : function(pickingGroup, completeEvt) {
		var $this = this;
		if(pickingGroup != null) {
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl,
				dataType: 'json',
				type: 'POST',
				data: JSON.stringify(pickingGroup),
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	addToPickingGroup : function(pickingGroupId, companyCode, completeEvt) {
		var $this = this;
		if(pickingGroupId != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				url:$this._serviceUrl + "/" + pickingGroupId + "/company/" + companyCode,
				dataType:'json',
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
	removeCompany: function(pickingGroupId, companyCode, completeEvt) {
		var $this = this;
		if(pickingGroupId != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/" + pickingGroupId + "/company/" + companyCode,
				dataType:'json',
				type: 'DELETE',
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	update: function(pickingGroup, completeEvt) {
		var $this = this;
		if(pickingGroup != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl,
				dataType:'json',
				type: 'PUT',
				data: JSON.stringify(pickingGroup),
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	remove: function(pickingGroupId, completeEvt) {
		var $this = this;
		if(pickingGroupId != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/" + pickingGroupId,
				dataType:'json',
				type: 'DELETE',
				data: pickingGroupId,
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