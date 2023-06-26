package it.polimi.ingsw.PSP45.controller;

import it.polimi.ingsw.PSP45.model.Coordinate;
import it.polimi.ingsw.PSP45.model.LightBlock;
import it.polimi.ingsw.PSP45.model.Player;
import it.polimi.ingsw.PSP45.model.Worker;
import it.polimi.ingsw.PSP45.utils.Service;
import it.polimi.ingsw.PSP45.utils.StateNumber;
import it.polimi.ingsw.PSP45.utils.frommodeltoview;

import java.util.ArrayList;
/**
 * @author Filippo Locatelli
 *
 * class that represent the rules of a turn if the chosen god is Pan
 */
public class Pan extends God {

	int levelBefore;
	int levelAfter;

	/**
	 * class constructor
	 *
	 * @param abstractRule abstractRule that will be decorated whit the divinity
	 */
	public Pan(AbstractRule abstractRule){
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
	public String getStringParticular() {
		return stringParticular;
	}

	@Override
	public ArrayList<LightBlock> getWorkerToSend() {
		return workerToSend;
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

	/**
	 * set Pan's state to the given one
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
	 * Method that shows the player in which blocks his/hers workers can be moved
	 *
	 * @param worker worker of which we want to kw where it can be moved
	 */
	@Override
	public void showMoves(Worker worker){
		abstractRuleGod.showMoves(worker);
	}

	/**
	 * Method that moves a worker modified for Pan
	 *
	 * @param worker worker that is being moved
	 * @param coordinate final coordinates of the movement
	 * @param idWorker id number of the worker that is being moved
	 */
	@Override
	public void performMove(Worker worker, Coordinate coordinate, int idWorker){
		Coordinate coordinateBefore=worker.getCoordinate();
		levelBefore = board.getBlock(coordinateBefore).getLevel();
		levelAfter=board.getBlock(coordinate).getLevel();
		abstractRuleGod.performMove(worker, coordinate, idWorker);
		panCheckIfWin();
		setHasWon(abstractRuleGod.getHasWon());
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
	 * method that checks if the player has won in this turn
	 */
	@Override
	public void CheckIfWin() {
		abstractRuleGod.CheckIfWin();
		setHasWon(abstractRuleGod.getHasWon());
	}

	/**
	 * method that controls if Pan's power can be activated in this turn, allowing the player to win the game
	 */
	public void panCheckIfWin(){
		System.out.println(levelBefore);
		int leevel = levelAfter+2;
		if (levelBefore>=levelAfter+2){
			abstractRuleGod.Win();
		}
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