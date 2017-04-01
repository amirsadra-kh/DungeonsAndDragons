package test.java.org.model;


import main.java.org.model.CharacterPackage.RollDice;
import org.junit.Test;

/**
 * A class to test the RollDice class
 *
 * @see RollDice
 * @author Parisa Nikzad
 * @version 1.0
 * @since 20.02.2017
 */
public class RollDiceTest {

    @Test
    public void RollTest()
    {
        int max = 20;
        RollDice dice = new RollDice(max);
        int type = dice.getDiceType();
        assert (type == max);

        int output = dice.roll();
        assert ((1 <= type) && (type <= max));
    }
}
