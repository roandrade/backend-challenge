package com.invillia.acme.services;

import java.time.LocalDate;
import java.util.List;

import com.invillia.acme.models.Order;
import com.invillia.models.OrderTO;

public interface OrderService extends CrudService<Order, OrderTO> {
	
	
	List<OrderTO> findOrdersByParameters(String neighborhood, String city,
			String state, LocalDate confirmationDate, String status,
			String itemDescription);
	
	void refundOrder(Long id);
	
	void refundOrderItem(Long id);

}
