
package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.Map;

import java.io.FileNotFoundException;
import java.util.List;

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

    public Map loadMap(String fileName) throws FileNotFoundException {

        return (Map) loadFile("./src/test/resources/files/maps/" + fileName);

    }

    public Campaign loadCampaign(String path) {
        return null;
    }

    public Character loadCharacter(String path) {
        return null;
    }

}
