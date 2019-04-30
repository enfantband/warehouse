function MenuService() {
	this._serviceUrl = '/api/menu';
	
}

MenuService.prototype = {
	getList : function() {
		var result = {};
		var $this = this;
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken()
			},
			url:$this._serviceUrl + "/adminmenu",
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
	getOne : function(menuId) {
		var result = {};
		var $this = this;
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken()
			},
			url:$this._serviceUrl + "/" + menuId,
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
	add : function(menu, completeEvt) {
		var $this = this;
		if(menu != null) {
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				url:$this._serviceUrl,
				headers: {
					"X-Authorization": "Bearer " + getToken()
				},
				dataType: 'json',
				type: 'POST',
				data: JSON.stringify(menu),
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	update: function(menu, completeEvt) {
		var $this = this;
		if(menu != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				url:$this._serviceUrl,
				headers: {
					"X-Authorization": "Bearer " + getToken()
				},
				dataType:'json',
				type: 'PUT',
				data: JSON.stringify(menu),
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	remove: function(menuId, completeEvt) {
		var $this = this;
		if(menuId != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken()
				},
				url:$this._serviceUrl + "/" + menuId,
				dataType:'json',
				type: 'DELETE',
				data: menuId,
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
	},
	addRoles: function(menuId, roleIds, completeEvt) {
		var $this = this;
		$.ajax({
			contentType: 'application/json; charset=utf-8',
			headers: {
				"X-Authorization": "Bearer " + getToken()
			},
			url:$this._serviceUrl + "/" + menuId + "/roles",
			dataType: 'json',
			type: 'POST',
			data: JSON.stringify(roleIds),
			error: function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			success: function(data) {
				completeEvt();
			}
		});
	},
	addRole: function(menuId, roleId, completeEvt) {
		var $this = this;
		$.ajax({
			contentType: 'application/json; charset=utf-8',
			headers: {
				"X-Authorization": "Bearer " + getToken()
			},
			url:$this._serviceUrl + "/" + menuId + "/role/" + roleId,
			dataType: 'json',
			type: 'POST',
			error: function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			success: function(data) {
				completeEvt();
			}
		});
	}
}