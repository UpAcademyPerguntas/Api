package controller;

import javax.ws.rs.Path;

import model.Person;
import repository.PersonRepository;
import service.PersonService;

@Path("person")
public class PersonController extends AbstractController<PersonService,PersonRepository,Person> {

}
