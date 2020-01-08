package repository;


import java.sql.Timestamp;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import model.Question;

//What does this means ApplicationScoped??//
@ApplicationScoped
public class QuestionRepository extends EntityRepository<Question> {

	@Override
	protected Class<Question> getEntityClass() {
		
		return Question.class;
	}

	@Override
	protected String getAllQueryName() {
		
		return Question.GET_ALL_QUESTIONS_QUERY_NAME;
	}

	@Override
	protected String getAllIdsQueryName() {
		
		return Question.GET_ALL_QUESTIONS_IDS_QUERY_NAME;
	}
	
	public Collection<Integer> getAllQuestionsIdsByConferenceId(int id){
		
		return entityManager.createNamedQuery(Question.GET_ALL_QUESTIONS_IDS_BY_CONFERENCE_ID_QUERY_NAME,Integer.class)
				.setParameter("id", id)
				.getResultList();
	}

	public Collection<Question> getAllQuestionsByConferenceId(int id){
		
		return entityManager.createNamedQuery(Question.GET_ALL_QUESTIONS_BY_CONFERENCE_ID_QUERY_NAME,Question.class)
				.setParameter("id", id)
				.getResultList();
	}
	
	public Collection<Long> getAllQuestionsTime(){
		
		return entityManager.createNamedQuery(Question.GET_ALL_QUESTIONS_TIME_QUERY_NAME, Long.class).getResultList();
	}
	
	public Collection<Question> getAllNewQuestions(long time,int id){
		
		return entityManager.createNamedQuery(Question.GET_ALL_NEW_QUESTIONS_QUERY_NAME, Question.class)
				.setParameter("time", time)
				.setParameter("id", id)
				.getResultList();
	}
	
	public Collection<Integer> getAllNewAnsweredQuestions(long time, int id){
			
		return entityManager.createNamedQuery(Question.GET_ALL_NEW_ANSWERED_QUESTIONS_QUERY_NAME,Integer.class)
				.setParameter("time", time)
				.setParameter("id", id)
				.getResultList();
	}
}
