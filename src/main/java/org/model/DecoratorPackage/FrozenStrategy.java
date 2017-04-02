package main.java.org.model.DecoratorPackage;

import main.java.org.model.CharacterPackage.Character;

/**
 * A strategy for the Freezing Decorator
 * Target cannot move for a number of turns equal to the enhancement
 * bonus of the weapon.
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 02.04.2017
 */
public class FrozenStrategy implements CharacterStrategy {
    @Override
    public void execute(Character character, int enhancement) {
        int turns = enhancement;
        while(turns > 0) {
            // character does nothing
            turns--; // Should only decrease each time the character has finished a turn
        }
    }
}
