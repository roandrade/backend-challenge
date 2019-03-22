package com.invillia.acme.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.invillia.acme.models.Store;
import com.invillia.acme.repositories.StoreRepository;
import com.invillia.acme.services.StoreService;
import com.invillia.models.AddressTO;
import com.invillia.models.StoreTO;

@Service
public class StoreServiceImpl extends CrudServiceImpl<Store, StoreTO> implements StoreService {
	
	public StoreServiceImpl() {
		super(Store.class, StoreTO.class);		
	}

	@Autowired
	private StoreRepository repository;

	@Override
	protected JpaRepository<Store, Long> getRepository() {
		return repository;
	}

	@Override
	public List<StoreTO> findByParameters(String name, String neighborhood, String city, String state) {
		StoreTO example = new StoreTO()
							.name(name)
							.address(new AddressTO()
										.neighborhood(neighborhood)
										.city(city)
										.state(state));
			
		return this.findByExample(example);
	}

}
