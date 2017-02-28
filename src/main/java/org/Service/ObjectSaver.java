package main.java.org.Service;


import main.java.org.model.Campaign;
import main.java.org.model.Item;
import main.java.org.model.Map;

import java.util.List;
import java.util.UUID;

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

    public void SaveItem(Item item) {
        saveFile("/items/"+ UUID.randomUUID(), item);
    }

    public void saveCampaigns(List<Campaign> campaigns) {
        saveFile("/campaigns/", campaigns);
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


}
