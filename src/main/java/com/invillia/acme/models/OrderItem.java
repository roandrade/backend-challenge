package com.invillia.acme.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.invillia.acme.models.enums.Status;

import lombok.Data;

@Entity
@Table(name = "order_item")
@Data
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	@Column(name = "unit_price")
	private BigDecimal unitPrice;
	
	private Integer quantity;
	
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "id_order", referencedColumnName = "id")
	private Order order;

}
