package main.java.org.model.CharacterPackage;

import main.java.org.Service.Calculation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observer;

/**
 * A AbilityScoreBuilder class for Character Type
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-21
 */
public abstract class AbilityScoreBuilder {
    // Ability generated
    protected Ability ability;

    // Scores generated using 4d6
    protected int dice1;
    protected int dice2;
    protected int dice3;
    protected int[] dices = new int[3];

    /**
     * Get the constructed Ability from the Builder
     * @return
     */
    public Ability getAbility() {
        return ability;
    }

    /**
     * Create a new unspecified Ability that will be
     * eventually build by calling the following abstract
     * method in a concrete class derived from the
     * Ability class
     */
    public void createNewAbility() {
        ability = new Ability();
        setDices();
        sortDices();
    }

    /**
     * An abstract method for building the ability according
     * to the fighter type.
     */
    abstract void buildAbilityScores();

    /**
     * A method to set the dice values with 4d6 rolls
     */
    private void setDices() {
        // Get dice values
        Calculation roll_4d6 = new Calculation();
        this.dice1 = roll_4d6.getCalculation();
        this.dice2 = roll_4d6.getCalculation();
        this.dice3 = roll_4d6.getCalculation();
        this.dices[0] = dice1;
        this.dices[1] = dice2;
        this.dices[2] = dice3;
    }

    /**
     * A method to add the three dices to an array and sort them
     * @return a sorted array
     */
    private void sortDices() {
        Arrays.sort(this.dices);
    }
}
