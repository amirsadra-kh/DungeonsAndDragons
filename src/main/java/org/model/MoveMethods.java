package main.java.org.model;

import main.java.org.model.CharacterPackage.Character;

import java.awt.*;
import java.util.Random;

/**
 * A Move class for the different characters
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 02.04.2017
 */
public class MoveMethods {
    /**
     * A method for moving a friendly NPC randomly
     * @param fchar
     */
    public static void friendlyMove(Character fchar) {
        Random r = new Random();
        // Get the current position
        Point current = fchar.getCurrentPosition();
        // Get a random number to move from 1 to 3 on the x axis
        int x = r.nextInt(3);
        // Subtract the movement made on the x axis from 3
        // This has to be done cause the character is only allowed to move 3 map cells
        // if x = 2 the user only has one cell left to move and so on.
        int y = (int) current.getY() + (3 - x);
        // Add the random movement on x to the current x
        x += (int) current.getX();

        //TODO add out of bounds check

        // Set the new position
        fchar.setCurrentPosition(new Point(x,y));
    }

    /**
     * A method for moving a player character - by choice
     * @param player
     */
    public static void playerMove(Character player) {

    }

    /**
     * A method for moving a hostile character - move towards player
     * @param monster
     */
    public static void hostileMove(Character monster, Character player) {

    }

    /**
     * A method for moving a computer player - move towards objective of map or towards exit
     * @param compPlayer
     */
    public static void computerPlayerMove(Character compPlayer, Point objective) {

    }
}
