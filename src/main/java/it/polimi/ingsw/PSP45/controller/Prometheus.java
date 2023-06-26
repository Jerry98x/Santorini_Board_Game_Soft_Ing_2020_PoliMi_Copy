package it.polimi.ingsw.PSP45.controller;

import it.polimi.ingsw.PSP45.model.*;
import it.polimi.ingsw.PSP45.utils.Service;
import it.polimi.ingsw.PSP45.utils.StateNumber;

import java.util.ArrayList;
/**
 * @author Filippo Locatelli
 * @author Lorenzo Longaretti
 *
 * class that represent the rules of a turn if the chosen god is Prometheus
 */
public class Prometheus extends God {

	int control=0;
	Coordinate movedWorkerC1=null;
	Coordinate movedWorkerC2=null;
	Coordinate movedWorkerC=null;
	Coordinate buildC=null;
	Worker MovedWorkerbis = null;
	int id = 2;

	/**
	 * class constructor
	 *
	 * @param abstractRule abstractRule that will be decorated whit the divinity
	 */
	public Prometheus(AbstractRule abstractRule){
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
	public Boolean getIsalive() {
		return abstractRuleGod.getIsalive();
	}

	@Override
	public String getStringParticular() {
		return stringParticular;
	}

	@Override
	public ArrayList<LightBlock> getWorkerToSend() {
		return workerToSend;
	}

	/**
	 * set Prometheus's state to the given one
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
	@Override
	public void turnHandler(Service s) {
		super.turnHandler(s);
	}

	/**
	 * Method that manages the sequences of moves in a turn based on a player's divinity, modified for Prometheus
	 *
	 * @param s service
	 */
	@Override
	public void turnHandler_real(Service s){
		if (state== StateNumber.settingMovement) {
			if(control==0){
				abstractRuleGod.setState(StateNumber.settingBuilding);
				abstractRuleGod.turnHandler_real(s);
			}
			else{
				abstractRuleGod.turnHandler_real(s);
			}
		}
		if (state== StateNumber.moving  && s.getType() == StateNumber.readyToMove) {
			abstractRuleGod.turnHandler_real(s);
		}

		if (state== StateNumber.onlymoving  && s.getType() == StateNumber.readyToMove) {
			abstractRuleGod.turnHandler_real(s);
		}

		if (state== StateNumber.settingBuilding){
			abstractRuleGod.turnHandler_real(s);
		}

		if (state== StateNumber.building && s.getType() == StateNumber.readyToBuild) {
			if (control == 0) {
				id=s.getAvailableCells2().get(0).getIdWorker();
				MovedWorkerbis = currentPlayer.getSpecificWorker(board.getBlock(s.getAvailableCells2().get(0).getC()).getWorker());
				control = 10;
				abstractRuleGod.setState(StateNumber.settingBuilding);
				abstractRuleGod.turnHandler_real(s);
			}
			else if(control==10){
				if (s.getAvailableCells2().get(0).getC().getX()==movedWorkerC.getX() && s.getAvailableCells2().get(0).getC().getY()==movedWorkerC.getY()){
					control=2;
				}
				else {
					control = 1;
					abstractRuleGod.setState(StateNumber.onlybuilding);
					abstractRuleGod.turnHandler_real(s);

				}

				abstractRuleGod.setState(StateNumber.settingMovement);
				abstractRuleGod.turnHandler_real(s);
			}
			else if(control == 2 || control == 1){
				abstractRuleGod.turnHandler_real(s);
			}
		}

		if (state== StateNumber.onlybuilding && s.getType() == StateNumber.readyToBuild){
			abstractRuleGod.turnHandler_real(s);
		}

		if (state== StateNumber.onlynextturn ){
			abstractRuleGod.turnHandler_real(s);
		}

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
	 * method that shows the spaces in which a worker can be moved, modified for Prometheus
	 * @param worker worker of which we want to know where it can be moved
	 */
	@Override
	public void showMoves(Worker worker) {
		abstractRuleGod.showMoves(worker);
		if (control==1){
			int l = board.getBlock(worker.getCoordinate()).getLevel();
			for (int i = 0; i < moveList.size(); i++) {
				if (board.getBlock(moveList.get(i).getC()).getLevel()  > l || moveList.get(i).getIdWorker() != MovedWorkerbis.getIdWorker()) {
					moveList.remove(i);
					i--;
				}
			}
			if(abstractRuleGod.getWorkerToSend().size() == 2){
				if(id == 0){
					if(abstractRuleGod.getWorkerToSend().get(0).getIdWorker() == MovedWorkerbis.getIdWorker() ){
						abstractRuleGod.getWorkerToSend().remove(1);
					}
					else{
						abstractRuleGod.getWorkerToSend().remove(0);
					}
				}
				else if(id == 1){
					if(abstractRuleGod.getWorkerToSend().get(0).getIdWorker() == MovedWorkerbis.getIdWorker() ){
						abstractRuleGod.getWorkerToSend().remove(1);
					}
					else{
						abstractRuleGod.getWorkerToSend().remove(0);
					}
				}
			}
			else if(abstractRuleGod.getWorkerToSend().size() == 1){
				if(abstractRuleGod.getWorkerToSend().get(0).getIdWorker() == 0){
					//abstractRuleGod.getWorkerToSend().get(0).setIdWorker(1);
				}
				else {
					//abstractRuleGod.getWorkerToSend().get(0).setIdWorker(0);
				}
			}
		}
		else if(control==2){
			if(abstractRuleGod.getWorkerToSend().size() == 2){
				if(id == 0){
					if(abstractRuleGod.getWorkerToSend().get(0).getIdWorker() == MovedWorkerbis.getIdWorker() ){
						abstractRuleGod.getWorkerToSend().remove(1);
					}
					else{
						abstractRuleGod.getWorkerToSend().remove(0);
					}
				}

				else if(id == 1){
					if(abstractRuleGod.getWorkerToSend().get(0).getIdWorker() == MovedWorkerbis.getIdWorker() ){
						abstractRuleGod.getWorkerToSend().remove(1);
					}
					else{
						abstractRuleGod.getWorkerToSend().remove(0);
					}
				}
			}
			else if(abstractRuleGod.getWorkerToSend().size() == 1){
				if(abstractRuleGod.getWorkerToSend().get(0).getIdWorker() == 0){
					//abstractRuleGod.getWorkerToSend().get(0).setIdWorker(1);
				}
				else {
					//abstractRuleGod.getWorkerToSend().get(0).setIdWorker(0);
				}
			}

		}
	}
	/**
	 * moves the worker, modified for Prometheus
	 *  @param worker worker that is being moved
	 *  @param coordinate final coordinates of the movement
	 *  @param idWorker id number of the worker that is being moved
	 */
	@Override
	public void performMove(Worker worker, Coordinate coordinate, int idWorker) {
		if (control==1){
			abstractRuleGod.performMove(worker, coordinate, idWorker);
		}
		else {
			abstractRuleGod.performMove(worker, coordinate, idWorker);
		}
	}

	/**
	 * Method that sets the construction of a block in this turn, modified for Prometheus
	 */
	@Override
	public void setBuild() {
		abstractRuleGod.setBuild();
		if(control == 10){
			abstractRuleGod.setState(StateNumber.settingMovement);
		}
	}

	/**
	 * Method that shows the player where it's possible to build, modified for Prometheus
	 *
	 * @param worker worker that got moved in this turn
	 */
	@Override
	public void showBuild(Worker worker) {
		if (control==0){
			movedWorkerC1 = currentPlayer.getSpecificWorker(0).getCoordinate().clone();
			movedWorkerC2 = currentPlayer.getSpecificWorker(1).getCoordinate().clone();
			LightBlock moved = new LightBlock(movedWorkerC1);
			LightBlock moved1 = new LightBlock(movedWorkerC2);
			moved.setIdWorker(currentPlayer.getSpecificWorker(0).getIdWorker());
			moved1.setIdWorker(currentPlayer.getSpecificWorker(1).getIdWorker());
			buildList.add(moved);
			buildList.add(moved1);
			setMovedWorker(currentPlayer.getSpecificWorker(0));
		}
		else if (control==10) {
			abstractRuleGod.buildList.clear();
			abstractRuleGod.showBuild(MovedWorkerbis);
			movedWorkerC=MovedWorkerbis.getCoordinate().clone();
			buildList.add(new LightBlock(movedWorkerC));

		}
		else {
			if (worker.getIdWorker() == MovedWorkerbis.getIdWorker()) {
				buildList.clear();
				abstractRuleGod.showBuild(worker);
			}

		}

	}

	/**
	 * Method used to build a block, modified for Prometheus
	 *
	 * @param coordinate coordinates where the block will be added
	 */
	@Override
	public void performBuild(Coordinate coordinate) {
		if (control==0) {
			Coordinate c0=currentPlayer.getSpecificWorker(0).getCoordinate();
			if ((c0.getX() == coordinate.getX() && c0.getY() == coordinate.getY())) {
				abstractRuleGod.setMovedWorker(currentPlayer.getSpecificWorker(0));
			}
			else {
				abstractRuleGod.setMovedWorker(currentPlayer.getSpecificWorker(1));
			}

		}
		else if (control==10){
			buildC=coordinate;
			buildC =service.getAvailableCells2().get(0).getC();
		}

		else
			abstractRuleGod.performBuild(coordinate);
	}

	/**
	 * Method that sets the state for the player to 0, meaning his/her turn is over
	 */
	@Override
	public void nextTurn() {
		abstractRuleGod.nextTurn();	setState(abstractRuleGod.getState());
		control =0;
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
