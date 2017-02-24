package main.java.org.Service;

import javax.swing.*;
import java.awt.*;

/**
 * Created by misha on 2017-02-23.
 */
public class MapGrid extends JFrame {

    private JPanel gt;

    public static void ShowGrid(int rows, int cols) {
    //    int rows = 2;
    //    int cols = 3;
        MapGrid gt = new MapGrid(rows, cols);
        gt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gt.setLocationRelativeTo(null);
        gt.pack();
        gt.setVisible(true);
    }

    public MapGrid(int rows, int cols) {
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(rows, cols));
        for (int j=1 ; j<= cols; j++) {
            for (int i = 1; i <= rows; i++) {
                JButton button = new JButton(i+" "+j); //Integer.toString(i + 1)
                pane.add(button);
            }
        }
    }

}





