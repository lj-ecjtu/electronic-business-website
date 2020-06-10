package com.eshop.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.pojo.CartItem;
import com.eshop.pojo.OrderDetail;
import com.eshop.pojo.OrderInfo;
import com.eshop.pojo.ProductInfo;
import com.eshop.service.OrderInfoService;
import com.eshop.service.ProductInfoService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/order")
public class OrderInfoController {
	@Autowired
	OrderInfoService orderInfoService;
	@Autowired
	ProductInfoService productInfoService;

	@RequestMapping(value = "/handlerOrder", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String handlerOrder(String cart) throws JsonParseException, JsonMappingException, IOException {
		
		// 创建ObjectMapper对象,实现JavaBean和JSON的转换
		ObjectMapper mapper = new ObjectMapper();
		// 将JSON字符串转换成List<CartItem>集合
		List<CartItem> ciList = mapper.readValue(cart, new TypeReference<ArrayList<CartItem>>() {
		});
		OrderInfo oi = new OrderInfo();
		oi.setUid(1);
		oi.setStatus("未付款");
		oi.setOrdertime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		double orderPrice = 0;
		for (CartItem ci : ciList) {
			ProductInfo pi = productInfoService.getProductInfoById(ci.getId());
			orderPrice += ci.getCount() * pi.getPrice();
		}
		oi.setOrderprice(orderPrice);
		// 保存订单信息
		orderInfoService.addOrderInfo(oi);
		for (CartItem ci : ciList) {
			OrderDetail od=new OrderDetail();
			od.setOid(oi.getId());
			od.setPid(ci.getId());
			od.setNum(ci.getCount());
			// 保存订单明细
			orderInfoService.addOrderDetail(od);
		}
		return "sucess";
	}
}
