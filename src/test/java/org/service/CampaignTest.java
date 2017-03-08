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

    }

    @After
    void tearDown() {

    }

    @Test
    void testGetLevels() {

    }

    @Test
    void testGetNumLevels() {

    }

    @Test
    void testAddMap() {

    }

    @Test
    void testSetLevels() {

    }

    @Test
    void testSetName() {

    }

    @Test
    void testSetNumLevels() {

    }

    @Test
    void testGetCampaign() throws Exception {

    }

    @Test
    void testRemoveLevel() throws Exception {

    }

}