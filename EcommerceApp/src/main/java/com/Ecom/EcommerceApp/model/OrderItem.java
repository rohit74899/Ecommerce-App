package com.Ecom.EcommerceApp.model;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Product product;
    private int quantity;
    private BigDecimal price;
    @ManyToOne
    private Order order;

    public int getId() {
        return id;
    }   // [web:2]

    public void setId(int id) {
        this.id = id;
    }   // [web:2]

    public Product getProduct() {
        return product;
    }   // [web:2]

    public void setProduct(Product product) {
        this.product = product;
    }   // [web:2]

    public int getQuantity() {
        return quantity;
    }   // [web:2]

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }   // [web:2]

    public BigDecimal getPrice() {
        return price;
    }   // [web:2]

    public void setPrice(BigDecimal price) {
        this.price = price;
    }   // [web:2]

    public Order getOrder() {
        return order;
    }   // [web:2]

    public void setOrder(Order order) {
        this.order = order;
    }   // [web:2]
}

