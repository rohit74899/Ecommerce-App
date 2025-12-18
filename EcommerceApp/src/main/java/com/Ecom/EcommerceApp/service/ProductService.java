package com.Ecom.EcommerceApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Ecom.EcommerceApp.model.Product;
import com.Ecom.EcommerceApp.repo.Repo;

@Service
public class ProductService {
	@Autowired
	Repo repo;

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return repo.save(product);
	}

	public Product getProduct(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(new Product());	
	}

	public byte[] getImage(int productId) {
		
		Product product =repo.findById(productId).orElse(new Product());	
		// TODO Auto-generated method stub
		return product.getImagefile();
	}

	
	public Product updatePrduct(Product product,int id) {
		// TODO Auto-generated method stub
		
		System.out.println("Service -->");
		Product Search=repo.findById(id).orElse(new Product());
		
		if(id==Search.getId()) {
			
			Search.setName(product.getName());
			Search.setBrand(product.getBrand());
			Search.setCategory(product.getCategory());
			Search.setDescription(product.getDescription());
			Search.setImagefile(product.getImagefile());
			Search.setPrice(product.getPrice());
			Search.setProductAvailable(product.getProductAvailable());
			Search.setStockQuantity(product.getStockQuantity());
		}
		return repo.save(Search);
	}

	public void DeleteProduct(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	
	
}
