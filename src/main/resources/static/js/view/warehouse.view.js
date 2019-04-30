function WarehouseView(obj) {
	if(obj.warehouseSelectorChangeEvent != null) {
		this._selectorChangeEvent = obj.warehouseSelectorChangeEvent;
	}
};

WarehouseView.prototype = {
	createSelector : function(id, data, completeEvt) {
		var $this = this;
		$this.emptySelector(id);
		var length = 0;
		if(data.content == null) {
			length = data.length;
		} else {
			length = data.content.length;	
		}
		var option = $("<option></option>").attr("disabled", "disabled").attr("selected", "selected").html("- Warehouse -");
		$("#"+id).append(option);
		for(var i=0; i<length; i++) {
			var item = {};
			if(data.content == null) {
				item = data[i];
			} else {
				item = data.content[i];	
			}
			var option = $("<option></option>").attr("value", item.warehouseId).html(item.warehouseTag.tag);
			$("#"+id).append(option);
		}
		
		$("#" + id).unbind('change');
		$("#" + id).change(function(){
			$this._selectorChangeEvent($(this).val());
		});
	},
	
	emptySelector : function(id) {
		$("#" + id).empty();
	}
}