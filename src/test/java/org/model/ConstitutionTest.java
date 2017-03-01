package test.java.org.model;

import main.java.org.model.Modifier;
import main.java.org.model.Constitution;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test the Constitution class
 *
 * @see main.java.org.model.Constitution
 * @author Parisa Nikzad
 * @version 1.0
 * @since 20.02.2017
 */
public class ConstitutionTest {
    @Test
    public void getConstitutionTest() {
        Constitution constitution = new Constitution();
        assertNotNull(constitution);
        int value =  constitution.get();
        assertNotNull(value);

        int newValue = 14;
        constitution.set(newValue);
        assertTrue(newValue == constitution.get());

        Modifier modifier = new Modifier(constitution.get());
        assertTrue(modifier.get() == constitution.modifier());
    }
}