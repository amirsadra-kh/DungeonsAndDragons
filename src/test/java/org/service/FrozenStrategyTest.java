package test.java.org.service;

import main.java.org.Service.ObjectLoader;
import main.java.org.model.CharacterPackage.Ability;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Item;
import main.java.org.model.Map;
import main.java.org.model.StrategyPackage.AggressiveNPC;
import main.java.org.model.StrategyPackage.FrozenStrategy;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.Fixtures;

import java.awt.*;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * A class to test the Frozen strategy
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 09.04.2017
 */
public class FrozenStrategyTest {
    private Map map;
    private Character attacker;
    private Character target;
    private FrozenStrategy frozenStrategy = new FrozenStrategy();

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
        wearing.add(Fixtures.createFrozenWeapon());
        this.attacker.setItemsWearing(wearing);

        AggressiveNPC aggressiveNPC = new AggressiveNPC();
        this.target.setBehaviourStrategy(aggressiveNPC);

        frozenStrategy.setUp(Fixtures.createFrozenWeapon().getEnhance(), this.target.getBehaviourStrategy());
    }

    /**
     * Tear down tests
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        this.map = null;
        this.attacker = null;
        this.target = null;
    }

    @Test
    public void move() throws Exception {
        target.setCurrentPosition(new Point(2, 2));
        attacker.setCurrentPosition(new Point(0, 3));
        Point chest = new Point(0, 1);
        this.target.setBehaviourStrategy(frozenStrategy);
        Point newPoint = this.target.getBehaviourStrategy().move(target, attacker, chest, this.map);

        // acceptable moves
        Point samePoint = new Point(2, 2);
        Assert.assertEquals(samePoint, newPoint);

        // Target gets 2 more turns - enhance = 2
        newPoint = this.target.getBehaviourStrategy().move(target, attacker, chest, this.map);
        newPoint = this.target.getBehaviourStrategy().move(target, attacker, chest, this.map);
        // target is back to being aggressive and will move towards player
        Point idealPoint = new Point(0, 2);
        Assert.assertEquals(idealPoint, newPoint);
    }
}