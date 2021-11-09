package fi.ptuomaal.ping.api;

import fi.ptuomaal.ping.entity.Character;
import fi.ptuomaal.ping.service.CharacterService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("characters")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CharacterResource {

    @Inject
    CharacterService service;

    @GET
    public List<Character> getCharacters(){
        return service.getAllCharacters();
    }

    @GET
    @Path("/class/{playerClass}")
    public Response getCharactersByClass(@PathParam("playerClass") String playerClass){
        List<Character> classes = service.getCharactersByClass(playerClass);
        if (classes.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(classes).build();
    }
}
