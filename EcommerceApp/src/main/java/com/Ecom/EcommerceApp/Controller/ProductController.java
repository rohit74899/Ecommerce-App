package com.Ecom.EcommerceApp.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Ecom.EcommerceApp.model.Product;
import com.Ecom.EcommerceApp.service.ProductService;

import tools.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	ProductService service;
	
	@GetMapping("/Products")
	@ResponseBody
	public List<Product> getProducts() {
		System.out.println("Fetching Products...");
		return service.getAllProducts();
	}
	
	@GetMapping("/Product/{id}")
	public Product getProduct(@PathVariable int id) {
		System.out.println("Finding Product....");
 		return service.getProduct(id);
	}
	
	
	//Product Without Image
	@PostMapping("/x")
	public Product addProducts(@RequestBody Product product) {
		System.out.println("Adding Product..."+ product);
		
		return service.addProduct(product);
	}
	
	// product with Image 
	@PostMapping(value= "/Products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Product addProductWithImage(@RequestPart("product") String ProductJson,@RequestPart("imagefile") MultipartFile imagefile)throws Exception {
		System.out.println(ProductJson);
		
		//form-data always Sends data as Binary 
		// While Using Multi-part we have to Manually do the Type Conversion from Binary to JSON. 
		// Spring lacks Converter Octet-Stream to JSON 
		
		ObjectMapper mapper = new ObjectMapper();
		Product product=mapper.readValue(ProductJson,Product.class);
		
		product.setImagefile(imagefile.getBytes());
		return service.addProduct(product);
	}
	
}
