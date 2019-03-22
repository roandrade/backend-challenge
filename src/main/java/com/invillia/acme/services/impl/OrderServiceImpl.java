package com.invillia.acme.services.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.invillia.acme.exceptions.BusinessException;
import com.invillia.acme.models.Order;
import com.invillia.acme.models.OrderItem;
import com.invillia.acme.models.enums.Status;
import com.invillia.acme.repositories.OrderItemRepository;
import com.invillia.acme.repositories.OrderRepository;
import com.invillia.acme.services.OrderService;
import com.invillia.acme.services.PaymentService;
import com.invillia.models.AddressTO;
import com.invillia.models.OrderItemTO;
import com.invillia.models.OrderTO;
import com.invillia.models.PaymentTO;

@Service
public class OrderServiceImpl extends CrudServiceImpl<Order, OrderTO> implements OrderService{
	
	public OrderServiceImpl() {
		super(Order.class, OrderTO.class);
	}

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private PaymentService paymentService;

	@Override
	protected JpaRepository<Order, Long> getRepository() {
		return repository;
	}

	@Override
	public List<OrderTO> findOrdersByParameters(String neighborhood, String city, String state,
			LocalDate confirmationDate, String status, String itemDescription) {
		OrderTO model = new OrderTO()
							.status(status)
							.confirmationDate(confirmationDate)
							.address(new AddressTO()
										.neighborhood(neighborhood)
										.city(city)
										.state(state)
										).addItemsItem(new OrderItemTO()
														.description(itemDescription));									
							
		return this.findByExample(model);
		
	}

	@Override
	public void refundOrder(Long id) {		
		OrderTO model = this.findOne(id);
		this.validateRefundConditions(model);
		this.save(model);
	}

	@Override
	public void refundOrderItem(Long id) {
		OrderItem item = orderItemRepository.findById(id).orElse(null);
		if(item != null) {
			OrderTO model = this.findOne(item.getId());
			this.validateRefundConditions(model);
			item.setStatus(Status.REFUNDED);
			orderItemRepository.save(item);
		}
		
	}

	private void validateRefundConditions(OrderTO model) {
		PaymentTO payment = paymentService.findPaymentByOrder(model);
		if(!(payment != null 
				&& Status.PAID.name().equals(payment.getStatus())
				&& Period.between(model.getConfirmationDate(), LocalDate.now()).getDays() > 10)) {
			throw new BusinessException("Can't refund the order/item");
		}

	}
}
