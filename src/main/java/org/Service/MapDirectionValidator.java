package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.Map;

import java.awt.*;

public class MapDirectionValidator {

    static boolean validateDirectionIsValidBoundriesAndMovePlayer(Map map, String direction, Campaign campaign) {
        Point point =getPlayerCoordinate(map);
        if ("L".equalsIgnoreCase(direction)) {
            return validateAndCheckIfPlayerGoesToNextLevel(map, point.x,point.y-1,campaign);
        } else if ("R".equalsIgnoreCase(direction)) {
            return validateAndCheckIfPlayerGoesToNextLevel(map, point.x , point.y+1, campaign);
        } else if ("D".equalsIgnoreCase(direction)) {
            return validateAndCheckIfPlayerGoesToNextLevel(map, point.x+1, point.y, campaign);
        } else if ("U".equalsIgnoreCase(direction)) {
            return validateAndCheckIfPlayerGoesToNextLevel(map, point.x-1, point.y, campaign);
        }
        return false;
    }

    /**
     * This method validates the new coordinate and set the player Character to new Coordinate
     * if we are interacting with empty space, we will move
     * if the target object is wall we should try again
     * otherwise we should intarct with other objects using SetInteractionStrategy
     * @param map
     * @param i new I coordinate
     * @param j new J Coordinate
     * @param campaign
     * @return the return condition is true only when player goes to next level
     */
    static boolean validateAndCheckIfPlayerGoesToNextLevel(Map map, int i, int j, Campaign campaign) {
        try {
            String str =map.getScreen()[i][j];
            if("W".equalsIgnoreCase(str)) {
                System.out.println("The target direction will lead to wall, please try another direction");
                MapScreen.printElementsInTheMap(map);
                MapScreen.showMap(map);
                return false;
            }
            else if("Q".equalsIgnoreCase(str)) {
                System.out.println("Great tou moved to next level");
                SetInteractionStrategy.interact(map, str, getPlayerCoordinate(map), new Point(i, j),campaign);
                MapScreen.printElementsInTheMap(map);
                MapScreen.showMap(map);
                return true;
            }
            else if(!" ".equalsIgnoreCase(str)) {
                SetInteractionStrategy.interact(map, str, getPlayerCoordinate(map), new Point(i, j), campaign);
                MapScreen.printElementsInTheMap(map);
                MapScreen.showMap(map);
                return false;
            }
            else {
                Point point = getPlayerCoordinate(map);
                map.getScreen()[point.x][point.y] = " ";
                map.getScreen()[i][j] = "P";
                MapScreen.printElementsInTheMap(map);
                MapScreen.showMap(map);
                return false;
            }
        }
        catch (IndexOutOfBoundsException e){
            System.out.print("the selected coordinate is out of bound , please try another coordinate");
        }
        return false;
    }

     static Point getPlayerCoordinate(Map map) {
        for (int i = 0; i < map.getScreen().length; i++) {
            for (int j = 0; j < map.getScreen()[i].length; j++) {
                if ("P".equalsIgnoreCase(map.getScreen()[i][j])) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }
}
