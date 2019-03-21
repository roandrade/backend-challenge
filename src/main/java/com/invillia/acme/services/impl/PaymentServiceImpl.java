package com.invillia.acme.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.invillia.acme.models.Payment;
import com.invillia.acme.repositories.PaymentRepository;
import com.invillia.acme.services.PaymentService;

@Service
public class PaymentServiceImpl extends CrudServiceImpl<Payment> implements PaymentService {

	@Autowired
	private PaymentRepository repository;
	
	@Override
	protected JpaRepository<Payment, Long> getRepository() {
		return repository;
	}

}
