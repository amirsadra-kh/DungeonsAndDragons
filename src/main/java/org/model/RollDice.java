package main.java.org.model;

import java.util.Random;

/**
 * This class is sending random numbers according to the type of dice that has been selected.
 */
public class RollDice {
    private Random r= new Random();
    private int diceType;
    private int result;

    /**
     * This method is setting the type of dice.
     * @param  diceType integer representing the Type of dice
     */
    public void setRollDice(int diceType){

        this.diceType = diceType;
    }

    /**
     * This method is sending the result of rolling dice.
     * @return  result return the integer of the dice roll
     */
    public int getRollDice() {
        result = 1 + r.nextInt(diceType);
        return result;
    }
}
