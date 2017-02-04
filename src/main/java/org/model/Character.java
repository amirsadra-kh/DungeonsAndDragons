package main.java.org.model;

import java.awt.*;

public class Character {

    private Point currentPosition = new Point(0,0);
    private Ability ability;

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Ability getAbvility() {
        return ability;
    }

    public void setAbvility(Ability abvility) {
        this.ability = abvility;
    }
}
