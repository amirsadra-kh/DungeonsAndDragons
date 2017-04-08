package main.java.org.model.StrategyPackage;

import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;

import java.awt.*;

/**
 * This strategy is for a player character controlled by the computer. A computer
 * player character’s objective is to go to the next map, i.e. fulfilling any objective
 * that you have defined to finish a map.
 *
 * @author
 * @version
 * @since
 */
public class ComputerPlayer implements BehaviourStrategy {
    @Override
    public void execute() {

    }

    /**
     * A method for moving a computer player - move towards objective of map or towards exit
     * @param character the one who has a turn now
     * @param player the player character of the map
     * @param objective the position of the objective of the map - chest or exit
     */
    @Override
    public void move(Character character, Character player, Point objective) {

    }

    @Override
    public void attack(Character attackingChar, Character attackedChar) {

    }

    @Override
    public void interact(Character character, BackPackInventory chestORbackpack) {

    }
}
