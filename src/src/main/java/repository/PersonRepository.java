package repository;

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

}
