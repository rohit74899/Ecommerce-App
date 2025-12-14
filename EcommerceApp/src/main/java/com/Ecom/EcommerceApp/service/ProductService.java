package com.Ecom.EcommerceApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
}
