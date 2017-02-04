package main.java.org.model;

import java.util.List;

public class Turn {

    List<Character> turns;

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

    public void setTurnsInit(PlayerCharacter playerCharacter, List<Character> nonPlayerCharacters) {
        List<Character> turns = nonPlayerCharacters;
        turns.add(playerCharacter);
        this.turns = turns;
    }

}
