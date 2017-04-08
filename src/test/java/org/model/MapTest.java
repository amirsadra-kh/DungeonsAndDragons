package test.java.org.model;

import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Map;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void testGetName() {
        String name = "map1";
        this.map.setName(name);
        Assert.assertEquals(name, this.map.getName());
    }

    @Test
    public void testisLevelCompleted() {
        this.map.setLevelCompleted(true);
        Assert.assertEquals(true, this.map.isLevelCompleted());
    }

    @Test
    public void testGetCols() {
        int cols=5;
        this.map.setCols(cols);
        Assert.assertEquals(cols, this.map.getCols());
    }

    @Test
    public void testgetRows() {
        int rows=4;
        this.map.setRows(rows);
        Assert.assertEquals(rows, this.map.getRows());
    }

    @Test
    public void testgetPlayer() {
        Character player = new Character() ;
        this.map.setPlayer(player);
        Assert.assertEquals(player, this.map.getPlayer());
    }


    @After
    public void tearDown() {
        this.map = null;
    }
}
