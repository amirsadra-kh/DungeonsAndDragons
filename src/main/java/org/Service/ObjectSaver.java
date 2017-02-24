package main.java.org.Service;


import main.java.org.model.Campaign;
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
    public void SaveMaps(List<Map> maps) {
        saveFile("/maps/", maps);
    }

    public void saveCampaign(String camp) {
        //saveFile("/campaigns/", campaigns);
        writer("resources/campaigns/campaign.txt", camp);
    }

    public void editedCampaign(String newCamp, String oldCamp) {
        rewriter("resources/campaigns/campaign.txt", newCamp, oldCamp);
    }

    public void loadCharacters(List<Character> characters) {
        saveFile("/characters/", characters);
    }

    public void saveMap(String path, Map map) {
        saveFile(path, map);
    }

    public void loadCampaign(String path, Campaign campaign) {
        saveFile(path, campaign);
    }

    /**
     * A method for adding text to a txt file. For creating purposes
     *
     * @author Freyja
     * @since 23.02.2017
     * @param path the path of the text file
     * @param object the object as a string to be added to the text file
     */
    public static void writer(String path, String object) {
        // Add a new object (Character, Map, Item or Campaign) information to text file.
        try(FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(object);
            fw.close();
            bw.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method for overriding text to a txt file. For editing purposes
     *
     * @author Freyja
     * @since 23.02.2017
     * @param path the path of the text file
     * @param newObject the edited object as a string to be added to the text file
     * @param oldObject the old object (before editing) as a string to be replaces in the text file
     */
    public static void rewriter(String path, String newObject, String oldObject) {
        BufferedReader buffer = null;
        String lineIn = "";

        // Try to open the file with the path given
        try {
            buffer = new BufferedReader(new FileReader(path));
            String text = "";

            // Read each line of the file
            while ((lineIn = buffer.readLine()) != null) {
                text += lineIn;
            }
            buffer.close();

            // Replace the old object with the new object
            String replacedText = text.replaceAll(oldObject, newObject);

            // Write the full text file again but with the new object instead of the old
            FileWriter fw = new FileWriter(path);
            fw.write(replacedText);
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
