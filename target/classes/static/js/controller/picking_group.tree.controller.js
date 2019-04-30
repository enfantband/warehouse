function PickingGroupTreeController(obj) {
	this._serverUrl = obj.serverUrl;
	this._pickingGroupService = obj.pickingGroupService;
	this._companyListTable = obj.companyListTable;
	this._addModal = obj.modals[0];
	this._deleteModal = obj.modals[1];
	this._addForm = obj.forms[0];
	this._deleteForm = obj.forms[1];
	this._companyListId = obj.companyListId;
	this._addButtonForm = obj.addButtonForm;
	this._selectedPickingGroup = {};
	this._reloadEvent = obj.reloadEvent;
	
	var $this = this;
	$("#" + this._addButtonForm).click(function(e){
		$this.addCompany($this._selectedPickingGroup, $("#" + $this._companyListId).val());
	});
	
	this._viewId = "";
};

PickingGroupTreeController.prototype = {
	createTree: function( viewId ) {
		data = this._pickingGroupService.getListAll();
		this._viewId = viewId;
		this.createView(data)
	},
	reload: function() {
		this.createTree(this._viewId);
		if(this._reloadEvent != null) {
			this._reloadEvent();
		}
	},
	addCompany: function( pickingGroup, companyCode ) {
		var $this = this;
		this._pickingGroupService.addToPickingGroup(pickingGroup.pickingGroupId, companyCode, function(){
			$("#" + $this._addForm).trigger("reset");
			$("#" + $this._addModal).modal('hide');
			$this.reload();
		});
	},
	bindCompanyList: function() {
		// find maximum step
		var $this = this;
		$("#"+$this._companyListId).empty();
		var companyList = this._companyListTable;
		var maxStep = 0;
		
		for(var i in companyList) {
			var company = companyList[i];
			var opt = $("<option></option>").val(company.code).html(company.name);
			$("#" + this._companyListId).append(opt);
		}
	},
	createView(pickingGroups) {
		var $this = this;
		var viewId = this._viewId;
		$this.bindCompanyList();
		$("#" + viewId).empty();
		var treeLayer = $("<div></div>").addClass("tree smart-form");
		var groupUl = $("<ul></ul>");
		for(var i=0; i<pickingGroups.length; i++) {
			var groupLi = $("<li></li>");
			var groupSpan = $("<span></span>").append("<i class='fa fa-lg fa-group'></i> " + pickingGroups[i].name);
			var plusButton = $("<button></button>").attr("type", "submit").addClass("btn btn-warning btn-xs").append(" <i class='fa fa-plus'></i>").css("margin","3px");
			plusButton.bind("click", {pickingGroup:pickingGroups[i]}, function(event){
				$this._selectedPickingGroup = event.data.pickingGroup;
				$("#"+$this._addModal).modal('show');
			});
			var minusButton = $("<button></button>").attr("type", "submit").addClass("btn btn-danger btn-xs").append(" <i class='fa fa-minus'></i>");
			minusButton.bind("click", {pickingGroup:pickingGroups[i]}, function(event){
				var elements = $("input[name^='companies[" + event.data.pickingGroup.pickingGroupId + "]']:checked");
				if(elements.length > 0) {
					var vals = [];
					elements.each(function(){
						vals.push($(this).val());
					});
					for(var k=0; k<vals.length; k++) {
						var val = vals[k];
						if(k==vals.length-1) {
							$this._pickingGroupService.removeCompany(event.data.pickingGroup.pickingGroupId, val, function(){
								$this.reload();
							});
						} else {
							$this._pickingGroupService.removeCompany(event.data.pickingGroup.pickingGroupId, val, function(){});	
						}
					}

				} else {
					showMessage('Warning', 'Please check the company to remove');
				}
			});
			
			var companies = pickingGroups[i].pickingGroupCompanies;
			
			groupLi.append(groupSpan);
			groupLi.append(plusButton);
			groupLi.append(minusButton);
			for(var j=0; j<companies.length; j++) {
				var company = companies[j];
				var companyUl = $("<ul><li><span> <label class='checkbox inline-block'><input type='checkbox' name='companies[" + pickingGroups[i].pickingGroupId + "]["+company.companyCode+"]' value='"+company.companyCode+"'><i></i>" + $this._companyListTable[company.companyCode].name + "</label> </span></li></ul>");

				groupLi.append(companyUl);
			}

			groupUl.append(groupLi);
		}
		treeLayer.append(groupUl);
		$("#" + viewId).append(treeLayer);
	}

}