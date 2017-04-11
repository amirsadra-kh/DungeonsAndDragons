package main.java.org.Service.StrategyPackage;

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
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 09.04.2017
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
            String direction = readInput.readCoordinate();
            if(!checkIfMoveIsValid(direction, map, humanPlayer)) {
                i--;
                System.out.println("Invalid Move!");
            } else {
                humanPlayer.setCurrentPosition(setNewPosition(direction, humanPlayer.getCurrentPosition()));
            }
        }

        // TODO return the new coordinate
        return humanPlayer.getCurrentPosition();
    }

    /**
     * A method to check if the move is valid
     * @param direction
     * @param map
     * @return
     */
    private boolean checkIfMoveIsValid(String direction, Map map, Character humanPlayer) {
        MapDirectionValidator validate = new MapDirectionValidator();
        return validate.directionIsValidToMove(direction, map, humanPlayer.getCurrentPosition());
    }

    /**
     * A method to get the new position after a valid move
     * @param direction headed direction
     * @param current position point
     * @return
     */
    private Point setNewPosition(String direction, Point current) {
        MapDirectionValidator validate = new MapDirectionValidator();
        return validate.getNextCellToMove(direction, current);
    }

    /**
     * A method for the player to attack another character
     * @param player
     * @param attackedChar
     */
    @Override
    public void attack(final Character player, final Character attackedChar) {

    }

    /**
     * A method for the player to interact with a chest, a friendly characters backpack or a dead monster's backpack
     * @param character
     * @param chestORbackpack
     */
    @Override
    public void interact(final Character character, final BackPackInventory chestORbackpack) {

    }
}
