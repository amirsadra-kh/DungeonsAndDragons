package main.java.org.model;

import java.util.Random;

public class Dice6 implements Dice{
    @Override
    public int roll() {
        return new Random().nextInt(6) + 1;
    }
}
