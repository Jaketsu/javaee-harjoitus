package fi.ptuomaal.ping.service;

import fi.ptuomaal.ping.entity.Pong;
import fi.ptuomaal.ping.repository.PongRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

/*
* Luokka käynnistaa logien keruu utilityn, luo aloitusviestin microprofile-config.properties filen sisällöstä
* ja luo muistiin tyhjän "ping" kannan. Lisäksi luokassa on määritetty funktioita joita tullaan käyttämään ohjelmassa
* kun PongServiceä tarvitaan.
* */
@Stateless
public class PongService {

    private final static Logger logger = Logger.getLogger(PongService.class.getName());

    @Inject
    @ConfigProperty(name = "greeting.message")
    String message;

    @Inject
    @ConfigProperty(name = "greeting.excitement")
    String excitement;

    @Inject
    PongRepository repository;

    public List<Pong> getPongs(String name) {
        if (repository.findByName(name).isEmpty()){
            return repository.findAll();
        }
        return repository.findByPongName(name);
    }

    public String getPongsHTML (String name) {
        if (repository.findByName(name).isEmpty()){
            return convertPongListToHTML(repository.findAll());
        }
        return convertPongListToHTML(repository.findByPongName(name));
    }

    public String convertPongListToHTML(List<Pong> pongs){
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>\n");
        for (Pong p : pongs){
            sb.append("<li>")
                    .append(p.getName())
                    .append(" (id: ").append(p.getId()).append(")")
                    .append("</li>\n");
        }
        sb.append("</ul>");
        return sb.toString();
    }

    public void generateContent() {
        if ( repository.findAll().isEmpty() ) {
            logger.info("Kanta on tyhjä, luodaan sinne muutama tulos");
            repository.create("Ping");
            repository.create("Pong");
            repository.create("Pang");
        } else {
            logger.info("Kannassa on jo tavaraa, ei syytä luoda lisää");
        }
    }

    public String getResponseForTest() {
        return message + " " + excitement;
    }

    public boolean testDbOperations() {
        try {
            Pong tmp = repository.create("Test");
            Pong tmp2 = repository.update(tmp);
            return repository.delete(tmp2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public Pong getPong(Long id) {
        return repository.findById(id);
    }

    public Pong addPong(String name){
        return repository.create(name);
    }

    public Pong updatePong(Pong pong) {
        return repository.update(pong);
    }

    public boolean deletePong(Long id){
        return repository.delete(getPong(id));
    }
}
