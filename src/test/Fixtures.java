package test;

import main.java.org.model.CharacterPackage.Ability;
import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.EnhancementTypesEnum;
import main.java.org.model.Item;
import main.java.org.model.ItemEnum;
import main.java.org.model.Map;

import java.awt.*;
import java.util.ArrayList;

public class Fixtures {

    public  static char[][] createATestScreen(){
         char[][] table=new char[3][3];
        table[0][0] = ' ';
        table[0][1] = ' ';
        table[0][2] = ' ';
        table[1][0] = ' ';
        table[1][1] = ' ';
        table[1][2] = ' ';
        table[2][0] = ' ';
        table[2][1] = ' ';
        table[2][2] = ' ';
        return table;
    }

    public static Map createMap(){
        ArrayList<Character> nonPlayerCharacters = new ArrayList<Character>();
        nonPlayerCharacters.add(createCharacter());

        Map map = new Map(null);
        return map;
    }

    public static Character createCharacter(){
        Character character = new Character();
        Ability ability = new Ability();
        ability.setArmorClass(0);
        ability.setAttackBonus(1, 0);
        ability.setDamageBonus(0);
        character.setAbility(ability);
        BackPackInventory inventory = new BackPackInventory();
        inventory.setItems(null);
        character.setBackPackInventory(inventory);
        character.setCurrentPosition(new Point(1,2));

        return character;
    }

    /**
     * A method to create a belt for test units
     * @return a belt item
     */
    public static Item createBelt(){
        ItemEnum belt = ItemEnum.BELT;
        Item item = new Item();
        item.setName("belt1");
        item.setEnhancementType(EnhancementTypesEnum.STRENGTH);
        item.setEnhance(3);
        item.setItem(belt);
        return item;
    }

    /**
     * A method to create boots for test units
     * @return a boots item
     */
    public static Item createBoots() {
        ItemEnum boots = ItemEnum.BOOTS;
        Item item = new Item();
        item.setName("boots1");
        item.setEnhancementType(EnhancementTypesEnum.STRENGTH);
        item.setEnhance(3);
        item.setItem(boots);
        return item;
    }

    /**
     * A method to create a second belt for test units
     * @return a belt item
     */
    public static Item createBelt2() {
        ItemEnum belt = ItemEnum.BELT;
        Item item = new Item();
        item.setName("belt2");
        item.setEnhancementType(EnhancementTypesEnum.STRENGTH);
        item.setEnhance(1);
        item.setItem(belt);
        return item;
    }
}
