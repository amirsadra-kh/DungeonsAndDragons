package main.java.org.model;

import main.java.org.model.CharacterPackage.Character;

/**
 * A class to be used to choose objects in play mode
 */
public class GameShoppingCard {
    Campaign campaign;
    Character character;

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
