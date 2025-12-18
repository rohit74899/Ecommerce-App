package com.Ecom.EcommerceApp.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<List<Product>> getProducts() {
		System.out.println("Fetching Products...");
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllProducts());
	}
	
	@GetMapping("/Product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id) {
		System.out.println("Finding Product....");
		
		Product res= service.getProduct(id);
 		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@GetMapping("/Product/{ProductId}/image")
	public ResponseEntity<byte[]> getProductImage(@PathVariable int ProductId){
		
		return ResponseEntity.status(HttpStatus.OK).body(service.getImage(ProductId));
		
	}
	//Product Without Image
	@PostMapping("/x")
	public ResponseEntity<?> addProducts(@RequestBody Product product) {
		System.out.println("Adding Product..."+ product);
		
		Product res=service.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}
	
	// product with Image 
	@PostMapping(value= "/Products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addProductWithImage(@RequestPart("product") String ProductJson,@RequestPart("imagefile") MultipartFile imagefile) {
		System.out.println(ProductJson);
		
		//form-data always Sends data as Binary 
		// While Using Multi-part we have to Manually do the Type Conversion from Binary to JSON. 
		// Spring lacks Converter Octet-Stream to JSON 
		
		ObjectMapper mapper = new ObjectMapper();
		Product product=mapper.readValue(ProductJson,Product.class);
		
		try {
			product.setImagefile(imagefile.getBytes());
			Product Saved= service.addProduct(product);
			return ResponseEntity.status(HttpStatus.CREATED).body(Saved);
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Error",e.getMessage()));
			

		}
		
	}
	
	@PutMapping(value="/Product/{id}",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Product> updateProduct(@PathVariable int id,@RequestPart("product") String productJSON,@RequestPart("imagefile") MultipartFile imagefile) 
	{
		
		ObjectMapper mapper = new ObjectMapper();
		Product product=mapper.readValue(productJSON,Product.class);
		try {
			
			System.out.println("Controller -->");
			product.setImagefile(imagefile.getBytes());
			Product res=service.updatePrduct(product,id);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Product());
			
		}
		
	}
	
	@DeleteMapping("/Product/{id}")
	public ResponseEntity<?> DeleteProduct(@PathVariable int id) {
		
		service.DeleteProduct(id);
		return ResponseEntity.status(HttpStatus.OK).body("Product with Id"+id+"Deleted!!");
	}
	
}
