package main.java.org.view;

import main.java.org.Service.ObjectLoader;
import main.java.org.model.Map;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * A this class provides an interactive UI for the MAP (Grids)
 *
 * @author Maysam/Mehran
 * @version 2.0
 * @since 2017-02-17
 */
public class MapGrid extends JFrame {

    private JPanel gt;

    /**
     * A method to show the map grid
     *
     * @param rows number of rows for the map
     * @param cols number of columns for the map
     * @param grid the MapGrid object
     * @param name the name of the map
     * @param newMap a boolean to indicate if it is a new map or not
     * @return grid
     */
    public MapGrid ShowGrid(int rows, int cols, MapGrid grid, String name, boolean newMap) {
        // MapGrid gt = new MapGrid(rows, cols);

        if (newMap ) {
            grid.getContentPane();
            createBoard(GetTextFromGrid(grid), rows, cols);
        } else {
            editBoard(name);

        }
       // grid.setLocationRelativeTo(null);
        grid.setLocationByPlatform(true);
        grid.setSize( 500, 500);
        grid.setLocation(600,0);
        grid.setVisible(true);
        grid.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       return  grid;

    }

    /**
     * A method to get text input from user from the grid
     *
     * @param gt a MapGrid object
     * @return a list of Strings inputted to the grid
     */
    public static java.util.List<String> GetTextFromGrid(MapGrid gt) {
        java.util.List<String> list = new ArrayList<>();
        Container components = gt.getContentPane();
        for (Component c : components.getComponents()) {
            //   c.ge
            if (c instanceof JTextField) {
                String s = ((JTextField) c).getText();
                list.add(s);
            }

        }
        return list;
    }

    /**
     * A method to edit the board
     *
     * @param name of the map
     */
    public void editBoard( String name) {

        String[][] exitingBoard = getExistingBoard(name);

        Container pane = getContentPane();
        if(exitingBoard.length!=0) {
            pane.setLayout(new GridLayout(exitingBoard.length, exitingBoard[0].length));
        }
        for (int i = 0; i < exitingBoard.length; i++) {
            for (int j = 0; j < exitingBoard[i].length; j++) {
                JTextField NewTextField = new JTextField(exitingBoard[i][j]);
                pane.add(NewTextField);
            }
        }
    }

    /**
     * A method to get an existing board to edit it or for any other purposes
     *
     * @param name the path of the board along with the name of the map
     * @return a 2D String Array - the board
     */
    private String[][] getExistingBoard(String name) {

        Map map = null;
        try {
            map = ObjectLoader.loadMapFromXML(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map.getScreen();
    }

    /**
     * A method to get an existing map
     *
     * @param name of the map
     * @return a map or nothing if the map does not exist
     */
    public Map getExistingMap(String name) {
        try {
            return  ObjectLoader.loadMapFromXML(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * A method to create the board
     *
     * @param list of Strings
     * @param rows number of rows
     * @param cols number of columns
     * @return a 2D array of strings - a new board
     */
    public String[][] createBoard(java.util.List<String> list, int rows, int cols) {
        String[][] board = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (list!=null && list.size()!=0) {
                    board[i][j] = list.get(0);
                    list.remove(0);
                }
            }
        }
        return board;

    }

    /**
     * A MapGrid object
     *
     * @param rows number of rows
     * @param cols number of columns
     */
    public MapGrid(int rows, int cols) {
        if(rows!=0 && cols!=0) {
        Container pane = getContentPane();

            pane.setLayout(new GridLayout(rows, cols));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JTextField NewTextField = new JTextField(); //i + "" + j
                pane.add(NewTextField);
            }
        }
        }
        //alert(pane.NewTextField.getText());
    }

}






