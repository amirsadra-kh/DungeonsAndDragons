package main.java.org.Service;

import main.java.org.model.Map;

public class DirectionValidatorService {

    static boolean isValidDirection(Map map, int i, int j) {
        try {
            String str = map.getScreen()[i][j];
        } catch (IndexOutOfBoundsException e) {
            System.out.print("the selected coordinate is out of bound , please try another coordinate");
        }
        return false;
    }

    static boolean isValidDirectionEnetered(String str){
        return ("u".equalsIgnoreCase(str)||
                "r".equalsIgnoreCase(str)||
                "l".equalsIgnoreCase(str)||
                "d".equalsIgnoreCase(str));
    }
}
