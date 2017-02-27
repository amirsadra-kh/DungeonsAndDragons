package main.java.org.model;

import main.java.org.Service.ObjectSaver;

import java.awt.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Map implements Serializable {

    private String[][] screen;
    private Turn turn;
    private String name;

    public Map(){

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Map{" +
                "screen=" + Arrays.toString(screen) +
                ", turn=" + turn +
                '}';
    }

    public String[][] getScreen() {
        return screen;
    }

    public void setScreen(String[][] screen) {
        this.screen = screen;
    }

    public Map( String[][] screen) {

        this.screen = screen;

    }


    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public Map generateMap(String[][] screen){
        return new Map(screen);
    }


    public void saveObject() {
        ObjectSaver objectSaver = new ObjectSaver();
        objectSaver.saveMap("src/main/java/org/resources/maps/"+ UUID.randomUUID().toString().substring(5),this);
        //objectSaver.saveMap("mo", this);
        //TODO save Ma
    }


    void setNonPlayerCharacters(List<Character> character){
        //TODO nothing
    }

    void setCharacter(Character cahacter){
        //TODO nothing
    }
}
