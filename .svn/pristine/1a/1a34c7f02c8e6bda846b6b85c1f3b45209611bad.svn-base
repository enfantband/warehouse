function formValidate(form, errorClass, validClass) {
	var check = true;
	$(form).find('input,textarea,select').each(function(){
		var $this = this;
		if($($this).prop("required")){
			if($($this).val() == null || $($this).val() == '') {
				$($this).addClass(errorClass).removeClass(validClass);
				$($this).parent().addClass('state-error').removeClass('state-success');
				check = false;
			} else {
				$($this).removeClass(errorClass).addClass(validClass);
				$($this).parent().removeClass('state-error').addClass('state-success');				
			}
		}
	});
	return check;
}

function convertElementName(name) {
	name = name.replace(/\s+/g, '');
	return name.charAt(0).toLowerCase() + name.slice(1);
}
function addErrorAlertDiv(target, header, msg) {
	var alertHeader = $("<div></div>").addClass("alert alert-danger alert-block");
	var xbutton = $("<a></a>").addClass("close").attr("data-dismiss", "alert").attr("href", "#");
	var header = $("<h4></h4>").addClass("alert-heading").html(header);
	alertHeader.append(xbutton);
	alertHeader.append(header);
	alertHeader.append(msg);
	target.append(alertHeader);
}
function showMessage(header, msg) {	
	$("#modalMessage").remove();
	var closeButton = $("<button></button>").attr("type", "button").addClass("close").attr("data-dismiss", "modal").attr("aria-hidden", "true").html('&times;');
	var title = $("<h4></h4>").addClass("modal-title").html(header);
	
	var modalHeader = $("<div></div>").addClass("modal-header");
	modalHeader.append(closeButton);
	modalHeader.append(title);
	
	var modalBody = $("<div></div>").addClass("modal-body").html(msg);
	
	var modalFooter = $("<div></div>").addClass("modal-footer").append($("<button></button>").attr("type", "button").addClass("btn btn-default").attr("data-dismiss","modal").html("Ok"));
	
	var modalDialog = $("<div></div>").addClass("modal-dialog");
	
	var modalContent = $("<div></div>").addClass("modal-content");
	modalContent.append(modalHeader);
	modalContent.append(modalBody);
	modalContent.append(modalFooter);
	
	modalDialog.append(modalContent);
	
	var modalMessage = 
		$("<div></div>").attr("id", "modalMessage").addClass("modal fade").attr("tabindex", "-1").attr("role", "dialog").attr("aria-hidden", "true");
	modalMessage.append(modalDialog);
	$('#main').append(modalMessage);
	
	$("#modalMessage").modal('show');
}
function showMessageYesNo(id, header, msg, yes, no, yesfn, data) {
	$("#" + id).remove();
	var closeButton = $("<button></button>").attr("type", "button").addClass("close").attr("data-dismiss", "modal").attr("aria-hidden", "true").html('&times;');
	var title = $("<h4></h4>").addClass("modal-title").html(header);
	
	var modalHeader = $("<div></div>").addClass("modal-header");
	modalHeader.append(closeButton);
	modalHeader.append(title);
	
	var modalBody = $("<div></div>").addClass("modal-body").html(msg);
	
	var yesBt = $("<button></button>").attr("type", "button").addClass("btn btn-danger").html(yes);
	yesBt.bind("click", {data: data}, function(e) {
		yesfn(e.data.data);
	});
	var noBt = $("<button></button>").attr("type", "button").addClass("btn btn-default").attr("data-dismiss","modal").html(no);
	var modalFooter = $("<div></div>").addClass("modal-footer").append(noBt);
	modalFooter.append(yesBt);
	
	var modalDialog = $("<div></div>").addClass("modal-dialog");
	
	var modalContent = $("<div></div>").addClass("modal-content");
	modalContent.append(modalHeader);
	modalContent.append(modalBody);
	modalContent.append(modalFooter);
	
	modalDialog.append(modalContent);
	
	var modalMessage = 
		$("<div></div>").attr("id", id).addClass("modal fade").attr("tabindex", "-1").attr("role", "dialog").attr("aria-hidden", "true");
	modalMessage.append(modalDialog);
	$('#main').append(modalMessage);
	
	$("#" + id).modal('show');
}


function reloadTreeLib() {
	if($(".tree > ul")&&!mytreebranch){var mytreebranch=$(".tree").find("li:has(ul)").addClass("parent_li").attr("role","treeitem").find(" > span").attr("title","Collapse this branch");$(".tree > ul").attr("role","tree").find("ul").attr("role","group"),mytreebranch.on("click",function(a){var b=$(this).parent("li.parent_li").find(" > ul > li");b.is(":visible")?(b.hide("fast"),$(this).attr("title","Expand this branch").find(" > i").addClass("icon-plus-sign").removeClass("icon-minus-sign")):(b.show("fast"),$(this).attr("title","Collapse this branch").find(" > i").addClass("icon-minus-sign").removeClass("icon-plus-sign")),a.stopPropagation()})}
}

function millisecondsToTime(s){
	var original = s;
	var ms = s % 1000;
	s = (s - ms) / 1000;
	var secs = s % 60;
	s = (s - secs) / 60;
	var mins = s % 60;
	var hrs = (s - mins) / 60;
	var returnString = '';
	if(hrs > 0){
		if(hrs == 1){
			returnString = hrs + ' Hour ';
		} else {
			returnString = hrs + ' Hours ';
		}
	}
	if(mins > 0){
		if(mins == 1){
			returnString += mins + ' Minute ';	
		} else {
			returnString += mins + ' Minutes '
		}
	}
	if(secs > 0){
		if(secs == 1){
			returnString += secs + ' Second ';	
		} else {
			returnString += secs + ' Seconds ';
		}
	}
	return returnString;
}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function getToken() {
	if(getCookie("token") == null || getCookie("token") == null) {
		if(getCookie("refreshToken") == null || getCookie("refreshToken") == "") {
			location.href = "/loginpage";
		} else {
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				url:"/api/auth/token",
				dataType: 'json',
				data: JSON.stringify(data),
				type: 'POST',
				async: false,
				error:function(e) {
					location.href = "/loginpage";
				},
				success: function(res) {
					setCookie("token", res.token, 1);
				}
			});
		}
	}
	return getCookie("token");
}
