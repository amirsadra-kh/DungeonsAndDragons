package main.java.org.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is taking care of the turns in a map
 * Please Note : the the NEXT Character is always located at last index
 * + *
 * + * @author Maysam Mokarian
 * + * @version 1.0
 * + * @since 2017-02-08
 */
public class Turn {

    List<Character> turns;

    public Turn(List<Character> nonPlayerCharacters, Character character) {
        List<Character> characters = new ArrayList<>();
        if (character != null ) {
            characters.addAll(nonPlayerCharacters);
            characters.add(character);

        }
        this.turns = characters;
    }

    public Character getActorTurnAndAdjustListOfTurns() {

        Character character = this.turns.get(turns.size() - 1);
        turns.remove(turns.size() - 1);
        turns.add(0, character);

        return character;
    }

    public List<Character> getTurns() {
        return turns;
    }

    public void setTurns(List<Character> turns) {
        this.turns = turns;
    }


}
