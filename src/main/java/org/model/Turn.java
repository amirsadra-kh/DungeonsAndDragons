package main.java.org.model;

import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is taking care of the turns in a map
 * Please Note : the the NEXT Character is always located at last index
 *
 * @author Maysam Mokarian
 * @version 1.0
 * @since 2017-02-08
 */
public class Turn {

    List<Character> turns;

    /**
     * A method for determining whos turn it is
     *
     * @param nonPlayerCharacters
     * @param character
     */
    public Turn(List<Character> nonPlayerCharacters, Character character) {
        List<Character> characters = new ArrayList<>();
        if (character != null ) {
            characters.addAll(nonPlayerCharacters);
            characters.add(character);

        }
        this.turns = characters;
    }

    /**
     * A method fot getting the actor whos turn it is and the list of turns
     *
     * @return a character object
     */
    public Character getActorTurnAndAdjustListOfTurns() {

        Character character = this.turns.get(turns.size() - 1);
        turns.remove(turns.size() - 1);
        turns.add(0, character);

        return character;
    }

    /**
     * Get the list of turns
     *
     * @return a list of turns
     */
    public List<Character> getTurns() {
        return turns;
    }

    /**
     * Set the list of turns
     *
     * @param turns to set the list of turns
     */
    public void setTurns(List<Character> turns) {
        this.turns = turns;
    }

    /**
     * A method for moving a character when it is their turn.
     * @param character the one who has a turn now
     * @param player the player character of the map
     * @param objective the position of the objective of the map - chest or exit
     */
    public static void move(Character character, Character player, Point objective) {
        //TODO add switch case here based on behaviour strategy
        // Friendly NPC moves randomly
        MoveMethods.friendlyMove(character);
        // Player gets a choice
        MoveMethods.playerMove(character);
        // Computer player moves towards the objective of the map or exit if objective is done
        MoveMethods.computerPlayerMove(character, objective);
        // Aggressive NPC moves towards player
        MoveMethods.hostileMove(character, player);
    }

    public static void interact(Character character, BackPackInventory chestORbackpack) {

    }

    public static void attack(Character attackingChar, Character attackedChar) {
        // if the character is friendly - do nothing
        // Player gets a choice
        // Computer player attacks if that is the objective of the map
        // Aggressive NPC attacks if possible
    }
}
