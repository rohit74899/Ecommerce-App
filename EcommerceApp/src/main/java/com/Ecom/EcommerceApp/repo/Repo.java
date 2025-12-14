package com.Ecom.EcommerceApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecom.EcommerceApp.model.Product;

@Repository
public interface Repo extends JpaRepository<Product,Integer>{

}
