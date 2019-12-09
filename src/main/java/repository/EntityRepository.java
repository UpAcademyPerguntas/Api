package repository;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.GenericEntity;

public abstract class EntityRepository <T extends GenericEntity> {
	
	//What does this persistenceContext means/does??//
	@PersistenceContext
	protected EntityManager entityManager;
	
	public T create(T entity) {	
		
		return entityManager.merge(entity);
	}
	
	//Why is this edited instead of creating a new one??//
	public T update(T editEntity) {
		
		return entityManager.merge(editEntity);		
		
	}
	
	public T get(Integer id) {
		
		return entityManager.find(getEntityClass(),id);	
	}
	
	public Collection<T> getAll(){
		
		return entityManager.createNamedQuery(getAllQueryName(), getEntityClass()).getResultList() ;
	}
	
	public Collection<Integer> getAllIds(){

		return entityManager.createNamedQuery(getAllIdsQueryName(), Integer.class).getResultList();
	}
	
	public void remove(Integer id) {
		
		T entity = entityManager.find(getEntityClass(), id);
		entityManager.remove(entity);
		
	}

	protected abstract Class<T> getEntityClass();
		
	protected abstract String getAllQueryName();

	protected abstract String getAllIdsQueryName();

}
