function LocationController(obj) {
	if(obj.selectorId) {
		this._selectorId = obj.selectorId;
	}
	
	this._service = obj.service;
	
	this._view = new WarehouseView({
		warehouseSelectorChangeEvent: function() {
			
		}
	});
	
};

LocationController.prototype = {
	

	createSelector : function() {
		var $this = this;
		var data = $this._service.getList();
		
		this._view.createSelector(this._selectorId, data, function(){
			
		});
		
	}
}