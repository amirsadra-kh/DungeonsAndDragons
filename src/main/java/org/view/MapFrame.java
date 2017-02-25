package main.java.org.view;

import main.java.org.Service.ObjectSaver;
import main.java.org.model.Map;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import static java.lang.Integer.parseInt;

/**
 * Created by misha on 2017-02-21.
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
    private final int SIZE = 9;
    private MapGrid grid=null;

    public MapFrame() {
        String MapActionInput;
        //    MapActionInput=input("Make or Edit");
        //    alert("You Select : "+MapActionInput);
        //   showGrid();
        //   final MapGrid mapGrid = new MapGrid(10, 10);
        openMapGrid.addActionListener(this);
        saveMapButton.addActionListener(this);

        //   MapGrid.ShowGrid(10,20);
    }

    public static void alert(String message) {
        JOptionPane.showMessageDialog(null,message);
    }

    public String input(String message) {
        return JOptionPane.showInputDialog(message);
    }

    public Map makeFrame (String frameTitle) {
        Map map = new Map();
        JFrame MapFrame = new JFrame(frameTitle);
        MapFrame.setContentPane(new MapFrame().MapPanel);
        MapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //      MapFrame.add(GridPanel);
        MapFrame.setSize(800,500);
        MapFrame.setLocationRelativeTo(null);
        MapFrame.setVisible(true);
        //  MapFrame.setAlwaysOnTop(true);
//        int    rows= parseInt(RowsInput.getText());
 //       int    cols=parseInt(ColumnsInput.getText());

        //   MapFrame.pack();
        return map;
    }



    public static void showGrid(){

        //   GridPanel = new JPanel(new GridLayout(4, 4, 5, 5));


    }


    @Override
    public void actionPerformed(ActionEvent actionEvent ) {
        int rows = parseInt(RowsInput.getText());
        int cols = parseInt(ColumnsInput.getText());

        if (actionEvent.getSource()==openMapGrid) {
            //   alert( "Rows:"+rows+" Cols:"+cols);
            //  MapGrid.ShowGrid(rows,cols);
           //  grid = new MapGrid(rows, cols);
            this.grid = new MapGrid(rows, cols);;
            this.grid.ShowGrid(rows, cols,this.grid);
        }
        else if(actionEvent.getSource()==saveMapButton) {

            String[][] boardArray = MapGrid.CreateBoard(MapGrid.GetTextFromGrid(this.grid), rows, cols);
            Map map = new Map(boardArray);
            map.saveObject();
                //objectSaver.saveMap("./src/main/java/org/resources/maps/"+ UUID.randomUUID().toString().substring(5),map);

        }
    }
}