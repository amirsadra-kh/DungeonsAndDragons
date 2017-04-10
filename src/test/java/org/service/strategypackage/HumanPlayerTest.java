package test.java.org.service.strategypackage;

import main.java.org.Service.MapDirectionValidator;
import main.java.org.Service.ObjectLoader;
import main.java.org.Service.StrategyPackage.HumanPlayer;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Map;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * A class to test the HumanPlayer strategy
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 10.04.2017
 */
public class HumanPlayerTest {
    private Map map;
    private Character player;

    @Before
    public void setUp() throws Exception {
        this.map = new ObjectLoader().loadMapTest("StrategyTestMap");
        this.player = new Character();
        HumanPlayer humanPlayer = new HumanPlayer();
        player.setBehaviourStrategy(humanPlayer);
    }

    @After
    public void tearDown() throws Exception {
        this.map = null;
        this.player = null;
    }

    /**
     * Test if the valid cells are taken as valid cells when the human player tries to move on the map
     * @throws Exception
     */
    @Test
    public void testMoveHumanPlayerInBounds() throws Exception {
        // GIVEN
        player.setCurrentPosition(new Point(3,0));
        String direction1 = "U";
        String direction2 = "R";

        // WHEN
        MapDirectionValidator validate = new MapDirectionValidator();
        boolean valid = validate.directionIsValidToMove(direction1, this.map, player.getCurrentPosition());

        // THEN
        Assert.assertTrue(valid);

        // WHEN
        valid = validate.directionIsValidToMove(direction2, this.map, player.getCurrentPosition());

        // THEN
        Assert.assertTrue(valid);

        // WHEN
        valid = validate.directionIsValidToMove(direction1, this.map, player.getCurrentPosition());

        // THEN
        Assert.assertTrue(valid);
    }

    /**
     * Test the new location of the player after moving
     */
    @Test
    public void testGetNewPositionHumanPlayer() {
        // GIVEN
        this.player.setCurrentPosition(new Point(3,0));
        MapDirectionValidator validate = new MapDirectionValidator();
        String direction1 = "U";
        String direction2 = "R";
        String direction3 = "U";

        // WHEN
        Point newPoint = validate.getNextCellToMove(direction1, player.getCurrentPosition());
        player.setCurrentPosition(newPoint);
        newPoint = validate.getNextCellToMove(direction2, player.getCurrentPosition());
        player.setCurrentPosition(newPoint);
        newPoint = validate.getNextCellToMove(direction3, player.getCurrentPosition());
        player.setCurrentPosition(newPoint);

        // THEN
        Point correctPoint = new Point(1,1);
        Assert.assertEquals(correctPoint, newPoint);
    }

    /**
     * Test if it is an invalid move to move out of the map
     */
    @Test
    public void testMoveHumanPlayerOutOfBounds() {
        // GIVEN
        player.setCurrentPosition(new Point(3,0));
        String direction = "L";

        // WHEN
        MapDirectionValidator validate = new MapDirectionValidator();
        boolean valid = validate.directionIsValidToMove(direction, this.map, player.getCurrentPosition());

        // THEN
        Assert.assertFalse(valid);
    }
}