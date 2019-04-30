package com.samsbeauty.warehouse.old.helper;

import java.util.List;

import com.samsbeauty.old.model.OrderInfoForPicking;
import com.samsbeauty.old.model.OrderItem;
import com.samsbeauty.old.model.OrderMemoListBean;
import com.samsbeauty.old.model.ReturnMessageWithList;

public interface OrderInfoHelper {
	Integer getNumberOfNewOrders() throws Exception;
	List<OrderItem> getOrderItemList(Integer numberOfProcess, Integer pickingSet, String includingOrders, Integer amzhs, Integer amzeb);
	List<OrderInfoForPicking> getOrderInfoForPicking(String orderNos, Integer pickingSet);
	ReturnMessageWithList<OrderMemoListBean> getOrderMemoList(String orderNo, String gid, String orderBy, String orderDesc);
}
