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
     final static String MAPS_PATH="./src/main/java/org/resources/maps/";

    public MapGrid ShowGrid(int rows, int cols, MapGrid grid, String name, boolean newMap) {
        // MapGrid gt = new MapGrid(rows, cols);

        if (newMap ) {
            grid.getContentPane();
            createBoard(GetTextFromGrid(grid), rows, cols);
        } else {
            editBoard(name);

        }
        grid.setDefaultCloseOperation(grid.EXIT_ON_CLOSE);
        grid.setLocationRelativeTo(null);
        grid.setSize( 500, 500);
        grid.setVisible(true);
       return  grid;

    }

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

    public void editBoard( String name) {

        String[][] exitingBoard = getExistingBoard(MAPS_PATH + name);

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

    private String[][] getExistingBoard(String name) {

        Map map = null;
        try {
            map = ObjectLoader.loadMap(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map.getScreen();
    }

    public Map getExistingMap(String name) {
        try {
            return  ObjectLoader.loadMap(MAPS_PATH+name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[][] createBoard(java.util.List<String> list, int rows, int cols) {
        String[][] board = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (CollectionUtils.isNotEmpty(list)) {
                    board[i][j] = list.get(0);
                    list.remove(0);
                }
            }
        }
        return board;

    }


    public MapGrid(int rows, int cols) {
        if(rows!=0 && cols!=0) {
        Container pane = getContentPane();

            pane.setLayout(new GridLayout(rows, cols));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JTextField NewTextField = new JTextField(i + "" + j); //Integer.toString(i + 1)
                pane.add(NewTextField);
            }
        }
        }
        //alert(pane.NewTextField.getText());
    }

}






