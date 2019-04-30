function createTableGlobal(headers) {
	var table = $("<table></table>").addClass("table table-striped table-bordered table-hover").attr("width", "100%");
	var thead = $("<thead></thead>");
	var tr = $("<tr></tr>");
	var numOfHeads = headers.length;
	for(var i=0; i<numOfHeads; i++) {
		var header = headers[i];
		var th = $("<th></th>");
		if(header.dataHide != null) {
			th.attr("data-hide", header.dataHide);
		}
		if(header.dataClass != null) {
			th.attr("data-class", header.dataClass);
		}
		if(header.icon != null) {
			var icon = $("<i></i>").addClass("fa fa-fw text-muted hidden-md hidden-sm hidden-xs");
			icon.addClass(header.icon);
			th.append(icon);
		}
		th.append(header.label);
		tr.append(th);
	}
	thead.append(tr);
	table.append(thead);
	return table;
}