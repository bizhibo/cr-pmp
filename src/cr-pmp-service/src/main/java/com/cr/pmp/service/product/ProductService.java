package com.cr.pmp.service.product;

import java.util.Map;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.model.product.Product;

public interface ProductService {

	public Result queryProductPageList(Map<String, Object> params);

	public Result addProduct(Product product);

	public Result queryProductInfo(Integer id);

	public Result delProduct(Integer id);
}
