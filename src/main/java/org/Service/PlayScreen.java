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
import java.awt.*;
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
        if (getMapsInTheCampaign().size() > 0) {
            map = getMapsInTheCampaign().get(level);
            map.setPlayer(this.character);
            playGame(false);
        }
    }


    /**
     * This method is the base of playing the game
     *
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
        while (!"Y".equals(choice) && !"N".equals(choice) && !"y".equals(choice) && !"n".equals(choice)) {
            System.out.println("Invalid input! Please try again: ");
            choice = readInput.readStringHandling(choice);
        }
        if ("Y".equals(choice) || "y".equals(choice)) {
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
        String direction = readInput.readCoordinate();
        final MapDirectionValidator mapValidator = new MapDirectionValidator(this.campaign, this.map);
        while (mapValidator.isDirectionLeadsToValidCell(direction, this.map)) {
            final Point point = Map.getPlayerCoordinate(this.map);
            final Point nextCell = mapValidator.getNextCellToMove(direction, point);
            final String objectInMap = map.getScreen()[nextCell.x][nextCell.y];
            MapScreen.showMap(this.map);
            if (checkInteractionObject(objectInMap, nextCell.x, nextCell.y)) {
                direction = readInput.readCoordinate();
                continue;
                // in case we go next level, we should set the
                // player coordinate and next coordinate again
            }
            System.out.println("Enter \"EXIT\" at any time you like to save and exit the game");
            direction = readInput.readCoordinate();
            if (userWantToSaveTheGameAndExit(direction))
                return;
        }
    }

    /**
     * Thsi method is to check the interaction Object and interact accordingly
     *
     * @param str The target object
     * @param i   the I coordinate
     * @param j   the j coordinate
     * @return returns true if we go next level
     */
    private boolean checkInteractionObject(final String str, final int i, final int j) {

        if ("W".equalsIgnoreCase(str)) {
            System.out.println("The target direction will lead to wall, please try another direction");
            MapScreen.printElementsInTheMap(this.map);
            MapScreen.showMap(this.map);
            return false;
        } else if ("Q".equalsIgnoreCase(str)) {
            return isLevelCompleted(this.map, i, j, campaign, str);
        } else if (!" ".equalsIgnoreCase(str)) {
            SetInteractionStrategy.interact(this.map, str, Map.getPlayerCoordinate(this.map), new Point(i, j), campaign);
            MapScreen.printElementsInTheMap(this.map);
            MapScreen.showMap(this.map);
            return false;
        } else {
            final Point point = Map.getPlayerCoordinate(this.map);
            this.map.getScreen()[point.x][point.y] = " ";
            this.map.getScreen()[i][j] = "P";
            MapScreen.printElementsInTheMap(this.map);
            MapScreen.showMap(this.map);
            return false;
        }
    }

    /**
     * This method is to validate if we can go to next level
     *
     * @param map      the map we are playing
     * @param i        the i coordinate on map
     * @param j        the j coordinate on map
     * @param campaign the campaign we are playing
     * @param str      the target object we are interacting with
     * @return true if we go next level
     */
    private boolean isLevelCompleted(final Map map, final int i, final int j, final Campaign campaign, final String str) {
        if (this.map.isCanGoNextLevel()) {

            try {
                this.map = campaign.getNextLevel();
            } catch (final Exception e) {
                System.out.println("You finished all the levels in the Campaign!");
                System.exit(0);
                return true;
            }
            PlayScreen.setPlayerAtEntryPoint(this.map);
            System.out.println("Great you moved to next level");
            MapScreen.showMap(this.map);
            System.out.println(GameConstantsInterface.ENTER_DIRECTION);
            //SetInteractionStrategy.interact(this.map, str, Map.getPlayerCoordinate(this.map), new Point(i, j), campaign);

            return true;
        } else {
            System.out.println("You have not found a chest in the map yet\n" +
                    " Please find the chest in the map and then you can go to next level");
            return false;
        }
    }

    /**
     * this methdo checks if user wants to save the game and exit
     *
     * @param direction
     * @return true if user wants to exit
     */
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
    private void enterDirection() {
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
     *
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

        while (mapCharacters.get(choice - 1).getCharName().equals(null)) {
            System.out.println("This character does not exist! Please try again: ");
            observeChar = character.loadCharacter(readInput.readLine());
        }
        return mapCharacters.get(choice - 1);
    }

    /**
     * A method to interact with user about the ability observer
     *
     * @param observeChar the character being observed.
     */
    public void askUserAbilityObserver(final Character observeChar) {
        String choice = "";
        // This gets the character's abilities - Observer
        System.out.println("Would you like to observe the ability of " + observeChar.getCharName() + "? Y/N");
        choice = readInput.readStringHandling(choice);
        while (!"Y".equals(choice) && !"N".equals(choice) && !"y".equals(choice) && !"n".equals(choice)) {
            System.out.println("Invalid input! Please try again: ");
            choice = readInput.readStringHandling(choice);
        }

        if ("Y".equals(choice) || "y".equals(choice)) {
            observeChar.setState(observeChar.getAbility());
        }
    }

    /**
     * A method to interact with the user about the inventory observer
     *
     * @param observeChar the character being observed
     */
    public void askUserInventoryObserver(Character observeChar) {
        String choice = "";
        System.out.println("Would you like to observe the inventory of " + observeChar.getCharName() + "? Y/N");
        choice = readInput.readStringHandling(choice);
        while (!"Y".equals(choice) && !"N".equals(choice) && !"y".equals(choice) && !"n".equals(choice)) {
            System.out.println("Invalid input! Please try again: ");
            choice = readInput.readStringHandling(choice);
        }

        if ("Y".equals(choice) || "y".equals(choice)) {
            // This gets the character's inventory - Observer
            final Inventory observeInventory = new Inventory();
            observeInventory.setBackpackItems(observeChar.getBackPackInventoryItems());
            observeInventory.setWearingItems(observeChar.getItemsWearing());
            observeInventory.setState(observeInventory.getItems());

            // If the character being observed is the player character, ask user if they want to make changes
            if (this.character.getCharName().equals(observeChar.getCharName())) {
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

    /**
     * This method is to set the Character
     *
     * @param character
     */
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
            final PlayScreen playScreen = (PlayScreen) u.unmarshal(f);
            playScreen.getCampaign().setMaps(playScreen.getCampaign().getMapsFromCampaign(playScreen.getCharacter()));
            return playScreen;
        } catch (final Exception e) {
            //e.printStackTrace();
            return null;
        }
    }
}