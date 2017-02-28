
package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.Map;

import java.util.List;

/**
 * This class is is to Load the Objects from the File.
 * + *
 * + * @author Maysam Mokarian
 * + * @version 1.0
 * + * @since 2017-02-08
 */

public class ObjectLoader extends FileProcessor {

    public static List<Map> loadMaps() {
        return null;
    }

    public static List<Campaign> loadCampaigns() {
        return null;
    }

    public static List<Character> loadCharacters() {
        return null;
    }

    public static Map loadMap(String fileName) {

        return (Map) loadFile(fileName);
    }
    public static Campaign loadCampaign(String path) {
        return null;
    }

    public static Character loadCharacter(String path) {
        return null;
    }

}
