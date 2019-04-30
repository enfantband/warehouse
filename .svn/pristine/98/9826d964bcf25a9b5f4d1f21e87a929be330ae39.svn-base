function PickingJobProgressView(obj) {
	if(obj == null) {
		throw 'Please specify parameters';
	}
	this._viewId = obj.viewId;	
};
PickingJobProgressView.prototype = {
	createView : function(pickingJobGroups) {
		var $this = this;
		// Get all picking jobs
		for(var i in pickingJobGroups) {
			var pickingJobGroup = pickingJobGroups[i];
			var pickingJobs = pickingJobGroup.pickingJobs;

			var pickingSet = pickingJobGroup.pickingSet;
			var length = pickingJobs.length;
			
			var hairList = [];
			var gmList = [];			
			for(var j=0; j<length; j++) {
				var pickingJob = pickingJobs[j];
				var productType = pickingJob.pickingGroup.productType;
				
				if(productType.toLowerCase() == 'hair') {
					hairList.push(pickingJob);
				} else {
					gmList.push(pickingJob);
				}
			}
			
			if(hairList.length > 0) {
				var pDiv = $("<div></div>");
				var pPha = $("<p></p>").html("Progress of Picking Job for Hair [Picking #" + pickingSet + "]");
				var pcDiv = $("<div></div>").addClass("well no-padding");
				pDiv.append(pPha);
				pDiv.append(pcDiv);
				for(var i=0; i<hairList.length; i++) {
					var pickingJob = hairList[i];
					var numberOfItems = 0;
					var numberOfPickedItems = 0;
					for(var j=0; j<pickingJob.pickingItems.length; j++) {
						numberOfPickedItems += pickingJob.pickingItems[j].quantity;
					}
					for(var j=0; j<pickingJob.pickingItemInfos.length; j++) {
						numberOfItems += pickingJob.pickingItemInfos[j].orderQuantity;
					}
					console.log("numberOfPickedItems: " + numberOfPickedItems);
					console.log("numberOfItems" + numberOfItems);
					var percent = 0;
					if(numberOfPickedItems > 0 && numberOfItems > 0) {
						percent = parseInt(parseFloat(numberOfPickedItems/numberOfItems) * 100);
					}
					var pickingGroup = pickingJob.pickingGroup;
					var groupName = pickingGroup.name;
					var groupDescription = pickingGroup.description;
					var barDiv = $("<div></div>").addClass("bar-holder");
					color = $this.getColor(percent);
					var barSpan = $("<span></span>").addClass("label " + color).html(groupName);
					var progDiv = $("<div></div>").addClass("progress");
					var progBar = $("<div></div>").addClass("progress-bar " + color).attr("data-transitiongoal", percent);
					
					pcDiv.append(barDiv);
					barDiv.append(barSpan);
					barDiv.append(progDiv);
					progDiv.append(progBar);
				}

				$("#" + $this._viewId).append(pDiv);
				// total 
			}
			if(gmList.length > 0) {
				var pDiv = $("<div></div>");
				var pPha = $("<p></p>").html("Progress of Picking Job for GM [Picking #" + pickingSet + "]");
				var pcDiv = $("<div></div>").addClass("well no-padding");
				pDiv.append(pPha);
				pDiv.append(pcDiv);
				for(var i=0; i<gmList.length; i++) {
					var pickingJob = gmList[i];
					
					var numberOfItems = 0;
					var numberOfPickedItems = 0;
					for(var j=0; j<pickingJob.pickingItems.length; j++) {
						numberOfPickedItems += pickingJob.pickingItems[j].quantity;
					}
					for(var j=0; j<pickingJob.pickingItemInfos.length; j++) {
						numberOfItems += pickingJob.pickingItemInfos[j].orderQuantity;
					}
					var percent = 0;
					if(numberOfPickedItems > 0 && numberOfItems > 0) {
						percent = parseInt(parseFloat(numberOfPickedItems/numberOfItems) * 100);
					}
					var pickingGroup = pickingJob.pickingGroup;
					var groupName = pickingGroup.name;
					var groupDescription = pickingGroup.description;
					var barDiv = $("<div></div>").addClass("bar-holder");
					color = $this.getColor(percent);
					var barSpan = $("<span></span>").addClass("label " + color).html(groupName);
					var progDiv = $("<div></div>").addClass("progress");
					var progBar = $("<div></div>").addClass("progress-bar " + color).attr("data-transitiongoal", percent);
					
					pcDiv.append(barDiv);
					barDiv.append(barSpan);
					barDiv.append(progDiv);
					progDiv.append(progBar);
				}

				$("#" + $this._viewId).append(pDiv);
			}
			$('.progress .progress-bar').progressbar({display_text: 'fill', use_percentage: true});
		}
	},
	redraw : function(pickingJobGroups, pickers) {
		var $this = this;
		$("#" + this._viewId).empty();
		$this.createView(pickingJobGroups);
	},
	getColor : function(percent) {
		color = 'bg-color-red';
		if(percent < 10) {
			color = "bg-color-red";
		} else if(percent < 30) {
			color = "bg-color-redLight";			
		} else if(percent < 50) {
			color = "bg-color-blueLight";
		} else if(percent < 70) {
			color = "bg-color-blue";			
		} else if(percent < 90) {
			color = "bg-color-greenLight";			
		} else if(percent == 100) {
			color = "bg-color-green";
		}
		return color;
		
	}
}