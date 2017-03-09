package test.java.org.model;

import main.java.org.model.Campaign;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by freyjaj93 on 2017-03-09.
 */
class CampaignTest {
    private Campaign camp;

    @Before
    void setUp() {
        camp = new Campaign();
    }

    @After
    void tearDown() {

    }

    @Test
    void generateCampaign() {

    }

    @Test
    void setName() {
        String name = "camp1";
        camp.setName(name);
        Assert.assertEquals(name, camp.getName());
    }

    @Test
    void getName() {

    }

    @Test
    void getNumLevels() {

    }

    @Test
    void getMapNames() {

    }

    @Test
    void setNumLevels() {

    }

    @Test
    void setMapNames() {

    }

    @Test
    void getMap() {

    }

    @Test
    void getCampaign() {

    }

    @Test
    void removeLevel() {

    }

    @Test
    void saveCampaign() {

    }

    @Test
    void loadCampaign() {

    }

}