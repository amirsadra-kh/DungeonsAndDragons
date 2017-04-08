package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.CharacterPackage.Ability;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.CharacterPackage.Inventory;
import main.java.org.model.GameConstantsInterface;
import main.java.org.model.Item;
import main.java.org.model.Map;
import main.java.org.model.ReadInput;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A class to interact with the user while playing the game
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-10
 */
@XmlRootElement
public class PlayScreen {
    private Character character;
    private Campaign campaign;
    private ReadInput readInput = new ReadInput();
    private Map map = new Map();
    // The first level to be played, should be increased after a map has been finished.
    private int level = 0;
    // Observer variables
    private CharacterObserver characterObserver;
    private InventoryObserver inventoryObserver;
    private List<Item> inventory = new ArrayList<>();
    private Ability ability;

    public PlayScreen() {

    }

    /**
     * This method sets the player at the entry point of the map
     * it searches for E in the Map and set the payer position there
     *
     * @param map
     */
    public static void setPlayerAtEntryPoint(final Map map) {
        for (int i = 0; i < map.getScreen().length; i++) {
            for (int j = 0; j < map.getScreen()[i].length; j++) {
                if (map.getScreen()[i][j].equalsIgnoreCase("E")) {
                    map.getScreen()[i][j] = "P";
                }
            }
        }
    }

    /**
     * A basic interaction screen after the user chooses Play
     */
    public void PlayScreen() throws Exception {
        choseCharacterForPlayingGame();
        choseCampaignForPlayingGame();
        if(getMapsInTheCampaign().size() > 0) {
            map = getMapsInTheCampaign().get(level);
            map.setPlayer(this.character);
            playGame(false);
        }
    }


    /**
     * This method is the base of playing the game
     * @param gameLoaded this parameter is to decide if the player character should be located at the entry point of map
     */
    protected void playGame(final boolean gameLoaded) {
        if (!gameLoaded) {
            setPlayerAtEntryPoint(map);
        }
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
        movePlayer();
    }

    /**
     * This method is to move the player in the Map
     * It reads the direction , validate the direction and show the map again
     */
    private void movePlayer() {
        String direction =readInput.readLine();
        final MapDirectionValidator mapValidator = new MapDirectionValidator(this.campaign, this.map);
        while (!mapValidator.validateDirectionIsValidBoundriesAndMovePlayer(direction)) {
            System.out.println("Enter \"EXIT\" at any time you like to save and exit the game");
            direction =readInput.readLine();
            MapScreen.showMap(map);
            if (userWantToSaveTheGameAndExit(direction))
                return;
        }
    }

