package main.java.org.model;

import main.java.org.Service.ObjectSaver;

import java.io.Serializable;
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
        return this.mapChars;
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
}
