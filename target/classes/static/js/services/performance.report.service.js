function PerformanceReportService() {
	this._serviceUrl = "/api/pickingJobTimelineReport";
};

PerformanceReportService.prototype = {
	getIndividualReport : function(employeeId, reportDate, completeEvt) {
		if(employeeId != null) {
			var $this = this;
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				headers: {
					"X-Authorization": "Bearer " + getToken(),
				},
				url:$this._serviceUrl + '/individual/' + employeeId + "?reportDate=" + reportDate,
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
	}
}