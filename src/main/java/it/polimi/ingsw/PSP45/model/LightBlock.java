package it.polimi.ingsw.PSP45.model;


import it.polimi.ingsw.PSP45.utils.Color;

import java.io.Serializable;

/**
 * @author  Andrea Gerosa
 *
 * Class which represent a reduced version of a board's block, with only its essential attributes and methods
 *
 */
public class LightBlock implements Serializable {
    private int level;
    private Coordinate c;
    private boolean occupied;
    private boolean isDome;
    private Color color;
    private int idworker;


    /**
     * @author Andrea Gerosa
     *
     * Class constructor
     *
     * @param c coordinate of the block
     */
    public LightBlock(Coordinate c) {

        this.c = new Coordinate(c.getX(), c.getY());
        this.level = 0;
        this.occupied = false;
        this.isDome = false;
    }


    /**
     * @author Andrea Gerosa
     *
     * Sets the LightBlock with the informations of the original Block.
     *
     * @param block
     */
    public void setLightBlock(Block block) {
        this.level = block.getLevel();
        this.c = block.getCoordinate();
        this.occupied = block.getOccupied();
        this.isDome = block.getIsDome();
        this.color = block.getWorkerColor();
        this.idworker = block.getWorker();
    }

    /**
     * @return the Id of the worker occupying this block
     */
    public int getIdWorker() {
        return idworker;
    }

    /**
     * @return color of the worker occupying this block
     */
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return level of the block
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return coordinates of the block
     */
    public Coordinate getC() {
        return c;
    }

    /**
     * @return boolean value of the occupied attribute
     */
    public boolean getOccupied() {
        return occupied;
    }

    /**
     * @return boolean value of the isDome attribute
     */
    public boolean getIsDome() {
        return isDome;
    }

    /**
     * method that returns a new lightBlock with the same attributes as the block given in input
     * @param b block of which we need a lightBloc version
     * @return lightBlock with the same attributes as b
     */
    public LightBlock getLightBlock(Block b){
         LightBlock light = new LightBlock(b.getCoordinate());
         light.setLightBlock(b);
         return light;
    }

    /**
     * sets the Id of the worker occupying this block to the given one
     * @param idworker value that will be assigned to the Id of the worker occupying this block
     */
    public void setIdWorker(int idworker) {
        this.idworker = idworker;
    }

    /**
     * sets the level of the block to the given value
     * @param level value that will be assigned to the level of this block
     */
    public void setLevel(int level){this.level=level;}

}
