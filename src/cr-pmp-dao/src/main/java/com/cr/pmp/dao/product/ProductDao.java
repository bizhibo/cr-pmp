package com.cr.pmp.dao.product;

import java.util.List;
import java.util.Map;

import com.cr.pmp.model.product.Product;

public interface ProductDao {

	public List<Product> queryProductPageList(Map<String, Object> params);

	public Integer queryCount(Map<String, Object> params);

	public Integer addProduct(Product product);

	public Product queryProductInfo(Integer id);

	public Integer delProduct(Integer id);
}
