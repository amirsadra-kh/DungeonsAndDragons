package main.java.org.model.Character;

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
     * Constitution constructor
     */
    public Constitution() {
        Calculation score = new Calculation();
        this.constitution = score.getCalculation();
    }

    /**
     * A method to get the constitution
     *
     * @return the constitution as an integer.
     */
    @Override
    public int get() {
        return this.constitution;
    }

    /**
     * A method to set the constitution
     *
     * @param value the constitution as an integer to be set.
     */
    @Override
    public void set(int value) {
        this.constitution = value;
    }

    /**
     * A method to get the constitution modifier
     *
     * @return an integer constitution modifier
     */
    @Override
    public int modifier(){
        Modifier modifier = new Modifier(this.constitution);
        return modifier.get();
    }
}

