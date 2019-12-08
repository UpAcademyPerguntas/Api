package repository;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import model.Person;

//What does this means ApplicationScoped??//
@ApplicationScoped
public class PersonRepository extends EntityRepository<Person> {

	@Override
	protected Class<Person> getEntityClass() {
		
		return Person.class;
	}

	@Override
	protected String getAllQueryName() {
		
		return Person.GET_ALL_PERSONS_QUERY_NAME;
	}

	@Override
	protected String getAllIdsQueryName() {
		
		return Person.GET_ALL_PERSONS_IDS_QUERY_NAME;
	}

	public Collection<String> getAllPersonsNames() {
		
		return  entityManager.createNamedQuery(Person.GET_ALL_PERSONS_NAMES_QUERY_NAME, String.class).getResultList();
	}
	
	public Person getPersonByUserName(String userName) {
		
		return entityManager.createNamedQuery(Person.GET_PERSON_BY_USER_NAME_QUERY_NAME, Person.class)
				.setParameter("userName", userName)
				.getSingleResult();
	}
}
