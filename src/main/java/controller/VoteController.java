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
import model.Vote;
import repository.VoteRepository;
import service.VoteService;

@Path("vote")
public class VoteController extends AbstractController <VoteService,VoteRepository,Vote> {

	@GET
	@Path("count/question/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVotesCountByQuestionId(@PathParam("id") int id) {
		
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
	
	@GET
	@Path("/time")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllVotesTime() {
		
		Collection<Long> timeList;
		try {
	
			timeList=service.getAllVotesTime();		
		}
		catch (Exception e){
			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
		return Response.ok(timeList,MediaType.APPLICATION_JSON).build();
		
	}
	
	@GET
	@Path("/question/{id}/time/{time}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllNewVotes(@PathParam("time") Long time,@PathParam("id") int id) {
		
		Collection<Vote> votesList=null;
		try {
			
			if(time<Vote.lastUpdate) {
				
				votesList=service.getAllNewVotes(time,id);
			}
		}
		catch (Exception e){
			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
		return Response.ok(votesList,MediaType.APPLICATION_JSON).build();
		
	}

	
}
