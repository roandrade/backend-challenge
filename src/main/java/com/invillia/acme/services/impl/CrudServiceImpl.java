package com.invillia.acme.services.impl;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.acme.services.CrudService;

public abstract class CrudServiceImpl<T> implements CrudService<T> {
	
	protected abstract JpaRepository<T, Long> getRepository();

	@Override
	public List<T> findAll() {		
		return this.getRepository().findAll();
	}
	
	@Override
	public List<T> findByExample(T entity) {
		Example<T> example = Example.of(entity);
		return this.getRepository().findAll(example);
	}
	
	@Override
	public T findOne(Long id) {
		return this.getRepository().findById(id).orElse(null);
	}

	@Override
	public T save(T entity) {
		return this.getRepository().save(entity);
	}

	@Override
	public T update(T entity) {
		return this.update(entity);
	}

	@Override
	public void delete(Long id) {
		T entity = this.findOne(id);
		this.getRepository().delete(entity);
	}
	
	

}
