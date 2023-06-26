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
 * class that represent the rules of a turn if the chosen god is Hephaestus
 */
public class Hephaestus extends God {

	int levelAfterBuild;
	Coordinate buildC=null;
	Boolean secondBuild = false;
	Boolean endSecondBuild = false;
	Coordinate buildC2=null;

	/**
	 * class constructor
	 *
	 * @param abstractRule abstractRule that will be decorated whit the divinity
	 */
	public Hephaestus(AbstractRule abstractRule){super(abstractRule);}

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

	/**
	 * @return the boolean value of the secondBuild attribute for Hephaestus
	 */
	public Boolean getSecondBuild() {
		return secondBuild;
	}

	/**
	 * @return the boolean value of the getSecondBuild attribute for Hephaestus
	 */
	public Boolean getEndSecondBuild() {
		return endSecondBuild;
	}

	/**
	 * @return the level of the worker after the first build
	 */
	public int getLevelAfterBuild() {
		return levelAfterBuild;
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

	@Override
	public int getLevelPrecPosition() {
		return abstractRuleGod.getLevelPrecPosition();
	}

	/**
	 * set Hephaestus's state to the given one
	 * @param state
	 */
	@Override
	public void setState(StateNumber state) {
		this.state = state;
		abstractRuleGod.setState(state);
	}

	/**
	 * method that sets the value of the boolean attribute secondBuild to the given one
	 * @param secondBuild boolean that represent if Hephaestus is in his first or second build of the turn
	 */
	public void setSecondBuild(Boolean secondBuild) {
		this.secondBuild = secondBuild;
	}

	/**
	 * method that sets the levelAfterBuild attribute to the given one
	 * @param levelAfterBuild level of a worker after the first build of the turn
	 */
	public void setLevelAfterBuild(int levelAfterBuild) {
		this.levelAfterBuild = levelAfterBuild;
	}

	/**
	 * method that sets the value of the boolean attribute endSecondBuild to the given one
	 * @param endSecondBuild boolean that represent if Hephaestus has ended his second build of the turn
	 */
	public void setEndSecondBuild(Boolean endSecondBuild) {
		this.endSecondBuild = endSecondBuild;
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
		abstractRuleGod.setHasWon(hasWon);
	}

	@Override
	public void setLevelPrecPosition(int i) {
		abstractRuleGod.setLevelPrecPosition(i);
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
	 * Method that manages the sequences of moves in a turn based on a player's divinity, modified for Hephaestus
	 *
	 * @param s service
	 */
	public void turnHandler_real(Service s){
		if (state== StateNumber.settingMovement){
			abstractRuleGod.turnHandler_real(s);
		}

		if (state== StateNumber.moving  && s.getType() == StateNumber.readyToMove ){
			abstractRuleGod.turnHandler_real(s);
		}


		if (state== StateNumber.settingBuilding)
			abstractRuleGod.turnHandler_real(s);

		if (state== StateNumber.building && s.getType() == StateNumber.readyToBuild){
			setState(StateNumber.onlybuilding);

			abstractRuleGod.turnHandler_real(s);

			if (getSecondBuild() == true && getEndSecondBuild() == false) {
				setState(StateNumber.settingBuilding);
				abstractRuleGod.turnHandler(s);
				setState(StateNumber.building);
			}
			else if(getEndSecondBuild()){
				setState(StateNumber.onlynextturn);
			}
		}

		if (state== StateNumber.onlybuilding && s.getType() == StateNumber.readyToBuild){
			abstractRuleGod.turnHandler_real(s);
		}

		if (state== StateNumber.onlymoving  && s.getType() == StateNumber.readyToMove )
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
		buildC2 = coordinate;
		abstractRuleGod.performMove(worker, coordinate, idWorker);
		moveList.clear();

	}

	/**
	 * Method that sets the construction of a block in this turn modified for Hephaestus
	 */
	@Override
	public void setBuild() {
		if(getEndSecondBuild() != true ) {
			abstractRuleGod.setBuild();
		}
		else if((movedWorker.getCoordinate().getX() != service.getAvailableCells2().get(0).getC().getX() || movedWorker.getCoordinate().getY() != service.getAvailableCells2().get(0).getC().getY()) ){
			abstractRuleGod.setBuild();
		}
		if(getSecondBuild()){
			setState(StateNumber.settingBuilding);
		}
	}

	/**
	 * Method that shows the player where it's possible to build modified for Hephaestus
	 *
	 * @param worker worker that got moved in this turn
	 */
	@Override
	public void showBuild(Worker worker) {
		abstractRuleGod.showBuild(worker);

		if(getSecondBuild() && getLevelAfterBuild() < 3){
			for(int j = 0 ; j< buildList.size();j++){
				if(buildList.get(j).getC().getX() != buildC.getX() || buildList.get(j).getC().getY() != buildC.getY() ){
					buildList.remove(j);
					j--;
				}
			}
			LightBlock lightBlock = new LightBlock(worker.getCoordinate());
			lightBlock.setLightBlock(board.getBlock(worker.getCoordinate()));
			buildList.add(lightBlock);
			setEndSecondBuild(true);
		}
		else if (getSecondBuild() && getLevelAfterBuild()>=3){
			for (int i = 0; i < buildList.size() ; i++) {
				buildList.remove(i);
				i--;
			}
			LightBlock lightBlock = new LightBlock(worker.getCoordinate());
			lightBlock.setLightBlock(board.getBlock(worker.getCoordinate()));
			buildList.add(lightBlock);
			setEndSecondBuild(true);
		}
	}

	/**
	 * Method used to build a block modified for Hephaestus
	 *
	 * @param coordinate coordinates where the block will be added
	 */
	@Override
	public void performBuild(Coordinate coordinate) {
		if(coordinate.getX() == buildC2.getX() && coordinate.getY() == buildC2.getY()) {
			levelAfterBuild=board.getBlock(coordinate).getLevel();
			setState(abstractRuleGod.getState());
			setSecondBuild(true);
			buildList.clear();
		}
		else{
			buildC=coordinate.clone();
			abstractRuleGod.performBuild(coordinate);
			levelAfterBuild=board.getBlock(coordinate).getLevel();
			setState(abstractRuleGod.getState());
			setSecondBuild(true);
			buildList.clear();
		}
	}

	/**
	 * Method that sets the state for the player to 0, meaning his/her turn is over
	 */
	@Override
	public void nextTurn() {
		abstractRuleGod.nextTurn();
		setState(abstractRuleGod.getState());
		setSecondBuild(false);
		setEndSecondBuild(false);
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


