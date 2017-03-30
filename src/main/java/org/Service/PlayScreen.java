package main.java.org.Service;


import main.java.org.model.*;
import main.java.org.model.CharacterPackage.Ability;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.CharacterPackage.Inventory;
import main.java.org.model.Map;


import java.util.*;


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
    // The first level to be played, should be increased after a map has been finished.
    private int level = 0;
    private CharacterObserver characterObserver;
    private InventoryObserver inventoryObserver;
    private List<Item> inventory = new ArrayList<>();
    private Ability ability;

    /**
     * A basic interaction screen after the user chooses Play
     */
    public void PlayScreen() throws Exception {
        choseCharacterForPlayingGame();
        choseCampaignForPlayingGame();
        if(getMapsInTheCampaign().size() > 0) {
            Map currentMap = getMapsInTheCampaign().get(level);
            currentMap.setPlayer(this.character);
            playGame(currentMap);
        }
    }

    /**
     * This method is the base of playing the game
     * @param map the current map that the game is playing in
     */
    private void playGame(Map map) {
        setPlayerAtEntryPoint(map);
        MapScreen.showMap(map);
        // Allow the user to observe a character.
        String choice = "";
        System.out.println("Would you like to observe a character from the map? Y/N");
        choice = readInput.readStringHandling(choice);
        while(!"Y".equals(choice) && !"N".equals(choice) && !"y".equals(choice) && !"n".equals(choice)) {
            System.out.println("Invalid input! Please try again: ");
            choice = readInput.readStringHandling(choice);
        }
        if("Y".equals(choice) || "y".equals(choice)) {
            userObserverChoice(map);
        }
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
        MapDirectionValidator mapValidator= new MapDirectionValidator(this.campaign,map);
        while(!mapValidator.validateDirectionIsValidBoundriesAndMovePlayer(direction)){
            direction =readInput.readLine();
            MapScreen.showMap(map);
        }
    }

    /**
     *This method sets the player at the entry point of the map
     * it searches for E in the Map and set the payer position there
     * @param map
     */
    public static void setPlayerAtEntryPoint(Map map) {
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
        System.out.println(GameConstantsInterface.ENTER_DIRECTION);
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
        this.campaign.setMaps(this.campaign.getMapsFromCampaign(this.character));
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

        while (this.character.equals(null)) {
            System.out.println("This character does not exist! Please try again: ");
            this.character = character.loadCharacter(readInput.readLine());
        }

        // Set the character chosen to player character
        character.setPlayerCharacter(true);
    }

    /**
     * A method to give the user the choice to observe a character
     * The inventory, Ability or both of that character
     */
    public void userObserverChoice(Map currentMap) {
        // Get the character the user wants to observe
        Character observeChar = getObserverCharacter(currentMap);

        // Ask user if they want to observe the ability of the character
        askUserAbilityObserver(observeChar);

        // Ask user if they want to observe the inventory of the character
        askUserInventoryObserver(observeChar);

        MapScreen.setObservers(this.characterObserver, this.inventoryObserver);
    }

    /**
     * Get the character the user want to observe
     * @param currentMap being played
     * @return observeChar the character the user wants to observe
     */
    public Character getObserverCharacter(Map currentMap) {
        ObjectLoader ol = new ObjectLoader();
        // Testing the CharacterPackage Observer
        Character observeChar = new Character();
        // Get the characters that are in the map
        List<Character> mapCharacters = currentMap.getNonPLayerCharacters();
        // Add the player character to the list as well
        mapCharacters.add(character);
        int choice = 0;

        System.out.println("Please enter the name of the character you would like to observe from the list below: ");
        // Print the name of each character that is on the map
        int i = 1;
        for(Character character : mapCharacters) {
            System.out.println(i + ". " + character.getCharName());
            i++;
        }
        choice = readInput.readIntHandling(choice);
        // load the character chosen by user

        while(mapCharacters.get(choice-1).getCharName().equals(null)) {
            System.out.println("This character does not exist! Please try again: ");
            observeChar = character.loadCharacter(readInput.readLine());
        }
        return mapCharacters.get(choice-1);
    }

    /**
     * A method to interact with user about the ability observer
     * @param observeChar the character being observed.
     */
    public void askUserAbilityObserver(Character observeChar) {
        String choice = "";
        // This gets the character's abilities - Observer
        System.out.println("Would you like to observe the ability of " +observeChar.getCharName() +"? Y/N");
        choice = readInput.readStringHandling(choice);
        while(!"Y".equals(choice) && !"N".equals(choice) && !"y".equals(choice) && !"n".equals(choice)) {
            System.out.println("Invalid input! Please try again: ");
            choice = readInput.readStringHandling(choice);
        }

        if("Y".equals(choice) || "y".equals(choice)) {
            this.characterObserver = new CharacterObserver(observeChar);
            observeChar.setState(observeChar.getAbility());
            observeChar.attach(this.characterObserver);
            this.characterObserver = new CharacterObserver(observeChar);
        }
    }

    /**
     * A method to interact with the user about the inventory observer
     * @param observeChar the character being observed
     */
    public void askUserInventoryObserver(Character observeChar) {
        String choice = "";
        System.out.println("Would you like to observe the inventory of " +observeChar.getCharName() +"? Y/N");
        choice = readInput.readStringHandling(choice);
        while(!"Y".equals(choice) && !"N".equals(choice) && !"y".equals(choice) && !"n".equals(choice)) {
            System.out.println("Invalid input! Please try again: ");
            choice = readInput.readStringHandling(choice);
        }

        if("Y".equals(choice) || "y".equals(choice)) {
            // This gets the character's inventory - Observer
            Inventory observeInventory = new Inventory();
            observeInventory.setBackpackItems(observeChar.getBackPackInventoryItems());
            observeInventory.setWearingItems(observeChar.getItemsWearing());
            this.inventoryObserver = new InventoryObserver(observeInventory);
            observeInventory.setState(observeInventory.getItems());
            observeChar.attach(this.inventoryObserver);
            this.inventoryObserver = new InventoryObserver(observeInventory);

            // If the character being observed is the player character, ask user if they want to make changes
            if(this.character.getCharName().equals(observeChar.getCharName())) {
                // Ask the user if they want to make changes to the player's inventory
                InventoryScreen inventoryScreen = new InventoryScreen();
                inventoryScreen.setObserverChar(observeChar);
                inventoryScreen.InventoryScreen();
                observeChar = inventoryScreen.getObserverChar();
                // Update the player according to the new inventory
                this.character = observeChar;
            }
        }
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