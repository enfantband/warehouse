function WarehouseGroupController(obj) {
	if(obj.selectorId) {
		this._selectorId = obj.selectorId;
	}
	
	if(obj.managePopupId) {
		this._managePopupId = obj.managePopupId;
	}
	this._service = obj.service;
	this._changeEvent = obj.changeEvent;
	
	var $this = this;
	this._view = new WarehouseGroupView({
		warehouseGroupSelectorChangeEvent: function(data) {
			$this._changeEvent(data);
			
		}
	});
};

WarehouseGroupController.prototype = {


	createSelector : function(aisleId) {
		var $this = this;
		var data = $this._service.getListAllByAisleId(aisleId);
		
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