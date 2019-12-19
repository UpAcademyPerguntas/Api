package model;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name=Conference.GET_ALL_CONFERENCES_QUERY_NAME, query="SELECT c FROM Conference c")
@NamedQuery(name=Conference.GET_ALL_CONFERENCES_IDS_QUERY_NAME, query="SELECT c.id FROM Conference c")
@NamedQuery(name=Conference.GET_ALL_CONFERENCES_BY_USER_ID, query="SELECT distinct c FROM Conference c JOIN c.managersList m WHERE m.id= :id")
@NamedQuery(name=Conference.GET_ALL_CONFERENCES_IDS_BY_USER_ID,query="SELECT distinct c.id FROM Conference c JOIN c.managersList m WHERE m.id= :id")
public class Conference extends GenericEntity{
	
	private static final long serialVersionUID = 1L;

	public static final String GET_ALL_CONFERENCES_QUERY_NAME="Conference.getAllConferences";
	public static final String GET_ALL_CONFERENCES_IDS_QUERY_NAME="Conference.getAllConferencesIds";
	public static final String GET_ALL_CONFERENCES_BY_USER_ID="Conference.getAllConferencesByUserId";
	public static final String GET_ALL_CONFERENCES_IDS_BY_USER_ID ="Conference.getAllConferencesIdsByUserId";
	
	private String name;
	private String description;
	private String location;
	
	private LocalDate date;
	private LocalTime time; 

	@ManyToMany
	private Collection<User> managersList;
	
	public Conference() {
		
	}
	
	public Conference(String name, String description, String location) {
		super();
		this.name = name;
		this.description = description;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(int year, int month, int dayOfMonth) {
		
		this.date = LocalDate.of(year, month, dayOfMonth);
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(int hour,int min) {
		
		this.time = LocalTime.of(hour, min);
	}

	public Collection<User> getManagersList() {
		return managersList;
	}

	public void setManagersList(Collection<User> managersList) {
		this.managersList = managersList;
	}
	
	public void addManager(User manager) {
		
		this.managersList.add(manager);
	}
	
	public void removeManager(User manager) {
		
		this.managersList.remove(manager);
	}
	
	public void removeManager(int index) {
		
		this.managersList.remove(index);
	}

	@Override
	public String toString() {
		return "Conference [name=" + name + ", description=" + description + ", location=" + location + ", date=" + date
				+ ", time=" + time + "]";
	}
	
}
