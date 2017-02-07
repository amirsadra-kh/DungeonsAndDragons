package main.java.org.model;

import java.util.ArrayList;
import java.util.List;

public class Turn {

    List<Character> turns;

    public Turn(List<Character> nonPlayerCharacters, Character character) {
        List<Character> characters= new ArrayList<>();
        characters.add(character);
        characters.addAll(nonPlayerCharacters);
        this.turns = characters;
    }

    Character getActorTurnAndAdjustListOfTurns(){

        Character character= this.turns.get(turns.size()-1);
        turns.remove(turns.size()-1);
        turns.add(0,character);

        return  character;
    }

    public List<Character> getTurns() {
        return turns;
    }

    public void setTurns(List<Character> turns) {
        this.turns = turns;
    }


}
