package it.polimi.ingsw.PSP45.controller;

import it.polimi.ingsw.PSP45.model.Coordinate;
import it.polimi.ingsw.PSP45.model.LightBlock;
import it.polimi.ingsw.PSP45.model.Player;
import it.polimi.ingsw.PSP45.model.Worker;
import it.polimi.ingsw.PSP45.utils.Color;
import it.polimi.ingsw.PSP45.utils.Service;
import it.polimi.ingsw.PSP45.utils.StateNumber;

import java.util.ArrayList;

/**
 * @author Lorenzo Longaretti
 * @author Filippo Locatelli
 *
 * class that represent the rules of a turn if the chosen god is Artemis
 */
public class Artemis extends God {

	int numOfBuild=0;
	Coordinate firstBuildC=null;
	Coordinate movedWorkerC=null;
	int id = 2;
	/**
	 * class constructor
	 *
	 * @param abstractRule abstractRule that will be decorated whit the divinity
	 */
	public Artemis(AbstractRule abstractRule){
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
	public StateNumber getState() {
		return abstractRuleGod.getState();
	}

	@Override
	public int getTurn() {
		return abstractRuleGod.getTurn();
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
	 * set Artemis's state to the given one
	 * @param state
	 */
	@Override
	public void setState(StateNumber state) {
		this.state = state;
		abstractRuleGod.setState(state);
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

	@Override
	public void setHasWon(Boolean hasWon) {
		abstractRuleGod.setHasWon(hasWon);
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
	public void turnHandler(Service s) {
		super.turnHandler(s);
	}

	/**
	 * Method that manages the sequences of moves in a turn based on a player's divinity, modified to allow Artemis' power
	 *
	 * @param s service
	 */
	public void turnHandler_real(Service s){
		if (state==StateNumber.settingMovement)
			abstractRuleGod.turnHandler_real(s);

		if (state == StateNumber.building && s.getType() == StateNumber.readyToBuild)
			abstractRuleGod.turnHandler_real(s);

		if (state == StateNumber.settingBuilding )
			abstractRuleGod.turnHandler_real(s);

		if (state == StateNumber.moving && s.getType() == StateNumber.readyToMove && numOfBuild==2)
		{
			abstractRuleGod.turnHandler_real(s);
			setState(StateNumber.settingBuilding);
			numOfBuild = 1;
		}

		if (state == StateNumber.moving && s.getType() == StateNumber.readyToMove && numOfBuild==0){
			id = s.getIntservizio();
			abstractRuleGod.setState(StateNumber.onlymoving);
			firstBuildC = board.getBlock(currentPlayer.getSpecificWorker(s.getIntservizio()).getCoordinate()).getCoordinate().clone();
			abstractRuleGod.turnHandler_real(s);
			numOfBuild=1;
			abstractRuleGod.setState(StateNumber.settingMovement);
			abstractRuleGod.service.setType(StateNumber.moving);
			s.setType(StateNumber.moving);
			abstractRuleGod.turnHandler_real(s);
			s.setType(StateNumber.readyToMove);
			setState(StateNumber.moving);
			numOfBuild = 2;
		}

		if ( numOfBuild==1)
		{
			numOfBuild = 0;

		}

		if (state== StateNumber.onlymoving  && s.getType() == StateNumber.readyToMove)
			abstractRuleGod.turnHandler_real(s);

		if (state== StateNumber.onlybuilding && s.getType() == StateNumber.readyToBuild)
			abstractRuleGod.turnHandler_real(s);

		if (state== StateNumber.onlynextturn )
			abstractRuleGod.turnHandler_real(s);
	}

	/**
	 * Method that sets the movement in this turn modified for Artemis
	 */
	@Override
	public void setMoves() {

		if(numOfBuild == 1){
			moveList.clear();
			abstractRuleGod.setMoves();
			setState(StateNumber.moveState);
		}
		else{
			abstractRuleGod.setMoves();
			setState(currentPlayer.getAbstractRule().getState());
		}
	}

	/**
	 * Method that shows the player in which blocks his/hers workers can be moved, modified for Artemis
	 *
	 * @param worker worker of which we want to kw where it can be moved
	 */
	@Override
	public void showMoves(Worker worker) {
		if (numOfBuild==0){
			abstractRuleGod.showMoves(worker);
		}
		if (numOfBuild==1){
			movedWorkerC=worker.getCoordinate().clone();
			abstractRuleGod.showMoves(worker);
			LightBlock moved = new LightBlock(movedWorkerC);
			moved.setIdWorker(worker.getIdWorker());
			moveList.add(moved);

			for (int i = 0; i < moveList.size(); i++) {
				if (moveList.get(i).getC().getX() == firstBuildC.getX() && moveList.get(i).getC().getY() == firstBuildC.getY()) {
					moveList.remove(i);
					i--;
				}
			}

			if (id != worker.getIdWorker()) {
				for (int j = 0; j < moveList.size(); j++) {
					if (moveList.get(j).getIdWorker() != id) {
						moveList.remove(j);
						j--;
					}
				}
			}
			if(abstractRuleGod.getWorkerToSend().size() == 2){
				if(id == 0){
					if(abstractRuleGod.getWorkerToSend().get(0).getIdWorker() == board.getBlock(movedWorkerC).getWorker() ){
						abstractRuleGod.getWorkerToSend().remove(0);
					}
					else{
						abstractRuleGod.getWorkerToSend().remove(1);
					}
				}
				else if(id == 1){
					if(abstractRuleGod.getWorkerToSend().get(0).getIdWorker() == board.getBlock(movedWorkerC).getWorker() ){
						abstractRuleGod.getWorkerToSend().remove(1);
					}
					else{
						abstractRuleGod.getWorkerToSend().remove(0);
					}
				}
			}
		}
	}

	/**
	 * Method that moves a worker, modified for Artemis
	 *
	 * @param worker worker that is being moved
	 * @param coordinate final coordinates of the movement
	 * @param idWorker id number of the worker that is being moved
	 */
	@Override
	public void performMove(Worker worker, Coordinate coordinate, int idWorker) {
		if (numOfBuild==0){
			abstractRuleGod.performMove(worker, coordinate, idWorker);
			setState(abstractRuleGod.getState());

		}
		if (numOfBuild==1){
			if (coordinate.getX()==movedWorkerC.getX() && coordinate.getY()==movedWorkerC.getY())
					abstractRuleGod.setState(StateNumber.settingBuilding);

			else{
			abstractRuleGod.performMove(worker, coordinate, idWorker);
			setState(abstractRuleGod.getState());
			}
		}
		if(numOfBuild == 2) {
			abstractRuleGod.performMove(worker, coordinate, idWorker);
			setState(abstractRuleGod.getState());
		}
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
	public void showBuild(Worker worker){
		abstractRuleGod.showBuild(worker);

	}

	/**
	 * Method used to build a block
	 *
	 * @param coordinate coordinates where the block will be added
	 */
	@Override
	public void performBuild(Coordinate coordinate){
		abstractRuleGod.performBuild(coordinate);
	}

	/**
	 * Method that sets the state for the player to 0, meaning his/her turn is over
	 */
	@Override
	public void nextTurn() {
		abstractRuleGod.nextTurn();
		setState(abstractRuleGod.getState());
	}

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
