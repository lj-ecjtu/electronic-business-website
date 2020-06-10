package com.eshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.dao.OrderInfoDao;
import com.eshop.pojo.OrderDetail;
import com.eshop.pojo.OrderInfo;
import com.eshop.service.OrderInfoService;

@Service("orderInfoService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class OrderInfoServiceImpl implements OrderInfoService {
	@Autowired
	OrderInfoDao orderInfoDao;

	@Override
	public int addOrderInfo(OrderInfo oi) {
		return orderInfoDao.saveOrderInfo(oi);
	}

	@Override
	public int addOrderDetail(OrderDetail od) {
		return orderInfoDao.saveOrderDetail(od);
	}

}
