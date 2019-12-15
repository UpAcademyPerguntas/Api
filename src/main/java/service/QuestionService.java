package service;

import java.util.Collection;
import java.util.Iterator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import model.Question;
import repository.QuestionRepository;

@RequestScoped
public class QuestionService extends AbstractService<QuestionRepository,Question>{

	@Inject
	ConferenceService confServ;
	
	@Inject
	VoteService voteServ;
	
	@Override
	@Transactional
	public Question create(Question question) {
		
		if(question.getId()!=0) {
			
			throw new IllegalArgumentException("Id da questão deve ser deixada em branco ou a zero.");
		}
		else if(question.getConference()==null) {
			
			throw new IllegalArgumentException("Uma conferência deve sempre estar associada a uma questão.");
		}
		else if(!confServ.getAllIds().contains(question.getConference().getId())) {
			
			throw new IllegalArgumentException("Id da conferência à qual pertence a questão não existe.");
		}
		
		return repository.create(question);
			
	}

	@Override
	@Transactional
	public Question update(int id, Question question) {
		
		if(question.getId()!=id || !repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id passado no Path difere do Id passado por parâmetro ou Id não existe.");
		}
		else if(question.getConference()==null) {
			
			throw new IllegalArgumentException("Uma conferência deve sempre estar associada a uma questão.");
		}
		else if(!confServ.getAllIds().contains(question.getConference().getId())) {
			
			throw new IllegalArgumentException("Id da conferência à qual pertence a questão não existe.");
			
		}
		
		return repository.update(question);
		
	}

	@Override
	@Transactional
	public void remove(int id) {
		
		if(!repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id introduzido não existe");
		}
		
		Collection<Integer> votesList= voteServ.getAllVotesIdsByQuestionId(id); 
		Iterator<Integer> listIterator=votesList.iterator();
		
		while(listIterator.hasNext()) {
			
			voteServ.remove(listIterator.next());
		}
		
		repository.remove(id);
		
	}
	
	@Transactional
	public Collection<Integer> getAllQuestionsIdsByConferenceId(int id){
		
		
		return repository.getAllQuestionsIdsByConferenceId(id);
	}

	@Transactional
	public Collection<Question> getAllQuestionsByConferenceId(int id){
		
		if(!confServ.getAllIds().contains(id)) {
			
			throw new IllegalArgumentException("Id da conferência não existe.");
		}
		
		return repository.getAllQuestionsByConferenceId(id);
	}

}
