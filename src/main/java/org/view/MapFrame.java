package main.java.org.view;

import main.java.org.Service.ObjectLoader;
import main.java.org.model.Character;
import main.java.org.model.GameConstantsInterface;
import main.java.org.model.Map;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * This class provides an interactive UI for the MAP
 *
 * @author Maysam/Mehran
 * @version 2.0
 * @since 2017-02-17
 */
public class MapFrame implements ActionListener {
    public JPanel MapPanel;
    private JLabel MapLabel;
    private JTextField RowsInput;
    private JLabel RowsText;
    private JTextField ColumnsInput;
    private JLabel ColumnsText;
    private JLabel NameText;
    private JTextField NameInput;
    private JButton openMapGrid;
    private JButton saveMapButton;
    public JPanel GridPanel;
    private JCheckBox newCheckBox;
    private JTextPane useTheFollowingGuidTextPane;
    private JLabel characterLabel;
    private JLabel characterList;
    private JLabel itemLabel;
    private JLabel itemList;
    private JList characterJList;
    private JList itemJList;
    private final int SIZE = 9;
    private MapGrid grid = null;
    private boolean newMap = false;
    static int rows;
    static int cols;
    private boolean validMap;
    private boolean EntryPointExist= false;
    private boolean ExitPointExist= false;
    private boolean MonsterExist= false;
    private String errorValidMap="";
    private DefaultListModel listModel;
    /**
     * A MapFrame object
     */
    public MapFrame() {
        String MapActionInput;
        openMapGrid.addActionListener(this);
        saveMapButton.addActionListener(this);
        newCheckBox.addActionListener(this);
        loadObjectsNames();
    }
    public static void alert(String message) {
        JOptionPane.showMessageDialog(null, message);
    }


    /**
     * This class is is to load Character & Items to the Map and assign to according Labels.
     + *
     + * @author Mehran Ishanian
     + * @version 1.2
     + * @since 2017-03-16
     */
    private void loadObjectsNames(){
        ArrayList<String> charactersArrayList=new ObjectLoader().returnItemNames("src/main/java/org/resources/characters/", "C");
        ArrayList<String> itemsArrayList=new ObjectLoader().returnItemNames("src/main/java/org/resources/items/", "I");

        DefaultListModel modelC = new DefaultListModel<String>();
        for (String c: charactersArrayList) {
            modelC.addElement(c);
        }

        DefaultListModel modelI = new DefaultListModel<String>();
        for (String c: itemsArrayList) {
            modelI.addElement(c);
        }

        characterJList.setModel(modelC);
        characterJList.setDragEnabled(true);
        characterJList.setSelectedIndex(0);

        itemJList.setModel(modelI);
        itemJList.setDragEnabled(true);
        itemJList.setSelectedIndex(0);

    }


    public String input(String message) {
        return JOptionPane.showInputDialog(message);
    }

    /**
     * A mehtod to make a frame
     *
     * @param frameTitle the title of the frame
     * @return a map
     */
    public Map makeFrame(String frameTitle) {
        Map map = new Map();
        JFrame MapFrame = new JFrame(frameTitle);
        MapFrame.setContentPane(this.MapPanel);
        MapFrame.setSize(800, 500);
        MapFrame.setLocationRelativeTo(null);
        MapFrame.setVisible(true);

        return map;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        newMap = newCheckBox.isSelected();
        try {
            if (newMap) {
                rows = parseInt(RowsInput.getText());
                cols = parseInt(ColumnsInput.getText());
            }
        } catch (NumberFormatException e) {
            System.out.println(GameConstantsInterface.NOT_A_NUMBER + e.getMessage());
        }


        String name = NameInput.getText();

        if (actionEvent.getSource() == openMapGrid) {
            grid = new MapGrid(rows, cols);
            this.grid = this.grid.ShowGrid(rows, cols, this.grid, name, newMap);

        } else if (actionEvent.getSource() == saveMapButton) {
            Map map=new Map();
            if (!newMap) {
                map = grid.getExistingMap(name);
                rows=map.getRows();
                cols=map.getCols();
            }
            String[][] boardArray = grid.createBoard(MapGrid.GetTextFromGrid(this.grid), rows, cols);
            map.setScreen(boardArray);
            map.setCols(cols);
            map.setRows(rows);
            map.setName(name);
            validMap =false;

            for (int i=0;i<boardArray.length;i++) {
                for (int j = 0; j < boardArray[i].length; j++) {
                    switch (boardArray[i][j]) {
                        case "E":
                            EntryPointExist = true;
                            break;
                        case "Q":
                            ExitPointExist = true;
                            break;
                        case "M":
                            MonsterExist = true;
                            break;
                        default:
                    }
                }
            }
            if(EntryPointExist==false || ExitPointExist == false || MonsterExist == false){

                if (EntryPointExist==false){errorValidMap="Please add entry point by E";}
                if (ExitPointExist==false){errorValidMap="Please add exit point by Q";}
                if (MonsterExist==false){errorValidMap="Please add Monsters by M";}

                validMap=false;
                alert("Not Valid "+errorValidMap);
            }else{
                map.saveObject();
                alert("Your map is saved. you may close the map");
            }





        }
    }

    private void editMap(int rows, int cols, String name) {

    }


}