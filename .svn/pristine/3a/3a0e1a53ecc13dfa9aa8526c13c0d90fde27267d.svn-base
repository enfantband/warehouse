package com.samsbeauty.warehouse.old.helper.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.samsbeauty.warehouse.old.helper.HttpRestHelper;
import com.samsbeauty.warehouse.old.helper.ProductRestHelper;

@Component
public class ProductRestHelperImpl extends HttpRestHelper implements ProductRestHelper {

	@Value("${samsbeauty.api.product.host.update}")
	private String apiProductHostUpdate;
	
	public void updateProductHost(String visible, String productNo, String hostCode, String gid) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		restTemplate.postForEntity(apiUrl + apiProductHostUpdate + "?ACCESS_KEY=" + apiCommonKey + "&visible=" + visible + "&product_no=" + productNo + "&host=" + hostCode + "&gid=" + gid, request, String.class );
	}
}
