package main.java.org.model.StrategyPackage;

import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Item;
import main.java.org.model.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This strategy is for enemy NPCs. An aggressive NPC will always run toward the
 * player character and attack it. If it comes near a chest or other NPC while doing
 * so, it will loot the chest and attack the NPC.
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 09.04.2017
 */
public class AggressiveNPC implements BehaviourStrategy {
    @Override
    public void execute() {

    }

    @Override
    /**
     * A method for moving a hostile character - move towards player
     * @param character the one who has a turn now
     * @param player the player character of the map
     * @param objective the position of the objective of the map - chest or exit
     * @param map the map the character is on
     */
    public void move(Character monster, Character player, Point objective, Map map) {
        Point playerPoint = player.getCurrentPosition();
        Point monsterPoint = monster.getCurrentPosition();

        // Add all validated points for target to move to a list of points
        FrighteningStrategy frighten = new FrighteningStrategy();
        ArrayList<Point> possiblePoints = frighten.addValidatedPoints(map, monsterPoint);


        // Find a point furthest away from attacker
        Point min = getMinDistance(possiblePoints, playerPoint);

        // Set the new position
        // return min;
    }

    /**
     * A method to get the point closest to the player out of the valid points
     * @param possiblePoints points the monster can move
     * @param playerPoint the position of the player
     * @return a point closest to the player
     */
    private Point getMinDistance(ArrayList<Point> possiblePoints, Point playerPoint) {
        Point min = possiblePoints.get(0);
        for(Point p : possiblePoints) {
            if((Math.abs(p.x - playerPoint.x)) + Math.abs(p.y - playerPoint.y) <
                    (Math.abs(min.x - playerPoint.x)) + Math.abs(min.y - playerPoint.y))
                min = p;
        }
        return min;
    }

    /**
     * The aggressive character will attack when possible
     * @param mon
     * @param attackedChar
     */
    @Override
    public void attack(Character mon, Character attackedChar) {

    }

    /**
     * The aggressive character can loot a chest
     * @param mon
     * @param chest
     */
    @Override
    public void interact(Character mon, BackPackInventory chest) {
        BackPackInventory monBackpack = mon.getBackPackInventory();
        // remove items from chest and add to backpack
        List<Item> changedChestItems = monBackpack.addToBackpack(chest.getItems());
        chest.setItems(changedChestItems);
        // set the new backpack inventory
        mon.setBackPackInventory(monBackpack);
    }
}
