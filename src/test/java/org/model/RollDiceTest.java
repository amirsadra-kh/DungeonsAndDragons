package test.java.org.model;


import main.java.org.model.RollDice;
import org.junit.Test;


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
