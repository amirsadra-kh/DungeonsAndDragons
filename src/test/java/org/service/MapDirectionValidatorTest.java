package test.java.org.service;

import main.java.org.Service.MapDirectionValidator;
import main.java.org.Service.ObjectLoader;
import main.java.org.Service.PlayScreen;
import main.java.org.model.Campaign;
import main.java.org.model.Character;
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
    Character character;
    Map map;
    PlayScreen playScreen;

    @Before
    public void setup() throws Exception {

        campaign = new Campaign();
        character = new Character();
        map = new ObjectLoader().loadMapTest("map2");
        mapDirectionValidator = new MapDirectionValidator(campaign,map);
        playScreen=new PlayScreen();

    }

    @Test
    public void testValidateDirectionToLeftOutOfBound() throws Exception {
        boolean result = mapDirectionValidator.validateDirectionIsValidBoundriesAndMovePlayer("l");
        assertFalse(result);
    }

    @Test
    public void testValidateDirectionToRightOutOfBound() throws Exception {
        boolean result = mapDirectionValidator.validateDirectionIsValidBoundriesAndMovePlayer("r");
        assertFalse(result);
    }

    @Test
    public void testValidateDirectionToTopOutOfBound() throws Exception {
        boolean result = mapDirectionValidator.validateDirectionIsValidBoundriesAndMovePlayer("u");
        assertFalse(result);
    }

    @Test
    public void testValidateDirectionToDownOutOfBound() throws Exception {
        boolean result = mapDirectionValidator.validateDirectionIsValidBoundriesAndMovePlayer("d");
        assertFalse(result);
    }

}