function WarehouseSubgroupController(obj) {
	if(obj.selectorId) {
		this._selectorId = obj.selectorId;
	}
	
	if(obj.managePopupId) {
		this._managePopupId = obj.managePopupId;
	}
	this._service = obj.service;
	this._changeEvent = obj.changeEvent;
	
	var $this = this;
	this._view = new WarehouseSubgroupView({
		warehouseSubgroupSelectorChangeEvent: function(data) {
			$this._changeEvent(data);
			
		}
	});	
};

WarehouseSubgroupController.prototype = {


	createSelector : function(groupId) {
		var $this = this;
		var data = $this._service.getListAllByGroupId(groupId);
		
		this._view.createSelector(this._selectorId, data, function(){
			
		});
		
	},
	getSelectedValue : function() {
		return $("#"+this._selectorId).val();
	},
	emptySelector : function() {
		this._view.emptySelector(this._selectorId);
	},
	showManagePopup : function() {
		$("#" + this._managePopupId).modal('show');
	}
}