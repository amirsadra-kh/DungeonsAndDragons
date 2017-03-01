package main.java.org.model;

/**
 * An interface for the game constants used in the main menu as well as in the different object screens
 *
 * @author Maysam Mokarian
 * @since 02.04.2017
 * @version 1.0
 */
public interface GameConstants {
    String CAMPAIGN = "Campaign";
    String CHARACTER = "Character";
    String MAP ="Map";
    String ITEM = "Item";
    String START = "Start";
    String END = "End";
    String CHOSEN_ITEM_NOT_VALID = "The Chosen Item is not valid, please try again";
    String NOT_A_NUMBER = "The prompted is not a number! ";
    String NOT_A_STRING = "The prompted is not a STRING";
    String EMPTY_STRING = "";
    String MAIN_MENU_TO_LOAD_EDIT_GAME = "Welcome to Dungeons and Dragons\n " +
            "Please choose from the following options:\n" +
            "1) Create/Modify Character:\n" +
            "2) Create/Modify Map:\n" +
            "3) Create/Modify Campaign:\n" +
            "4) Create/Modify Items:\n" +
            "5) Start the Game:\n"+
            "6) End the Game:\n";
}
