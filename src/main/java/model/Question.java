package model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name=Question.GET_ALL_QUESTIONS_QUERY_NAME, query="SELECT q FROM Question q")
@NamedQuery(name=Question.GET_ALL_QUESTIONS_IDS_QUERY_NAME, query="SELECT q.id FROM Question q")
public class Question extends GenericEntity {
	
	public static final String GET_ALL_QUESTIONS_QUERY_NAME="Question.getAllQuestions";
	public static final String GET_ALL_QUESTIONS_IDS_QUERY_NAME="Person.getAllQuestionsIds";
	
	private String questionContent;
	@ManyToOne
	private Person person;
	
	public Question() {
		
	}
	
	public Question(String questionContent,Person person) {
		
		super();
		this.questionContent=questionContent;
		this.person=person;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Question [question content=" + questionContent + ", person=" + person + "]";
	}	

}
