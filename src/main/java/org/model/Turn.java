package main.java.org.model;

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


}
