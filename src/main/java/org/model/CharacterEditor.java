package main.java.org.model;

import java.util.List;

/**
 * A class for adding characters to a map
 *
 * @author TODO who is the author?
 * @version 1.0
 * @since 2017-02-23
 */
public class CharacterEditor {
    /**
     * TODO JavaDoc
     * @param character
     * @param map
     * @return
     * @throws Exception
     */
    private Map setplayerCharacter(Character character, final  Map map) throws Exception {
        map.setCharacter(character);
        return map;
    }

    /**
     * TODO JavaDoc
     * @param item
     * @param character
     * @param map
     * @return
     */
    private Map setNonplayerCharacters(ItemEnum item , List<Character> character, final  Map map) {
        map.setNonPlayerCharacters(character);
        return map;
    }

    /**
     * TODO JavaDoc
     * @param character
     * @param item
     */
    private void addItem(Character character, Item item){
        character.getBackPackInventory().add(item);
    }

}
