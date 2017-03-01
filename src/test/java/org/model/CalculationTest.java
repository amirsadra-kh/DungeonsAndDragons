package test.java.org.model;

import main.java.org.Service.Calculation;
import org.junit.Test;

/**
 * A class to test the Calculation class
 *
 * @see main.java.org.Service.Calculation
 * @author Parisa Nikzad
 * @version 1.0
 * @since 20.02.2017
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
