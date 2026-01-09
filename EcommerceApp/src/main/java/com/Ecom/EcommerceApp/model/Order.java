package com.Ecom.EcommerceApp.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "APP_ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String orderId;

    private String customerName;
    private String email;
    private String status;

    private LocalDate orderDate;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public long getId() {
        return id;
    }   // [web:2]

    public void setId(long id) {
        this.id = id;
    }   // [web:2]

    public String getOrderId() {
        return orderId;
    }   // [web:2]

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }   

    public String getCustomerName() {
        return customerName;
    }  
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }   // [web:2]

    public String getEmail() {
        return email;
    }  

    public void setEmail(String email) {
        this.email = email;
    }   

    public String getStatus() {
        return status;
    }   

    public void setStatus(String status) {
        this.status = status;
    }   

    public LocalDate getOrderDate() {
        return orderDate;
    }   

	public void setOrderDate(LocalDate orderDate) {
		// TODO Auto-generated method stub
		this.orderDate = orderDate;
	}   
	
	
	
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems=orderItems;
	}

	public List<OrderItem> getOrderItems() {
		// TODO Auto-generated method stub
		return this.orderItems;
	}
}

