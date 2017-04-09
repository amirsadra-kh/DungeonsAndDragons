package main.java.org.model.StrategyPackage;

import main.java.org.Service.MapDirectionValidator;
import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Map;

import java.awt.*;

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
    public void setUp(final int enhancement, final BehaviourStrategy previousStrategy, final Character attacker) {
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
    public Point move(final Character target, final Character player, final Point objective, final Map map) {
        // TODO Runaway implementation here, use this.attacker position and go in the other direction
        final Point attackerPoint = this.attacker.getCurrentPosition();
        final Point targetPoint = target.getCurrentPosition();

        final MapDirectionValidator validate = new MapDirectionValidator();
        return null;

    }

    /**
     * A method where the character does nothing - frightened
     * @param attackingChar
     * @param attackedChar
     */
    @Override
    public void attack(final Character attackingChar, final Character attackedChar) {

    }

    /**
     * A method where the character does nothing = frightened
     * @param character
     * @param chestORbackpack
     */
    @Override
    public void interact(final Character character, final BackPackInventory chestORbackpack) {

    }
}
