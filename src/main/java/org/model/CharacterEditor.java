package main.java.org.model;

import java.util.List;

/**
 * A class for adding characters to a map
 *
 * @author
 * @version 1.0
 * @since 2017-02-23
 */
public class CharacterEditor {
    Map setplayerCharacter(Character character, final  Map map) throws Exception {
        map.setCharacter(character);
        return map;
    }

    Map setNonplayerCharacters(ItemEnum item , List<Character> character, final  Map map) {
        map.setNonPlayerCharacters(character);
        return map;
    }

    void addItem(Character chacater, ItemEnum item){
        chacater.getBackPackInventory().getItems().add(item);
    }

}
