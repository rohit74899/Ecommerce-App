package com.Ecom.EcommerceApp.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecom.EcommerceApp.model.Order;
import com.Ecom.EcommerceApp.model.OrderItem;
import com.Ecom.EcommerceApp.model.Product;
import com.Ecom.EcommerceApp.model.dto.OrderItemRequest;
import com.Ecom.EcommerceApp.model.dto.OrderItemResponse;
import com.Ecom.EcommerceApp.model.dto.OrderRequest;
import com.Ecom.EcommerceApp.model.dto.OrderResponse;
import com.Ecom.EcommerceApp.repo.OrderRepo;
import com.Ecom.EcommerceApp.repo.Repo;

@Service
public class OrderService {

	@Autowired
	Repo productRepo;
	@Autowired
	OrderRepo orderRepo;
	

	public List<OrderResponse> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}


	public OrderResponse PlaceOrder(OrderRequest orderRequest) {

		// Java.util.UUID 
		// to Generate a Unique ID 
		// UUID.randomUUID().toString().substring(0,8)
		Order order=new Order();
		String orderId="ORD"+UUID.randomUUID().toString().substring(0,8);
		order.setOrderId(orderId);
		order.setCustomerName(orderRequest.customerName());
		order.setEmail(orderRequest.email());
		order.setStatus("PLACED");
		order.setOrderDate(LocalDate.now());
		
		List<OrderItem> Orderitems= new ArrayList<>();
		
		for(OrderItemRequest itemReq: orderRequest.items()) {
			
			// we Have to update the inventory After the Request Found and FullFilled 
			Product product = productRepo.findById(itemReq.productId())
					.orElseThrow(() ->new RuntimeException("Product Not Found!!"));	
			
			product.setStockQuantity(product.getStockQuantity() - itemReq.quantity());
			productRepo.save(product);
			
			
			// We Should have Register/ Update System With the the Requested Order Item
		
			OrderItem orderItem = new OrderItem();
			
			orderItem.setProduct(product);
			orderItem.setQuantity(itemReq.quantity());
			orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(itemReq.quantity())));
			orderItem.setOrder(order);
			
			
			// collect OrderItems to ArrayList
			Orderitems.add(orderItem);
	
		}
		
		order.setOrderItems(Orderitems);
		
		Order saveorders=orderRepo.save(order);
		
		List<OrderItemResponse> itemResponse = new ArrayList<>();
		
		for(OrderItem item : order.getOrderItems()) {
			OrderItemResponse orderItemResponse=new OrderItemResponse(
								item.getProduct().getName(),
								item.getQuantity(),
								item.getPrice());
			
			itemResponse.add(orderItemResponse);
		}
		
		OrderResponse response= new OrderResponse(
				saveorders.getOrderId(),
				saveorders.getCustomerName(),
				saveorders.getEmail(),
				saveorders.getStatus(),
				saveorders.getOrderDate(),
				itemResponse
	    );
		
		return response;
	}

}
