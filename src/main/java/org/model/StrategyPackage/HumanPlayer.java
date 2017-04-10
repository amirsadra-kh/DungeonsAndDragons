package main.java.org.model.StrategyPackage;

import main.java.org.Service.MapDirectionValidator;
import main.java.org.Service.MapScreen;
import main.java.org.model.Campaign;
import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.GameConstantsInterface;
import main.java.org.model.Map;
import main.java.org.model.ReadInput;

import java.awt.*;

/**
 * This strategy is for a player character controlled by the user. It requires user
 * interaction for determining where the player moves, what NPC it attacks, and
 * what other interactions it will do during a turn.
 *
 * @author
 * @version
 * @since
 */
public class HumanPlayer implements BehaviourStrategy {
    ReadInput readInput = new ReadInput();
    /**
     * A method for moving a player character - by choice
     * @param humanPlayer the one who has a turn now - the player character
     * @param player the player character of the map
     * @param objective the position of the objective of the map - chest or exit
     * @param map the map the character is on
     */
    @Override
    public Point move(Character humanPlayer, Character player, Point objective, Map map) {
        if(humanPlayer.getBurning()) {
            // TODO decrease monster's hitpoints here based on getBurningDamage in burning decorator
        }

        // Ask user 3 times where they want to move using R, L, U and D
        for(int i = 0; i < 3; i ++) {
            //This prints and ask user to enter the directions
            System.out.println(GameConstantsInterface.ENTER_DIRECTION);
            if(!checkIfMoveIsValid(map)) {
                i--;
                System.out.println("Invalid Move!");
            }
        }

        // TODO return the new coordinate
        return null;
    }

    /**
     * A method to check if the move is valid
     * @param map
     * @return
     */
    private boolean checkIfMoveIsValid(Map map) {
        String direction = readInput.readCoordinate();
        MapDirectionValidator validate = new MapDirectionValidator();
        return validate.isDirectionLeadsToValidCell(direction, map);
    }

    /**
     * A method for the player to attack another character
     * @param player
     * @param attackedChar
     */
    @Override
    public void attack(Character player, Character attackedChar) {

    }

    /**
     * A method for the player to interact with a chest, a friendly characters backpack or a dead monster's backpack
     * @param character
     * @param chestORbackpack
     */
    @Override
    public void interact(Character character, BackPackInventory chestORbackpack) {

    }
}
