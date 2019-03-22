package com.invillia.acme.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.invillia.acme.models.Payment;
import com.invillia.acme.repositories.PaymentRepository;
import com.invillia.acme.services.PaymentService;
import com.invillia.models.OrderTO;
import com.invillia.models.PaymentTO;

@Service
public class PaymentServiceImpl extends CrudServiceImpl<Payment, PaymentTO> implements PaymentService {

	public PaymentServiceImpl() {
		super(Payment.class, PaymentTO.class);
	}

	@Autowired
	private PaymentRepository repository;
	
	@Override
	protected JpaRepository<Payment, Long> getRepository() {
		return repository;
	}

	@Override
	public PaymentTO findPaymentByOrder(OrderTO orderTO) {		
		return this.convertEntityToModel(repository.findByOrderId(orderTO.getId()));
	}

}
