package it.polimi.ingsw.PSP45.model;

import it.polimi.ingsw.PSP45.controller.AbstractRule;
import it.polimi.ingsw.PSP45.controller.Rules;
import it.polimi.ingsw.PSP45.utils.Color;
import it.polimi.ingsw.PSP45.utils.GodName;

import java.util.ArrayList;

/**
 * @author Filippo Locatelli
 *
 * Class that represents a player
 *
 */
public class Player {

    private String name;
    private int eta;
    private GodName god;
    private Color color;
    private boolean isAlive;
    private AbstractRule abstractRule = new Rules() ;

    private ArrayList<Worker> workers = new ArrayList<>(2);

    /**
     * Class constructor
     *
     * @param name name of the player
     * @param eta age of the player
     * @param god deity chosen by the player
     * @param color color given to the player
     */
    public Player (String name, int eta, GodName god, Color color){
        this.name = name;
        this.eta = eta;
        this.god = god;
        this.color = color;
        this.isAlive = true;


        workers.add(new Worker(0,this.color));
        workers.add(new Worker(1,this.color));
        if (workers.size()>2)
            throw new IllegalArgumentException();
    }

    /**
     * sets the player's abstractRule to the given one
     * @param abstractRule
     */
    public void setAbstractRule(AbstractRule abstractRule) {
        this.abstractRule = abstractRule;
    }

    /**
     * @return player's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return player's age
     */
    public int getEta() {
        return eta;
    }

    /**
     * @return player's chosen divinity
     */
    public GodName getGod() {
        return god;
    }

    /**
     * @return player's color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the list of workers controlled by the player
     */
    public  ArrayList<Worker> getWorkers() {
        return workers;
    }

    /**
     * @return the player's attribute "isAlive"
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * sets the attribute isAlive to the one given in input
     * @param alive boolean parameter
     */
    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    /**
     * remove a worker from the list
     *
     * @param index worker's Id
     */
    public void removeWorker(int index){
        workers.remove(index);

    }

    /**
     * remove all of the player's workers from the list
     *
     */
    public void removeAllWorkers() {
        for(int i = 0; i<=workers.size(); i++) {
            removeWorker(0);
        }
    }

    /**
     * @param i id of a specific worker
     * @return the worker with the id given in input
     */
    public Worker getSpecificWorker(int i){
        return workers.get(i);
    }

    /**
     * @return player's abstractRule
     */
    public AbstractRule getAbstractRule() {
        return abstractRule;
    }

    /**
     * @return a list of LightBlock with every element representing the block occupied by a worker
     */
    public ArrayList<LightBlock> getCoordinateWorkers(){
        ArrayList<LightBlock> workers = new ArrayList<>();
        ArrayList<Worker> workerhelper = getWorkers();
        for (Worker w: workerhelper) {
            LightBlock l = new LightBlock(w.getCoordinate());
            l.setIdWorker(w.getIdWorker());
            workers.add(l);
        }
        return workers;
    }
}


