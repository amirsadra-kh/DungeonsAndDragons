package main.java.org.model.CharacterPackage;

import main.java.org.Service.Calculation;

import java.util.ArrayList;
import java.util.Observer;

/**
 * A Builder class for CharacterPackage Type
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-21
 */
public class Builder {
    // Needed variables
    private String fighterType;
    private String charName;

    // Ability generated
    private Ability ability;

    // Scores generated using 4d6
    private int dice1;
    private int dice2;
    private int dice3;

    // A base line for the hit points
    RollDice dice10 = new RollDice(10);
    public int dice = dice10.roll();
    private int hitPoints = dice;

    // For the observer
    private java.util.List<Observer> observers = new ArrayList<>();
    private Ability state;

    /**
     * A CharacterPackage builder
     *
     * @param name
     */
    public Builder(String fighterType, String name) {
        this.fighterType = fighterType.toLowerCase();
        this.charName = name;
        this.ability = new Ability();

        setDices();

        if(fighterType.equals("bully"))
            Bully();
        else if(fighterType.equals("nimble"))
            Nimble();
        else if(fighterType.equals("tank"))
            Tank();
    }

    /**
     * A Bully builder
     * Ability Scores in decreasing order of importance:
     * Strength, Dexterity, Constitution
     * @return return this type
     */
    public Builder Bully() {
        int[] sortedDices = sortDices();
        this.ability.setConstitution(sortedDices[0]);
        this.ability.setDexterity(sortedDices[1]);
        this.ability.setStrength(sortedDices[2]);
        return this;
    }

    /**
     * A Nimble builder
     * Ability Scores in decreasing order of importance:
     * Dexterity, Constitution, Strength
     * @return return this type
     */
    public Builder Nimble() {
        int[] sortedDices = sortDices();
        this.ability.setConstitution(sortedDices[1]);
        this.ability.setDexterity(sortedDices[2]);
        this.ability.setStrength(sortedDices[0]);
        return this;
    }

    /**
     * A Tank builder
     * Ability Scores in decreasing order of importance:
     * Constitution, Dexterity, Strength
     * @return return this type
     */
    public Builder Tank() {
        int[] sortedDices = sortDices();
        this.ability.setConstitution(sortedDices[2]);
        this.ability.setDexterity(sortedDices[1]);
        this.ability.setStrength(sortedDices[0]);
        return this;
    }

    /**
     * A method to set the dice values with 4d6 rolls
     */
    private void setDices() {
        // Get dice values
        Calculation roll_4d6 = new Calculation();
        this.dice1 = roll_4d6.getCalculation();
        this.dice2 = roll_4d6.getCalculation();
        this.dice3 = roll_4d6.getCalculation();
    }

    /**
     * A method to add the three dices to an array and sort them
     * @return a sorted array
     */
    private int[] sortDices() {
        int[] dices = {this.dice1, this.dice2, this.dice3};
        if (dices[0] > dices[1]) {
            int temp = dices[0];
            dices[0] = dices[1];
            dices[1] = temp;
        }
        if (dices[1] > dices[2]) {
            int temp = dices[1];
            dices[1] = dices[2];
            dices[2] = temp;
        }
        if (dices[0] > dices[1]) {
            int temp = dices[0];
            dices[0] = dices[1];
            dices[1] = temp;
        }

        return dices;
    }

    /**
     * A methid to get the character's ability
     * @return ability
     */
    public Ability getAbility() {
        return this.ability;
    }

    /**
     * A getter for charName
     * @return
     */
    public String getName() {
        return this.charName;
    }

    /**
     * A getter for the fighterType
     * @return
     */
    public String getFighterType() {
        return this.fighterType;
    }
}
