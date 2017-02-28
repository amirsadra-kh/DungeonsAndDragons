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
            diceResult[i] = dice6.roll();
        }
        //System.out.println(java.util.Arrays.toString(diceResult));
        Arrays.sort(diceResult);
        return diceResult[1] + diceResult[2] + diceResult[3];
    }

}
