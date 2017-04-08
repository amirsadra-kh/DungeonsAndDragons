package main.java.org.model;

import main.java.org.Service.Calculation;
import main.java.org.model.CharacterPackage.Character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The game is implemented using a turn-based mechanism. When the player characters enters a map, all
 * characters (player and NPCs) are registered in a list using a d20 initiative roll to determine their order of play
 * during a round. Each round, each character (player or NPC) is given a  turn, during which it can (1) move 3 squares
 * (2) attack once (3) interact with other adjacent game elements.
 * This class and the setTurns method should be called every time a character enters a new map
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 08.04.2017
 */
public class TurnBasedMechanism {
    private Calculation roll = new Calculation();

    /**
     * A constructor for the turn-based mechanism
     */
    public TurnBasedMechanism() {}

    /**
     * A method to set the list of characters according to d20 rolls and their dexterity modifier.
     * @param characters on the map
     * @return characters in correct order
     */
    public List<Character> setTurns(List<Character> characters) {
        // Print out the order of the characters before sorting - For Log Window
        System.out.println("------------------------Log Window-------------------------");
        System.out.println(ColorConstants.ANSI_GREEN +"Characters before sorting: " +ColorConstants.ANSI_RESET);
        for(Character c : characters)
            System.out.println(ColorConstants.ANSI_PURPLE +c.getCharName() +ColorConstants.ANSI_RESET);


        System.out.println(ColorConstants.ANSI_GREEN +"Characters roll information: " +ColorConstants.ANSI_RESET);
        for(Character c : characters) {
            int d20 = roll.getDice20();
            // For Log Window
            System.out.println(ColorConstants.ANSI_PURPLE +c.getCharName() +" d20 roll: " +d20 +ColorConstants.ANSI_RESET);
            c.setTurnRoll(d20);
            // For Log Window
            System.out.println(ColorConstants.ANSI_PURPLE +c.getCharName() +" dexterity modifier: "
                    +c.getAbility().getDexterityModifier() +ColorConstants.ANSI_RESET);
            // For Log Window
            System.out.println(ColorConstants.ANSI_PURPLE +c.getCharName() +" roll added with the dexterity modifier: "
                    +c.getTurnRoll() +ColorConstants.ANSI_RESET);
        }

        // Sort the characters according to the value of the turnRoll (d20 + dexterityModifier)
        Collections.sort(characters, new Comparator<Character>() {
            @Override public int compare(Character c1, Character c2) {
                return c1.getTurnRoll() - c2.getTurnRoll(); // Ascending
            }
        });
        characters.sort(Comparator.comparingInt(Character::getTurnRoll));
        // Reverse the sorting so that it is from increasing to decreasing
        Collections.reverse(characters);

        // Print out the characters after sorting - For Log Window
        System.out.println(ColorConstants.ANSI_GREEN +"Characters after sorting: " +ColorConstants.ANSI_RESET);
        for(Character c : characters)
            System.out.println(ColorConstants.ANSI_PURPLE +c.getCharName()+ColorConstants.ANSI_RESET);
        System.out.println("------------------------------------------------------------");

        return characters;
    }
}
