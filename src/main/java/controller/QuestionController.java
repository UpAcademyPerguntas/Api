package controller;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import model.Question;
import repository.QuestionRepository;
import service.QuestionService;

@Path("question")
public class QuestionController extends AbstractController<QuestionService,QuestionRepository,Question>{

	
	@GET
	@Path("/conference/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		
		Collection<Question> questionsList;
		try {
	
			questionsList=service.getAllQuestionsByConferenceId(id);		
		}
		catch (Exception e){
			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
		return Response.ok(questionsList,MediaType.APPLICATION_JSON).build();
		
	}
	
}
