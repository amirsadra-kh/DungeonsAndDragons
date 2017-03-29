package main.java.org.model;

/**
 * An interface for the game constants used in the main menu as well as in the different object screens
 *
 * @author Maysam Mokarian
 * @version 2.0
 * @since 02.04.2017
 */
public interface GameConstantsInterface {
    String CAMPAIGN = "Campaign";
    String CHARACTER = "Character";
    String MAP = "Map";
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
            "5) Start the Game:\n" +
            "6) End the Game:\n";
    String MAP_SOURCE_PATH = "src/main/java/org/resources/characters/";
    String ENTER_DIRECTION = "Enter the direction you wish to move: \n Left:L\n Right:R \n Down:D\n Up:U\n";
}
