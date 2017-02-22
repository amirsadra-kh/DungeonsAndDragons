package main.java.org.Service;

import javax.swing.*;
import java.awt.*;

/**
 * Created by misha on 2017-02-21.
 */
public class MapFrame {
    public JPanel MapPanel;
    private JLabel MapLabel;
    private JTextField RowsInput;
    private JLabel RowsText;
    private JTextField ColumnsInput;
    private JLabel ColumnsText;
    private JLabel NameText;
    private JTextField NameInput;
    private JButton saveMapButton;
    private JPanel GridPanel;

    MapFrame() {
        //String MapActionInput;
        //MapActionInput=input("Make or Edit");
        //alert("You Select : "+MapActionInput);
        showGrid();
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
        MapFrame.setSize(800,500);
        MapFrame.setLocationRelativeTo(null);
        MapFrame.setVisible(true);
        MapFrame.setAlwaysOnTop(true);
     //   MapFrame.pack();
    }
public static void showGrid(){

  //  GridPanel = new JPanel(new GridLayout(4, 4, 5, 5));

}


}
