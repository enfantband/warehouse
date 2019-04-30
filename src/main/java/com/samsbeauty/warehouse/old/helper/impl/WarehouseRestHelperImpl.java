package com.samsbeauty.warehouse.old.helper.impl;

import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.samsbeauty.old.model.InventoryBean;
import com.samsbeauty.old.model.JSONReturnModel;
import com.samsbeauty.old.model.LocationItemInput;
import com.samsbeauty.old.model.LocationItemTransferInput;
import com.samsbeauty.old.model.ReturnMessageWithList;
import com.samsbeauty.old.model.WarehouseItemBean;
import com.samsbeauty.warehouse.old.helper.HttpRestHelper;
import com.samsbeauty.warehouse.old.helper.WarehouseRestHelper;
import com.samsbeauty.warehouse.util.BarcodeUtil;

@Component
public class WarehouseRestHelperImpl extends HttpRestHelper implements WarehouseRestHelper {
	@Value("{samsbeauty.api.warehouse.item.insert}")
	private String apiWarehouseItemInsert;
	
	@Value("{samsbeauty.api.warehouse.item.list}")
	private String apiWarehouseItemList;
	
	@Value("{samsbeauty.api.warehouse.item.delete}")
	private String apiWarehouseItemDelete;
	
	@Value("{samsbeauty.api.warehouse.item.transfer}")
	private String apiWarehouseItemTransfer;
	
	@Value("{samsbeauty.api.warehouse.items.delete}")
	private String apiWarehouseItemsDelete;
	
