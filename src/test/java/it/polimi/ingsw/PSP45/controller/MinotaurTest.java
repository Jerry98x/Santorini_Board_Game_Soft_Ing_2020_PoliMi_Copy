package it.polimi.ingsw.PSP45.controller;

import it.polimi.ingsw.PSP45.model.*;
import it.polimi.ingsw.PSP45.utils.Color;
import it.polimi.ingsw.PSP45.utils.GodName;
import it.polimi.ingsw.PSP45.utils.Service;
import it.polimi.ingsw.PSP45.utils.StateNumber;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Filippo Locatelli
 *
 */
public class MinotaurTest {
	Minotaur minotaur =null;
	Rules rules =null;
	Player cPlayer=null;
	Player nPlayer=null;
	Player tPlayer=null;
	Board board=null;

	@Before
	public void setUp() {
		board=new Board();
		cPlayer=new Player("nome",0, GodName.minotaur, Color.ANSI_RED);
		nPlayer=new Player("nome",0, GodName.apollo, Color.ANSI_BLUE);
		tPlayer=new Player("nome",0,GodName.apollo,Color.ANSI_PURPLE);
		rules =new Rules(1,board,cPlayer,nPlayer,tPlayer);
		minotaur =new Minotaur(rules);
		cPlayer.setAbstractRule(minotaur);
	}

	@After
	public void tearDown() {
		minotaur =null;
	}

