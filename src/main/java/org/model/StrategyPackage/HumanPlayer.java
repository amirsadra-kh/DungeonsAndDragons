package main.java.org.model.StrategyPackage;

import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;

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
    @Override
    public void execute() {

    }

    /**
     * A method for moving a player character - by choice
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
