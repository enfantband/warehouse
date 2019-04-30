function RoleService() {
	this._serviceUrl = '/api/role';	
}

RoleService.prototype = {
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
			error:function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			success: function(data) {
				returnData = data;
			}
		});
		return returnData;
		
	},
	add : function(role, completeEvt) {
		var $this = this;
		if(role != null) {
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl,
				dataType: 'json',
				type: 'POST',
				data: JSON.stringify(role),
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	update: function(role, completeEvt) {
		var $this = this;
		if(role != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl,
				dataType:'json',
				type: 'PUT',
				data: JSON.stringify(role),
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	remove: function(roleId, completeEvt) {
		var $this = this;
		if(roleId != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/" + roleId,
				dataType:'json',
				type: 'DELETE',
				data: roleId,
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