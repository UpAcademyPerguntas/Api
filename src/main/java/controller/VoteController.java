package controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import model.Vote;
import repository.VoteRepository;
import service.VoteService;

@Path("vote")
public class VoteController extends AbstractController <VoteService,VoteRepository,Vote> {

	@GET
	@Path("count/question/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		
		Long count;
		try {
	
			count=service.getVotesCountByQuestionId(id);		
		}
		catch (Exception e){
			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
		return Response.ok(count,MediaType.APPLICATION_JSON).build();
		
	}
	
}
