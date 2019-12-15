package service;

import java.util.Collection;
import java.util.Iterator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import model.Vote;
import repository.VoteRepository;

@RequestScoped
public class VoteService extends AbstractService<VoteRepository,Vote>{
	
	@Inject
	QuestionService questionServ;
	
	@Override
	@Transactional
	public Vote create(Vote vote) {
		
		if(vote.getId()!=0) {
			
			throw new IllegalArgumentException("Id do voto deve ser deixado em branco ou a zero.");
		}
		else if(vote.getQuestion()==null) {
			
			throw new IllegalArgumentException("Uma questão deve sempre estar associada a um voto.");
		}
		else if(!questionServ.getAllIds().contains(vote.getQuestion().getId())) {
			
			throw new IllegalArgumentException("Id da questão associada ao voto não existe.");
		}
		
		return repository.create(vote);
	}

	@Override
	@Transactional
	public Vote update(int id, Vote vote) {
		
		//Must be implemented because it's an abstract method in the Superclass, but for Vote there will not be the possibility for update, which means the user//
		//will only be able to create or delete the vote.
		return null;
	}

	@Override
	@Transactional
	public void remove(int id) {
		
		if(!repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id introduzido não existe.");
		}
		
		repository.remove(id);
		
	}
	
	@Transactional
	public Collection<Integer> getAllVotesIdsByQuestionId(int id){
		
		return repository.getAllVotesIdsByQuestionId(id);
	}

	@Transactional
	public Long getVotesCountByQuestionId(int id) {
		
		if(!questionServ.getAllIds().contains(id)) {
			
			throw new IllegalArgumentException("Id da questão não existe.");
		}
		return repository.getVotesCountByQuestionId(id);
	}
}
