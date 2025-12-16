package com.Ecom.EcommerceApp.model;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private String brand;
	private BigDecimal price;
	private String category;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date releaseDate;
	private boolean productAvailable;
	private int stockQuantity;
	
	@Lob
	private byte[] imagefile;
	
	Product(int id,String name, String description,String brand,BigDecimal price,String category,Date releaseDate,boolean productAvailable,int stockQuantity,byte[] imagefile){
		this.id=id;
		this.name=name;
		this.description=description;
		this.brand=brand;
		this.price=price;
		this.category=category;
		this.releaseDate=releaseDate;
		this.productAvailable=productAvailable;
		this.stockQuantity=stockQuantity;
		this.imagefile=imagefile;
	}
	
	public Product(){
		
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id=id;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	
	public String getBrand() {
		return this.brand;
	}
	public void setBrand(String brand) {
		this.brand=brand;
	}
	
	public BigDecimal getPrice() {
		return this.price;
	}
	public void setPrice(BigDecimal price) {
		this.price=price;
	}
	
	public String getCategory() {
		return this.category;
	}
	public void setCategory(String category) {
		this.category=category;
	}
	public Date getReleaseDate() {
		return this.releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate=releaseDate;
	}
	
	
	public Boolean getProductAvailable() {
		return this.productAvailable;
	}
	public void setProductAvailable(Boolean productAvailable) {
		this.productAvailable=productAvailable;
	}
	
	public int getStockQuantity() {
		return this.stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity=stockQuantity;
	}
	
	public byte[] getImagefile() {
		return this.imagefile;
	}
	public void setImagefile(byte[] imagefile) {
		this.imagefile=imagefile;
	}
	@Override
	public String toString() {
		return "["+this.id+" "+this.name+" "+this.brand+" "+this.price+" "+this.category+" "+this.price+"]";
	}

}

