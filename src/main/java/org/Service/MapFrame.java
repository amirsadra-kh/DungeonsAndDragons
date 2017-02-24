package main.java.org.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public JPanel GridPanel;
    private final int SIZE = 9;

    MapFrame() {
        String MapActionInput;
    //    MapActionInput=input("Make or Edit");
    //    alert("You Select : "+MapActionInput);
     //   showGrid();
     //   final MapGrid mapGrid = new MapGrid(10, 10);
        openMapGrid.addActionListener(this);
     //   MapGrid.ShowGrid(10,20);
    }

    public static void alert(String message) {
        JOptionPane.showMessageDialog(null,message);
    }

    public String input(String message) {
        return JOptionPane.showInputDialog(message);
    }

    public static void makeFrame (String frameTitle) {

        JFrame MapFrame = new JFrame(frameTitle);
        MapFrame.setContentPane(new MapFrame().MapPanel);
        MapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //      MapFrame.add(GridPanel);
        MapFrame.setSize(800,500);
        MapFrame.setLocationRelativeTo(null);
        MapFrame.setVisible(true);
      //  MapFrame.setAlwaysOnTop(true);


        //   MapFrame.pack();
    }



    public static void showGrid(){

        //   GridPanel = new JPanel(new GridLayout(4, 4, 5, 5));


    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    int    rows= parseInt(RowsInput.getText());
    int    cols=parseInt(ColumnsInput.getText());
    alert( "Rows:"+rows+" Cols:"+cols);
        MapGrid.ShowGrid(rows,cols);
    }
}
