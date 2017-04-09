package main.java.org.model.StrategyPackage;

import main.java.org.Service.MapDirectionValidator;
import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Map;

import java.awt.*;

/**
 * This strategy is for friendly NPCs. A friendly NPC will wander around the map
 * randomly. If it comes near a chest while moving, it will loot it.
 * If a character using the friendly strategy is attacked, it will change its
 * strategy and become aggressive.
 *
 * @author
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
     *
     * @param fchar     the one who has a turn now
     * @param player    the player character of the map
     * @param objective the position of the objective of the map - chest or exit
     * @param map       the map the character is on
     */
    @Override
    public Point move(final Character fchar, final Character player, final Point objective, final Map map) {
        final Point nextPossition = getPossiblePoints(map, fchar.getCurrentPosition());
        return null;

    }

    @Override
    public void attack(final Character attackingChar, final Character attackedChar) {

    }

    @Override
    public void interact(final Character character, final BackPackInventory chestORbackpack) {

    }

    private Point getPossiblePoints(final Map map, final Point targetPoint) {

        final MapDirectionValidator validate = new MapDirectionValidator();
        for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getCols(); col++) {
                if (validate.coordinateIsValidToMove(row, col, map, targetPoint)) {
                    return new Point(row, col);
                }
            }
        }
        return null;


    }
}
