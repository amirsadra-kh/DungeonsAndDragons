package test.java.org.model;

import main.java.org.model.CharacterPackage.Modifier;
import main.java.org.model.CharacterPackage.Strength;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test the Strength class
 *
 * @see Strength
 * @author Parisa Nikzad
 * @version 1.0
 * @since 20.02.2017
 */
public class StrengthTest {
    @Test
    public void getStrengthTest() {
        Strength strength = new Strength();
        assertNotNull(strength);
        int value =  strength.get();
        assertNotNull(value);

        int newValue = 14;
        strength.set(newValue);
        assertTrue(newValue == strength.get());

        Modifier modifier = new Modifier(strength.get());
        assertTrue(modifier.get() == strength.modifier());
    }
}