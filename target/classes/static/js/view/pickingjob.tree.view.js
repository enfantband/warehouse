function PickingJobTreeView(obj) {
	if(obj == null) {
		throw 'Please specify parameters';
	}
	this._viewId = obj.viewId;
	this._assignAction = obj.assignAction;
	this._startAction = obj.startAction;
	this._groupDeleteAction = obj.groupDeleteAction;
	this._withdrawPickerAction = obj.withdrawPickerAction;
	this._packingListDownloadAction = obj.packingListDownloadAction;
	this._pickingListDownloadAction = obj.pickingListDownloadAction;
	this._openMissingItemInputWindowAction = obj.openMissingItemInputWindowAction;
	this._missingListDownloadAction = obj.missingListDownloadAction;
	this._requestMissingListAction = obj.requestMissingListAction;
};
PickingJobTreeView.prototype = {
	createView : function(pickingJobGroups, pickers) {
		var $this = this;
		var mainView = $("#" + this._viewId).addClass("tree");
		var ul = $("<ul></ul>");
		$this.createList(ul, pickingJobGroups, pickers);
		mainView.append(ul);
	},
	redraw : function(pickingJobGroups, pickers) {
		var $this = this;
		$("#" + this._viewId).empty();
		var ul = $("<ul></ul>");
		$this.createList(ul, pickingJobGroups, pickers);
		$("#" + this._viewId).append(ul);
	},
	confirmWithdrawPicker: function(timelineId, warehouseEmployeeId) {
		var $this= this;
		showMessageYesNo('timeline-delete-confirm', 'Withraw Picker', 'Are you sure?', 'Delete', 'Cancel', function(data){
			$this._withdrawPickerAction(data.timelineId, data.warehouseEmployeeId, function(){
				$("#timeline-delete-confirm").modal('hide');
			});
		}, {timelineId:timelineId, warehouseEmployeeId:warehouseEmployeeId});
	},
	createGroupJob : function(subGroupLi, jobList, pickers) {
		var $this = this;
		for(var j=0; j<jobList.length; j++) {
			var groupUl = $("<ul></ul>");
			var groupLi = $("<li></li>");
			var job = jobList[j];
			var pickingGroup = job.pickingGroup;
			var groupName = pickingGroup.name;
			var groupDescription = pickingGroup.description;
			var productType = pickingGroup.productType;
			var pickingItemInfos = job.pickingItemInfos;
			
			// If this group is to pick later, then put `save for later` mark at the front
			var jobLabel = 'label-success';
			var jobIcon = "<i class='fa fa-lg fa-plus-circle'></i> ";
			if(job.saved) {
				jobLabel = 'label-warning';
				var jobIcon = "<i class='fa fa-lg fa-save'></i> [Saved] ";
			}
			
			var groupInfoSpan = $("<span></span>").addClass("label " + jobLabel).append(jobIcon + groupName + " (" + groupDescription + ")");
			var itemsInfoSpan = $("<span></span>").addClass("label label-default").append("Items (<em>" + pickingItemInfos.length + "</em>)");

			groupLi.append(groupInfoSpan);
			groupLi.append(itemsInfoSpan);
			// If there is a saved picking list then show a button to download.
			var pickingJobExport = job.pickingJobExport;
			if(pickingJobExport != null) {
				var pickingJobDownloadButton = $("<button><i class='fa fa-lg fa-file-pdf-o'></i> Picking List</button>").addClass("btn btn-default btn-xs");
				pickingJobDownloadButton.bind("click", {pickingJobId: job.pickingJobId}, function(event){
					$this._pickingListDownloadAction(event.data.pickingJobId, function(pickingJobId, token) {
						window.open("/download/picking/" + pickingJobId + "?token=" + token, "_blank");	
					});
				});
				groupLi.append(pickingJobDownloadButton);
				
			}
			
			groupUl.append(groupLi);
			subGroupLi.append(groupUl);
			var itemsUl = $("<ul></ul>");
			groupLi.append(itemsUl);
			var timelines = job.pickingJobTimelines;
			var lastStatus = '';
			for(var k=0; k<timelines.length; k++) {
				var timeline = timelines[k];
				if(timeline != null) {
					var itemLi = $("<li></li>");

					
					var eventTime = '';
					if(eventTime!= null) {
						var date = new Date(timeline.eventTime);
						eventTime = date.toLocaleDateString('en-US') + ' ' + date.toLocaleTimeString('en-US');;
					}
					var preposition = 'by';
					if(timeline.pickingJobStatusTo == 'Assigned') {
						preposition = 'to'
					}
					var itemSpan = $("<span></span>").append("<i class='fa fa-clock-o'></i> " + eventTime + " &ndash; " + timeline.pickingJobStatusTo + " " + preposition + " " + timeline.warehouseEmployee.name + " ");
					if(timeline.pickingJobStatusTo == 'Assigned' && job.pickingStatus == 'Assigned') {
						var withdrawBt = $("<button>x</button>").attr("timelineId", timeline.timelineId).attr("warehouseEmployeeId", timeline.warehouseEmployee.warehouseEmployeeId).addClass("btn btn-danger btn-xs").html("&times;");
						itemSpan.append(withdrawBt);
						withdrawBt.bind("click", {timeline: timeline}, function(e) {
							var timelineId = e.data.timeline.timelineId;
							var warehouseEmployeeId = e.data.timeline.warehouseEmployee.warehouseEmployeeId;
							var $bt = this;
							$this.confirmWithdrawPicker(timelineId, warehouseEmployeeId);
						});	
												
						itemSpan.append("&nbsp;");
						var startBt = $("<button></button>").addClass("btn btn-primary btn-xs").html("Start");
						itemSpan.append(startBt);
						startBt.bind("click", {pickingJobId: job.pickingJobId, timeline: timeline}, function(e){
							var warehouseEmployeeId = e.data.timeline.warehouseEmployee.warehouseEmployeeId;
							$this._startAction(e.data.pickingJobId, warehouseEmployeeId, function(){
								//...
							});
						});
					}
					
					itemLi.append(itemSpan);
					itemsUl.append(itemLi);
				}
				if(timeline.pickingJobStatusTo == 'Started') {
					// Missing Input
					var manualFinishBt = $("<button></button>").addClass("btn btn-default btn-xs").html("Manage");
					itemSpan.append(manualFinishBt);
					manualFinishBt.bind("click", {pickingJobId: job.pickingJobId, timeline: timeline}, function(e){
						var timelineId = e.data.timeline.timelineId;
						var warehouseEmployeeId = e.data.timeline.warehouseEmployee.warehouseEmployeeId;
						$this._openMissingItemInputWindowAction(e.data.pickingJobId, timelineId, warehouseEmployeeId);
					});
				}
				if(timeline.pickingJobStatusTo == 'Paused' || timeline.pickingJobStatusTo == 'Finished') {
					// add summary 
					var summaryLi = $("<li></li>");
					
					var pickingJobTimelineReport = timeline.pickingJobTimelineReport;
					
					if(pickingJobTimelineReport != null){
						var totalPicked = pickingJobTimelineReport.totalPicked + pickingJobTimelineReport.totalPickedWithoutScan;
						var summarySpan = $("<span></span>").addClass("label label-primary").html("Picking Total: " + totalPicked);
						var summaryTimeSpan = $("<span></span>").addClass("label label-primary").html('<i class="fa fa-clock-o"></i> ' + millisecondsToTime(pickingJobTimelineReport.totalTimeSpent));
						summaryLi.append(summarySpan).append(summaryTimeSpan);	

						if(timeline.pickingJobStatusTo == 'Finished' && job.missingExport != null) {
							var buttonLi = $("<li></li>");
							var downloadMissingBt = $("<button><i class='fa fa-lg fa-file-pdf-o'></i> Missing List</button>").addClass("btn btn-default btn-xs");
							
							downloadMissingBt.bind('click', {pickingJobId: job.pickingJobId}, function(event){
								$this._missingListDownloadAction(event.data.pickingJobId, function(pickingJobId, token){
									window.open("/download/missing/" + pickingJobId + "?token=" + token, "_blank");
								});
							});
							summaryLi.append(downloadMissingBt);
						}
					}
					
					itemsUl.append(summaryLi);
					
				}
				if(k == timelines.length - 1) {
					lastStatus = timeline.pickingJobStatusTo;
				}
			}

			if(timelines.length == 0 || lastStatus == 'Handed Over') {
				var assignButton = $("<button></button>").addClass("btn btn-default btn-xs").html("<i class='fa fa-plus'></i> Assign");
				assignButton.bind("click", {pickingJob: job}, function(event) {
					$("#assignPickerWindow").remove();
					var closeButton = $("<button></button>").attr("type", "button").addClass("close").attr("data-dismiss", "modal").attr("aria-hidden", "true").html('&times;');
					var title = $("<h4></h4>").addClass("modal-title").html("Assign Picker");
					
					var modalHeader = $("<div></div>").addClass("modal-header");
					modalHeader.append(closeButton);
					modalHeader.append(title);
					
					var form = $("<form></form>").addClass("smart-form");
					var label = $("<label></label>").addClass("select").append("<i class='icon-append fa fa-user'></i> ");
					var select = $("<select></select>").attr("id", "assigned_picker");

					select.append($("<option></option>").val("").html("---- select ----"));
					for(var i=0; i<pickers.length; i++) {
						var picker = pickers[i];
						var option = $("<option></option>").val(picker.warehouseEmployeeId).html(picker.name);
						select.append(option);
					}
					label.append(select);
					form.append(label);
					
					var modalBody = $("<div></div>").addClass("modal-body").append(form);
					
					var assign = $("<button></button>").attr("type", "button").addClass("btn btn-primary").html("Assign");
					assign.click(function(){
						if($("#assigned_picker").val() == '') {
							showMessage("Error", "Please select a picker");
						} else {
							$this._assignAction(event.data.pickingJob.pickingJobId, $("#assigned_picker").val(), function(){
								modalMessage.modal('hide');
							});	
						}
					})
					var cancel = $("<button></button>").attr("type", "button").addClass("btn btn-default").attr("data-dismiss","modal").html("Cancel");
					var modalFooter = $("<div></div>").addClass("modal-footer").append(cancel);
					modalFooter.append(assign);
					
					var modalDialog = $("<div></div>").addClass("modal-dialog");
					
					var modalContent = $("<div></div>").addClass("modal-content");
					modalContent.append(modalHeader);
					modalContent.append(modalBody);
					modalContent.append(modalFooter);
					
					modalDialog.append(modalContent);
					
					var modalMessage = 
						$("<div></div>").addClass("modal fade").attr("id","assignPickerWindow").attr("tabindex", "1").attr("role", "dialog").attr("aria-hidden", "true");
					modalMessage.append(modalDialog);
					$('#main').append(modalMessage);
					
					modalMessage.modal('show');
				});
				groupLi.append(assignButton);				
			}
			subGroupLi.append(itemsUl);
			
		}
		subGroupLi.append(itemsUl);
		
	},
	createList : function(ul, pickingJobGroups, pickers) {
		var $this = this;
		for(var key in pickingJobGroups) {
			var pickingJobGroup = pickingJobGroups[key];
			var pickingJobs = pickingJobGroup.pickingJobs;
			var length = pickingJobs.length;
			var pickingSet = pickingJobGroup.pickingSet;
			var totalItems = pickingJobGroup.totalItems;
			
			var groupLi = $("<li></li>");
			var groupTitle = $("<span></span>").append("<i class='fa fa-lg fa-calendar'></i> " + "Picking #" + pickingSet);
			groupLi.append(groupTitle);
			groupLi.append("<span> Items (<em>" + totalItems + "</em>)</span> ");
			
			var packingDownBt = $("<button><i class='fa fa-lg fa-file-pdf-o'></i> Packing List</button>").addClass("btn btn-default btn-xs");
			groupLi.append(packingDownBt);
			packingDownBt.bind('click', {pickingJobGroup: pickingJobGroup}, function(event){
				$this._packingListDownloadAction(event.data.pickingJobGroup.pickingJobGroupId, function(pickingJobGroupId, token){
				
					window.open("/download/packing/" + pickingJobGroupId + "?token=" + token, "_blank");					
				});
			});
			
			groupLi.append("&nbsp;")
			var generatedMissingBt = $("<button> Generate Missing List</button>").addClass("btn btn-default btn-xs");
			groupLi.append(generatedMissingBt);
			generatedMissingBt.bind('click', {pickingJobGroupId: pickingJobGroup.pickingJobGroupId}, function(event){
				$this._requestMissingListAction(event.data.pickingJobGroupId);
			});			
			
			// This remove button is only for testing. Please if you need this function to remove a job list then you should implement it.
			
//			var deleteGroupBt = $("<button>remove</button>").addClass("btn btn-danger btn-xs").html("remove");
//			groupLi.append(deleteGroupBt);
//			
//			deleteGroupBt.bind("click", {pickingJobGroup: pickingJobGroup}, function(event) {
//				showMessageYesNo('pickinggroup-delete-confirm', 'Delete Picking Job Group', 'Are you sure?', 'Delete', 'Cancel', function(pickingJobGroupId){
//					$this._groupDeleteAction(pickingJobGroupId, function(){
//						$("#pickinggroup-delete-confirm").modal('hide');
//					});
//				}, event.data.pickingJobGroup.pickingJobGroupId);
//			});
			

			var hairList = [];
			var gmList = [];
			for(var i=0; i<length; i++) {
				// divide by productType
				var productType = pickingJobs[i].pickingGroup.productType;
				

				// Each Group
				var pickingJob = pickingJobs[i];				
				if(productType.toLowerCase() == 'hair') {
					hairList.push(pickingJob);
				} else {
					gmList.push(pickingJob);
				}
			}

			var subGroupUl = $("<ul></ul>");
			groupLi.append(subGroupUl);
			if(hairList.length > 0) {
				var subGroupLi = $("<li></li>");
				var subGroupSpan = $("<span></span>").addClass("label bg-color-magenta").html("Hair");
				
				subGroupLi.append(subGroupSpan);
				this.createGroupJob(subGroupLi, hairList, pickers);
				subGroupUl.append(subGroupLi);
			}
			if(gmList.length > 0) {
				var subGroupLi = $("<li></li>");
				var subGroupSpan = $("<span></span>").addClass("label label-warning").html("GM");
				
				subGroupLi.append(subGroupSpan);
				this.createGroupJob(subGroupLi, gmList, pickers);
				subGroupUl.append(subGroupLi);
			}
			ul.append(groupLi);
		}
	}

};
