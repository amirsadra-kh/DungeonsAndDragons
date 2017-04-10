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
        final char[][] table = new char[3][3];
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
        final ArrayList<Character> nonPlayerCharacters = new ArrayList<>();
        nonPlayerCharacters.add(createCharacter());

        final Map map = new Map(null);
        return map;
    }

    public static Character createCharacter(){
        final Character character = new Character();
        final Ability ability = new Ability();
        ability.setArmorClass(0);
        ability.setAttackBonus(1, 0);
        ability.setDamageBonus(0);
        character.setAbility(ability);
        final BackPackInventory inventory = new BackPackInventory();
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
        final ItemEnum belt = ItemEnum.BELT;
        final Item item = new Item();
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
        final ItemEnum boots = ItemEnum.BOOTS;
        final Item item = new Item();
        item.setName("boots1");
        item.setEnhancementType(EnhancementTypesEnum.STRENGTH);
        item.setEnhance(3);
        item.setItem(boots);
        return item;
    }

    /**
     * This method creates an Item with several belts
     *
     * @param count the number of belts your Item have
     * @return and Item
     */
    public static ArrayList<Item> createItemWithSeveralBelts(final int count) {
        final Item item = new Item();
        item.setItem(ItemEnum.BELT);
        final ArrayList items = new ArrayList<Item>();
        for (int i = 0; i < count; i++) {
            items.add(item);
        }
        return items;
    }

    /**
     * A method to create a second belt for test units
     * @return a belt item
     */
    public static Item createBelt2() {
        final ItemEnum belt = ItemEnum.BELT;
        final Item item = new Item();
        item.setName("belt2");
        item.setEnhancementType(EnhancementTypesEnum.STRENGTH);
        item.setEnhance(1);
        item.setItem(belt);
        return item;
    }

    /**
     * Thsi method is to create a map with no empty spot (all walls)
     *
     * @param map a map with no empty spot
     * @return
     */
    public static Map createAMapWithNoEmptySpot(final Map map) {
        for (int row = 0; row < map.getScreen().length; row++) {
            for (int col = 0; col < map.getScreen()[row].length; col++) {
                map.getScreen()[row][col] = "w";
            }
        }
        return map;

    }
}
