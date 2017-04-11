package test;

import main.java.org.model.CharacterPackage.Ability;
import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.DecoratorPackage.Weapon;
import main.java.org.model.DecoratorPackage.WeaponFactory;
import main.java.org.model.EnhancementTypesEnum;
import main.java.org.model.Item;
import main.java.org.model.ItemEnum;
import main.java.org.model.Map;

import java.awt.*;
import java.util.ArrayList;

/**
 * A fixture class for testing classes
 */
public class Fixtures {

    /**
     * A method to create a test screen
     * @return
     */
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

    /**
     * A method to create a null map
     * @return
     */
    public static Map createMap(){
        final ArrayList<Character> nonPlayerCharacters = new ArrayList<>();
        nonPlayerCharacters.add(createCharacter());

        final Map map = new Map(null);
        return map;
    }

    /**
     * A method to create a character
     * @return
     */
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
     * A method to create a belt with cons for test units
     * @return a belt item
     */
    public static Item createBeltCon(){
        final ItemEnum belt = ItemEnum.BELT;
        final Item item = new Item();
        item.setName("belt1");
        item.setEnhancementType(EnhancementTypesEnum.CONSTITUTION);
        item.setEnhance(2);
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
     * A method to create boots dexterity for test units
     * @return a boots item
     */
    public static Item createBootsDex() {
        final ItemEnum boots = ItemEnum.BOOTS;
        final Item item = new Item();
        item.setName("boots2");
        item.setEnhancementType(EnhancementTypesEnum.DEXTERITY);
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
     * A weapon item - longsword for testing frightening
     * @return
     */
    public static Weapon createWeapon() {
        ItemEnum weapon = ItemEnum.WEAPON;
        Weapon item = new Weapon();
        item = WeaponFactory.setSpecialEnhancement(item, 4);
        item.setName("weapon1");
        item.setEnhancementType(EnhancementTypesEnum.DAMAGEBONUS);
        item.setEnhance(2);
        item.setItem(weapon);
        item.setType("longsword");
        return item;
    }

    /**
     * A weapon item - longsword for testing frozen
     * @return
     */
    public static Weapon createFrozenWeapon() {
        ItemEnum weapon = ItemEnum.WEAPON;
        Weapon item = new Weapon();
        item = WeaponFactory.setSpecialEnhancement(item, 1);
        item.setName("weapon2");
        item.setEnhancementType(EnhancementTypesEnum.DAMAGEBONUS);
        item.setEnhance(2);
        item.setItem(weapon);
        item.setType("longsword");
        return item;
    }

    /**
     * A method to create a pacifying weapon for testing pacifying decorator
     * @return pacifying weapon
     */
    public static Weapon createPacifyingWeapon() {
        ItemEnum weapon = ItemEnum.WEAPON;
        Weapon item = new Weapon();
        item = WeaponFactory.setSpecialEnhancement(item, 5);
        item.setName("weapon3");
        item.setEnhancementType(EnhancementTypesEnum.DAMAGEBONUS);
        item.setEnhance(2);
        item.setItem(weapon);
        item.setType("longsword");
        return item;
    }

    /**
     * A method to create a slaying weapon for testing slaying decorator
     * @return slaying weapon
     */
    public static Weapon createSlayingWeapon() {
        ItemEnum weapon = ItemEnum.WEAPON;
        Weapon item = new Weapon();
        item = WeaponFactory.setSpecialEnhancement(item, 3);
        item.setName("weapon4");
        item.setEnhancementType(EnhancementTypesEnum.DAMAGEBONUS);
        item.setEnhance(2);
        item.setItem(weapon);
        item.setType("longsword");
        return item;
    }

    /**
     * A weapon item - longsword for testing frightening
     * @return
     */
    public static Weapon createWeaponAtt() {
        ItemEnum weapon = ItemEnum.WEAPON;
        Weapon item = new Weapon();
        item = WeaponFactory.setSpecialEnhancement(item, 4);
        item.setName("weapon1");
        item.setEnhancementType(EnhancementTypesEnum.ATTACKBONUS);
        item.setEnhance(4);
        item.setItem(weapon);
        item.setType("longsword");
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
