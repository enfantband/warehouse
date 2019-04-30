package com.samsbeauty.warehouse.old.helper.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.samsbeauty.old.model.Company;
import com.samsbeauty.old.model.CompanyList;
import com.samsbeauty.warehouse.old.helper.CompanyRestHelper;
import com.samsbeauty.warehouse.old.helper.HttpRestHelper;

@Component
public class CompanyRestHelperImpl extends HttpRestHelper implements CompanyRestHelper {
	
	@Value("${samsbeauty.api.company}")
	protected String apiCompany;
	
	public List<Company> getList() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(headers);
        
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiCompany + "?ACCESS_KEY=" + apiCommonKey, request, String.class);
		Gson gson = new Gson();
		CompanyList companyList = gson.fromJson(response.getBody(), CompanyList.class);
		return companyList.getList(); 
	}
}
