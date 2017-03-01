package main.java.org.Service;


import main.java.org.model.Campaign;
import main.java.org.model.Character;
import main.java.org.model.Map;

import java.io.*;
import java.util.List;

/**
 * This class is is to Save the Objects to the File.
 * + *
 * + * @author Maysam Mokarian
 * + * @version 1.0
 * + * @since 2017-02-08
 */

public class ObjectSaver extends FileProcessor {
    private final static String CAMPAIGN_PATH = "src/main/java/org/resources/campaigns/campaign.txt";
    private final static String TEMP_CAMPAIGN_PATH = "src/main/java/org/resources/campaigns/tempCampaign.txt";
    private final static String MAP_PATH = "src/main/java/org/resources/maps/map.txt";
    private final static String CHARACTER_PATH = "src/main/java/org/resources/characters/character.txt";
    private final static String TEMP_CHARACTER_PATH = "main/java/org/resources/characters/tempCharacter.txt";
    //TODO
    private final static String ITEM_PATH = "";

    public void SaveMaps(List<Map> maps) {
        saveFile("/maps/", maps);
    }

    public void saveCampaign(String camp) {
        //saveFile("/campaigns/", campaigns);
        writer(CAMPAIGN_PATH, camp);
    }

    public void editedCampaign(String newCamp, String oldCamp) {
        reWriter(CAMPAIGN_PATH, TEMP_CAMPAIGN_PATH, newCamp, oldCamp);
    }

    public void loadCharacters(List<Character> characters) {
        saveFile("/characters/", characters);
    }

    public void saveCharacter(String name) {
        writer(CHARACTER_PATH, name);
    }

    public void editedCharacter(String newChar, String oldChar) {
        reWriter(CHARACTER_PATH, TEMP_CHARACTER_PATH, newChar, oldChar);
    }

    public void loadCampaign(String path, Campaign campaign) {
        saveFile(path, campaign);
    }

    /**
     * A method for adding text to a txt file. For creating purposes
     *
     * @since 23.02.2017
     * @param path the path of the text file
     * @param object the object as a string to be added to the text file
     */
    private static void writer(String path, String object) {
        // Add a new object (Character, Map, Item or Campaign) information to text file.
        try(FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCharacter(String path, Character character) throws IOException {
        saveFile(path, character);
    }

    /**
     * A method for overriding text to a txt file. For editing purposes
     *
     * @since 23.02.2017
     * @param path the path of the text file
     * @param tempPath the temporary path for the re-writing
     * @param newObject the edited object as a string to be added to the text file
     * @param oldObject the old object (before editing) as a string to be replaces in the text file
     */
    private static void reWriter(String path, String tempPath, String newObject, String oldObject) {
        // Remove an old object (Character, Map, Item or Campaign) information from text file.
        BufferedReader br;
        BufferedWriter bw;
        String lineIn;

        // Try to open the file with the path given
        try {
            br = new BufferedReader(new FileReader(path));
            bw = new BufferedWriter(new FileWriter(tempPath));

            // Read each line of the file
            while ((lineIn = br.readLine()) != null) {
                if(!lineIn.contains(oldObject))
                    bw.write(lineIn +"\n");
            }
            bw.write(newObject);
            br.close();
            bw.close();

            br = new BufferedReader(new FileReader(tempPath));
            bw = new BufferedWriter(new FileWriter(path));

            // Read each line of the file
            while ((lineIn = br.readLine()) != null) {
                if(lineIn.equals(oldObject))
                    continue;
                bw.write(lineIn +"\n");
            }
            br.close();
            bw.close();
            // Show exception stack if file is not found
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
