package service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import model.Conference;
import model.Question;
import model.User;
import model.dto.ConferenceDto;
import repository.ConferenceRepository;

@RequestScoped
public class ConferenceService {
	
	@Inject
	UserService userServ;
	
	@Inject
	QuestionService questionServ;
	
	@Inject
	ConferenceRepository repository;
	
	@Transactional
	public Conference create(ConferenceDto conferenceDto) {
		
		if(conferenceDto.getId()!=0) {
			
			throw new IllegalArgumentException("Id da conferência deve ser deixada em branco ou a zero.");
		}
		else if(conferenceDto.getManagersList().isEmpty()) {
			
			throw new IllegalArgumentException("Uma conferência deve possuir pelo menos um manager.");
		}
		
		
		Collection<User> managersList=conferenceDto.getManagersList();
		
		Iterator<User> listIterator = managersList.iterator();
		
		while(listIterator.hasNext()) {
			
			User tempManager=listIterator.next();
			
			if(!userServ.getAllIds().contains(tempManager.getId())) {
				
				throw new IllegalArgumentException("Id do manager associado à conferência não existe.");
			}
			
		}	
		
		Conference conference = convertDto(conferenceDto);
		
		return repository.create(conference);
			
	}


	@Transactional
	public Conference update(int id, ConferenceDto conferenceDto) {
			
		if(conferenceDto.getId()!=id || !repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id passado no Path difere do Id passado por parâmetro ou Id não existe.");
		}
		else if(conferenceDto.getManagersList().isEmpty()) {
			
			throw new IllegalArgumentException("Uma conferência deve possuir pelo menos um manager.");
		}
		
		Collection<User> managersList=conferenceDto.getManagersList();
		
		Iterator<User> listIterator = managersList.iterator();
		
		while(listIterator.hasNext()) {
			
			User tempManager=listIterator.next();
			
			if(!userServ.getAllIds().contains(tempManager.getId())) {
				
				throw new IllegalArgumentException("Id do manager associado à conferência não existe.");
			}
			
		}
		
		Conference conference = convertDto(conferenceDto);
		
		return repository.update(conference);
		
	}
	
	@Transactional
	public Conference get(int id) {
		
		if(!repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id introduzido não existe.");
		}
		
		return repository.get(id);
	}
	
	@Transactional
	public Collection<Conference> getAll(){
		
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
		
		Collection<Integer> questionsList= questionServ.getAllQuestionsIdsByConferenceId(id); 
		Iterator<Integer> listIterator=questionsList.iterator();
		
		while(listIterator.hasNext()) {
			
			questionServ.remove(listIterator.next());
		}
				
		repository.remove(id);
		
	}
	
	@Transactional
	public Conference convertDto(ConferenceDto conferenceDto) {
		
		Conference conference = new Conference();
		
		conference.setName(conferenceDto.getName());
		conference.setDescription(conferenceDto.getDescription());
		conference.setLocation(conferenceDto.getLocation());
		
		conference.setDate(conferenceDto.getYear(), conferenceDto.getMonth(),conferenceDto.getDay());
		conference.setTime(conferenceDto.getHour(), conferenceDto.getMin());
		
		conference.setManagersList(conferenceDto.getManagersList());
		
		return conference;
	}
	
	@Transactional
	public Collection<Conference> getAllConferencesByUserId(int id) {
		
		if(!userServ.getAllIds().contains(id)) {
			
			throw new IllegalArgumentException("Id do manager não existe.");
		}
		
		return repository.getAllConferencesByUserId(id);
	}
	
	@Transactional
	public Collection<Integer> getAllConferencesIdsByUserId(int id) {
		
		if(!userServ.getAllIds().contains(id)) {
			
			throw new IllegalArgumentException("Id do manager não existe.");
		}
		
		return repository.getAllConferencesIdsByUserId(id);
	}

}
