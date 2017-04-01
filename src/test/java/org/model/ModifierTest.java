package test.java.org.model;

import main.java.org.model.CharacterPackage.Modifier;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * A class to test the Modifier class
 *
 * @see Modifier
 * @author Parisa Nikzad
 * @version 1.0
 * @since 20.02.2017
 */
public class ModifierTest {
    @Test
    public void getModifierTest() {
        Modifier modifier = new Modifier(18);
        int m = modifier.get();
        assertTrue( m == 4);

        Modifier modifier2 = new Modifier(9);
        int m2 = modifier2.get();
        assertTrue(m2 == 0);

        Modifier modifier3 = new Modifier(11);
        int m3 = modifier3.get();
        assertTrue(m3 == 1);
    }
}