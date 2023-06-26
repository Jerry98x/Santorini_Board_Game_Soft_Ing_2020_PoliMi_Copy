package it.polimi.ingsw.PSP45.controller;
import it.polimi.ingsw.PSP45.model.*;
import it.polimi.ingsw.PSP45.utils.*;
import java.util.ArrayList;

/**
 * @author Filippo Locatelli
 *
 * class that represent the rules of a player's turn
 */
public class Rules extends AbstractRule {

    /**
     * Class constructor
     *
     * @param turn  Integer that identifies who is the player in control during this turn
     * @param board The board in which the game is being played
     */
    public Rules(int turn, Board board, Player currentPlayer, Player nextPlayer, Player thirdPlayer) {
        super(turn, board, currentPlayer, nextPlayer, thirdPlayer);
    }

    /**
     * other class constructor without input parameters
     */
    public Rules() {
        super();
    }

    /**
     * @return rule's Turn
     */
    @Override
    public int getTurn() {
        return turn;
    }

    /**
     * @return rule's current player
     */
    @Override
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * @return rule's next player
     */
    @Override
    public Player getNextPlayer() {
        return this.nextPlayer;
    }

    /**
     * @return rule's third player
     */
    @Override
    public Player getThirdPlayer() {
        return this.thirdPlayer;
    }

    /**
     * @return rule's state
     */
    @Override
    public StateNumber getState() {
        return state;
    }

    @Override
    public ArrayList<LightBlock> getWorkerToSend() {
        return workerToSend;
    }

    /**
     * @return rule's list of possible moves
     */
    @Override
    public ArrayList<LightBlock> getMoveList() {
        return moveList;
    }

    /**
     * @return rule's list of possible builds
     */
    @Override
    public ArrayList<LightBlock> getBuildList() {
        return buildList;
    }

    /**
     * @return rule's hasWon attribute
     */
    @Override
    public Boolean getHasWon() {
        return this.HasWon;
    }

    /**
     * @return rule's worker that moved in this turn
     */
    @Override
    public Worker getMovedWorker() {
        return this.movedWorker;
    }

    /**
     * @return rule's stringParticular attribute
     */
    @Override
    public String getStringParticular() {
        return stringParticular;
    }

    @Override
    public Boolean getIsalive() {
        return this.isalive;
    }

    /**
     * @return rule's level of the block occupied by the worker before moving
     */
    @Override
    public int getLevelPrecPosition() {
        return this.levelPrecPosition;
    }

    /**
     * set rule's state to the given one
     * @param state
     */
    @Override
    public void setState(StateNumber state) {
        this.state = state;
    }

    /**
     * set rule's moved Worker to the given one
     * @param movedWorker
     */
    @Override
    public void setMovedWorker(Worker movedWorker) {
        workerToSend.clear();
        this.movedWorker=movedWorker;
    }

    /**
     * sets rule's stringParticular to the given one
     * @param stringParticular
     */
    @Override
    public void setStringParticular(String stringParticular) {
        this.stringParticular = stringParticular;
    }

    @Override
    public void setWorkerToSend(ArrayList<LightBlock> workerToSend) {
        this.workerToSend = workerToSend;
    }

    /**
     * set rule's isAlive attribute to the given one
     * @param isalive
     */
    @Override
    public void setIsalive(Boolean isalive) {
        this.isalive = isalive;
    }

    /**
     * set rule's hasWon attribute to the given one
     * @param hasWon
     */
    @Override
    public void setHasWon(Boolean hasWon) {
        this.HasWon = hasWon;
    }

    /**
     * set rule's level of the block occupied by the worker before moving to the given one
     * @param i
     */
    @Override
    public void setLevelPrecPosition(int i) {
        this.levelPrecPosition = i;
    }

    /**
     * Method that manages the sequences of moves in a turn based on a player's divinity
     *
     * @param s service
     */
    @Override
    public void turnHandler(Service s) {
        super.turnHandler(s);
    }

