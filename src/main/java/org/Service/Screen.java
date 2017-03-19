package main.java.org.Service;

import main.java.org.model.GameConstantsInterface;

/**
 * A class for calling the GameConstantsInterface to interact with the user
 *
 * @author Maysam Mokarian
 * @version 1.0
 * @since 2017-02-08
 */
public class Screen {

    /**
     * A method to show the choices at the beginning of the game.
     */
    static void ShowMainMenu(){
        System.out.println(GameConstantsInterface.MAIN_MENU_TO_LOAD_EDIT_GAME);
    }


}
