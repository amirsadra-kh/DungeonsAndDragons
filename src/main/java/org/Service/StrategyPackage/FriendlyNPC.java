package main.java.org.Service.StrategyPackage;

import main.java.org.Service.MapDirectionValidator;
import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Item;
import main.java.org.model.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This strategy is for friendly NPCs. A friendly NPC will wander around the map
 * randomly. If it comes near a chest while moving, it will loot it.
 * If a character using the friendly strategy is attacked, it will change its
 * strategy and become aggressive.
 *
 * @author Masyam Mokarian
 * @version 1.0
 * @since 08.04.2017
 */
public class FriendlyNPC implements BehaviourStrategy {
    /**
     * A method for moving a friendly NPC randomly
     * @param fchar the one who has a turn now
     * @param player the player character of the map
     * @param objective the position of the objective of the map - chest or exit
     */
    @Override
    public Point move( Character fchar,  Character player,  Point objective,  Map map) {
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
     * @param map    the playing map
     * @param player the friendly character
     */
    public void interactWithChest (Map map, Character player) {
        final BackPackInventory chest = map.getChest();
        final ArrayList<Item> loot = new ArrayList<>();
        if (chest != null) {
            loot.addAll(chest.getItems());
        }
        final java.util.List<Item> playerBackpack = player.getBackPackInventoryItems();
        if (playerBackpack == null) {
            player.setBackPackInventory(new BackPackInventory());
        }
        while (loot.size() > 0) {
            if (player.getBackPackInventoryItems().size() < 10) {
                player.getBackPackInventoryItems().add(loot.remove(loot.size() - 1));
            } else {
                break;
            }
        }
        map.getChest().setItems(loot);
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
    public void interact(Character fchar, BackPackInventory chest, Map map) {

    }

    /**
     * This method is to get the possible point for the friendly character
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
