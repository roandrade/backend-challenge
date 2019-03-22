package com.invillia.acme.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.services.StoreService;
import com.invillia.controllers.StoresApi;
import com.invillia.models.StoreTO;

@RestController
@RequestMapping("/api")
public class StoresController implements StoresApi{
	
	@Autowired
	private StoreService service;
	
	@Override
	@GetMapping("/stores")
	public ResponseEntity<List<StoreTO>> findStores(@Valid String name, @Valid String neighborhood, @Valid String city,
			@Size(min = 2, max = 2) @Valid String state) {
		
		return ResponseEntity.ok(service.findByParameters(name, neighborhood, city, state));
	}
	
	@Override
	public ResponseEntity<Void> saveStore(@Valid StoreTO store) {		
		service.save(store);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@Override
	public ResponseEntity<Void> updateStore(Long id, @Valid StoreTO store) {
		store.setId(id);
		service.update(store);
		return ResponseEntity.ok().build();
	}

}
