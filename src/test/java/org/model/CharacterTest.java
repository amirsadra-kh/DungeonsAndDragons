package test.java.org.model;

import main.java.org.model.*;
import main.java.org.model.CharacterPackage.Ability;
import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.CharacterPackage.Strength;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import test.Fixtures;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Class to test the CharacterPackage class which allows user to create and edit a CharacterPackage
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
     * TODO fix this method
     * @throws Exception
     */
    @Test
    public void testGetItemsWearing() throws Exception {
        // Create a belt
        Item item1 = Fixtures.createBelt();
        // Create boots
        Item item2 = Fixtures.createBoots();
        // Create another belt
        Item item3 = Fixtures.createBelt2();

        HashSet<Item> wearingItems = new HashSet<>();
        wearingItems.add(item1);
        wearingItems.add(item2);
        this.character.setItemsWearing(wearingItems);

        // Test if adding wearing items is successful
        Assert.assertEquals(wearingItems, this.character.getItemsWearing());

        // Test if two items of the same type can be added
        wearingItems.add(item2);
        wearingItems.add(item3);
        this.character.setItemsWearing(wearingItems);
        // Since boots1 == boots1 and belt1 has the same itemEnum as belt2, there should be only two items
        int size = 2;
        Assert.assertEquals(size, this.character.getItemsWearing().size());
    }

    /**
     * A test method to test the backpack inventory of a character.
     * @throws Exception
     */
    @Test
    public void testGetBackPackInventory() throws Exception {
        Item item = new Item();
        BackPackInventory backPackInventory = new BackPackInventory();
        List<Item> backpack = new ArrayList<>();
        backpack.add(item.loadItem("belt1"));
        backpack.add(item.loadItem("boots1"));
        // Check if items are added to the backpack
        backPackInventory.setItems(backpack);
        this.character.setBackPackInventory(backPackInventory);
        Assert.assertEquals(backpack, character.getBackPackInventory());

        // Check if multiple items of the same type can be added
        backpack.add(item.loadItem("belt2"));
        backpack.add(item.loadItem("boots1"));
        backPackInventory.setItems(backpack);
        this.character.setBackPackInventory(backPackInventory);
        // Backpack allows many items of same type and the same item multiple times therefore there should be 4 items
        int size = 4;
        Assert.assertEquals(size, this.character.getBackPackInventoryItems().size());
    }

    /**
     * A test method to test character name setting and getting
     * @throws Exception
     */
    @Test
    public void testGetCharName() throws Exception {
        String name = "char1";
        character.setCharName(name);
        Assert.assertEquals(name, character.getCharName());
    }

}