	@Test
	public void showMoves_allFreeAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));
		minotaur.showMoves(cWorker0);
		assertEquals(testArray.size(), minotaur.moveList.size());

		for (int i = 0; i < minotaur.moveList.size(); i++) {
			assertEquals(minotaur.moveList.get(i).getC().getX(),testArray.get(i).getX());
			assertEquals(minotaur.moveList.get(i).getC().getY(),testArray.get(i).getY());
		}
	}

	@Test
	public void showMoves_1DomeAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		board.getBlock(new Coordinate(1,0)).setDome();
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));
		minotaur.showMoves(cWorker0);
		assertEquals(testArray.size(), minotaur.moveList.size());

		for (int i = 0; i < minotaur.moveList.size(); i++) {
			assertEquals(minotaur.moveList.get(i).getC().getX(),testArray.get(i).getX());
			assertEquals(minotaur.moveList.get(i).getC().getY(),testArray.get(i).getY());
		}
	}

	@Test
	public void showMoves_samePlayersWorkerInBlockAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(1,1);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));

		minotaur.showMoves(cWorker0);

		assertEquals(testArray.size(), minotaur.moveList.size());

		for (int i = 0; i < minotaur.moveList.size(); i++) {
			assertEquals(minotaur.moveList.get(i).getC().getX(),testArray.get(i).getX());
			assertEquals(minotaur.moveList.get(i).getC().getY(),testArray.get(i).getY());
		}
	}

	@Test
	public void showMoves_levelTooHigh_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		board.getBlock(new Coordinate(0,1)).setLevel(3);

		ArrayList<Coordinate> testArray=new ArrayList<>();
		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(1,1));
		minotaur.showMoves(cWorker0);
		assertEquals(testArray.size(), minotaur.moveList.size());

		for (int i = 0; i < minotaur.moveList.size(); i++) {
			assertEquals(minotaur.moveList.get(i).getC().getX(),testArray.get(i).getX());
			assertEquals(minotaur.moveList.get(i).getC().getY(),testArray.get(i).getY());
		}
	}

	@Test
	public void showMoves_1OccupiedAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(1,1);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		ArrayList<Coordinate> testArray=new ArrayList<>();
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));

		minotaur.showMoves(cWorker0);
		assertEquals(testArray.size(), minotaur.moveList.size());

		for (int i = 0; i < minotaur.moveList.size(); i++) {
			assertEquals(minotaur.moveList.get(i).getC().getX(),testArray.get(i).getX());
			assertEquals(minotaur.moveList.get(i).getC().getY(),testArray.get(i).getY());
		}
	}

	@Test
	public void showMoves_2OccupiedAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,2);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(1,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(3,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();
		testArray.add(new Coordinate(1,2));
		testArray.add(new Coordinate(3,3));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));

		minotaur.showMoves(cWorker0);
		assertEquals(testArray.size(), minotaur.moveList.size());

		for (int i = 0; i < minotaur.moveList.size(); i++) {
			assertEquals(minotaur.moveList.get(i).getC().getX(),testArray.get(i).getX());
			assertEquals(minotaur.moveList.get(i).getC().getY(),testArray.get(i).getY());
		}
	}

	@Test
	public void showMoves_2OccupiedAroundDomeAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,2);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(1,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(3,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		board.getBlock(new Coordinate(3,1)).setDome();
		ArrayList<Coordinate> testArray=new ArrayList<>();
		testArray.add(new Coordinate(1,2));
		testArray.add(new Coordinate(3,3));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));

		minotaur.showMoves(cWorker0);
		assertEquals(testArray.size(), minotaur.moveList.size());

		for (int i = 0; i < minotaur.moveList.size(); i++) {
			assertEquals(minotaur.moveList.get(i).getC().getX(),testArray.get(i).getX());
			assertEquals(minotaur.moveList.get(i).getC().getY(),testArray.get(i).getY());
		}
	}

	@Test
	public void showMoves_1OccupiedAroundAndNextCellOccupied_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,2);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(1,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,2);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));
		testArray.add(new Coordinate(3,3));

		minotaur.showMoves(cWorker0);
		assertEquals(testArray.size(), minotaur.moveList.size());

		for (int i = 0; i < minotaur.moveList.size(); i++) {
			assertEquals(minotaur.moveList.get(i).getC().getX(),testArray.get(i).getX());
			assertEquals(minotaur.moveList.get(i).getC().getY(),testArray.get(i).getY());
		}
	}

	@Test
	public void showMoves_1OccupiedAroundAndNextCellDome_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,2);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(1,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(3,0);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		board.getBlock(new Coordinate(0,2)).setDome();

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));
		testArray.add(new Coordinate(3,3));

		minotaur.showMoves(cWorker0);
		assertEquals(testArray.size(), minotaur.moveList.size());

		for (int i = 0; i < minotaur.moveList.size(); i++) {
			assertEquals(minotaur.moveList.get(i).getC().getX(),testArray.get(i).getX());
			assertEquals(minotaur.moveList.get(i).getC().getY(),testArray.get(i).getY());
		}
	}

	@Test
	public void performMove_finalCoordinateFree_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(2,2);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		minotaur.performMove(cWorker0,new Coordinate(1,0),0);

		assertEquals(1,cWorker0.getCoordinate().getX());
		assertEquals(0,cWorker0.getCoordinate().getY());
		assertEquals(cc1.getX(),cWorker1.getCoordinate().getX());
		assertEquals(cc1.getY(),cWorker1.getCoordinate().getY());
		assertEquals(nc0.getX(),nWorker0.getCoordinate().getX());
		assertEquals(nc0.getY(),nWorker0.getCoordinate().getY());
		assertEquals(nc1.getX(),nWorker1.getCoordinate().getX());
		assertEquals(nc1.getY(),nWorker1.getCoordinate().getY());
	}

	@Test
	public void performMove_finalCoordinateOccupiedAndNextCellFree_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(2,2);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(1,0);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		minotaur.performMove(cWorker0,new Coordinate(1,0),0);

		assertEquals(1,cWorker0.getCoordinate().getX());
		assertEquals(0,cWorker0.getCoordinate().getY());
		assertEquals(cc1.getX(),cWorker1.getCoordinate().getX());
		assertEquals(cc1.getY(),cWorker1.getCoordinate().getY());
		assertEquals(2,nWorker0.getCoordinate().getX());
		assertEquals(0,nWorker0.getCoordinate().getY());
		assertEquals(nc1.getX(),nWorker1.getCoordinate().getX());
		assertEquals(nc1.getY(),nWorker1.getCoordinate().getY());
	}

	@Test
	public void nextBlockCoordinates_blockInBoardCenterNextBlockAround_correctOutput(){
		Coordinate minotaurCoordinate=new Coordinate(2,2);
		Coordinate coordinate=new Coordinate(2,3);
		Coordinate nbc= minotaur.nextBlockCoordinates(minotaurCoordinate,coordinate);
		assertEquals(2,nbc.getX());
		assertEquals(4,nbc.getY());
	}

	@Test
	public void nextBlockCoordinates_blockInBoardCenterNextBlockNotAround_correctOutput(){
		Coordinate minotaurCoordinate=new Coordinate(2,2);
		Coordinate coordinate=new Coordinate(4,4);
		Coordinate nbc= minotaur.nextBlockCoordinates(minotaurCoordinate,coordinate);
		assertEquals(minotaurCoordinate.getX(),nbc.getX());
		assertEquals(minotaurCoordinate.getY(),nbc.getY());
	}

	@Test
	public void nextBlockCoordinates_blockInBoardSideNextBlockInCorrectPosition_correctOutput(){
		Coordinate minotaurCoordinate=new Coordinate(0,2);
		Coordinate coordinate=new Coordinate(0,3);
		Coordinate nbc= minotaur.nextBlockCoordinates(minotaurCoordinate,coordinate);
		assertEquals(0,nbc.getX());
		assertEquals(4,nbc.getY());
	}

	@Test
	public void nextBlockCoordinates_blockInBoardSideNextBlockShouldNotExist_correctOutput(){
		Coordinate minotaurCoordinate=new Coordinate(0,1);
		Coordinate coordinate=new Coordinate(0,0);
		Coordinate nbc= minotaur.nextBlockCoordinates(minotaurCoordinate,coordinate);
		assertEquals(minotaurCoordinate.getX(),nbc.getX());
		assertEquals(minotaurCoordinate.getY(),nbc.getY());
	}

	@Test
	public void isBlockFree_blockIsNotOccupied_shouldReturnTrue(){
		Coordinate c=new Coordinate(2,2);
		board.getBlock(c).setNotOccupied();
		assertTrue(minotaur.isBlockFree(c));
	}

	@Test
	public void isBlockFree_blockIsNotDome_shouldReturnTrue(){
		Coordinate c=new Coordinate(2,2);
		assertTrue(minotaur.isBlockFree(c));
	}

	@Test
	public void isBlockFree_blockIsOccupied_shouldReturnFalse(){
		Coordinate c=new Coordinate(2,2);
		board.getBlock(c).setOccupied();
		assertFalse(minotaur.isBlockFree(c));
	}

	@Test
	public void isBlockFree_blockIsDome_shouldReturnFalse(){
		Coordinate c=new Coordinate(2,2);
		board.getBlock(c).setDome();
		assertFalse(minotaur.isBlockFree(c));
	}

	@Test
	public void turnHandler_real_stateIsSettingMovement_shouldBeMoving(){
		minotaur.setState(StateNumber.settingMovement);
		Service s = new Service(StateNumber.settingMovement,"codice","whatshouldido");
		minotaur.turnHandler_real(s);
		assertEquals(StateNumber.moving, minotaur.getState());
	}

	@Test
	public void turnHandler_real_stateIsMoving_shouldBeBuilding(){
		minotaur.setState(StateNumber.moving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		minotaur.turnHandler_real(s);
		assertEquals(StateNumber.building, minotaur.getState());
	}

	@Test
	public void turnHandler_real_stateIsSettingBuilding_shouldBeBuilding(){
		minotaur.setState(StateNumber.settingBuilding);
		Service s = new Service(StateNumber.settingBuilding,"codice","whatshouldido");
		minotaur.setMovedWorker(cPlayer.getSpecificWorker(0));
		minotaur.turnHandler_real(s);
		assertEquals(StateNumber.building, minotaur.getState());
	}

	@Test
	public void turnHandler_real_stateIsBuilding_shouldBeSettingMovement(){
		minotaur.setState(StateNumber.building);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		minotaur.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, minotaur.getState());
	}

	@Test
	public void turnHandler_real_stateIsOnlyMoving_shouldBeOnlyMoving(){
		minotaur.setState(StateNumber.onlymoving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		minotaur.turnHandler_real(s);
		assertEquals(StateNumber.onlymoving, minotaur.getState());
	}

	@Test
	public void turnHandler_real_stateIsOnlyBuilding_shouldBeOnlyBuilding(){
		minotaur.setState(StateNumber.onlybuilding);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		minotaur.turnHandler_real(s);
		assertEquals(StateNumber.onlybuilding, minotaur.getState());
	}

	@Test
	public void turnHandler_real_stateIsOnlyNextTurn_shouldBeSettingMovement(){
		minotaur.setState(StateNumber.onlynextturn);
		Service s = new Service(StateNumber.onlynextturn,"codice","whatshouldido");
		minotaur.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, minotaur.getState());
	}

	@Test
	public void setMove_worker1InBoardCornerWorker2InOppositeCorner_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(1,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(3,3));
		testArray.add(new Coordinate(4,3));
		testArray.add(new Coordinate(3,4));

		minotaur.setMoves();

		assertEquals(testArray.size(), minotaur.moveList.size());

		for (int i = 0; i < minotaur.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), minotaur.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), minotaur.moveList.get(i).getC().getY());
		}
	}

	@Test
	public void setMove_worker1InSideWorker2InDifferentCorner_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(1,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(3,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(3,3));
		testArray.add(new Coordinate(4,3));
		testArray.add(new Coordinate(3,4));

		minotaur.setMoves();

		assertEquals(testArray.size(), minotaur.moveList.size());

		for (int i = 0; i < minotaur.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), minotaur.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), minotaur.moveList.get(i).getC().getY());
		}
	}

	@Test
	public void setMove_worker1InSideWithOpponentAroundWorker2InDifferentCorner_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,1);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(1,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(3,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(3,3));
		testArray.add(new Coordinate(4,3));
		testArray.add(new Coordinate(3,4));

		minotaur.setMoves();

		assertEquals(testArray.size(), minotaur.moveList.size());

		for (int i = 0; i < minotaur.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), minotaur.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), minotaur.moveList.get(i).getC().getY());
		}
	}

	@Test
	public void setMove_worker1InCenterWorker2InDifferentCorner_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,2);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(1,2));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));
		testArray.add(new Coordinate(3,3));
		testArray.add(new Coordinate(3,3));
		testArray.add(new Coordinate(4,3));
		testArray.add(new Coordinate(3,4));

		minotaur.setMoves();

		assertEquals(testArray.size(), minotaur.moveList.size());

		for (int i = 0; i < minotaur.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), minotaur.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), minotaur.moveList.get(i).getC().getY());
		}
	}

	@Test
	public void setBuild_movedWorkerInCornerAllFreeAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		minotaur.setMovedWorker(cWorker0);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));

		minotaur.setBuild();

		assertEquals(testArray.size(), minotaur.buildList.size());

		for (int i = 0; i < minotaur.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), minotaur.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), minotaur.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void setBuild_movedWorkerInCorner1WorkerAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(1,0);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		minotaur.setMovedWorker(cWorker0);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));

		minotaur.setBuild();

		assertEquals(testArray.size(), minotaur.buildList.size());

		for (int i = 0; i < minotaur.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), minotaur.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), minotaur.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void setBuild_movedWorkerInCorner1DomeAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,0);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		minotaur.setMovedWorker(cWorker0);
		board.getBlock(new Coordinate(1,0)).setDome();
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));

		minotaur.setBuild();

		assertEquals(testArray.size(), minotaur.buildList.size());

		for (int i = 0; i < minotaur.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), minotaur.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), minotaur.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void setBuild_movedWorkerInCornerAllDomeAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,0);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		minotaur.setMovedWorker(cWorker0);

		board.getBlock(new Coordinate(1,0)).setDome();
		board.getBlock(new Coordinate(1,1)).setDome();
		board.getBlock(new Coordinate(0,1)).setDome();
		ArrayList<Coordinate> testArray=new ArrayList<>();

		minotaur.setBuild();

		assertEquals(testArray.size(), minotaur.buildList.size());
	}

	@Test
	public void setBuild_movedWorkerInSideAllFreeAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,0);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		minotaur.setMovedWorker(cWorker0);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(3,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));

		minotaur.setBuild();

		assertEquals(testArray.size(), minotaur.buildList.size());

		for (int i = 0; i < minotaur.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), minotaur.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), minotaur.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void setBuild_movedWorkerInSide1WorkerAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(3,0);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		minotaur.setMovedWorker(cWorker0);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));

		minotaur.setBuild();

		assertEquals(testArray.size(), minotaur.buildList.size());

		for (int i = 0; i < minotaur.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), minotaur.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), minotaur.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void setBuild_movedWorkerInSide1DomeAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,0);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		minotaur.setMovedWorker(cWorker0);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		board.getBlock(new Coordinate(3,0)).setDome();
		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));

		minotaur.setBuild();

		assertEquals(testArray.size(), minotaur.buildList.size());

		for (int i = 0; i < minotaur.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), minotaur.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), minotaur.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_workerInCornerAllFreeAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));

		minotaur.showBuild(cWorker0);

		assertEquals(testArray.size(), minotaur.buildList.size());

		for (int i = 0; i < minotaur.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), minotaur.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), minotaur.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_workerInCorner1WorkerAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(1,1);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));

		minotaur.showBuild(cWorker0);

		assertEquals(testArray.size(), minotaur.buildList.size());

		for (int i = 0; i < minotaur.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), minotaur.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), minotaur.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_workerInCorner1DomeAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,1);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		board.getBlock(new Coordinate(1,1)).setDome();
		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));

		minotaur.showBuild(cWorker0);

		assertEquals(testArray.size(), minotaur.buildList.size());

		for (int i = 0; i < minotaur.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), minotaur.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), minotaur.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_workerInSideAllFreeAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(3,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));

		minotaur.showBuild(cWorker0);

		assertEquals(testArray.size(), minotaur.buildList.size());

		for (int i = 0; i < minotaur.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), minotaur.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), minotaur.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_workerInSide1WorkerAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,1);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(3,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(3,1));

		minotaur.showBuild(cWorker0);

		assertEquals(testArray.size(), minotaur.buildList.size());

		for (int i = 0; i < minotaur.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), minotaur.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), minotaur.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_workerInSide1DomeAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,1);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		board.getBlock(new Coordinate(2,1)).setDome();
		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(3,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(3,1));

		minotaur.showBuild(cWorker0);

		assertEquals(testArray.size(), minotaur.buildList.size());

		for (int i = 0; i < minotaur.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), minotaur.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), minotaur.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void performBuild_CoordinateAtLevel0_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=0;
		board.getBlock(c).setLevel(level);
		minotaur.performBuild(c);
		assertEquals(level+1,board.getBlock(c).getLevel());
	}

	@Test
	public void performBuild_CoordinateAtLevel1_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=1;
		board.getBlock(c).setLevel(level);
		minotaur.performBuild(c);
		assertEquals(level+1,board.getBlock(c).getLevel());
	}

	@Test
	public void performBuild_CoordinateAtLevel2_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=2;
		board.getBlock(c).setLevel(level);
		minotaur.performBuild(c);
		assertEquals(level+1,board.getBlock(c).getLevel());
	}

	@Test
	public void performBuild_CoordinateAtLevel3_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=3;
		board.getBlock(c).setLevel(level);
		minotaur.performBuild(c);
		assertEquals(level+1,board.getBlock(c).getLevel());
		assertTrue(board.getBlock(c).getIsDome());
	}

	@Test
	public void performBuild_CoordinateAtLevel4_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=4;
		board.getBlock(c).setLevel(level);
		minotaur.performBuild(c);
		assertEquals(level,board.getBlock(c).getLevel());
	}

	@Test
	public void getMoveList_correctInput_correctOutput(){
		LightBlock lb1=new LightBlock(new Coordinate(0,0));
		minotaur.moveList.add(lb1);
		assertEquals(0, minotaur.getMoveList().get(0).getC().getX());
		assertEquals(0, minotaur.getMoveList().get(0).getC().getY());
	}

	@Test
	public void getBuildList_correctInput_correctOutput(){
		LightBlock lb1=new LightBlock(new Coordinate(0,0));
		minotaur.buildList.add(lb1);
		assertEquals(0, minotaur.getBuildList().get(0).getC().getX());
		assertEquals(0, minotaur.getBuildList().get(0).getC().getY());
	}

	@Test
	public void getTurn_correctInput_correctOutput(){
		minotaur.turn=1;
		assertEquals(1, minotaur.getTurn());
	}

	@Test
	public void getCurrentPlayer_correctInput_correctOutput(){
		assertEquals(cPlayer, minotaur.getCurrentPlayer());
	}

	@Test
	public void getNextPlayer_correctInput_correctOutput(){
		assertEquals(nPlayer, minotaur.getNextPlayer());
	}

	@Test
	public void getThirdPlayer_correctInput_correctOutput(){
		assertEquals(tPlayer, minotaur.getThirdPlayer());
	}

	@Test
	public void turnHandler_stateIsSettingMovement_shouldBeMoving(){
		minotaur.setState(StateNumber.settingMovement);
		Service s = new Service(StateNumber.settingMovement,"codice","whatshouldido");
		minotaur.turnHandler_real(s);
		assertEquals(StateNumber.moving, minotaur.getState());
	}

	@Test
	public void turnHandler_stateIsMoving_shouldBeBuilding(){
		minotaur.setState(StateNumber.moving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		minotaur.turnHandler_real(s);
		assertEquals(StateNumber.building, minotaur.getState());
	}

	@Test
	public void turnHandler_stateIsSettingBuilding_shouldBeBuilding(){
		minotaur.setState(StateNumber.settingBuilding);
		Service s = new Service(StateNumber.settingBuilding,"codice","whatshouldido");
		minotaur.setMovedWorker(cPlayer.getSpecificWorker(0));
		minotaur.turnHandler_real(s);
		assertEquals(StateNumber.building, minotaur.getState());
	}

	@Test
	public void turnHandler_stateIsBuilding_shouldBeSettingMovement(){
		minotaur.setState(StateNumber.building);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		minotaur.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, minotaur.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyMoving_shouldBeOnlyMoving(){
		minotaur.setState(StateNumber.onlymoving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		minotaur.turnHandler_real(s);
		assertEquals(StateNumber.onlymoving, minotaur.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyBuilding_shouldBeOnlyBuilding(){
		minotaur.setState(StateNumber.onlybuilding);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		minotaur.turnHandler_real(s);
		assertEquals(StateNumber.onlybuilding, minotaur.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyNextTurn_shouldBeSettingMovement(){
		minotaur.setState(StateNumber.onlynextturn);
		Service s = new Service(StateNumber.onlynextturn,"codice","whatshouldido");
		minotaur.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, minotaur.getState());
	}

	@Test
	public void getLevelPrecPosition_correctInput_correctOutput(){
		rules.levelPrecPosition=1;
		assertEquals(1, minotaur.getLevelPrecPosition());
	}

	@Test
	public void setLevelPrecPosition_correctInput_correctOutput(){
		minotaur.setLevelPrecPosition(1);
		assertEquals(1, minotaur.getLevelPrecPosition());
	}

	@Test
	public void getMovedWorker_correctInput_correctOutput(){
		rules.movedWorker=cPlayer.getSpecificWorker(0);
		assertEquals(cPlayer.getSpecificWorker(0), minotaur.getMovedWorker());
	}

	@Test
	public void lose_correctInput_correctOutput(){
		minotaur.lose();

		assertEquals(StateNumber.sendhaslose, minotaur.getState());
		assertEquals(StateNumber.haswon, rules.service.getType());
		assertEquals(0, minotaur.service.getAvailableCells().size());
		assertEquals(0, minotaur.service.getAvailableCells2().size());
	}

	@Test
	public void invertPlayer_correctInput_correctOutput(){
		Player tPlayer=new Player("tplayer",0,null,Color.ANSI_YELLOW);
		rules.thirdPlayer=tPlayer;

		minotaur.invertPlayer();

		assertEquals(Color.ANSI_YELLOW, rules.nextPlayer.getColor());
		assertEquals(Color.ANSI_BLUE, rules.thirdPlayer.getColor());
	}

	@Test
	public void Win_correctInput_correctOutput(){
		minotaur.Win();

		assertEquals(StateNumber.haswon, minotaur.getState());
		assertTrue(minotaur.getHasWon());
		assertEquals(StateNumber.haswon, rules.service.getType());
		assertEquals(0, minotaur.service.getAvailableCells().size());
		assertEquals(0, minotaur.service.getAvailableCells2().size());
	}

	@Test
	public void getStringParticular_correctInput_correctOutput(){
		String string= "testString";
		minotaur.stringParticular=string;
		assertEquals(string,minotaur.getStringParticular());
	}

	@Test
	public void setStringParticular_correctInput_correctOutput(){
		String string= "testString";
		minotaur.setStringParticular(string);
		assertEquals(string,minotaur.getStringParticular());
	}

	@Test
	public void CheckList_notEmptyList_shouldDoNothing(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		testArray.add(new LightBlock(new Coordinate(0,0)));
		minotaur.setIsalive(true);
		minotaur.CheckList(testArray);

		assertTrue(minotaur.getIsalive());
	}

	@Test
	public void CheckList_emptyListAndOtherPlayerIsNotAlive_stateShouldBeHasWon(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		minotaur.setIsalive(true);
		tPlayer.getAbstractRule().setIsalive(false);
		minotaur.CheckList(testArray);


		assertFalse(minotaur.getIsalive());
		assertEquals(StateNumber.haswon, rules.service.getType());
	}

	@Test
	public void CheckList_emptyListAndOtherPlayersAreAlive_stateShouldBeNextTurn(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		minotaur.setIsalive(true);
		minotaur.CheckList(testArray);


		assertFalse(minotaur.getIsalive());
		assertEquals(StateNumber.nextTurn, rules.service.getType());
	}

	@Test
	public void getWorkerToSend_correctInput_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		LightBlock lb0=new LightBlock(cc0);
		LightBlock lb1=new LightBlock(cc1);
		minotaur.workerToSend.add(lb0);
		minotaur.workerToSend.add(lb1);

		ArrayList<LightBlock> testArray = new ArrayList<>();
		testArray.add(lb0);
		testArray.add(lb1);
		ArrayList<LightBlock> wTs=minotaur.getWorkerToSend();
		assertEquals(testArray.size(),wTs.size());

		for (int i = 0; i < wTs.size(); i++) {
			assertEquals(testArray.get(i),wTs.get(i));
		}
	}

	@Test
	public void setWorkerToSend_correctInput_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		LightBlock lb0=new LightBlock(cc0);
		LightBlock lb1=new LightBlock(cc1);

		ArrayList<LightBlock> testArray = new ArrayList<>();
		testArray.add(lb0);
		testArray.add(lb1);

		minotaur.setWorkerToSend(testArray);

		assertEquals(testArray.size(),minotaur.getWorkerToSend().size());

		for (int i = 0; i < minotaur.getWorkerToSend().size(); i++) {
			assertEquals(testArray.get(i),minotaur.getWorkerToSend().get(i));
		}
	}
}