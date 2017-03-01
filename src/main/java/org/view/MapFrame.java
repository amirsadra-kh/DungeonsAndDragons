package main.java.org.view;

import main.java.org.model.GameConstants;
import main.java.org.model.Map;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextPane useTheFollowingGuidTextPane1;
    private final int SIZE = 9;
    private MapGrid grid = null;
    private boolean newMap = false;
    static int rows;
    static int cols;

    public MapFrame() {
        String MapActionInput;
        openMapGrid.addActionListener(this);
        saveMapButton.addActionListener(this);
        newCheckBox.addActionListener(this);
    }

    public static void alert(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public String input(String message) {
        return JOptionPane.showInputDialog(message);
    }

    public Map makeFrame(String frameTitle) {
        Map map = new Map();
        JFrame MapFrame = new JFrame(frameTitle);
        MapFrame.setContentPane(this.MapPanel);

        //MapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //      MapFrame.add(GridPanel);
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
            System.out.println(GameConstants.NOT_A_NUMBER + e.getMessage());
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
            map.saveObject();

        }
    }

    private void editMap(int rows, int cols, String name) {

    }


}