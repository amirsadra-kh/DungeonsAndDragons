
package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.Character;
import main.java.org.model.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is is to Load the Objects from the File.
 *
 * @author Maysam Mokarian/Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-02-08
 */

public class ObjectLoader extends FileProcessor {

    public static List<Map> loadMaps() {
        return null;
    }
    private final static String CAMPAIGN_PATH = "src/main/java/org/resources/campaigns/";
    private final static String MAP_PATH = "src/main/java/org/resources/maps/";
    private final static String CHARACTER_PATH = "src/main/java/org/resources/characters/";
    //TODO
    private final static String ITEM_PATH = "";

    // TODO
    public static List<Campaign> loadCampaigns() {
        List<Campaign> campaign = (List<Campaign>) loadFile(CAMPAIGN_PATH);
        return campaign;
    }

    public static List<Character> loadCharacters() {
        return null;
    }

    public static Map loadMapFromXML(String mapName) throws Exception {
        return (Map) loadFile(MAP_PATH +mapName);
    }

    public static Character loadCharacterFromXML(String charName) throws Exception {
        return (Character) loadFile(CHARACTER_PATH +charName);
    }

    /**
     * A method for reading a txt file and storing the text in a list.
     *
     * @since 23.02.2017
     * @param path the path of a txt file
     * @return a list of strings of the text from the txt file
     */
    private static ArrayList<String> reader(String path) {
        BufferedReader buffer;
        String lineIn;
        int i = 0;
        ArrayList<String> list = new ArrayList<>();

        // Try to open the file with the path given
        try {
            buffer = new BufferedReader(new FileReader(path));

            // Read each line of the file
            while ((lineIn = buffer.readLine()) != null) {
                // Split each line by commas
                String[] temp = lineIn.split(",");
                for(int j = 0; j < temp.length; j++){
                    //store words in list
                    list.add(temp[j]);
                }
            }
            buffer.close();
        // Show exception stack if file is not found
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Return the list of words from the file.
        return list;
    }

}
