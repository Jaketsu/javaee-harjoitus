package fi.ptuomaal.ping.service;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class CharacterGenerator {

    @Inject
    CharacterService service;

    @PostConstruct
    public void init() {
        service.generateCharacters();
    }
}
