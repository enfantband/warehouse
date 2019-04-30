function PrinterLabelService() {
	this._serviceUrl = "/api/printer/label";
};

PrinterLabelService.prototype = {
	getListAll : function() {
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
	getListForSelect : function() {
		var $this = this;
		var result = [];
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url:$this._serviceUrl + '/',
			dataType: 'json',
			type: 'GET',
			error:function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			async:false,
			success: function(data) {
				var length = data.length;
				for(var i=0; i<length; i++) {
					var item = {};
					item.key = data[i].labelId;
					item.value = data[i].labelName;
					result.push(item);
				}
			}
		});

		return result;
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