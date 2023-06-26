package it.polimi.ingsw.PSP45.controller;


import it.polimi.ingsw.PSP45.model.*;
import it.polimi.ingsw.PSP45.utils.Color;
import it.polimi.ingsw.PSP45.utils.Service;
import it.polimi.ingsw.PSP45.utils.StateNumber;

import java.util.ArrayList;

/**
 * @author Filippo Locatelli
 * @author Lorenzo Longaretti
 *
 * class that represent the rules of a turn if the chosen god is Apollo
 */
public class Apollo extends God {

	/**
	 * class constructor
	 *
	 * @param abstractRule abstractRule that will be decorated whit the divinity
	 */
	public Apollo(AbstractRule abstractRule){
		super(abstractRule);
	}

	@Override
	public ArrayList<LightBlock> getMoveList() {
		return abstractRuleGod.getMoveList();
	}

	@Override
	public Player getCurrentPlayer() {
		return abstractRuleGod.getCurrentPlayer();
	}

	@Override
	public ArrayList<LightBlock> getWorkerToSend() {
		return workerToSend;
	}

	/**
	 * @return Apollo's stringParticular attribute
	 */
	@Override
	public String getStringParticular() {
		return stringParticular;
	}

	@Override
	public Boolean getIsalive() {

		return abstractRuleGod.getIsalive();
	}

	@Override
	public int getLevelPrecPosition() {
		return abstractRuleGod.getLevelPrecPosition();
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
	public ArrayList<LightBlock> getBuildList() {
		return abstractRuleGod.getBuildList();
	}

	@Override
	public StateNumber getState() {
		return abstractRuleGod.getState();
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
	public int getTurn() {
		return abstractRuleGod.getTurn();
	}

	/**
	 * set Apollo's state to the given one
	 * @param state
	 */
	@Override
	public void setState(StateNumber state) {
		this.state=state;
		abstractRuleGod.setState(state);
	}

	@Override
	public void setHasWon(Boolean hasWon) {
		this.HasWon = hasWon;
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
	public void turnHandler(Service s){
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
	 * @author Lorenzo Longaretti
	 *
	 * method that shows the spaces in which a worker can be moved, also includes the ones occupied by an adversary if Apollo's power can be activated
	 * @param worker worker of which we want to know where it can be moved
	 */
	@Override
	public void showMoves(Worker worker){
		Coordinate coordinateApollo = worker.getCoordinate();

		try {
			ArrayList<Coordinate> helpApollo = new ArrayList<>();
			ArrayList<Coordinate> helpApollo2 = new ArrayList<>();
			ArrayList<LightBlock> helpApollo3 = new ArrayList<>();
			ArrayList<LightBlock> helpApollo4 = new ArrayList<>();
			helpApollo=board.spacesAround(coordinateApollo);
			helpApollo2 = board.whereIsOccupied(helpApollo);

			LightBoard lightBoard = new LightBoard();
			lightBoard.update(board);

			for(Coordinate c : helpApollo2){
				if (!board.getBlock(c).getWorkerColor().equals(currentPlayer.getColor())) {
					helpApollo3.add(lightBoard.getLightBlock(c));
				}
			}
			helpApollo4 = board.whereIsRightLevel(coordinateApollo, helpApollo3);

			for(int i = 0 ; i< helpApollo4.size(); i++){
				helpApollo4.get(i).setIdWorker(worker.getIdWorker());
				moveList.add(helpApollo4.get(i));
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
	 * @author Lorenzo Longaretti
	 *
	 * moves the worker and if necessary activates Apollo's power
	 *  @param worker worker that is being moved
	 *  @param coordinate final coordinates of the movement
	 *  @param idWorker id number of the worker that is being moved
	 */
	@Override
	public void performMove(Worker worker,Coordinate coordinate, int idWorker){
		int idWorkerHelp=0;
		Color colorHelp = Color.ANSI_WHITE;
		Coordinate wherePutSecond = new Coordinate(worker.getCoordinate().getX(),worker.getCoordinate().getY());
		boolean se = false;
		if (board.getBlock(coordinate).getOccupied()== true) {
			se = true;
			idWorkerHelp = board.getBlock(coordinate).getWorker();
			colorHelp = board.getBlock(coordinate).getWorkerColor();
			board.removeWorker(coordinate);
		}


		abstractRuleGod.performMove(worker,coordinate,idWorker);
		setState(abstractRuleGod.getState());


		if (se) {
			board.addWorker(wherePutSecond,idWorkerHelp,colorHelp);
			if(nextPlayer.getColor().equals(colorHelp)){
				nextPlayer.getWorkers().get(idWorkerHelp).setCoordinate(wherePutSecond);
			}
			else{
				thirdPlayer.getWorkers().get(idWorkerHelp).setCoordinate(wherePutSecond);
			}
		}
	}

	/**
	 * Method that sets the construction of a block in this turn
	 */
	@Override
	public void setBuild() {
		abstractRuleGod.setBuild();
		setState(abstractRuleGod.getState());
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

	@Override
	public void nextTurn() {
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
