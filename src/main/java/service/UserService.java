package service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;

import Utils.PasswordUtils;
import model.Conference;
import model.User;
import model.dto.UserDto;
import repository.ConferenceRepository;
import repository.UserRepository;

@RequestScoped
public class UserService {
	
	@Inject
	ConferenceService confServ;
	
	@Inject
	UserRepository repository;
	
	
	@Transactional
	public User create(UserDto userDto) {
		
		if(userDto.getId()!=0) {
			
			throw new IllegalArgumentException("Id do manager deve ser deixada em branco ou a zero.");
		}
		else if(!userDto.getRole().equalsIgnoreCase("admin") && !userDto.getRole().equalsIgnoreCase("manager")) {
			
			throw new IllegalArgumentException("Apenas as funções de admin e manager são válidas.");
		}
		if(repository.getAllUsersNames().contains(userDto.getUserName())) {
    		
    		throw new IllegalArgumentException("Username introduzido já existe.");
    	}
    	
		User user=convertDto(userDto);
		
		return repository.create(user);
			
	}

	@Transactional
	public User update(int id, UserDto userDto) {
	
		if(userDto.getId()!=id	|| !repository.getAllIds().contains(id)){
			
			throw new IllegalArgumentException("Id passado no Path difere do Id passado por parâmetro ou Id não existe.");
		}
		else if(!userDto.getRole().equalsIgnoreCase("admin") && !userDto.getRole().equalsIgnoreCase("manager")) {
			
			throw new IllegalArgumentException("Apenas as funções de admin e manager são válidas.");
		}
		if(repository.getAllUsersNames().contains(userDto.getUserName())) {
    		
    		throw new IllegalArgumentException("Username introduzido já existe.");
    	}
    	
		
		User user=convertDto(userDto);
		
		return repository.update(user);
	}
	
	@Transactional
	public User get(int id) {
		
		if(!repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id introduzido não existe.");
		}
		
		return repository.get(id);
	}
	
	@Transactional
	public Collection<User> getAll(){
		
		return repository.getAll();
	}

	@Transactional
	public Collection<Integer> getAllIds(){
		
		return repository.getAllIds();
	}

	@Transactional
	public void remove(int id) {
		
		if(!repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id introduzido não existe");
		}
		
		Collection<Integer> conferencesIdsList= confServ.getAllConferencesIdsByUserId(id); 
		Iterator<Integer> listIterator=conferencesIdsList.iterator();
		
		while(listIterator.hasNext()) {
			
			confServ.remove(listIterator.next());
		}
		
		repository.remove(id);

		
	}
	
	@Transactional
	public User convertDto(UserDto userDto) {
	
		String username=userDto.getUserName();
        
        User user=new User();

        //password->(hash, salt)
        String password = userDto.getPassword();
        String role=userDto.getRole();

        String[] hashCode=passwordToHashcode(password);
        user.setId(userDto.getId());
        //set fields to Entity
        user.setUserName(username); 
        user.setHashcode(hashCode[0]);
        user.setSalt(hashCode[1]);
        user.setRole(role);
        
		return user; 
	}
	
	public String[] passwordToHashcode(String password) {
        String salt = PasswordUtils.generateSalt(50).get();
        String key = PasswordUtils.hashPassword(password, salt).get();
        String[] result= {key, salt};
        return result;
        
    }public void checkIfUserValid(UserDto userDTO, String password) throws Exception {            
        //User valid if both username and password are valid
        checkIfPasswordValid(userDTO, password);
    }

    public void checkIfPasswordValid(UserDto userDto, String password) throws Exception {
        
    	if(!repository.getAllUsersNames().contains(userDto.getUserName())) {
    		
    		throw new IllegalArgumentException("Username introduzido não existe.");
    	}
    	
    	User myUser=repository.getUserByUserName(userDto.getUserName());
        
        String key=myUser.getHashcode();
        String salt=myUser.getSalt();

        if(!PasswordUtils.verifyPassword(password, key, salt))
            throw new BadRequestException("Password inválida.");
    }

    @Transactional
    public User getUserByUserName(String userName) {
    	
    	return repository.getUserByUserName(userName);
    }
    
    @Transactional
    public Collection<String> getAllManagers(){
    	
    	String manager="manager";
    	return repository.getAllManagers(manager);
    }
    
    @Transactional
    public boolean verifyIfUserNameExists(String userName){
    	
    	if(repository.getAllUsersNames().contains(userName)) {
    		
    		return true;
    	}
    	else {
    		
    		return false;
    	}
    	
    }
}
