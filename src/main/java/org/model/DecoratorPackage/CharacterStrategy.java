package main.java.org.model.DecoratorPackage;

import main.java.org.model.CharacterPackage.Character;

/**
 * The classes that implement a concrete strategy should implement this.
 * The Character class uses this to use a concrete strategy for Frozen and Frightening
 * A Character Strategy interface for FrozenStrategy and FrighteningStrategy
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 02.04.2017
 */
public interface CharacterStrategy {
    /**
     * Method whose implementation varies depending on the strategy adopted.
     */
    void execute(Character character, int enhancement);
}
