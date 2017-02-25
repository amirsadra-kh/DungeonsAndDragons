
package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.GameConstants;
import main.java.org.model.Map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
    public static String campaignsPath = "src/main/java/org/resources/campaigns/campaign.txt";
    public static String mapsPath = "src/main/java/org/resources/maps/map.txt";
    public static String charactersPath = "src/main/java/org/resources/characters/character.txt";
    public static String itemsPath;

    public static List<Campaign> loadCampaigns() {
        List<Campaign> campaign = (List<Campaign>) loadFile("resources/campaigns/campaign.txt");
        return campaign;
    }

    public static List<Character> loadCharacters() {
        return null;
    }

    public static Map loadMap(String mapName) throws Exception {
        Map map = new Map();
        boolean found = false;
        // Read the Map file
        ArrayList<String> mapFile = reader(mapsPath);
        for(int i = 0; i < mapFile.size(); i ++) {
            if(mapName.equals(mapFile.get(i))) {
                map.setName(mapName);
                found = true;
                // TODO Set rest of map attributes here to have a complete map object
            }

        }
        if(!found)
            throw new Exception("Map not found!");
        //return (Map) loadFile("./src/test/resources/files/maps/" + fileName);
        return map;
    }

    public static Campaign loadCampaign(String campName) throws Exception {
        List<Map> maps = new ArrayList<Map>();
        Campaign camp = new Campaign(maps);
        ArrayList<String> campaigns = new ArrayList<String>();
        boolean found = false;

        // Read campaign text file and save in campaigns list
        campaigns = reader(campaignsPath);

        // Search for campaign name to find the information about that campaign
        for(int i = 0; i < campaigns.size(); i++){
            if(campName.equals(campaigns.get(i))){
                camp.setName(campName);
                camp.setNumLevels(Integer.parseInt(campaigns.get(i+1)));
                for(int j = 1; j <= camp.getNumLevels(); j++) {
                    camp.addMap(campaigns.get(i+1+j));
                }
                found = true;
            }
        }

        if(!found)
            throw new Exception("Campaign not found!");

        // Return the campaign found
        return camp;
    }

    public static Character loadCharacter(String path) {
        return null;
    }

    /**
     * A method for reading a txt file and storing the text in a list.
     *
     * @author Freyja
     * @since 23.02.2017
     * @param path the path of a txt file
     * @return a list of strings of the text from the txt file
     */
    public static ArrayList<String> reader(String path) {
        BufferedReader buffer = null;
        String lineIn = "";
        int i = 0;
        ArrayList<String> list = new ArrayList<String>();

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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Return the list of words from the file.
        return list;
    }
}
