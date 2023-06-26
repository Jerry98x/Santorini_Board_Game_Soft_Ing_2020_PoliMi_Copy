package it.polimi.ingsw.PSP45.model;


import it.polimi.ingsw.PSP45.utils.Color;

/**
 * @author Lorenzo Longaretti
 *
 * class that represent a block of the board
 *
 */
public class Block {

    private int level;
    private Coordinate c;
    private boolean occupied;
    private boolean isDome;
    private int idWorker;
    private Color workerColor;


    /**
     * @author Lorenzo Longaretti
     * costructor of the class
     *
     *   @param c coordinate of the block
     *
     */
    public Block(Coordinate c) {
        this.c = new Coordinate(c.getX(),c.getY());
        this.isDome = false;
        this.level = 0;
        this.occupied = false;
        this.idWorker = 100;
        this.workerColor = Color.ANSI_WHITE;
    }

    /**
     * @return color of the worker occupying this block
     */
    public Color getWorkerColor() {
        return workerColor;
    }

    /**
     * @return coordinate of the block
     */
    public Coordinate getCoordinate(){ return c;}

    /**
     * @return level of the block
     */
    public int getLevel() {
        return level;
    }

    /**
     * @author Lorenzo Longaretti
     *  increment the level of the block, if the level is set at 3 or 4 set true dome
     *
     */
    public void levelUp(){
        if(level==4 || isDome == true){
        }
        else {
            if(level == 3) { level ++; setDome();}
            else{level++;}
        }
    }

    /**
     * @return the boolean value of the occupied attribute
     */
    public boolean getOccupied() {
        return occupied;
    }

    /**
     * @author Lorenzo Longaretti
     * set the block occupied
     */
    public void setOccupied() {
        if(this.occupied == false){this.occupied = true;}
        else {
        }

    }

    /**
     * @author Lorenzo Longaretti
     * set the block not occupied
     */
    public void setNotOccupied() {
        if(this.occupied == true){this.occupied = false;}
        else {
        }

    }

    /**
     * @author Lorenzo Longaretti
     * return is dome
     *
     * @return the state of isDome
     *
     */
    public boolean getIsDome() {
        return isDome;
    }

    /**
     * @author Lorenzo Longaretti
     * return the id of the worker in the block
     *
     * @return idWorker
     *
     */
    public int getWorker() {
        return idWorker;
    }

    /**
     * @author Lorenzo Longaretti
     * set idDome true
     */
    public void setDome() {
        if (this.isDome == false){
            this.isDome = true;
        }
        else{
        }

    }

    /**
     * @author Lorenzo Longaretti
     * set the worker in the block
     *
     *   @param idWorker
     *
     */
    public void addWorker(int idWorker, Color color){
        if(this.occupied==true){

        }
        else{
            setOccupied();
            this.idWorker = idWorker;
            this.workerColor = color;
        }

    }

    /**
     * @author Lorenzo Longaretti
     * remove the worker from the block
     *
     */
    public void removeWorker(){
        if(this.occupied == true){
            this.idWorker = 100;
            setNotOccupied();
            this.workerColor = Color.ANSI_WHITE;
        }
        else {

        }

    }

    /**
     * sets the level of the block to the given value
     * @param level value that will be assigned to the level of this block
     */
    public void setLevel(int level){
        this.level=level;
    }

}
