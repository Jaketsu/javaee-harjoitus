package fi.ptuomaal.ping.api;

import fi.ptuomaal.ping.entity.Pong;
import fi.ptuomaal.ping.service.PongService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
* Tässä luokassa määritellään rest API:n /ping resourcen pathit ja saatavilla olevat HTTP komennot.
* Alussa määritelty oli vain kaksi GET komentoa, jotka molemmat palauttivat PongService luokan olioita
* */

@Path("ping")
public class PongResource {

    @Inject
    PongService service;

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_HTML)
    public Response ping(@PathParam("name") String name) {
        // return Response.status(Response.Status.OK).entity(service.getPongs(name)).build();
        return Response.status(Response.Status.OK).entity(service.getPongsHTML(name)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Pong addPong(Pong pong){
        return service.addPong(pong.getName());
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pong getPong(@PathParam("id") Long id) {
        return service.getPong(id);
    }

    @PUT
    @Path("/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Pong updatePong(@PathParam("id") int id, Pong pong){
        pong.setId(id);
        return service.updatePong(pong);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/id/{id}")
    public void deletePong(@PathParam("id") Long id){
        service.deletePong(id);
    }

}
