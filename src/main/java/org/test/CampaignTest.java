package main.java.org.test;

import main.java.org.model.Campaign;
import main.java.org.model.Map;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Class to test Campaign.java.
 * The Map class has to be tested before this class can be tested. TODO
 *
 * @see main.java.org.model.Campaign
 * @author Freyja Jokulsdottir
 * @since 2017-02-17
 */
class CampaignTest {
    private Campaign camp;
    private Map map;
    private List<Map> levels;
    private String mapName;

    @BeforeEach
    void setUp() throws Exception {
        map = new Map();
        mapName = "SomeMap";
        levels = new ArrayList<Map>();
        camp = new Campaign(levels);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testGenerateCampaign() {

    }

    @Test
    void testGetLevels() {
        List<Map> levels2 = camp.getLevels();
        Assert.assertSame(levels, levels2);
    }

    @Test
    void testGetNumLevels() {
        int num = camp.getNumLevels();
        Assert.assertEquals(0, num);
    }

    @Test
    void testAddMap() {
        camp.addMap(mapName);
        int num = camp.getNumLevels();
        Assert.assertEquals(1, num);
    }

    @Test
    void testSetLevels() {
        List<Map> levels2 = new ArrayList<Map>();
        Campaign camp2 = new Campaign(levels2);
        camp2.setLevels(levels);
        Assert.assertSame(camp, camp2);
    }

}