package it.polimi.ingsw.PSP45.controller;

import it.polimi.ingsw.PSP45.model.*;
import it.polimi.ingsw.PSP45.utils.Color;
import it.polimi.ingsw.PSP45.utils.Service;
import it.polimi.ingsw.PSP45.utils.StateNumber;

import java.util.ArrayList;

/**
 * @author Lorenzo Longaretti
 *
 * class that represent the rules of a turn if the chosen god is Athena
 */
public class Athena extends God{
	Boolean isActive;
	Boolean LevelUp;
	int i = 0;

	/**
	 * class constructor
	 *
	 * @param abstractRule abstractRule that will be decorated whit the divinity
	 * @param isActive if true, this player is using Athena, if false it's an opponent
	 */
	public Athena(AbstractRule abstractRule,Boolean isActive){
		super(abstractRule);
		this.isActive = isActive;
		this.LevelUp = false;
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
	public ArrayList<LightBlock> getWorkerToSend() {
		return workerToSend;
	}

	@Override
	public String getStringParticular() {
		return stringParticular;
	}

	/**
	 * @return isActive attribute for Athena
	 */
	public Boolean getActive() {
		return isActive;
	}

	/**
	 * @return Athena's levelUp attribute
	 */
	public Boolean getLevelUp() {
		return LevelUp;
	}

	@Override
	public Boolean getIsalive() {
		return abstractRuleGod.getIsalive();
	}

	@Override
	public Boolean getHasWon() {
		return abstractRuleGod.getHasWon();
	}

	@Override
	public Worker getMovedWorker() {
		return abstractRuleGod.getMovedWorker();
	}

	/**
	 * set Athena's state to the given one
	 * @param state
	 */
	@Override
	public void setState(StateNumber state) {
		this.state=state;
		abstractRuleGod.setState(state);
	}

	/**
	 * sets Athena's isAlive attribute to the given one
	 * @param isalive
	 */
	@Override
	public void setIsalive(Boolean isalive) {
		abstractRuleGod.setIsalive(isalive);
	}

	/**
	 * sets Athena's levelUp attribute to the one given in input
	 * @param levelUp
	 */
	public void setLevelUp(Boolean levelUp) {
		LevelUp = levelUp;
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
	public void setHasWon(Boolean hasWon) {
		this.HasWon = hasWon;
	}

	@Override
	public void setLevelPrecPosition(int i) {
		abstractRuleGod.setLevelPrecPosition(i);
	}

	/**
	 * Method that manages the sequences of moves in a turn based on a player's divinity
	 *
	 * @param s service
	 */
	public void turnHandler(Service s){
		if(i == 0){
			setLevelUp(false);
		}
		i++;
		super.turnHandler(s);
	}

	/**
	 * Method that manages the sequences of moves in a turn based on a player's divinity
	 *
	 * @param s service
	 */
	public void turnHandler_real(Service s){
		AbstractRule abstractRule1 = currentPlayer.getAbstractRule();
		abstractRuleGod.turnHandler_real(s);
	}

	/**
	 * Method that sets the movement in this turn
	 */
	@Override
	public void setMoves(){
		abstractRuleGod.setMoves();
		setState(currentPlayer.getAbstractRule().getState());
	}

	/**
	 * Method that shows the player in which blocks his/hers workers can be moved, modified for Athena
	 *
	 * @param worker worker of which we want to kw where it can be moved
	 */
	@Override
	public void showMoves(Worker worker){
		abstractRuleGod.showMoves(worker);
		if(isActive == false) {
			Athena second = (Athena)nextPlayer.getAbstractRule();
			Boolean thirdCheck = false;
			if(thirdPlayer != null ) {
				Athena third = (Athena) thirdPlayer.getAbstractRule();
				if(third.getLevelUp() == true ){thirdCheck = true;}
			}

			if(second.getLevelUp() || thirdCheck){
				System.out.println(moveList.size());
				for(int j = 0; j < moveList.size(); j++){
					if (board.getBlock(moveList.get(j).getC()).getLevel() > board.getBlock(worker.getCoordinate()).getLevel() ){
						moveList.remove(j);
						j--;
					}
				}

			}

		}
	}

	/**
	 * Method that moves a worker, modified for Athena
	 *
	 * @param worker worker that is being moved
	 * @param coordinate final coordinates of the movement
	 * @param idWorker id number of the worker that is being moved
	 */
	@Override
	public void performMove(Worker worker,Coordinate coordinate, int idWorker){
		Coordinate hepCoordinate = worker.getCoordinate().clone();
		abstractRuleGod.performMove(worker,coordinate,idWorker);
		setState(abstractRuleGod.getState());
		if(board.getBlock(worker.getCoordinate()).getLevel() > board.getBlock(hepCoordinate).getLevel() && getActive()== true){
			setLevelUp(true);
		}
	}

	/**
	 * Method that sets the construction of a block in this turn
	 */
	@Override
	public void setBuild() {
		AbstractRule abstractRule1 = currentPlayer.getAbstractRule();
		abstractRuleGod.setBuild();
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
		}

	/**
	 * Method that sets the state for the player to 0, meaning his/her turn is over
	 */
	@Override
	public void nextTurn() {
		i = 0;
		abstractRuleGod.nextTurn();
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




