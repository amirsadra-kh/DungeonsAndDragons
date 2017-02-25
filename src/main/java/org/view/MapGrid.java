package main.java.org.view;

import org.apache.commons.collections.CollectionUtils;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by misha on 2017-02-23.
 */
public class MapGrid extends JFrame {

    private JPanel gt;

    public static void ShowGrid(int rows, int cols, MapGrid gt) {
       // MapGrid gt = new MapGrid(rows, cols);
        Container pane = gt.getContentPane();
        String[][] boardArray=CreateBoard(GetTextFromGrid(gt),rows,cols);
        gt.setDefaultCloseOperation(gt.EXIT_ON_CLOSE);
        gt.setLocationRelativeTo(null);
        gt.setSize(rows*100,cols*100);
        gt.setVisible(true);
    }

    public static java.util.List<String> GetTextFromGrid(MapGrid gt){
        java.util.List<String> list = new ArrayList<>();
        Container components=gt.getContentPane();
        for(Component c:components.getComponents()){
         //   c.ge
            if(c instanceof  JTextField){
               String s =((JTextField) c).getText();
                list.add(s);
            }

        }
        return list;
    }

    public static String[][] CreateBoard(java.util.List<String> list,int rows, int cols )
    {
     String[][] board = new String[rows][cols];
     for (int i=0; i<rows; i++) {
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
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(rows, cols));
        for (int j=1 ; j<= cols; j++) {
            for (int i = 1; i <= rows; i++) {
                JTextField NewTextField = new JTextField(i+""+j); //Integer.toString(i + 1)
                pane.add(NewTextField);
            }
        }
        //alert(pane.NewTextField.getText());
    }

}






