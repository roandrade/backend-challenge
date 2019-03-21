package com.invillia.acme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.acme.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
