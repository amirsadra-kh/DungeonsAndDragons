
package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Map;

import java.util.List;

/**
 * This class is is to Load the Objects from the File.
 *
 * @author Maysam Mokarian/Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-02-08
 */
public class ObjectLoader extends FileProcessor {
    private final static String CAMPAIGN_PATH = "src/main/java/org/resources/campaigns/";
    private final static String MAP_PATH = "src/main/java/org/resources/maps/";
    private final static String MAP_PATH_TEST = "src/test/resources/files/maps/";
    private final static String CHARACTER_PATH = "src/main/java/org/resources/characters/";

    /**
     * A method to load a list of maps
     * TODO figure out what the point of this method is.
     *
     * @return null
     */
    public static List<Map> loadMaps() {
        return null;
    }

    /**
     * A method for loading a list of campaigns
     * TODO figure out what the point of this method is.
     *
     * @return a list of campaigns
     */
    public static List<Campaign> loadCampaigns() {
        List<Campaign> campaign = (List<Campaign>) loadFile(CAMPAIGN_PATH);
        return campaign;
    }

    /**
     * A method for loading a list of characters
     * TODO remove this method? It is not being used.
     *
     * @return null
     */
    public static List<Character> loadCharacters() {
        return null;
    }

    /**
     * A method to load a map object
     *
     * @param mapName the name of the map to be saved
     * @return
     * @throws Exception
     */
    public static Map loadMapFromXML(String mapName) throws Exception {
        return (Map) loadFile(MAP_PATH +mapName);
    }

    /**
     * Method for loading maps for testing
     *
     * @param mapName the name of the map to test
     * @return a test map object
     * @throws Exception
     */
    public static Map loadMapTest(String mapName) throws Exception {
        return (Map) loadFile(MAP_PATH_TEST +mapName);
    }

    /**
     * A method to load a campaign object
     * TODO remove this method? It is not being used
     *
     * @param campName the name of the campaign to load
     * @return a campaign object with the name given
     * @throws Exception in case the campaign does not exist
     */
    public static Campaign loadCampaignFromXML(String campName) throws Exception {
        Campaign camp;
        try {
            camp = (Campaign) loadFile(CAMPAIGN_PATH + campName);
        } catch (Exception e) {
            camp = null;
        }
        return camp;
    }

    /**
     * A method to load a character object
     *
     * @param charName the name of the character to be loaded
     * @return the character object with the name given
     * @throws Exception in case the character with that name does not exist
     */
    public static Character loadCharacterFromXML(String charName) throws Exception {
        return (Character) loadFile(CHARACTER_PATH +charName);
    }
}
