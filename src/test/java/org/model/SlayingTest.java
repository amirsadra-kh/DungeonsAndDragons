package test.java.org.model;

import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.DecoratorPackage.Slaying;
import main.java.org.model.DecoratorPackage.Weapon;
import main.java.org.model.StrategyPackage.BehaviourStrategy;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.Fixtures;

import static org.junit.Assert.*;

/**
 * A class to test the Slaying decorator
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 09.04.2017
 */
public class SlayingTest {
    private Character target;

    /**
     * Setup tests
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        this.target = new Character();
    }

    /**
     * Tear down tests
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        this.target = null;
    }

    /**
     * A method to check if the slaying decorator kills the target
     * @throws Exception
     */
    @Test
    public void testSlayingToKill() throws Exception {
        // GIVEN
        Weapon weapon = Fixtures.createSlayingWeapon();

        // WHEN
        Slaying slay = new Slaying(weapon);
        slay.slay(this.target);

        // THEN
        int hitpoints = 0;
        Assert.assertEquals(hitpoints, this.target.getHitPoints());
    }

}