function PrivilegeService() {
	this._serviceUrl = '/api/privilege';	
}

PrivilegeService.prototype = {
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
				pageSize: 1000,
				sortingFields: 'privilege',
				sortingDirection: 'asc'
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
	addToRole: function(roleId, privilegeId, completeEvt) {
		var $this = this;
		if(roleId != null && privilegeId != null) {
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/" + privilegeId + "/role/" + roleId,
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
	removeFromRole: function(roleId, privilegeId, completeEvt) {
		var $this = this;
		if(roleId != null && privilegeId != null) {
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/" + privilegeId + "/role/" + roleId,
				dataType: 'json',
				type: 'DELETE',
				async:false,
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	add : function(privilege, completeEvt) {
		var $this = this;
		if(privilege != null) {
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl,
				dataType: 'json',
				type: 'POST',
				data: JSON.stringify(privilege),
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	update: function(privilege, completeEvt) {
		var $this = this;
		if(privilege != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl,
				dataType:'json',
				type: 'PUT',
				data: JSON.stringify(privilege),
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	remove: function(privilege, completeEvt) {
		var $this = this;
		if(privilegeId != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/" + privilegeId,
				dataType:'json',
				type: 'DELETE',
				data: privilegeId,
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