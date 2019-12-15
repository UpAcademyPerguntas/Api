package model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name=Vote.GET_ALL_VOTES_QUERY_NAME, query="SELECT v FROM Vote v")
@NamedQuery(name=Vote.GET_ALL_VOTES_IDS_QUERY_NAME, query="SELECT v.id FROM Vote v")
@NamedQuery(name=Vote.GET_ALL_VOTES_IDS_BY_QUESTION_ID_QUERY_NAME, query="SELECT v.id FROM Vote v WHERE v.question.id= :id")
@NamedQuery(name=Vote.GET_VOTES_COUNT_BY_QUESTION_ID_QUERY_NAME, query="SELECT COUNT(v.id) FROM Vote v WHERE v.question.id= :id")
public class Vote extends GenericEntity {

	private static final long serialVersionUID = 1L;

	public static final String GET_ALL_VOTES_QUERY_NAME="Vote.getAllVotes";
	public static final String GET_ALL_VOTES_IDS_QUERY_NAME="Vote.getAllVotesIds";
	public static final String GET_ALL_VOTES_IDS_BY_QUESTION_ID_QUERY_NAME="Vote.getAllVotesIdsByQuestionId";
	public static final String GET_VOTES_COUNT_BY_QUESTION_ID_QUERY_NAME="Vote.getVotesCountByQuestionId";
	
	@ManyToOne
	private Question question;
	
	public Vote() {
		
	}
	
	public Vote(Question question) {
	
		super();
		this.question = question;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Vote [question=" + question + "]";
	}

}
