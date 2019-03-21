package com.invillia.acme.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.invillia.acme.models.Order;
import com.invillia.acme.repositories.OrderRepository;
import com.invillia.acme.services.OrderService;

@Service
public class OrderServiceImpl extends CrudServiceImpl<Order> implements OrderService{
	
	@Autowired
	private OrderRepository repository;

	@Override
	protected JpaRepository<Order, Long> getRepository() {
		return repository;
	}

}
