package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * A view for creating a Campaign
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-02-17
 */
public class CampaignScreen {
    private Campaign newCamp;

    /**
     *  A basic interaction screen after the user chooses Campaign
     */
    public void CampaignScreen() {
        Scanner scan = new Scanner(System.in);
        int choice = 0;

        // Let user choose an action - Create or Edit a Campaign
        System.out.println("Choose one of the following by entering the number associated with the choice:");
        System.out.println("1. Create a Campaign\n2. Edit a Campaign\n3. Back to Main Menu");
        choice = scan.nextInt();

        // If the user enters an invalid input, they will be asked again
        while (choice < 1 || choice > 3) {
            System.out.println("Your input is invalid, please try again");
            choice = scan.nextInt();
        }

        scan.close();

        switch (choice) {
            case 1:
                createCampaignScreen();
                break;
            case 2:
                editCampaignScreen();
                break;
        }
    }

    /**
     * This method prompts the user for information to create a Campaign
     */
    public void createCampaignScreen() {
        Scanner scan = new Scanner(System.in);
        int choice = 1;

        // Create a new Campaign
        List<Map> levels = new ArrayList<Map>();
        Campaign camp = new Campaign(levels);

        // Add a Map
        while(choice == 1) {
            //Get Map input from user
            String mapName = "";
            System.out.println("Enter the name of the Map you would like to add:");
            mapName = scan.nextLine();

            //Send Map input to CampaignModule
            camp.addMap(mapName);

            System.out.println("Enter 0 to stop adding maps or 1 to add more maps:");
            choice = scan.nextInt();
        }
        //Save Campaign

        scan.close();

        //Back to Main Menu without saving
        this.newCamp = camp;
    }

    /**
     * This method prompts the user for information to edit a Campaign
     *
     */
    public void editCampaignScreen() {
        Scanner scan = new Scanner(System.in);
        String campName = "";
        int choice = 0;
        List<Map> levels = new ArrayList<Map>();
        Campaign camp = new Campaign(levels);

        System.out.println("Enter the name of the Campaign you would like to edit:");
        campName = scan.nextLine();

        camp = camp.getCampaign(campName);

        System.out.println("Choose one of the following by entering the number associated with the choice:");
        System.out.println("1. Add a Map\n2. Remove a Map\n3. Back to Main Menu");
        choice = scan.nextInt();

        // If the user enters an invalid input, they will be asked again
        while (choice < 1 || choice > 3) {
            System.out.println("Your input is invalid, please try again");
            choice = scan.nextInt();
        }

        // Add a Map
        while(choice == 1) {
            //Get Map input from user
            String mapName = "";
            System.out.println("Enter the name of the Map you would like to add:");
            mapName = scan.nextLine();

            //Send Map input to CampaignModule
            camp.addMap(mapName);

            System.out.println("Enter 0 to stop adding maps or 1 to add more maps:");
            choice = scan.nextInt();
        }

        // Remove a Map - the last one
        while(choice == 2) {
            levels = camp.getLevels();

            // Remove the last map if there are any maps in the Campaign
            if (camp.getNumLevels() != 0) {
                camp.removeLevel(levels);
            } else
                System.out.println("There are no maps left in this campaign");

            System.out.println("Enter 0 to stop removing maps or 2 to remove more maps:");
            choice = scan.nextInt();
        }

        scan.close();

        this.newCamp = camp;
    }

    /**
     * Return camp
     */
    public Campaign getNewCamp() {
        return newCamp;
    }


    /**
     * This method should look the same for all screens.
     * TODO
     */
    public void backToMain(){

    }
}
