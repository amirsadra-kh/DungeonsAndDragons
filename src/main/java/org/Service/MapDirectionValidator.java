package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.GameConstantsInterface;
import main.java.org.model.Map;
import main.java.org.model.TurnBasedMechanism;

import java.awt.*;
import java.util.*;
import java.util.List;

import static main.java.org.Service.SetInteractionStrategy.readInput;

/**
 * This Class is to validate the directions on the MAP
 *
 * @author Maysam
 * @version 1.0
 */
public class MapDirectionValidator {
    private static int levelsPlayed = 0;
    private Campaign campaign;
    private Map map;

    /**
     * This is the constructor of the MapDirectionValidator
     * @param campaign the campaign we are playing with
     * @param map the map we are currently playing
     */
    public MapDirectionValidator(Campaign campaign, Map map) {
        this.campaign = campaign;
        this.map = map;
    }

    /**
     * This method is to move the player on the map based on the provided direction
     *@param direction the direction player in moving toward
     * @return it returns true if player can move
     */
    public boolean validateDirectionIsValidBoundriesAndMovePlayer(String direction) {
        //String direction =readInput.readLine();
        Point point = Map.getPlayerCoordinate(map);
        MapScreen.showMap(map);
        if ("L".equalsIgnoreCase(direction)) {
            return validateAndCheckIfPlayerGoesToNextLevel(point.x, point.y - 1);
        } else if ("R".equalsIgnoreCase(direction)) {
            return validateAndCheckIfPlayerGoesToNextLevel(point.x, point.y + 1);
        } else if ("D".equalsIgnoreCase(direction)) {
            return validateAndCheckIfPlayerGoesToNextLevel(point.x + 1, point.y);
        } else if ("U".equalsIgnoreCase(direction)) {
            return validateAndCheckIfPlayerGoesToNextLevel(point.x - 1, point.y);
        }
        return false;
    }

    /**
     * This method validates the new coordinate and set the player CharacterPackage to new Coordinate
     * if we are interacting with empty space, we will move
     * if the target object is wall we should try again
     * otherwise we should interact with other objects using SetInteractionStrategy
     *
     * @param i          new I coordinate
     * @param j          new J Coordinate
     * @return the return condition is true only when player goes to next level
     */
    protected boolean validateAndCheckIfPlayerGoesToNextLevel( int i, int j) {
        try {
            String str = map.getScreen()[i][j];
            if ("W".equalsIgnoreCase(str)) {
                System.out.println("The target direction will lead to wall, please try another direction");
                MapScreen.printElementsInTheMap(map);
                MapScreen.showMap(map);
                return false;
            } else if ("Q".equalsIgnoreCase(str)) {
                return validateQuiteLevel(map, i, j, campaign, str);
            }
            else if (!" ".equalsIgnoreCase(str)) {
                SetInteractionStrategy.interact(map, str, Map.getPlayerCoordinate(map), new Point(i, j), campaign);
                MapScreen.printElementsInTheMap(map);
                MapScreen.showMap(map);
                return false;
            } else {
                Point point = Map.getPlayerCoordinate(map);
                map.getScreen()[point.x][point.y] = " ";
                map.getScreen()[i][j] = "P";
                MapScreen.printElementsInTheMap(map);
                MapScreen.showMap(map);
                return false;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.print("the selected coordinate is out of bound , please try another coordinate");
        }
        return false;
    }

    /**
     * This method is to validate if we can go to next level
     * @param map the map we are playing
     * @param i the i coordinate on map
     * @param j the j coordinate on map
     * @param campaign the campaign we are playing
     * @param str  the target object we are interacting with
     * @return true (if there is no more levels left), false : if goes to next level or remains in this level
     */
    private  boolean validateQuiteLevel(Map map, int i, int j, Campaign campaign, String str) {
        if (this.map.isCanGoNextLevel()) {

            try {
                this.map = campaign.getNextLevel(map);
            } catch (Exception e) {
                System.out.println("You finished all the levels in the Campaign!");
                return true;
            }
            PlayScreen.setPlayerAtEntryPoint(this.map);
            System.out.println("Great you moved to next level");
            MapScreen.showMap(this.map);
            System.out.println(GameConstantsInterface.ENTER_DIRECTION);
            SetInteractionStrategy.interact(this.map, str, Map.getPlayerCoordinate(this.map), new Point(i, j), campaign);

            return false;
        } else {
            System.out.println("You have not found a chest in the map yet\n" +
                    " Please find the chest in the map and then you can go to next level");
            return false;
        }
    }

}
