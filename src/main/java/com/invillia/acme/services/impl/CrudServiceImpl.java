package com.invillia.acme.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.acme.services.CrudService;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public abstract class CrudServiceImpl<E, M> implements CrudService<E, M> {
	
	protected abstract JpaRepository<E, Long> getRepository();
	
	private Class<E> entityClass;
	private Class<M> modelClass;
	
	public CrudServiceImpl(Class<E> entity, Class<M> model) {		
		this.entityClass = entity;
		this.modelClass = model;
	}

	@Override
	public List<M> findAll() {		
		return this.convertListEntityToListModel(this.getRepository().findAll());
	}
	
	@Override
	public List<M> findByExample(M model) {
		Example<E> example = Example.of(this.convertModelToEntity(model));
		return this.convertListEntityToListModel(this.getRepository().findAll(example));
	}
	
	@Override
	public M findOne(Long id) {
		return this.convertEntityToModel(this.getRepository().findById(id).orElse(null));
	}

	@Override
	public M save(M model) {
		return this.convertEntityToModel(this.getRepository().save(this.convertModelToEntity(model)));
	}

	@Override
	public M update(M model) {
		return this.convertEntityToModel(this.getRepository().save(this.convertModelToEntity(model)));
	}

	@Override
	public void delete(Long id) {
		E entity = getRepository().findById(id).get();
		this.getRepository().delete(entity);
	}	
	
	protected List<M> convertListEntityToListModel(List<E> list){
		return list.stream().map(entity -> this.convertEntityToModel(entity)).collect(Collectors.toList());
	}
	
	protected List<E> convertListModelToListEntity(List<M> list){
		return list.stream().map(model -> this.convertModelToEntity(model)).collect(Collectors.toList());
	}
	
	protected M convertEntityToModel(E entity) {
		if(entity != null) {
			MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
			mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDate.class));
			mapperFactory.classMap(entity.getClass(), modelClass);
			MapperFacade mapper = mapperFactory.getMapperFacade();		
			return mapper.map(entity, modelClass);
		} else {
			return null;
		}
	}
	
	protected E convertModelToEntity (M model) {
		if(model != null) {
			MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
			mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDate.class));
			mapperFactory.classMap(model.getClass(), entityClass);
			MapperFacade mapper = mapperFactory.getMapperFacade();		
			return mapper.map(model, entityClass);
		} else {
			return null;
		}
	}

}
