package com.cr.pmp.service.product.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.common.utils.LogUtils;
import com.cr.pmp.common.utils.PaginatedArrayList;
import com.cr.pmp.common.utils.PaginatedList;
import com.cr.pmp.dao.product.ProductDao;
import com.cr.pmp.dao.user.UserDao;
import com.cr.pmp.model.product.Product;
import com.cr.pmp.model.user.User;
import com.cr.pmp.service.product.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;

	@Override
	public Result queryProductPageList(Map<String, Object> params) {
		Result result = new Result();
		try {
			int count = productDao.queryCount(params);
			if (params.get("page") == null || params.get("page").equals("")
					|| params.get("page").equals("0")) {
				params.put("page", PaginatedArrayList.PAGEINDEX_DEFAULT);
			}
			if (params.get("pageSize") == null
					|| params.get("pageSize").equals("")
					|| params.get("pageSize").equals("0")) {
				params.put("pageSize", PaginatedArrayList.PAGESIZE_DEFAULT);
			}
			PaginatedList<Product> pageList = new PaginatedArrayList<Product>(
					Integer.valueOf(params.get("page").toString()),
					Integer.valueOf(params.get("pageSize").toString()), count);
			params.put("startRow",
					pageList.getStartRow() > 0 ? pageList.getStartRow() - 1
							: pageList.getStartRow());
			List<Product> productList = productDao.queryProductPageList(params);
			pageList.addAll(productList);
			result.addObject("pageList", pageList);
			result.addObject("params", params);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result addProduct(Product product) {
		Result result = new Result();
		try {
			int flag = productDao.addProduct(product);
			if (flag > 0) {
				result.setResultCode(true);
			} else {
				result.setResultCode(false);
			}
		} catch (Exception e) {
			result.setResultCode(false);
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result queryProductInfo(Integer id) {
		Result result = new Result();
		try {
			Product product = productDao.queryProductInfo(id);
			if (product != null) {
				User user = userDao.queryUserInfo(product.getLeader());
				result.addObject("user", user);
				result.addObject("product", product);
			}
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result delProduct(Integer id) {
		Result result = new Result();
		try {
			Integer flag = productDao.delProduct(id);
			if (flag > 0) {
				result.setResultCode(true);
			} else {
				result.setResultCode(false);
			}
		} catch (Exception e) {
			result.setResultCode(false);
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}
}
