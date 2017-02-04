package main.java.org.controller;

import main.java.org.Service.ObjectSaver;
import main.java.org.model.Campaign;
import  main.java.org.model.Game;

import java.util.List;

public class Driver {
    public static void main(String[] args) {

        Game game = Game.getInstance();
        List<Campaign> campaigns = game.loadCampaigns();
        ObjectSaver os = new ObjectSaver();
        game.Play();

    }

    public String run() {
        return "Hello dungeons and dragons";
    }

}
