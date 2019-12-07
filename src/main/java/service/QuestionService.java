package service;

import javax.inject.Inject;
import javax.transaction.Transactional;

import model.Question;
import repository.QuestionRepository;

public class QuestionService extends AbstractService<QuestionRepository,Question>{

	@Inject
	PersonService personServ;
	
	@Override
	@Transactional
	public void create(Question question) {
		
		if(question.getId()!=0) {
			
			throw new IllegalArgumentException("Id da questão deve ser deixada em branco ou a zero.");
		}
		else if(question.getPerson()!=null && !personServ.getAllIds().contains(question.getPerson().getId())) {
			
			throw new IllegalArgumentException("Id da pessoa que colocou a questão não é válido.");
		}
		
		repository.create(question);
			
	}

	@Override
	@Transactional
	public void update(int id, Question question) {
		
		if(question.getId()!=id || !repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id da questão não é válido.");
		}
		else if(question.getPerson()!=null && !personServ.getAllIds().contains(question.getPerson().getId())) {
			
			throw new IllegalArgumentException("Id da pessoa que colocou a questão não é válido.");
		}
		
		repository.update(question);
		
	}

	@Override
	@Transactional
	public void remove(int id) {
		
		if(!repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id introduzido não é válido.");
		}
		
		//Methods should be added here because if a Question exists in a Vote, the Question cannot be removed before clearing the//
		//the Question from the Vote, so this method is incomplet!!//
		
		repository.remove(id);
		
	}

}
