package main.java.org.Service.StrategyPackage;

import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Map;

import java.awt.*;

/**
 * The classes that implement a concrete strategy should implement this.
 * The Character class uses this to use a concrete strategy for the behaviour of the character
 *
 * @author
 * @version
 * @since
 */
public interface BehaviourStrategy {
    /**
     * Method whose implementation varies depending on the strategy adopted
     */
    void execute();

    /**
     * A method for moving a character when it is their turn.
     * @param character the one who has a turn now
     * @param player the player character of the map
     * @param objective the position of the objective of the map - chest or exit
     * @param map the map the character is on
     */
    Point move(Character character, Character player, Point objective, Map map);

    /**
     * A method for a character attack after they have moved
     * @param attackingChar
     * @param attackedChar
     */
    void attack(Character attackingChar, Character attackedChar);

    /**
     * A method for a character to interact with a chest or another character's backpack during their turn
     * @param character
     * @param chestORbackpack
     */
    void interact(Character character, BackPackInventory chestORbackpack);
}
