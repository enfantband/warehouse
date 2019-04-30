package com.samsbeauty.old.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.samsbeauty.old.model.Code;
import com.samsbeauty.old.model.Cost;
import com.samsbeauty.old.model.Inventory;
import com.samsbeauty.old.model.InventoryHistory;
import com.samsbeauty.old.model.InventoryHistoryList;
import com.samsbeauty.old.model.InventoryList;
import com.samsbeauty.old.model.OrderToVendor;
import com.samsbeauty.old.model.ReturnMessage;
import com.samsbeauty.old.model.ReturnMessageWithList;
import com.samsbeauty.old.model.ReturnMessageWithObject;
import com.samsbeauty.old.model.Vendor;
import com.samsbeauty.warehouse.old.helper.InventoryRestHelper;
import com.samsbeauty.warehouse.old.helper.ProductRestHelper;
import com.samsbeauty.warehouse.old.helper.VendorHelper;
import com.samsbeauty.warehouse.util.BarcodeUtil;
import com.samsbeauty.warehouse.util.PathUtil;

@Controller
public class InventoryOldController {
	@Autowired
	private InventoryRestHelper inventoryRestHelper;
	@Autowired
	private ProductRestHelper productRestHelper;
	
	@Autowired
	private VendorHelper vendorHelper;
	
	@RequestMapping(value="/api/inventory/getInventoryList")
	public @ResponseBody ReturnMessageWithList<Inventory> getInventoryList( 
			@RequestParam("search_key") String search_key,
			@RequestParam("page_no") String page_no,
			@RequestParam("lpp") Integer lpp,
			@RequestParam("search_type") String search_type,
			@RequestParam(value="location_id", required=false) String location_id,
			HttpServletRequest request){
		List<Inventory> items = null;
		InventoryList list = null;
		if(!search_key.equals("") || !location_id.equals("") ){
			Integer pageNo = Integer.valueOf(page_no);
			list = inventoryRestHelper.getItemInfoFromServerBySearchKey(BarcodeUtil.getBarcode(search_key), String.valueOf(pageNo-1), lpp, search_type, location_id);
			items = list.getList();
			if(items.size() != 1){
				ReturnMessageWithList<Inventory> returnMessage = new ReturnMessageWithList<Inventory>();
				returnMessage.setMessage("Failed to get an item information");
				returnMessage.setList(items);
				returnMessage.setSuccess(false);
			}
			for(int i=0; i<items.size(); i++){
				Inventory item = items.get(i);
				item.setProductImage(PathUtil.getProductImagePath(item.getProductGroup(), item.getProductImage()));
			}	
		}
		
		ReturnMessageWithList<Inventory> returnMessage = new ReturnMessageWithList<Inventory>();
		returnMessage.setMessage("Completed");
		returnMessage.setList(items);
		returnMessage.setSuccess(true);
		if(list == null || list.getTotalRecord() == null || list.getTotalRecord().equals("")){
			returnMessage.setTotalRecords(0);	
		} else {
			returnMessage.setTotalRecords(Integer.valueOf(list.getTotalRecord()));
		}
		return returnMessage;
	}
	
	@RequestMapping(value="/api/inventory/getProductInfo", method=RequestMethod.POST)
	public @ResponseBody ReturnMessageWithObject<Inventory> getProductInfo(
			@RequestParam("barcode") String barcode,
			HttpServletRequest request
			) {
		Inventory item = null;
		ReturnMessageWithObject<Inventory> returnMessage = new ReturnMessageWithObject<>();
		List<Inventory> items = inventoryRestHelper.getInventoryList(Arrays.asList(barcode));
		if(items.size() > 0) {
			item = items.get(0);
			item.setProductImage(PathUtil.getProductImagePath(item.getProductGroup(), item.getProductImage()));
		}
		if(item == null) {
			returnMessage.setMessage("Failed to get data");
			returnMessage.setSuccess(false);
		} else {
			returnMessage.setObject(item);
			returnMessage.setMessage("Completed");
			returnMessage.setSuccess(true);
		}
		
		return returnMessage;
	}
	
	@RequestMapping(value="/api/inventory/getInventoryHistoryReason")
	public @ResponseBody ReturnMessageWithList<Code> getInventoryHistoryReason() {
		List<Code> list = inventoryRestHelper.getInventoryReasonList();
		ReturnMessageWithList<Code> returnMessage = new ReturnMessageWithList<Code>();
		returnMessage.setMessage("Completed");
		returnMessage.setList(list);
		returnMessage.setSuccess(true);
		return returnMessage;
	}
	
	@RequestMapping(value="/api/inventory/getInventoryStatus")
	public @ResponseBody ReturnMessageWithList<Code> getInventoryStatus() {
		List<Code> list = inventoryRestHelper.getInventoryStatusList();
		ReturnMessageWithList<Code> returnMessage = new ReturnMessageWithList<Code>();
		returnMessage.setMessage("Completed");
		returnMessage.setList(list);
		returnMessage.setSuccess(true);
		return returnMessage;
	}
	
