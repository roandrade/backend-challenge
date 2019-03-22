package com.invillia.acme.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.services.OrderService;
import com.invillia.controllers.OrdersApi;
import com.invillia.models.OrderTO;

@RestController
@RequestMapping("/api")
public class OrderController implements OrdersApi {
	
	@Autowired
	private OrderService service;

	@Override
	@GetMapping("/orders")
	public ResponseEntity<List<OrderTO>> findOrders(@Valid String neighborhood, @Valid String city,
			@Size(min = 2, max = 2) @Valid String state, @Valid LocalDate confirmationDate, @Valid String status,
			@Valid String itemDescription) {
		
		return ResponseEntity.ok(service.findOrdersByParameters(neighborhood, city, state, confirmationDate, status, itemDescription));
	}
	
	@Override
	public ResponseEntity<Void> saveOrder(@Valid OrderTO order) {
		service.save(order);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@Override
	@PutMapping("/orders/items/refunds/{id}")
	public ResponseEntity<Void> doOrderItemRefund(@PathVariable("id") Long idOrderItem) {
		service.refundOrderItem(idOrderItem);
		return ResponseEntity.ok().build();
	}
	
	@Override
	@PutMapping("/orders/refunds/{id}")
	public ResponseEntity<Void> doOrderRefund(@PathVariable("id") Long idOrder) {
		service.refundOrder(idOrder);
		return ResponseEntity.ok().build();
	}
	
}
