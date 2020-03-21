package service;

import java.sql.Timestamp;
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
			//create and dont throw exception ?!
			throw new IllegalArgumentException("Id da conferência à qual pertence a questão não existe.");
		}
		
		Timestamp timeStamp=new Timestamp(System.currentTimeMillis());

		question.setCreatedAt(timeStamp.getTime());
		Question.lastUpdate=timeStamp.getTime();
		
		return repository.create(question);
			
	}

	@Override
	@Transactional
	public Question update(int id, Question question) {
		
		if(question.getId()!=id) {
			throw new IllegalArgumentException("Id passado no Path difere do Id passado por parâmetro ou Id não existe.");
		}
		else if(!repository.getAllIds().contains(id)) {
			throw new IllegalArgumentException("Id da questão não existe.");
		}
		else if(question.getConference()==null) {
			
			throw new IllegalArgumentException("Uma conferência deve sempre estar associada a uma questão.");
		}
		else if(!confServ.getAllIds().contains(question.getConference().getId())) {
			
			throw new IllegalArgumentException("Id da conferência à qual pertence a questão não existe.");	
		}
		
		if(question.isAnswered()) {

			Timestamp timeStamp=new Timestamp(System.currentTimeMillis());

			question.setAnsweredAt(timeStamp.getTime());
			Question.lastAnsweredUpdate=timeStamp.getTime();

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
		
		if(!confServ.getAllIds().contains(id)) {
			
			throw new IllegalArgumentException("Id da conferência não existe.");
		}
		return repository.getAllQuestionsIdsByConferenceId(id);
	}

	@Transactional
	public Collection<Question> getAllQuestionsByConferenceId(int id){
		
		if(!confServ.getAllIds().contains(id)) {
			
			throw new IllegalArgumentException("Id da conferência não existe.");
		}
		
		return repository.getAllQuestionsByConferenceId(id);
	}
	
	@Transactional
	public Collection<Long> getAllQuestionsTime(){
		
		return repository.getAllQuestionsTime();
	}

	@Transactional
	public Collection<Question> getAllNewQuestions(long time,int id){
		
		if(!confServ.getAllIds().contains(id)) {
			
			throw new IllegalArgumentException("Id da conferência não existe.");
		}
		
		return repository.getAllNewQuestions(time,id);
	}
	
	@Transactional
	public Collection<Integer> getAllNewAnsweredQuestions(long time,int id){
		
		if(!confServ.getAllIds().contains(id)) {
			
			throw new IllegalArgumentException("Id da conferência não existe.");
		}
		
		return repository.getAllNewAnsweredQuestions(time,id);
	}
}
