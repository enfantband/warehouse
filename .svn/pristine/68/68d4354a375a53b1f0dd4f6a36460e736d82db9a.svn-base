package com.samsbeauty.warehouse.old.helper.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.samsbeauty.old.model.Code;
import com.samsbeauty.old.model.CommonCode;
import com.samsbeauty.old.model.Cost;
import com.samsbeauty.old.model.Inventory;
import com.samsbeauty.old.model.InventoryHistoryList;
import com.samsbeauty.old.model.InventoryList;
import com.samsbeauty.warehouse.old.helper.HttpRestHelper;
import com.samsbeauty.warehouse.old.helper.InventoryRestHelper;

@Component
public class InventoryRestHelperImpl extends HttpRestHelper implements InventoryRestHelper {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/* Inventory Variables */
	@Value("${samsbeauty.api.inventory}")
	private String apiInventory;
	
	@Value("${samsbeauty.api.inventory.update}")
	private String apiInventoryUpdate;
	
	@Value("${samsbeauty.api.inventory.history.reason}")
	private String apiInventoryHistoryReason;
	
	@Value("${samsbeauty.api.inventory.history.insert}")
	private String apiInventoryHistoryInsert;
	
	@Value("${samsbeauty.api.inventory.history.list}")
	private String apiInventoryHistoryList;
	
	@Value("${samsbeauty.api.inventory.status}")
	private String apiInventoryStatus;
	
	@Value("${samsbeauty.api.inventory.cost}")
	private String apiInventoryCost;
	
	@Value("${samsbeauty.api.inventory.cost.insert}")
	private String apiInventoryCostInsert;
	
	@Value("${samsbeauty.api.inventory.cost.update}")
	private String apiInventoryCostUpdate;
	
	@Value("${samsbeauty.api.inventory.cost.delete}")
	private String apiInventoryCostDelete;
	
	@Value("${samsbeauty.api.inventory.location}") // This is not warehouse location info
	private String apiInventoryLocation; 
	
	@Value("${samsbeauty.api.inventory.location.update}")
	private String apiInventoryLocationUpdate;
	
	@Value("${samsbeauty.api.inventory.order}")
	private String apiInventoryOrder;
	
	@Value("${samsbeauty.api.inventory.unitoption.status.update}")
	private String apiInventoryUnitOptionStatusUpdate;
	/* Inventory Variable Ends */
	

	private static Map<String, String> InventoryHistoryCategoryMap = new HashMap<>();
	static {
		InventoryHistoryCategoryMap.put("In", "006002000");
		InventoryHistoryCategoryMap.put("Out", "006001000");
		InventoryHistoryCategoryMap.put("Both", "");
		InventoryHistoryCategoryMap.put("", "");
	}
	
