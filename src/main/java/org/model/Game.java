
package main.java.org.model;

import main.java.org.Service.GameGenerator;
import main.java.org.Service.ObjectLoader;

import java.util.ArrayList;
import java.util.List;


public class Game {
    private static Game ourInstance = new Game();

    public static Game getInstance() {
        return ourInstance;
    }

    private Game() {

    }

    Map CreateMap(String[][] screen ){
        Map map =new Map();
        return map.generateMap(screen);

    }

    public void startGame() throws Exception {
        new GameGenerator().showMenuToStartTheGame();
    }

    Campaign CreateCampign(ArrayList<String> mapNames, String name, int numLevels){
        return new Campaign(mapNames, name, numLevels);
    }

    public List<Campaign> loadCampaigns(){
        return new ObjectLoader().loadCampaigns();
    }

    List<Map> loadMaps(){
        return new ObjectLoader().loadMaps();
    }

    public void Play(){

    }
}
