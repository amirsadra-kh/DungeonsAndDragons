package main.java.org.model.Character;

import java.util.Random;

/**
 * This class is sending random numbers according to the type of dice that has been selected.
 *
 * @author Parisa Nikzad
 * @version 1.0
 * @since 2017-02-06
 */
public class RollDice {
    private Random r = new Random();
    private int diceType;

    /**
     * Constructor
     *
     * @param diceType take the integer as the type of dice.
     */
    public RollDice(int diceType) {

        this.diceType = diceType;
    }

    /**
     * This method is setting the type of dice.
     *
     * @return diceType integer representing the Type of dice
     */
    public int getDiceType() {

        return diceType;
    }

    /**
     * This method send integer of dice roll.
     *
     * @return integer of the dice roll
     */
    public int roll() {
        return 1 + r.nextInt(diceType);
    }
}
