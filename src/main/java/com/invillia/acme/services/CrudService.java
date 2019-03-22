package com.invillia.acme.services;

import java.util.List;

public interface CrudService<E, M> {
	
	List<M> findAll();
	
	List<M> findByExample(M model);
	
	M findOne(Long id);
	
	M save(M model);
	
	M update(M model);
	
	void delete(Long id);
	

}
