package main.java.org.Service;
import main.java.org.model.Ability;
import main.java.org.model.BackPackInventory;
import main.java.org.model.Character;
import main.java.org.model.GameConstants;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class is to interact with user to create the character, save it and edit it.
 *
 * @author Parisa Nikzad/Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-02-23
 */
public class CharacterScreen {
    /**
     * A Scanner for reading input from user
     * TODO we should find a way to close the scanner when it is not need anymore.
     *
     * @return a String which has been read from input
     */
    private String readLine(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * A method for reading an integer input from user and handling a wrong input
     *
     * @param num an input from the user
     * @return the integer if it was in fact an integer
     */
    private int readInt(int num){
        try{
            num = Integer.parseInt(readLine());
        } catch (NumberFormatException e){
            System.out.println(GameConstants.NOT_A_NUMBER);
            System.out.println(GameConstants.CHOSEN_ITEM_NOT_VALID);
        }
        return num;
    }

    /**
     * A method for reading in text input from user and handling the wrong input
     *
     * @param text input from userr
     * @return the text if it was in fact a string
     */
    private String readText(String text){
        try{
            text = readLine();
        } catch (NumberFormatException e){
            System.out.println(GameConstants.NOT_A_STRING);
            System.out.println(GameConstants.CHOSEN_ITEM_NOT_VALID);
        }
        return text;
    }

    /**
     * The interactino screen with the user to create or edit a character
     *
     * @throws Exception
     */
    public void CharacterScreen() throws Exception {
        int choice = 0;


        // Let user choose an action - Create or Edit a Character
        System.out.println("Choose one of the following by entering the number associated with the choice:");
        System.out.println("1. Create a Character\n2. Edit a Character\n3. Back to Main Menu");
        while(choice == 0)
            choice = readInt(choice);

        // If the user enters an invalid input, they will be asked again
        while (choice < 1 || choice > 3) {
            System.out.println("Your input is invalid, please try again");
            choice = readInt(choice);
        }

        switch (choice) {
            case 1:
                createCharacterScreen();
                break;
            case 2:
                editCharacterScreen();
                break;
            case 3:
                backToMain();
                break;
            }
    }

    /**
     * The interaction screen with iuser to create a new character
     */
    public void createCharacterScreen(){
        String charName = "";

        Character character = new Character();
        Ability ability = new Ability();
        character.setAbility(ability);

        System.out.println("please enter a name for character: ");
        charName = readText(charName);

        character.setCharName(charName);

        BackPackInventory backpack = new BackPackInventory();
        character.setBackPackInventory(backpack);

        System.out.println(ability.toString());

        // TODO charName should be the path of the character
        ObjectSaver os = new ObjectSaver();
        try {
            os.saveCharacter(charName, character);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The interaction screen with user to edit an existing character.
     *
     * @throws Exception
     */
    public void editCharacterScreen() throws Exception {
        String charName = "";
        Character character = new Character();

        System.out.println("Please enter the name of the character you would like to edit");
        charName = readText(charName);

        character.setCharName(charName);

        // TODO charName should be the path of the character
        ObjectLoader ol = new ObjectLoader();
        character = ol.loadCharacter(charName);

        ObjectSaver os = new ObjectSaver();
        try {
            os.saveCharacter(charName, character);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method to go back to the main menu
     */
    public void backToMain(){
        new GameGenerator();
    }



}
