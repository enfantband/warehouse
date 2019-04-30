function EmployeeService() {
	this._serviceUrl = '/api/employee';
	
}

EmployeeService.prototype = {
	getAll : function(){
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
		})
		return result;
	},
	getPickers : function() {
		var $this = this;
		var result = {};
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url:$this._serviceUrl + "/pickers",
			dataType: 'json',
			type: 'GET',
			error:function(e) {
				if(e.status == '401') {
					location.href = '/loginpage';
				} else {
					showMessage(e.statusText, e.responseJSON.error);
				}
			},
			async:false,
			success: function(data) {
				result = data;
			}
		})
		return result;
	},
	getOne: function(employeeId) {
		var result = {};
		var $this = this;
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url:$this._serviceUrl + "/" + employeeId,
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
	add : function(employee, completeEvt) {
		var $this = this;
		if(employee != null) {
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl,
				dataType: 'json',
				type: 'POST',
				data: JSON.stringify(employee),
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	addRoles: function(employeeId, roleIds, completeEvt) {
		var $this = this;
		$.ajax({
			contentType: 'application/json; charset=utf-8',
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url:$this._serviceUrl + "/" + employeeId + "/roles",
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
	addRole: function(employeeId, roleId, completeEvt) {
		var $this = this;
		$.ajax({
			contentType: 'application/json; charset=utf-8',
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url:$this._serviceUrl + "/" + employeeId + "/role/" + roleId,
			dataType: 'json',
			type: 'POST',
			error: function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			success: function(data) {
				completeEvt();
			}
		});
	},
	update: function(employee, completeEvt) {
		var $this = this;
		if(employee != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl,
				dataType:'json',
				type: 'PUT',
				data: JSON.stringify(employee),
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	remove: function(employeeId, completeEvt) {
		var $this = this;
		if(employeeId != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/" + employeeId,
				dataType:'json',
				type: 'DELETE',
				data: employeeId,
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