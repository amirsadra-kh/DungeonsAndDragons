package test.java.org.model;

import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Map;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import main.java.org.view.MapFrame;
import test.Fixtures;

/**
 * Created by misha on 2017-04-08.
 */
public class MapTest {
    private Map map;

    /**
    * A method to set up before the tests.
    */
    @Before
    public void setUp() {
        this.map = new Map();
    }

    /**
     * A method to get the name of the map.
     */
    @Test
    public void testGetName() {
        String name = "map1";
        this.map.setName(name);
        Assert.assertEquals(name, this.map.getName());
    }

    /**
     * A method to check if level is completed
     */
    @Test
    public void testIsLevelCompleted() {
        this.map.setLevelCompleted(true);
        Assert.assertEquals(true, this.map.isLevelCompleted());
    }

    /**
     * A method to get the cols of the map.
     */
    @Test
    public void testGetCols() {
        int cols=5;
        this.map.setCols(cols);
        Assert.assertEquals(cols, this.map.getCols());
    }

    /**
     * A method to get the rows of the map.
     */
    @Test
    public void testGetRows() {
        int rows=4;
        this.map.setRows(rows);
        Assert.assertEquals(rows, this.map.getRows());
    }

    /**
     * A method to get the Player in the map.
     */
    @Test
    public void testGetPlayer() {
        Character player = new Character();
        this.map.setPlayer(player);
        Assert.assertEquals(player, this.map.getPlayer());
    }

    /**
     * A method to validate map.
     */
    @Test
    public void testMapValidate() {
        this.map.setRows(4);
        this.map.setCols(5);
        Fixtures.createTestMap(this.map);
        String[][] boardArray=map.getScreen();
        boolean validMap=MapFrame.testMap(boardArray);
        Assert.assertEquals(validMap, true);
    }






    /**
     * A method to tear down after the tests.
     */
    @After
    public void tearDown() {
        this.map = null;
    }
}
