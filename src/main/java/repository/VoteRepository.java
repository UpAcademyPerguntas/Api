package repository;

import javax.enterprise.context.ApplicationScoped;

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

}
