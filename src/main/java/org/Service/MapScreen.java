package main.java.org.Service;

import main.java.org.model.Character;
import main.java.org.model.ColorConstants;
import main.java.org.model.Map;

import java.util.List;

public class MapScreen {
    /**
     * Thsi method is to show the map
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
    static void showMap(final Map map) {
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

    protected static void printElementsInTheMap(Map map) {
        System.out.println("------------Elements in the Map------------------");
        for (int i = 0; i < map.getScreen().length; i++) {
            System.out.print("|");
            for (int j = 0; j < map.getScreen()[i].length; j++) {
                if('f'==map.getScreen()[i][j].charAt(0)
                        ||'m'==map.getScreen()[i][j].charAt(0) ){
                    try {
                        System.out.println(map.getScreen()[i][j].charAt(0)+" at position i="+i+",j="+j );
                        System.out.print((Character)ObjectLoader.loadCharacterFromXML(map.getScreen()[i][j].toString()));
                        System.out.println("-----------------------------------------------------");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    static void printMapHeader(Map map) {
        for (int i = 0; i < map.getCols() * 3; i++) {

            if (i == (map.getCols() * 3) / 2) {
                System.out.print(map.getName());
            } else {
                System.out.print("-");
            }
        }
        System.out.print("-\n");
    }

    static void printMapFooter(Map map) {
        for (int i = 0; i < map.getCols() * 4; i++) {
            System.out.print("-");
        }
        System.out.print("-\n");
    }

    static Map showMaps(final List<Map> maps) {
        for (Map map : maps) {
            showMap(map);
        }
        return null;
    }
}
