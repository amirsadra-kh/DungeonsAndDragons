package main.java.org.model.StrategyPackage;

import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;

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
     */
    void move(Character character, Character player, Point objective);

    void attack(Character attackingChar, Character attackedChar);

    void interact(Character character, BackPackInventory chestORbackpack);
}
