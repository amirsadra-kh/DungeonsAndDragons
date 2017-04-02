package main.java.org.model.DecoratorPackage;

import main.java.org.model.CharacterPackage.Character;

/**
 * A strategy for the Frightening decorator
 * Target runs away from character for a number of turns
 * equal to the enchantment bonus of the weapon.
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 02.04.2017
 */
public class FrighteningStrategy implements CharacterStrategy {
    /**
     * A method for running away from character with the weapon
     * for as many turns as the enhancement of the weapon has.
     *
     * @param character
     * @param enhancement
     */
    @Override
    public void execute(Character character, int enhancement) {
        int turns = enhancement;
        while(turns > 0) {
            // Run away from character here
            turns--; // Should only decrease each time the character has finished a turn
        }
    }
}
