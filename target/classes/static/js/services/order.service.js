function OrderService() {
	this._serviceUrl = '/api/order/';
};

OrderService.prototype = {
	getNumberOfNewOrders : function() {
		var result = {};
		var $this = this;
		$.ajax({
			contentType: "application/json; charset=utf-8",
			headers: {
				"X-Authorization": "Bearer " + getToken()
			},
			url:$this._serviceUrl + "/numberOfNewOrders",
			dataType: 'json',
			type: 'GET',
			error: function(e) {
				showMessage(e.statusText, e.responseJSON.error);
			},
			async: false,
			success: function(data) {
				result = data;
			}
		});
		return result;
	}
}