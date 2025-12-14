package com.Ecom.EcommerceApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecom.EcommerceApp.model.Product;
import com.Ecom.EcommerceApp.service.ProductService;


@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	ProductService service;
	
	@GetMapping("/Products")
	public List<Product> getProducts() {
		System.out.println("Hello");
		return service.getAllProducts();
	}
}
