package test.java.org.model;

import main.java.org.model.Calculation;
import org.junit.Test;

/**
 * Created by Parisa Nikzad on 2/20/2017.
 */
public class CalculationTest {
    Calculation calculation = new Calculation();

    @Test
    public void getCalculationTest(){
        int type = calculation.getCalculation();
        assert ((3 <= type) && (type <= 18));
        System.out.println("getCalculation" + type);
    }
}
