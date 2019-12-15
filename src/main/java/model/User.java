package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name=User.GET_ALL_USERS_QUERY_NAME, query="SELECT u FROM User u")
@NamedQuery(name=User.GET_ALL_USERS_IDS_QUERY_NAME, query="SELECT u.id FROM User u")
@NamedQuery(name=User.GET_USER_BY_USER_NAME_QUERY_NAME, query="SELECT u FROM User u WHERE u.userName = :userName")
@NamedQuery(name=User.GET_ALL_USERS_NAMES_QUERY_NAME, query="SELECT u.userName FROM User u")
public class User extends GenericEntity {

	private static final long serialVersionUID = 1L;
	
	public static final String GET_ALL_USERS_QUERY_NAME="User.getAllUsers";
	public static final String GET_ALL_USERS_IDS_QUERY_NAME="User.getAllUsersIds";
	public static final String GET_USER_BY_USER_NAME_QUERY_NAME="User.getUserByUserName";
	public static final String GET_ALL_USERS_NAMES_QUERY_NAME="User.getAllUsersNames";			
	
	private String userName;
	private String hashcode;
	private String salt;
	
	private String role;
	
	public User() {
		
	}
	
	public User(String userName, String hashcode,String salt, String role) {
		super();
		this.userName = userName;
		this.hashcode=hashcode;
		this.salt=salt;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHashcode() {
		return hashcode;
	}

	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}
	
	public String getSalt() {
		
		return salt;
	}
	
	public void setSalt(String salt) {
		
		this.salt=salt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", id="+this.getId()+", hashcode=" + hashcode + ", salt=" + salt + ", role=" + role + "]";
	}

	
}
