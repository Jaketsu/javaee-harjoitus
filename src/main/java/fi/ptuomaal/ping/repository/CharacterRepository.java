package fi.ptuomaal.ping.repository;

import fi.ptuomaal.ping.entity.Character;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Project javaee-harjoitus was created by Jaakko on 6.11.2021.
 */
public class CharacterRepository {
    @PersistenceContext(unitName="characters")
    EntityManager entityManager;

    public List<Character> findAll() {
        List<Character> results = entityManager.createNamedQuery(Character.QUERY_ALL_CHARACTERS).getResultList();
        return results;
    }

    public  List<Character> findByPlayerClass(String playerClass){
        List<Character> results = entityManager.createNamedQuery(Character.QUERY_CHARACTERS_BY_CLASS).setParameter("playerClass", playerClass).getResultList();
        return results;
    }

    public Character findById(Long id) {
        return (Character) entityManager.createNativeQuery("select * from pong where id=" + id, Character.class).getSingleResult();
    }

    public Character create(String name, int level, String playerClass){
        Character c = new Character();
        c.setName(name);
        c.setLevel(level);
        c.setPlayerClass(playerClass);
        entityManager.persist(c);
        return c;
    }
}
