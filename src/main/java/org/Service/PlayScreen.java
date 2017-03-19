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

    private void playGame(Map map) {
        setPlayerAtEntryPoint(map);
        MapScreen.showMap(map);
        enterDirection();
        movePlayer(map);

    }

    private void movePlayer(Map map) {
        String direction =readLine();
        while(!MapDirectionValidator.validateDirectionIsValidBoundriesAndMovePlayer(map,direction,this.campaign)){
            direction =readLine();
            MapScreen.showMap(map);
        }
    }

    private void setPlayerAtEntryPoint(Map map) {
        for(int i=0;i<map.getScreen().length;i++){
            for(int j=0;j<map.getScreen()[i].length;j++){
                if(map.getScreen()[i][j].equalsIgnoreCase("E")){
                    map.getScreen()[i][j]="P";
                }
            }
        }
    }

    private void enterDirection(){
        System.out.println("Enter the direction you wish to move: \n Left:L\n Right:R \n Down:D\n Up:U\n");
    }

    private Map choseMapForPlayingGame() throws Exception {
        List<Map> maps = getMapsInTheCampaign();
        System.out.println("Please chose from the following maps by entering the desired map name:");
        MapScreen.showMaps(maps);
        String name = "";
        while (name == "") {
            name = readLine();
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
        this.campaign = campaign.getCampaign(readLine());

        while (this.campaign == null) {
            System.out.println("This campaign does not exist! Please try again: ");
            this.campaign = campaign.getCampaign(readLine());
        }
    }


    private void makeAHorizontalLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
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
        this.character = ol.loadCharacterFromXML(readInput.readLine());

        while (this.character == null) {
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

    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
