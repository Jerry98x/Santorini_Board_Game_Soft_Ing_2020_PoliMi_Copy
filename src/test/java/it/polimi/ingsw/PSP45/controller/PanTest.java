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
public class PanTest {

	Pan pan=null;
	Rules rules =null;
	Player cPlayer=null;
	Player nPlayer=null;
	Player tPlayer=null;
	Board board=null;

	@Before
	public void setUp(){
		board=new Board();
		cPlayer=new Player("nome",0, GodName.pan, Color.ANSI_RED);
		nPlayer=new Player("nome",0, GodName.minotaur, Color.ANSI_BLUE);
		tPlayer=new Player("nome",0,GodName.apollo,Color.ANSI_PURPLE);
		rules =new Rules(1,board,cPlayer,nPlayer,tPlayer);
		pan=new Pan(rules);
		cPlayer.setAbstractRule(pan);
	}

	@After
	public void tearDown(){
		pan=null;
	}

	@Test
	public void turnHandler_real_stateIsSettingMovement_shouldBeMoving(){
		pan.setState(StateNumber.settingMovement);
		Service s = new Service(StateNumber.settingMovement,"codice","whatshouldido");
		pan.turnHandler_real(s);
		assertEquals(StateNumber.moving, pan.getState());
	}

	@Test
	public void turnHandler_real_stateIsMoving_shouldBeBuilding(){
		pan.setState(StateNumber.moving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		pan.turnHandler_real(s);
		assertEquals(StateNumber.building, pan.getState());
	}

	@Test
	public void turnHandler_real_stateIsSettingBuilding_shouldBeBuilding(){
		pan.setState(StateNumber.settingBuilding);
		Service s = new Service(StateNumber.settingBuilding,"codice","whatshouldido");
		pan.setMovedWorker(cPlayer.getSpecificWorker(0));
		pan.turnHandler_real(s);
		assertEquals(StateNumber.building, pan.getState());
	}

	@Test
	public void turnHandler_real_stateIsBuilding_shouldBeSettingMovement(){
		pan.setState(StateNumber.building);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		pan.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, pan.getState());
	}

