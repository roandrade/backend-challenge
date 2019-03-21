package com.invillia.acme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.acme.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
