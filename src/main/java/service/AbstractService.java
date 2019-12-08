package service;

import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.Transactional;

import model.GenericEntity;
import repository.EntityRepository;

public abstract class AbstractService <T extends EntityRepository<E>, E extends GenericEntity>{
	
	@Inject
	protected T repository;
	
	public abstract int create(E entity);
	
	public abstract void update(int id, E entity);

	@Transactional
	public E get(int id) {
		
		if(!repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id introduzido não existe.");
		}
		
		return repository.get(id);
	}
	
	@Transactional
	public Collection<E> getAll(){
		
		return repository.getAll();
	}

	@Transactional
	public Collection<Integer> getAllIds(){
		
		return repository.getAllIds();
	}
	
	public abstract void remove(int id);


}
