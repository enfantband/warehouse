function PickingJobProgressController(obj) {
	if(obj == null) {
		throw 'Please specify parameters';
	}
	
	if(obj.pickingJobService == null) {
		throw 'Please specify a service';
	}
	
	this._pickingJobService = obj.pickingJobService;
	if(obj.viewId == null || obj.viewId == '') {
		throw 'Please specify the viewId';
	}
	
	var today = new Date();
	var tomorrow = new Date();
	tomorrow.setDate(tomorrow.getDate() + 1);

	this._startDate = (today.getMonth()+1) + "/" + today.getDate() + "/" + today.getFullYear();
	this._endDate = (tomorrow.getMonth()+1) + "/" + tomorrow.getDate() + "/" + tomorrow.getFullYear();

	
	var $this = this;
	this._view = new PickingJobProgressView({
		viewId: obj.viewId
	});

	this._searchDate = obj.searchDate;
};
PickingJobProgressController.prototype = {
	init : function() {
		var $this = this;
		var model = this._pickingJobService.getList($this._startDate, $this._endDate);		
		$this._view.createView(model.content);		
	},
	load : function(searchDate) {
		var $this = this;
		if(searchDate == null) {
			var model = this._pickingJobService.getList($this._startDate, $this._endDate);
			$this._view.redraw(model.content);
		} else {
			var fromDate = new Date(searchDate);
			var toDate = new Date();
			toDate.setDate(fromDate.getDate() + 1);			
			$this._startDate = (fromDate.getMonth()+1) + "/" + fromDate.getDate() + "/" + fromDate.getFullYear();
			$this._endDate = (toDate.getMonth()+1) + "/" + toDate.getDate() + "/" + toDate.getFullYear();
			

			var model = this._pickingJobService.getList($this._startDate, $this._endDate);
			$this._view.redraw(model.content);
		}
	}
}