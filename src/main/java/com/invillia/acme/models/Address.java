package com.invillia.acme.models;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Address {

	private String street;
	
	private Integer number;
	
	private String neighborhood;
	
	private String city;
	
	private String state;
	
}
