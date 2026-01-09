package com.Ecom.EcommerceApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecom.EcommerceApp.model.dto.OrderRequest;
import com.Ecom.EcommerceApp.model.dto.OrderResponse;
import com.Ecom.EcommerceApp.service.OrderService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrderController {
	
	@Autowired
	private OrderService orderservice;
	
	
	@PostMapping("/orders/place")
	public ResponseEntity<OrderResponse> PlaceOrder(@RequestBody OrderRequest orderRequest) {
		
		OrderResponse orderResponse=orderservice.PlaceOrder(orderRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<OrderResponse>> GetAllOrders(){
		
		List<OrderResponse> orders = orderservice.getAllOrders();
		
		return ResponseEntity.status(HttpStatus.OK).body(orders);
	}
	
}
