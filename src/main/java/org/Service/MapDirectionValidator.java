package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.Character;
import main.java.org.model.Map;

import java.awt.*;

/**
 * This Class is to validate the directions on the MAP
 *
 * @author Maysam
 * @version 1.0
 */
public class MapDirectionValidator {
    private static int levelsPlayed = 0;

    /**
     * This method is to move the player on the map based on the provided direction
     *
     * @param map        The map we are playing
     * @param direction  the direction to ove
     * @param campaign   the campaign that we are playing
     * @param character
     * @param playScreen
     * @return it returns true if player can move
     */
    public static boolean validateDirectionIsValidBoundriesAndMovePlayer(Map map, String direction, Campaign campaign, Character character, PlayScreen playScreen) {
        Point point = Map.getPlayerCoordinate(map);
        if ("L".equalsIgnoreCase(direction)) {
            return validateAndCheckIfPlayerGoesToNextLevel(map, point.x, point.y - 1, campaign, character, playScreen);
        } else if ("R".equalsIgnoreCase(direction)) {
            return validateAndCheckIfPlayerGoesToNextLevel(map, point.x, point.y + 1, campaign, character, playScreen);
        } else if ("D".equalsIgnoreCase(direction)) {
            return validateAndCheckIfPlayerGoesToNextLevel(map, point.x + 1, point.y, campaign, character, playScreen);
        } else if ("U".equalsIgnoreCase(direction)) {
            return validateAndCheckIfPlayerGoesToNextLevel(map, point.x - 1, point.y, campaign, character, playScreen);
        }
        return false;
    }

    /**
     * This method validates the new coordinate and set the player Character to new Coordinate
     * if we are interacting with empty space, we will move
     * if the target object is wall we should try again
     * otherwise we should interact with other objects using SetInteractionStrategy
     *
     * @param map
     * @param i          new I coordinate
     * @param j          new J Coordinate
     * @param campaign
     * @param character
     * @param playScreen
     * @return the return condition is true only when player goes to next level
     */
    static boolean validateAndCheckIfPlayerGoesToNextLevel(Map map, int i, int j, Campaign campaign, Character character, PlayScreen playScreen) {
        try {
            String str = map.getScreen()[i][j];
            if ("W".equalsIgnoreCase(str)) {
                System.out.println("The target direction will lead to wall, please try another direction");
                MapScreen.printElementsInTheMap(map);
                MapScreen.showMap(map);
                return false;
            } else if ("Q".equalsIgnoreCase(str)) {
                if (map.isCanGoNextLevel()) {
                    System.out.println("Great you moved to next level");
                    try {
                        map = campaign.nextLevel(++levelsPlayed, character);
                    } catch (Exception e) {
                        System.out.println("Could not go to the next level");
                    }
                    if (map == null) {
                        System.out.println("You finished all the levels in the Campaign!");
                        return true;
                    }
                    playScreen.setPlayerAtEntryPoint(map);
                    SetInteractionStrategy.interact(map, str, Map.getPlayerCoordinate(map), new Point(i, j), campaign);
                    try {
                        playScreen.playGame(map);
                    }
                    catch (Exception e){
                        System.out.println("Could not go to the next level");
                    }

                    return false;
                } else {
                    System.out.println("You have not found a chest in the map yet\n" +
                            " Please find the chest in the map and then you can go to next level");
                    return false;
                }
            } else if (!" ".equalsIgnoreCase(str)) {
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


}
