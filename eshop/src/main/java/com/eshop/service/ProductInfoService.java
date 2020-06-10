package com.eshop.service;

import java.util.List;

import com.eshop.pojo.ProductInfo;

public interface ProductInfoService {
	// 获取所有商品
	public List<ProductInfo> getProductInfo();
	// 根据商品编号获取商品
	public ProductInfo getProductInfoById(int id);
}
