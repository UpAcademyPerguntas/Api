package repository;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import model.User;

@ApplicationScoped
public class UserRepository extends EntityRepository<User>{

	@Override
	protected Class<User> getEntityClass() {
		
		return User.class;
	}

	@Override
	protected String getAllQueryName() {
		
		return User.GET_ALL_USERS_QUERY_NAME;
	}

	@Override
	protected String getAllIdsQueryName() {
		
		return User.GET_ALL_USERS_IDS_QUERY_NAME;
	}
	
	public User getUserByUserName(String userName) {
		
		return entityManager.createNamedQuery(User.GET_USER_BY_USER_NAME_QUERY_NAME, User.class)
				.setParameter("userName", userName)
				.getSingleResult();
	}
	
	public Collection<String> getAllUsersNames(){
		
		return entityManager.createNamedQuery(User.GET_ALL_USERS_NAMES_QUERY_NAME, String.class).getResultList();
	}
	
	public Collection<String> getAllManagers(String manager){
		
		return entityManager.createNamedQuery(User.GET_ALL_MANAGERS_QUERY_NAME, String.class)
				.setParameter("manager", manager)
				.getResultList();
	}
}
