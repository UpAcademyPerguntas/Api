package repository;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import model.Question;
import model.Vote;

//What does this means ApplicationScoped??//
@ApplicationScoped
public class VoteRepository extends EntityRepository<Vote> {

	@Override
	protected Class<Vote> getEntityClass() {
		
		return Vote.class;
	}

	@Override
	protected String getAllQueryName() {

		return Vote.GET_ALL_VOTES_QUERY_NAME;
	}

	@Override
	protected String getAllIdsQueryName() {
		
		return Vote.GET_ALL_VOTES_IDS_QUERY_NAME;
	}

	public Collection<Integer> getAllVotesIdsByQuestionId(int id){
		
		return entityManager.createNamedQuery(Vote.GET_ALL_VOTES_IDS_BY_QUESTION_ID_QUERY_NAME,Integer.class)
				.setParameter("id", id)
				.getResultList();
	}
	
	public Long getVotesCountByQuestionId(int id) {
		
		return entityManager.createNamedQuery(Vote.GET_VOTES_COUNT_BY_QUESTION_ID_QUERY_NAME,Long.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
