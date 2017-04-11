package main.java.org.Service.CharacterScreens;

import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.ReadInput;

/**
 * This class is to interact with user to create the character, save it and edit it.
 *
 * @author Freyja Jokulsdottir
 * @version 2.0
 * @since 2017-02-23
 */
public class CharacterScreen {
    private ReadInput readInput = new ReadInput();
    private Character character;

    /**
     * A choice given to the user to create, edit or go back to main menu
     * @throws Exception
     */
    public void choiceScreen() throws Exception {
        int choice = 0;

        // Let user choose an action - Create or Edit a CharacterPackage
        System.out.println("Choose one of the following by entering the number associated with the choice:");
        System.out.println("1. Create a CharacterPackage\n2. Edit a CharacterPackage\n3. Back to Main Menu");
        while(choice == 0)
            choice = readInput.readIntHandling(choice);

        // If the user enters an invalid input, they will be asked again
        while (choice < 1 || choice > 3) {
            System.out.println("Your input is invalid, please try again");
            choice = readInput.readIntHandling(choice);
        }
        switch (choice) {
            case 1:
                CreateCharacterScreen create = new CreateCharacterScreen();
                this.character = create.createChar();
                chooseToSave(this.character);
                break;
            case 2:
                EditCharacterScreen edit = new EditCharacterScreen();
                this.character = edit.editChar();
                chooseToSave(this.character);
                break;
            case 3:
                break;
        }
    }

    /**
     * A method that asks the user if they want to save their changes.
     *
     * @param character the character object to be saved
     */
    private void chooseToSave(Character character) {
        // Save changes?
        String save = "";
        System.out.println("Would you like to save your changes?Y/N");
        save = readInput.readStringHandling(save);
        while(!save.equals("Y") && !save.equals("N") && !save.equals("y") && !save.equals("n")) {
            System.out.println("Invalid input! Please try again: ");
            save = readInput.readStringHandling(save);
        }
        if(save.equals("Y") || save.equals("y")) {
            character.saveCharacter();
        }
    }
}
