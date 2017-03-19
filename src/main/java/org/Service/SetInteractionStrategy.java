package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.Character;
import main.java.org.model.Map;

import java.awt.*;

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
        Character character=null;
        try {
            character = ObjectLoader.loadCharacterFromXML(targetObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO interactWithFriendlyCharacter here
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
        Character character=null;
        try {
            character = ObjectLoader.loadCharacterFromXML(targetObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO interactWithMonster here
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
