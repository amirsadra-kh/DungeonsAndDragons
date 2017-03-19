package main.java.org.Service;

import main.java.org.model.*;
import main.java.org.model.Character;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
    public void interact(Map map, String targetObject, Point playerCoordinate, Point objectCoordinate, Campaign campaign) {

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
     * This method will exchange an item with a friendly monster.
     */
    private void interactWithFriendlyCharacter(Map map, String targetObject, Point playerCoordinate, Point objectCoordinate, Campaign campaign) {
        Character friendlyCharacter=null;
        try {
            friendlyCharacter = ObjectLoader.loadCharacterFromXML(targetObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO interactWithFriendlyCharacter here
        Character character = null;

        List<Item> friendlyCharacterBackpack, playerBackPack;
        friendlyCharacterBackpack = friendlyCharacter.getBackPackInventory();
        playerBackPack = character.getBackPackInventory();

        System.out.println("Choose an item to exchange with an item from friendly monster: \n"+playerBackPack.toString());
        int itemToGive = Integer.parseInt(readLine());

        Item temp1 = playerBackPack.get(itemToGive);
        playerBackPack.remove(itemToGive);

        Random randomGenerator;
        randomGenerator = new Random();
        int index = randomGenerator.nextInt(friendlyCharacterBackpack.size());

        Item itemToReceive = friendlyCharacterBackpack.get(index);

        playerBackPack.add(itemToReceive);
        friendlyCharacterBackpack.remove(itemToReceive);

        friendlyCharacterBackpack.add(temp1);
    }

    private static void interactWithChest(Map map, String targetObject, Point playerCoordinate, Point objectCoordinate, Campaign campaign) {
        //TODO interactWithChest here
        Character character = null;
        BackPackInventory chest = null;
        List<Item> loot;
        loot = chest.getItems();
        character.setBackPackInventory((BackPackInventory) loot);
    }

    private static void interactWithMonster(Map map, String targetObject, Point playerCoordinate, Point objectCoordinate, Campaign campaign) {
        Character character = null;

        Character monster=null;
        try {
            monster = ObjectLoader.loadCharacterFromXML(targetObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO interactWithMonster here
        Ability ability = character.getAbility();
        monster.decreaseHitPoint(ability.getAttackBonus());

    }

    private static void goToNextLevel(Map map, String targetObject, Point playerCoordinate, Point objectCoordinate, Campaign campaign) {
        //TODO go to next level logic here + adjust the Campaign
    }

    private String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
