package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.Item;
import main.java.org.model.Character;
import main.java.org.model.Map;

import java.io.*;
import java.util.List;
/**
 * This class is is to Save the Objects to the File.
 *
 * @author Maysam Mokarian/Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-02-08
 */

public class ObjectSaver extends FileProcessor {
    private final static String CAMPAIGN_PATH = "src/main/java/org/resources/campaigns/";
    //private final static String MAP_PATH = "src/main/java/org/resources/maps/map.txt";
    private final static String CHARACTER_PATH = "src/main/java/org/resources/characters/";
    //TODO
    private final static String ITEM_PATH = "";

    public void SaveMaps(List<Map> maps) {
        saveFile("/maps/", maps);
    }

    public void saveItem(String path, Item item) {
        saveFile(path, item);
    }

    public void saveCampaigns(List<Campaign> campaigns) {
        saveFile("/campaigns/", campaigns);
    }

    public void saveCampaign(String campName, Campaign camp) {
        saveFile(CAMPAIGN_PATH+campName, camp);
    }

    public void saveMap(String path, Map map ) {
        this.saveFile(path, map);
    }

    /**
     * A method for saving a character object
     *
     * @param charName the name of the character
     * @param character the character object
     * @throws IOException in case the path does not exist
     */
    public void saveCharacter(String charName, Character character) throws IOException {
        saveFile(CHARACTER_PATH +charName, character);
    }
}
