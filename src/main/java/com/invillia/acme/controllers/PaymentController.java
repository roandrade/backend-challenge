package com.invillia.acme.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.services.PaymentService;
import com.invillia.controllers.PaymentsApi;
import com.invillia.models.PaymentTO;

@RestController
@RequestMapping("/api")
public class PaymentController implements PaymentsApi{

	@Autowired
	private PaymentService service;
	
	@Override
	public ResponseEntity<Void> savePayment(@Valid PaymentTO payment) {
		service.save(payment);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
}
