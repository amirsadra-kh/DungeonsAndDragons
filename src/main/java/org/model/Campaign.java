package main.java.org.model;

import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Class to allow user to create and edit Campaigns.
 *
 * @author Freyja Jokulsdottir
 * @version 1.5
 * @since 2017-02-05
 */

public class Campaign implements Serializable {

    private List<Map> levels;

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

    /**
     * A method to get the number of levels in a campaign
     *
     * @return numLevels the number of levels in the campaign
     */
    public int getNumLevels() {
        int numLevels = this.levels.size();
        return numLevels;
    }

    /**
     * This is the method for adding maps to the campaign
     * @param mapName a name of a map which will be added to the list of levels in the campaign
     */
    public void addMap(String mapName){
        // Get Map using Map name - TODO
        // Contemporary Map item until fetch method is finished
        Map map = new Map();

        this.getLevels().add(map);
    }

    public void setLevels(List<Map> levels) {

        this.levels = levels;
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
}
