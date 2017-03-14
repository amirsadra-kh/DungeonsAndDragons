package test.java.com;

import main.java.org.model.EnhancementTypes;
import main.java.org.model.Item;
import main.java.org.model.ItemEnum;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Sadra on 2/22/17.
 */
public class ItemTest {

    Item item1;
    Item item2;

    @Before
    public  void  beforeMethod(){
        item1 = new Item("item1", ItemEnum.HELMET, EnhancementTypes.ARMORCLASS, 5);
        item2 = new Item("item2", ItemEnum.HELMET, EnhancementTypes.ARMORCLASS, 5);

    }

    @Test
    public  void test1(){
        assertTrue(item1.equals(item2));
    }
}
