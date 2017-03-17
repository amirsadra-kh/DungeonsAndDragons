package main.java.org.model;

import main.java.org.Service.GameGenerator;
import main.java.org.Service.ObjectLoader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class to allow user to create and edit Campaign

 * @author Freyja Jokulsdottir
 * @version 2.0
 * @since 2017-02-05
 */
@XmlRootElement
public class Campaign implements Serializable {
    //private List<Map> levels;
    private ArrayList<String> mapNames = new ArrayList<>();
    private String name;
    private int numLevels;

    /**
     * A default constructor
     */
    public Campaign() {
    }

    /**
     * This is the campaign object to be created or edited
     * @param mapNames these are a list of maps which are in the campaign object
     */
    public Campaign(ArrayList<String> mapNames, String name, int numLevels) {
        this.mapNames=mapNames;
        this.name = name;
        this.numLevels = numLevels;
    }

    /**
     * This is the method for returning finished campaigns
     * @param mapNames these are a list of maps which were used to create or modify the campaign
     * @return Campaign A new campaign created by the user or an edited campaign
     */
    private Campaign generateCampaign(ArrayList<String> mapNames,  String name, int numLevels) {
        Campaign campaign = new Campaign(mapNames, name, numLevels);
        return campaign;
    }

    /*
    public List<Map> getLevels() {
        return levels;
    }
    */
    public void setName(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }

    /**
     * A method to get the number of levels in a campaign
     *
     * @return numLevels the number of levels in the campaign
     */
    public int getNumLevels() {
        return this.numLevels;
    }
    @XmlElement
    public List<String> getMapNames() {
        return  mapNames;
    }

    /**
     * A method to set the number of levels in a campaign
     */
    public void setNumLevels(int num) {
            this.numLevels += num;
    }

    /**
     * This is the method for adding maps by names to the campaign
     * @param mapName a name of a map which will be added to the list of levels in the campaign
     */
    public boolean setMapNames(String mapName){
        ObjectLoader ol = new ObjectLoader();
        boolean mapExist = false;
        try {
            Map map = ol.loadMapFromXML(mapName);
            mapExist = false;
        } catch (Exception e) {
            System.out.println("Sorry this map does not exist!");
            mapExist = true;
        }
        if(mapExist == false)
            this.mapNames.add(mapName);

        return mapExist;
    }

    /**
     * This is the method for getting the maps connected by names
     * @param mapName
     * @return a map object
     */
    public Map getMap(String mapName){
        // Get Map using Map name
        try {
            Map map = ObjectLoader.loadMapFromXML(mapName);
            return map;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This is the method that retrieves campaigns for editing.
     * @param campName the path of the campaign.
     * @return an existing campaign object.
     */
    public Campaign getCampaign(String campName) throws Exception {
        return this.loadCampaign(campName);
        //ObjectLoader ol = new ObjectLoader();
        //return ol.loadCampaignFromXML(campName);
    }

    /*
    public void setLevels(List<Map> levels) {

        this.levels = levels;
    }
    */
    public void removeLevel(List<String> mapNames) {
        if(mapNames.size() != 0)
            mapNames.remove(mapNames.size() - 1);
        this.numLevels -= 1;
    }

    /**
     * A method to get the map to play and that goes back to main menu if the campaign is finished.
     *
     * @param levelsPlayed the number of levels already played in this campaign
     * @param character the player character to be added to the map
     * @return the map to play or go back to main menu
     */
    public Map nextLevel(int levelsPlayed, Character character) throws Exception {
        // Check if there are any levels left to play
        if(levelsPlayed <= numLevels) {
            //get the name of the next level to play
            String nextMap = this.mapNames.get(levelsPlayed);
            // Set the current map to the next map to play
            Map currentMap = getMap(nextMap);

            // Add character player to the map
            currentMap.addPlayer(character);

            // Modify the level of the characters on the map according to the player
            List<Character> mapChars = currentMap.getNonPLayerCharacters();
            for (Character mapChar : mapChars) {
                mapChar.setLevel(character.getLevel());
            }
            currentMap.setNonPlayerCharacters(mapChars);

            // Set the enhance of the items on a map according to the level
            BackPackInventory chest = currentMap.getChest();
            Set<Item> chestItems = chest.getItems();
            for(Item item : chestItems) {
                item.setItemOnMapEnhancement(character.getLevel());
            }
            chest.setItems(chestItems);
            currentMap.setChest(chest);

            return currentMap;
        }
        // The game is finished! Go back to main menu
        else {
            System.out.println("CONGRATULATIONS YOU WON!!");
            GameGenerator game = new GameGenerator();
            game.showMenuToStartTheGame();
        }
        return null;
    }

    /**
     * This method returns a Campaign which will be used for playing
     *
     * @return Information about the Campaign
     */
    @Override
    public String toString() {
        return "Campaign{" + name +
                " levels=" + mapNames +
                "number of levels: " +numLevels +
                '}';
    }

    /**
     * A method for saving a campaign
     */
    public void saveCampaign()  {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Campaign.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(this,new FileOutputStream("src/main/java/org/resources/campaigns/"+this.name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A method for loading an existing campaign
     *
     * @param name of the campaign
     * @return an existing campaign object
     */
    public Campaign loadCampaign(String name){
        try {
            JAXBContext jc = JAXBContext.newInstance(Campaign.class);
            Unmarshaller u = null;
            u = jc.createUnmarshaller();
            File f = new File("src/main/java/org/resources/campaigns/"+name);
            return (Campaign) u.unmarshal(f);
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

}
