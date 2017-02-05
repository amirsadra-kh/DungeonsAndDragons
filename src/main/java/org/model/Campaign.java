package main.java.org.model;

import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Class to allow user to create and edit Campaigns.
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-02-05
 */

public class Campaign implements Serializable {

    private List<Map> levels;

    /**
     * This is the method ugets the list of maps which will be used in the creation or editing of a campaign
     * @param levels these are a list of maps which can be used to create or modify the campaigns
     */
    public Campaign(final List<Map> levels) {

        this.levels = levels;
    }

    /**
     * This is the Campaign object which represents the campaign created by the user
     */
    public Campaign() {
    }

    /**
     * This is the method which is used to create the Campaign
     *
     * @param numLevels the number of levels or maps the user wants to have in this campaign
     * @param start the first map in the campaign
     * @param end the last map in the campaign
     */
    public void createCampaign(int numLevels, Map start, Map end) {
        // A Campaign should have at least one map!
        if(numLevels == 0)
            throw new IllegalArgumentException("There should be at least one map!");

        // Each map represents one level and there has to be a specific start level and a specific end level.


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
     * This is the method for adding maps to the campaign
     * @param map a map which will be added to the list of levels in the campaign
     */
    public void addMap(final Map map){

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
