package repository;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import model.Conference;

@ApplicationScoped
public class ConferenceRepository extends EntityRepository<Conference>{

	@Override
	protected Class<Conference> getEntityClass() {
		
		return Conference.class;
	}

	@Override
	protected String getAllQueryName() {
		
		return Conference.GET_ALL_CONFERENCES_QUERY_NAME;
	}

	@Override
	protected String getAllIdsQueryName() {
		
		return Conference.GET_ALL_CONFERENCES_IDS_QUERY_NAME;
	}

	public Collection<Conference> getAllConferencesByUserId(int id){
		
		return entityManager.createNamedQuery(Conference.GET_ALL_CONFERENCES_BY_USER_ID,Conference.class)
				.setParameter("id", id)
				.getResultList();
	}
	
	public Collection<Integer> getAllConferencesIdsByUserId(int id){
		
		return entityManager.createNamedQuery(Conference.GET_ALL_CONFERENCES_IDS_BY_USER_ID,Integer.class)
				.setParameter("id", id)
				.getResultList();
	}
}
