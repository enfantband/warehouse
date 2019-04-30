package com.samsbeauty.warehouse.old.helper;

import com.samsbeauty.old.model.OrderInfo;
import com.samsbeauty.old.model.OutboundItem;
import com.samsbeauty.old.model.PendingOrderBean;
import com.samsbeauty.old.model.ReturnMessage;
import com.samsbeauty.old.model.ReturnMessageWithList;
import com.samsbeauty.old.model.ReturnMessageWithListAndModel;

public interface OutboundRestHelper {
	ReturnMessageWithListAndModel<OutboundItem, OrderInfo> getOrderInfoAndItemByOrderNo (String orderNo, String gid);
	ReturnMessage sendPendingOrderItemList(String orderNo, String outboundList, String gid, String host);
	ReturnMessage outboundOrder(String locationBarcode, String boxBarcode, String orderNo, String outboundList, String gid, String host);
	ReturnMessage clearTransaction(String orderNo, String gid);
	ReturnMessageWithList<PendingOrderBean> getPendingOrder(String gid, String orderNo, Integer lpp);
	void ready(String orderNo, String gid);
}
