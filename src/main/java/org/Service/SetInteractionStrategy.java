package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.Character;
import main.java.org.model.Map;
import main.java.org.model.ReadInput;
import main.java.org.model.BackPackInventory;
import main.java.org.model.Item;
import main.java.org.model.Ability;

import java.awt.Point;
import java.util.List;
import java.util.Random;

/**
 * this method is to dispatch different behaviour based on the interactions with Monster, ....
 *
 * @author Maysam Mokarian
 * @version 1.0
 * @map: the map we are playing with
 * @targetObject the Object we are interacting with
 * @playerCoordinate the coordinate of player character
 * @objectCoordinate the coordinate of the object we are interacting with
 */
public class SetInteractionStrategy {
    static ReadInput readInput = new ReadInput();
    /**
     * This method represents the interaction of the player with the elements in the map
     * @param map the current map
     * @param targetObject the object we are interacting with
     * @param playerCoordinate the current coordinate of the player
     * @param objectCoordinate the coordinate of the object we are interacting with
     * @param campaign the campaign we are playing
     */
    public static void interact(Map map, String targetObject, Point playerCoordinate, Point objectCoordinate, Campaign campaign) {
        if ("Q".equalsIgnoreCase(targetObject)) {
            goToNextLevel(map, targetObject, playerCoordinate, objectCoordinate, campaign);
        } else if ('m'==targetObject.charAt(0)||'M'==targetObject.charAt(0)) {
            interactWithMonster(map, targetObject, playerCoordinate, objectCoordinate, campaign);
        } else if ('c'==targetObject.charAt(0)||'C'==targetObject.charAt(0)) {
            interactWithChest(map, targetObject, playerCoordinate, objectCoordinate, campaign);
        } else if ('f'==targetObject.charAt(0)||'F'==targetObject.charAt(0)) {
            interactWithFriendlyCharacter(map, targetObject, playerCoordinate, objectCoordinate, campaign);
        }

    }

    /**
     * This method represents the intecat with friendly character
     * @param map the current map
     * @param targetObject the object we are interacting with
     * @param playerCoordinate the current coordinate of the player
     * @param objectCoordinate the coordinate of the object we are interacting with
     * @param campaign the campaign we are playing
     */
    private static void interactWithFriendlyCharacter(Map map, String targetObject, Point playerCoordinate, Point objectCoordinate, Campaign campaign) {
        Character friendlyCharacter = new Character();
        try {
            friendlyCharacter = friendlyCharacter.loadCharacter(targetObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Character player = map.getPlayer();
        System.out.println("Friendly character: " +friendlyCharacter.getCharName());

        BackPackInventory friendlyCharacterBackpack, playerBackPack;
        friendlyCharacterBackpack = friendlyCharacter.getBackPackInventory();
        playerBackPack = player.getBackPackInventory();
        List<Item> playerItems = playerBackPack.getItems();
        List<Item> friendlyCharacterItems = friendlyCharacterBackpack.getItems();

        System.out.println("Choose an item to exchange with an item from friendly monster: ");
        for (Item i : playerItems)
            System.out.println(i.getName());
        int itemToGive = Integer.parseInt(readInput.readLine());

        Item temp1 = playerItems.get(itemToGive);
        playerItems.remove(itemToGive);

        int index = new Random().nextInt(friendlyCharacterItems.size());
        Item itemToReceive = friendlyCharacterItems.get(index);

        playerItems.add(itemToReceive);
        playerBackPack.setItems(playerItems);

        friendlyCharacterItems.remove(index);
        friendlyCharacterItems.add(temp1);

        friendlyCharacterBackpack.setItems(friendlyCharacterItems);
    }

    /**
     * This method represents the interaction with Chest
     * @param map the current map
     * @param targetObject the object we are interacting with
     * @param playerCoordinate the current coordinate of the player
     * @param objectCoordinate the coordinate of the object we are interacting with
     * @param campaign the campaign we are playing
     */
    private static void interactWithChest(Map map, String targetObject, Point playerCoordinate, Point objectCoordinate, Campaign campaign) {
        //TODO interactWithChest here
        Character player = map.getPlayer();
        BackPackInventory chest = map.getChest();
        List<Item> loot;
        loot = chest.getItems();
        List<Item> playerBackpack = player.getBackPackInventoryItems();
        for (int i = playerBackpack.size()+loot.size(), j=0; i < 10; i++,j++)
            playerBackpack.add(loot.get(j));
    }

    /**
     * This method represents the interaction with monsters
     * @param map the current map
     * @param targetObject the object we are interacting with
     * @param playerCoordinate the current coordinate of the player
     * @param objectCoordinate the coordinate of the object we are interacting with
     * @param campaign the campaign we are playing
     */
    private static void interactWithMonster(Map map, String targetObject, Point playerCoordinate, Point objectCoordinate, Campaign campaign) {
        Character character = map.getPlayer();

        Character monster = new Character();
        try {
            monster = monster.loadCharacter(targetObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO interactWithMonster here
        Ability ability = character.getAbility();
        monster.decreaseHitPoint(ability.getAttackBonus());
        System.out.println("Monster's Hit Point: " +monster.getHitPoints());
        monster.saveCharacter();
    }
    /**
     * This method will have the logic of going to next level
     * @param map the current map
     * @param targetObject the object we are interacting with
     * @param playerCoordinate the current coordinate of the player
     * @param objectCoordinate the coordinate of the object we are interacting with
     * @param campaign the campaign we are playing
     */
    private static void goToNextLevel(Map map, String targetObject, Point playerCoordinate, Point objectCoordinate, Campaign campaign) {
        //TODO go to next level logic here + adjust the Campaign
    }
}
