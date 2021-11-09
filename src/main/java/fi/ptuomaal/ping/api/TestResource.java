package fi.ptuomaal.ping.api;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fi.ptuomaal.ping.entity.Pong;
import fi.ptuomaal.ping.service.PongService;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/*
* Samoin kuin PongReasource, tässä luokassa määritellään API:n tietyn resurssin saatavilla olevat
* toiminnot ja määritellään. Tämä määrittää /test resurssin GET:in toiminnon, joka ajaa PongService luokan
* getResponseForTest() funktion.
* */
@Path("test")
public class TestResource {

    @Inject
    PongService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String test() {
        return service.getResponseForTest();
    }
}
