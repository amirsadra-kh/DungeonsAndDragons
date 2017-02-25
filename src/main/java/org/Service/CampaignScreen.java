package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.GameConstants;
import main.java.org.model.Map;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Scanner;

/**
 * A view for creating a Campaign
 *
 * @author Freyja Jokulsdottir
 * @version 2.0
 * @since 2017-02-17
 */
public class CampaignScreen {
    private Campaign newCamp;

    String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     *  A basic interaction screen after the user chooses Campaign
     */
    public void CampaignScreen() throws Exception {
        int choice = 0;

        // Let user choose an action - Create or Edit a Campaign
        System.out.println("Choose one of the following by entering the number associated with the choice:");
        System.out.println("1. Create a Campaign\n2. Edit a Campaign\n3. Back to Main Menu");
        try {
            choice = Integer.parseInt(readLine());

            // If the user enters an invalid input, they will be asked again
            while (choice < 1 || choice > 3) {
                System.out.println("Your input is invalid, please try again");
                choice = Integer.parseInt(readLine());
            }
        } catch (NumberFormatException e) {
            System.out.println(GameConstants.NOT_A_NUMBER);
            System.out.println(GameConstants.CHOSEN_ITEM_NOT_VALID);
        }

        switch (choice) {
            case 1:
                createCampaignScreen();
                break;
            case 2:
                editCampaignScreen();
                break;
            case 3:
                return;
        }
    }

    /**
     * This method prompts the user for information to create a Campaign
     */
    public void createCampaignScreen() throws Exception {
        // Create a new Campaign
        List<Map> levels = new ArrayList<Map>();
        Campaign camp = new Campaign(levels);
        String name = "";
        int numLevels = 0;

        System.out.println("Enter the name of the new Campaign (No spaces): ");
        try {
            name = readLine();
        } catch (IllegalFormatException e) {
            System.out.println(GameConstants.NOT_A_STRING);
            System.out.println(GameConstants.CHOSEN_ITEM_NOT_VALID);
        }

        camp.setName(name);

        System.out.println("Enter the number of levels of the new Campaign: ");
        try {
            numLevels = Integer.parseInt(readLine());
        } catch (NumberFormatException e) {
            System.out.println(GameConstants.NOT_A_NUMBER);
            System.out.println(GameConstants.CHOSEN_ITEM_NOT_VALID);
        }

        camp.setNumLevels(numLevels);

        // Add a Map
        for(int i = 0; i < numLevels; i++) {
            //Get Map input from user
            String mapName = "";
            System.out.println("Enter the name of the Map you would like to add:");
            while(mapName.equals("")) {
                try {
                    mapName = readLine();
                } catch (IllegalFormatException e) {
                    System.out.println(GameConstants.NOT_A_STRING);
                    System.out.println(GameConstants.CHOSEN_ITEM_NOT_VALID);
                }
            }

            //Send Map input to CampaignModule
            camp.addMap(mapName);
        }
        //Save Campaign
        ObjectSaver os = new ObjectSaver();
        os.saveCampaign(camp.campaignString());

        CampaignScreen();

        this.newCamp = camp;
    }

    /**
     * This method prompts the user for information to edit a Campaign
     *
     */
    public void editCampaignScreen() throws Exception {
        String campName = "";
        int choice = 0;
        List<Map> levels = new ArrayList<Map>();
        Campaign camp = new Campaign(levels);

        System.out.println("Enter the name of the Campaign you would like to edit:");
        try {
            campName = readLine();
        } catch (IllegalFormatException e) {
            System.out.println(GameConstants.NOT_A_STRING);
            System.out.println(GameConstants.CHOSEN_ITEM_NOT_VALID);
        }

        camp = camp.getCampaign(campName);

        System.out.println("Choose one of the following by entering the number associated with the choice:");
        System.out.println("1. Add a Map\n2. Remove a Map\n3. Back to Main Menu");
        try {
            choice = Integer.parseInt(readLine());

            // If the user enters an invalid input, they will be asked again
            while (choice < 1 || choice > 3) {
                System.out.println("Your input is invalid, please try again");
                choice = Integer.parseInt(readLine());
            }
        } catch (NumberFormatException e) {
            System.out.println(GameConstants.NOT_A_NUMBER);
            System.out.println(GameConstants.CHOSEN_ITEM_NOT_VALID);
        }

        // Add a Map
        if(choice == 1) {
            //Get the number of maps the user would like to add
            int num = camp.getNumLevels();
            int addingNum = 0;
            System.out.println("Please enter the number of levels you would like to add: ");
            try {
                addingNum = Integer.parseInt(readLine());
            } catch (NumberFormatException e) {
                System.out.println(GameConstants.NOT_A_NUMBER);
                System.out.println(GameConstants.CHOSEN_ITEM_NOT_VALID);
            }

            num += addingNum;
            camp.setNumLevels(num);

            for(int i = 0; i < addingNum; i++) {
                //Get Map input from user
                String mapName = "";
                System.out.println("Enter the name of the Map you would like to add:");
                while (mapName.equals("")) {
                    try {
                        mapName = readLine();
                    } catch (IllegalFormatException e) {
                        System.out.println(GameConstants.NOT_A_STRING);
                        System.out.println(GameConstants.CHOSEN_ITEM_NOT_VALID);
                    }
                }

                //Send Map input to CampaignModule
                camp.addMap(mapName);
            }
        }

        // Remove a Map - the last one
        if(choice == 2) {
            int removeNum = 0;
            System.out.println("Please enter the number of levels you would like to remove: ");
            try {
                removeNum = Integer.parseInt(readLine());
            } catch (NumberFormatException e) {
                System.out.println(GameConstants.NOT_A_NUMBER);
                System.out.println(GameConstants.CHOSEN_ITEM_NOT_VALID);
            }

            levels = camp.getLevels();

            for (int i = 0; i < removeNum; i++) {
                // Remove the last map if there are any maps in the Campaign
                if (camp.getNumLevels() != 0) {
                    camp.removeLevel(levels);
                } else
                    System.out.println("There are no maps left in this campaign");
            }
        }

        //Save Campaign
        ObjectSaver os = new ObjectSaver();
        os.editedCampaign(camp.campaignString(), campName);

        CampaignScreen();

        this.newCamp = camp;
    }

    /**
     * Return camp
     */
    public Campaign getNewCamp() {
        return newCamp;
    }
}
