package fi.ptuomaal.ping.service;

import fi.ptuomaal.ping.entity.Character;
import fi.ptuomaal.ping.repository.CharacterRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless
public class CharacterService {

    @Inject
    CharacterRepository repository;

    public void generateCharacters(){
        if (repository.findAll().isEmpty()){
            repository.create("Vezkubio", 5, "Wizard");
            repository.create("Bakgrumin Boulderfoot", 2, "Paladin");
            repository.create("Wyn Kealar", 4, "Ranger");
            repository.create("Zabub", 4, "Fighter");
            repository.create("Newbie", 1, "Fighter");
        }
    }

    public List<Character> getAllCharacters(){
        return repository.findAll();
    }

    public List<Character> getCharactersByClass(String playerClass) {
        return repository.findByPlayerClass(playerClass);
    }
}
