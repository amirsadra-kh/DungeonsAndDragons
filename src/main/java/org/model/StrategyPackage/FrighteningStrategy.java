package main.java.org.model.StrategyPackage;

import main.java.org.Service.MapDirectionValidator;
import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Map;

import java.awt.*;
import java.util.ArrayList;

/**
 * A strategy for the Frightening decorator
 * Target runs away from character for a number of turns
 * equal to the enchantment bonus of the weapon.
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 02.04.2017
 */
public class FrighteningStrategy implements BehaviourStrategy {
    private int turns = 0;
    private BehaviourStrategy previousStrategy;
    private Character attacker;

    /**
     * A method to set up the frightening strategy number of turns, previous strategy of the target and the attacker
     * character
     *
     * @param enhancement of the weapon
     * @param previousStrategy of the target
     * @param attacker the character who made the target frightened
     */
    public void setUp(int enhancement, BehaviourStrategy previousStrategy, Character attacker) {
        this.turns = enhancement;
        this.previousStrategy = previousStrategy;
        this.attacker = attacker;
    }

    @Override
    public void execute() {
    }

    /**
     * A method for running away from character who attacked with frightening enhancement
     * for as many turns as the enhancement of the weapon has.
     *
     * @param target
     * @param player
     * @param objective
     * @param map the map the character is on
     */
    @Override
    public Point move(Character target, Character player, Point objective, Map map) {
        Point attackerPoint = this.attacker.getCurrentPosition();
        Point targetPoint = target.getCurrentPosition();

        // Add all validated points for target to move to a list of points
        ArrayList<Point> possiblePoints = addValidatedPoints(map, targetPoint);


        // Find a point furthest away from attacker
        Point max = getMaxDistance(possiblePoints, attackerPoint);

        // Set the new position
        return max;
    }

    /**
     * A method for checking all the cells in a map and see if the target can move there
     *
     * @param map the current map being played
     * @param targetPoint the position of the target
     * @return a list of validated cells for the target ro go to
     */
    public ArrayList<Point> addValidatedPoints(Map map, Point targetPoint) {
        ArrayList<Point> possiblePoints = new ArrayList<>();

        // Add all valid points for moving to possiblePoints list
        MapDirectionValidator validate = new MapDirectionValidator();
        for(int row = 0; row < map.getScreen().length; row++) {
            for (int col = 0; col < map.getScreen()[row].length; col++) {
                if(validate.coordinateIsValidToMove(row, col, map, targetPoint)) {
                    Point validPoint = new Point(row, col);
                    possiblePoints.add(validPoint);
                }
            }
        }

        return possiblePoints;
    }

    /**
     * A method to get the point furthest away from the attacker out of the valid points
     * @param possiblePoints points the target can move
     * @param attackerPoint the position of the attacker
     * @return a point furthest away from the attacker
     */
    private Point getMaxDistance(ArrayList<Point> possiblePoints, Point attackerPoint) {
        Point max = possiblePoints.get(0);
        for(Point p : possiblePoints) {
            if((Math.abs(p.x - attackerPoint.x)) + Math.abs(p.y - attackerPoint.y) >
                    (Math.abs(max.x - attackerPoint.x)) + Math.abs(max.y - attackerPoint.y))
                max = p;
        }
        return max;
    }

    /**
     * A method where the character does nothing - frightened
     * @param attackingChar
     * @param attackedChar
     */
    @Override
    public void attack(Character attackingChar, Character attackedChar) {

    }

    /**
     * A method where the character does nothing = frightened
     * @param character
     * @param chestORbackpack
     */
    @Override
    public void interact(Character character, BackPackInventory chestORbackpack) {

    }
}
