package main.java.org.model;

import java.io.Serializable;
import java.util.List;

public class Campaign implements Serializable {

    private List<Map> levels;

    public Campaign(final List<Map> levels) {
        this.levels = levels;
    }

    Campaign generateCampaign(final List<Map> levels) {
        return new Campaign(levels);
    }

    public List<Map> getLevels() {
        return levels;
    }

    public void setLevels(List<Map> levels) {
        this.levels = levels;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "levels=" + levels +
                '}';
    }
}
