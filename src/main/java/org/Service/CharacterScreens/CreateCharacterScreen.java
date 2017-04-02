package main.java.org.Service.CharacterScreens;

import main.java.org.model.*;
import main.java.org.model.CharacterPackage.*;
import main.java.org.model.CharacterPackage.Character;

/**
 * This class is to interact with user to create the character
 *
 * @author Freyja Jokulsdottir
 * @version 2.0
 * @since 2017-02-23
 */
public class CreateCharacterScreen {
    private Character character = new Character();
    ReadInput readInput = new ReadInput();

    /**
     * A method to interact with the user to create a character
     * @return a character object
     */
    public Character createChar() {
        character.newCharacter();

        // Ask user for the name of the new character and verify it does not exist
        String charName = setName();
        character.setCharName(charName);

        // Ask user for the fighter type of the new character
        String fighterType = setFighterType();

        // Use the fighterType to set the builder type
        AbilityScoreBuilder builder;
        if(fighterType.equals("bully"))
            builder = new BullyBuilder();
        else if(fighterType.equals("nimble"))
            builder = new NimbleBuilder();
        else
            builder = new TankBuilder();

        // Use the director to direct the ability build
        AbilityScoreDirector director = new AbilityScoreDirector();
        director.setBuilder(builder);
        director.constructAbility();

        // Set the ability of the character using the ability the director got
        Ability ability = director.getAbility();
        character.setAbility(ability);

        // Set the backpack inventory of the new character
        BackpackScreen backpackScreen = new BackpackScreen();
        backpackScreen.createBackpack(this.character);

        // Set the items the character is wearing
        ItemsWearingScreen itemsWearingScreen = new ItemsWearingScreen();
        itemsWearingScreen.createItemsWearing(this.character);

        return this.character;
    }

    /**
     * A method to interact with the user to get the name of the new character
     * @return charName
     */
    private String setName() {
        String charName = "";
        boolean charExist = true;

        // Check if a character with the name chosen already exists and prompt for a new name if it does
        while("".equalsIgnoreCase(charName) || GameConstantsInterface.CHOSEN_ITEM_NOT_VALID.equalsIgnoreCase(charName) || charExist) {
            System.out.println("Enter the name of the new Character (No spaces): ");
            charName = readInput.readStringHandling(charName);

            // Check if a campaign with the name chose already exists
            if(character.loadCharacter(charName) == null) {
                charExist = false;
            }
            else {
                System.out.println("A Character with this name already exists!");
            }
        }

        return charName;
    }

    /**
     * A method to interact with the user to set the fighter type of the new character
     * @return fighterType
     */
    private String setFighterType() {
        String fighterType = "";
        boolean typeIncorrect = true;

        while("".equalsIgnoreCase(fighterType) || GameConstantsInterface.CHOSEN_ITEM_NOT_VALID.equalsIgnoreCase(fighterType) || typeIncorrect) {
            System.out.println("Enter the fighter type of the new CharacterPackage (No spaces): (Bully/Nimble/Tank)");
            fighterType = readInput.readStringHandling(fighterType);
            fighterType = fighterType.toLowerCase();
            if(fighterType.equals("bully") || fighterType.equals("nimble") || fighterType.equals("tank"))
                typeIncorrect = false;
            else
                System.out.println("This type is invalid! Try again!");

        }
        return fighterType;
    }
}
