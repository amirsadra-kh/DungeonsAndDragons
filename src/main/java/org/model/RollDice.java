package main.java.org.model;

import java.util.Random;

/**
 * This class is sending random numbers according to the type of dice that has been selected.
 */
public class RollDice {
    private Random r = new Random();
    private int diceType;

    public RollDice(int diceType) {
        this.diceType = diceType;
    }

    /**
     * This method is setting the type of dice.
     *
     * @param  diceType integer representing the Type of dice
     */
    public int getDiceType() {
        return diceType;
    }

    /**
     * This method is sending the result of rolling dice.
     *
     * @return integer of the dice roll
     */
    public int roll()
    {
        System.out.println(1 + r.nextInt(diceType));
        return 1 + r.nextInt(diceType);
    }
}
