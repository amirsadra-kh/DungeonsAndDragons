package test;

import main.java.org.model.Ability;
import main.java.org.model.BackPackInventory;
import main.java.org.model.Character;
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

        Map map = new Map(new Point(1,2),
                new Point(1,6),
                createATestScreen(),
                createCharacter(),
                nonPlayerCharacters,
                null);
        return map;
    }

    public static Character createCharacter(){
        Character character = new Character();
        Ability ability =new Ability();
        ability.setArmorClass(1);
        ability.setAttackBonus(12);
        ability.setAttackBonus(32);
        ability.setDamageBonus(2);
        ability.setHitPoints(1);
        character.setAbility(ability);
        BackPackInventory inventory = new BackPackInventory();
        inventory.setItems(null);
        character.setBackPackInventory(inventory);
        character.setCurrentPosition(new Point(1,2));

        return character;
    }
}
