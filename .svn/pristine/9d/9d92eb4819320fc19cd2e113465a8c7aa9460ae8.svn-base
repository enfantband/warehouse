function PickingJobService() {

	this._serviceUrl = '/api/pickingJob';	
}

PickingJobService.prototype = {
	create : function(parameter, completeEvt, errorEvt) {
		var numOfProcess = parameter.numberOfOrders;
		var includingOrders = parameter.includingOrders;
		var amzhs = parameter.amzhs;
		var amzeb = parameter.amzeb;
		var $this = this;
		if(numOfProcess != null) {
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + '/create?numberOfProcess=' + numOfProcess + '&includingOrders=' + includingOrders + '&amzhs=' + amzhs + '&amzeb=' + amzeb,
				dataType: 'json',
				type: 'POST',
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
					errorEvt();
				},
				success: function(data) {
					completeEvt(data);
				}
			});
		}
	},
	getList: function(startDate, endDate, completeEvt) {
		var $this = this;
		var result = {};
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url: $this._serviceUrl + "/group",
			dataType: 'json',
			type: 'GET',
			data: {
				searchDateFrom: startDate,
				searchDateTo: endDate
			},
			async: false,
			error: function(e) {
				console.log(e);
				showMessage(e.statusText, e.responseJSON.error);
			},
			success: function(data) {
				result = data;
			}
		});
		return result;
	},
	assignJob: function(pickingJobId, pickerId, completeEvt ) {
		var $this = this;
		$.ajax({
			contentType: 'application/json; charset=utf-8',
			headers: {
				"X-Authorization": "Bearer " + getToken()
			},
			url: $this._serviceUrl + '/timeline/picker/' + pickerId + '/pickingJob/' + pickingJobId,
			dataType: 'json',
			type: 'POST',
			error: function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			success: function(data) {
				completeEvt(data);
			}
		});
	},
	startJob: function(pickingJobId, pickerId, completeEvt) {
		var $this = this;
		$.ajax({
			contentType: 'application/json; charset=utf-8',
			headers: {
				"X-Authorization": "Bearer " + getToken()
			},
			url: $this._serviceUrl + '/picker/' + pickerId + '/assignedJob/' + pickingJobId + '/start',
			dataType: 'json',
			type: 'POST',
			error: function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			success: function(data) {
				completeEvt(data);
			}
		});
	},
	requestMissingList: function(pickingJobGroupId, completeEvt) {
		var $this = this;
		if(pickingJobGroupId != null) {
			$.ajax({
				contentType: 'application/json; charset=utf-8',
				headers: {
					"X-Authorization": "Bearer " + getToken()
				},
				url: $this._serviceUrl + '/pickingJobGroup/' + pickingJobGroupId + '/missingItemPdf',
				dataType: 'json',
				type: 'POST',
				error: function(e) {
					completeEvt();
				},
				success: function(data) {
					completeEvt(data);
				}
			});
		}
	},
	withdrawPicker: function(timelineId, pickerId, completeEvt) {
		var $this = this;
		$.ajax({
			contentType: 'application/json; charset=utf-8',
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url: $this._serviceUrl + '/timeline/' + timelineId + '/picker/' + pickerId,
			dataType: 'json',
			type: 'DELETE',
			error: function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			success: function(data) {
				completeEvt(data);
			}
		});
	},
	deletePickingJobGroup : function(pickingJobGroupId, completeEvt) {
		var $this = this;
		$.ajax({
			contentType: 'application/json; charset=utf-8',
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url: $this._serviceUrl + "/group/" + pickingJobGroupId,
			dataType: 'json',
			type: 'DELETE',
			error: function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			success: function(data) {
				completeEvt(data);
			}
		});
	}
}