package com.invillia.acme.services;

import java.util.List;

import com.invillia.acme.models.Store;
import com.invillia.models.StoreTO;

public interface StoreService extends CrudService<Store, StoreTO>{
	
	List<StoreTO> findByParameters(String name, String neighborhood, String city, String state);

}
