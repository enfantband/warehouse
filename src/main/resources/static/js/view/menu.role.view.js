function MenuRoleView(obj) {
	if(obj == null) {
		throw 'Please specify parameters';
	}
	if(obj.savingAction == null) {
		throw 'Please specify action for saving roles';
	}
	this._savingAction = obj.savingAction;
};

MenuRoleView.prototype = {	
	drawSettingForm : function(roleList, menu) {
		var $this = this;
		var roleLength = roleList.length;
		var userRoleLength = menu.roles.length;
		var checkMap = [];
		for(var i=0; i<userRoleLength; i++) {
			checkMap[menu.roles[i].roleId] = true;
		}
		$("#roleSettingWindow").remove();
		var dialogDiv = $("<div></div>").addClass('modal fade').attr('tabindex', '-1').attr('role', 'dialog').attr('id', 'roleSettingWindow');
		var documentDiv = $("<div></div>").addClass("modal-dialog").attr("role", "document");
		var contentDiv = $("<div></div>").addClass("modal-content");
		var headerDiv = $("<div></div>").addClass("modal-header")
		.append(
			$("<h4></h4>").addClass("modal-title").html("Set Role")
		.append(
			$("<button></button>").attr("type", "button").addClass("close").attr("data-dismiss", "modal").attr("aria-label", "Close").append("<span aria-hidden='true'>&times;</span>")
		));
		var modalBodyDiv = $("<div></div>").addClass("modal-body");
		var form = $("<form></form>").attr("id", "setting-form").addClass("smart-form");
		var fieldset = $("<fieldset></fieldset>");
		var section = $("<section></section>");
		var label = $("<label></label>").addClass("label").html("Having Roles");
		var row = $("<div></div>").addClass("row");
		var col = $("<div></div>").addClass("col col-4");
		for(var i=0; i<roleLength; i++){
			var role = roleList[i];
			var checkLabel = $("<label></label>").addClass("checkbox");

			
			if(checkMap[role.roleId]) {
				checkLabel.html("<input type='checkbox' name='roles-chk' value='" + role.roleId + "' checked='checked'/> <i></i>" + role.roleName);
			} else {
				checkLabel.html("<input type='checkbox' name='roles-chk' value='" + role.roleId + "'/> <i></i>" + role.roleName);
			}
			col.append(checkLabel);
		}
		var savingButton = $("<button></button>").attr("type", "button").addClass("btn btn-primary").attr("id", "saveRoleButton").html("Save");
		var modalFooterDiv = $("<div></div>").addClass("modal-footer").append($("<button></button>").attr('type', 'button').addClass('btn btn-default').attr('data-dismiss', 'modal').html('Cancel'))
		.append(savingButton);
		savingButton.click(function(event) {
			var roles = [];
			$.each($("input[name='roles-chk']:checked"), function(){
				roles.push($(this).val())
			});
			$this._savingAction(menu.menuId, roles, function(){
				dialogDiv.modal('hide');
			});
		});
		row.append(col);
		section.append(label);
		section.append(row);
		fieldset.append(section);
		form.append(fieldset);
		modalBodyDiv.append(form);
		contentDiv.append(headerDiv);
		contentDiv.append(modalBodyDiv);
		contentDiv.append(modalFooterDiv);
		documentDiv.append(contentDiv);
		dialogDiv.append(documentDiv);
		dialogDiv.modal('show');
	}
}