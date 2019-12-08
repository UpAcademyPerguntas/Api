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
	public int create(Question question) {
		
		if(question.getId()!=0) {
			
			throw new IllegalArgumentException("Id da questão deve ser deixada em branco ou a zero.");
		}
		else if(question.getPerson()==null) {
			
			throw new IllegalArgumentException("Uma pessoa deve sempre estar associada a uma questão.");
		}
		else if(!personServ.getAllIds().contains(question.getPerson().getId())) {
			
			throw new IllegalArgumentException("Id da pessoa que colocou a questão não existe.");
		}
		
		return repository.create(question).getId();
			
	}

	@Override
	@Transactional
	public void update(int id, Question question) {
		
		if(question.getId()!=id || !repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id passado no Path difere do Id passado por parâmetro ou Id não existe.");
		}
		else if(question.getPerson()==null) {
			
			throw new IllegalArgumentException("Uma pessoa deve sempre estar associada a uma questão.");
		}
		
		else if(!personServ.getAllIds().contains(question.getPerson().getId())) {
			
			throw new IllegalArgumentException("Id da pessoa que colocou a questão não existe.");
		}
		
		repository.update(question);
		
	}

	@Override
	@Transactional
	public void remove(int id) {
		
		if(!repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id introduzido não existe");
		}
		
		//Methods should be added here because if a Question exists in a Vote, the Question cannot be removed before clearing the//
		//the Question from the Vote, so this method is incomplet!!//
		
		repository.remove(id);
		
	}

}
