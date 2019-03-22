package com.invillia.acme.services;

import com.invillia.acme.models.Payment;
import com.invillia.models.OrderTO;
import com.invillia.models.PaymentTO;

public interface PaymentService extends CrudService<Payment, PaymentTO> {
	
	PaymentTO findPaymentByOrder(OrderTO orderTO);

}
