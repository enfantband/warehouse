
function createFormElements(elements) {
	var numOfFed = elements.length;
	var formElements = [];
	for(var i=0; i<numOfFed; i++) {
		var feds = elements[i];
		if(feds.fieldset == null) {
			throw "Incorrect elements";
		}
		var eles = feds.fieldset;
		var numOfEles = eles.length;
		var fieldset = $("<fieldset></fieldset>");
		for(var j=0; j<numOfEles; j++) {
			var ele = eles[j];
			var section = $("<section></section>");
			var input = {};
			var label = $("<label></label>");
			if (ele.type == 'text') {
				label.addClass("input");
				input = $("<input></input>").attr("type", "text");
				
			} else if(ele.type == 'password'){
				label.addClass("input");
				input = $("<input></input>").attr("type", "password");
			} else if(ele.type == 'textarea') {
				label.addClass("input");
				label = $("<label></label>").addClass("textarea");
				input = $("<textarea></textarea>");
			} else if(ele.type == 'number') {
				label.addClass("input");
				input = $("<input></input>").attr("type", "number");
			} else if(ele.type == 'select') {
				label.addClass("select");
				input = $("<select></select>");
				if(ele.data != null) {
					for(var t = 0; t<ele.data.length; t++) {
						var opt = ele.data[t];
						var option = $("<option></option>").val(opt.key).html(opt.value);
						input.append(option);
					}
				}
			} else if(ele.type == 'colorpicker') {
				label.addClass("input");
				// implement it later
				input = $("<input></input>").attr("type", "text");
			
			}

			if(ele.label != null) {
				section.append($("<label></label>").addClass("label").html(ele.label));
			}
			if(ele.icon != null) {
				label.append($("<i class='icon-append fa " + ele.icon + "'></i>"));
			}
			if(ele.required) {
				input.attr("required", true);
			}
			if(!ele.editable) {
				input.attr("disabled", true);
			}			
			if(ele.name != null) {
				input.attr("name", ele.name);
			}
			if(ele.id != null){
				input.attr("id", ele.id);
			}
			if(ele.placeholder != null) {
				input.attr("placeholder", ele.placeholder);
			}
			if(ele.maxlength != null) {
				input.attr("maxlength", ele.maxlength);
			}
			label.append(input);
			if(ele.type == 'colorpicker') {
				label.append($('<span><i></i></span>').addClass('input-group-addon'));
			}
			section.append(label);
			fieldset.append(section);	
		}
		formElements.push(fieldset);
	}
	return formElements;
}