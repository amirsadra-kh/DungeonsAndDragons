package main.java.org.Service;

import main.java.org.model.*;
import main.java.org.model.Character;
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
    private ReadInput readInput = new ReadInput();

    /**
     * A basic interaction screen after the user chooses Play
     */
    public void PlayScreen() throws Exception {

        choseCharacterForPlayingGame();
        choseCampaignForPlayingGame();
        Map map =choseMapForPlayingGame();
        List<Map> maps = getMapsInTheCampaign();
       // MapScreen.showMaps(maps);
        playGame(map);

    }

    /**
     * This method is the base of playing the game
     * @param map the current map that the game is playing in
     */
    private void playGame(Map map) {
        setPlayerAtEntryPoint(map);
        MapScreen.showMap(map);
        enterDirection();
        movePlayer(map);

    }

    /**
     * This method is to move the player in the Map
     * It reads the direction , validate the direction and show the map again
     * @param map
     */
    private void movePlayer(Map map) {
        String direction =readInput.readLine();
        while(!MapDirectionValidator.validateDirectionIsValidBoundriesAndMovePlayer(map,direction,this.campaign)){
            direction =readInput.readLine();
            MapScreen.showMap(map);
        }
    }

    /**
     *This method sets the player at the entry point of the map
     * it searches for E in the Map and set the payer position there
     * @param map
     */
    private void setPlayerAtEntryPoint(Map map) {
        for(int i=0;i<map.getScreen().length;i++){
            for(int j=0;j<map.getScreen()[i].length;j++){
                if(map.getScreen()[i][j].equalsIgnoreCase("E")){
                    map.getScreen()[i][j]="P";
                }
            }
        }
    }

    /**
     * This method is to print and ask user to enter the directions
     */
    private void enterDirection(){
        System.out.println("Enter the direction you wish to move: \n Left:L\n Right:R \n Down:D\n Up:U\n");
    }

    /**
     * This method is to chose a map from Campaign to play
     * @return it returns the selected map
     * @throws Exception
     */
    private Map choseMapForPlayingGame() throws Exception {
        List<Map> maps = getMapsInTheCampaign();
        System.out.println("Please chose from the following maps by entering the desired map name:");
        MapScreen.showMaps(maps);
        String name = "";
        while (name == "") {
            name = readInput.readLine();
            for (Map map : maps) {
                if (map.getName().equalsIgnoreCase(name)) {
                    return map;
                }
            }
            System.out.println("The selected map is not valid, try again by entering a map name");
        }
        return null;
    }

    /**
     * This method is to chose the campaign to play the game
     *
     * @throws Exception
     */
    private void choseCampaignForPlayingGame() throws Exception {
        // Get the user to choose an existing map from a list to play
        ObjectLoader ol = new ObjectLoader();
        System.out.println("Please enter the name of the campaign you would like to play from the list below: ");
        this.campaign = new Campaign();
        ol.showItemNames("src/main/java/org/resources/campaigns/");
        this.campaign = campaign.getCampaign(readInput.readLine());

        while (this.campaign == null) {
            System.out.println("This campaign does not exist! Please try again: ");
            this.campaign = campaign.getCampaign(readInput.readLine());
        }
    }

    /**
     * This method is to chose the character to play the game
     *
     * @throws Exception
     */
    private void choseCharacterForPlayingGame() throws Exception {
        ObjectLoader ol = new ObjectLoader();
        // Get the user to choose an existing character from a list to play
        System.out.println("Please enter the name of the character you would like to play from the list below: ");
        this.character = new Character();
        ol.showItemNames("src/main/java/org/resources/characters/");
        this.character = character.loadCharacter(readInput.readLine());

        while (this.character == null) {
            System.out.println("This character does not exist! Please try again: ");
            this.character = character.loadCharacter(readInput.readLine());
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
        observeChar = character.loadCharacter(readInput.readLine());

        while(this.character == null) {
            System.out.println("This character does not exist! Please try again: ");
            observeChar = character.loadCharacter(readInput.readLine());
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
     *
     * @return character chosen to play
     */
    public Character getCharacter() {
        return this.character;
    }

    /**
     * A method for getting the campaign chosen by user
     *
     * @return campaign chosen to play
     */
    public Campaign getCampaign() {
        return this.campaign;
    }

    /**
     * This method is to return the list of maps
     *
     * @return
     * @throws Exception
     */
    public List<Map> getMapsInTheCampaign() throws Exception {
        List<Map> maps = new ArrayList<>();
        for (String mapName : this.campaign.getMapNames()) {
            maps.add(new ObjectLoader().loadMapFromXML(mapName));
        }
        return maps;
    }

}
