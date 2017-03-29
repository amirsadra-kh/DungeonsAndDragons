package test.java.org.model;

import main.java.org.Service.ObjectLoader;
import main.java.org.model.Campaign;
import main.java.org.model.Character.Character;
import main.java.org.model.Map;
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

    /**
     * A method to set up before the tests.
     */
    @Before
    public void setUp() {
        this.camp = new Campaign();
        this.mapNames = new ArrayList<>();
    }

    /**
     * A method to tear down after the tests.
     */
    @After
    public void tearDown() {
        this.camp = null;
        this.mapNames = null;
    }

    /**
     * A test method for the setName method.
     * Checks if the name giving to a campaign
     * is the same as the one gotten using getName
     */
    @Test
    public void testSetName() {
        String name = "camp1";
        camp.setName(name);
        Assert.assertEquals(name, camp.getName());
    }

    /**
     * A test method for the setNumLevels method.
     * Checks if the number of levels given to a
     * campaign is the same as the one gotten
     * using getNumLevels.
     */
    @Test
    public void testSetNumLevels() {
        int num = 2;
        camp.setNumLevels(num);
        Assert.assertEquals(num, camp.getNumLevels());
    }

    /**
     * A test method for the setMapNames method.
     * Checks if the name of the map added to a
     * campaign is the same as the one gotten
     * using getMapNames.
     */
    @Test
    public void testSetMapNames() {
        String mapName = "map1";
        mapNames.add(mapName);
        camp.setMapNames(mapName);
        Assert.assertEquals(mapNames, camp.getMapNames());
    }

    /**
     * A test method fot getting the next level in a campaign.
     * The method takes in 2 parameters, int levelsPlayed and
     * a the character being played.
     * It return the current map to play
     */
    @Test
    public void testNextLevel() {
        int levelsPlayed = 0;
        ObjectLoader ol = new ObjectLoader();
        try {
            Character character = ol.loadCharacterFromXML("char1");
            camp = camp.loadCampaign("camp1");
            Map current = camp.nextLevel(levelsPlayed, character);
            Map correct = camp.getMap("map2");
            // TODO Check if the name is correct
            //Assert.assertEquals(correct.getName(), current.getName());
            // TODO Check if the non-player character level is correct
            // TODO Check if the chest item enhancement is correct
        } catch (Exception e) {
            System.out.println("This character or campaign does not exist!");
            e.printStackTrace();
        }

    }

    /**
     * A test method for the removeLevel method.
     * Checks if the number of levels has been
     * decreased by one if a map has been removed
     * from the campaign.
     */
    @Test
    public void testRemoveLevel() {
        int num = 2;
        camp.setNumLevels(num);
        mapNames.add("map1");
        camp.removeLevel(mapNames);
        Assert.assertEquals(num-1, camp.getNumLevels());
    }
}