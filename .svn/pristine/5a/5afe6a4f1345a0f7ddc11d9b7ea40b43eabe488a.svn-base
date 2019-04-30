function RoleTreeController(obj) {
	this._serverUrl = obj.serverUrl;
	this._roleService = obj.roleService;
	this._privilegeService = obj.privilegeService;
	this._addModal = obj.modals[0];
	this._deleteModal = obj.modals[1];
	this._addForm = obj.forms[0];
	this._deleteForm = obj.forms[1];
	this._privilegesListId = obj.privilegesListId;
	this._addButtonForm = obj.addButtonForm;
	this._selectedRole = {};
	this._reloadEvent = obj.reloadEvent;
	
	var $this = this;
	$("#" + this._addButtonForm).click(function(e){
		$this.addPrivilege($this._selectedRole, $("#" + $this._privilegesListId).val());
	});
	
	this._viewId = "";
};

RoleTreeController.prototype = {
	createTree: function( viewId ) {
		data = this._roleService.getListAll();
		this._viewId = viewId;
		this.createView(data)
	},
	reload: function() {
		this.createTree(this._viewId);
		if(this._reloadEvent != null) {
			this._reloadEvent();
		}
	},
	addPrivilege: function( role, privilegeId ) {
		var $this = this;
		this._privilegeService.addToRole(role.roleId, privilegeId, function(){
			$("#" + $this._addForm).trigger("reset");
			$("#" + $this._addModal).modal('hide');
			$this.reload();
		});
	},
	bindPrivilegeList: function() {
		// find maximum step
		var $this = this;
		$("#"+$this._privilegesListId).empty();
		var privileges = this._privilegeService.getListAll();
		var maxStep = 0;
		var list = [];
		for(var i in privileges) {
			var privilege = privileges[i];

			var obj = {};
			obj.id = privilege.privilegeId;
			if(privilege.privilege != null) {
				var sep = privilege.privilege.split(".");
				var cnt = sep.length;
				if(cnt > maxStep) {
					maxStep = cnt;
				}
				var key = privilege.privilege.replace("." + sep[cnt-1], "");
				if(list[key] == null) {
					list[key] = [];
				}

				obj.name = sep[cnt-1];

				list[key].push(obj);
			}
			
		}

		for(var key in list){

			var step = ''; 
			var sep = key.split(".");

			for(var i = sep.length + 1; i < maxStep; i++) {
				step += '&nbsp;&nbsp';
			}
			for(var i in sep){
				var opt =  $("<option></option>").attr("disabled", "disabled").html(step + sep[i]);	
				$("#"+$this._privilegesListId).append(opt);
				step += '&nbsp;&nbsp';
			}
			$("#"+$this._privilegesListId).append(opt);

			step += '&nbsp;&nbsp';
			for(var i = 0; i<list[key].length; i++) {
				var val = list[key][i].name;
				var opt = $("<option></option>").val(list[key][i].id).html(step + val);

				$("#"+$this._privilegesListId).append(opt);
			}
		}
	},
	createView(roles) {
		var $this = this;
		var viewId = this._viewId;
		$this.bindPrivilegeList();
		$("#" + viewId).empty();
		var treeLayer = $("<div></div>").addClass("tree smart-form");
		var roleUl = $("<ul></ul>");
		for(var i=0; i<roles.length; i++) {
			var roleLi = $("<li></li>");
			var roleSpan = $("<span></span>").append("<i class='fa fa-lg fa-group'></i> " + roles[i].roleName);
			var plusButton = $("<button></button>").attr("type", "submit").addClass("btn btn-warning btn-xs").append(" <i class='fa fa-plus'></i>").css("margin","3px");
			plusButton.bind("click", {role:roles[i]}, function(event){
				$this._selectedRole = event.data.role;
				$("#"+$this._addModal).modal('show');
			});
			var minusButton = $("<button></button>").attr("type", "submit").addClass("btn btn-danger btn-xs").append(" <i class='fa fa-minus'></i>");
			minusButton.bind("click", {role:roles[i]}, function(event){
				var elements = $("input[name^='privileges[" + event.data.role.roleId + "]']:checked");
				if(elements.length > 0) {
					var vals = [];
					elements.each(function(){
						vals.push($(this).val());
					});
					for(var k=0; k<vals.length; k++) {
						var val = vals[k];
						if(k==vals.length-1) {
							$this._privilegeService.removeFromRole(event.data.role.roleId, val, function(){
								$this.reload();
							});
						} else {
							$this._privilegeService.removeFromRole(event.data.role.roleId, val, function(){});	
						}
					}

				} else {
					showMessage('Warning', 'Please check the privilege to remove');
				}
			});
			
			var privileges = roles[i].privileges;
			
			roleLi.append(roleSpan);
			roleLi.append(plusButton);
			roleLi.append(minusButton);
			for(var j=0; j<privileges.length; j++) {
				var privilege = privileges[j];
				var privilegeUl = $("<ul><li><span> <label class='checkbox inline-block'><input type='checkbox' name='privileges[" + roles[i].roleId + "]["+privilege.privilegeId+"]' value='"+privilege.privilegeId+"'><i></i>" + privilege.privilege + "</label> </span></li></ul>");

				roleLi.append(privilegeUl);
			}

			roleUl.append(roleLi);
		}
		treeLayer.append(roleUl);
		$("#" + viewId).append(treeLayer);
	}

}