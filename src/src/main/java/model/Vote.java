package model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name=Vote.GET_ALL_VOTES_QUERY_NAME, query="SELECT v FROM Vote v")
@NamedQuery(name=Vote.GET_ALL_VOTES_IDS_QUERY_NAME, query="SELECT v.id FROM Vote v")
public class Vote extends GenericEntity {

	public static final String GET_ALL_VOTES_QUERY_NAME="Vote.getAllVotes";
	public static final String GET_ALL_VOTES_IDS_QUERY_NAME="Vote.getAllVotesIds";
	
	@ManyToOne
	private Person person;
	@ManyToOne
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
