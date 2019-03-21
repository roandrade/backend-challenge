package com.invillia.acme.services;

import java.util.List;

public interface CrudService<T> {
	
	List<T> findAll();
	
	List<T> findByExample(T entity);
	
	T findOne(Long id);
	
	T save(T entity);
	
	T update(T entity);
	
	void delete(Long id);
	

}
