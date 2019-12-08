package repository;

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

}