	@Test
	public void turnHandler_real_stateIsOnlyMoving_shouldBeOnlyMoving(){
		pan.setState(StateNumber.onlymoving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		pan.turnHandler_real(s);
		assertEquals(StateNumber.onlymoving, pan.getState());
	}

	@Test
	public void turnHandler_real_stateIsOnlyBuilding_shouldBeOnlyBuilding(){
		pan.setState(StateNumber.onlybuilding);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		pan.turnHandler_real(s);
		assertEquals(StateNumber.onlybuilding, pan.getState());
	}

	@Test
	public void turnHandler_real_stateIsOnlyNextTurn_shouldBeSettingMovement(){
		pan.setState(StateNumber.onlynextturn);
		Service s = new Service(StateNumber.onlynextturn,"codice","whatshouldido");
		pan.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, pan.getState());
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

		pan.showMoves(cWorker0);

		assertEquals(testArray.size(), pan.moveList.size());

		for (int i = 0; i < pan.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.moveList.get(i).getC().getY());
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

		pan.showMoves(cWorker0);

		assertEquals(testArray.size(), pan.moveList.size());

		for (int i = 0; i < pan.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.moveList.get(i).getC().getY());
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

		pan.showMoves(cWorker0);

		assertEquals(testArray.size(), pan.moveList.size());

		for (int i = 0; i < pan.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.moveList.get(i).getC().getY());
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

		pan.showMoves(cWorker0);

		assertEquals(testArray.size(), pan.moveList.size());

		for (int i = 0; i < pan.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.moveList.get(i).getC().getY());
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

		pan.showMoves(cWorker0);

		assertEquals(testArray.size(), pan.moveList.size());

		for (int i = 0; i < pan.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.moveList.get(i).getC().getY());
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

		pan.showMoves(cWorker0);

		assertEquals(testArray.size(), pan.moveList.size());

		for (int i = 0; i < pan.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.moveList.get(i).getC().getY());
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

		pan.showMoves(cWorker0);

		assertEquals(testArray.size(), pan.moveList.size());

		for (int i = 0; i < pan.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.moveList.get(i).getC().getY());
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

		pan.setMoves();

		assertEquals(testArray.size(), pan.moveList.size());

		for (int i = 0; i < pan.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.moveList.get(i).getC().getY());
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

		pan.setMoves();

		assertEquals(testArray.size(), pan.moveList.size());

		for (int i = 0; i < pan.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.moveList.get(i).getC().getY());
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

		pan.setMoves();

		assertEquals(testArray.size(), pan.moveList.size());

		for (int i = 0; i < pan.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.moveList.get(i).getC().getY());
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

		pan.setMoves();

		assertEquals(testArray.size(), pan.moveList.size());

		for (int i = 0; i < pan.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.moveList.get(i).getC().getY());
		}
	}

	@Test
	public void performMove_goesFromLevel0to0_shouldNotWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		board.getBlock(cc0).setLevel(0);

		Coordinate coordinate=new Coordinate(1,0);
		board.getBlock(coordinate).setLevel(0);

		pan.performMove(cWorker0,coordinate,0);
		assertFalse(pan.getHasWon());
	}

	@Test
	public void performMove_goesFromLevel1to0_shouldNotWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		board.getBlock(cc0).setLevel(1);

		Coordinate coordinate=new Coordinate(1,0);
		board.getBlock(coordinate).setLevel(0);

		pan.performMove(cWorker0,coordinate,0);
		assertFalse(pan.getHasWon());
	}

	@Test
	public void performMove_goesFromLevel2to0_shouldWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		board.getBlock(cc0).setLevel(2);

		Coordinate coordinate=new Coordinate(1,0);
		board.getBlock(coordinate).setLevel(0);

		pan.performMove(cWorker0,coordinate,0);
		assertTrue(pan.getHasWon());
	}

	@Test
	public void performMove_goesFromLevel3to0_shouldWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		board.getBlock(cc0).setLevel(3);

		Coordinate coordinate=new Coordinate(1,0);
		board.getBlock(coordinate).setLevel(0);

		pan.performMove(cWorker0,coordinate,0);
		assertTrue(pan.getHasWon());
	}

	@Test
	public void performMove_goesFromLevel4to0_shouldWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		board.getBlock(cc0).setLevel(4);

		Coordinate coordinate=new Coordinate(1,0);
		board.getBlock(coordinate).setLevel(0);

		pan.performMove(cWorker0,coordinate,0);
		assertTrue(pan.getHasWon());
	}

	@Test
	public void performMove_goesFromLevel1to1_shouldNotWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		board.getBlock(cc0).setLevel(1);

		Coordinate coordinate=new Coordinate(1,0);
		board.getBlock(coordinate).setLevel(1);

		pan.performMove(cWorker0,coordinate,0);
		assertFalse(pan.getHasWon());
	}

	@Test
	public void performMove_goesFromLevel2to2_shouldNotWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		board.getBlock(cc0).setLevel(2);

		Coordinate coordinate=new Coordinate(1,0);
		board.getBlock(coordinate).setLevel(2);

		pan.performMove(cWorker0,coordinate,0);
		assertFalse(pan.getHasWon());
	}

	@Test
	public void performMove_goesFromLevel3to3_shouldNotWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		board.getBlock(cc0).setLevel(3);

		Coordinate coordinate=new Coordinate(1,0);
		board.getBlock(coordinate).setLevel(3);

		pan.performMove(cWorker0,coordinate,0);
		assertFalse(pan.getHasWon());
	}

	@Test
	public void performMove_goesFromLevel4to4_shouldNotWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		board.getBlock(cc0).setLevel(4);

		Coordinate coordinate=new Coordinate(1,0);
		board.getBlock(coordinate).setLevel(4);

		pan.performMove(cWorker0,coordinate,0);
		assertFalse(pan.getHasWon());
	}

	@Test
	public void performMove_goesFromLevel4to1_shouldWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		board.getBlock(cc0).setLevel(4);

		Coordinate coordinate=new Coordinate(1,0);
		board.getBlock(coordinate).setLevel(1);

		pan.performMove(cWorker0,coordinate,0);
		assertTrue(pan.getHasWon());
	}

	@Test
	public void performMove_goesFromLevel4to2_shouldWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		board.getBlock(cc0).setLevel(4);

		Coordinate coordinate=new Coordinate(1,0);
		board.getBlock(coordinate).setLevel(2);

		pan.performMove(cWorker0,coordinate,0);
		assertTrue(pan.getHasWon());
	}

	@Test
	public void performMove_goesFromLevel3to2shouldNotWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		board.getBlock(cc0).setLevel(3);

		Coordinate coordinate=new Coordinate(1,0);
		board.getBlock(coordinate).setLevel(2);

		pan.performMove(cWorker0,coordinate,0);
		assertFalse(pan.getHasWon());
	}

	@Test
	public void performMove_goesFromLevel3to1shouldWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		board.getBlock(cc0).setLevel(3);

		Coordinate coordinate=new Coordinate(1,0);
		board.getBlock(coordinate).setLevel(1);

		pan.performMove(cWorker0,coordinate,0);
		assertTrue(pan.getHasWon());
	}

	@Test
	public void performMove_goesFromLevel2to1_shouldNotWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		board.getBlock(cc0).setLevel(2);

		Coordinate coordinate=new Coordinate(1,0);
		board.getBlock(coordinate).setLevel(1);

		pan.performMove(cWorker0,coordinate,0);
		assertFalse(pan.getHasWon());
	}

	@Test
	public void panCheckIfWin_goesFromLevel4to0_shouldWin(){
		pan.levelBefore=4;
		pan.levelAfter=0;
		pan.panCheckIfWin();
		assertTrue(pan.getHasWon());
	}

	@Test
	public void panCheckIfWin_goesFromLevel4to1_shouldWin(){
		pan.levelBefore=4;
		pan.levelAfter=1;
		pan.panCheckIfWin();
		assertTrue(pan.getHasWon());
	}

	@Test
	public void panCheckIfWin_goesFromLevel4to2_shouldWin(){
		pan.levelBefore=4;
		pan.levelAfter=2;
		pan.panCheckIfWin();
		assertTrue(pan.getHasWon());
	}

	@Test
	public void panCheckIfWin_goesFromLevel3to0_shouldWin(){
		pan.levelBefore=3;
		pan.levelAfter=0;
		pan.panCheckIfWin();
		assertTrue(pan.getHasWon());
	}

	@Test
	public void panCheckIfWin_goesFromLevel3to1_shouldWin(){
		pan.levelBefore=3;
		pan.levelAfter=1;
		pan.panCheckIfWin();
		assertTrue(pan.getHasWon());
	}

	@Test
	public void panCheckIfWin_goesFromLevel2to0_shouldWin(){
		pan.levelBefore=2;
		pan.levelAfter=0;
		pan.panCheckIfWin();
		assertTrue(pan.getHasWon());
	}

	@Test
	public void panCheckIfWin_goesFromLevel1to0_shouldNotWin(){
		pan.levelBefore=1;
		pan.levelAfter=0;
		pan.panCheckIfWin();
		assertFalse(pan.getHasWon());
	}


	@Test
	public void panCheckIfWin_goesFromLevel0to0_shouldNotWin(){
		pan.levelBefore=0;
		pan.levelAfter=0;
		pan.panCheckIfWin();
		assertFalse(pan.getHasWon());
	}

	@Test
	public void panCheckIfWin_goesFromLevel2to1_shouldNotWin(){
		pan.levelBefore=2;
		pan.levelAfter=1;
		pan.panCheckIfWin();
		assertFalse(pan.getHasWon());
	}

	@Test
	public void panCheckIfWin_goesFromLevel3to2_shouldNotWin(){
		pan.levelBefore=3;
		pan.levelAfter=2;
		pan.panCheckIfWin();
		assertFalse(pan.getHasWon());
	}

	@Test
	public void panCheckIfWin_goesFromLevel4to3_shouldNotWin(){
		pan.levelBefore=4;
		pan.levelAfter=3;
		pan.panCheckIfWin();
		assertFalse(pan.getHasWon());
	}

	@Test
	public void panCheckIfWin_goesFromLevel4to4_shouldNotWin(){
		pan.levelBefore=4;
		pan.levelAfter=4;
		pan.panCheckIfWin();
		assertFalse(pan.getHasWon());
	}

	@Test
	public void panCheckIfWin_goesFromLevel3to4_shouldNotWin(){
		pan.levelBefore=3;
		pan.levelAfter=4;
		pan.panCheckIfWin();
		assertFalse(pan.getHasWon());
	}

	@Test
	public void panCheckIfWin_goesFromLevel3to3_shouldNotWin(){
		pan.levelBefore=3;
		pan.levelAfter=3;
		pan.panCheckIfWin();
		assertFalse(pan.getHasWon());
	}

	@Test
	public void panCheckIfWin_goesFromLevel2to2_shouldNotWin(){
		pan.levelBefore=2;
		pan.levelAfter=2;
		pan.panCheckIfWin();
		assertFalse(pan.getHasWon());
	}

	@Test
	public void panCheckIfWin_goesFromLevel1to1_shouldNotWin(){
		pan.levelBefore=1;
		pan.levelAfter=1;
		pan.panCheckIfWin();
		assertFalse(pan.getHasWon());
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
		pan.setMovedWorker(cWorker0);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));

		pan.setBuild();

		assertEquals(testArray.size(), pan.buildList.size());

		for (int i = 0; i < pan.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.buildList.get(i).getC().getY());
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
		pan.setMovedWorker(cWorker0);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));

		pan.setBuild();

		assertEquals(testArray.size(), pan.buildList.size());

		for (int i = 0; i < pan.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.buildList.get(i).getC().getY());
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
		pan.setMovedWorker(cWorker0);
		board.getBlock(new Coordinate(1,0)).setDome();
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));

		pan.setBuild();

		assertEquals(testArray.size(), pan.buildList.size());

		for (int i = 0; i < pan.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.buildList.get(i).getC().getY());
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
		pan.setMovedWorker(cWorker0);

		board.getBlock(new Coordinate(1,0)).setDome();
		board.getBlock(new Coordinate(1,1)).setDome();
		board.getBlock(new Coordinate(0,1)).setDome();
		ArrayList<Coordinate> testArray=new ArrayList<>();

		pan.setBuild();

		assertEquals(testArray.size(), pan.buildList.size());
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
		pan.setMovedWorker(cWorker0);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(3,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));

		pan.setBuild();

		assertEquals(testArray.size(), pan.buildList.size());

		for (int i = 0; i < pan.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.buildList.get(i).getC().getY());
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
		pan.setMovedWorker(cWorker0);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));

		pan.setBuild();

		assertEquals(testArray.size(), pan.buildList.size());

		for (int i = 0; i < pan.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.buildList.get(i).getC().getY());
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
		pan.setMovedWorker(cWorker0);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		board.getBlock(new Coordinate(3,0)).setDome();
		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));

		pan.setBuild();

		assertEquals(testArray.size(), pan.buildList.size());

		for (int i = 0; i < pan.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.buildList.get(i).getC().getY());
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

		pan.showBuild(cWorker0);

		assertEquals(testArray.size(), pan.buildList.size());

		for (int i = 0; i < pan.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.buildList.get(i).getC().getY());
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

		pan.showBuild(cWorker0);

		assertEquals(testArray.size(), pan.buildList.size());

		for (int i = 0; i < pan.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.buildList.get(i).getC().getY());
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

		pan.showBuild(cWorker0);

		assertEquals(testArray.size(), pan.buildList.size());

		for (int i = 0; i < pan.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.buildList.get(i).getC().getY());
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

		pan.showBuild(cWorker0);

		assertEquals(testArray.size(), pan.buildList.size());

		for (int i = 0; i < pan.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.buildList.get(i).getC().getY());
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

		pan.showBuild(cWorker0);

		assertEquals(testArray.size(), pan.buildList.size());

		for (int i = 0; i < pan.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.buildList.get(i).getC().getY());
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

		pan.showBuild(cWorker0);

		assertEquals(testArray.size(), pan.buildList.size());

		for (int i = 0; i < pan.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), pan.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), pan.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void performBuild_CoordinateAtLevel0_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=0;
		board.getBlock(c).setLevel(level);
		pan.performBuild(c);
		assertEquals(level+1,board.getBlock(c).getLevel());
	}

	@Test
	public void performBuild_CoordinateAtLevel1_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=1;
		board.getBlock(c).setLevel(level);
		pan.performBuild(c);
		assertEquals(level+1,board.getBlock(c).getLevel());
	}

	@Test
	public void performBuild_CoordinateAtLevel2_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=2;
		board.getBlock(c).setLevel(level);
		pan.performBuild(c);
		assertEquals(level+1,board.getBlock(c).getLevel());
	}

	@Test
	public void performBuild_CoordinateAtLevel3_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=3;
		board.getBlock(c).setLevel(level);
		pan.performBuild(c);
		assertEquals(level+1,board.getBlock(c).getLevel());
		assertTrue(board.getBlock(c).getIsDome());
	}

	@Test
	public void performBuild_CoordinateAtLevel4_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=4;
		board.getBlock(c).setLevel(level);
		pan.performBuild(c);
		assertEquals(level,board.getBlock(c).getLevel());
	}

	@Test
	public void nextTurn_correctInput_shouldSetToNextTurn(){
		pan.nextTurn();
		assertEquals(StateNumber.settingMovement, pan.getState());
		assertEquals(StateNumber.nextTurn, rules.service.getType());
		assertEquals(0, pan.service.getAvailableCells().size());
		assertEquals(0, pan.service.getAvailableCells2().size());
	}

	@Test
	public void checkIfWin_endMoveAtLevel0_shouldNotWin(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		pan.setHasWon(false);
		board.getBlock(cc0).setLevel(0);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(0);
		pan.showMoves(cWorker0);
		pan.performMove(cWorker0,c,0);
		pan.CheckIfWin();

		assertFalse(pan.getHasWon());
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

		pan.setHasWon(false);
		board.getBlock(cc0).setLevel(0);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(1);
		pan.showMoves(cWorker0);
		pan.performMove(cWorker0,c,0);
		pan.CheckIfWin();

		assertFalse(pan.getHasWon());
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

		pan.setHasWon(false);
		board.getBlock(cc0).setLevel(1);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(2);
		pan.showMoves(cWorker0);
		pan.performMove(cWorker0,c,0);
		pan.CheckIfWin();

		assertFalse(pan.getHasWon());
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

		pan.setHasWon(false);
		board.getBlock(cc0).setLevel(3);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(3);
		pan.showMoves(cWorker0);
		pan.performMove(cWorker0,c,0);
		pan.CheckIfWin();

		assertFalse(pan.getHasWon());
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

		pan.setHasWon(false);
		board.getBlock(cc0).setLevel(2);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(3);
		pan.showMoves(cWorker0);
		pan.performMove(cWorker0,c,0);
		pan.CheckIfWin();

		assertTrue(pan.getHasWon());
		assertEquals(StateNumber.haswon, rules.service.getType());
		assertEquals(0, pan.service.getAvailableCells().size());
		assertEquals(0, pan.service.getAvailableCells2().size());
	}

	@Test
	public void lose_correctInput_correctOutput(){
		pan.lose();

		assertEquals(StateNumber.sendhaslose, pan.getState());
		assertEquals(StateNumber.haswon, rules.service.getType());
		assertEquals(0, pan.service.getAvailableCells().size());
		assertEquals(0, pan.service.getAvailableCells2().size());
	}

	@Test
	public void invertPlayer_correctInput_correctOutput(){
		Player tPlayer=new Player("tplayer",0,null,Color.ANSI_YELLOW);
		rules.thirdPlayer=tPlayer;

		pan.invertPlayer();

		assertEquals(Color.ANSI_YELLOW, rules.nextPlayer.getColor());
		assertEquals(Color.ANSI_BLUE, rules.thirdPlayer.getColor());
	}

	@Test
	public void CheckList_notEmptyList_shouldDoNothing(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		testArray.add(new LightBlock(new Coordinate(0,0)));
		pan.setIsalive(true);
		pan.CheckList(testArray);

		assertTrue(pan.getIsalive());
	}

	@Test
	public void CheckList_emptyListAndOtherPlayerIsNotAlive_stateShouldBeHasWon(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		pan.setIsalive(true);
		tPlayer.getAbstractRule().setIsalive(false);
		pan.CheckList(testArray);


		assertFalse(pan.getIsalive());
		assertEquals(StateNumber.haswon, rules.service.getType());
	}

	@Test
	public void CheckList_emptyListAndOtherPlayersAreAlive_stateShouldBeNextTurn(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		pan.setIsalive(true);
		pan.CheckList(testArray);


		assertFalse(pan.getIsalive());
		assertEquals(StateNumber.nextTurn, rules.service.getType());
	}

	@Test
	public void Win_correctInput_correctOutput(){
		pan.Win();

		assertEquals(StateNumber.haswon, pan.getState());
		assertTrue(pan.getHasWon());
		assertEquals(StateNumber.haswon, rules.service.getType());
		assertEquals(0, pan.service.getAvailableCells().size());
		assertEquals(0, pan.service.getAvailableCells2().size());
	}

	@Test
	public void getMoveList_correctInput_correctOutput(){
		LightBlock lb1=new LightBlock(new Coordinate(0,0));
		pan.moveList.add(lb1);
		assertEquals(0,pan.getMoveList().get(0).getC().getX());
		assertEquals(0,pan.getMoveList().get(0).getC().getY());
	}

	@Test
	public void getBuildList_correctInput_correctOutput(){
		LightBlock lb1=new LightBlock(new Coordinate(0,0));
		pan.buildList.add(lb1);
		assertEquals(0,pan.getBuildList().get(0).getC().getX());
		assertEquals(0,pan.getBuildList().get(0).getC().getY());
	}

	@Test
	public void getTurn_correctInput_correctOutput(){
		pan.turn=1;
		assertEquals(1,pan.getTurn());
	}

	@Test
	public void getCurrentPlayer_correctInput_correctOutput(){
		assertEquals(cPlayer,pan.getCurrentPlayer());
	}

	@Test
	public void getNextPlayer_correctInput_correctOutput(){
		assertEquals(nPlayer,pan.getNextPlayer());
	}

	@Test
	public void getThirdPlayer_correctInput_correctOutput(){
		assertEquals(tPlayer,pan.getThirdPlayer());
	}

	@Test
	public void turnHandler_stateIsSettingMovement_shouldBeMoving(){
		pan.setState(StateNumber.settingMovement);
		Service s = new Service(StateNumber.settingMovement,"codice","whatshouldido");
		pan.turnHandler_real(s);
		assertEquals(StateNumber.moving, pan.getState());
	}

	@Test
	public void turnHandler_stateIsMoving_shouldBeBuilding(){
		pan.setState(StateNumber.moving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		pan.turnHandler_real(s);
		assertEquals(StateNumber.building, pan.getState());
	}

	@Test
	public void turnHandler_stateIsSettingBuilding_shouldBeBuilding(){
		pan.setState(StateNumber.settingBuilding);
		Service s = new Service(StateNumber.settingBuilding,"codice","whatshouldido");
		pan.setMovedWorker(cPlayer.getSpecificWorker(0));
		pan.turnHandler_real(s);
		assertEquals(StateNumber.building, pan.getState());
	}

	@Test
	public void turnHandler_stateIsBuilding_shouldBeSettingMovement(){
		pan.setState(StateNumber.building);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		pan.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, pan.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyMoving_shouldBeOnlyMoving(){
		pan.setState(StateNumber.onlymoving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		pan.turnHandler_real(s);
		assertEquals(StateNumber.onlymoving, pan.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyBuilding_shouldBeOnlyBuilding(){
		pan.setState(StateNumber.onlybuilding);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		pan.turnHandler_real(s);
		assertEquals(StateNumber.onlybuilding, pan.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyNextTurn_shouldBeSettingMovement(){
		pan.setState(StateNumber.onlynextturn);
		Service s = new Service(StateNumber.onlynextturn,"codice","whatshouldido");
		pan.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, pan.getState());
	}

	@Test
	public void getLevelPrecPosition_correctInput_correctOutput(){
		rules.levelPrecPosition=1;
		assertEquals(1,pan.getLevelPrecPosition());
	}

	@Test
	public void setLevelPrecPosition_correctInput_correctOutput(){
		pan.setLevelPrecPosition(1);
		assertEquals(1,pan.getLevelPrecPosition());
	}

	@Test
	public void getMovedWorker_correctInput_correctOutput(){
		rules.movedWorker=cPlayer.getSpecificWorker(0);
		assertEquals(cPlayer.getSpecificWorker(0),pan.getMovedWorker());
	}

	@Test
	public void getStringParticular_correctInput_correctOutput(){
		String string= "testString";
		pan.stringParticular=string;
		assertEquals(string,pan.getStringParticular());
	}

	@Test
	public void setStringParticular_correctInput_correctOutput(){
		String string= "testString";
		pan.setStringParticular(string);
		assertEquals(string,pan.getStringParticular());
	}

	@Test
	public void getWorkerToSend_correctInput_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		LightBlock lb0=new LightBlock(cc0);
		LightBlock lb1=new LightBlock(cc1);
		pan.workerToSend.add(lb0);
		pan.workerToSend.add(lb1);

		ArrayList<LightBlock> testArray = new ArrayList<>();
		testArray.add(lb0);
		testArray.add(lb1);
		ArrayList<LightBlock> wTs=pan.getWorkerToSend();
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

		pan.setWorkerToSend(testArray);

		assertEquals(testArray.size(),pan.getWorkerToSend().size());

		for (int i = 0; i < pan.getWorkerToSend().size(); i++) {
			assertEquals(testArray.get(i),pan.getWorkerToSend().get(i));
		}
	}
}