    /**
     * Method that manages the sequences of moves in a turn based on a player's divinity
     *
     * @param s service
     */
    @Override
    public void turnHandler_real(Service s) {
        AbstractRule abstractRule1 = currentPlayer.getAbstractRule();
        Coordinate coordinate = new Coordinate(0, 0);
        if (state == StateNumber.settingMovement) {

            abstractRule1.setMoves();
            setState(abstractRule1.getState());

        }
        if (state == StateNumber.moving && s.getType() == StateNumber.readyToMove) {
            Worker worker = currentPlayer.getSpecificWorker(s.getIntservizio());
            coordinate = s.getAvailableCells().get(0).getC();
            abstractRule1.performMove(worker, coordinate, s.getIntservizio());
            abstractRule1.CheckIfWin();
            setHasWon(abstractRule1.getHasWon());
            if (!abstractRule1.getHasWon()) {
                abstractRule1.setBuild();
            }
        }
        if (state == StateNumber.settingBuilding) {
            abstractRule1.setBuild();
        }
        if (state == StateNumber.building && s.getType() == StateNumber.readyToBuild) {
            coordinate = s.getAvailableCells2().get(0).getC();
            abstractRule1.performBuild(coordinate);
            abstractRule1.nextTurn();
        }

        if (state== StateNumber.onlymoving  && s.getType() == StateNumber.readyToMove) {
            Worker worker = currentPlayer.getSpecificWorker(s.getIntservizio());
            coordinate = s.getAvailableCells().get(0).getC();
            currentPlayer.getAbstractRule().performMove(worker, coordinate, s.getIntservizio());
            setState(currentPlayer.getAbstractRule().getState());
        }

        if (state== StateNumber.onlybuilding && s.getType() == StateNumber.readyToBuild){
            coordinate = s.getAvailableCells2().get(0).getC();
            abstractRule1.performBuild(coordinate);
        }

        if (state== StateNumber.onlynextturn ){
            currentPlayer.getAbstractRule().nextTurn();
            setState(currentPlayer.getAbstractRule().getState());
        }
    }


    /**
     * Method that sets the movement in this turn
     */
    @Override
    public void setMoves() {
        workerToSend.clear();
        AbstractRule abstractRule1 = currentPlayer.getAbstractRule();
        ArrayList<LightBlock> worker = new ArrayList<>();
        for (int i = 0; i < currentPlayer.getWorkers().size(); i++) {
            abstractRule1.showMoves(currentPlayer.getWorkers().get(i));

        }
        abstractRule1.CheckList(moveList);
        setIsalive(abstractRule1.getIsalive());
        if(isalive){
            LightBoard lightBoard = new LightBoard();
            lightBoard.update(board);
            service.sendMoveMessage(moveList, lightBoard, workerToSend, stringParticular);
            abstractRule1.setState(StateNumber.moving);
            setState(abstractRule1.getState());
            frommodeltoview help = new frommodeltoview(currentPlayer, service);
            notify(help);
        }

    }

