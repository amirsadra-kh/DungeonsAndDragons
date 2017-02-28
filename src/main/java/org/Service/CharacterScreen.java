package main.java.org.Service;
import main.java.org.model.Ability;
import main.java.org.model.BackPackInventory;
import main.java.org.model.Character;
import main.java.org.model.GameConstants;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class is make the character, save it and edit the character.
 *
 * @author Parisa Nikzad
 * @version 1.0.0
 * @since 2017-02-23
 */


public class CharacterScreen {

    private String readLine(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private int readInt(int num){
        try{
            num = Integer.parseInt(readLine());
        } catch (NumberFormatException e){
            System.out.println(GameConstants.NOT_A_NUMBER);
            System.out.println(GameConstants.CHOSEN_ITEM_NOT_VALID);
        }
        return num;
    }

    private String readText(String text){
        try{
            text = readLine();
        } catch (NumberFormatException e){
            System.out.println(GameConstants.NOT_A_STRING);
            System.out.println(GameConstants.CHOSEN_ITEM_NOT_VALID);
        }
        return text;
    }


    public void CharacterScreen() throws Exception {
        Scanner scan = new Scanner(System.in);
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

        scan.close();

    }
    public void createCharacterScreen(){
        Scanner scan = new Scanner(System.in);
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
        scan.close();
    }

    public void editCharacterScreen() throws Exception {
        Scanner scan = new Scanner(System.in);
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

        scan.close();
    }

    public void backToMain(){
        new GameGenerator();
    }



}
