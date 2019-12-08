package model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name=Person.GET_ALL_PERSONS_QUERY_NAME, query="SELECT p FROM Person p")
@NamedQuery(name=Person.GET_ALL_PERSONS_IDS_QUERY_NAME, query="SELECT p.id FROM Person p")
public class Person extends GenericEntity {
	
	public static final String GET_ALL_PERSONS_QUERY_NAME="Person.getAllPersons";
	public static final String GET_ALL_PERSONS_IDS_QUERY_NAME="Person.getAllPersonsIds";
	
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
