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

    @Test
    public void testMoveHumanPlayerInBounds() throws Exception {
        // GIVEN
        player.setCurrentPosition(new Point(3,0));
        Point chest = new Point(1,0);
        String direction1 = "U";
        String direction2 = "R";
        String direction3 = "U";

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
        valid = validate.directionIsValidToMove(direction3, this.map, player.getCurrentPosition());

        // THEN
        Assert.assertTrue(valid);
    }

}