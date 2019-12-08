package service;

import javax.transaction.Transactional;

import model.Person;
import repository.PersonRepository;

public class PersonService extends AbstractService<PersonRepository,Person>{

	@Override
	@Transactional
	public int create(Person person) {
		
		if(person.getId()!=0) {
			throw new IllegalArgumentException("Id da pessoa deve ser deixada em branco ou a zero.");
		}
		else if(repository.getAllPersonsNames().contains(person.getUserName())) {
			
			throw new IllegalArgumentException("Nome de utilizador introduzido já existe.");
		}
		
		return repository.create(person).getId();	
	}

	@Override
	@Transactional
	public void update(int id, Person person) {
		
		if(person.getId()!=id || !repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id passado no Path difere do Id passado por parâmetro ou Id não existe.");
		}
		else if(repository.getAllPersonsNames().contains(person.getUserName())) {
			
			throw new IllegalArgumentException("Nome de utilizador introduzido já existe.");
		}
		
		repository.update(person);
		
	}

	@Override
	@Transactional
	public void remove(int id) {
		
		if(!repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id introduzido não existe.");
		}
		
		//Methods should be added here because if a Person exists in a Question or in a Vote, the Person cannot be removed before clearing the//
		//Person from the Question and from the Vote, so this method is incomplet!!//
		
		repository.remove(id);
		
	}
	
	@Transactional
	public int get(String userName,String password) {

		if(!repository.getAllPersonsNames().contains(userName)) {
			
			throw new IllegalArgumentException("Nome de utilizador introduzido não existe.");
		}
		
		Person person= repository.getPersonByUserName(userName);
		
		if(!person.getPassword().equals(password)) {
			
			throw new IllegalArgumentException("Password introduzida não é válida.");
		}
		
		return person.getId();

	}

}
