package model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name=Person.GET_ALL_PERSONS_QUERY_NAME, query="SELECT p FROM Person p")
@NamedQuery(name=Person.GET_ALL_PERSONS_IDS_QUERY_NAME, query="SELECT p.id FROM Person p")
@NamedQuery(name=Person.GET_ALL_PERSONS_NAMES_QUERY_NAME, query="SELECT p.userName FROM Person p")
@NamedQuery(name=Person.GET_PERSON_BY_USER_NAME_QUERY_NAME, query="SELECT p FROM Person p WHERE p.userName= :userName")
public class Person extends GenericEntity {
	
	public static final String GET_ALL_PERSONS_QUERY_NAME="Person.getAllPersons";
	public static final String GET_ALL_PERSONS_IDS_QUERY_NAME="Person.getAllPersonsIds";
	public static final String GET_ALL_PERSONS_NAMES_QUERY_NAME="Person.getAllPersonsNames";
	public static final String GET_PERSON_BY_USER_NAME_QUERY_NAME="Person.getPersonByUserName";
	
	private String userName;
	private String password;
	
	public Person() {
		
	}
	
	public Person(String userName, String password) {
		
		super();
		this.userName=userName;
		this.password=password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Pessoa [userName=" + userName + ", password=" + password + "]";
	}

}
