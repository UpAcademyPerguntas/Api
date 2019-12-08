package controller;

import javax.ws.rs.Path;

import model.Vote;
import repository.VoteRepository;
import service.VoteService;

@Path("vote")
public class VoteController extends AbstractController <VoteService,VoteRepository,Vote> {

}
