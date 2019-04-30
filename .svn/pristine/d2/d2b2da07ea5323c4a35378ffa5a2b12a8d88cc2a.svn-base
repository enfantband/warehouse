package com.samsbeauty.old.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.samsbeauty.old.model.OrderInfo;
import com.samsbeauty.old.model.OutboundItem;
import com.samsbeauty.old.model.ReturnMessage;
import com.samsbeauty.old.model.ReturnMessageWithListAndModel;
import com.samsbeauty.warehouse.old.helper.OutboundRestHelper;

import io.jsonwebtoken.ExpiredJwtException;

@Controller
public class OutboundController {
	@Autowired
	private OutboundRestHelper outboundRestHelper;
	
	@RequestMapping("/api/outbound/orderInfoAndItemList")
	public @ResponseBody ReturnMessageWithListAndModel<OutboundItem, OrderInfo> getOrderInfoAndItemByOrderNo (@RequestParam("orderNo") String orderNo, @RequestParam("gid") String gid) {
		ReturnMessageWithListAndModel<OutboundItem, OrderInfo> returnMessage = null;
		returnMessage = outboundRestHelper.getOrderInfoAndItemByOrderNo(orderNo, gid); 
				
		return returnMessage;
	}
	
	@RequestMapping(value="/api/outbound/outboundPendingOrder", method=RequestMethod.POST)
	public @ResponseBody ReturnMessage ouboundPendingOrder(@RequestParam("orderNo") String orderNo, @RequestParam("list") String listInJSON, @RequestParam("gid") String gid, @RequestParam("host") String host){
		return outboundRestHelper.sendPendingOrderItemList(orderNo, listInJSON, gid, host);
	}
	
	@RequestMapping(value="/api/outbound/outboundOrder", method=RequestMethod.POST)
	public @ResponseBody ReturnMessage outboundOrder(
			@RequestParam("locationBarcode") String locationBarcode, 
			@RequestParam("boxBarcode") String boxBarcode, 
			@RequestParam("orderNo") String orderNo, 
			@RequestParam("list") String listInJSON, 
			@RequestParam("gid") String gid, 
			@RequestParam("host") String host) {
		return outboundRestHelper.outboundOrder(locationBarcode, boxBarcode, orderNo, listInJSON, gid, host);
	}
	
	@RequestMapping(value="/api/outbound/clearTransaction", method=RequestMethod.POST) 
	public @ResponseBody ReturnMessage clearTransaction(
			@RequestParam("orderNo") String orderNo,
			@RequestParam("gid") String gid
			) {
		return outboundRestHelper.clearTransaction(orderNo, gid); 
	}
}
