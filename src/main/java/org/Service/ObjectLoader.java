
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

    public List<Map> loadMaps() {
        return null;
    }

    public List<Campaign> loadCampaigns() {
        return null;
    }

    public List<Character> loadCharacters() {
        return null;
    }

    public Map loadMap(String fileName) {

        return (Map) loadFile("./src/test/resources/files/maps/" + fileName);
    }
    public Campaign loadCampaign(String path) {
        return null;
    }

    public Character loadCharacter(String path) {
        return null;
    }

}
