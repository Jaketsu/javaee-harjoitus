package fi.ptuomaal.ping.service;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/*
* Luokka joka sisältää toiminnon jonka avulla voidaan generoida PongServicen kantaan pong olioita.
* Tämä on määritelty singletoniksi, joten Generaattori luokasta voi olla vain yksi instanssi ja se luodaan
* applikaation käynnistyessä
* */
@Startup
@Singleton
public class PongGenerator {

    @Inject
    PongService service;

    @PostConstruct
    public void init() {
        service.generateContent();
    }
}
