package main.java.org.model.StrategyPackage;

import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Map;

import java.awt.*;

/**
 * This strategy is for enemy NPCs. An aggressive NPC will always run toward the
 * player character and attack it. If it comes near a chest or other NPC while doing
 * so, it will loot the chest and attack the NPC.
 *
 * @author
 * @version
 * @since
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
    public Point move(final Character character, final Character player, final Point objective, final Map map) {
        return null;
    }

    /**
     * The aggressive character will attack when possible
     * @param mon
     * @param attackedChar
     */
    @Override
    public void attack(final Character mon, final Character attackedChar) {

    }

    /**
     * The aggressive character can loot a chest
     * @param mon
     * @param chest
     */
    @Override
    public void interact(final Character mon, final BackPackInventory chest) {

    }
}
