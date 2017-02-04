package main.java.org.Service;


import main.java.org.model.Campaign;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ObjectSaver extends  FileProcessor{
    void SaveMaps(List<Map> maps) throws IOException {
        saveFile("/maps/",maps);
    }

    void saveCampaigns(List<Campaign> campaigns) throws IOException {
        saveFile("/campaigns/",campaigns);
    }

    void loadCharacters(List<Character> characters) throws IOException {
        saveFile("/characters/",characters);
    }

    void saveMap(String path, Map map) throws IOException {
        saveFile(path,map);
    }

    void loadCampaign(String path, Campaign campaign) throws IOException {
        saveFile(path,campaign);
    }

    void loadCharacter(String path, Character character) throws IOException {
        saveFile(path,character);
    }
}
