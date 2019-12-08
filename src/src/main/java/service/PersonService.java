package service;

import javax.transaction.Transactional;

import model.Person;
import repository.PersonRepository;

public class PersonService extends AbstractService<PersonRepository,Person>{

	@Override
	@Transactional
	public void create(Person person) {
		
		if(person.getId()!=0) {
			throw new IllegalArgumentException("Id da pessoa deve ser deixada em branco ou a zero.");
		}
		
		repository.create(person);	
	}

	@Override
	@Transactional
	public void update(int id, Person person) {
		
		if(person.getId()!=id || !repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id da pessoa não é válido.");
		}
		
		repository.update(person);
		
	}

	@Override
	@Transactional
	public void remove(int id) {
		
		if(!repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id introduzido não é válido.");
		}
		
		//Methods should be added here because if a Person exists in a Question or in a Vote, the Person cannot be removed before clearing the//
		//Person from the Question and from the Vote, so this method is incomplet!!//
		
		repository.remove(id);
		
	}

}
