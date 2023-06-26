package it.polimi.ingsw.PSP45.model;


import it.polimi.ingsw.PSP45.observer.Observable;
import it.polimi.ingsw.PSP45.utils.TurnObject;
import it.polimi.ingsw.PSP45.utils.frommodeltoview;

import java.util.ArrayList;

/**
 * @author Andrea Gerosa
 * @author Lorenzo Longaretti
 * Class that represent a match
 *
 */
public class Match extends Observable<frommodeltoview> {


    private Board board;
    ArrayList<Player> turnobject = new ArrayList<>();
    Coordinate cooordinate = new Coordinate(0,0);

    /**
     * @author Andrea Gerosa
     * Class constructor
     *
     */
    public  Match(){
        this.board = null;
        this.turnobject = null;
    }

    /**
     * class constructor
     * @param board board of the game
     * @param turnobject list of players
     * @param coordinates list of starting coordinates for each worker
     */
    public Match(Board board, ArrayList<Player> turnobject, ArrayList<Coordinate> coordinates) {

        this.board = board;
        this.turnobject = turnobject;
        int y = 0;
        for (int i = 0 ; i< turnobject.size();i++){
            for (int j =0; j<turnobject.get(i).getWorkers().size() ; j++){
                turnobject.get(i).getWorkers().get(j).setCoordinate(coordinates.get(y));
                y++;
            }
        }
    }

    /**
     * @return list of players
     */
    public ArrayList<Player> getTurnObject() {
        return turnobject;
    }

    /**
     * @return the board of the game
     */
    public Board getBoard() {
        return board;
    }


}
