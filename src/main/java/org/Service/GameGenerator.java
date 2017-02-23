package main.java.org.Service;

import main.java.org.model.Ability;
import main.java.org.model.Campaign;
import main.java.org.model.GameConstants;
import main.java.org.model.GameShoppingCard;

import java.util.Scanner;

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

    private Campaign createOrChoseCampaign() {
        return null;
    }

    private void createOrEditCharacter() {
        Ability ability = new Ability();
        Scanner scan = new Scanner(System.in);
        String charName = "";

        System.out.println("please enter a name for character: ");
        charName = scan.nextLine();
        System.out.println( charName + " has the following ability ");
        System.out.println(ability.toString());
        ObjectSaver os = new ObjectSaver();

        os.saveCharacter(charName, );

    }

    private void createOrChoseMaps() {
    }

}
