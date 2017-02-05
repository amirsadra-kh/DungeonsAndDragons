package main.java.org.model;

import java.util.List;

public class ChacterEditor {
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