    private boolean userWantToSaveTheGameAndExit(final String direction) {
        if ("Exit".equalsIgnoreCase(direction)) {
            saveGame();
            return true;
        }
        return false;
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
        final ObjectLoader ol = new ObjectLoader();
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
        final ObjectLoader ol = new ObjectLoader();
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
    public void userObserverChoice(final Map currentMap) {
        // Get the character the user wants to observe
        final Character observeChar = getObserverCharacter(currentMap);

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
    public Character getObserverCharacter(final Map currentMap) {
        final ObjectLoader ol = new ObjectLoader();
        // Testing the CharacterPackage Observer
        Character observeChar = new Character();
        // Get the characters that are in the map
        final List<Character> mapCharacters = currentMap.getNonPLayerCharacters();
        // Add the player character to the list as well
        mapCharacters.add(character);
        int choice = 0;

        System.out.println("Please enter the name of the character you would like to observe from the list below: ");
        // Print the name of each character that is on the map
        int i = 1;
        for (final Character character : mapCharacters) {
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
    public void askUserAbilityObserver(final Character observeChar) {
        String choice = "";
        // This gets the character's abilities - Observer
        System.out.println("Would you like to observe the ability of " +observeChar.getCharName() +"? Y/N");
        choice = readInput.readStringHandling(choice);
        while(!"Y".equals(choice) && !"N".equals(choice) && !"y".equals(choice) && !"n".equals(choice)) {
            System.out.println("Invalid input! Please try again: ");
            choice = readInput.readStringHandling(choice);
        }

        if("Y".equals(choice) || "y".equals(choice)) {
            observeChar.setState(observeChar.getAbility());
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
            final Inventory observeInventory = new Inventory();
            observeInventory.setBackpackItems(observeChar.getBackPackInventoryItems());
            observeInventory.setWearingItems(observeChar.getItemsWearing());
            observeInventory.setState(observeInventory.getItems());

            // If the character being observed is the player character, ask user if they want to make changes
            if(this.character.getCharName().equals(observeChar.getCharName())) {
                // Ask the user if they want to make changes to the player's inventory
                final InventoryScreen inventoryScreen = new InventoryScreen();
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

    public void setCharacter(final Character character) {
        this.character = character;
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
     * a setter for Campaign
     *
     * @param campaign
     */
    public void setCampaign(final Campaign campaign) {
        this.campaign = campaign;
    }

    /**
     * This method is to return the list of maps
     *
     * @return
     * @throws Exception
     */
    public List<Map> getMapsInTheCampaign() throws Exception {
        final List<Map> maps = new ArrayList<>();
        for (final String mapName : this.campaign.getMapNames()) {
            maps.add(new ObjectLoader().loadMapFromXML(mapName));
        }
        return maps;
    }

    /**
     * a getter for ReadInput
     *
     * @return ReadInput
     */
    public ReadInput getReadInput() {
        return readInput;
    }

    /**
     * a setter for ReadInput
     *
     * @param readInput
     */
    public void setReadInput(final ReadInput readInput) {
        this.readInput = readInput;
    }

    /**
     * a getter for level
     *
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * a setter for level
     *
     * @param level
     */
    public void setLevel(final int level) {
        this.level = level;
    }

    /**
     * a getter for CharacterObserver
     *
     * @return CharacterObserver
     */
    @XmlElement
    public CharacterObserver getCharacterObserver() {
        return characterObserver;
    }

    /**
     * a setter for CharacterObserver
     *
     * @param characterObserver
     */
    public void setCharacterObserver(final CharacterObserver characterObserver) {
        this.characterObserver = characterObserver;
    }

    /**
     * a getter forInventoryObserver
     *
     * @return InventoryObserver
     */
    @XmlElement
    public InventoryObserver getInventoryObserver() {
        return inventoryObserver;
    }

    /**
     * a setter for InventoryObserver
     *
     * @param inventoryObserver
     */
    public void setInventoryObserver(final InventoryObserver inventoryObserver) {
        this.inventoryObserver = inventoryObserver;
    }

    /**
     * get The Inventory
     *
     * @return
     */
    @XmlElement
    public List<Item> getInventory() {
        return inventory;
    }

    /**
     * Set the Inventory
     *
     * @param inventory an Inventory Object
     */
    public void setInventory(final List<Item> inventory) {
        this.inventory = inventory;
    }

    /**
     * Set the Ability
     *
     * @return
     */
    @XmlElement
    public Ability getAbility() {
        return ability;
    }

    /**
     * Get the Ability
     *
     * @param ability
     */
    public void setAbility(final Ability ability) {
        this.ability = ability;
    }

    /**
     * Get the Map
     *
     * @return
     */
    @XmlElement
    public Map getMap() {
        return map;
    }

    /**
     * Set the Map
     *
     * @param map
     */
    public void setMap(final Map map) {
        this.map = map;
    }

    /**
     * A method for saving a campaign
     */
    public void saveGame() {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(PlayScreen.class);
            final Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            System.out.println("Please Enter the Game Name to save");
            final String fileName = readInput.readLine();
            m.marshal(this, new FileOutputStream("src/main/java/org/resources/games/" + fileName));
        } catch (final Exception e) {
            e.printStackTrace();
            System.out.print("Could not save the game");
        }
    }

    /**
     * A method for loading an existing PlayScreen
     *
     * @param name of the campaign
     * @return an existing campaign object
     */
    public PlayScreen loadGame(final String name) {
        try {
            final JAXBContext jc = JAXBContext.newInstance(PlayScreen.class);
            Unmarshaller u = null;
            u = jc.createUnmarshaller();
            final File f = new File("src/main/java/org/resources/games/" + name);
            return (PlayScreen) u.unmarshal(f);
        } catch (final Exception e) {
            //e.printStackTrace();
            return null;
        }
    }
}