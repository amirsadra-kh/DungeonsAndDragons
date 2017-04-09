package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.Map;

import java.awt.*;

/**
 * This Class is to validate the directions on the MAP
 *
 * @author Maysam
 * @version 1.0
 */
public class MapDirectionValidator {
    private static final int levelsPlayed = 0;
    private Campaign campaign;
    private Map map;

    /**
     * This is the constructor of the MapDirectionValidator
     * @param campaign the campaign we are playing with
     * @param map the map we are currently playing
     */
    public MapDirectionValidator(final Campaign campaign, final Map map) {
        this.campaign = campaign;
        this.map = map;
    }

    /**
     * An empty constructor for the move methods in strategy pattern.
     */
    public MapDirectionValidator() {}

    /**
     * This method is to move the player on the map based on the provided direction
     *
     * @param direction the direction player in moving toward
     * @param  @return it returns true if player can move
     */
    public boolean isDirectionLeadsToValidCell(final String direction, final Map map) {
        final Point point = Map.getPlayerCoordinate(map);
        final Point nextPoint = getNextCellToMove(direction, point);
        return coordinateIsValid(nextPoint.x, nextPoint.y, map);
    }


    public Point getNextCellToMove(final String direction, final Point currentPosition) {
        if ("L".equalsIgnoreCase(direction)) {
            return new Point(currentPosition.x, currentPosition.y - 1);
        } else if ("R".equalsIgnoreCase(direction)) {
            return new Point(currentPosition.x, currentPosition.y + 1);
        } else if ("D".equalsIgnoreCase(direction)) {
            return new Point(currentPosition.x + 1, currentPosition.y);
        } else if ("U".equalsIgnoreCase(direction)) {
            return new Point(currentPosition.x - 1, currentPosition.y);
        }
        return null;
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
    protected boolean coordinateIsValid(final int i, final int j, final Map map) {
        try {
            final String str = map.getScreen()[i][j];
            return true;
        } catch (final IndexOutOfBoundsException e) {
            System.out.print("the selected coordinate is out of bound , please try another coordinate");
            return false;
        }

    }

    /**
     * This method is to return a valid coordinate with the maximum 3 spots
     * @param i i coordinate
     * @param j j coordinate
     * @param map the target map
     * @param target the target coordinate
     * @return
     */
    public boolean coordinateIsValidToMove(final int i, final int j, final Map map, final Point target) {
        try {
            final String str = map.getScreen()[i][j];
            return str.equalsIgnoreCase(" ") && ((Math.abs(target.x - i)) + Math.abs(target.y - j)) <= 3;
        } catch (final IndexOutOfBoundsException e) {
            System.out.print("the selected coordinate is out of bound , please try another coordinate");
            return false;
        }

    }

//    /**
//     * This method is to validate if we can go to next level
//     * @param map the map we are playing
//     * @param i the i coordinate on map
//     * @param j the j coordinate on map
//     * @param campaign the campaign we are playing
//     * @param str  the target object we are interacting with
//     * @return true (if there is no more levels left), false : if goes to next level or remains in this level
//     */
//    private boolean isAllLevelsCompleted(Map map, final int i, final int j, final Campaign campaign, final String str) {
//        if (this.map.isCanGoNextLevel()) {
//
//            try {
//                this.map = map = campaign.getNextLevel(map);
//
//            } catch (final Exception e) {
//                System.out.println("You finished all the levels in the Campaign!");
//                return true;
//            }
//            PlayScreen.setPlayerAtEntryPoint(this.map);
//            System.out.println("Great you moved to next level");
//            MapScreen.showMap(this.map);
//            System.out.println(GameConstantsInterface.ENTER_DIRECTION);
//            SetInteractionStrategy.interact(this.map, str, Map.getPlayerCoordinate(this.map), new Point(i, j), campaign);
//
//            return false;
//        } else {
//            System.out.println("You have not found a chest in the map yet\n" +
//                    " Please find the chest in the map and then you can go to next level");
//            return false;
//        }
//    }

}
