package test.java.com;

import main.java.org.model.AbilityEnum;
import main.java.org.model.Item;
import main.java.org.model.ItemEnum;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Sadra on 2/22/17.
 */
public class ItemTest {

    Item item1;
    Item item2;

    @Before
    public  void  beforeMethod(){
        item1 = new Item(ItemEnum.HELMET, AbilityEnum.ARMORCLASS,new Point(0,0));
        item2 = new Item(ItemEnum.HELMET, AbilityEnum.ARMORCLASS,new Point(0,0));

    }

    @Test
    public  void test1(){
        assertTrue(item1.equals(item2));
    }
}
