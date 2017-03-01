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
    private int cols;
    private int rows;

    public Map(){

    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
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
        //objectSaver.saveMap("src/main/java/org/resources/maps/"+ this.name ,this);
    }


    void setNonPlayerCharacters(List<Character> character){
        //TODO nothing
    }

    void setCharacter(Character cahacter){
        //TODO nothing
    }
}
