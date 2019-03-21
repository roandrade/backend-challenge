package com.invillia.acme.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.invillia.acme.models.Store;
import com.invillia.acme.repositories.StoreRepository;
import com.invillia.acme.services.StoreService;

@Service
public class StoreServiceImpl extends CrudServiceImpl<Store> implements StoreService {
	
	@Autowired
	private StoreRepository repository;

	@Override
	protected JpaRepository<Store, Long> getRepository() {
		return repository;
	}

}
