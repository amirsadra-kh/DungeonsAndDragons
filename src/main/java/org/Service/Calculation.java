package main.java.org.Service;

import main.java.org.model.RollDice;

import java.util.Arrays;

/**
 * This class is for calculating the result of rolling three dice for ability class
 * @author Parisa Nikzad
 * @version 1.0.0
 * @since 2017-02-20
 */
public class Calculation {
    int [] diceResult = new int [4];


    public int getCalculation(){
        RollDice dice6 = new RollDice(6);

        for (int i = 0; i < 4; i++) {
            this.diceResult[i] = dice6.roll();
        }

        Arrays.sort(this.diceResult);
        return this.diceResult[1] + this.diceResult[2] + this.diceResult[3];
    }

}