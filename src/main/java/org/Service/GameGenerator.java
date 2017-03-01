package main.java.org.Service;

import main.java.org.model.Ability;
import main.java.org.model.Campaign;
import main.java.org.model.GameConstants;
import main.java.org.model.GameShoppingCard;
import main.java.org.model.Map;
import main.java.org.view.MapFrame;
import java.util.Scanner;

/**
 * This class is is to Generate/edit game objects .
 * Game , Map, Campaigns, Items, Characters are created/edited by interacting with user
 * + *
 * + * @author Maysam Mokarian
 * + * @version 1.0
 * + * @since 2017-02-08
 */

public class GameGenerator {
    public void showMenuToStartTheGame() throws Exception {
        String chosen = GameConstants.EMPTY_STRING;
        while ("".equalsIgnoreCase(chosen) ||
                GameConstants.CHOSEN_ITEM_NOT_VALID.equalsIgnoreCase(chosen) ||
                !GameConstants.END.equalsIgnoreCase(chosen)) {
            Screen.ShowMainMenu();
            chosen = getUserChosenOption();
        }
        System.exit(0);
    }

    String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    String getUserChosenOption() throws Exception {
        int option = 0;
        GameShoppingCard gameShoppingCard = new GameShoppingCard();

        try {
            option = Integer.parseInt(readLine());
        } catch (NumberFormatException e) {
            System.out.println(GameConstants.NOT_A_NUMBER);
            System.out.println(GameConstants.CHOSEN_ITEM_NOT_VALID);
            getUserChosenOption();
        }

        switch (option) {
            case 1:
                //first index is the player character and the rest are non-player-characters
                createOrEditCharacter();
                return GameConstants.CHARACTER;
            case 2:
                createOrChoseMaps();
                return GameConstants.MAP;
            case 3:
                gameShoppingCard.setCampaign(createOrChoseCampaign());
                return GameConstants.CAMPAIGN;
            case 4:
                createOrEditItems();
                return GameConstants.ITEM;
            case 5:
                System.out.println("Starting the Game");
                //TODO next deliverable to implement starting the game
                return GameConstants.START;
            case 6:
                System.out.println("Ending the Game, Thanks!");
                return GameConstants.END;
            default:
                System.out.println(GameConstants.CHOSEN_ITEM_NOT_VALID);
                return GameConstants.CHOSEN_ITEM_NOT_VALID;
        }

    }

    private void createOrEditItems() {
    }

    private Campaign createOrChoseCampaign() throws Exception {
        CampaignScreen cs = new CampaignScreen();
        cs.CampaignScreen();
        showMenuToStartTheGame();
        return cs.getNewCamp();
    }

    private void createOrEditCharacter() {
        Ability ability = new Ability();
        System.out.println("The new character is made with the following Ability: ");
        System.out.println(ability.toString());


    }

    private void createOrChoseMaps() {

        Map map = new MapFrame().makeFrame("Map Builder");
        //  MapFrame.Main();
       }

}
