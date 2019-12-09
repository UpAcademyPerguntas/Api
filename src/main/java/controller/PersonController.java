package controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import model.Person;
import repository.PersonRepository;
import service.PersonService;

@Path("person")
public class PersonController extends AbstractController<PersonService,PersonRepository,Person> {

	@GET
	@Path("/userName/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("userName") String userName, String password) {
		
		int id;
		try {
	
			id=service.get(userName,password);		
		}
		catch (Exception e){
			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
		return Response.ok(id,MediaType.APPLICATION_JSON).build();
		
	}
	
	
}
