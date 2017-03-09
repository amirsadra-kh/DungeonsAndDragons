package main.java.org.Service;
import main.java.org.model.*;
import main.java.org.model.Character;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;
import java.util.Arrays;
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
     * @param text input from user
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
        Scanner sc = new Scanner(System.in);
        String answer;
        boolean yn = true;
        Set<Item> wearingItem = new HashSet<Item>();


        Character character = new Character();
        Ability ability = new Ability();
        Strength strength = new Strength();
        character.setAbility(ability);

        System.out.println("please enter a name for character: ");
        charName = readText(charName);

        character.setCharName(charName);

        BackPackInventory backpack = new BackPackInventory();
        character.setBackPackInventory(backpack);

        System.out.println(ability.toString());



            for (int i = 1; i<8 && yn ; i++) {
                System.out.println("Please enter the name of the item no." + i +  "  that you want the character to wear from the list below:");
                Item item = new Item();
                new ObjectLoader().showItemNames("src/main/java/org/resources/items/");
                item = item.loadItem(readLine());
                if((wearingItem.contains(item.getItem()))){
                    System.out.println("You cannot wear the same type of item");
                    i = i-1;
                }else {
                    if (item.getEnhancementType() == EnhancementTypes.STRENGTH) {
                        ability.setStrength(ability.getStrength() + item.getEnhance());
                        ability.setDamageBonus(item.getEnhance()-1);
                        ability.setHitPoints();
                    } else if (item.getEnhancementType() == EnhancementTypes.CONSTITUTION) {
                        ability.setConstitution(ability.getConstitution() + item.getEnhance());
                    } else if (item.getEnhancementType() == EnhancementTypes.DEXTERITY) {
                        ability.setDexterity(ability.getConstitution() + item.getEnhance());
                    } else if (item.getEnhancementType() == EnhancementTypes.ARMORCLASS) {
                        ability.setArmorClass(ability.getArmorClass() + item.getEnhance() - 10);
                    } else if (item.getEnhancementType() == EnhancementTypes.ATTACKBONUS) {
                        ability.setAttackBonus(ability.getAttackBonus() + item.getEnhance());
                    } else if (item.getEnhancementType() == EnhancementTypes.DAMAGEBONUS) {
                        ability.setDamageBonus(item.getEnhance());
                    }

                    wearingItem.add(item);

                    System.out.println("Your Item had the followings:" + item.toString());
                    System.out.println("According to this new item your character have the following ability");
                    System.out.println(ability.toString());
                    System.out.println("do you  want to add the another item ? Y/N");

                    while (true) {
                        answer = sc.nextLine().trim().toLowerCase();
                        if (answer.equals("y")) {
                            yn = true;
                            break;
                        } else if (answer.equals("n")) {
                            yn = false;
                            break;
                        } else {
                            System.out.println("Sorry, I didn't catch that. Please answer y/n");
                        }
                    }
                }
            }
        System.out.println("THE CHARACTER HAS :");
        System.out.println(ability.toString());
        System.out.println("iT WEARS " + wearingItem);









        /* TODO fix backpack setItems, presently crash
        Item item = new Item();
        new ObjectLoader().showItemNames("src/main/java/org/resources/items/");
        item=item.loadItem(readLine());
        backpack.setItems(Arrays.asList(item.getItem()));
        */

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
        int choice = 0;

        System.out.println("Please enter the name of the character you would like to edit");
        charName = readText(charName);

        // TODO charName should be the path of the character
        ObjectLoader ol = new ObjectLoader();
        Character character = ol.loadCharacterFromXML(charName);

        // What does the user want to edit about the character?
        System.out.println("Which of these would you like to edit?");
        System.out.println("1. Ability\n2. Wearing Item\n3. BackPack\n4. Exit");
        choice = readInt(choice);


        switch (choice) {
            case 1:
                editAbility(character, charName);
                System.out.println();
                CharacterScreen();
                break;
            case 2:
                character = editWearingItem(character);
                System.out.println();
                CharacterScreen();
                break;
            case 3:
                character = editBackPack(character);
                System.out.println();
                CharacterScreen();
                break;
            case 4:
                backToMain();
        }
    }

    /**
     * A method to interact with the user for editing the ability of a character
     *
     * @param character the existing character to be edited
     * @return the edited character object
     */
    private void editAbility(Character character, String charName) {
        int level = 0;
        int choice = 0;
        Ability ability = character.getAbility();

        // Edit the level of the character
        System.out.println("Please enter the level you would like the character to be at (0 if not changed): ");
        level = readInt(level);
        if(level != 0)
            ability.setLevel(level);

        // Change the strength of the character
        System.out.println("Please enter the strength you would like the character to have (0 if not changed): ");
        choice = readInt(choice);
        if(choice != 0)
            ability.setStrength(choice);

        // Change the constitution of the character
        choice = 0;
        System.out.println("Please enter the constitution you would like the character to have (0 if not changed): ");
        choice = readInt(choice);
        if(choice != 0)
            ability.setConstitution(choice);

        // Change the dexterity of the character
        choice = 0;
        System.out.println("Please enter the dexterity you would like the character to have (0 if not changed): ");
        choice = readInt(choice);
        if(choice != 0)
            ability.setDexterity(choice);

        // Set the new ability for the character
        character.setAbility(ability);

        System.out.println("The other ability points depend on what the character has on item!\n");

        // Save changes?
        String save = "";
        System.out.println("Would you like to save your changes?Y/N");
        save = readText(save);
        while(!save.equals("Y") && !save.equals("N") && !save.equals("y") && !save.equals("n")) {
            System.out.println("Invalid input! Please try again: ");
            save = readText(save);
        }
        if(save.equals("Y") || save.equals("y"))
            Save(charName, character);
    }

    /**
     * A method to interact with the user for editing the items that character is wearing
     *
     * @param character the existing character to be edited
     * @return the edited character object
     */

    private Character editWearingItem(Character character) {

        return character;
    }


    private Character editBackPack(Character character) {
        return character;
    }

    /**
     * A method for overriding the edited character and exit the game
     *
     * @param charName name of the character
     * @param character the character object
     */
    private void Save(String charName, Character character) {
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
    private void backToMain(){
        new GameGenerator();
    }
}