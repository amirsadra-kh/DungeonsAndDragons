package test.java.org.service;

import main.java.org.Service.ObjectLoader;
import main.java.org.model.CharacterPackage.Ability;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Item;
import main.java.org.model.Map;
import main.java.org.Service.StrategyPackage.AggressiveNPC;
import main.java.org.Service.StrategyPackage.FrighteningStrategy;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.Fixtures;

import java.awt.*;
import java.util.HashSet;

/**
 * A class to test the Frightening strategy
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 09.04.2017
 */
public class FrighteningStrategyTest {
    private Map map;
    private Character attacker;
    private Character target;
    private FrighteningStrategy frighteningStrategy = new FrighteningStrategy();

    /**
     * Setup tests
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        this.map = new ObjectLoader().loadMapTest("StrategyTestMap");
        this.attacker = new Character();
        this.target = new Character();
        Ability ability = new Ability();
        attacker.setAbility(ability);
        HashSet<Item> wearing = new HashSet<Item>();
        wearing.add(Fixtures.createWeapon());
        this.attacker.setItemsWearing(wearing);

        AggressiveNPC aggressiveNPC = new AggressiveNPC();
        this.target.setBehaviourStrategy(aggressiveNPC);

        frighteningStrategy.setUp(Fixtures.createWeapon().getEnhance(), this.target.getBehaviourStrategy(), this.attacker);
    }

    /**
     * Teardown tests
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        this.map = null;
        this.attacker = null;
        this.target = null;
    }

    /**
     * A method to check if the target moves as far away as possible from the attacker
     * @throws Exception
     */
    @Test
    public void testMoveAwayFromAttacker() throws Exception {
        // GIVEN
        target.setCurrentPosition(new Point(2,2));
        attacker.setCurrentPosition(new Point(3,0));
        Point chest = new Point(1,0);
        this.target.setBehaviourStrategy(frighteningStrategy);

        // WHEN
        Point newPoint = this.target.getBehaviourStrategy().move(target, attacker, chest, this.map, null);

        // THEN
        // acceptable moves
        Point idealPoint1 = new Point(0,2);
        Assert.assertEquals(idealPoint1, newPoint);
    }

    /**
     * A test method to check if the character goes back to normal behaviour after turns are up
     */
    @Test
    public void testBackToNormalStrategy() {
        // GIVEN
        target.setCurrentPosition(new Point(2,2));
        attacker.setCurrentPosition(new Point(3,0));
        Point chest = new Point(1,0);
        this.target.setBehaviourStrategy(frighteningStrategy);

        // WHEN
        // Target gets 2 more turns -> enhance = 2
        Point newPoint = this.target.getBehaviourStrategy().move(target, attacker, chest, this.map, null);
        target.setCurrentPosition(newPoint);
        newPoint = this.target.getBehaviourStrategy().move(target, attacker, chest, this.map, null);
        target.setCurrentPosition(newPoint);
        //newPoint = this.target.getBehaviourStrategy().move(target, attacker, chest, this.map, null);
        target.setCurrentPosition(newPoint);

        // THEN
        // target is back to being aggressive and will move towards player
        Point idealPoint = new Point(2, 1);
        //Assert.assertEquals(idealPoint, newPoint);
    }
}