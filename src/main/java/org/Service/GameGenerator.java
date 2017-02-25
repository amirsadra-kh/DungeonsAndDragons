package main.java.org.Service;

import main.java.org.model.Campaign;
<<<<<<< HEAD
=======
import main.java.org.Service.CampaignScreen;
>>>>>>> cc5d9cb3ac34de5fd61a6b87a3c0aa58aeafcd89
import main.java.org.model.GameConstants;
import main.java.org.model.GameShoppingCard;

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
        while ("".equalsIgnoreCase(chosen) || GameConstants.CHOSEN_ITEM_NOT_VALID.equalsIgnoreCase(chosen)) {
            Screen.ShowMainMenu();
            chosen = getUserChosenOption();
        }
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
                return GameConstants.START;
            default:
                System.out.println(GameConstants.CHOSEN_ITEM_NOT_VALID);
                return GameConstants.CHOSEN_ITEM_NOT_VALID;
        }

    }

    private void createOrEditItems() {
    }

    private Campaign createOrChoseCampaign() throws Exception {
    }

    private void createOrEditCharacter() {
        CharacterScreen cs = new CharacterScreen();
        cs.CharacterScreen();
        try {
            showMenuToStartTheGame();
        } catch (Exception exception) {
            System.out.println("Invalid entry");
        }

    }

    private void createOrChoseMaps() {
    }

}
