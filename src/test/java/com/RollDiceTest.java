package test.java.com;

import main.java.org.model.RollDice;


import org.junit.*;
import static org.junit.Assert.*;

public class RollDiceTest {

    public void RollTest()
    {
        RollDice test = new RollDice();
        test.setRollDice(6);
        int output = test.getRollDice();
        assert ((1 <= output) && (output <= 6));
    }
}
