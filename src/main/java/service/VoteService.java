package service;

import java.util.Collection;
import java.util.Iterator;

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
	public int create(Vote vote) {
		
		if(vote.getId()!=0) {
			
			throw new IllegalArgumentException("Id do voto deve ser deixado em branco ou a zero.");
		}
		else if(vote.getPerson()==null) {
			
			throw new IllegalArgumentException("Uma pessoa deve sempre estar associada a um voto.");
		}
		else if(!personServ.getAllIds().contains(vote.getPerson().getId())) {
			
			throw new IllegalArgumentException("Id da pessoa associada ao voto não existe.");
		}
		else if(vote.getQuestion()==null) {
			
			throw new IllegalArgumentException("Uma questão deve sempre estar associada a um voto.");
		}
		else if(!questionServ.getAllIds().contains(vote.getQuestion().getId())) {
			
			throw new IllegalArgumentException("Id da questão associada ao voto não existe.");
		}
		
		Collection<Vote> votesCollection=repository.getAll();
		Iterator<Vote> votesIterator =votesCollection.iterator();
		
		while(votesIterator.hasNext()) {
			
			Vote tempVote=votesIterator.next();
			if(tempVote.getPerson().getId()==vote.getPerson().getId() && tempVote.getQuestion().getId()==vote.getQuestion().getId()) {
				
				throw new IllegalArgumentException("A mesma pessoa não pode votar na mesma questão mais do que uma vez.");
			}
			
		}
		
		return repository.create(vote).getId();
	}

	@Override
	@Transactional
	public void update(int id, Vote vote) {
		
		if(vote.getId()!=id || !repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id passado no Path difere do Id passado por parâmetro ou Id não existe.");
		}
		else if(vote.getPerson()==null) {
			
			throw new IllegalArgumentException("Uma pessoa deve sempre estar associada a um voto.");
		}
		else if(!personServ.getAllIds().contains(vote.getPerson().getId())) {
			
			throw new IllegalArgumentException("Id da pessoa associada ao voto não existe.");
		}
		else if(vote.getQuestion()==null) {
			
			throw new IllegalArgumentException("Uma questão deve sempre estar associada a um voto.");
		}
		else if(!questionServ.getAllIds().contains(vote.getQuestion().getId())) {
			
			throw new IllegalArgumentException("Id da questão associada ao voto não existe.");
		}
		
		repository.update(vote);
	}

	@Override
	@Transactional
	public void remove(int id) {
		
		if(!repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id introduzido não existe.");
		}
		
		repository.remove(id);
		
	}

}
