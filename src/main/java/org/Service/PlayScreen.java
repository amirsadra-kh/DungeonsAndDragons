package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.Character;
import main.java.org.model.Inventory;
import main.java.org.model.Map;
import main.java.org.view.MapFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A class to interact with the user while playing the game
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-10
 */
public class PlayScreen {
    private Character character;
    private Campaign campaign;
    // The level each map represents
    private int level = 1;
    private ArrayList<Map> levels = new ArrayList<>();

    /**
     * A method to read input to prevent copied code
     *
     * @return a String input from user
     */
    private String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     *  A basic interaction screen after the user chooses Play
     */
    public void PlayScreen() throws Exception {
        ObjectLoader ol = new ObjectLoader();

        // Get the user to choose an existing character from a list to play
        System.out.println("Please enter the name of the character you would like to play from the list below: ");
        this.character = new Character();
        ol.showItemNames("src/main/java/org/resources/characters/");
        this.character = ol.loadCharacterFromXML(readLine());

        while(this.character == null) {
            System.out.println("This character does not exist! Please try again: ");
            this.character = ol.loadCharacterFromXML(readLine());
        }

        // Get the user to choose an existing map from a list to play
        System.out.println("Please enter the name of the campaign you would like to play from the list below: ");
        this.campaign = new Campaign();
        ol.showItemNames("src/main/java/org/resources/campaigns/");
        this.campaign = campaign.getCampaign(readLine());

        while(this.campaign == null) {
            System.out.println("This campaign does not exist! Please try again: ");
            this.campaign = campaign.getCampaign(readLine());
        }

        // Load the maps in the campaign to a Map List
        List<String> mapNames = campaign.getMapNames();
        for(String map : mapNames) {
            levels.add(campaign.getMap(map));
        }

        // Get the current map being played according to the level
        Map currentMap = levels.get(level - 1);

        /* TODO Add character player to the map
         * currentMap.addPlayer(this.character);
         */

        /* TODO Modify the level of the characters on the map according to the player
         * ArrayList<Character> mapChars = currentMap.getCharacters()
         * for each mapChar in mapChars
         *      mapChar.setLevel(character.getLevel());
         * currentMap.setCharacters(mapChars);
         */

        /* TODO Set the enhance of the items on a map according to the level
         * Set<Items> mapItems = currenMap.getItems()
         * for each item in mapItems
         *      item.setItemOnMapEnhancement(this.level);
         * currenMap.setItems(mapItems);
         */

        // TODO load an existing map from campaign
        //currentMap = new MapFrame().makeFrame("Map");

        // TODO change this is done as the user clicks on a character on a map
        // TODO when campaign and map are working as they should
        // Testing the Character Observer
        Character observeChar;

        System.out.println("Please enter the name of the character you would like to observe from the list below: ");
        this.character = new Character();
        ol.showItemNames("src/main/java/org/resources/characters/");
        observeChar = ol.loadCharacterFromXML(readLine());

        while(this.character == null) {
            System.out.println("This character does not exist! Please try again: ");
            observeChar = ol.loadCharacterFromXML(readLine());
        }

        // This gets the character's abilities - Observer
        new CharacterObserver(observeChar);
        observeChar.setState(observeChar.getAbility());

        // This gets the character's inventory - Observer
        Inventory observeInventory = new Inventory();
        observeInventory.setItems(observeChar);
        new InventoryObserver(observeInventory);
        observeInventory.setState(observeInventory.getItems());
    }

    /**
     * A method to get the character chosen by user
     * @return character chosen to play
     */
    public Character getCharacter() {
        return this.character;
    }

    /**
     * A method for getting the campaign chosen by user
     * @return campaign chosen to play
     */
    public Campaign getCampaign() {
        return this.campaign;
    }

    //TODO Add a load map from campaign
}
