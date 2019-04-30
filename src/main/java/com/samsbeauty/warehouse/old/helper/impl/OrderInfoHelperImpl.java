package com.samsbeauty.warehouse.old.helper.impl;

import java.lang.reflect.Type;
import java.util.List;

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
import com.samsbeauty.old.model.OrderInfoForPicking;
import com.samsbeauty.old.model.OrderInfoList;
import com.samsbeauty.old.model.OrderItem;
import com.samsbeauty.old.model.OrderItemList;
import com.samsbeauty.old.model.OrderMemoListBean;
import com.samsbeauty.old.model.ReturnMessage;
import com.samsbeauty.old.model.ReturnMessageWithList;
import com.samsbeauty.warehouse.old.helper.HttpRestHelper;
import com.samsbeauty.warehouse.old.helper.OrderInfoHelper;

@Component
public class OrderInfoHelperImpl extends HttpRestHelper implements OrderInfoHelper {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${samsbeauty.api.order.count}")
	private String apiOrderCount;
	
	@Value("${samsbeauty.api.order.items}")
	private String apiOrderItemList;
	
	@Value("${samsbeauty.api.order.memo}")
	private String apiOrderMemoList;
	
	@Value("${samsbeauty.api.order.picking}")
	private String apiOrderPicking;

	@Override
	public Integer getNumberOfNewOrders() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiOrderCount + "?ACCESS_KEY=" + apiCommonKey, request, String.class);
		Gson gson = new Gson();
		ReturnMessage returnMessage = gson.fromJson(response.getBody(), ReturnMessage.class);
		Integer numberOfNewOrders = 0;
		try {
			numberOfNewOrders = Integer.parseInt(returnMessage.getReturnMessage());
		} catch(NumberFormatException e) {
			
		}
		return numberOfNewOrders;
	}

	@Override
	public List<OrderItem> getOrderItemList(Integer numberOfProcess, Integer pickingSet, String includingOrders, Integer amzhs, Integer amzeb) {
		RestTemplate restTemplate = new RestTemplate();	
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiOrderItemList + "?ACCESS_KEY=" + apiCommonKey + "&number_of_process=" + numberOfProcess + "&set=" + pickingSet + "&including_orders=" + includingOrders + "&amzhs=" + amzhs + "&amzeb=" + amzeb, request, String.class);
		Gson gson = new Gson();
		OrderItemList itemList = gson.fromJson(response.getBody(), OrderItemList.class);
		return itemList.getList();
	}
	
	@Override
	public List<OrderInfoForPicking> getOrderInfoForPicking(String orderNos, Integer pickingSet) {
		RestTemplate restTemplate = new RestTemplate();	
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiOrderPicking + "?ACCESS_KEY=" + apiCommonKey + "&orderNos=" + orderNos + "&set=" + pickingSet, request, String.class);
		Gson gson = new Gson();
		OrderInfoList infoList = gson.fromJson(response.getBody(), OrderInfoList.class);
		return infoList.getList();
	}
	
	@Override
	public ReturnMessageWithList<OrderMemoListBean> getOrderMemoList(String orderNo, String gid, String orderBy, String orderDesc) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiOrderMemoList + "?ACCESS_KEY=" + apiCommonKey + "&order_no=" + orderNo + "&gid=" + gid + "&orderBy=" + orderBy + "&orderDesc=" + orderDesc, request, String.class);
		Gson gson = new Gson();
		Type collectionType = new TypeToken<ReturnMessageWithList<OrderMemoListBean>>(){}.getType();
		ReturnMessageWithList<OrderMemoListBean> list = gson.fromJson(response.getBody(),  collectionType);
		return list;
	}
}
