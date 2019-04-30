function EmployeeRoleController(obj) {
	if(obj == null) {
		throw 'Please specify parameters';
	}
	if(obj.employeeService == null) {
		throw 'Please specify employee service';
	}
	this._employeeService = obj.employeeService;
	if(obj.roleService == null) {
		throw 'Please specify role service';
	}
	if(obj.tableController == null) {
		throw 'Please specify the table';
	}
	this._tableController = obj.tableController;
	this._roleService = obj.roleService;
	var $this = this;
	this._view = new EmployeeRoleView({
		savingAction: function(employeeId, roles, completedAction) {
			$this._employeeService.addRoles(employeeId, roles, function(){
				$this._tableController.reload();
				if(completedAction != null) {
					completedAction();
				}
			})
		}
	});
};

EmployeeRoleController.prototype = {
	showSettingForm : function(employeeId) {
		var roleList = this._roleService.getListAll();
		var employeeInfo = this._employeeService.getOne(employeeId);
		this._view.drawSettingForm(roleList, employeeInfo);
	}
}