package main.java.org.Service.CharacterScreens;

import main.java.org.model.CharacterPackage.Ability;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.ReadInput;

/**
 * This class is to interact with user to edit the character
 *
 * @author Freyja Jokulsdottir
 * @version 2.0
 * @since 2017-02-23
 */
public class EditCharacterScreen {
    private Character character = new Character();
    ReadInput readInput = new ReadInput();

    /**
     * A main method for editing a character
     * @return
     */
    public Character editChar() {
        boolean yes = true;
        // Ask the user which character they would like to edit
        getCharacterFromUser();

        while(yes) {
            // Ask the user what they want to edit and direct them there
            getEditChoice();
            System.out.println("do you  want to edit another part of the character?");
            yes = readInput.askUserIfAgain();
        }

        return this.character;
    }

    /**
     * A method to interact with the uer to get the character to edit
     */
    private void getCharacterFromUser() {
        String charName = "";

        System.out.println("Please enter the name of the character you would like to edit");
        charName = readInput.readStringHandling(charName);

        while(this.character.loadCharacter(charName) == null) {
            System.out.println("No such character exists, try again: ");
            charName = readInput.readStringHandling(charName);
        }

        // Load the character for editing
        this.character = this.character.loadCharacter(charName);
    }

    private void getEditChoice() {
        int choice = 0;

        // What does the user want to edit about the character?
        System.out.println("Which of these would you like to edit?");
        System.out.println("1. Ability\n2. Wearing Item\n3. BackPack\n4. Exit");
        choice = readInput.readIntHandling(choice);

        switch (choice) {
            case 1:
                editAbility();
                break;
            case 2:
                ItemsWearingScreen itemsWearingScreen = new ItemsWearingScreen();
                itemsWearingScreen.editItemsWearing(this.character);
                break;
            case 3:
                BackpackScreen backpackScreen = new BackpackScreen();
                backpackScreen.editBackpack(this.character);
                break;
            case 4:
                break;
        }
    }

    /**
     * A method for editing the CharacterPackage's characteristics - ability and level
     */
    private void editAbility(){
        int level = 0;
        int choice = 0;
        Ability ability = this.character.getAbility();

        // Edit the level of the character
        System.out.println("Please enter the level you would like the character to be at (0 if not changed): ");
        level = readInput.readIntHandling(level);
        if(level != 0)
            this.character.setLevel(level);

        // Change the strength of the character
        System.out.println("Please enter the strength you would like the character to have (0 if not changed): ");
        choice = readInput.readIntHandling(choice);
        if(choice != 0)
            ability.setStrength(choice);

        // Change the constitution of the character
        choice = 0;
        System.out.println("Please enter the constitution you would like the character to have (0 if not changed): ");
        choice = readInput.readIntHandling(choice);
        if(choice != 0)
            ability.setConstitution(choice);

        // Change the dexterity of the character
        choice = 0;
        System.out.println("Please enter the dexterity you would like the character to have (0 if not changed): ");
        choice = readInput.readIntHandling(choice);
        if(choice != 0)
            ability.setDexterity(choice);

        // Set the new ability for the character
        this.character.setAbility(ability);

        System.out.println("The other ability points depend on what the character has on item!\n");
    }
}
