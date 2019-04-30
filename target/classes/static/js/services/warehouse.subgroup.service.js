function WarehouseSubgroupService() {
	this._serviceUrl = "/api/warehouse/subgroup";
};

WarehouseSubgroupService.prototype = {
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
	getListAllByGroupId : function(groupId) {
		var $this = this;
		var result = {};
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url:$this._serviceUrl + '/group/' + groupId,
			data: groupId,
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
	getListByGroupId : function(groupId) {
		var $this = this;
		var result = {};
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url:$this._serviceUrl,
			data: groupId,
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
	getListByGroupCode : function(groupCode) {
		var $this = this;
		var result = {};
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url:$this._serviceUrl + '/warehouse/group/code/' + groupCode,
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
	add : function(data, completeEvt) {
		var warehouseSubgroup = data.warehouseSubgroup;
		var groupId = data.groupId;
		if(groupId != null && warehouseSubgroup != null) {
			var $this = this;
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/group/" + groupId,
				data: JSON.stringify(warehouseSubgroup),
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
	update: function(warehouseSubgroup, completeEvt) {
		var $this = this;
		if(warehouseSubgroup != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl,
				dataType:'json',
				type: 'PUT',
				data: JSON.stringify(warehouseSubgroup),
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	remove: function(subgroupId, completeEvt) {
		var $this = this;
		if(subgroupId != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/" + subgroupId,
				dataType:'json',
				type: 'DELETE',
				data: subgroupId,
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	removeByIds: function(subgroupIds, completeEvt) {
		var $this = this;
		if(subgroupIds != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/ids/" + subgroupIds,
				dataType:'json',
				type: 'DELETE',
				data: subgroupIds,
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