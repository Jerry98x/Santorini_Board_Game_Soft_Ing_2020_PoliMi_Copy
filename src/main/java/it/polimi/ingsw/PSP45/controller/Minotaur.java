package it.polimi.ingsw.PSP45.controller;

import it.polimi.ingsw.PSP45.model.*;
import it.polimi.ingsw.PSP45.utils.Color;
import it.polimi.ingsw.PSP45.utils.Service;
import it.polimi.ingsw.PSP45.utils.StateNumber;

//import javax.sound.midi.spi.SoundbankReader;
import java.util.ArrayList;

/**
 * @author Filippo Locatelli
 *
 * class that represent the rules of a turn if the chosen god is Minotaur
 */
public class Minotaur extends God {

	/**
	 * class constructor
	 *
	 * @param abstractRule abstractRule that will be decorated whit the divinity
	 */
	public Minotaur(AbstractRule abstractRule){
		super(abstractRule);
	}

	@Override
	public ArrayList<LightBlock> getMoveList() {
		return abstractRuleGod.getMoveList();
	}

	@Override
	public ArrayList<LightBlock> getBuildList() {
		return abstractRuleGod.getBuildList();
	}

	@Override
	public StateNumber getState() {
		return abstractRuleGod.getState();
	}

	@Override
	public int getTurn() {
		return abstractRuleGod.getTurn();
	}

	@Override
	public Player getCurrentPlayer() {
		return abstractRuleGod.getCurrentPlayer();
	}

	@Override
	public Player getNextPlayer() {
		return abstractRuleGod.getNextPlayer();
	}

	@Override
	public Player getThirdPlayer() {
		return abstractRuleGod.getThirdPlayer();
	}

	@Override
	public int getLevelPrecPosition() {
		return abstractRuleGod.getLevelPrecPosition();
	}

	@Override
	public Boolean getHasWon() {
		return abstractRuleGod.getHasWon();
	}

	@Override
	public Worker getMovedWorker() {
		return abstractRuleGod.getMovedWorker();
	}

	@Override
	public String getStringParticular() {
		return stringParticular;
	}

	@Override
	public Boolean getIsalive() {
		return abstractRuleGod.getIsalive();
	}

	@Override
	public ArrayList<LightBlock> getWorkerToSend() {
		return workerToSend;
	}

	/**
	 * set Minotaur's state to the given one
	 * @param state
	 */
	@Override
	public void setState(StateNumber state) {
		this.state = state;
		abstractRuleGod.setState(state);
	}

	@Override
	public void setHasWon(Boolean hasWon) {
		abstractRuleGod.setHasWon(hasWon);
	}

	@Override
	public void setLevelPrecPosition(int i) {
		abstractRuleGod.setLevelPrecPosition(i);
	}

	@Override
	public void setMovedWorker(Worker movedWorker) {
		abstractRuleGod.setMovedWorker(movedWorker);
	}

	@Override
	public void setStringParticular(String stringParticular) {
		this.stringParticular = stringParticular;
	}

	@Override
	public void setWorkerToSend(ArrayList<LightBlock> workerToSend) {
		this.workerToSend = workerToSend;
	}

	@Override
	public void setIsalive(Boolean isalive) {
		abstractRuleGod.setIsalive(isalive);
	}

	/**
	 * Method that manages the sequences of moves in a turn based on a player's divinity
	 *
	 * @param s service
	 */
	public void turnHandler(Service s) {
		super.turnHandler(s);
	}

	/**
	 * Method that manages the sequences of moves in a turn based on a player's divinity
	 *
	 * @param s service
	 */
	public void turnHandler_real(Service s){
		abstractRuleGod.turnHandler_real(s);
	}

	/**
	 * Method that sets the movement in this turn
	 */
	@Override
	public void setMoves() {
		abstractRuleGod.setMoves();
		setState(currentPlayer.getAbstractRule().getState());
	}

