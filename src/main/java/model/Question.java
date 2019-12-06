package model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Question extends GenericEntity {
	
	private String question;
	@ManyToOne
	private Person person;
	
	public Question() {
		
	}
	
	public Question(String question,Person person) {
		
		super();
		this.question=question;
		this.person=person;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Question [question=" + question + ", person=" + person + "]";
	}	

}
