package it.polimi.ingsw.PSP45.controller;

import it.polimi.ingsw.PSP45.model.*;
import it.polimi.ingsw.PSP45.utils.Service;
import it.polimi.ingsw.PSP45.utils.StateNumber;

import java.util.ArrayList;


/**
 * @author Lorenzo Longaretti
 *
 * class that rappresent the abstract rule
 *
 */
public abstract class AbstractRule extends Match {
    int turn;
    StateNumber state = StateNumber.settingMovement;
    Board board = new Board();
    Service service ;
    Player currentPlayer;
    Player nextPlayer;
    Player thirdPlayer;
    ArrayList<LightBlock> moveList = new ArrayList<>();
    ArrayList<LightBlock> buildList = new ArrayList<>();
    Worker movedWorker=null;
    int levelPrecPosition = 0;
    Boolean HasWon = false;
    Boolean isalive = true;
    String stringParticular = "1";
    ArrayList<LightBlock> workerToSend = new ArrayList<>();

    /**
     * @author Lorenzo Longaretti
     *
     * class contructor
     * @param turn turn of the game
     * @param board board of the game
     * @param currentPlayer playing in this turn
     * @param nextPlayer playing in the next turn
     * @param thirdPlayer if the game is for 3 player, this represent the third one
     */
    public AbstractRule(int turn, Board board, Player currentPlayer, Player nextPlayer, Player thirdPlayer){
        this.turn=turn;
        this.state= StateNumber.settingMovement;
        this.board=board;
        this.service = new Service(StateNumber.active,"Default", "Default");
        this.currentPlayer = currentPlayer;
        this.nextPlayer=nextPlayer;
        this.thirdPlayer = thirdPlayer;
        this.levelPrecPosition = 0;
        this.HasWon = false;
        this.isalive= true;
        this.movedWorker = null;
    }

    /**
     * @author Lorenzo Longaretti
     *
     * other class constructor
     */
    public AbstractRule(){
        turn = 0;
        state = StateNumber.settingMovement;
        board = new Board();
        service = null ;
        currentPlayer = null;
        nextPlayer = null;
        thirdPlayer= null;
        moveList = new ArrayList<>();
        buildList = new ArrayList<>();
        movedWorker=null;
        levelPrecPosition = 0;
        HasWon= false;
        isalive = true;
        stringParticular = "1";
        workerToSend = new ArrayList<>();
    }

    /**
     * @author Lorenzo Longaretti
     *
     * other class constructor
     * @param abstractRule other abstractRule
     */
    public AbstractRule(AbstractRule abstractRule){
        this.turn=abstractRule.getTurn();
        this.state= abstractRule.getState();
        this.board=abstractRule.getBoard();
        this.service = new Service(StateNumber.active,"Default", "Default");
        this.currentPlayer = abstractRule.getCurrentPlayer();
        this.nextPlayer= abstractRule.getNextPlayer();
        this.thirdPlayer = abstractRule.getThirdPlayer();
        this.moveList = abstractRule.getMoveList();
        this.buildList = abstractRule.getBuildList();
        this.levelPrecPosition = abstractRule.getLevelPrecPosition();
        this.HasWon = abstractRule.getHasWon();
        this.movedWorker= abstractRule.getMovedWorker();
        this.isalive = abstractRule.getIsalive();
        this.stringParticular = abstractRule.getStringParticular();
        this.workerToSend = abstractRule.getWorkerToSend();
    }

    public abstract ArrayList<LightBlock> getMoveList();

    public abstract ArrayList<LightBlock> getBuildList();

    public abstract StateNumber getState();

    public abstract int getTurn();

    public abstract Player getCurrentPlayer();

    public abstract Player getThirdPlayer();

    public abstract int getLevelPrecPosition();

    public abstract Boolean getHasWon();

    public abstract Worker getMovedWorker() ;

    public abstract Boolean getIsalive();

    public abstract String getStringParticular();

    public abstract ArrayList<LightBlock> getWorkerToSend();

    public abstract Player getNextPlayer();

    @Override
    public Board getBoard() {
        return board;
    }


    public abstract void setMoves();

    public abstract void setState(StateNumber state);

    public abstract void setHasWon(Boolean hasWon);

    public abstract void setLevelPrecPosition(int i);

    public abstract void setIsalive(Boolean isalive);

    public abstract void setMovedWorker(Worker movedWorker);

    public abstract void setStringParticular(String stringParticular);

    public abstract void setWorkerToSend(ArrayList<LightBlock> workerToSend);

    public abstract void setBuild();


    /**
     * @author Lorenzo Longaretti
     * abstract method that shows the player in which blocks his workers can be moved
     *
     * @param worker worker of which we want to kw where it can be moved
     */
    public abstract void showMoves(Worker worker);


    public abstract void lose();

    public abstract void Win();

    /**
     * @author Lorenzo Longaretti
     * abstract method that moves a worker
     *
     * @param worker worker that is being moved
     * @param coordinate final coordinates of the movement
     * @param idWorker id number of the worker that is being moved
     */
    public abstract void performMove(Worker worker,Coordinate coordinate, int idWorker);



    /**
     * @author Lorenzo Longaretti
     * abstract method that shows the player where it's possible to build
     *
     * @param worker worker that got moved in this turn
     */
    public abstract void showBuild(Worker worker);


    /**
     * @author Lorenzo Longaretti
     * abstract method used to build a block
     *
     * @param coordinate coordinates where the block will be added
     */
    public abstract void performBuild(Coordinate coordinate);

    public abstract void nextTurn();

    public abstract void CheckIfWin();

    public abstract void invertPlayer();

    /**
     * @author Lorenzo Longaretti
     * method that check if buildlist or movelist is empty
     * @param list
     */

    public abstract void CheckList(ArrayList<LightBlock> list);

    /**
     * @author Lorenzo Longaretti
     * abstract method that manages the sequences of moves in a turn
     *
     * @param s service
     */
    public void turnHandler(Service s){
        currentPlayer.getAbstractRule().turnHandler_real(s);
    }

    /**
     * abstract method that manages the sequences of moves in a turn
     *
     * @param s service
     */
    public abstract void turnHandler_real(Service s);

}