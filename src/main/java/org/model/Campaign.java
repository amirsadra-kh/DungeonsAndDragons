package main.java.org.model;

import com.sun.xml.internal.txw2.annotation.XmlElement;
import main.java.org.Service.ObjectLoader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Class to allow user to create and edit Campaigns.
 *
 * @author Freyja Jokulsdottir
 * @version 1.5
 * @since 2017-02-05
 */
@XmlRootElement
public class Campaign implements Serializable {

    private List<Map> levels;
    private String name;
    private int numLevels;

    /**
     * A default constructor
     */
    public Campaign() {

    }

    /**
     * This is the campaign object to be created or edited
     * @param levels these are a list of maps which are in the campaign object
     */
    public Campaign(final List<Map> levels) {

        this.levels = levels;
    }

    /**
     * This is the method for returning finished campaigns
     * @param levels these are a list of maps which were used to create or modify the campaign
     * @return Campaign A new campaign created by the user or an edited campaign
     */
    Campaign generateCampaign(final List<Map> levels) {
        return new Campaign(levels);
    }

    public List<Map> getLevels() {
        return levels;
    }

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
        int numLevels = this.levels.size();
        this.numLevels = numLevels;
        return numLevels;
    }

    /**
     * A method to set the number of levels in a campaign
     */
    public void setNumLevels(int num) {
        if(numLevels < num) {
            this.numLevels = num;
        } else {
            // Remove any additional maps if the new numLevel is less than the previous
            int diff = num - numLevels;
            for(int i = numLevels -1; i <= num; i++) {
                levels.remove(i);
            }
            this.numLevels = levels.size();
        }
    }

    /**
     * This is the method for adding maps to the campaign
     * @param mapName a name of a map which will be added to the list of levels in the campaign
     */
    public void addMap(String mapName){
        // Get Map using Map name
        try {
            Map map = ObjectLoader.loadMapFromXML(mapName);
            this.levels.add(map);
        } catch(Exception e) {
            System.out.println("Map not found!");
        }
    }

    /**
     * This is the method that retrieves campaigns for editing.
     * @param campName the path of the campaign.
     * @return an existing campaign object.
     */
    public Campaign getCampaign(String campName) throws Exception {
        return this.loadCampaign(campName);
    }

    public void setLevels(List<Map> levels) {

        this.levels = levels;
    }

    public void removeLevel(List<Map> levels) {
        if(levels.size() != 0)
            levels.remove(levels.size() - 1);
    }

    /**
     * This method returns a Campaign which will be used for playing
     * @return Information about the Campaign
     */
    @Override
    public String toString() {
        return "Campaign{" +
                "levels=" + levels +
                '}';
    }

    /**
     * A method for changing the campaign into a string to be added to the Campaign text file.
     *
     * @return a string to be used to write to the text file.
     */
    public String campaignString() {
        List<Map> maps = this.levels;
        String mapName = "";
        // Add all the information about a campaign to one string
        String campaign = this.name
                +"," +this.numLevels;
        for(int i = 0; i < this.numLevels; i++) {
            mapName = maps.get(i).getName();
            campaign += "," +mapName;
        }

        // Return campaign information string
        return campaign;
    }

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


    public Campaign loadCampaign(String name){
        try {
            JAXBContext jc = JAXBContext.newInstance(Campaign.class);
            Unmarshaller u = null;
            u = jc.createUnmarshaller();
            File f = new File("src/main/java/org/resources/campaigns/"+name);
            return (Campaign) u.unmarshal(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
