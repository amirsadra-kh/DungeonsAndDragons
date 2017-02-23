package main.java.org.model;

/**
 * This class is to calculate the modifier.
 * @author Parisa Nikzad
 * @version 1.0.0
 * @since 2017-02-18
 */
public class Modifier {

    private int modifying;

    /**
     * Constructor
     *
     * @param value This parameter take the value to calculate the modifier.
     */
    public Modifier(int value) {
        float floatValue = (float) value;
        floatValue = (floatValue - 10 )/2;
        if(floatValue < 0){
            this.modifying = 0;
        }else{
            this.modifying = (int)Math.ceil(floatValue);
        }

    }

    /**
     * This method return the modifier.
     * @return the modifier
     */
    public int get() {
        return modifying;
    }
}
