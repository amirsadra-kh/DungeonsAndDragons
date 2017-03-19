package main.java.org.model;

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
     * Constructor
     */
    public Strength() {
        Calculation score = new Calculation();
        this.strength = score.getCalculation();
    }

    @Override
    public int get() {
        return this.strength;
    }

    @Override
    public void set(int value) {
        this.strength = value;
    }

    @Override
    public int modifier(){
        Modifier modifier = new Modifier(this.strength);
        return modifier.get();
    }
}
