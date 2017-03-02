package test.java.org.service;

import main.java.org.Service.ObjectSaver;
import main.java.org.model.Campaign;
import main.java.org.model.Map;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test for class to allow user to create and edit Campaigns
 * A map called "SomeMap" must already be saved.
 *
 * @see main.java.org.model.Map
 * @see main.java.org.model.Campaign
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-02-25
 */
class CampaignTest {
    private Campaign camp;
    private Map map;
    private List<Map> levels;
    private String mapName;
    private ObjectSaver os = new ObjectSaver();

    @Before
    void setUp() {
        map = new Map();
        // This map has to be saved in map.txt
        mapName = "SomeMap";
        levels = new ArrayList<Map>();
        camp = new Campaign(levels);
        camp.setName("camp1");
        camp.setNumLevels(1);
        camp.addMap(mapName);
    }

    @After
    void tearDown() {
        camp = null;
        map = null;
        levels = null;
        mapName = null;
    }

    @Test
    void testGetLevels() {
        List<Map> levels2 = camp.getLevels();
        Assert.assertSame(levels, levels2);
    }

    @Test
    void testGetNumLevels() {
        int num = camp.getNumLevels();
        Assert.assertEquals(1, num);
    }

    @Test
    void testAddMap() {
        camp.addMap(mapName);
        int num = camp.getNumLevels();
        Assert.assertEquals(2, num);
    }

    @Test
    void testSetLevels() {
        camp.setLevels(levels);
        Assert.assertSame(levels, camp.getLevels());
    }

    @Test
    void testSetName() {
        camp.setName("camp1");
        Assert.assertSame("camp1", camp.getName());
    }

    @Test
    void testSetNumLevels() {
        camp.setNumLevels(0);
        Assert.assertEquals(0, camp.getNumLevels());
    }

    @Test
    void testGetCampaign() throws Exception {
        //Save a campaign
        camp.saveCampaign();
        camp.getCampaign(camp.getName());
    }

    @Test
    void testRemoveLevel() throws Exception {
        //Save a campaign
        String campName = camp.getName();
        camp.saveCampaign();
        camp = camp.getCampaign(camp.getName());
        levels = camp.getLevels();
        camp.removeLevel(levels);
        Assert.assertEquals(0, camp.getNumLevels());
    }

}