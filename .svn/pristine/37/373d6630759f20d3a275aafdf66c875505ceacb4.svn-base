package com.samsbeauty.old.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.samsbeauty.old.model.PendingOrderBean;
import com.samsbeauty.old.model.ReturnMessage;
import com.samsbeauty.old.model.ReturnMessageWithList;
import com.samsbeauty.warehouse.old.helper.OutboundRestHelper;

public class PendingOrderController {
	@Autowired
	private OutboundRestHelper outboundRestHelper;
	
	@RequestMapping("/api/pending/pendingOrderList")
	public @ResponseBody ReturnMessageWithList<PendingOrderBean> getPendingOrders (
			@RequestParam(value = "orderNo", required = false) String orderNo, 
			@RequestParam("gid") String gid, 
			@RequestParam("lpp") Integer lpp) {
		return outboundRestHelper.getPendingOrder(gid, orderNo, lpp);
	}
	
	@RequestMapping(value="/api/pending/ready", method=RequestMethod.POST)
	public @ResponseBody ReturnMessage readyPendingOrder (
				@RequestParam("orderNo") String orderNo,
				@RequestParam("gid") String gid
			) {
		outboundRestHelper.ready(orderNo, gid);
		return new ReturnMessage("Complete", true);
	}
}
