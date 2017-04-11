package test.java.org.model;

import main.java.org.model.EnhancementTypesEnum;
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
        item1 = new Item("item1", ItemEnum.HELMET, EnhancementTypesEnum.ARMORCLASS, 5);
        item2 = new Item("item2", ItemEnum.HELMET, EnhancementTypesEnum.ARMORCLASS, 5);


    }

    @Test
    public  void test1(){
        assertTrue(item1.equals(item2));
    }
}
