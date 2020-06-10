package com.eshop.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.eshop.pojo.OrderDetail;
import com.eshop.pojo.OrderInfo;

public interface OrderInfoDao {
	// 保存订单主表
	@Insert("insert into order_info(uid,status,ordertime,orderprice) "
			+ "values(#{uid},#{status},#{ordertime},#{orderprice})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int saveOrderInfo(OrderInfo oi);

	// 保存订单明细
	@Insert("insert into order_detail(oid,pid,num) values(#{oid},#{pid},#{num})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int saveOrderDetail(OrderDetail od);

}
