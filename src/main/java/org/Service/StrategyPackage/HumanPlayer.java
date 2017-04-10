package main.java.org.Service.StrategyPackage;

import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Map;

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
     * @param character the one who has a turn now - the player character
     * @param player the player character of the map
     * @param objective the position of the objective of the map - chest or exit
     * @param map the map the character is on
     */
    @Override
    public Point move(final Character character, final Character player, final Point objective, final Map map) {
        return null;

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
