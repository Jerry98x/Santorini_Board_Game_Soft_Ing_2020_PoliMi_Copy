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
public class DemeterTest {
	Demeter demeter =null;
	Rules rules =null;
	Player cPlayer=null;
	Player nPlayer=null;
	Player tPlayer=null;
	Board board=null;

	@Before
	public void setUp(){
		board=new Board();
		cPlayer=new Player("nome",0, GodName.demeter, Color.ANSI_RED);
		nPlayer=new Player("nome",0, GodName.apollo, Color.ANSI_BLUE);
		tPlayer=new Player("nome",0,GodName.apollo,Color.ANSI_PURPLE);
		rules =new Rules(1,board,cPlayer,nPlayer,tPlayer);
		demeter =new Demeter(rules);
		cPlayer.setAbstractRule(demeter);
	}

	@After
	public void tearDown(){
		demeter =null;
	}


	@Test
	public void turnHandler_real_stateIsSettingMovement_shouldBeMoving(){
		demeter.setState(StateNumber.settingMovement);
		Service s = new Service(StateNumber.settingMovement,"codice","whatshouldido");
		demeter.turnHandler_real(s);
		assertEquals(StateNumber.moving, demeter.getState());
	}

	@Test
	public void turnHandler_real_stateIsMoving_shouldBeBuilding(){
		demeter.setState(StateNumber.moving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		demeter.turnHandler_real(s);
		assertEquals(StateNumber.building, demeter.getState());
	}

	@Test
	public void turnHandler_real_stateIsSettingBuilding_shouldBeBuilding(){
		demeter.setState(StateNumber.settingBuilding);
		Service s = new Service(StateNumber.settingBuilding,"codice","whatshouldido");
		demeter.setMovedWorker(cPlayer.getSpecificWorker(0));
		demeter.turnHandler_real(s);
		assertEquals(StateNumber.building, demeter.getState());
	}

	@Test
	public void turnHandler_real_stateIsBuildingAndNumOfBuildIs0_shouldBeBuilding(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		demeter.setMovedWorker(cWorker0);
		demeter.setState(StateNumber.building);
		demeter.numOfBuild=0;
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		demeter.turnHandler_real(s);
		assertEquals(StateNumber.building, demeter.getState());
		assertEquals(1, demeter.numOfBuild);
	}

	@Test
	public void turnHandler_real_stateIsBuildingAndNumOfBuildIs1_shouldBeSettingMovement(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		demeter.setMovedWorker(cWorker0);
		demeter.movedWorkerC=cc0;
		demeter.setState(StateNumber.building);
		demeter.numOfBuild=1;
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		demeter.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, demeter.getState());
		assertEquals(0, demeter.numOfBuild);
		assertEquals(StateNumber.setgod,s.getType());
	}

	@Test
	public void turnHandler_real_stateIsOnlyMoving_shouldBeOnlyMoving(){
		demeter.setState(StateNumber.onlymoving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		demeter.turnHandler_real(s);
		assertEquals(StateNumber.onlymoving, demeter.getState());
	}

	@Test
	public void turnHandler_real_stateIsOnlyBuilding_shouldBeOnlyBuilding(){
		demeter.setState(StateNumber.onlybuilding);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		demeter.turnHandler_real(s);
		assertEquals(StateNumber.onlybuilding, demeter.getState());
	}

	@Test
	public void turnHandler_real_stateIsOnlyNextTurn_shouldBeSettingMovement(){
		demeter.setState(StateNumber.onlynextturn);
		Service s = new Service(StateNumber.onlynextturn,"codice","whatshouldido");
		demeter.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, demeter.getState());
	}

