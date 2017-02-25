package main.java.org.test;

import main.java.org.Service.ObjectLoader;
import main.java.org.Service.ObjectSaver;
import main.java.org.model.Campaign;
import main.java.org.model.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Class to test CampaignScreen.java.
 * The Campaign class has to be tested before this class can be tested. TODO
 *
 * @see main.java.org.model.Campaign
 * @see main.java.org.Service.CampaignScreen
 * @author Freyja Jokulsdottir
 * @since 2017-02-24
 */
class CampaignScreenTest {
    private Campaign camp;
    private Map map;
    private List<Map> levels;
    private String mapName;
    private ObjectSaver os = new ObjectSaver();
    private ObjectLoader ol = new ObjectLoader();

    @BeforeEach
    void setUp() throws Exception {
        map = new Map();
        // This map has to be saved in map.txt
        mapName = "SomeMap";
        levels = new ArrayList<Map>();
        camp = new Campaign(levels);
        camp.setName("camp1");
        camp.setNumLevels(1);
        camp.addMap(mapName);
    }

    @AfterEach
    void tearDown() {
        camp = null;
        map = null;
        levels = null;
        mapName = null;
    }

    @Test
    void createCampaignScreen() {

    }

    @Test
    void editCampaignScreen() {

    }

    @Test
    void getNewCamp() {

    }

}