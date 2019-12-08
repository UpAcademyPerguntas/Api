package controller;

import javax.ws.rs.Path;

import model.Question;
import repository.QuestionRepository;
import service.QuestionService;

@Path("question")
public class QuestionController extends AbstractController<QuestionService,QuestionRepository,Question>{

}
