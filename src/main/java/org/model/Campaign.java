package main.java.org.model;

import java.io.Serializable;
import java.util.List;

/**
 * Inner class to allow user to create and edit Campaigns.
 *
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

    public Campaign() {
    }

    /**
     * This is the method for creating campaigns
     * @param levels these are a list of maps which will be used to create or modify the campaigns
     * @return
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
     * @return
     */
    @Override
    public String toString() {
        return "Campaign{" +
                "levels=" + levels +
                '}';
    }
}