    /**
     * Method that shows the player in which blocks his/hers workers can be moved
     *
     * @param worker worker of which we want to kw where it can be moved
     */
    @Override
    public void showMoves(Worker worker) {

        Coordinate coordinate = worker.getCoordinate();

        try {
            ArrayList<LightBlock> help1 = new ArrayList<>();
            ArrayList<LightBlock> help = new ArrayList<>();
            help1 = board.freeSpacesBuild(coordinate);
            help = board.whereIsRightLevel(coordinate,help1);

           if(help.size() == 7){
           }
            for (int i = 0; i < help.size(); i++) {
                if(help.get(i)== null){
                }
                help.get(i).setIdWorker(worker.getIdWorker());
                moveList.add(help.get(i));
            }
            if(help.size()!=0){
                LightBlock lb = new LightBlock(worker.getCoordinate());
                lb.setIdWorker(worker.getIdWorker());
                workerToSend.add(lb);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Method that moves a worker
     *
     * @param worker worker that is being moved
     * @param coordinate final coordinates of the movement
     * @param idWorker id number of the worker that is being moved
     */
    @Override
    public void performMove(Worker worker, Coordinate coordinate, int idWorker) {
        setLevelPrecPosition(board.getBlock(worker.getCoordinate()).getLevel());
        Coordinate startingBlock = worker.getCoordinate();
        board.removeWorker(startingBlock);
        board.addWorker(coordinate, idWorker, currentPlayer.getColor()); //con apollo cambierà   //passerò player in handle player
        if (currentPlayer.getWorkers().get(0).getIdWorker() == idWorker) {
            currentPlayer.getWorkers().get(0).setCoordinate(coordinate);
        } else {
            currentPlayer.getWorkers().get(1).setCoordinate(coordinate);

        }
        movedWorker = worker;
    }

    /**
     * Method that sets the construction of a block in this turn
     */
    @Override
    public void setBuild() {
        AbstractRule abstractRule1 = currentPlayer.getAbstractRule();

        abstractRule1.showBuild(movedWorker);
        abstractRule1.CheckList(buildList);
        setIsalive(abstractRule1.getIsalive());
        if(isalive){
            LightBoard lightBoard = new LightBoard();
            lightBoard.update(board);
            service.sendBuildMessage(buildList, lightBoard, movedWorker.getIdWorker(), stringParticular);
            abstractRule1.setState(StateNumber.building);
            setState(abstractRule1.getState());
            frommodeltoview help = new frommodeltoview(currentPlayer, service);
            notify(help);
        }

    }

    /**
     * Method that shows the player where it's possible to build
     *
     * @param worker worker that got moved in this turn
     */
    @Override
    public void showBuild(Worker worker) {
        Coordinate workersCoordinate = worker.getCoordinate();
        ArrayList<LightBlock> help2 = new ArrayList<>();
        help2 = board.freeSpacesBuild(workersCoordinate);
        for (int i = 0; i < help2.size(); i++) {
            buildList.add(help2.get(i));
        }

    }

    /**
     * Method used to build a block
     *
     * @param coordinate coordinates where the block will be added
     */
    @Override
    public void performBuild(Coordinate coordinate) {
        board.getBlock(coordinate).levelUp();
    }

    /**
     * Method that sets the state for the player to 0, meaning his/her turn is over
     */
    @Override
    public void nextTurn() {
        setState(StateNumber.settingMovement);
        service.setNexTurn();
        frommodeltoview help = new frommodeltoview(nextPlayer, service);
        if(thirdPlayer != null ){
            if(!nextPlayer.getAbstractRule().getIsalive()){
                help = new frommodeltoview(nextPlayer.getAbstractRule().nextPlayer, service);
            }
        }
        service.getAvailableCells().clear();
        service.getAvailableCells2().clear();
        notify(help);
    }

    /**
     * method that checks if the player has won in this turn
     */
    @Override
    public void CheckIfWin() {
        if (levelPrecPosition == 2 && board.getBlock(movedWorker.getCoordinate()).getLevel() == 3) {
            setState(StateNumber.haswon);
            setHasWon(true);
            service.setHasWon();
            frommodeltoview help = new frommodeltoview(currentPlayer, service);
            service.getAvailableCells().clear();
            service.getAvailableCells2().clear();
            notify(help);
        }
    }

    /**
     * method that manages what happens when a player loses the game
     */
    @Override
    public void lose() {
        setState(StateNumber.sendhaslose);
        service.sendHasLose();
        frommodeltoview help = new frommodeltoview(nextPlayer, service);
        if(thirdPlayer != null ){
            if(!nextPlayer.getAbstractRule().getIsalive()){
                help = new frommodeltoview(nextPlayer.getAbstractRule().nextPlayer, service);
            }
        }
        service.getAvailableCells().clear();
        service.getAvailableCells2().clear();
        notify(help);
    }

    /**
     * method that reorganize the order of players
     */
    @Override
    public void invertPlayer() {
        Player help = new Player("def", 1, GodName.hephaestus, Color.ANSI_WHITE);
        help = nextPlayer;
        nextPlayer = thirdPlayer;
        thirdPlayer = help;
    }

    /**
     * method that checks if a player can move or build in his turn
     * @param list list of blocks in which the player can move
     */
    @Override
    public void CheckList(ArrayList<LightBlock> list) {
        if(list.size()==0){
            setIsalive(false);
            if(thirdPlayer != null){
                if(nextPlayer.getAbstractRule().getIsalive() && thirdPlayer.getAbstractRule().getIsalive()){
                    nextTurn();
                }
                else{
                    lose();
                }
            }
            else{
                lose();
            }
        }
    }

    /**
     * method that manages what happens when a player wins the game
     */
    @Override
    public void Win() {
        setState(StateNumber.haswon);
        setHasWon(true);
        service.setHasWon();
        frommodeltoview help = new frommodeltoview(currentPlayer, service);
        service.getAvailableCells().clear();
        service.getAvailableCells2().clear();
        notify(help);
    }

}