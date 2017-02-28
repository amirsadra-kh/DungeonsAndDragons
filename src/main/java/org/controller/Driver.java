package main.java.org.controller;

import  main.java.org.model.Game;

import javax.swing.*;

public class Driver {


    public static void main(String[] args) throws Exception {

        Game game = Game.getInstance();
        game.startGame();

    }

    public String run() {
        return "Hello dungeons and dragons";
    }



}
