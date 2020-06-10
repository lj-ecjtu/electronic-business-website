package com.eshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.dao.ProductInfoDao;
import com.eshop.pojo.ProductInfo;
import com.eshop.service.ProductInfoService;

@Service("productInfoService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class ProductInfoServiceImpl implements ProductInfoService {

	@Autowired
	ProductInfoDao productInfoDao;

	@Override
	public List<ProductInfo> getProductInfo() {
		return productInfoDao.selectProductInfo();
	}

	@Override
	public ProductInfo getProductInfoById(int id) {
		return productInfoDao.selectProductInfoById(id);
	}

}
