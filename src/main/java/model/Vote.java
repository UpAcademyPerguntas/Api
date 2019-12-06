package model;

import javax.persistence.Entity;

@Entity
public class Vote extends GenericEntity {

	private Person person;
	private Question question;
	
	public Vote() {
		
	}
	
	public Vote(Person person, Question question) {
	
		super();
		this.person = person;
		this.question = question;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Vote [person=" + person + ", question=" + question + "]";
	}

}
