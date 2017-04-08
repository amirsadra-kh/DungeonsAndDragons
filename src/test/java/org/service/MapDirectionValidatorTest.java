package test.java.org.service;

import main.java.org.Service.MapDirectionValidator;
import main.java.org.Service.ObjectLoader;
import main.java.org.Service.PlayScreen;
import main.java.org.model.Campaign;
import main.java.org.model.Map;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Tests for the map direction validator
 *
 * @author Maysam MOkarian
 * @version 1.0
 */
public class MapDirectionValidatorTest {
    MapDirectionValidator mapDirectionValidator;
    Campaign campaign;
    Map map;
    PlayScreen playScreen;

    @Before
    public void setup() throws Exception {

        campaign = new Campaign();
        map = new ObjectLoader().loadMapTest("map2");
        mapDirectionValidator = new MapDirectionValidator(campaign,map);
        playScreen=new PlayScreen();

    }


    @Test
    public void testValidateDirectionToLeftOutOfBound() throws Exception {
        final boolean result = mapDirectionValidator.isDirectionLeadsToValidCell("l", map);
        assertTrue(result);
    }

    @Test
    public void testValidateDirectionToRightOutOfBound() throws Exception {
        final boolean result = mapDirectionValidator.isDirectionLeadsToValidCell("r", map);
        assertFalse(result);
    }

    @Test
    public void testValidateDirectionToTopOutOfBound() throws Exception {
        final boolean result = mapDirectionValidator.isDirectionLeadsToValidCell("u", map);
        assertTrue(result);
    }

    @Test
    public void testValidateDirectionToDownOutOfBound() throws Exception {
        final boolean result = mapDirectionValidator.isDirectionLeadsToValidCell("d", map);
        assertTrue(result);
    }

}