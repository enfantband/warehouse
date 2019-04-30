function PickingItemInfoService () {
	this._serviceUrl = "/api/pickingJob"
};

PickingItemInfoService.prototype = {
		
	getListByPickingJobId : function(pickingJobId, completeEvt) {
		if(pickingJobId != null) {
			var $this = this;
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + '/' + pickingJobId + '/pickingItemInfos',
				dataType: 'json',
				type: 'GET',
				error:function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt(data);
				}
			});
		}
	},
	
	addMissingItems : function(data, completeEvt) {
		if(data != null) {
			var $this = this;
			var pickingJobId = data.pickingJobId;
			var timelineId = data.timelineId;
			var pickingItemInfos = data.pickingItemInfos;
			var pickingDate = data.pickingDate;
			var pickingTime = data.pickingTime;
			$.ajax({
				contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + '/' + pickingJobId + '/timeline/' + timelineId + '/pickingItemInfos/missingItems?pickingDate=' + pickingDate + '&pickingTime=' + pickingTime,
				dataType: 'json',
				type: 'POST',
				data: JSON.stringify(pickingItemInfos),
				error: function(e) {
					showMessage(e.statusText, e.responseJSON.error);
				},
				success: function(data) {
					completeEvt(data);
				}
			});
			
		}
	}
}