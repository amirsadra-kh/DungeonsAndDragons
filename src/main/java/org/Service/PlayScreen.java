package main.java.org.Service;

import main.java.org.model.*;
import main.java.org.model.Character;
import main.java.org.view.MapFrame;

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
    private ReadInput readInput = new ReadInput();

    /**
     *  A basic interaction screen after the user chooses Play
     */
    public void PlayScreen() throws Exception {
        ObjectLoader ol = new ObjectLoader();

        // Get the user to choose an existing character from a list to play
        System.out.println("Please enter the name of the character you would like to play from the list below: ");
        this.character = new Character();
        ol.showItemNames("src/main/java/org/resources/characters/");
        this.character = ol.loadCharacterFromXML(readInput.readLine());

        while(this.character == null) {
            System.out.println("This character does not exist! Please try again: ");
            this.character = ol.loadCharacterFromXML(readInput.readLine());
        }

        // Set the character chosen to player character
        character.setPlayerCharacter(true);

        // Get the user to choose an existing map from a list to play
        System.out.println("Please enter the name of the campaign you would like to play from the list below: ");
        this.campaign = new Campaign();
        ol.showItemNames("src/main/java/org/resources/campaigns/");
        this.campaign = campaign.getCampaign(readInput.readLine());

        while(this.campaign == null) {
            System.out.println("This campaign does not exist! Please try again: ");
            this.campaign = campaign.getCampaign(readInput.readLine());
        }

        // The number of levels played
        int levelsPlayed = 0;
        // Indicator if the user has finished a map or not
        boolean playing = true;

        while(levelsPlayed <= this.campaign.getNumLevels()) {
            if(playing == true) {
                // Get the current map being played according to the level
                // This currentMap has the player character and the level adjusted
                Map currentMap = this.campaign.nextLevel(levelsPlayed, this.character);

                // TODO load an existing map from campaign
                //currentMap = new MapFrame().makeFrame("Map");

                // TODO When player reaches Exit point, set playing = false
                playing = false;
            }

            if(playing == false) {
                // Get the next level to play
                levelsPlayed += 1;
                // The player character goes up a level since it finished a map
                this.character.setLevel(this.character.getLevel() + 1);
                playing = true;
            }
        }

        // TODO change this is done as the user clicks on a character on a map
        // TODO when campaign and map are working as they should
        // Testing the Character Observer
        Character observeChar;

        System.out.println("Please enter the name of the character you would like to observe from the list below: ");
        this.character = new Character();
        ol.showItemNames("src/main/java/org/resources/characters/");
        observeChar = ol.loadCharacterFromXML(readInput.readLine());

        while(this.character == null) {
            System.out.println("This character does not exist! Please try again: ");
            observeChar = ol.loadCharacterFromXML(readInput.readLine());
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
