package main.java.org.model;

import main.java.org.Service.GameGenerator;
import main.java.org.Service.ObjectLoader;
import main.java.org.model.Character.BackPackInventory;
import main.java.org.model.Character.Character;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to allow user to create and edit Campaign
 *
 * @author Freyja Jokulsdottir
 * @version 2.0
 * @since 2017-02-05
 */
@XmlRootElement
public class Campaign implements Serializable {
    private ArrayList<String> mapNames = new ArrayList<>();
    private List<Map> maps = new ArrayList<>();
    private String name;
    private int numLevels;

    /**
     * A default constructor
     */
    public Campaign() {
        this.maps = new ArrayList<>();
    }

    /**
     * This is the campaign object to be created or edited
     *
     * @param mapNames these are a list of maps which are in the campaign object
     */
    public Campaign(ArrayList<String> mapNames, String name, int numLevels) {
        this.mapNames = mapNames;
        this.name = name;
        this.numLevels = numLevels;
        this.maps = new ArrayList<>();
    }

    /**
     * This method is loading maps in a campaign
     * @param character
     * @return
     */
    public List<Map> getMapsFromCampaign(Character character) {
        List<Map> maps = new ArrayList<>();

        for (String mapName : mapNames) {
            try {
                Map map =new ObjectLoader().loadMapFromXML(mapName);
                map.setPlayer(character);
                maps.add(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return maps;
    }

    public void setMaps(List<Map> maps) {
        this.maps = maps;
    }

    //The below annotation will exclude the file from saving the maps
    @XmlTransient
    public List<Map> getMaps() {
        return maps;
    }

    /**
     * A method to set the name of the campaign object
     *
     * @param name the name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A method fot getting the name of a campaign
     *
     * @return the name as a String
     */
    public String getName() {
        return this.name;
    }

    /**
     * A method to get the number of levels in a campaign
     *
     * @return numLevels the number of levels in the campaign
     */
    public int getNumLevels() {
        return this.numLevels;
    }

    /**
     * A method for getting the mapNames of the campaign
     *
     * @return a list of strings containing the map names
     */
    @XmlElement
    public List<String> getMapNames() {
        return mapNames;
    }

    /**
     * A method to set the number of levels in a campaign
     */
    public void setNumLevels(int num) {
        this.numLevels += num;
    }

    /**
     * This is the method for adding maps by names to the campaign
     *
     * @param mapName a name of a map which will be added to the list of levels in the campaign
     */
    public boolean setMapNames(String mapName) {
        ObjectLoader ol = new ObjectLoader();
        boolean mapExist = false;
        try {
            Map map = ol.loadMapFromXML(mapName);
            mapExist = false;
        } catch (Exception e) {
            System.out.println("Sorry this map does not exist!");
            mapExist = true;
        }
        if (mapExist == false)
            this.mapNames.add(mapName);

        return mapExist;
    }

    /**
     * This is the method for getting the maps connected by names
     *
     * @param mapName
     * @return a map object
     */
    public Map getMap(String mapName) {
        // Get Map using Map name
        try {
            Map map = ObjectLoader.loadMapFromXML(mapName);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This is the method that retrieves campaigns for editing.
     *
     * @param campName the name of the campaign.
     * @return an existing campaign object.
     */
    public Campaign getCampaign(String campName) throws Exception {
        return loadCampaign(campName);
    }

    /**
     * A method to remove the last map or level in a campaign
     *
     * @param mapNames the list of mapNames in the campaign
     */
    public void removeLevel(List<String> mapNames) {
        if (mapNames.size() != 0)
            mapNames.remove(mapNames.size() - 1);
        this.numLevels -= 1;
    }

    /**
     * This method get the map in the current level, sets the level completed flag and returns the next level
     * @param completedMap the current map which is completed
     * @return the new level
     */
    public Map getNextLevel(Map completedMap) {
        maps.stream().
                filter(map -> map.getName().equals(completedMap.getName())).
                findFirst().
                get().setLevelCompleted(true);
       return maps.stream().
               filter(map -> !map.isLevelCompleted()).
               findFirst().get();
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
                "number of levels: " + numLevels +
                '}';
    }

    /**
     * A method for saving a campaign
     */
    public void saveCampaign() {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Campaign.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(this, new FileOutputStream("src/main/java/org/resources/campaigns/" + this.name));
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
    public Campaign loadCampaign(String name) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Campaign.class);
            Unmarshaller u = null;
            u = jc.createUnmarshaller();
            File f = new File("src/main/java/org/resources/campaigns/" + name);
            return (Campaign) u.unmarshal(f);
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

}
