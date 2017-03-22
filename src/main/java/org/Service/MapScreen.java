package main.java.org.Service;

import main.java.org.model.Character;
import main.java.org.model.ColorConstants;
import main.java.org.model.Map;
import main.java.org.model.ReadInput;

import java.util.*;

/**
 * This class represents the Map Screen logic, it shows the elements on the map
 * @author Maysam MOkarian
 * @version 1.0
 */
public class MapScreen {
    private static ReadInput readInput = new ReadInput();
    private static CharacterObserver characterObserver;
    private static InventoryObserver inventoryObserver;

    protected static void setObservers(final CharacterObserver characterObs, final InventoryObserver inventoryObs) {
        characterObserver = characterObs;
        inventoryObserver = inventoryObs;
    }
    /**
     * This method is to show the map
     * W:Wall
     * Q:Quit
     * M:Monster
     * F:friendly Character
     * C:Chest
     * E:Entry point
     * p:Player Character
     *
     * @param map
     */
    protected static void showMap(final Map map) {
        printMapHeader(map);
        for (int i = 0; i < map.getScreen().length; i++) {
            System.out.print("|");
            for (int j = 0; j < map.getScreen()[i].length; j++) {
                String charToShow = map.getScreen()[i][j].length() == 0 ? " " : map.getScreen()[i][j];
                map.getScreen()[i][j] = charToShow;
                switch (charToShow.charAt(0)) {
                    case 'M':
                    case 'm':
                        System.out.print("[" + ColorConstants.ANSI_RED + charToShow.charAt(0) + ColorConstants.ANSI_RESET + "]|");
                        break;
                    case 'F':
                    case 'f':
                        System.out.print("[" + ColorConstants.ANSI_RED + charToShow.charAt(0) + ColorConstants.ANSI_RESET + "]|");
                        break;
                    case 'W':
                        System.out.print("[" + ColorConstants.ANSI_BLUE + charToShow + ColorConstants.ANSI_RESET + "]|");
                        break;
                    case 'Q':
                        System.out.print("[" + ColorConstants.ANSI_GREEN + charToShow + ColorConstants.ANSI_RESET + "]|");
                        break;
                    case 'c':
                    case 'C':
                        System.out.print("[" + ColorConstants.ANSI_CYAN + charToShow.charAt(0) + ColorConstants.ANSI_RESET + "]|");
                        break;
                    case 'p':
                    case 'P':
                        System.out.print("[" + ColorConstants.ANSI_YELLOW + charToShow.charAt(0) + ColorConstants.ANSI_RESET + "]|");
                        break;
                    default:
                        System.out.print("[" + charToShow + "]|");
                        break;

                }

            }
            System.out.println("");
        }
        printMapFooter(map);
    }

    /**
     * This method is print elements in the MAP
     * @param map
     */
    protected static void printElementsInTheMap(Map map) {
        System.out.println("------------Elements in the Map------------------");
        for (int i = 0; i < map.getScreen().length; i++) {
            System.out.print("|");
            for (int j = 0; j < map.getScreen()[i].length; j++) {
                if('f'==map.getScreen()[i][j].charAt(0)
                        ||'m'==map.getScreen()[i][j].charAt(0) ){
                    try {
                        System.out.println(map.getScreen()[i][j].charAt(0)+" at position i="+i+",j="+j );
                        System.out.println("Character being observed: ");
                        try {
                            characterObserver.update();
                        } catch (NullPointerException e) {
                            System.out.println("non");
                        }
                        try {
                            inventoryObserver.update();
                        } catch (NullPointerException e) {
                            System.out.println("non");
                        }
                        System.out.println("-----------------------------------------------------");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * This methos is to print a header for the MAP
     * @param map the current map
     */

    protected static void printMapHeader(Map map) {
        for (int i = 0; i < map.getCols() * 3; i++) {

            if (i == (map.getCols() * 3) / 2) {
                System.out.print(map.getName());
            } else {
                System.out.print("-");
            }
        }
        System.out.print("-\n");
    }

    /**
     * This method is to print a footer for a map
     * @param map
     */
    protected static void printMapFooter(Map map) {
        for (int i = 0; i < map.getCols() * 4; i++) {
            System.out.print("-");
        }
        System.out.print("-\n");
    }

    /**
     * This method is to show the map from a list of Maps
     * @param maps
     * @return
     */
    protected static void showMaps(final List<Map> maps) {
        for (Map map : maps) {
            showMap(map);
        }
    }
}
