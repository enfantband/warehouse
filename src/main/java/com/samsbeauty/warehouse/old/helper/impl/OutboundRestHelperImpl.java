package com.samsbeauty.warehouse.old.helper.impl;

import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.samsbeauty.old.model.OrderInfo;
import com.samsbeauty.old.model.OutboundItem;
import com.samsbeauty.old.model.PendingOrderBean;
import com.samsbeauty.old.model.ReturnMessage;
import com.samsbeauty.old.model.ReturnMessageWithList;
import com.samsbeauty.old.model.ReturnMessageWithListAndModel;
import com.samsbeauty.warehouse.old.helper.HttpRestHelper;
import com.samsbeauty.warehouse.old.helper.OutboundRestHelper;

@Component
public class OutboundRestHelperImpl extends HttpRestHelper implements OutboundRestHelper {
	@Value("${samsbeauty.api.outbound.order.itemlist}")
	private String apiOutboundOrderItemList;
	
	@Value("${samsbeauty.api.outbound.order.insert}")
	private String apiOutboundOrderItem;
	
	@Value("${samsbeauty.api.pending.order}")
	private String apiPendingOrderList;
	
	@Value("${samsbeauty.api.pending.ready}")
	private String apiPendingReady;
	
	@Value("${samsbeauty.api.pending.finish}")
	private String apiPendingFinish;
	
	@Value("${samsbeauty.api.pending.clear}")
	private String apiPendingClear;
	
	@Override
	public ReturnMessageWithListAndModel<OutboundItem, OrderInfo> getOrderInfoAndItemByOrderNo (String orderNo, String gid) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiOutboundOrderItemList + "?ACCESS_KEY=" + apiCommonKey + "&order_no=" + orderNo + "&gid=" + gid, request, String.class);
		Gson gson = new Gson();
		Type collectionType = new TypeToken<ReturnMessageWithList<OutboundItem>>(){}.getType();
		ReturnMessageWithList<OutboundItem> message= gson.fromJson(response.getBody(), collectionType);
		
		ReturnMessageWithListAndModel<OutboundItem, OrderInfo> msg = new ReturnMessageWithListAndModel<>();
		msg.setList(message.getList());
		Type colType = new TypeToken<OrderInfo>(){}.getType();
		msg.setModel(gson.fromJson(message.getEtc01(), colType));
		msg.setMessage(message.getReturnMessage());
		return msg;
	}
	
	@Override
	public ReturnMessage sendPendingOrderItemList(String orderNo, String outboundList, String gid, String host){
		RestTemplate restTemplate = new RestTemplate();
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.add("Accept", "application/json");
	    params.add("list", outboundList);
	    
        
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiPendingFinish + "?ACCESS_KEY=" + apiCommonKey + "&orderNo=" + orderNo + "&gid=" + gid, params, String.class);
		
		System.out.println(response.getBody());
		Gson gson = new Gson();
		Type objectType = new TypeToken<ReturnMessage>(){}.getType();
		ReturnMessage message = gson.fromJson(response.getBody(),  objectType);
		message.setMessage(message.getReturnMessage());
		return message;
	}
	
	@Override
	public ReturnMessage outboundOrder(String locationBarcode, String boxBarcode, String orderNo, String outboundList, String gid, String host) {
		RestTemplate restTemplate = new RestTemplate();

	    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("ACCESS_KEY", apiCommonKey);
        params.add("locationBarocde", locationBarcode);
        params.add("boxBarcode", boxBarcode);
        params.add("orderNo", orderNo);
        params.add("list", outboundList);
        params.add("gid", gid);
        params.add("host", host);
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiOutboundOrderItem, params, String.class);
		
		Gson gson = new Gson();
		Type objectType = new TypeToken<ReturnMessage>(){}.getType();
		ReturnMessage message = gson.fromJson(response.getBody(), objectType);
		message.setMessage(message.getReturnMessage());
		return message;
	}
	
	@Override
	public ReturnMessage clearTransaction(String orderNo, String gid) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiPendingClear + "?ACCESS_KEY=" + apiCommonKey + "&orderNo=" + orderNo + "&gid=" + gid, request, String.class);
		
		Gson gson = new Gson();
		Type objectType = new TypeToken<ReturnMessage>(){}.getType();
		ReturnMessage message = gson.fromJson(response.getBody(), objectType);
		message.setMessage(message.getReturnMessage());
		return message;
	}
	
	@Override
	public ReturnMessageWithList<PendingOrderBean> getPendingOrder(String gid, String orderNo, Integer lpp) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + apiPendingOrderList + "?ACCESS_KEY=" + apiCommonKey + "&orderNo=" + orderNo + "&gid=" + gid + "&lpp=" + (lpp == null ? "1000" : lpp.toString()), request, String.class);
		
		Gson gson = new Gson();
		Type objectType = new TypeToken<ReturnMessageWithList<PendingOrderBean>>(){}.getType();
		ReturnMessageWithList<PendingOrderBean> message = gson.fromJson(response.getBody(), objectType);
		return message;
	}
	
	@Override
	public void ready(String orderNo, String gid) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		restTemplate.postForEntity(apiUrl + apiPendingReady + "?ACCESS_KEY=" + apiCommonKey + "&orderNo=" + orderNo + "&gid=" + gid, request, String.class);
	}
}
