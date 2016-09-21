package com.cr.pmp.controller.product;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cr.pmp.common.base.BaseController;
import com.cr.pmp.common.dict.FilePathDict;
import com.cr.pmp.common.result.Result;
import com.cr.pmp.common.utils.UUIDUtils;
import com.cr.pmp.model.product.Product;
import com.cr.pmp.service.product.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/index")
	public Result index() {
		Result result = new Result();
		result.setViewName("/product/index");
		return result;
	}

	@RequestMapping("/go-add-page")
	public Result goAddPage() {
		Result result = new Result();
		result.setViewName("/product/addProduct");
		return result;
	}

	@RequestMapping("/page-list")
	public Result queryProductList() {
		Result result = productService.queryProductPageList(this.getParams());
		result.setViewName("/product/productList");
		return result;
	}

	@RequestMapping("/add-product")
	@ResponseBody
	public String addProduct(MultipartFile addFile, String name, String code,
			String productDescribe, String leader) throws Exception {
		String path = request.getSession().getServletContext()
				.getRealPath(FilePathDict.PRODUCTFILEPATH);
		StringBuffer fileName = new StringBuffer(UUIDUtils.upperUUID())
				.append(addFile.getOriginalFilename());
		File targetFile = new File(path, fileName.toString());
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		addFile.transferTo(targetFile);
		Product product = new Product();
		product.setLeader(leader);
		product.setCode(code);
		product.setProductDescribe(productDescribe);
		product.setName(name);
		product.setFileUrl(FilePathDict.PRODUCTFILEPATH + fileName);
		Result result = productService.addProduct(product);
		return result.toJson();
	}

	@RequestMapping("/product-Info-{id}")
	public Result getProductInfo(@PathVariable("id") Integer id) {
		Result result = productService.queryProductInfo(id);
		result.setViewName("/product/productInfo");
		return result;
	}

	@RequestMapping("/del-product")
	@ResponseBody
	public String delProduct() {
		Result result = productService.delProduct(Integer.valueOf(this
				.getParams("id").toString()));
		return result.toJson();
	}
}
