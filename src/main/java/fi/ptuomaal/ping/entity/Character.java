package fi.ptuomaal.ping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * DnD style character
 */
@Entity
@NamedQueries({
        @NamedQuery(
                name=Character.QUERY_ALL_CHARACTERS,
                query="SELECT c FROM Character c"
                ),
        @NamedQuery(
                name=Character.QUERY_CHARACTERS_BY_CLASS,
                query="SELECT c FROM Character c WHERE c.playerClass =: playerClass"
        ),
})
public class Character {
    public static final String QUERY_ALL_CHARACTERS="Q1";
    public static final String QUERY_CHARACTERS_BY_CLASS="Q2";

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private int level;

    @Column
    private String playerClass;

    public Character() {}

    public Character(int id, String name, int level, String playerClass){
        this.id = id;
        this.name = name;
        this.level = level;
        this.playerClass = playerClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }
}
