package test.java.org.service;

import main.java.org.Service.GameGenerator;
import main.java.org.model.Item;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Sadra on 2/23/17.
 */
public class GameGeneratorTest {
    @Test
    public void createOrEditItems() throws Exception {
       //GIVEN
        GameGenerator gameGenerator = new GameGenerator();
        //WHEN
        Item item = gameGenerator.createOrEditItems();
        //THEN
        assertTrue(item instanceof Item);

    }

}