package main.java.org.model.StrategyPackage;

import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;

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
     */
    public void move(Character character, Character player, Point objective) {

    }

    @Override
    public void attack(Character attackingChar, Character attackedChar) {

    }

    @Override
    public void interact(Character character, BackPackInventory chestORbackpack) {

    }
}
