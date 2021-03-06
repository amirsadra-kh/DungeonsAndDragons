package main.java.org.Service;

import main.java.org.model.Campaign;
import main.java.org.model.GameConstantsInterface;
import main.java.org.model.ReadInput;

import java.util.List;

/**
 * A view for creating a Campaign
 *
 * @author Freyja Jokulsdottir
 * @version 2.0
 * @since 2017-02-17
 */
public class CampaignScreen {
    private Campaign newCamp;
    private ReadInput readInput = new ReadInput();

    /**
     *  A basic interaction screen after the user chooses Campaign
     */
    public void CampaignScreen() throws Exception {
        int choice = 0;

        while(choice == 0) {
            // Let user choose an action - Create or Edit a Campaign
            System.out.println("Choose one of the following by entering the number associated with the choice:");
            System.out.println("1. Create a Campaign\n2. Edit a Campaign\n3. Back to Main Menu");
            choice = readInput.readIntHandling(choice);

            // If the user enters an invalid input, they will be asked again
            while (choice < 1 || choice > 3) {
                System.out.println("Your input is invalid, please try again");
                choice = readInput.readIntHandling(choice);
            }
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
    private void createCampaignScreen() throws Exception {
        // Create a new Campaign
        Campaign camp = new Campaign();
        String name = "";
        int numLevels = 0;
        boolean campExist = true;

        while("".equalsIgnoreCase(name) || GameConstantsInterface.CHOSEN_ITEM_NOT_VALID.equalsIgnoreCase(name) || campExist) {
            System.out.println("Enter the name of the new Campaign (No spaces): ");
            name = readInput.readStringHandling(name);

            // Check if a campaign with the name chose already exists
            if(camp.getCampaign(name) == null) {
                campExist = false;
            }
            else {
                System.out.println("A Campaign with this name already exists!");
            }
        }

        camp.setName(name);

        while(numLevels == 0) {
            System.out.println("Enter the number of levels of the new Campaign: ");
            numLevels = readInput.readIntHandling(numLevels);
        }

        camp.setNumLevels(numLevels);

        // Add a Map
        addMaps(numLevels, camp);

        //Save Campaign
        camp.saveCampaign();
        //ObjectSaver os = new ObjectSaver();
        //os.saveCampaign(camp.getName(), camp);

        CampaignScreen();

        this.newCamp = camp;
    }

    /**
     * This method prompts the user for information to edit a Campaign
     *
     */
    private void editCampaignScreen() throws Exception {
        String campName = "";
        int choice = 0;
        List<String> levelNames;
        Campaign camp = new Campaign();

        while(campName.equals("")) {
            System.out.println("Enter the name of the Campaign you would like to edit:");
            campName = readInput.readStringHandling(campName);
        }

        while(camp.getCampaign(campName) == null) {
            System.out.println("No such campaign exists, try again: ");
            campName = readInput.readStringHandling(campName);
        }

        // Load the campaign for editing
        camp = camp.getCampaign(campName);

        while(choice == 0) {
            System.out.println("Choose one of the following by entering the number associated with the choice:");
            System.out.println("1. Add a Map\n2. Remove a Map\n3. Back to Main Menu");
            choice = readInput.readIntHandling(choice);

            // If the user enters an invalid input, they will be asked again
            while (choice < 1 || choice > 3) {
                System.out.println("Your input is invalid, please try again");
                choice = readInput.readIntHandling(choice);
            }

        }

        // Add a Map
        if(choice == 1) {
            //Get the number of maps the user would like to add
            int addingNum = 0;
            while(addingNum == 0) {
                System.out.println("Please enter the number of levels you would like to add: ");
                addingNum = readInput.readIntHandling(addingNum);
            }

            camp.setNumLevels(addingNum);

            addMaps(addingNum, camp);
        }

        // Remove a Map - the last one in the list
        if(choice == 2) {
            int removeNum = 0;
            while(removeNum == 0) {
                System.out.println("Please enter the number of levels you would like to remove: ");
                removeNum = readInput.readIntHandling(removeNum);
            }

            levelNames = camp.getMapNames();

            for (int i = 0; i < removeNum; i++) {
                // Remove the last map if there are any maps in the Campaign
                if (camp.getNumLevels() != 0) {
                    camp.removeLevel(levelNames);
                } else
                    System.out.println("There are no maps left in this campaign");
            }
        }

        //Save Campaign
        camp.saveCampaign();

        CampaignScreen();

        this.newCamp = camp;
    }

    /**
     * Return camp
     */
    public Campaign getNewCamp() {
        return newCamp;
    }

    /**
     * A method for getting the maps based on map names from user
     *
     * @param num number of maps to be added
     * @param camp the campaign object which the maps will be added to
     * @return campaign object with maps
     */
    private void addMaps(int num, Campaign camp){
        for(int i = 0; i < num; i++) {
            //Get Map input from user
            String mapName = "";
            while (mapName.equals("") || GameConstantsInterface.CHOSEN_ITEM_NOT_VALID.equalsIgnoreCase(mapName)) {
                System.out.println("Enter the name of the Map you would like to add:");
                mapName = readInput.readStringHandling(mapName);
                //Send Map input to CampaignModule
                if(camp.setMapNames(mapName))
                    i -= 1;
            }
        }
    }
}
