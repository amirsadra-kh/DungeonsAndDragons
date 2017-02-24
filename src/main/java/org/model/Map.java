package main.java.org.model;

import java.awt.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Map implements Serializable {

    private Point enterPoint;
    private Point exitPoint;
    private char[][] screen;
    private Character character;
    private List<Character> nonPlayerCharacters;
    private Map nextLevel;
    private List<Item> items;
    private String name;

    private Turn turn;

    public Map(){

    }

    public Map(Point enterPoint, Point exitPoint, char[][] screen, Character character, List<Character> nonPlayerCharacters, Map nextLevel) {
        this.enterPoint = enterPoint;
        this.exitPoint = exitPoint;
        this.screen = screen;
        this.character = character;
        this.nonPlayerCharacters = nonPlayerCharacters;
        this.nextLevel = nextLevel;
        this.turn =new Turn(nonPlayerCharacters,character);
    }


    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public Map generateMap(Point enterPoint, Point exitPoint, char[][] screen, Character character, List<Character> nonPlayerChacaters, Map nextLevel){
        return new Map(enterPoint,exitPoint,screen,character,nonPlayerChacaters,nextLevel);
    }

    public Point getEnterPoint() {
        return enterPoint;
    }

    public void setEnterPoint(Point enterPoint) {
        this.enterPoint = enterPoint;
    }

    public Point getExitPoint() {
        return exitPoint;
    }

    public void setExitPoint(Point exitPoint) {
        this.exitPoint = exitPoint;
    }

    public char[][] getScreen() {
        return screen;
    }

    public void setScreen(char[][] screen) {
        this.screen = screen;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) throws Exception {

        this.character = character;

    }

    public List<Character> getNonPlayerCharacters() {
        return nonPlayerCharacters;
    }

    public void setNonPlayerCharacters(List<Character> nonPlayerCharacters) {
        this.nonPlayerCharacters = nonPlayerCharacters;
    }

    public Map getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(Map nextLevel) {
        this.nextLevel = nextLevel;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Map{" +
                "enterPoint=" + enterPoint +
                ", exitPoint=" + exitPoint +
                ", screen=" + Arrays.toString(screen) +
                ", character=" + character +
                ", nonPlayerCharacters=" + nonPlayerCharacters +
                ", nextLevel=" + nextLevel +
                '}';
    }
}
