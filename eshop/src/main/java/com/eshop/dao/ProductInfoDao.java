package com.eshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.eshop.pojo.ProductInfo;

public interface ProductInfoDao {
	// 获取所有商品
	@Select("select * from product_info")
	public List<ProductInfo> selectProductInfo();

	// 根据商品id获取商品
	@Select("select * from product_info where id = #{id}")
	public ProductInfo selectProductInfoById(@Param("id") int id);
}
