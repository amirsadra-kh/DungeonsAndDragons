package main.java.org.Service;


import main.java.org.model.Campaign;
import main.java.org.model.Map;

import java.io.IOException;
import java.util.List;

public class ObjectSaver extends  FileProcessor{
    public void SaveMaps(List<Map> maps) throws IOException {
        saveFile("/maps/",maps);
    }

    public void saveCampaigns(List<Campaign> campaigns) throws IOException {
        saveFile("/campaigns/",campaigns);
    }

    public void loadCharacters(List<Character> characters) throws IOException {
        saveFile("/characters/",characters);
    }

    public void saveMap(String path, Map map) throws IOException {
        saveFile(path,map);
    }

    public void loadCampaign(String path, Campaign campaign) throws IOException {
        saveFile(path,campaign);
    }

    public void saveCharacter(String path, Character character) throws IOException {
        saveFile(path,character);
    }
}
