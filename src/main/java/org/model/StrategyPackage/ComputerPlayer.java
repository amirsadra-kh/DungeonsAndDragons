package main.java.org.model.StrategyPackage;

import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Map;

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
    /**
     * A method for moving a computer player - move towards objective of map or towards exit
     * @param compPlayer the computer player
     * @param player the player character of the map
     * @param objective the position of the objective of the map - chest or exit
     * @param map the map the character is on
     */
    @Override
    public Point move(Character compPlayer, Character player, Point objective, Map map) {
        if(compPlayer.getBurning()) {
            // TODO decrease monster's hitpoints here based on getBurningDamage in burning decorator
        }
        return null;
    }

    /**
     * A method for attacking for the computer player
     * @param compPlayer
     * @param attackedChar
     */
    @Override
    public void attack(Character compPlayer, Character attackedChar) {

    }

    /**
     * A method for the compPlayer to interact with a chest, a friendly characters backpack or a dead monster's backpack
     * @param compPlayer
     * @param chestORbackpack
     */
    @Override
    public void interact(Character compPlayer, BackPackInventory chestORbackpack) {

    }
}
