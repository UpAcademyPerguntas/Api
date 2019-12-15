package model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name=Question.GET_ALL_QUESTIONS_QUERY_NAME, query="SELECT q FROM Question q")
@NamedQuery(name=Question.GET_ALL_QUESTIONS_IDS_QUERY_NAME, query="SELECT q.id FROM Question q")
@NamedQuery(name=Question.GET_ALL_QUESTIONS_IDS_BY_CONFERENCE_ID_QUERY_NAME,query="SELECT q.id FROM Question q WHERE q.conference.id= :id")
@NamedQuery(name=Question.GET_ALL_QUESTIONS_BY_CONFERENCE_ID_QUERY_NAME,query="SELECT q FROM Question q WHERE q.conference.id= :id")
public class Question extends GenericEntity {
	

	private static final long serialVersionUID = 1L;
	
	public static final String GET_ALL_QUESTIONS_QUERY_NAME="Question.getAllQuestions";
	public static final String GET_ALL_QUESTIONS_IDS_QUERY_NAME="Question.getAllQuestionsIds";
	public static final String GET_ALL_QUESTIONS_IDS_BY_CONFERENCE_ID_QUERY_NAME="Question.getAllQuestionsIdsByConferenceId";
	public static final String GET_ALL_QUESTIONS_BY_CONFERENCE_ID_QUERY_NAME="Question.getAllQuestionsByConferenceId";
	
	
	private String questionContent;
	@ManyToOne
	private Conference conference;
	
	public Question() {
		
	}
	
	public Question(String questionContent,Conference conference) {
		
		super();
		this.questionContent=questionContent;
		this.conference=conference;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference=conference;
	}

	@Override
	public String toString() {
		return "Question [question content=" + questionContent + ", conference=" + conference + "]";
	}	

}
