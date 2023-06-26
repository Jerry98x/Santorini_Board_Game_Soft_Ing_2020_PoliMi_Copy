package it.polimi.ingsw.PSP45.model;

import it.polimi.ingsw.PSP45.utils.Color;

/**
 * @author Filippo Locatelli
 *
 * Class that represent a worker
 */
public class Worker {

    private int idWorker;
    private Color color;
    private Coordinate coordinate;

    /**
     * Class constructor
     * @param idWorker number that represent a specific worker
     * @param color color chosen by the player controlling this worker
     */
    public Worker (int idWorker, Color color){
        if (idWorker<0 || idWorker>1) {
            throw new IllegalArgumentException();
        }
        this.idWorker = idWorker;
        this.color = color;
        this.coordinate = new Coordinate(0,0);
    }

    /**
     * @return worker's id
     */
    public int getIdWorker() {
        return idWorker;
    }

    /**
     * @return worker's color
     */
    public Color getColor() {
        return color;

    }

    /**
     * @return worker's coordinate
     */
    public Coordinate getCoordinate(){
        return coordinate;
    }

    /**
     * sets the coordinates of this worker
     *
     * @param c coordinates of the block occupied by the worker
     */
    public void setCoordinate (Coordinate c){

        int cX=c.getX();
        int cY=c.getY();
        this.coordinate.setX(cX);
        this.coordinate.setY(cY);
    }

}
