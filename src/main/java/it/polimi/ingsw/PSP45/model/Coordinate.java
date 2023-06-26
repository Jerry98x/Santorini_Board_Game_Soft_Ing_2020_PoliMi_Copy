package it.polimi.ingsw.PSP45.model;

import java.io.Serializable;

/**
 * @author Lorenzo Longaretti
 *
 * Class that represents a coordinate
 *
 */
public class Coordinate implements Serializable {
    private int x;
    private int y;

    /**
     * @author Lorenzo Longaretti
     * Class constructor
     *
     * @param x
     * @param y
     */
    public Coordinate(int x, int y) {
        if (x <0 || x > 4 || y < 0 || y > 4 ) {
            throw new IllegalArgumentException();
        }
        this.x = x;
        this.y = y;

    }

    /**
     * @author Lorenzo Longaretti
     * Returns the x value of the coordinate.
     *
     * @return x
     *
     */
    public int getX() {
        return x;
    }

    /**
     * @author Lorenzo Longaretti
     * Sets the x value of the coordinate.
     *
     * @param x
     *
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @author Lorenzo Longaretti
     * Returns the y value of the coordinate.
     *
     * @return y
     *
     */
    public int getY() {
        return y;
    }

    /**
     * @author Lorenzo Longaretti
     * Sets the y value of the coordinate.
     *
     * @param y
     *
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return a new coordinate with the same x and y value as this one
     */
    public Coordinate clone(){
        return new Coordinate(this.getX(),this.getY());
    }
}
