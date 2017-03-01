package main.java.org.model;

import main.java.org.Service.Calculation;

/**
 * This class represent a constitution object and implement AbilityInterface
 *
 * @author Parisa Nikzad
 * @version 1.0.0
 * @since 2017-02-22
 */
public class Constitution implements AbilityInterface {
    protected int constitution;

    /**
     * Constructor
     */
    public Constitution() {
        Calculation score = new Calculation();
        this.constitution = score.getCalculation();
    }

    @Override
    public int get() {
        return this.constitution;
    }

    @Override
    public void set(int value) {
        this.constitution = value;
    }

    @Override
    public int modifier(){
        Modifier modifier = new Modifier(this.constitution);
        return modifier.get();
    }
}

