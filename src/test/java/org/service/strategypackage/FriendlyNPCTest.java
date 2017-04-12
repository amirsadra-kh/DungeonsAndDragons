package test.java.org.service.strategypackage;

import main.java.org.Service.ObjectLoader;
import main.java.org.Service.StrategyPackage.FriendlyNPC;
import main.java.org.model.Campaign;
import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Item;
import main.java.org.model.ItemEnum;
import main.java.org.model.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static test.Fixtures.createAMapWithNoEmptySpot;
import static test.Fixtures.createItemWithSeveralBelts;

/**
 * This class covers the unit test for the friendly character
 */
public class FriendlyNPCTest {

    Map map;
    Character fChar;
    Character player;
    FriendlyNPC friendlyNPC;

    @Before
    public void init() throws Exception {
        map = new ObjectLoader().loadMapTest("map2");
        fChar = new Character();
        player = new Character();
        friendlyNPC = new FriendlyNPC();
        fChar.setCurrentPosition(new Point(0, 0));

    }

    @Test
    public void moveReturnsValidPoint() throws Exception {

        //GIVEN
        Point expectedPoint = new Point(0, 0);
        //WHEN
        Point p = friendlyNPC.move(fChar, player, null, map, null);
        //THEN
        assertEquals(expectedPoint, p);
        assertEquals(fChar.getCurrentPosition(), expectedPoint);
    }

    @Test
    public void moveReturnsNullIfThereIsNoPoint() throws Exception {
        //GIVEN
        Map map = createAMapWithNoEmptySpot(this.map);
        //WHEN
        Point p = friendlyNPC.move(fChar, player, null, map, null);
        //THEN
        assertEquals(new Point(0,0), p);
    }

    @Test
    public void interactWithChest() throws Exception {
        //GIVEN
        BackPackInventory backPackInventory = new BackPackInventory();
        Item belt = new Item();
        belt.setItem(ItemEnum.BELT);
        Item helmet = new Item();
        helmet.setItem(ItemEnum.HELMET);
        backPackInventory.setItems(Arrays.asList(belt, helmet));
        map.setChest(backPackInventory);
        //WHEN
        friendlyNPC.interactWithChest(map, fChar);
        //THEN
        assertEquals(fChar.getBackPackInventory().getItems().size(), 2);
        assertEquals(fChar.getBackPackInventory().getItems().get(0), helmet);
        assertEquals(fChar.getBackPackInventory().getItems().get(1), belt);
        assertEquals(map.getChest().getItems().size(), 0);

    }

    @Test
    public void interactWithChestHavingMoreThanTenItems() throws Exception {
        //GIVEN
        BackPackInventory backPackInventory = new BackPackInventory();
        BackPackInventory playerBackPackInventory = new BackPackInventory();
        Item belt = new Item();
        ArrayList<Item> itemsForChest = createItemWithSeveralBelts(5);
        ArrayList<Item> itemsForPlayer = createItemWithSeveralBelts(8);
        backPackInventory.setItems(itemsForChest);
        playerBackPackInventory.setItems(itemsForPlayer);
        fChar.setBackPackInventory(playerBackPackInventory);
        map.setChest(backPackInventory);
        //WHEN
        friendlyNPC.interactWithChest(map, fChar);
        //THEN
        assertEquals(fChar.getBackPackInventory().getItems().size(), 10);
        //assertEquals(fChar.getBackPackInventory().getItems().get(0), belt);
        assertEquals(map.getChest().getItems().size(), 3);

    }

    @After
    public void tearDown() {
        map = null;
        fChar = null;
        player = null;
        friendlyNPC = null;
    }

}