package it.polimi.ingsw.PSP45.controller;

import it.polimi.ingsw.PSP45.model.Coordinate;
import it.polimi.ingsw.PSP45.model.LightBlock;
import it.polimi.ingsw.PSP45.model.Player;
import it.polimi.ingsw.PSP45.model.Worker;
import it.polimi.ingsw.PSP45.utils.Service;
import it.polimi.ingsw.PSP45.utils.StateNumber;

import java.util.ArrayList;
/**
 * @author Filippo Locatelli
 *
 * class that represent the rules of a turn if the chosen god is Demeter
 */
public class Demeter extends God {

	int numOfBuild=0;
	Coordinate firstBuildC=null;
	Coordinate movedWorkerC=null;

	/**
	 * class constructor
	 *
	 * @param abstractRule abstractRule that will be decorated whit the divinity
	 */
	public Demeter(AbstractRule abstractRule){
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
	public ArrayList<LightBlock> getWorkerToSend() {
		return workerToSend;
	}

	@Override
	public String getStringParticular() {
		return stringParticular;
	}

	/**
	 * set Demeter's state to the given one
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
	 * Method that manages the sequences of moves in a turn based on a player's divinity, modified for Demeter
	 *
	 * @param s service
	 */
	public void turnHandler_real(Service s){
		if (state==StateNumber.settingMovement)
			abstractRuleGod.turnHandler_real(s);

		if (state == StateNumber.moving && s.getType() == StateNumber.readyToMove)
			abstractRuleGod.turnHandler_real(s);

		if (state == StateNumber.settingBuilding )
			abstractRuleGod.turnHandler_real(s);


		if (state == StateNumber.settingBuilding)
			abstractRuleGod.turnHandler_real(s);

		if (state == StateNumber.building && s.getType() == StateNumber.readyToBuild && numOfBuild==1)
		{
			abstractRuleGod.turnHandler_real(s);
			s.setType(StateNumber.setgod);
			numOfBuild = 0;
		}

		if (state == StateNumber.building && s.getType() == StateNumber.readyToBuild && numOfBuild==0){
			abstractRuleGod.setState(StateNumber.onlybuilding);
			abstractRuleGod.turnHandler_real(s);
			numOfBuild=1;
			abstractRuleGod.setState(StateNumber.settingBuilding);
			s.setType(StateNumber.readyToBuild);
			abstractRuleGod.service.setType(StateNumber.building);
			abstractRuleGod.turnHandler_real(s);
			setState(StateNumber.building);
		}

		if (state== StateNumber.onlymoving  && s.getType() == StateNumber.readyToMove)
			abstractRuleGod.turnHandler_real(s);

		if (state== StateNumber.onlybuilding && s.getType() == StateNumber.readyToBuild)
			abstractRuleGod.turnHandler_real(s);

		if (state== StateNumber.onlynextturn)
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
	 * Method that shows the player in which blocks his/hers workers can be moved
	 *
	 * @param worker worker of which we want to kw where it can be moved
	 */
	@Override
	public void showMoves(Worker worker){
		abstractRuleGod.showMoves(worker);
	}

	/**
	 * Method that moves a worker
	 *
	 * @param worker worker that is being moved
	 * @param coordinate final coordinates of the movement
	 * @param idWorker id number of the worker that is being moved
	 */
	@Override
	public void performMove(Worker worker, Coordinate coordinate, int idWorker){
		abstractRuleGod.performMove(worker, coordinate, idWorker);
	}

	/**
	 * Method that sets the construction of a block in this turn modified for Demeter
	 */
	@Override
	public void setBuild() {
		abstractRuleGod.setBuild();
		if(numOfBuild == 1){
			setState(StateNumber.moveState);
		}
		else{
			setState(currentPlayer.getAbstractRule().getState());
		}
	}

	/**
	 * Method that shows the player where it's possible to build modified for Demeter
	 *
	 * @param worker worker that got moved in this turn
	 */
	@Override
	public void showBuild(Worker worker) {
		if (numOfBuild==0)
			abstractRuleGod.showBuild(worker);
		if (numOfBuild==1){
			buildList.clear();
			movedWorkerC=worker.getCoordinate().clone();
			abstractRuleGod.showBuild(worker);
			buildList.add(new LightBlock(movedWorkerC));
			for (int i = 0; i < buildList.size(); i++) {
				if (buildList.get(i).getC().getX()==firstBuildC.getX() && buildList.get(i).getC().getY()==firstBuildC.getY()) {
					buildList.remove(i);
					i--;
				}
			}
		}
	}

	/**
	 * Method used to build a block modified for Demeter
	 *
	 * @param coordinate coordinates where the block will be added
	 */
	@Override
	public void performBuild(Coordinate coordinate) {
		if (numOfBuild==0){
			abstractRuleGod.performBuild(coordinate);
			setState(abstractRuleGod.getState());
			firstBuildC = coordinate.clone();
		}
		if (numOfBuild==1){
			if (coordinate.getX()==movedWorkerC.getX() && coordinate.getY()==movedWorkerC.getY()) {
				abstractRuleGod.setState(StateNumber.onlynextturn);
			}
			else{
				abstractRuleGod.performBuild(coordinate);
				setState(abstractRuleGod.getState());
			}
		}
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
