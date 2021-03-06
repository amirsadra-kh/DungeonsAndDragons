package test.java.org.model;

import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.DecoratorPackage.Pacifying;
import main.java.org.model.DecoratorPackage.Weapon;
import main.java.org.Service.StrategyPackage.BehaviourStrategy;
import main.java.org.Service.StrategyPackage.FriendlyNPC;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.Fixtures;

/**
 * A class to test the Pacifying decorator
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 09.04.2017
 */
public class PacifyingTest {
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
     * A method to test the pacifying decorator
     * @throws Exception
     */
    @Test
    public void testPacifyToFriendly() throws Exception {
        // GIVEN
        Weapon weapon = Fixtures.createPacifyingWeapon();

        // WHEN
        Pacifying pacify = new Pacifying(weapon);
        pacify.setPacifying(this.target);

        // THEN
        BehaviourStrategy friendly = new FriendlyNPC();
        Assert.assertEquals(friendly.toString(), this.target.getBehaviourStrategy().toString());
    }

}