	@Value("{samsbeauty.api.warehouse.items.empty")
	private String apiWarehouseItemsEmpty;
	
	
	@Override
	public JSONReturnModel<String> addLocation(LocationItemInput<InventoryBean> itemInput) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.add("Accept", "application/json");
	    params.add("locationBarcode", itemInput.getLocationBarcode());
	    params.add("boxBarcode", itemInput.getBoxBarcode());
	    // Make id and generated barcode list to string to post
	    StringBuilder sb = new StringBuilder();
	    if(itemInput.getItems() != null && itemInput.getItems().size() > 0) {
	    	for(int i=0; i<itemInput.getItems().size(); i++) {
	    		InventoryBean item = itemInput.getItems().get(i);
	    		sb.append(item.getProductId());
	    		sb.append(":");
	    		sb.append(BarcodeUtil.getBarcode(item.getGeneratedBarcode()));
				sb.append(":");
				sb.append(item.getQuantity());
				if(i != itemInput.getItems().size() - 1) {
					sb.append(",");
				}
	    	}
	    }
	    params.add("idAndBarcodes", sb.toString());
	    params.add("gid", itemInput.getGid());
		
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiWarehouseItemInsert + "?ACCESS_KEY=" + apiCommonKey, params, String.class);
		
		Gson gson = new Gson();
		Type collectionType = new TypeToken<JSONReturnModel<String>>(){}.getType();
		
		JSONReturnModel<String> res = gson.fromJson(response.getBody(),  collectionType);
		
		return res;
	}

	@Override
	public ReturnMessageWithList<WarehouseItemBean> getWarehouseItemList(String locationBarcode, String boxBarcode,
			String gid) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.add("Accept", "application/json");
	    params.add("locationBarcode", locationBarcode);
	    params.add("boxBarcode", boxBarcode);
	    params.add("gid", gid);
		
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiWarehouseItemList + "?ACCESS_KEY=" + apiCommonKey, params, String.class);
		
		Gson gson = new Gson();
		Type collectionType = new TypeToken<ReturnMessageWithList<WarehouseItemBean>>(){}.getType();
		
		ReturnMessageWithList<WarehouseItemBean> res = gson.fromJson(response.getBody(),  collectionType);
		
		return res;
	}

	@Override
	public JSONReturnModel<String> deleteItemInLocation(String itemId, String gid) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.add("Accept", "application/json");
	    params.add("itemId", itemId);
	    params.add("gid", gid);
		
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiWarehouseItemDelete + "?ACCESS_KEY=" + apiCommonKey, params, String.class);
		
		Gson gson = new Gson();
		Type collectionType = new TypeToken<JSONReturnModel<String>>(){}.getType();
		
		JSONReturnModel<String> res = gson.fromJson(response.getBody(),  collectionType);
		
		return res;
	}

	@Override
	public JSONReturnModel<String> deleteItemsInLocation(LocationItemInput<WarehouseItemBean> itemInput)
			throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.add("Accept", "application/json");
	    
	    StringBuilder sb = new StringBuilder();
	    if(itemInput.getItems() != null && itemInput.getItems().size() > 0) {
	    	for(int i=0; i<itemInput.getItems().size(); i++) {
	    		WarehouseItemBean item = itemInput.getItems().get(i);
	    		sb.append(item.getItemId());
	    		sb.append(":");
	    		sb.append(BarcodeUtil.getBarcode(item.getGeneratedBarcode()));
	    		sb.append(":");
	    		sb.append(item.getQuantity());
	    		if(i != itemInput.getItems().size() - 1) {
					sb.append(",");
				}
	    	}
	    }
	    params.add("idAndBarcodes", sb.toString());
	    params.add("gid", itemInput.getGid());
	    
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiWarehouseItemDelete + "?ACCESS_KEY=" + apiCommonKey, params, String.class);
		
		Gson gson = new Gson();
		Type collectionType = new TypeToken<JSONReturnModel<String>>(){}.getType();
		
		JSONReturnModel<String> res = gson.fromJson(response.getBody(),  collectionType);
		
		return res;
	}

	@Override
	public JSONReturnModel<String> updateToMakeItemsEmpty(LocationItemInput<WarehouseItemBean> itemInput)
			throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.add("Accept", "application/json");
	    StringBuilder sb = new StringBuilder();
	    if(itemInput.getItems() != null && itemInput.getItems().size() > 0) {
	    	for(int i=0; i<itemInput.getItems().size(); i++) {
	    		WarehouseItemBean item = itemInput.getItems().get(i);
	    		sb.append(item.getItemId());
	    		sb.append(":");
	    		sb.append(BarcodeUtil.getBarcode(item.getGeneratedBarcode()));
	    		sb.append(":");
	    		sb.append(item.getQuantity());
	    		if(i != itemInput.getItems().size() - 1) {
	    			sb.append(",");
	    		}
	    	}
	    }
	    params.add("idAndBarcodes", sb.toString());
	    params.add("gid", itemInput.getGid());
	    
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiWarehouseItemsEmpty + "?ACCESS_KEY=" + apiCommonKey, params, String.class);
		
		Gson gson = new Gson();
		Type collectionType = new TypeToken<JSONReturnModel<String>>(){}.getType();
		
		JSONReturnModel<String> res = gson.fromJson(response.getBody(),  collectionType);
		
		return res;
	}

	@Override
	public JSONReturnModel<String> transferItemsToLocation(LocationItemTransferInput itemInput) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.add("Accept", "application/json");
	    StringBuilder sb = new StringBuilder();
	    if(itemInput.getItems() != null && itemInput.getItems().size() > 0) {
	    	for(int i=0; i<itemInput.getItems().size(); i++) {
	    		WarehouseItemBean item = itemInput.getItems().get(i);
	    		sb.append(item.getItemId());
				sb.append(":");
				sb.append(item.getQuantity());
				if(i != itemInput.getItems().size() - 1) {
					sb.append(",");
				}
	    	}
	    }
	    params.add("fromLocationBarcode", itemInput.getFromLocationBarcode());
	    params.add("toLocationBarcode", itemInput.getToLocationBarcode());
	    params.add("toBoxBarcode", itemInput.getToBoxBarcode());
	    params.add("idAndBarcodes", sb.toString());
	    params.add("gid", itemInput.getGid());
	    
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiWarehouseItemTransfer + "?ACCESS_KEY=" + apiCommonKey, params, String.class);
		
		Gson gson = new Gson();
		Type collectionType = new TypeToken<JSONReturnModel<String>>(){}.getType();
		
		JSONReturnModel<String> res = gson.fromJson(response.getBody(),  collectionType);
		
		return res;
	}
	
}
