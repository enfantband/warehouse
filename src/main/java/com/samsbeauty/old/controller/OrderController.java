package com.samsbeauty.old.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.samsbeauty.old.model.OrderMemoListBean;
import com.samsbeauty.old.model.ReturnMessageWithList;
import com.samsbeauty.warehouse.old.helper.OrderInfoHelper;

@Controller
public class OrderController {
	@Autowired
	private OrderInfoHelper orderInfoHelper;
	
	@RequestMapping("/api/order/memoList")
	public @ResponseBody ReturnMessageWithList<OrderMemoListBean> getMemoList( 
				@RequestParam("orderNo") String orderNo,
				@RequestParam("gid") String gid,
				@RequestParam("orderBy") String orderBy,
				@RequestParam("orderDesc") String orderDesc
			){
		return orderInfoHelper.getOrderMemoList(orderNo, gid, orderBy, orderDesc);
	}
	
	@RequestMapping(value = "/api/order/numberOfNewOrders", method=RequestMethod.GET)
	public @ResponseBody Integer getNumberOfNewOrders() {

    	Integer numberOfNewOrders = 0;
    	try {
    		numberOfNewOrders = orderInfoHelper.getNumberOfNewOrders();	
    	} catch (Exception e) {
    		return 0;
    	}
    	return numberOfNewOrders;
	}
}
