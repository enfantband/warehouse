function LoginService() {
	this._serviceUrl = '/api/auth/login';
};

LoginService.prototype = {
	login : function (username, password, completeEvt, loginFailed) {
		var $this = this;
		if( username != null && password != null) {
			var data = {
				username: username,
				password: password
			};
			$.ajax({
		        contentType: "application/json; charset=utf-8",
				url:$this._serviceUrl,
				dataType: 'json',
				data: JSON.stringify(data),
				type: 'POST',
				error:function(e) {
					if(e.status == '401') {
						loginFailed(e);
					} else {
						showMessage(e.statusText, e.responseJSON.error);	
					}
				},
				success: function(data) {
					completeEvt(data);
				}
			});
		}
	},
	loginCheck: function() {
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url:"/loginCheck",
			dataType: 'json',
			type: 'GET',
			error:function(e) {
				if(e.status == '401') {
					location.href = '/loginpage';
				} else {
					if(e.responseJSON != null) {
						showMessage(e.statusText, e.responseJSON.error);	
					}
				}
			},
			async:false,
			success: function() {
			}
		});
	}
};