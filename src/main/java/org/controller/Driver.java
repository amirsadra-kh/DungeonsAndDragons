package main.java.org.controller;

import  main.java.org.model.Game;

import javax.swing.*;

/**
 * This class is is to run the program
 *
 * @author Maysam Mokarian
 * @version 1.0
 * @since 02.08.2017
 */
public class Driver {


    public static void main(String[] args) throws Exception {

        Game game = Game.getInstance();
        game.startGame();

    }

    public String run() {
        return "Hello dungeons and dragons";
    }



}
