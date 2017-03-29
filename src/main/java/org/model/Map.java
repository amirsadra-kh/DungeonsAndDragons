package main.java.org.model;

import main.java.org.Service.ObjectSaver;
import main.java.org.model.Character.BackPackInventory;
import main.java.org.model.Character.Character;

import javax.xml.bind.annotation.XmlElement;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A this class provides a MAP object
 * All the methods in this class are straight forward and therefore do not has JavaDoc comments
 *
 * @author Maysam/Mehran
 * @version 2.0
 * @since 2017-02-17
 */
public class Map implements Serializable {

    private String[][] screen;
    private Turn turn;
    private String name;
    private int cols;
    private int rows;
    private Character player;
    private List<Character> mapChars;
    private BackPackInventory chest;
    private boolean canGoNextLevel=false;
    private boolean isLevelCompleted;

    public boolean isLevelCompleted() {
        return isLevelCompleted;
    }

    public void setLevelCompleted(boolean levelCompleted) {
        isLevelCompleted = levelCompleted;
    }

    /**
     * An empty map constructor
     */
    public Map(){

    }

    /**
     * A map constructor
     *
     * @param screen the map screen
     */
    public Map( String[][] screen) {

        this.screen = screen;

    }

    /**
     * This method is to return the current coordinate of the player
     * @param map the current map
     * @return it returns the Point corresponding the player's coordinate
     */
     public static Point getPlayerCoordinate(Map map) {
        for (int i = 0; i < map.getScreen().length; i++) {
            for (int j = 0; j < map.getScreen()[i].length; j++) {
                if ("P".equalsIgnoreCase(map.getScreen()[i][j])) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    /**
     * A method to generate the map screen
     *
     * @param screen the map screen
     * @return show the map screen
     */
    public Map generateMap(String[][] screen){
        return new Map(screen);
    }

    /**
     * A method to get the number of columns of a map
     *
     * @return the number of columns of a map
     */
    public int getCols() {
        return cols;
    }

    /**
     * A method to set the number of columns of a map
     *
     * @param cols the number of columns to be set
     */
    public void setCols(int cols) {
        this.cols = cols;
    }

    /**
     * A method to get the number of rows of a map
     *
     * @return the number of rows of a map
     */
    public int getRows() {
        return rows;
    }

    /**
     * A method to set the number of rows of a map
     *
     * @param rows the number of rows to be set
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * A method to set the player character on the map
     * @return the player character
     */
    public Character getPlayer() { return this.player; }

    /**
     * A method to get the player character of the map
     * @param player chosen by user
     */
    public void setPlayer(Character player) { this.player = player; }

    /**
     * A method to get the name of a map
     *
     * @return the name of the map
     */
    public String getName() {
        return this.name;
    }

    /**
     * A method to set the name of a map
     *
     * @param name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A method to get the screen of a map
     *
     * @return screen of a map
     */
    public String[][] getScreen() {
        return screen;
    }

    /**
     * A method to set the screen of a map
     *
     * @param screen to be set
     */
    public void setScreen(String[][] screen) {
        this.screen = screen;
    }

    /**
     * A method to get the turn of a map
     *
     * @return the turn of the map
     */
    public Turn getTurn() {
        return this.turn;
    }

    /**
     * A method to set the turn of a map
     *
     * @param turn to be set
     */
    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    /**
     * A method to set the player character of a map
     *
     * @param character player
     */
    public void addPlayer(Character character) { this.player = character; }

    /**
     * A method for setting the non-player characters of a map
     *
     * @param character list of characters to add to the map
     */
    public void setNonPlayerCharacters(List<Character> character){
        this.mapChars = character;
    }

    /**
     * A method to get the non-player characters of a map
     *
     * @return a list of non-player characters on the map
     */
    public List<Character> getNonPLayerCharacters() {
        List<Character> characters=new ArrayList();
        Character temp = new Character();
        int monsterNum = 1;
        int friendlyNum = 1;
        for (int i = 0; i < this.getScreen().length; i++) {
            for (int j = 0; j < this.getScreen()[i].length; j++) {
                if ("".equals(this.getScreen()[i][j])) {
                    this.getScreen()[i][j] = " ";
                }
                if ('f' == this.getScreen()[i][j].charAt(0) || 'F' == this.getScreen()[i][j].charAt(0)) {
                    try {
                        //System.out.println(this.getScreen()[i][j].charAt(0)+" at position i="+i+",j="+j );
                        characters.add(temp.loadCharacter("fchar" +friendlyNum));
                        friendlyNum++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if('m' == this.getScreen()[i][j].charAt(0) || 'M' == this.getScreen()[i][j].charAt(0)){
                    try {
                        //System.out.println(this.getScreen()[i][j].charAt(0)+" at position i="+i+",j="+j );
                        characters.add(temp.loadCharacter("mon" + monsterNum));
                        monsterNum++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return characters;
    }

    /**
     * A method for adding characters to map using CharacterEditor
     * @param character to be added
     */
    public void setCharacter(Character character) {
        //TODO something?
    }

    /**
     * A method to set the chest of a map
     * @param chest a BackPackInventory object with Item objects.
     */
    public void setChest(BackPackInventory chest) {
        this.chest = chest;
    }

    //TODO if we are meant to have multiple chests, this method should return a definite chest object.
    @XmlElement
    public BackPackInventory getChest() {
        return this.chest;
    }

    /**
     * A method to save the map
     */
    public void saveObject() {
        ObjectSaver objectSaver = new ObjectSaver();
        objectSaver.saveMap("src/main/java/org/resources/maps/"+ this.name ,this);
    }

    /**
     * A method to set the map to a string
     *
     * @return a map string
     */
    @Override
    public String toString() {
        return "Map{" +
                "screen=" + Arrays.toString(screen) +
                ", turn=" + turn +
                '}';
    }

    public boolean isCanGoNextLevel() {
        return canGoNextLevel;
    }

    public void setCanGoNextLevel(boolean canGoNextLevel) {
        this.canGoNextLevel = canGoNextLevel;
    }

}