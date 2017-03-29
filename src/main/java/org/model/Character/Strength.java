package main.java.org.model.Character;

import main.java.org.Service.Calculation;

/**
 * This class represent a strength object and implement AbilityInterface
 * @author Parisa Nikzad
 * @version 1.0.0
 * @since 2017-02-22
 */
public class Strength implements AbilityInterface {
    protected int strength;

    /**
     * Strength constructor
     */
    public Strength() {
        Calculation score = new Calculation();
        this.strength = score.getCalculation();
    }

    /**
     * A method to get the strength
     *
     * @return the strength as an integer.
     */
    @Override
    public int get() {
        return this.strength;
    }

    /**
     * A method to set the strength
     *
     * @param value the strength as an integer to be set.
     */
    @Override
    public void set(int value) {
        this.strength = value;
    }

    /**
     * A method to get the strength modifier
     *
     * @return an integer strength modifier
     */
    @Override
    public int modifier(){
        Modifier modifier = new Modifier(this.strength);
        return modifier.get();
    }
}