	@Override
	public List<Inventory> getInventoryList(List<String> barcodes) {
		if(barcodes.size() > 0) {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<String> request = new HttpEntity<String>(headers);
			
			StringBuilder sb = new StringBuilder();

			for(int i=0; i<barcodes.size(); i++) {
				sb.append("'");
				sb.append(barcodes.get(i));
				if(i == barcodes.size() - 1) {
					sb.append("'");
				} else {
					sb.append("',");
				}
			}
			
			ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiInventory + "?ACCESS_KEY=" + apiCommonKey + "&barcodes=" + sb.toString(), request, String.class);
			
			Gson gson = new Gson();
			InventoryList inventoryList = gson.fromJson(response.getBody(), InventoryList.class);
			return inventoryList.getList();
		}
		return new ArrayList<>();
	}
	@Override
	public List<Inventory> getInventoryList(String searchKey, String pageNo, Integer lpp, String searchType) throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		String urlParam = apiUrl + apiInventory + "?ACCESS_KEY=" + apiCommonKey + "&searchKey=" + searchKey + "&pageNo=" + pageNo + "&lpp=" + lpp + "&searchType=" + searchType;		
		ResponseEntity<String> response = restTemplate.postForEntity(urlParam, request, String.class);		
		Gson gson = new Gson();
		InventoryList inventoryList = gson.fromJson(response.getBody(), InventoryList.class);
		return inventoryList.getList();
	}
	@Override
	public List<Code> getInventoryReasonList() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiInventoryHistoryReason + "?ACCESS_KEY=" + apiCommonKey, request, String.class);
		
		Gson gson = new Gson();

		Type collectionType = new TypeToken<List<CommonCode>>(){}.getType();
		List<CommonCode> codeList = gson.fromJson(response.getBody(), collectionType);
		List<Code> reasons = new ArrayList<>();
		for(CommonCode commonCode : codeList) {
			Code reason = new Code();
			reason.setCode(commonCode.getCcCode());
			reason.setTitle(commonCode.getCcTitle());
			reasons.add(reason);
		}
		return reasons;
	}
	@Override
	public List<Code> getInventoryStatusList() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiInventoryStatus + "?ACCESS_KEY=" + apiCommonKey, request, String.class);
		
		Gson gson = new Gson();

		Type collectionType = new TypeToken<List<CommonCode>>(){}.getType();
		List<CommonCode> codeList = gson.fromJson(response.getBody(), collectionType);
		List<Code> reasons = new ArrayList<>();
		for(CommonCode commonCode : codeList) {
			Code reason = new Code();
			reason.setCode(commonCode.getCcCode());
			reason.setTitle(commonCode.getCcTitle());
			reasons.add(reason);
		}
		return reasons;
	}
	@Override
	public void insertInOutStock(String orderNo, String quantity, String  status, String  barcode, String reason, String gid) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(headers);
		restTemplate.postForEntity(apiUrl + apiInventoryHistoryInsert + "?ACCESS_KEY=" + apiCommonKey + "&order_no=" + orderNo + "&quantity=" + quantity + "&status=" + status + "&barcode=" + barcode + "&reason=" + reason + "&gid=" + gid, request, String.class);
	}
	
	@Override
	public void updateInventory(String productBarcode, String productId, String statusId, String gid) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		restTemplate.postForEntity(apiUrl + apiInventoryUpdate + "?ACCESS_KEY=" + apiCommonKey + "&product_barcode=" + productBarcode + "&product_id=" + productId + "&status_id=" + statusId + "&gid=" + gid, request, String.class );
	}
	
	@Override
	public List<Cost> getCostList(String barcode) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiInventoryCost + "?ACCESS_KEY=" + apiCommonKey + "&barcode=" + barcode, request, String.class );
		Type collectionType = new TypeToken<List<Cost>>(){}.getType();

		Gson gson = new Gson();
		List<Cost> codeList = gson.fromJson(response.getBody(), collectionType);
		return codeList;
	}
	
	@Override
	public Cost getCost(String barcode) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiInventoryCost + "?ACCESS_KEY=" + apiCommonKey + "&barcode=" + barcode + "&startNo=0&endNo=1", request, String.class );
		Type collectionType = new TypeToken<List<Cost>>(){}.getType();

		Gson gson = new Gson();
		List<Cost> costList = gson.fromJson(response.getBody(), collectionType);
		if(costList.size() > 0) {
			return costList.get(0);
		} 
		throw new Exception("Error during communicating with the server");
	}
	
	@Override
	public void insertCost(String barcode, String price, String salePrice, String gid) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		restTemplate.postForEntity(apiUrl + apiInventoryCostInsert + "?ACCESS_KEY=" + apiCommonKey + "&sph_barcode=" + barcode + "&sph_price=" + price + "&sph_sale_price=" + salePrice + "&gid=" + gid, request, String.class );
	}
	
	@Override
	public void updateCost(String costId, String price, String salePrice, String gid) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		restTemplate.postForEntity(apiUrl + apiInventoryCostUpdate + "?ACCESS_KEY=" + apiCommonKey + "&sph_seq=" + costId + "&sph_price=" + price + "&sph_sale_price=" + salePrice + "&gid=" + gid, request, String.class );
	}
	
	@Override
	public void deleteCost(String costId, String gid) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		restTemplate.postForEntity(apiUrl + apiInventoryCostInsert + "?ACCESS_KEY=" + apiCommonKey + "&sph_seq=" + costId + "&gid=" + gid, request, String.class );
	}
	
	@Override
	public List<Code> getInventoryLocationList() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiInventoryLocation + "?ACCESS_KEY=" + apiCommonKey, request, String.class);
		
		Gson gson = new Gson();

		Type collectionType = new TypeToken<List<CommonCode>>(){}.getType();
		List<CommonCode> codeList = gson.fromJson(response.getBody(), collectionType);
		List<Code> reasons = new ArrayList<>();
		for(CommonCode commonCode : codeList) {
			Code reason = new Code();
			reason.setCode(commonCode.getCcCode());
			reason.setTitle(commonCode.getCcTitle());
			reasons.add(reason);
		}
		return reasons;
	}
	
	@Override
	public void updateInventoryLocation(String barcode, String location1, String location2, String gid) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(headers);
		String urlParam = apiUrl + apiInventoryLocationUpdate + "?ACCESS_KEY=" + apiCommonKey + "&barcode=" + barcode + "&location1=" + location1 + "&location2=" + location2 + "&gid=" + gid;
		logger.debug("Connect to " + urlParam);
		restTemplate.postForEntity(urlParam, request, String.class);
	}
	
	@Override
	public InventoryHistoryList getInventoryHistoryList(String category, String barcode, String pageNo, Integer lpp, String orderBy, String orderDesc) {
		String status = InventoryHistoryCategoryMap.get(category);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiInventoryHistoryList + "?ACCESS_KEY=" + apiCommonKey + "&status=" + status + "&barcode=" + barcode + "&pageNo=" + pageNo + "&lpp=" + lpp.toString() + "&orderBy=" + orderBy + "&orderDesc" + orderDesc, request, String.class );

		Gson gson = new Gson();
		InventoryHistoryList historyList = gson.fromJson(response.getBody(), InventoryHistoryList.class);
		return historyList;
	}
	@Override
	public void insertOrderToVendor(String productIds, String quantities, String svCode, String gid) {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		restTemplate.postForEntity(apiUrl + apiInventoryOrder + "?ACCESS_KEY=" + apiCommonKey + "&product_id=" + productIds + "&orderQuantities=" + quantities + "&sv_code=" + svCode + "&gid=" + gid, request, String.class );
	}
	@Override
	public void updateProductUnitStatus(String productGroup, String productCode, String unitTitle, String useFlag, String gid) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		restTemplate.postForEntity(apiUrl + apiInventoryUnitOptionStatusUpdate + "?ACCESS_KEY=" + apiCommonKey + "&product_group=" + productGroup + "&product_code=" + productCode + "&unit_title=" + unitTitle + "&use_flag=" + useFlag + "&gid=" + gid, request, String.class);
	}
	@Override
	public void updateProductOptionStatus(String productGroup, String productCode, String optionTitle, String useFlag, String gid) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		restTemplate.postForEntity(apiUrl + apiInventoryUnitOptionStatusUpdate + "?ACCESS_KEY=" + apiCommonKey + "&product_group=" + productGroup + "&product_code=" + productCode + "&option_title=" + optionTitle + "&use_flag=" + useFlag + "&gid=" + gid, request, String.class);
	}
	@Override
	public InventoryList getItemInfoFromServerBySearchKey(String searchKey, String pageNo, Integer lpp, String searchType, String locationId) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiInventory + "?ACCESS_KEY=" + apiCommonKey + "&searchKey=" + searchKey + "&pageNo=" + pageNo + "&lpp=" + lpp.toString() + "&searchType=" + searchType + "&locationId=" + (locationId == null || locationId.equals("") ? "" : locationId), request, String.class);
		
		Gson gson = new Gson();
		InventoryList inventoryList = gson.fromJson(response.getBody(), InventoryList.class);
		return inventoryList;
	}
}
