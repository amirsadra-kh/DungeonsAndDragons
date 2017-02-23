package test.java.org.model;

import main.java.org.model.Modifier;
import main.java.org.model.Dexterity;
import org.junit.Test;
import static org.junit.Assert.*;

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