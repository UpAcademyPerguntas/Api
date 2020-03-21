package controller;

import java.time.DateTimeException;
import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import model.Conference;
import model.Question;
import model.User;
import model.dto.ConferenceDto;
import model.dto.UserDto;
import repository.ConferenceRepository;
import service.ConferenceService;
import service.UserService;

@Path("conference")
public class ConferenceController {
	
	@Inject
	ConferenceService service;
	
	@Context
	private UriInfo context;
	
	@GET
	@Path("healthCheck")
	@Produces(MediaType.TEXT_PLAIN)
	public String healthCheck() {
		return "URI " + context.getRequestUri().toString() + " is OK!";
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(ConferenceDto conferenceDto) {
		
		Conference conference;
		try {
			
			conference=service.create(conferenceDto);
			
		}
		catch(DateTimeException dateTimeE) {
			
			return Response.status(Status.BAD_REQUEST).entity("Data e/ou hora introduzida(s) inv�lida(s).").build();
		}
		catch(Exception e) {
			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
		return Response.ok(conference,MediaType.APPLICATION_JSON).build();	
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, ConferenceDto conferenceDto) {
		
		Conference conference;
		try {
			
			conference=service.update(id,conferenceDto);
		}
		catch(DateTimeException dateTimeE) {
			
			return Response.status(Status.BAD_REQUEST).entity("Data e/ou hora introduzida(s) inv�lida(s).").build();
		}
		catch(Exception e) {
			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	
		return Response.ok(conference,MediaType.APPLICATION_JSON).build();
		
	}
	
	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Conference> getAll() {
		
		return service.getAll();
		
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		
		Conference conference;
		try {
	
			conference=service.get(id);		
		}
		catch (Exception e){
			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
		return Response.ok(conference,MediaType.APPLICATION_JSON).build();
		
	}
		
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id) {
		
		try{
			
			service.remove(id);
		}
		catch(Exception e) {
			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
			
		}
		
		return Response.ok("Entidade com id: "+id+" apagado com sucesso." ).build();
	}
	
	@GET
	@Path("/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getConferencesByUserId(@PathParam("id") int id) {
		
		Collection<Conference> conferencesList;
		try {
	
			conferencesList=service.getAllConferencesByUserId(id);		
		}
		catch (Exception e){
			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
		return Response.ok(conferencesList,MediaType.APPLICATION_JSON).build();
		
	}

}
