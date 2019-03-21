package com.invillia.acme.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.invillia.acme.models.enums.Status;

import lombok.Data;

@Entity
@Table(name = "tb_order")
@Data
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private Address address;
	
	@Column(name = "confirmation_date")
	private LocalDate confirmationDate;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(mappedBy = "order", fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<OrderItem> orderItems;
	
	@OneToOne(optional = true)
	private Payment payment;
	
}
