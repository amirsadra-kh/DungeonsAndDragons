package main.java.org.Service.StrategyPackage;

import main.java.org.Service.AdjacentObjectsFinder;
import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Item;
import main.java.org.model.Map;

import java.awt.*;
import java.util.ArrayList;

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

        // Check if there is a chest to loot
        AdjacentObjectsFinder finder = new AdjacentObjectsFinder();
        if(finder.checkForChest(compPlayer.getCurrentPosition(), map)) {
            System.out.println("Computer Player found a chest!!");
            System.out.println("Backpack inventory before chest: " +compPlayer.getBackPackInventoryItems().toString());
            interact(compPlayer, map.getChest(), map);
            System.out.println("Backpack inventory after chest: " +compPlayer.getBackPackInventoryItems().toString());
        }

        return null;
    }

    /**
     * A method for attacking for the computer player
     * @param compPlayer
     * @param attackedChar
     */
    @Override
    public void attack(final Character compPlayer, final Character attackedChar) {

    }

    /**
     * A method for the compPlayer to interact with a chest, a friendly characters backpack or a dead monster's backpack
     * @param compPlayer
     * @param chestORbackpack
     */
    @Override
    public void interact(final Character compPlayer, final BackPackInventory chestORbackpack, Map map) {
        ArrayList<Item> loot = new ArrayList<>();
        if (chestORbackpack != null) {
            loot.addAll(chestORbackpack.getItems());
        }
        java.util.List<Item> playerBackpack = compPlayer.getBackPackInventoryItems();
        if (playerBackpack == null) {
            compPlayer.setBackPackInventory(new BackPackInventory());
        }
        while (loot.size() > 0) {
            if(compPlayer.getBackPackInventoryItems().size() < 10) {
                compPlayer.getBackPackInventoryItems().add(loot.remove(loot.size() - 1));
            } else {
                break;
            }
        }
        map.getChest().setItems(loot);
    }
}
