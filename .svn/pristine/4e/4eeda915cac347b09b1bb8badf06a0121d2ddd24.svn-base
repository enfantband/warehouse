function PickingJobTreeController(obj) {
	if(obj == null) {
		throw "Please specify the parameters";
	}
	
	if(obj.pickingJobService == null) {
		throw 'Please specify the service';
	}
	this._pickingJobService = obj.pickingJobService;
	if(obj.employeeService == null) {
		throw 'Please specify the employee service';
	}
	this._employeeService = obj.employeeService;

	
	if(obj.viewId == null || obj.viewId == '') {
		throw 'Please specify the viewId which layer you want to draw this tree';
	}
	
	if(obj.filedownloadTokenService == null) {
		throw 'Please specify the filedownload token service';
	}
	this._filedownloadTokenService = obj.filedownloadTokenService;
	
	if(obj.openMissingItemInputWindowAction == null) {
		throw 'Please specifiy openMissingItemInputWindowAction';
	}
	this._openMissingItemInputWindowAction = obj.openMissingItemInputWindowAction;

	var today = new Date();
	var tomorrow = new Date();
	tomorrow.setDate(tomorrow.getDate() + 1);

	this._startDate = (today.getMonth()+1) + "/" + today.getDate() + "/" + today.getFullYear();
	this._endDate = (tomorrow.getMonth()+1) + "/" + tomorrow.getDate() + "/" + tomorrow.getFullYear();

	
	var $this = this;
	this._view = new PickingJobTreeView({
		viewId: obj.viewId,
		assignAction: function(pickingJobId, pickerId, completeEvent) {
			$this._pickingJobService.assignJob(pickingJobId, pickerId, function(){
				completeEvent();
				var model = $this._pickingJobService.getList($this._startDate, $this._endDate);
				var pickers = $this._employeeService.getPickers();
				$this._view.redraw(model.content, pickers);		
			});
		},
		startAction: function(pickingJobId, pickerId, completeEvent) {
			$this._pickingJobService.startJob(pickingJobId, pickerId, function(){
				completeEvent();
				var model = $this._pickingJobService.getList($this._startDate, $this._endDate);
				var pickers = $this._employeeService.getPickers();
				$this._view.redraw(model.content, pickers);		
			})
		},
		withdrawPickerAction: function(timelineId, pickerId, completeEvent) {
			$this._pickingJobService.withdrawPicker(timelineId, pickerId, function() {
				completeEvent();
				var model = $this._pickingJobService.getList($this._startDate, $this._endDate);
				var pickers = $this._employeeService.getPickers();
				$this._view.redraw(model.content, pickers);		
			})
		},
		groupDeleteAction: function(pickingJobGroupId, completeEvent) {
			$this._pickingJobService.deletePickingJobGroup(pickingJobGroupId, function(){
				completeEvent();
				var model = $this._pickingJobService.getList($this._startDate, $this._endDate);
				var pickers = $this._employeeService.getPickers();
				$this._view.redraw(model.content, pickers);				
			});
		},
		packingListDownloadAction: function(pickingJobGroupId, completeEvent) {
			var token = $this._filedownloadTokenService.getDownloadToken();			
			completeEvent(pickingJobGroupId, token);
		},
		pickingListDownloadAction: function(pickingJobId, completeEvent) {
			var token = $this._filedownloadTokenService.getDownloadToken();
			completeEvent(pickingJobId, token);
		},
		openMissingItemInputWindowAction: function(pickingJobId, timelineId, employeeId) {
			$this._openMissingItemInputWindowAction(pickingJobId, timelineId, employeeId);
		},
		missingListDownloadAction: function(pickingJobId, completeEvent) {
			var token = $this._filedownloadTokenService.getDownloadToken();
			completeEvent(pickingJobId, token);
		},
		requestMissingListAction: function(pickingJobGroupId) {
			$this._pickingJobService.requestMissingList(pickingJobGroupId, function(){
				var model = $this._pickingJobService.getList($this._startDate, $this._endDate);
				var pickers = $this._employeeService.getPickers();
				$this._view.redraw(model.content, pickers);
			});
		}
	});
	this._searchDate = obj.searchDate;
};

PickingJobTreeController.prototype = {
	init : function() {
		var $this = this;
		var model = this._pickingJobService.getList($this._startDate, $this._endDate);
		var pickers = this._employeeService.getPickers();
		$this._view.createView(model.content, pickers);		
	},
	load : function(searchDate) {
		var $this = this;
		if(searchDate == null) {
			var model = this._pickingJobService.getList($this._startDate, $this._endDate);			
			var pickers = this._employeeService.getPickers();
			$this._view.redraw(model.content, pickers);
		} else {
			var fromDate = new Date(searchDate);
			var toDate = new Date();
			toDate.setDate(fromDate.getDate() + 1);			
			$this._startDate = (fromDate.getMonth()+1) + "/" + fromDate.getDate() + "/" + fromDate.getFullYear();
			$this._endDate = (toDate.getMonth()+1) + "/" + toDate.getDate() + "/" + toDate.getFullYear();
			

			var model = this._pickingJobService.getList($this._startDate, $this._endDate);			
			var pickers = this._employeeService.getPickers();
			$this._view.redraw(model.content, pickers);
		}
	}
}