	@RequestMapping(value="/api/inventory/insertInOut", method=RequestMethod.POST)
	public @ResponseBody ReturnMessage insertInOut(
			@RequestParam("order_no") String orderNo,
			@RequestParam("quantity") String quantity,
			@RequestParam("status") String status,
			@RequestParam("barcode") String barcode,
			@RequestParam("reason") String reason,
			@RequestParam("gid") String gid
			) {

		System.out.println("gid is " + gid);
		System.out.println("orderNo is " + orderNo);
		System.out.println("status = " + status);
		inventoryRestHelper.insertInOutStock(orderNo, quantity, status, barcode, reason, gid);
		ReturnMessage returnMessage = new ReturnMessage();
		returnMessage.setSuccess(true);
		returnMessage.setMessage("Completed");
		return returnMessage;
	}
	
	@RequestMapping(value="/api/inventory/updateInventory", method=RequestMethod.POST) 
	public @ResponseBody ReturnMessage updateInventory(
			@RequestParam("product_barcode") String productBarcode,
			@RequestParam("product_id") String productId,
			@RequestParam("status_id") String statusId,
			@RequestParam("gid") String gid
			) {
		inventoryRestHelper.updateInventory(productBarcode, productId, statusId, gid);
		ReturnMessage returnMessage = new ReturnMessage();
		returnMessage.setSuccess(true);
		returnMessage.setMessage("Completed");
		return returnMessage;
	}
	
	@RequestMapping(value="/api/inventory/getCostList")
	public @ResponseBody ReturnMessageWithList<Cost> getCostList(
				@RequestParam("barcode") String barcode
			) {
		List<Cost> list = inventoryRestHelper.getCostList(barcode);
		ReturnMessageWithList<Cost> returnMessage = new ReturnMessageWithList<>();
		returnMessage.setList(list);
		returnMessage.setMessage("Completed");
		returnMessage.setSuccess(true);
		return returnMessage;
	}
	
	@RequestMapping(value="/api/inventory/getCost")
	public @ResponseBody ReturnMessageWithObject<Cost> getCost(@RequestParam("barcode") String barcode) {
		ReturnMessageWithObject<Cost> returnMessage = new ReturnMessageWithObject<>();
		try {
			Cost cost = inventoryRestHelper.getCost(barcode);	
			returnMessage.setObject(cost);
			returnMessage.setMessage("Completed");
			returnMessage.setSuccess(true);
		} catch (Exception e) {
			returnMessage.setObject(null);
			returnMessage.setMessage("Failed");
			returnMessage.setSuccess(false);
		}
		
		return returnMessage;
	}
	
	@RequestMapping(value="/api/inventory/insertCost", method=RequestMethod.POST)
	public @ResponseBody ReturnMessage insertCost(
			@RequestParam("barcode") String barcode,
			@RequestParam("price") String price,
			@RequestParam("sale_price") String salePrice,
			@RequestParam("gid") String gid
			) {
		inventoryRestHelper.insertCost(barcode, price, salePrice, gid);
		ReturnMessage returnMessage = new ReturnMessage();
		returnMessage.setMessage("Completed");
		returnMessage.setSuccess(true);
		return returnMessage;
	}
	
	@RequestMapping(value="/api/inventory/updateCost", method=RequestMethod.POST)
	public @ResponseBody ReturnMessage updateCost(
			@RequestParam("cost_id") String costId,
			@RequestParam("price") String price,
			@RequestParam("sale_price") String salePrice,
			@RequestParam("gid") String gid
			) {
		inventoryRestHelper.updateCost(costId, price, salePrice, gid);
		ReturnMessage returnMessage = new ReturnMessage();
		returnMessage.setMessage("Completed");
		returnMessage.setSuccess(true);
		return returnMessage;
	}
	
	@RequestMapping(value="/api/inventory/deleteCost", method=RequestMethod.POST) 
	public @ResponseBody ReturnMessage deleteCost(
			@RequestParam("cost_id") String costId,
			@RequestParam("gid") String gid
			) {
		inventoryRestHelper.deleteCost(costId, gid);
		ReturnMessage returnMessage = new ReturnMessage();
		returnMessage.setSuccess(true);
		returnMessage.setMessage("Completed");
		return returnMessage;
	}
	
