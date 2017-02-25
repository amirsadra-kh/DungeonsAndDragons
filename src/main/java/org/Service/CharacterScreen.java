package main.java.org.Service;
import main.java.org.model.Ability;
import main.java.org.model.BackPackInventory;
import main.java.org.model.Character;

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

    /* TODO: 2/25/2017
    String readText(){
        Scanner scan = new Scanner(System.in);
        String str =scan.nextLine();
...your exception handling...
    }

    int readInt(){
        Scanner scan = new Scanner(System.in);
        scan.nextInt();
..your exception handling...
    }*/

    public void CharacterScreen(){
        Scanner scan = new Scanner(System.in);
        int choice = 0;


        // Let user choose an action - Create or Edit a Character
        System.out.println("Choose one of the following by entering the number associated with the choice:");
        System.out.println("1. Create a Character\n2. Edit a Character\n3. Back to Main Menu");
        while(choice == 0)
            choice = scan.nextInt();

        // If the user enters an invalid input, they will be asked again
        while (choice < 1 || choice > 3) {
            System.out.println("Your input is invalid, please try again");
            choice = scan.nextInt();
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

        Character char1 = new Character();
        Ability ability = new Ability();
        char1.setAbility(ability);

        System.out.println("please enter a name for character: ");
        charName = scan.nextLine();

        BackPackInventory backpack = new BackPackInventory();
        char1.setBackPackInventory(backpack);

        System.out.println(ability.toString());

        // TODO charName should be the path of the character
        ObjectSaver os = new ObjectSaver();
        try {
            os.saveCharacter(charName, char1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        scan.close();
    }

    public void editCharacterScreen(){
        Scanner scan = new Scanner(System.in);
        String charName = "";
        Character character = new Character();

        System.out.println("Please enter the name of the character you would like to edit");
        charName = scan.nextLine();

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
