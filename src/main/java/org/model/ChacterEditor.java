package main.java.org.model;

import main.java.org.model.ItemEnum;
import main.java.org.model.Map;
import main.java.org.model.NonPlayerCharacter;
import main.java.org.model.PlayerCharacter;

import java.util.List;

public class ChacterEditor {
    Map setplayerCharacter(PlayerCharacter character, final  Map map) throws Exception {
        map.setCharacter(character);
        return map;
    }

    Map setNonplayerCharacters(ItemEnum item , List<Character> character, final  Map map) {
        map.setNonPlayerCharacters(character);
        return map;
    }

    void addItem(PlayerCharacter chacater, ItemEnum item){
        chacater.getBackPackInventory().getItems().add(item);
    }

}
