package com.samsbeauty.warehouse.old.helper.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.samsbeauty.old.model.Vendor;
import com.samsbeauty.warehouse.old.helper.HttpRestHelper;
import com.samsbeauty.warehouse.old.helper.VendorHelper;

@Component
public class VendorHelperImpl extends HttpRestHelper implements VendorHelper {
	@Value("${samsbeauty.api.vendor.list}")
	private String apiVendorList;
	
	public List<Vendor> getVendorList() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		// Assume that the total number of vendors don't exceed over 5000. It does not need paging function so just get all items from api server.
		
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiVendorList + "?ACCESS_KEY=" + apiCommonKey + "&lpp=5000", request, String.class);
		
		Gson gson = new Gson();
		Type collectionType = new TypeToken<List<Vendor>>(){}.getType();
		
		List<Vendor> vendorList = gson.fromJson(response.getBody(), collectionType);
		return vendorList;
	}
}
