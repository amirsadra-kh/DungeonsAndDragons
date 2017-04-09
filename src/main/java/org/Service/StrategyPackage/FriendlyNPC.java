package main.java.org.Service.StrategyPackage;

import main.java.org.Service.MapDirectionValidator;
import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Item;
import main.java.org.model.Map;

import java.awt.*;
import java.util.ArrayList;

/**
 * This strategy is for friendly NPCs. A friendly NPC will wander around the map
 * randomly. If it comes near a chest while moving, it will loot it.
 * If a character using the friendly strategy is attacked, it will change its
 * strategy and become aggressive.
 *
 * @author Masyam Mokarian
 * @version 1.0
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
        final Point nextPosition = getPossiblePoints(map, fchar.getCurrentPosition());
        if (nextPosition != null && map.getScreen()[nextPosition.x][nextPosition.y].equalsIgnoreCase("c")) {
            interactWithChest(map, fchar);
        }
        fchar.setCurrentPosition(nextPosition);
        return nextPosition;

    }

    /**
     * This method is for the friendly character to interact with the chest
     *
     * @param map    the paying map
     * @param player the freidnly character
     */
    public void interactWithChest(final Map map, final Character player) {

        final BackPackInventory chest = map.getChest();
        java.util.List<Item> loot = new ArrayList<>();
        if (chest != null) {
            loot = chest.getItems();
        }
        final java.util.List<Item> playerBackpack = player.getBackPackInventoryItems();
        if (playerBackpack == null) {
            player.setBackPackInventory(new BackPackInventory());
        }
        for (int i = 0; i < loot.size(); i++) {
            if (player.getBackPackInventoryItems().size() <= 10) {
                player.getBackPackInventoryItems().add(loot.get(i));
                map.getChest().getItems().remove(loot.get(i));
            } else {
                break;
            }
        }

    }

    @Override
    public void attack(final Character attackingChar, final Character attackedChar) {

    }

    @Override
    public void interact(final Character character, final BackPackInventory chestORbackpack) {

    }

    /**
     * Thsi method is to get the possible point for the friendly character
     *
     * @param map         the current map
     * @param targetPoint the target point
     * @return it returns the first available point
     */
    private Point getPossiblePoints(final Map map, final Point targetPoint) {

        final MapDirectionValidator validate = new MapDirectionValidator();
        for (int row = 0; row < map.getScreen().length; row++) {
            for (int col = 0; col < map.getScreen()[row].length; col++) {
                if (validate.coordinateIsValidForFriendlyCharacter(row, col, map, targetPoint)) {
                    return new Point(row, col);
                }
            }
        }
        return null;


    }
}
