package main.java.org.model.StrategyPackage;

import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;

import java.awt.*;
import java.util.Random;

/**
 * This strategy is for friendly NPCs. A friendly NPC will wander around the map
 * randomly. If it comes near a chest while moving, it will loot it.
 * If a character using the friendly strategy is attacked, it will change its
 * strategy and become aggressive.
 *
 * @author
 * @version
 * @since
 */
public class FriendlyNPC implements BehaviourStrategy {
    /**
     * A method for executing FriendlyNPC strategy
     * TODO add something or get rid of method
     */
    @Override
    public void execute() {

    }

    /**
     * A method for moving a friendly NPC randomly
     * @param fchar the one who has a turn now
     * @param player the player character of the map
     * @param objective the position of the objective of the map - chest or exit
     */
    @Override
    public void move(Character fchar, Character player, Point objective) {
        Random r = new Random();
        // Get the current position
        Point current = fchar.getCurrentPosition();
        // Get a random number to move from 1 to 3 on the x axis
        int x = r.nextInt(3);
        // Subtract the movement made on the x axis from 3
        // This has to be done cause the character is only allowed to move 3 map cells
        // if x = 2 the user only has one cell left to move and so on.
        int y = (int) current.getY() + (3 - x);
        // Add the random movement on x to the current x
        x += (int) current.getX();

        //TODO add out of bounds check

        // Set the new position
        fchar.setCurrentPosition(new Point(x,y));
    }

    /**
     * The friendly character does not attack!
     * Do nothing in this method
     * @param fchar
     * @param attackedChar
     */
    @Override
    public void attack(Character fchar, Character attackedChar) {

    }

    /**
     * The friendly character can interact with a chest on the map
     * @param fchar
     * @param chest
     */
    @Override
    public void interact(Character fchar, BackPackInventory chest) {

    }
}
