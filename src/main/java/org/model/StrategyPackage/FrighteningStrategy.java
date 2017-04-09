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
    public void move(Character target, Character player, Point objective, Map map) {
        // TODO Runaway implementation here, use this.attacker position and go in the other direction
        Point attackerPoint = this.attacker.getCurrentPosition();
        Point targetPoint = target.getCurrentPosition();
        ArrayList<Point> possiblePoints = new ArrayList<>();

        // Add all valid points for moving to possiblePoints list
        MapDirectionValidator validate = new MapDirectionValidator();
        for(int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getCols(); col++) {
                if(validate.coordinateIsValidToMove(row, col, map, targetPoint)) {
                    Point validPoint = new Point(row, col);
                    possiblePoints.add(validPoint);
                }
            }
        }

        // Find a point furthest away from attacker
        Point max = possiblePoints.get(0);
        for(Point p : possiblePoints) {
            if((Math.abs(p.x - attackerPoint.x)) + Math.abs(p.y - attackerPoint.y) >
                    (Math.abs(max.x - attackerPoint.x)) + Math.abs(max.y - attackerPoint.y))
                max = p;
        }

        // Set the new position
        target.setCurrentPosition(max);
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
