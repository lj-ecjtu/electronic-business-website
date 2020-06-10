package com.eshop.service;

import com.eshop.pojo.OrderDetail;
import com.eshop.pojo.OrderInfo;

public interface OrderInfoService {

	// 添加订单主表
	public int addOrderInfo(OrderInfo oi);

	// 添加订单明细
	public int addOrderDetail(OrderDetail od);

}