	/**
	 * method that shows the spaces in which a worker can be moved, also includes the ones occupied by an adversary if Minotaur's power can be activated
	 * @param worker worker of which we want to know where it can be moved
	 */
	@Override
	public void showMoves(Worker worker) {

		Coordinate coordinate = worker.getCoordinate();

		try {

			ArrayList<Coordinate> helpMinotaur = new ArrayList<>();
			ArrayList<Coordinate> helpMinotaur2 = new ArrayList<>();
			ArrayList<LightBlock> helpMinotaur3 = new ArrayList<>();
			ArrayList<LightBlock> helpMinotaur4 = new ArrayList<>();
			helpMinotaur=board.spacesAround(coordinate);
			helpMinotaur2 = board.whereIsOccupied(helpMinotaur);
			Coordinate nextBlockC=null;
			boolean nextBlockFree;
			boolean isOnBoard;
			int coordX=coordinate.getX();
			int coordY=coordinate.getY();
			int cX;
			int cY;

			LightBoard lightBoard = new LightBoard();
			lightBoard.update(board);

			for(Coordinate c : helpMinotaur2){
				if (!board.getBlock(c).getWorkerColor().equals(currentPlayer.getColor())) {
					nextBlockC=nextBlockCoordinates(coordinate,c);
					cX=nextBlockC.getX();
					cY=nextBlockC.getY();
					nextBlockFree=isBlockFree(nextBlockC);
					if (nextBlockFree && (cX!=coordX || cY!=coordY)) {
						helpMinotaur3.add(lightBoard.getLightBlock(c));
					}
				}
			}
			helpMinotaur4 = board.whereIsRightLevel(coordinate, helpMinotaur3);

			for(int i = 0 ; i < helpMinotaur4.size(); i++){
				helpMinotaur4.get(i).setIdWorker(worker.getIdWorker());
				moveList.add(helpMinotaur4.get(i));
			}
			if(moveList.size()==1){
				LightBlock lb = new LightBlock(worker.getCoordinate());
				lb.setIdWorker(worker.getIdWorker());
				abstractRuleGod.getWorkerToSend().add(lb);
			}
			else if (moveList.size()==2 && moveList.get(0).getIdWorker() != worker.getIdWorker()){
				LightBlock lb = new LightBlock(worker.getCoordinate());
				lb.setIdWorker(worker.getIdWorker());
				abstractRuleGod.getWorkerToSend().add(lb);
			}

			abstractRuleGod.showMoves(worker);
			setState(abstractRuleGod.getState());

		}
		catch (Exception e){
			System.out.println(e);
		}
	}

	/**
	 * moves the worker and if necessary activates Minotaur's power
	 *  @param worker worker that is being moved
	 *  @param coordinate final coordinates of the movement
	 *  @param idWorker id number of the worker that is being moved
	 */
	@Override
	public void performMove(Worker worker, Coordinate coordinate, int idWorker) {
		boolean usedMinotaur=false;

		int rivalWorkerId=100;
		Color rivalWorkerColor=Color.ANSI_WHITE;
		Coordinate minotaurStartingBlock = worker.getCoordinate();
		Coordinate nextBlockCoordinates=nextBlockCoordinates(minotaurStartingBlock,coordinate);
		boolean isNextBlockFree=isBlockFree(nextBlockCoordinates);

		if (board.getBlock(coordinate).getOccupied() && isNextBlockFree){
			usedMinotaur=true;
			rivalWorkerId = board.getBlock(coordinate).getWorker();
			rivalWorkerColor=board.getBlock(coordinate).getWorkerColor();
			board.removeWorker(coordinate);
		}

		abstractRuleGod.performMove(worker, coordinate, idWorker);

		setState(abstractRuleGod.getState());

		if (usedMinotaur){
			board.addWorker(nextBlockCoordinates,rivalWorkerId,rivalWorkerColor);
			if(nextPlayer.getColor().equals(rivalWorkerColor)){
				nextPlayer.getWorkers().get(rivalWorkerId).setCoordinate(nextBlockCoordinates);
			}
			else{
				thirdPlayer.getWorkers().get(rivalWorkerId).setCoordinate(nextBlockCoordinates);
			}
		}
		usedMinotaur=false;
	}


