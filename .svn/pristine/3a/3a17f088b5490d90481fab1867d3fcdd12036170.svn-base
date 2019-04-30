function WarehouseLevelService() {
	this._serviceUrl = "/api/warehouse/level";
};

WarehouseLevelService.prototype = {
	removeAll : function(ids, completeEvent) {
		
	},
	add: function( data, completeEvt) {
		var subgroupId = data.subgroupId;
		var numberOfLevels = data.numberOfLevels;
		var levelType = data.levelType;
		var levelStatus = data.levelStatus;
		if(numberOfLevels != null) {
			var $this = this;
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url: $this._serviceUrl + "/subgroup/" + subgroupId + "?numberOfLevels=" + numberOfLevels + "&levelType=" + levelType + "&levelStatus=" + levelStatus,
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
	update: function(data, completeEvt ) {
		var levelId = data.levelId;
		if(data != null){
			var $this = this;
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url: $this._serviceUrl,
				data: JSON.stringify(data),
				dataType: 'json',
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
	removeByIds: function(levelIds, completeEvt) {
		var $this = this;
		if(levelIds != null) {
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + "/ids/" + levelIds,
				dataType:'json',
				type: 'DELETE',
				data: levelIds,
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt();
				}
			});
		}
	},
	getLevelStatusListForSelect : function() {
		var $this = this;
		var result = [];
		$.ajax({
	        contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url: $this._serviceUrl + "/levelStatus",
			dataType: 'json',
			type: 'GET',
			error:function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			async:false,
			success: function(data) {
				for(var key in data){
					var obj = {key: data[key], value:data[key]};
					result.push(obj);
				}
			}
		});
		return result;
	},
	getLevelTypeListForSelect : function() {
		var $this = this;
		var result = [];
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url: $this._serviceUrl + "/levelType",
			dataType: 'json',
			type: 'GET',
			error:function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			async:false,
			success: function(data) {
				for(var key in data){
					var obj = {key: data[key], value:data[key]};
					result.push(obj);
				}
			}
		});
		return result;
	}
}