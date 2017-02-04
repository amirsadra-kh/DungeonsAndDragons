package main.java.org.model;

import java.util.Random;

public class Dice6 implements Dice{
    @Override
    public int roll() {
        Random rand;

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return new Random().nextInt(6) + 1;
    }
}
