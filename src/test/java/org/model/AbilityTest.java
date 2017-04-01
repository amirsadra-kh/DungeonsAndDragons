package test.java.org.model;

import main.java.org.model.CharacterPackage.Ability;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is to test the Ability Object.
 + *
 + * @author Parisa Nikzad
 + * @version 1.0
 + * @since 2017-02-22
 */
public class AbilityTest {
    Ability ability;

    @Before
    public void setUp() throws Exception {
        ability = new Ability();
    }

    @Test
    public void StrengthTest() {
        int score = ability.getStrength();
        System.out.println("strength is " + score);
    }

    @Test
    public void ConstitutionTest() {
        int score = ability.getConstitution();
        System.out.println("Constitution is " + score);
        System.out.println("Constitution is xxxxx " + ability.getConstitution());
    }

    @Test
    public void DexterityTest() {
        int score = ability.getDexterity();
        System.out.println("Dexterity is " + score);
        System.out.println("Dexterity isxxxx " + ability.getDexterity());
    }
}
