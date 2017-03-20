package test.java.org.model;

import main.java.org.Service.ObjectLoader;
import main.java.org.model.*;
import main.java.org.model.Character;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;


/**
 * Class to test the Character class which allows user to create and edit a Character
 *
 * @author Freyja Jokulsdottir
 * @version 2.0
 * @since 2017-03-19
 */
public class CharacterTest {
    private Character character;
    private BackPackInventory backpack;
    private Ability ability;

    /**
     * A method to set up before the tests.
     */
    @Before
    public void setUp() {
        this.character = new Character();
        this.character.newCharacter();
        this.backpack = new BackPackInventory();
        this.ability = new Ability();

        this.character.setAbility(this.ability);
    }

    /**
     * A method to tear down after the tests.
     */
    @After
    public void tearDown() {
        this.character = null;
        this.backpack = null;
        this.ability = null;
    }

    /**
     * A test method to get the current position of the character.
     * @throws Exception
     */
    @Test
    public void testGetCurrentPosition() throws Exception {
        Point current = new Point();
        current.setLocation(0, 0);
        this.character.setCurrentPosition(current);
        Assert.assertEquals(current, this.character.getCurrentPosition());
    }

    /**
     * A test method to get the ability of a character.
     * @throws Exception
     */
    @Test
    public void testGetAbility() throws Exception {
        Assert.assertEquals(this.ability, this.character.getAbility());
    }

    /**
     * A test method to get the level of a character
     * @throws Exception
     */
    @Test
    public void testGetLevel() throws Exception {
        // A character should start at level 1
        int level = 1;
        Assert.assertEquals(level, this.character.getLevel());
        // The level of a character can be edited
        level = 5;
        character.setLevel(level);
        Assert.assertEquals(level, this.character.getLevel());
    }

    /**
     * A test method to get the hitPoints of a character
     * @throws Exception
     */
    @Test
    public void testGetHitPoints() throws Exception {
        Strength strength = new Strength();
        int integerStrength  = this.ability.getStrength();
        strength.set(integerStrength);
        int hitPoints = strength.modifier() + this.character.dice;
        this.character.setHitPoints();
        Assert.assertEquals(hitPoints, this.character.getHitPoints());
    }

    /**
     * A test method to get the items the character is wearing
     * @throws Exception
     */
    @Test
    public void testGetItemsWearing() throws Exception {
        Item item = new Item();
        HashSet<Item> wearingItems = new HashSet<>();
        wearingItems.add(item.loadItem("belt1"));
        wearingItems.add(item.loadItem("boots1"));
        this.character.setItemsWearing(wearingItems);
        // Test if adding wearing items is successful
        Assert.assertEquals(wearingItems, this.character.getItemsWearing());
        // Test if two items of the same type can be added
        wearingItems.add(item.loadItem("boots1"));
        this.character.setItemsWearing(wearingItems);
        Assert.assertEquals(wearingItems, this.character.getItemsWearing());
    }

    @Test
    public void testGetBackPackInventory() throws Exception {

    }

    @Test
    public void testGetCharName() throws Exception {

    }

}