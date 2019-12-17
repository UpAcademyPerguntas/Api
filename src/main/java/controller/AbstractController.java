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

import model.GenericEntity;
import repository.EntityRepository;
import service.AbstractService;

//If this class (AbstractController) is not made abstract, the @Inject in the variable T service doesn't work (error occurs), WHY??
public abstract class AbstractController <T extends AbstractService<R,E>, R extends EntityRepository<E>, E extends GenericEntity> {

	@Inject
	protected T service;
	
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
	public Response create(E entity) {
		
		int id;
		try {
			
			id=service.create(entity);
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
		return Response.ok(id,MediaType.APPLICATION_JSON).build();	
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, E entity) {
		
		try {
			
			service.update(id,entity);
		}
		catch(Exception e) {
			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	
		return Response.ok("Entidade com id: "+id+" atualizada com sucesso.").build();
	
		
		
	}
	
	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<E> getAll() {
		
		return service.getAll();
		
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		
		E entity;
		try {
	
			entity=service.get(id);		
		}
		catch (Exception e){
			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
		return Response.ok(entity,MediaType.APPLICATION_JSON).build();
		
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
