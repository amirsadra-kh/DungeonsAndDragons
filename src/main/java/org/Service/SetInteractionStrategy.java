package main.java.org.Service;

import main.java.org.model.Character.Ability;
import main.java.org.model.Character.BackPackInventory;
import main.java.org.model.Campaign;
import main.java.org.model.Character.Character;
import main.java.org.model.Item;
import main.java.org.model.Map;
import main.java.org.model.ReadInput;

import java.awt.*;
import java.util.ArrayList;
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
    public static void interact( Map map,  String targetObject,  Point playerCoordinate,  Point objectCoordinate,  Campaign campaign) {
        if ("Q".equalsIgnoreCase(targetObject)) {
          //  goToNextLevel(map, targetObject, playerCoordinate, objectCoordinate, campaign);
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
    private static void interactWithFriendlyCharacter(Map map,  String targetObject, Point playerCoordinate,  Point objectCoordinate,  Campaign campaign) {
        Character friendlyCharacter = new Character();
        try {
            friendlyCharacter = friendlyCharacter.loadCharacter(targetObject);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        //TODO interactWithFriendlyCharacter here
        final Character player = map.getPlayer();

        final BackPackInventory friendlyCharacterBackpack;
        final BackPackInventory playerBackPack;
        friendlyCharacterBackpack = friendlyCharacter.getBackPackInventory();
        playerBackPack = player.getBackPackInventory();
        final List<Item> playerItems = playerBackPack.getItems();
        final List<Item> friendlyCharacterItems = friendlyCharacterBackpack.getItems();

        System.out.println("Choose an item to exchange with an item from friendly monster: \n"+playerItems.toString());
        System.out.println("Choose the number of the item, the first is number 1 and so on");
        final int itemToGive = Integer.parseInt(readInput.readLine());

        final Item temp1 = playerItems.get(itemToGive);
        playerItems.remove(itemToGive);

        final int index = new Random().nextInt(friendlyCharacterItems.size());
        final Item itemToReceive = friendlyCharacterItems.get(index);

        playerItems.add(itemToReceive);
        playerBackPack.setItems(playerItems);

        friendlyCharacterItems.remove(index);
        friendlyCharacterItems.add(temp1);

        friendlyCharacterBackpack.setItems(friendlyCharacterItems);

        // Set the new backpack inventory
        player.setBackPackInventory(playerBackPack);
        friendlyCharacter.setBackPackInventory(friendlyCharacterBackpack);

        // Save the characters
        player.saveCharacter();
        friendlyCharacter.saveCharacter();

        swapPlayerWithObjectSpotsInMap(map, playerCoordinate, objectCoordinate);
    }

    /**
     * This method is to swap the positions of the player and the target object on the map
     * @param map the current map
     * @param playerCoordinate
     * @param objectCoordinate
     */
    private static void swapPlayerWithObjectSpotsInMap( Map map,  Point playerCoordinate,  Point objectCoordinate) {
        final String player = map.getScreen()[playerCoordinate.x][playerCoordinate.y];
        map.getScreen()[playerCoordinate.x][playerCoordinate.y] = map.getScreen()[objectCoordinate.x][objectCoordinate.y];
        map.getScreen()[objectCoordinate.x][objectCoordinate.y] = player;
    }

    /**
     * This method represents the interaction with Chest
     * @param map the current map
     * @param targetObject the object we are interacting with
     * @param playerCoordinate the current coordinate of the player
     * @param objectCoordinate the coordinate of the object we are interacting with
     * @param campaign the campaign we are playing
     */
    private static void interactWithChest( Map map,  String targetObject,  Point playerCoordinate,  Point objectCoordinate,  Campaign campaign) {
        //TODO interactWithChest here
        final Character player = map.getPlayer();
        final BackPackInventory chest = map.getChest();
        List<Item> loot=new ArrayList<>();
        if(chest!=null) {
            loot = chest.getItems();
        }
        final List<Item> playerBackpack = player.getBackPackInventoryItems();
        for (int i = 0;i<loot.size();i++)
            playerBackpack.add(loot.get(i));

        swapPlayerWithObjectSpotsInMap(map, playerCoordinate, objectCoordinate);
        map.setCanGoNextLevel(true);
    }

    /**
     * This method represents the interaction with monsters
     * @param map the current map
     * @param targetObject the object we are interacting with
     * @param playerCoordinate the current coordinate of the player
     * @param objectCoordinate the coordinate of the object we are interacting with
     * @param campaign the campaign we are playing
     */
    private static void interactWithMonster( Map map,  String targetObject,  Point playerCoordinate,  Point objectCoordinate,  Campaign campaign) {
        final Character character = map.getPlayer();

        Character monster=new Character();
        try {
            monster = monster.loadCharacter(targetObject);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        //TODO interactWithMonster here
        final Ability ability = character.getAbility();
        monster.decreaseHitPoint(ability.getAttackBonus());
        swapPlayerWithObjectSpotsInMap(map, playerCoordinate, objectCoordinate);

    }
    /**
     * This method will have the logic of going to next level
     * @param map the current map
     * @param targetObject the object we are interacting with
     * @param playerCoordinate the current coordinate of the player
     * @param objectCoordinate the coordinate of the object we are interacting with
     * @param campaign the campaign we are playing
     */
    private static void goToNextLevel( Map map,  String targetObject,  Point playerCoordinate,  Point objectCoordinate,  Campaign campaign) {
        //TODO go to next level logic here + adjust the Campaign
    }
}
