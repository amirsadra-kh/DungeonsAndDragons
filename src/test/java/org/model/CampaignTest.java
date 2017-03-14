package test.java.org.model;

import main.java.org.model.Campaign;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to test the Campaign class which allows user to create and edit Campaign
 *
 * @author Freyja Jokulsdottir
 * @version 2.0
 * @since 2017-02-05
 */
public class CampaignTest {
    private Campaign camp;
    private List<String> mapNames;

    @Before
    public void setUp() {
        this.camp = new Campaign();
        this.mapNames = new ArrayList<>();
    }

    @After
    public void tearDown() {
        this.camp = null;
        this.mapNames = null;
    }

    @Test
    public void testSetName() {
        String name = "camp1";
        camp.setName(name);
        Assert.assertEquals(name, camp.getName());
    }

    @Test
    public void testSetNumLevels() {
        int num = 2;
        camp.setNumLevels(num);
        Assert.assertEquals(num, camp.getNumLevels());
    }

    @Test
    public void testSetMapNames() {
        String mapName = "map1";
        mapNames.add(mapName);
        camp.setMapNames(mapName);
        Assert.assertEquals(mapNames, camp.getMapNames());
    }

    @Test
    public void testRemoveLevel() {
        int num = 2;
        camp.setNumLevels(num);
        mapNames.add("map1");
        camp.removeLevel(mapNames);
        Assert.assertEquals(num-1, camp.getNumLevels());
    }
}