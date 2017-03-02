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

    public void saveCampaign(String camp) {
        //saveFile("/campaigns/", campaigns);
        writer(CAMPAIGN_PATH, camp);
    }

    public void loadCharacters(List<Character> characters) {
        saveFile("/characters/", characters);
    }

    public void saveCharacter(String name) {
        writer(CHARACTER_PATH, name);
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
