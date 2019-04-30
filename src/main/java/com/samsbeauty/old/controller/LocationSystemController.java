package com.samsbeauty.old.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.samsbeauty.old.model.InventoryBean;
import com.samsbeauty.old.model.JSONReturnModel;
import com.samsbeauty.old.model.LocationItemInput;
import com.samsbeauty.old.model.LocationItemTransferInput;
import com.samsbeauty.old.model.ReturnMessage;
import com.samsbeauty.old.model.ReturnMessageWithList;
import com.samsbeauty.old.model.WarehouseItemBean;
import com.samsbeauty.warehouse.old.helper.WarehouseRestHelper;

@Controller
@RequestMapping("/api/location")
public class LocationSystemController {
	@Autowired
	private WarehouseRestHelper warehouseRestHelper;
	
	@ResponseBody ReturnMessageWithList<WarehouseItemBean> getItemsInLocationMapping(
			@RequestParam("locationCode") String locationBarcode, 
			@RequestParam("boxBarcode") String boxBarcode,
			@RequestParam("gid") String gid) {
		ReturnMessageWithList<WarehouseItemBean> returnMessage = null;
		
		if(locationBarcode == null) locationBarcode = "";
		if(boxBarcode == null) boxBarcode = "";
		
		try {
			returnMessage = warehouseRestHelper.getWarehouseItemList(locationBarcode, boxBarcode, gid);
			returnMessage.setMessage("Completed");
		} catch(Exception e) {
			returnMessage = new ReturnMessageWithList<>();
			returnMessage.setList(null);
			returnMessage.setMessage("Cannot get a list. Please try it again later");
		}
		
		return returnMessage;
	}
	
	@RequestMapping(value="/insertItems", method=RequestMethod.POST) 
	public @ResponseBody ReturnMessage insertItemLocationMappingList(
				@RequestBody LocationItemInput<InventoryBean> input
			) {
		ReturnMessage message = new ReturnMessage();
		try {
			JSONReturnModel<String> response = warehouseRestHelper.addLocation(input);
			message.setMessage(response.getReturnMessage());
			message.setSuccess(true);
		} catch (Exception e) {
			message.setSuccess(false);
			message.setMessage("Error. Please try it again");
		}
		return message;
	}
	
	@RequestMapping(value="/emptyItems", method=RequestMethod.POST)
	public @ResponseBody ReturnMessage updateToMakeItemsEmpty(@RequestBody LocationItemInput<WarehouseItemBean> input) {
		ReturnMessage message = new ReturnMessage();
		try {
			JSONReturnModel<String> response = warehouseRestHelper.updateToMakeItemsEmpty(input);
			message.setMessage(response.getReturnMessage());
			message.setSuccess(true);
		} catch (Exception e) {
			message.setSuccess(false);
			message.setMessage("Error. Please try it again.");
		}
		return message;
	}
	
	@RequestMapping(value="/deleteItem", method=RequestMethod.POST)
	public @ResponseBody ReturnMessage deleteItemLocation(@RequestParam("itemId") String itemId, @RequestParam("gid") String gid) {
		ReturnMessage message = new ReturnMessage();
		try {
			JSONReturnModel<String> response = warehouseRestHelper.deleteItemInLocation(itemId, gid);
			message.setMessage(response.getReturnMessage());
			message.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			message.setSuccess(false);
			message.setMessage("Error. Please try it again later.");
		}
		return message;
	}
	
	@RequestMapping(value="/deleteItems", method=RequestMethod.POST)
	public @ResponseBody ReturnMessage deleteItemLocations(@RequestBody LocationItemInput<WarehouseItemBean> input) {
		ReturnMessage message = new ReturnMessage();
		try {
			JSONReturnModel<String> response = warehouseRestHelper.deleteItemsInLocation(input);
			message.setMessage(response.getReturnMessage());
			message.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			message.setSuccess(false);
			message.setMessage("Error. Please try it again.");
		}
		return message;
	}
	
	@RequestMapping(value="/transferItems", method=RequestMethod.POST)
	public @ResponseBody ReturnMessage transferItems(@RequestBody LocationItemTransferInput input) {
		ReturnMessage message = new ReturnMessage();
		try {
			JSONReturnModel<String> response = warehouseRestHelper.transferItemsToLocation(input);
			message.setMessage(response.getReturnMessage());
			message.setSuccess(true);
		} catch (Exception e) {
			message.setSuccess(false);
			message.setMessage("Error. Please try it again.");
		}
		return message;
	}
}
