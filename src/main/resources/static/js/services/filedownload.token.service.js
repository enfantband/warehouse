function FiledownloadTokenService () {
	this._serviceUrl = '/api/filedownloadToken';
};
FiledownloadTokenService.prototype = {
	getDownloadToken : function() {
		var $this = this;
		var result = "";
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url:$this._serviceUrl,
			dataType: 'json',
			type: 'POST',
			error:function(e) {
				if(e.status == '401') {
					location.href = '/loginpage';
				} else {
					showMessage(e.statusText, e.responseJSON.error);
				}
			},
			async:false,
			success: function(data) {
				result = data.token;
			}
		});
		return result;
	}	
}