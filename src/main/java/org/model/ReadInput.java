package main.java.org.model;

import java.util.IllegalFormatException;
import java.util.Scanner;

import static main.java.org.model.GameConstantsInterface.NOT_A_STRING;
import static main.java.org.model.GameConstantsInterface.WRONG_DIRECTION_MESSDAGE;

/**
 * A class for reading user input to prevent copied code in Screen classes
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-16.
 */
public class ReadInput {
    /**
     * A method to read input to prevent copied code
     *
     * @return a String input from user
     */
    public String readLine() {
        final Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * This method is to read the coordinate from user with exception handling
     *
     * @return
     */
    public String readCoordinate() {
        String coordinate = "";
        try {
            coordinate = readLine();
            while (!"u".equalsIgnoreCase(coordinate) &&
                    !"d".equalsIgnoreCase(coordinate) &&
                    !"l".equalsIgnoreCase(coordinate) &&
                    !"r".equalsIgnoreCase(coordinate) &&
                    !"exit".equalsIgnoreCase(coordinate)
                    ) {
                System.out.println(WRONG_DIRECTION_MESSDAGE);
                coordinate = readLine();
            }
        } catch (final IllegalFormatException Ex) {
            System.out.println(NOT_A_STRING);
        }
        return coordinate;

    }

    /**
     * A method that reads in a string, verifies it is a string and returns it.
     *
     * @param line to be read
     * @return new line after reading input
     */
    public String readStringHandling(String line) {
        try {
            line = readLine();
        } catch (final IllegalFormatException e) {
            System.out.println(NOT_A_STRING);
            System.out.println(GameConstantsInterface.CHOSEN_ITEM_NOT_VALID);
        }
        return line;
    }

    /**
     * A method that reads in a number, verifies it is a number and returns it.
     *
     * @param num to be read
     * @return new num after reading input
     */
    public int readIntHandling(int num) {
        try {
            num = Integer.parseInt(readLine());
        } catch (final NumberFormatException e) {
            System.out.println(GameConstantsInterface.NOT_A_NUMBER);
            System.out.println(GameConstantsInterface.CHOSEN_ITEM_NOT_VALID);
        }
        return num;
    }

    /**
     * A method to handle a user's input
     * @return boolean true repeat or false break loop
     */
    public boolean askUserIfAgain() {
        String answer = "";

        while (true) {
            answer = readLine().trim().toLowerCase();
            if (answer.equals("y")) {
                return true;
            } else if (answer.equals("n")) {
                return false;
            } else {
                System.out.println("Sorry, I didn't catch that. Please answer y/n");
            }
        }
    }

    // TODO figure out a way to close the scanner
}
