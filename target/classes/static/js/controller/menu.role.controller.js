function MenuRoleController(obj) {
	if(obj == null) {
		throw 'Please specify parameters';
	}
	if(obj.menuService == null) {
		throw 'Please specify menu service';
	}
	this._menuService = obj.menuService;
	
	if(obj.roleService == null) {
		throw 'Please specify role service';
	}
	this._roleService = obj.roleService;
	if(obj.tableController == null) {
		throw 'Please specify the table controller';
	}
	this._tableController = obj.tableController;
	var $this = this;
	this._view = new MenuRoleView({
		savingAction: function(menuId, roles, completedAction) {
			$this._menuService.addRoles(menuId, roles, function(){
				$this._tableController.reload();
				if(completedAction != null) {
					completedAction();
				}
			})
		}
	});
};

MenuRoleController.prototype = {
	showSettingForm : function(menuId) {
		var roleList = this._roleService.getListAll();
		var menuInfo = this._menuService.getOne(menuId);
		this._view.drawSettingForm(roleList, menuInfo);
	}
}