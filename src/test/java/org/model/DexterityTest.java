package test.java.org.model;

import main.java.org.model.CharacterPackage.Modifier;
import main.java.org.model.CharacterPackage.Dexterity;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test the Dexterity class
 *
 * @see Dexterity
 * @author Parisa Nikzad
 * @version 1.0
 * @since 20.02.2017
 */
public class DexterityTest {
    @Test
    public void getConstitutionTest() {
        Dexterity dexterity = new Dexterity();
        assertNotNull(dexterity);
        int value =  dexterity.get();
        assertNotNull(value);

        int newValue = 14;
        dexterity.set(newValue);
        assertTrue(newValue == dexterity.get());

        Modifier modifier = new Modifier(dexterity.get());
        assertTrue(modifier.get() == dexterity.modifier());
    }
}