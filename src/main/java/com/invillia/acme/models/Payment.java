package com.invillia.acme.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.invillia.acme.models.enums.Status;
import com.invillia.acme.models.Order;

import lombok.Data;

@Entity
@Table(name = "tb_payment")
@Data
public class Payment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(name = "creditcard_number")
	private String creditCardNumber;
	
	@Column(name = "payment_date")
	private LocalDate paymentDate;

	@OneToOne(optional = false)
	@JoinColumn(name = "id_order", referencedColumnName = "id")
	private Order order;

}
