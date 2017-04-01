package main.java.org.Service;

import main.java.org.Service.CharacterScreens.CharacterScreen;
import main.java.org.model.*;
import main.java.org.view.MapFrame;

/**
 * This class is is to Generate/edit game objects .
 * Game , Map, Campaigns, Items, Characters are created/edited by interacting with user
 *
 * @author Maysam Mokarian
 * @version 1.0
 * @since 2017-02-08
 */

public class GameGenerator {
    private ReadInput readInput = new ReadInput();
    /**
     * A start screen for the game, offers choices for the user
     *
     * @throws Exception in case the choice is no valid
     */
    public void showMenuToStartTheGame() throws Exception {
        String chosen = GameConstantsInterface.EMPTY_STRING;
        while ("".equalsIgnoreCase(chosen) ||
                GameConstantsInterface.CHOSEN_ITEM_NOT_VALID.equalsIgnoreCase(chosen) ||
                !GameConstantsInterface.END.equalsIgnoreCase(chosen)) {
            Screen.ShowMainMenu();
            chosen = getUserChosenOption();
        }
        System.exit(0);
    }

    /**
     * A method for getting an input from the user to determine what they would like to do.
     *
     * @return the users option such as create, edit, play or quit
     * @throws Exception in case the choice is no valid
     */
    String getUserChosenOption() throws Exception {
        int option = 0;
        GameShoppingCard gameShoppingCard = new GameShoppingCard();

        try {
            option = Integer.parseInt(readInput.readLine());
        } catch (NumberFormatException e) {
            System.out.println(GameConstantsInterface.NOT_A_NUMBER);
            System.out.println(GameConstantsInterface.CHOSEN_ITEM_NOT_VALID);
            getUserChosenOption();
        }

        switch (option) {
            case 1:
                //first index is the player character and the rest are non-player-characters
                createOrEditCharacter();
                return GameConstantsInterface.CHARACTER;
            case 2:
                createOrChoseMaps();
                return GameConstantsInterface.MAP;
            case 3:
                gameShoppingCard.setCampaign(createOrChoseCampaign());
                return GameConstantsInterface.CAMPAIGN;
            case 4:
                createOrEditItems();
                return GameConstantsInterface.ITEM;
            case 5:
                System.out.println("Starting the Game");
                playGame();
                return GameConstantsInterface.START;
            case 6:
                System.out.println("Ending the Game, Thanks!");
                return GameConstantsInterface.END;
            default:
                System.out.println(GameConstantsInterface.CHOSEN_ITEM_NOT_VALID);
                return GameConstantsInterface.CHOSEN_ITEM_NOT_VALID;
        }

    }

    /**
     * A method that calls the Item screen to interact with a user after the user has chosen to create, edit or
     * choose an item
     */
    public Item createOrEditItems() {
        ItemScreen itemScreen =new ItemScreen();
        return itemScreen.askUserToCreateOrEditItem();
      }

    /**
     * A method that calls the Campaign screen to interact with a user after the user has chosen to create, edit or
     * choose a campaign
     *
     * @return a Campaign object
     * @throws Exception
     */
    private Campaign createOrChoseCampaign() throws Exception {
        CampaignScreen cs = new CampaignScreen();
        cs.CampaignScreen();
        return cs.getNewCamp();
    }

    /**
     * A method that calls the CharacterPackage screen to interact with a user after the user has chosen to create or edit
     * a character
     *
     * @throws Exception
     */
    private void createOrEditCharacter() throws Exception {
        CharacterScreen cs = new CharacterScreen();
        cs.choiceScreen();
    }

    /**
     * A method that calls the map frame to interact with a user after the user has chosen to create or edit
     * a map
     */
    private void createOrChoseMaps() {

        Map map = new MapFrame().makeFrame("Map AbilityScoreBuilder");
        //  MapFrame.Main();
    }

    /**
     * A method that calls the play screen to interact with the user to play the game
     */
    private void playGame() throws Exception {
        PlayScreen ps = new PlayScreen();
        ps.PlayScreen();
    }

}
