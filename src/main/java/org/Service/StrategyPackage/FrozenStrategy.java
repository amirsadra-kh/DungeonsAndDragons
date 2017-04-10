package main.java.org.Service.StrategyPackage;

import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Map;

import java.awt.*;

/**
 * A strategy for the Freezing Decorator
 * Target cannot move for a number of turns equal to the enhancement
 * bonus of the weapon.
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 02.04.2017
 */
public class FrozenStrategy implements BehaviourStrategy {
    private int turns = 0;
    private BehaviourStrategy previousStrategy;

    /**
     * A method to set up the frozen strategy number of turns and previous strategy of the target
     * @param enhancement
     * @param previousStrategy
     */
    public void setUp(final int enhancement, final BehaviourStrategy previousStrategy) {
        this.turns = enhancement;
        this.previousStrategy = previousStrategy;
    }

    @Override
    public void execute() {
    }

    /**
     * A method where the character does nothing - frozen
     *
     * @param target the one who has a turn now and has been hit with a frozen enhancement
     * @param player the player character of the map
     * @param objective the position of the objective of the map - chest or exit
     * @param map the map the character is on
     */
    @Override
    public Point move(final Character target, final Character player, final Point objective, final Map map) {
        // decrease turns everytime it is the character's turn
        if(turns > 0) {
            turns--;
        } else {
            // Set the strategy of the character back to normal because the turns are finished
            target.setBehaviourStrategy(previousStrategy);
        }
        return null;

    }

    /**
     * A method where the character does nothing - frozen
     *
     * @param target the one who has a turn now and has been hit with a frozen enhancement
     * @param attackedChar a possible character to attack
     */
    @Override
    public void attack(final Character target, final Character attackedChar) {

    }

    /**
     * A method where the character does nothing - frozen
     *
     * @param target the one who has a turn now and has been hit with a frozen enhancement
     * @param chestORbackpack a possible chest or backpack to interact with
     */
    @Override
    public void interact(final Character target, final BackPackInventory chestORbackpack) {

    }
}
