package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.Item;
import main.java.org.model.CharacterPackage.Character;
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
    private final static String CHARACTER_PATH = "src/main/java/org/resources/characters/";

    /**
     * A method for saving a list of maps
     * TODO remove this method? It is not being used
     *
     * @param maps a list of maps to be saved
     */
    public void SaveMaps(List<Map> maps) {
        saveFile("/maps/", maps);
    }

    /**
     * A method to save an item
     * TODO remove this method? It is not being used
     *
     * @param path of the item to be saved
     * @param item object to be saved
     */
    public void saveItem(String path, Item item) {
        saveFile(path, item);
    }

    /**
     * A method to save a list of campaigns
     * TODO remove this method? It is not being used
     *
     * @param campaigns a list of campaigns to be saved.
     */
    public void saveCampaigns(List<Campaign> campaigns) {
        saveFile("/campaigns/", campaigns);
    }

    /**
     * A method to save a campaign
     * TODO remove this method? It is not being used
     *
     * @param campName the name of the campaign to be saved
     * @param camp the object to be saved
     */
    public void saveCampaign(String campName, Campaign camp) {
        saveFile(CAMPAIGN_PATH+campName, camp);
    }

    /**
     * A method for saving a map
     *
     * @param path of the map to be saved
     * @param map object to be saved
     */
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
