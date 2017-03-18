package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.Character;
import main.java.org.model.Map;

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
        this.character = ol.loadCharacterFromXML(readLine());

        while (this.character == null) {
            System.out.println("This character does not exist! Please try again: ");
            this.character = ol.loadCharacterFromXML(readLine());
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
