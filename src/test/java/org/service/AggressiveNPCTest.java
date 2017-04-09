package test.java.org.service;

import main.java.org.Service.ObjectLoader;
import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Item;
import main.java.org.model.Map;
import main.java.org.model.StrategyPackage.AggressiveNPC;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.Fixtures;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.Assert.*;

/**
 * A class to test the AggressiveNPC strategy
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 09.04.2017
 */
public class AggressiveNPCTest {
    private Map map;
    private Character monster;
    private Character player;

    @Before
    public void setUp() throws Exception {
        this.map = new ObjectLoader().loadMapTest("StrategyTestMap");
        this.monster = new Character();
        AggressiveNPC aggressiveNPC = new AggressiveNPC();
        this.monster.setBehaviourStrategy(aggressiveNPC);
        this.player = new Character();
    }

    @After
    public void tearDown() throws Exception {
        this.map = null;
        this.monster = null;
        this.player = null;
    }

    /**
     * TODO!
     * @throws Exception
     */
    @Test
    public void execute() throws Exception {

    }

    /**
     * Check if the point moved to is an ideal point
     * @throws Exception
     */
    @Test
    public void move() throws Exception {
        monster.setCurrentPosition(new Point(2,2));
        player.setCurrentPosition(new Point(0,3));
        Point chest = new Point(0,1);
        Point newPoint = monster.getBehaviourStrategy().move(monster, player, chest, this.map);
        // acceptable moves
        Point idealPoint1 = new Point(0,2);
        Point idealPoint2 = new Point(1, 3);
        Assert.assertEquals(idealPoint1, newPoint);
    }

    @Test
    public void attack() throws Exception {

    }

    /**
     * Check if monster interact with chest method is working
     * @throws Exception
     */
    @Test
    public void interact() throws Exception {
        BackPackInventory backpack = new BackPackInventory();
        java.util.List<Item> items = new ArrayList<>();
        items.add(Fixtures.createBelt());
        items.add(Fixtures.createBoots());
        backpack.setItems(items);
        monster.setBackPackInventory(backpack);
        items = new ArrayList<>();
        items.add(Fixtures.createBelt2());
        backpack.setItems(items);
        monster.getBehaviourStrategy().interact(monster, backpack);
        items.add(Fixtures.createBelt());
        items.add(Fixtures.createBoots());
        backpack.setItems(items);
        Assert.assertEquals(backpack, monster.getBackPackInventory());
    }

}