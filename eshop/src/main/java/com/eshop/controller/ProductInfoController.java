package com.eshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.pojo.ProductInfo;
import com.eshop.service.ProductInfoService;

@Controller
@RequestMapping("/product")
public class ProductInfoController {
	@Autowired
	ProductInfoService productInfoService;

	@RequestMapping(value = "/getProduct", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<ProductInfo> getProductInfo() {
		List<ProductInfo> pList = productInfoService.getProductInfo();
		return pList;
	}

	@RequestMapping(value = "/getProductById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ProductInfo getProductById(@PathVariable("id") Integer id) {
		ProductInfo Product = productInfoService.getProductInfoById(id);
		return Product;
	}
}
