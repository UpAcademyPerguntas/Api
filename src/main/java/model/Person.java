package model;

import javax.persistence.Entity;

@Entity
public class Person extends GenericEntity {
	
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
