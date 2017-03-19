package test.java.org.service;

import main.java.org.Service.MapDirectionValidator;
import main.java.org.Service.ObjectLoader;
import main.java.org.model.Campaign;
import main.java.org.model.Map;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

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

    @Before
    public void setup() throws Exception {
        mapDirectionValidator = new MapDirectionValidator();
        campaign = new Campaign();
        map = new ObjectLoader().loadMapTest("map2");

    }

    @Test
    public void testValidateDirectionToLeftOutOfBound() throws Exception {
        boolean result = mapDirectionValidator.validateDirectionIsValidBoundriesAndMovePlayer(map, "l", campaign);
        assertFalse(result);
    }

    @Test
    public void testValidateDirectionToRightOutOfBound() throws Exception {
        boolean result = mapDirectionValidator.validateDirectionIsValidBoundriesAndMovePlayer(map, "r", campaign);
        assertFalse(result);
    }

    @Test
    public void testValidateDirectionToTopOutOfBound() throws Exception {
        boolean result = mapDirectionValidator.validateDirectionIsValidBoundriesAndMovePlayer(map, "u", campaign);
        assertFalse(result);
    }

    @Test
    public void testValidateDirectionToDownOutOfBound() throws Exception {
        boolean result = mapDirectionValidator.validateDirectionIsValidBoundriesAndMovePlayer(map, "d", campaign);
        assertFalse(result);
    }

}