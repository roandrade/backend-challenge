package com.invillia.acme.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.invillia.acme.models.OrderItem;
import com.invillia.acme.repositories.OrderItemRepository;
import com.invillia.acme.services.OrderItemService;

@Service
public class OrderItemServiceImpl extends CrudServiceImpl<OrderItem> implements OrderItemService {
	
	@Autowired
	private OrderItemRepository repository;

	@Override
	protected JpaRepository<OrderItem, Long> getRepository() {
		return repository;
	}

}
