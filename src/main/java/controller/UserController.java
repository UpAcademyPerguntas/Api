package controller;

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

import model.User;
import model.dto.UserDto;
import repository.UserRepository;
import service.UserService;

@Path("user")
public class UserController  {

	@Inject
	protected UserService service;
	
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
	public Response create(UserDto userDto) {
		
		User user;
		try {
			
			user=service.create(userDto);
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
		return Response.ok(user,MediaType.APPLICATION_JSON).build();	
	}
	
	@POST
	@Path("/auth")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(UserDto userDto) {
		
		User user;
		try {
	
			service.checkIfUserValid(userDto,userDto.getPassword());
			user=service.getUserByUserName(userDto.getUserName());
		}
		catch (Exception e){
			
			e.printStackTrace();
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
		}
		
		return Response.ok(user,MediaType.APPLICATION_JSON).build();
		
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, UserDto userDto) {
		
		User user;
		try {
			
			user=service.update(id,userDto);
		}
		catch(Exception e) {
			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	
		return Response.ok(user,MediaType.APPLICATION_JSON).build();
	
		
		
	}
	
	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getAll() {
		
		return service.getAll();
		
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		
		User user;
		try {
	
			user=service.get(id);		
		}
		catch (Exception e){
			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
		return Response.ok(user,MediaType.APPLICATION_JSON).build();
		
	}
	
	@POST
	@Path("/userName")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response verifyIfUserNameExists(UserDto userDto) {
		
		
		boolean exists=service.verifyIfUserNameExists(userDto.getUserName());		
		
		return Response.ok(exists,MediaType.APPLICATION_JSON).build();
		
	}
	
	
	
	@GET
	@Path("/getAllManagers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<String> getAllManagers() {
		
		return service.getAllManagers();
		
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
	
}
