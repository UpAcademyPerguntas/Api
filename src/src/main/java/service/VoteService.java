package service;

import javax.inject.Inject;
import javax.transaction.Transactional;

import model.Vote;
import repository.VoteRepository;

public class VoteService extends AbstractService<VoteRepository,Vote>{
	
	@Inject
	PersonService personServ;
	@Inject
	QuestionService questionServ;
	
	@Override
	@Transactional
	public void create(Vote vote) {
		
		if(vote.getId()!=0) {
			
			throw new IllegalArgumentException("Id do voto deve ser deixado em branco ou a zero.");
		}
		else if(vote.getPerson()!=null && !personServ.getAllIds().contains(vote.getPerson().getId())) {
			
			throw new IllegalArgumentException("Id da pessoa associada ao voto não é válida.");
		}
		else if(vote.getQuestion()!=null && !questionServ.getAllIds().contains(vote.getQuestion().getId())) {
			
			throw new IllegalArgumentException("Id da questão associada ao voto não é válida.");
		}
		
		repository.create(vote);
	}

	@Override
	@Transactional
	public void update(int id, Vote vote) {
		
		if(vote.getId()!=id || !repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id do voto não é válido.");
		}
		else if(vote.getPerson()!=null && !personServ.getAllIds().contains(vote.getPerson().getId())) {
			
			throw new IllegalArgumentException("Id da pessoa associada ao voto não é válida.");
		}
		else if(vote.getQuestion()!=null && !questionServ.getAllIds().contains(vote.getQuestion().getId())) {
			
			throw new IllegalArgumentException("Id da questão associada ao voto não é válida.");
		}
		
		repository.update(vote);
	}

	@Override
	@Transactional
	public void remove(int id) {
		
		if(!repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id introduzido não é válido.");
		}
		
		repository.remove(id);
		
	}

}
