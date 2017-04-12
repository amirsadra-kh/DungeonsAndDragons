package main.java.org.Service;

import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Map;

import java.awt.*;

/**
 * A class for the Character strategies to use to see if there  is anything in the cells next to them for interaction
 * or an attack.
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 11.04.2017
 */
public class AdjacentObjectsFinder {
    private Point up;
    private Point down;
    private Point left;
    private Point right;

    /**
     * A method to check if there is a chest in the cells next to the character
     *
     * @param currentPoint the current point of the character
     * @param map the map being played
     * @return the true if the chest if it is close, else return false
     */
    public boolean checkForChest(Point currentPoint, Map map) {
        setAdjacentPoints(currentPoint);
        String[][] currentMap = map.getScreen();
        // Validate the point is on the map
        if(up.x > -1 && up.y > -1 && up.x < currentMap[0].length && up.y < currentMap.length)
            // Check if the point has a chest
            if(currentMap[up.x][up.y].equals("c"))
                return true;
        // Validate the point is on the map
        else if(down.x > -1 && down.y > -1 && down.x < currentMap[0].length && down.y < currentMap.length)
            // Check if the point has a chest
            if(currentMap[down.x][down.y].equals("c"))
                return true;
        // Validate the point is on the map
        else if(left.x > -1 && left.y > -1  && left.x < currentMap[0].length && left.y < currentMap.length)
            // Check if the point has a chest
            if(currentMap[left.x][left.y].equals("c"))
                return true;
        // Validate the point is on the map
        else if(right.x > -1 && right.y > -1  && right.x < currentMap[0].length && right.y < currentMap.length)
            // Check if the point has a chest
            if(currentMap[right.x][right.y].equals("c"))
                return true;

        return false;
    }

    public Point checkForTarget(Point currentPoint, Map map) {
        return null;
    }

    /**
     * A method to check if there is a friendly character in the adjacent cells
     * @param currentPoint of the character
     * @param map current map being played
     * @return the position of the friendly character
     */
    public Character checkForFriendly(Point currentPoint, Map map) {
        setAdjacentPoints(currentPoint);
        String[][] currentMap = map.getScreen();
        // Validate the point is on the map
        if(up.x > -1 && up.y > -1 && up.x < currentMap[0].length && up.y < currentMap.length) {
            // Check if the point has a chest
            if (currentMap[up.x][up.y].substring(0,1).equals("f")) {
                return loadCharacterByPoint(up, map);
            }
        }
        // Validate the point is on the map
        else if(down.x > -1 && down.y > -1 && down.x < currentMap[0].length && down.y < currentMap.length) {
            // Check if the point has a chest
            if (currentMap[down.x][down.y].substring(0,1).equals("f")) {
                return loadCharacterByPoint(down, map);
            }
        }
        // Validate the point is on the map
        else if(left.x > -1 && left.y > -1  && left.x < currentMap[0].length && left.y < currentMap.length) {
            // Check if the point has a chest
            if (currentMap[left.x][left.y].substring(0,1).equals("f")) {
                return loadCharacterByPoint(left, map);
            }
        }
        // Validate the point is on the map
        else if(right.x > -1 && right.y > -1  && right.x < currentMap[0].length && right.y < currentMap.length) {
            // Check if the point has a chest
            if (currentMap[right.x][right.y].substring(0,1).equals("f")) {
                return loadCharacterByPoint(right, map);
            }
        }
        return null;
    }

    /**
     * A method to check if there is a monster in the adjacent cells
     * @param currentPoint of the character
     * @param map being played
     * @return monster character
     */
    public Character checkForMonster(Point currentPoint, Map map) {
        setAdjacentPoints(currentPoint);
        String[][] currentMap = map.getScreen();
        // Validate the point is on the map
        if(up.x > -1 && up.y > -1 && up.x < currentMap[0].length && up.y < currentMap.length) {
            // Check if the point has a chest
            if (currentMap[up.x][up.y].substring(0,1).equals("m")) {
                return loadCharacterByPoint(up, map);
            }
        }
        // Validate the point is on the map
        else if(down.x > -1 && down.y > -1 && down.x < currentMap[0].length && down.y < currentMap.length) {
            // Check if the point has a chest
            if (currentMap[down.x][down.y].substring(0,1).equals("m")) {
                return loadCharacterByPoint(down, map);
            }
        }
        // Validate the point is on the map
        else if(left.x > -1 && left.y > -1  && left.x < currentMap[0].length && left.y < currentMap.length) {
            // Check if the point has a chest
            if (currentMap[left.x][left.y].substring(0,1).equals("m")) {
                return loadCharacterByPoint(left, map);
            }
        }
        // Validate the point is on the map
        else if(right.x > -1 && right.y > -1  && right.x < currentMap[0].length && right.y < currentMap.length) {
            // Check if the point has a chest
            if (currentMap[right.x][right.y].substring(0,1).equals("m")) {
                return loadCharacterByPoint(right, map);
            }
        }
        return null;
    }

    /**
     * A method to check if there is a player in the adjacent cells
     * @param currentPoint of the monster
     * @param map being played
     * @return player character
     */
    public Character checkForPlayer(Point currentPoint, Map map) {
        setAdjacentPoints(currentPoint);
        String[][] currentMap = map.getScreen();
        // Validate the point is on the map
        if(up.x > -1 && up.y > -1 && up.x < currentMap[0].length && up.y < currentMap.length) {
            // Check if the point has a chest
            if (currentMap[up.x][up.y].substring(0,1).equals("P")) {
                return loadCharacterByPoint(up, map);
            }
        }
        // Validate the point is on the map
        else if(down.x > -1 && down.y > -1 && down.x < currentMap[0].length && down.y < currentMap.length) {
            // Check if the point has a chest
            if (currentMap[down.x][down.y].substring(0,1).equals("P")) {
                return loadCharacterByPoint(down, map);
            }
        }
        // Validate the point is on the map
        else if(left.x > -1 && left.y > -1  && left.x < currentMap[0].length && left.y < currentMap.length) {
            // Check if the point has a chest
            if (currentMap[left.x][left.y].substring(0,1).equals("P")) {
                return loadCharacterByPoint(left, map);
            }
        }
        // Validate the point is on the map
        else if(right.x > -1 && right.y > -1  && right.x < currentMap[0].length && right.y < currentMap.length) {
            // Check if the point has a chest
            if (currentMap[right.x][right.y].substring(0,1).equals("P")) {
                return loadCharacterByPoint(right, map);
            }
        }
        return null;
    }

    /**
     * A method to set the adjacent points of the character for checking them
     * @param currentPoint the current point of the character
     */
    private void setAdjacentPoints(Point currentPoint) {
        this.up = new Point(currentPoint.x-1, currentPoint.y);
        this.down = new Point(currentPoint.x+1, currentPoint.y);
        this.left = new Point(currentPoint.x, currentPoint.y-1);
        this.right = new Point(currentPoint.x, currentPoint.y+1);
    }

    /**
     * A method to get a character by their current position
     * @param point
     * @param map
     * @return a character at position point
     */
    private Character loadCharacterByPoint(Point point, Map map) {
        java.util.List<Character> mapCharacters = map.getNonPLayerCharacters();
        for(Character c : mapCharacters)
            if(c.getCurrentPosition().equals(point))
                return c;

        return null;
    }
}