	@Test
	public void showMove_workerInBoardCorner_correctOutput(){
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

		demeter.showMoves(cWorker0);

		assertEquals(testArray.size(), demeter.moveList.size());

		for (int i = 0; i < demeter.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.moveList.get(i).getC().getY());
		}
	}

	@Test
	public void showMove_workerInBoardSide_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(3,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));

		demeter.showMoves(cWorker0);

		assertEquals(testArray.size(), demeter.moveList.size());

		for (int i = 0; i < demeter.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.moveList.get(i).getC().getY());
		}
	}

	@Test
	public void showMove_workerInBoardCenter_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,2);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(0,0);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(1,2));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));
		testArray.add(new Coordinate(3,3));

		demeter.showMoves(cWorker0);

		assertEquals(testArray.size(), demeter.moveList.size());

		for (int i = 0; i < demeter.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.moveList.get(i).getC().getY());
		}
	}

	@Test
	public void showMove_workerInBoardCenter1LevelTooHigh_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,2);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(0,0);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		board.getBlock(cc0).setLevel(0);
		board.getBlock(new Coordinate(1,2)).setLevel(3);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));
		testArray.add(new Coordinate(3,3));

		demeter.showMoves(cWorker0);

		assertEquals(testArray.size(), demeter.moveList.size());

		for (int i = 0; i < demeter.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.moveList.get(i).getC().getY());
		}
	}

	@Test
	public void showMove_workerInBoardCenterWorkerAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,2);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(0,0);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(3,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(1,2));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));

		demeter.showMoves(cWorker0);

		assertEquals(testArray.size(), demeter.moveList.size());

		for (int i = 0; i < demeter.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.moveList.get(i).getC().getY());
		}
	}

	@Test
	public void showMove_workerInBoardCenter2WorkersAround_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,2);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(0,0);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(1,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(3,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));

		demeter.showMoves(cWorker0);

		assertEquals(testArray.size(), demeter.moveList.size());

		for (int i = 0; i < demeter.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.moveList.get(i).getC().getY());
		}
	}

	@Test
	public void showMove_workerInBoardCenter1BlockAroundIsDome_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,2);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(0,0);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		board.getBlock(new Coordinate(3,3)).setDome();

		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(1,2));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));

		demeter.showMoves(cWorker0);

		assertEquals(testArray.size(), demeter.moveList.size());

		for (int i = 0; i < demeter.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.moveList.get(i).getC().getY());
		}
	}

	@Test
	public void setMove_worker1InBoardCornerWorker2InOppositeCorner_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
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

		demeter.setMoves();

		assertEquals(testArray.size(), demeter.moveList.size());

		for (int i = 0; i < demeter.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.moveList.get(i).getC().getY());
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

		demeter.setMoves();

		assertEquals(testArray.size(), demeter.moveList.size());

		for (int i = 0; i < demeter.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.moveList.get(i).getC().getY());
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

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(3,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(3,3));
		testArray.add(new Coordinate(4,3));
		testArray.add(new Coordinate(3,4));

		demeter.setMoves();

		assertEquals(testArray.size(), demeter.moveList.size());

		for (int i = 0; i < demeter.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.moveList.get(i).getC().getY());
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

		demeter.setMoves();

		assertEquals(testArray.size(), demeter.moveList.size());

		for (int i = 0; i < demeter.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.moveList.get(i).getC().getY());
		}
	}

	@Test
	public void performMove_acceptableFinalCoordinate_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		Coordinate c = new Coordinate(1,0);
		demeter.performMove(cWorker0,c,0);
		assertEquals(c.getX(),cWorker0.getCoordinate().getX());
		assertEquals(c.getY(),cWorker0.getCoordinate().getY());
		assertTrue(board.getBlock(c).getOccupied());
		assertFalse(board.getBlock(cc0).getOccupied());
		assertEquals(0, demeter.getMoveList().size());
	}

	@Test
	public void setBuild_workerInCornerNumOfBuildIs0_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		demeter.numOfBuild=0;
		demeter.setMovedWorker(cWorker0);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));

		demeter.setBuild();
		assertEquals(testArray.size(), demeter.buildList.size());

		for (int i = 0; i < demeter.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void setBuild_sideWorkerPositionNumOfBuildIs0_CorrectOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(2, 0);cWorker0.setCoordinate(cc0);board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate cc1 = new Coordinate(3, 3);cWorker1.setCoordinate(cc1);board.addWorker(cc1, 1, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(4, 4);nWorker0.setCoordinate(nc0);board.addWorker(nc0, 0, Color.ANSI_BLUE);
		Coordinate nc1 = new Coordinate(4, 3);nWorker1.setCoordinate(nc1);board.addWorker(nc1, 1, Color.ANSI_BLUE);
		demeter.numOfBuild=0;
		demeter.setMovedWorker(cWorker0);
		ArrayList<Coordinate> testArray = new ArrayList<>();

		testArray.add(new Coordinate(1, 0));
		testArray.add(new Coordinate(3, 0));
		testArray.add(new Coordinate(1, 1));
		testArray.add(new Coordinate(2, 1));
		testArray.add(new Coordinate(3, 1));
		demeter.setBuild();
		assertEquals(testArray.size(), demeter.buildList.size());

		for (int i = 0; i < demeter.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void setBuild_centerWorkerPositionNumOfBuildIs0_CorrectOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(2, 2);cWorker0.setCoordinate(cc0);board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate cc1 = new Coordinate(0, 0);cWorker1.setCoordinate(cc1);board.addWorker(cc1, 1, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(4, 4);nWorker0.setCoordinate(nc0);board.addWorker(nc0, 0, Color.ANSI_BLUE);
		Coordinate nc1 = new Coordinate(4, 3);nWorker1.setCoordinate(nc1);board.addWorker(nc1, 1, Color.ANSI_BLUE);
		demeter.numOfBuild=0;
		demeter.setMovedWorker(cWorker0);
		ArrayList<Coordinate> testArray = new ArrayList<>();

		testArray.add(new Coordinate(1, 1));
		testArray.add(new Coordinate(2, 1));
		testArray.add(new Coordinate(3, 1));
		testArray.add(new Coordinate(1, 2));
		testArray.add(new Coordinate(3, 2));
		testArray.add(new Coordinate(1, 3));
		testArray.add(new Coordinate(2, 3));
		testArray.add(new Coordinate(3, 3));
		demeter.setBuild();
		assertEquals(testArray.size(), demeter.buildList.size());

		for (int i = 0; i < demeter.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void setBuild_workerInCornerNumOfBuildIs1_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		demeter.numOfBuild=1;
		demeter.firstBuildC=new Coordinate(1,0);
		demeter.setMovedWorker(cWorker0);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(0,0));

		demeter.setBuild();
		assertEquals(testArray.size(), demeter.buildList.size());

		for (int i = 0; i < demeter.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.buildList.get(i).getC().getY());
		}
		assertEquals(StateNumber.moveState, demeter.getState());
	}

	@Test
	public void setBuild_sideWorkerPositionNumOfBuildIs1_CorrectOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(2, 0);cWorker0.setCoordinate(cc0);board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate cc1 = new Coordinate(3, 3);cWorker1.setCoordinate(cc1);board.addWorker(cc1, 1, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(4, 4);nWorker0.setCoordinate(nc0);board.addWorker(nc0, 0, Color.ANSI_BLUE);
		Coordinate nc1 = new Coordinate(4, 3);nWorker1.setCoordinate(nc1);board.addWorker(nc1, 1, Color.ANSI_BLUE);

		demeter.numOfBuild=1;
		demeter.firstBuildC=new Coordinate(3,0);
		demeter.setMovedWorker(cWorker0);
		ArrayList<Coordinate> testArray = new ArrayList<>();

		testArray.add(new Coordinate(1, 0));
		testArray.add(new Coordinate(1, 1));
		testArray.add(new Coordinate(2, 1));
		testArray.add(new Coordinate(3, 1));
		testArray.add(new Coordinate(2, 0));
		demeter.setBuild();
		assertEquals(testArray.size(), demeter.buildList.size());

		for (int i = 0; i < demeter.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.buildList.get(i).getC().getY());
		}
		assertEquals(StateNumber.moveState, demeter.getState());
	}

	@Test
	public void setBuild_centerWorkerPositionNumOfBuildIs1_CorrectOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(2, 2);cWorker0.setCoordinate(cc0);board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate cc1 = new Coordinate(0, 0);cWorker1.setCoordinate(cc1);board.addWorker(cc1, 1, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(4, 4);nWorker0.setCoordinate(nc0);board.addWorker(nc0, 0, Color.ANSI_BLUE);
		Coordinate nc1 = new Coordinate(4, 3);nWorker1.setCoordinate(nc1);board.addWorker(nc1, 1, Color.ANSI_BLUE);
		demeter.numOfBuild=1;
		demeter.firstBuildC=new Coordinate(2,3);
		demeter.setMovedWorker(cWorker0);
		ArrayList<Coordinate> testArray = new ArrayList<>();

		testArray.add(new Coordinate(1, 1));
		testArray.add(new Coordinate(2, 1));
		testArray.add(new Coordinate(3, 1));
		testArray.add(new Coordinate(1, 2));
		testArray.add(new Coordinate(3, 2));
		testArray.add(new Coordinate(1, 3));
		testArray.add(new Coordinate(3, 3));
		testArray.add(new Coordinate(2,2));
		demeter.setBuild();
		assertEquals(testArray.size(), demeter.buildList.size());

		for (int i = 0; i < demeter.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.buildList.get(i).getC().getY());
		}
		assertEquals(StateNumber.moveState, demeter.getState());
	}

	@Test
	public void showBuild_workerInCornerNumOfBuildIs0_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		demeter.numOfBuild=0;
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));

		demeter.showBuild(cWorker0);
		assertEquals(testArray.size(), demeter.buildList.size());

		for (int i = 0; i < demeter.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_sideWorkerPositionNumOfBuildIs0_CorrectOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(2, 0);cWorker0.setCoordinate(cc0);board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate cc1 = new Coordinate(3, 3);cWorker1.setCoordinate(cc1);board.addWorker(cc1, 1, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(4, 4);nWorker0.setCoordinate(nc0);board.addWorker(nc0, 0, Color.ANSI_BLUE);
		Coordinate nc1 = new Coordinate(4, 3);nWorker1.setCoordinate(nc1);board.addWorker(nc1, 1, Color.ANSI_BLUE);
		demeter.numOfBuild=0;
		ArrayList<Coordinate> testArray = new ArrayList<>();

		testArray.add(new Coordinate(1, 0));
		testArray.add(new Coordinate(3, 0));
		testArray.add(new Coordinate(1, 1));
		testArray.add(new Coordinate(2, 1));
		testArray.add(new Coordinate(3, 1));
		demeter.showBuild(cWorker0);
		assertEquals(testArray.size(), demeter.buildList.size());

		for (int i = 0; i < demeter.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_centerWorkerPositionNumOfBuildIs0_CorrectOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(2, 2);cWorker0.setCoordinate(cc0);board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate cc1 = new Coordinate(0, 0);cWorker1.setCoordinate(cc1);board.addWorker(cc1, 1, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(4, 4);nWorker0.setCoordinate(nc0);board.addWorker(nc0, 0, Color.ANSI_BLUE);
		Coordinate nc1 = new Coordinate(4, 3);nWorker1.setCoordinate(nc1);board.addWorker(nc1, 1, Color.ANSI_BLUE);
		demeter.numOfBuild=0;
		ArrayList<Coordinate> testArray = new ArrayList<>();

		testArray.add(new Coordinate(1, 1));
		testArray.add(new Coordinate(2, 1));
		testArray.add(new Coordinate(3, 1));
		testArray.add(new Coordinate(1, 2));
		testArray.add(new Coordinate(3, 2));
		testArray.add(new Coordinate(1, 3));
		testArray.add(new Coordinate(2, 3));
		testArray.add(new Coordinate(3, 3));
		demeter.showBuild(cWorker0);
		assertEquals(testArray.size(), demeter.buildList.size());

		for (int i = 0; i < demeter.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_workerInCornerNumOfBuildIs1_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		demeter.numOfBuild=1;
		demeter.firstBuildC=new Coordinate(1,0);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(0,0));

		demeter.showBuild(cWorker0);
		assertEquals(testArray.size(), demeter.buildList.size());

		for (int i = 0; i < demeter.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_sideWorkerPositionNumOfBuildIs1_CorrectOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(2, 0);cWorker0.setCoordinate(cc0);board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate cc1 = new Coordinate(3, 3);cWorker1.setCoordinate(cc1);board.addWorker(cc1, 1, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(4, 4);nWorker0.setCoordinate(nc0);board.addWorker(nc0, 0, Color.ANSI_BLUE);
		Coordinate nc1 = new Coordinate(4, 3);nWorker1.setCoordinate(nc1);board.addWorker(nc1, 1, Color.ANSI_BLUE);

		demeter.numOfBuild=1;
		demeter.firstBuildC=new Coordinate(3,0);
		ArrayList<Coordinate> testArray = new ArrayList<>();

		testArray.add(new Coordinate(1, 0));
		testArray.add(new Coordinate(1, 1));
		testArray.add(new Coordinate(2, 1));
		testArray.add(new Coordinate(3, 1));
		testArray.add(new Coordinate(2, 0));
		demeter.showBuild(cWorker0);
		assertEquals(testArray.size(), demeter.buildList.size());

		for (int i = 0; i < demeter.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_centerWorkerPositionNumOfBuildIs1_CorrectOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(2, 2);cWorker0.setCoordinate(cc0);board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate cc1 = new Coordinate(0, 0);cWorker1.setCoordinate(cc1);board.addWorker(cc1, 1, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(4, 4);nWorker0.setCoordinate(nc0);board.addWorker(nc0, 0, Color.ANSI_BLUE);
		Coordinate nc1 = new Coordinate(4, 3);nWorker1.setCoordinate(nc1);board.addWorker(nc1, 1, Color.ANSI_BLUE);
		demeter.numOfBuild=1;
		demeter.firstBuildC=new Coordinate(2,3);
		ArrayList<Coordinate> testArray = new ArrayList<>();

		testArray.add(new Coordinate(1, 1));
		testArray.add(new Coordinate(2, 1));
		testArray.add(new Coordinate(3, 1));
		testArray.add(new Coordinate(1, 2));
		testArray.add(new Coordinate(3, 2));
		testArray.add(new Coordinate(1, 3));
		testArray.add(new Coordinate(3, 3));
		testArray.add(new Coordinate(2,2));
		demeter.showBuild(cWorker0);
		assertEquals(testArray.size(), demeter.buildList.size());

		for (int i = 0; i < demeter.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), demeter.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), demeter.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void performBuild_CoordinatesAtLevel0NumOfBuildIs0_correctOutput(){
		demeter.numOfBuild=0;
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(0);
		int l=board.getBlock(c).getLevel();
		demeter.performBuild(c);
		assertEquals(l+1,board.getBlock(c).getLevel());
		assertEquals(c.getX(), demeter.firstBuildC.getX());
		assertEquals(c.getY(), demeter.firstBuildC.getY());
	}

	@Test
	public void performBuild_CoordinatesAtLevel1NumOfBuildIs0_correctOutput(){
		demeter.numOfBuild=0;
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(1);
		int l=board.getBlock(c).getLevel();
		demeter.performBuild(c);
		assertEquals(l+1,board.getBlock(c).getLevel());
		assertEquals(c.getX(), demeter.firstBuildC.getX());
		assertEquals(c.getY(), demeter.firstBuildC.getY());
	}

	@Test
	public void performBuild_CoordinatesAtLevel2NumOfBuildIs0_correctOutput(){
		demeter.numOfBuild=0;
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(2);
		int l=board.getBlock(c).getLevel();
		demeter.performBuild(c);
		assertEquals(l+1,board.getBlock(c).getLevel());
		assertEquals(c.getX(), demeter.firstBuildC.getX());
		assertEquals(c.getY(), demeter.firstBuildC.getY());
	}

	@Test
	public void performBuild_CoordinatesAtLevel3NumOfBuildIs0_correctOutput(){
		demeter.numOfBuild=0;
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(3);
		int l=board.getBlock(c).getLevel();
		demeter.performBuild(c);
		assertEquals(l+1,board.getBlock(c).getLevel());
		assertTrue(board.getBlock(c).getIsDome());
		assertEquals(c.getX(), demeter.firstBuildC.getX());
		assertEquals(c.getY(), demeter.firstBuildC.getY());
	}

	@Test
	public void performBuild_CoordinatesAtLevel4NumOfBuildIs0_correctOutput(){
		demeter.numOfBuild=0;
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(4);
		int l=board.getBlock(c).getLevel();
		demeter.performBuild(c);
		assertEquals(l,board.getBlock(c).getLevel());
		assertEquals(c.getX(), demeter.firstBuildC.getX());
		assertEquals(c.getY(), demeter.firstBuildC.getY());
	}

	@Test
	public void performBuild_NumOfBuildIs1CoordinatesOfFirstBuild_correctOutput(){
		demeter.numOfBuild=1;
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Coordinate cc0=new Coordinate(1,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		demeter.movedWorkerC=cc0;
		Coordinate c=new Coordinate(1,0);
		demeter.performBuild(c);
		assertEquals(StateNumber.onlynextturn, demeter.getState());
	}

	@Test
	public void performBuild_CoordinatesAtLevel0NumOfBuildIs1NotCoordinatesOfFirstBuild_correctOutput(){
		demeter.numOfBuild=1;
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		demeter.movedWorkerC=cc0;
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(0);
		int l=board.getBlock(c).getLevel();
		demeter.performBuild(c);
		assertEquals(l+1,board.getBlock(c).getLevel());
	}

	@Test
	public void performBuild_CoordinatesAtLevel1NumOfBuildIs1NotCoordinatesOfFirstBuild_correctOutput(){
		demeter.numOfBuild=1;
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		demeter.movedWorkerC=cc0;
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(1);
		int l=board.getBlock(c).getLevel();
		demeter.performBuild(c);
		assertEquals(l+1,board.getBlock(c).getLevel());
	}

	@Test
	public void performBuild_CoordinatesAtLevel2NumOfBuildIs1NotCoordinatesOfFirstBuild_correctOutput(){
		demeter.numOfBuild=1;
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		demeter.movedWorkerC=cc0;
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(2);
		int l=board.getBlock(c).getLevel();
		demeter.performBuild(c);
		assertEquals(l+1,board.getBlock(c).getLevel());
	}

	@Test
	public void performBuild_CoordinatesAtLevel3NumOfBuildIs1NotCoordinatesOfFirstBuild_correctOutput(){
		demeter.numOfBuild=1;
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		demeter.movedWorkerC=cc0;
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(3);
		int l=board.getBlock(c).getLevel();
		demeter.performBuild(c);
		assertEquals(l+1,board.getBlock(c).getLevel());
		assertTrue(board.getBlock(c).getIsDome());
	}

	@Test
	public void performBuild_CoordinatesAtLevel4NumOfBuildIs1NotCoordinatesOfFirstBuild_correctOutput(){
		demeter.numOfBuild=1;
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		demeter.movedWorkerC=cc0;
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(4);
		int l=board.getBlock(c).getLevel();
		demeter.performBuild(c);
		assertEquals(l,board.getBlock(c).getLevel());
	}

	@Test
	public void nextTurn_correctInput_shouldSetToNextTurn(){
		demeter.nextTurn();
		assertEquals(StateNumber.settingMovement, demeter.getState());
		assertEquals(StateNumber.nextTurn, rules.service.getType());
		assertEquals(0, demeter.service.getAvailableCells().size());
		assertEquals(0, demeter.service.getAvailableCells2().size());
	}

	@Test
	public void checkIfWin_endMoveAtLevel0_shouldNotWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0, Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		demeter.setHasWon(false);
		board.getBlock(cc0).setLevel(0);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(0);
		demeter.showMoves(cWorker0);
		demeter.performMove(cWorker0,c,0);
		demeter.CheckIfWin();

		assertFalse(demeter.getHasWon());
	}

	@Test
	public void checkIfWin_endMoveAtLevel1_shouldNotWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		demeter.setHasWon(false);
		board.getBlock(cc0).setLevel(0);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(1);
		demeter.showMoves(cWorker0);
		demeter.performMove(cWorker0,c,0);
		demeter.CheckIfWin();

		assertFalse(demeter.getHasWon());
	}

	@Test
	public void checkIfWin_endMoveAtLevel2_shouldNotWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		demeter.setHasWon(false);
		board.getBlock(cc0).setLevel(1);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(2);
		demeter.showMoves(cWorker0);
		demeter.performMove(cWorker0,c,0);
		demeter.CheckIfWin();

		assertFalse(demeter.getHasWon());
	}

	@Test
	public void checkIfWin_startAndEndMoveAtLevel3_shouldNotWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		demeter.setHasWon(false);
		board.getBlock(cc0).setLevel(3);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(3);
		demeter.showMoves(cWorker0);
		demeter.performMove(cWorker0,c,0);
		demeter.CheckIfWin();

		assertFalse(demeter.getHasWon());
	}

	@Test
	public void checkIfWin_startMoveAtLevel2AndEndAtLevel3_shouldNotWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		demeter.setHasWon(false);
		board.getBlock(cc0).setLevel(2);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(3);
		demeter.showMoves(cWorker0);
		demeter.performMove(cWorker0,c,0);
		demeter.CheckIfWin();

		assertTrue(demeter.getHasWon());
		assertEquals(StateNumber.haswon, rules.service.getType());
		assertEquals(0, demeter.service.getAvailableCells().size());
		assertEquals(0, demeter.service.getAvailableCells2().size());
	}

	@Test
	public void lose_correctInput_correctOutput(){
		demeter.lose();

		assertEquals(StateNumber.sendhaslose, demeter.getState());
		assertEquals(StateNumber.haswon, rules.service.getType());
		assertEquals(0, demeter.service.getAvailableCells().size());
		assertEquals(0, demeter.service.getAvailableCells2().size());
	}

	@Test
	public void invertPlayer_correctInput_correctOutput(){
		Player tPlayer=new Player("tplayer",0,null,Color.ANSI_YELLOW);
		rules.thirdPlayer=tPlayer;

		demeter.invertPlayer();

		assertEquals(Color.ANSI_YELLOW, rules.nextPlayer.getColor());
		assertEquals(Color.ANSI_BLUE, rules.thirdPlayer.getColor());
	}

	@Test
	public void CheckList_notEmptyList_shouldDoNothing(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		testArray.add(new LightBlock(new Coordinate(0,0)));
		demeter.setIsalive(true);
		demeter.CheckList(testArray);

		assertTrue(demeter.getIsalive());
	}

	@Test
	public void CheckList_emptyListAndOtherPlayerIsNotAlive_stateShouldBeHasWon(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		demeter.setIsalive(true);
		tPlayer.getAbstractRule().setIsalive(false);
		demeter.CheckList(testArray);


		assertFalse(demeter.getIsalive());
		assertEquals(StateNumber.haswon, rules.service.getType());
	}

	@Test
	public void CheckList_emptyListAndOtherPlayersAreAlive_stateShouldBeNextTurn(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		demeter.setIsalive(true);
		demeter.CheckList(testArray);


		assertFalse(demeter.getIsalive());
		assertEquals(StateNumber.nextTurn, rules.service.getType());
	}

	@Test
	public void Win_correctInput_correctOutput(){
		demeter.Win();

		assertEquals(StateNumber.haswon, demeter.getState());
		assertTrue(demeter.getHasWon());
		assertEquals(StateNumber.haswon, rules.service.getType());
		assertEquals(0, demeter.service.getAvailableCells().size());
		assertEquals(0, demeter.service.getAvailableCells2().size());
	}

	@Test
	public void getMoveList_correctInput_correctOutput(){
		LightBlock lb1=new LightBlock(new Coordinate(0,0));
		demeter.moveList.add(lb1);
		assertEquals(0, demeter.getMoveList().get(0).getC().getX());
		assertEquals(0, demeter.getMoveList().get(0).getC().getY());
	}

	@Test
	public void getBuildList_correctInput_correctOutput(){
		LightBlock lb1=new LightBlock(new Coordinate(0,0));
		demeter.buildList.add(lb1);
		assertEquals(0, demeter.getBuildList().get(0).getC().getX());
		assertEquals(0, demeter.getBuildList().get(0).getC().getY());
	}

	@Test
	public void getTurn_correctInput_correctOutput(){
		demeter.turn=1;
		assertEquals(1, demeter.getTurn());
	}

	@Test
	public void getCurrentPlayer_correctInput_correctOutput(){
		assertEquals(cPlayer, demeter.getCurrentPlayer());
	}

	@Test
	public void getNextPlayer_correctInput_correctOutput(){
		assertEquals(nPlayer, demeter.getNextPlayer());
	}

	@Test
	public void getThirdPlayer_correctInput_correctOutput(){
		assertEquals(tPlayer, demeter.getThirdPlayer());
	}

	@Test
	public void turnHandler_stateIsSettingMovement_shouldBeMoving(){
		demeter.setState(StateNumber.settingMovement);
		Service s = new Service(StateNumber.settingMovement,"codice","whatshouldido");
		demeter.turnHandler_real(s);
		assertEquals(StateNumber.moving, demeter.getState());
	}

	@Test
	public void turnHandler_stateIsMoving_shouldBeBuilding(){
		demeter.setState(StateNumber.moving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		demeter.turnHandler_real(s);
		assertEquals(StateNumber.building, demeter.getState());
	}

	@Test
	public void turnHandler_stateIsSettingBuilding_shouldBeBuilding(){
		demeter.setState(StateNumber.settingBuilding);
		Service s = new Service(StateNumber.settingBuilding,"codice","whatshouldido");
		demeter.setMovedWorker(cPlayer.getSpecificWorker(0));
		demeter.turnHandler_real(s);
		assertEquals(StateNumber.building, demeter.getState());
	}


	@Test
	public void turnHandler_stateIsOnlyMoving_shouldBeOnlyMoving(){
		demeter.setState(StateNumber.onlymoving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		demeter.turnHandler_real(s);
		assertEquals(StateNumber.onlymoving, demeter.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyBuilding_shouldBeOnlyBuilding(){
		demeter.setState(StateNumber.onlybuilding);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		demeter.turnHandler_real(s);
		assertEquals(StateNumber.onlybuilding, demeter.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyNextTurn_shouldBeSettingMovement(){
		demeter.setState(StateNumber.onlynextturn);
		Service s = new Service(StateNumber.onlynextturn,"codice","whatshouldido");
		demeter.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, demeter.getState());
	}

	@Test
	public void getLevelPrecPosition_correctInput_correctOutput(){
		rules.levelPrecPosition=1;
		assertEquals(1, demeter.getLevelPrecPosition());
	}

	@Test
	public void setLevelPrecPosition_correctInput_correctOutput(){
		demeter.setLevelPrecPosition(1);
		assertEquals(1, demeter.getLevelPrecPosition());
	}

	@Test
	public void getMovedWorker_correctInput_correctOutput(){
		rules.movedWorker=cPlayer.getSpecificWorker(0);
		assertEquals(cPlayer.getSpecificWorker(0), demeter.getMovedWorker());
	}

	@Test
	public void getStringParticular_correctInput_correctOutput(){
		String string= "testString";
		demeter.stringParticular=string;
		assertEquals(string,demeter.getStringParticular());
	}

	@Test
	public void setStringParticular_correctInput_correctOutput(){
		String string= "testString";
		demeter.setStringParticular(string);
		assertEquals(string,demeter.getStringParticular());
	}

	@Test
	public void getWorkerToSend_correctInput_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		LightBlock lb0=new LightBlock(cc0);
		LightBlock lb1=new LightBlock(cc1);
		demeter.workerToSend.add(lb0);
		demeter.workerToSend.add(lb1);

		ArrayList<LightBlock> testArray = new ArrayList<>();
		testArray.add(lb0);
		testArray.add(lb1);
		ArrayList<LightBlock> wTs=demeter.getWorkerToSend();
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

		demeter.setWorkerToSend(testArray);

		assertEquals(testArray.size(),demeter.getWorkerToSend().size());

		for (int i = 0; i < demeter.getWorkerToSend().size(); i++) {
			assertEquals(testArray.get(i),demeter.getWorkerToSend().get(i));
		}
	}
}