	@RequestMapping(value="/api/inventory/getInventoryLocation")
	public @ResponseBody ReturnMessageWithList<Code> getInventoryLocationList() {
		List<Code> list = inventoryRestHelper.getInventoryLocationList();
        ReturnMessageWithList<Code> returnMessage = new ReturnMessageWithList<Code>();
        returnMessage.setMessage("Complted");
        returnMessage.setList(list);
        returnMessage.setSuccess(true);
        return returnMessage;
	}
	@RequestMapping(value="/api/inventory/getInventoryHistoryList")
	public @ResponseBody ReturnMessageWithList<InventoryHistory> getInventoryHistoryList(
            @RequestParam("category") String category,
            @RequestParam("barcode") String barcode,
            @RequestParam("page_no") String page_no,
            @RequestParam("lpp") Integer lpp
            ){

        Integer pageNo = Integer.valueOf(page_no);  
        InventoryHistoryList list = inventoryRestHelper.getInventoryHistoryList(category, barcode, String.valueOf(pageNo-1), lpp, "", "");
        ReturnMessageWithList<InventoryHistory> returnMessage = new ReturnMessageWithList<InventoryHistory>();
        returnMessage.setMessage("Completed");
        returnMessage.setTotalRecords(Integer.valueOf(list.getTotalRecord()));
        returnMessage.setList(list.getList());
        returnMessage.setSuccess(true);
        return returnMessage;
    }
	
	@RequestMapping(value="/api/inventory/getVendorList")
	public @ResponseBody ReturnMessageWithList<Vendor> getVendorList(){
		List<Vendor> list = vendorHelper.getVendorList();
		
		ReturnMessageWithList<Vendor> returnMessage = new ReturnMessageWithList<Vendor>();
		returnMessage.setMessage("Completed");
		returnMessage.setList(list);
		returnMessage.setSuccess(true);
		return returnMessage;
	}
	
	@RequestMapping(value="/api/inventory/orderToVendor", method=RequestMethod.POST)
	public @ResponseBody ReturnMessage orderToVendor(
			@RequestBody List<OrderToVendor> orderToVendorMappings,
			HttpServletRequest request
			) {
		StringBuilder idBuilder = new StringBuilder();
		StringBuilder quantityBuilder = new StringBuilder();
		
		String svCode = "";
		for(OrderToVendor vendor : orderToVendorMappings) {
			idBuilder.append(":#:" + vendor.getProductId());
			quantityBuilder.append(":#:" + vendor.getOrderQuantity());
			if(svCode.equals("")) {
				svCode = vendor.getSvCode();
			}
		}
		String productIds = idBuilder.toString().replaceFirst(":#:", "");
		String quantities = quantityBuilder.toString().replaceFirst(":#:", "");
		String gid = (String) request.getSession().getAttribute("MEMBER_GID");
		
		inventoryRestHelper.insertOrderToVendor(productIds, quantities, svCode, gid);
		return new ReturnMessage("Complete", true);
	}
	
	@RequestMapping(value="/api/inventory/updateProductUnitStatus", method=RequestMethod.POST)
	public @ResponseBody ReturnMessage updateProductUnitStatus (
			@RequestParam("product_group") String productGroup,
			@RequestParam("product_code") String productCode,
			@RequestParam("unit_title") String unitTitle,
			@RequestParam("use_flag") String useFlag ,
			@RequestParam("gid") String gid
			){
		
		inventoryRestHelper.updateProductUnitStatus(productGroup, productCode, unitTitle, useFlag, gid);
		return new ReturnMessage("Complete", true);
	}
	
	@RequestMapping(value="/api/inventory/updateProductOptionStatus", method=RequestMethod.POST)
	public @ResponseBody ReturnMessage updateProductOptionStatus (
			@RequestParam("product_group") String productGroup,
			@RequestParam("product_code") String productCode,
			@RequestParam("option_title") String optionTitle,
			@RequestParam("use_flag") String useFlag,
			@RequestParam("gid") String gid
			) {
		inventoryRestHelper.updateProductOptionStatus(productGroup, productCode, optionTitle, useFlag, gid);
		return new ReturnMessage("Complete", true);
	}
	
	@RequestMapping(value="/api/inventory/updateProductHost", method=RequestMethod.POST)
	public @ResponseBody ReturnMessage updateProductHost(
			@RequestParam("visible") String visible,
			@RequestParam("product_no") String productNo,
			@RequestParam("host") String hostCode,
			@RequestParam("gid") String gid
			) {
		productRestHelper.updateProductHost(visible, productNo, hostCode, gid);
		return new ReturnMessage("Complete", true);
	}
	
	@RequestMapping(value="/api/inventory/updateInventoryLocation", method=RequestMethod.POST) 
	public @ResponseBody ReturnMessage updateInventoryLocation (
			@RequestParam("barcode") String barcode,
			@RequestParam("location1") String location1,
			@RequestParam("location2") String location2,
			@RequestParam("gid") String gid
			) {
		inventoryRestHelper.updateInventoryLocation(barcode, location1, location2, gid);
		
		return new ReturnMessage("Complete", true);
	}
}