	/**
	 * Method that sets the construction of a block in this turn
	 */
	@Override
	public void setBuild() {
		abstractRuleGod.setBuild();
		setState(currentPlayer.getAbstractRule().getState());
	}

	/**
	 * Method that shows the player where it's possible to build
	 *
	 * @param worker worker that got moved in this turn
	 */
	@Override
	public void showBuild(Worker worker) {
		abstractRuleGod.showBuild(worker);
	}

	/**
	 * Method used to build a block
	 *
	 * @param coordinate coordinates where the block will be added
	 */
	@Override
	public void performBuild(Coordinate coordinate) {
		abstractRuleGod.performBuild(coordinate);
		setState(abstractRuleGod.getState());
	}

	/**
	 * Method that sets the state for the player to 0, meaning his/her turn is over
	 */
	@Override
	public void nextTurn() {
		abstractRuleGod.nextTurn();
		setState(abstractRuleGod.getState());
	}

	/**
	 * method that return the coordinates of the next block in the same direction of the first two given
	 * @param minotaurCoordinate starting coordinates
	 * @param coordinate coordinates of the next block
	 * @return coordinates of the block behind the second one in input
	 */
	public Coordinate nextBlockCoordinates(Coordinate minotaurCoordinate, Coordinate coordinate) {
		Coordinate nextBlockCoord=null;
		int mX=minotaurCoordinate.getX();
		int mY=minotaurCoordinate.getY();
		int cX=coordinate.getX();
		int cY=coordinate.getY();
		int nbX=5;
		int nbY=5;
		if (mX==cX){
			if (cY==mY+1){
				nbX=cX;
				nbY=cY+1;
			}
			if (cY==mY-1){
				nbX=cX;
				nbY=cY-1;
			}

		}
		else if (mY==cY){
			if (cX==mX+1){
				nbX=cX+1;
				nbY=cY;
			}
			if (cX==mX-1){
				nbX=cX-1;
				nbY=cY;
			}
		}
		else if (cX==mX-1 && cY==mY-1) {
			nbX=cX-1;
			nbY=cY-1;
		}
		else if (cX==mX+1 && cY==mY-1) {
			nbX=cX+1;
			nbY=cY-1;

		}
		else if (cX==mX-1 && cY==mY+1) {
			nbX=cX-1;
			nbY=cY+1;
		}
		else if (cX==mX+1 && cY==mY+1) {
			nbX=cX+1;
			nbY=cY+1;
		}
		boolean isOnBoard=board.isOnBoard(nbX, nbY);
		if (isOnBoard){
			nextBlockCoord=new Coordinate(nbX,nbY);
			return nextBlockCoord;
		}
		else
			return minotaurCoordinate;
	}

	/**
	 * method that says if a block is occupied or free
	 * @param coordinate coordinates of the block
	 * @return true if the block is free, false otherwise
	 */
	public boolean isBlockFree(Coordinate coordinate){
		if (!board.getBlock(coordinate).getOccupied() && !board.getBlock(coordinate).getIsDome())
			return true;
		else
			return false;
	}

	/**
	 * method that checks if the player has won in this turn
	 */
	@Override
	public void CheckIfWin() {
		abstractRuleGod.CheckIfWin();
		setHasWon(abstractRuleGod.getHasWon());
	}

	@Override
	public void lose() {
		abstractRuleGod.lose();
	}

	@Override
	public void invertPlayer() {
		abstractRuleGod.invertPlayer();
	}

	@Override
	public void CheckList(ArrayList<LightBlock> list) {
		abstractRuleGod.CheckList(list);
	}

	@Override
	public void Win() {
		abstractRuleGod.Win();
	}

}