function CompanyService() {
	this._serviceUrl = '/api/company';	
	
}

CompanyService.prototype = {
	getListAll: function() {
		var returnData = {};
		$.ajax({
			dataType: 'json',
			headers: {
				"X-Authorization": "Bearer " + getToken(),
			},
			url: this._serviceUrl,
			type: 'GET',
			async: false,
			error:function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			success: function(data) {
				
				returnData = data;
			}
		});
		return returnData;
		
	}
}