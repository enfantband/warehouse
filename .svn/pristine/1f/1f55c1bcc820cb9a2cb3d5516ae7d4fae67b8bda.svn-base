
function FormGenerator(obj) {
	if(obj.name == null) {
		throw "Please specify the name";
	}
	this._name = obj.name;
	if(obj.addElements == null) {
		throw "Please specify the form elements to add";
	}
	if(obj.editElements == null) {
		throw "Please specify the form elements to edit";
	}

	this._addForm = $("<form></form>").addClass("smart-form").attr("id", convertElementName(this._name) + "AddForm");
	this._editForm = $("<form></form>").addClass("smart-form").attr("id", convertElementName(this._name) + "EditForm");
	
	var fields = createFormElements(obj.addElements);

	for(var i=0; i<fields.length; i++) {
		this._addForm.append(fields[i]);
	}
	var fields = createFormElements(obj.editElements);
	
	for(var i=0; i<fields.length; i++) {
		this._editForm.append(fields[i]);
	}
	
	this._addModal = {};
	this._editModal = {};
	this._deleteModal = {};
};

FormGenerator.prototype = {
	generate: function() {
		this._addModal = this.createAddModalForm();
		this._editModal = this.createEditModalForm();
		this._deleteModal = this.createDeleteModalForm('Are you sure you want to delete?');		
	},
	getAddModal: function() {
		return this._addModal;
	},
	getEditModal: function() {
		return this._editModal;
	},
	getDeleteModal: function() {
		return this._deleteModal;
	},
	getAddForm: function() {
		return this._addForm;
	},
	getAddButton: function() {
		return this._addButton;
	},
	getEditButton: function() {
		return this._editButton;
	},
	getDeleteButton: function() {
		return this._deleteButton;
	},
	getAddFormId: function() {
		return convertElementName(this._name) + "AddForm";
	},
	getEditFormId: function() {
		return convertElementName(this._name) + "EditForm";
	},	
	createAddModalForm: function () {
		var closeButton = $("<button></button>").attr("type", "button").addClass("close").attr("data-dismiss", "modal").attr("aria-hidden", "true").html('&times;');
		var title = $("<h4></h4>").addClass("modal-title").html("Add " + this._name.charAt(0).toUpperCase() + this._name.slice(1));
		
		var modalHeader = $("<div></div>").addClass("modal-header");
		modalHeader.append(closeButton);
		modalHeader.append(title);
		
		
		
		var modalBody = $("<div></div>").addClass("modal-body").html(this._addForm);
		
		var cancelButton = $("<button></button>").addClass("btn btn-default").attr("data-dismiss", "modal").html("Cancel");
		this._addButton = $("<button></button>").addClass("btn btn-danger").attr("id", convertElementName(this._name)  + "AddButton").html("Add");
		
		var modalFooter = $("<div></div>").addClass("modal-footer").append(cancelButton);
		modalFooter.append(this._addButton);
		
		var modalDialog = $("<div></div>").addClass("modal-dialog");
		
		var modalContent = $("<div></div>").addClass("modal-content");
		modalContent.append(modalHeader);
		modalContent.append(modalBody);
		modalContent.append(modalFooter);
		
		modalDialog.append(modalContent);
		
		var modalMessage = 
			$("<div></div>").attr("id", convertElementName(this._name) + "AddModal").addClass("modal fade").attr("tabindex", "-1").attr("role", "dialog").attr("aria-hidden", "true");
		modalMessage.append(modalDialog);
		$('#main').append(modalMessage);
		
		return $("#" + convertElementName(this._name) + "AddModal");
	},

	createEditModalForm : function () {
		var closeButton = $("<button></button>").attr("type", "button").addClass("close").attr("data-dismiss", "modal").attr("aria-hidden", "true").html('&times;');
		var title = $("<h4></h4>").addClass("modal-title").html("Edit " + this._name.charAt(0).toUpperCase() + this._name.slice(1));
		
		var modalHeader = $("<div></div>").addClass("modal-header");
		modalHeader.append(closeButton);
		modalHeader.append(title);
		
		var modalBody = $("<div></div>").addClass("modal-body").html(this._editForm);
		
		var cancelButton = $("<button></button>").addClass("btn btn-default").attr("data-dismiss", "modal").html("Cancel");
		this._editButton = $("<button></button>").addClass("btn btn-danger").attr("id", convertElementName(this._name)  + "EditButton").html("Edit");
		
		var modalFooter = $("<div></div>").addClass("modal-footer").append(cancelButton);
		modalFooter.append(this._editButton);
		
		var modalDialog = $("<div></div>").addClass("modal-dialog");
		
		var modalContent = $("<div></div>").addClass("modal-content");
		modalContent.append(modalHeader);
		modalContent.append(modalBody);
		modalContent.append(modalFooter);
		
		modalDialog.append(modalContent);
		
		var modalMessage = 
			$("<div></div>").attr("id", convertElementName(this._name) + "EditModal").addClass("modal fade").attr("tabindex", "-1").attr("role", "dialog").attr("aria-hidden", "true");
		modalMessage.append(modalDialog);
		$('#main').append(modalMessage);
		
		return $("#" + convertElementName(this._name) + "EditModal");
	},

	createDeleteModalForm : function (message) {
		var closeButton = $("<button></button>").attr("type", "button").addClass("close").attr("data-dismiss", "modal").attr("aria-hidden", "true").html('&times;');
		var title = $("<h4></h4>").addClass("modal-title").html("Delete " + this._name.charAt(0).toUpperCase() + this._name.slice(1));
		
		var modalHeader = $("<div></div>").addClass("modal-header");
		modalHeader.append(closeButton);
		modalHeader.append(title);
		
		var modalBody = $("<div></div>").addClass("modal-body").html(message);
		
		var cancelButton = $("<button></button>").addClass("btn btn-default").attr("data-dismiss", "modal").html("Cancel");
		this._deleteButton = $("<button></button>").addClass("btn btn-danger").attr("id", convertElementName(this._name)  + "DeleteButton").html("Delete");
		
		var modalFooter = $("<div></div>").addClass("modal-footer").append(cancelButton);
		modalFooter.append(this._deleteButton);
		
		var modalDialog = $("<div></div>").addClass("modal-dialog");
		
		var modalContent = $("<div></div>").addClass("modal-content");
		modalContent.append(modalHeader);
		modalContent.append(modalBody);
		modalContent.append(modalFooter);
		
		modalDialog.append(modalContent);
		
		var modalMessage = 
			$("<div></div>").attr("id", convertElementName(this._name) + "DeleteModal").addClass("modal fade").attr("tabindex", "-1").attr("role", "dialog").attr("aria-hidden", "true");
		modalMessage.append(modalDialog);
		$('#main').append(modalMessage);
		
		return $("#" + convertElementName(this._name) + "DeleteModal");
	}	
}