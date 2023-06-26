package it.polimi.ingsw.PSP45.controller;

import it.polimi.ingsw.PSP45.model.*;
import it.polimi.ingsw.PSP45.utils.Color;
import it.polimi.ingsw.PSP45.utils.GodName;
import it.polimi.ingsw.PSP45.utils.Service;
import it.polimi.ingsw.PSP45.utils.StateNumber;
import javafx.scene.effect.Light;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Filippo Locatelli
 *
 */
public class RulesTest {
	Board board=null;
	Rules rules=null;
	Player cPlayer=null;
	Player nPlayer=null;
	Player tPlayer=null;

	@Before
	public void setUp() {
		board = new Board();
		cPlayer=new Player("cplayer",0,null, Color.ANSI_RED);
		nPlayer=new Player("nplayer",0,null,Color.ANSI_BLUE);
		tPlayer=new Player("nome",0,null,Color.ANSI_PURPLE);
		rules=new Rules(1,board,cPlayer,nPlayer,tPlayer);
		cPlayer.setAbstractRule(rules);

	}

	@AfterEach
	void tearDown() {
		rules=null;
	}

	@Test
	public void turnHandler_real_stateIsSettingMovement_shouldBeMoving(){
		rules.setState(StateNumber.settingMovement);
		Service s = new Service(StateNumber.settingMovement,"codice","whatshouldido");
		rules.turnHandler_real(s);
		assertEquals(StateNumber.moving,rules.getState());
	}

	@Test
	public void turnHandler_real_stateIsMoving_shouldBeBuilding(){
		rules.setState(StateNumber.moving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		rules.turnHandler_real(s);
		assertEquals(StateNumber.building,rules.getState());
	}

	@Test
	public void turnHandler_real_stateIsSettingBuilding_shouldBeBuilding(){
		rules.setState(StateNumber.settingBuilding);
		Service s = new Service(StateNumber.settingBuilding,"codice","whatshouldido");
		rules.setMovedWorker(cPlayer.getSpecificWorker(0));
		rules.turnHandler_real(s);
		assertEquals(StateNumber.building,rules.getState());
	}

	@Test
	public void turnHandler_real_stateIsBuilding_shouldBeSettingMovement(){
		rules.setState(StateNumber.building);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		rules.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement,rules.getState());
	}

	@Test
	public void turnHandler_real_stateIsOnlyMoving_shouldBeOnlyMoving(){
		rules.setState(StateNumber.onlymoving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		rules.turnHandler_real(s);
		assertEquals(StateNumber.onlymoving,rules.getState());
	}

	@Test
	public void turnHandler_real_stateIsOnlyBuilding_shouldBeOnlyBuilding(){
		rules.setState(StateNumber.onlybuilding);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		rules.turnHandler_real(s);
		assertEquals(StateNumber.onlybuilding,rules.getState());
	}

	@Test
	public void turnHandler_real_stateIsOnlyNextTurn_shouldBeSettingMovement(){
		rules.setState(StateNumber.onlynextturn);
		Service s = new Service(StateNumber.onlynextturn,"codice","whatshouldido");
		rules.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement,rules.getState());
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

		rules.showMoves(cWorker0);

		assertEquals(testArray.size(),rules.moveList.size());

		for (int i = 0; i < rules.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.moveList.get(i).getC().getY());
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

		rules.showMoves(cWorker0);

		assertEquals(testArray.size(),rules.moveList.size());

		for (int i = 0; i < rules.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.moveList.get(i).getC().getY());
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

		rules.showMoves(cWorker0);

		assertEquals(testArray.size(),rules.moveList.size());

		for (int i = 0; i < rules.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.moveList.get(i).getC().getY());
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

		rules.showMoves(cWorker0);

		assertEquals(testArray.size(),rules.moveList.size());

		for (int i = 0; i < rules.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.moveList.get(i).getC().getY());
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

		rules.showMoves(cWorker0);

		assertEquals(testArray.size(),rules.moveList.size());

		for (int i = 0; i < rules.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.moveList.get(i).getC().getY());
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

		rules.showMoves(cWorker0);

		assertEquals(testArray.size(),rules.moveList.size());

		for (int i = 0; i < rules.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.moveList.get(i).getC().getY());
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

		rules.showMoves(cWorker0);

		assertEquals(testArray.size(),rules.moveList.size());

		for (int i = 0; i < rules.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.moveList.get(i).getC().getY());
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

		rules.setMoves();

		assertEquals(testArray.size(),rules.moveList.size());

		for (int i = 0; i < rules.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.moveList.get(i).getC().getY());
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

		rules.setMoves();

		assertEquals(testArray.size(),rules.moveList.size());

		for (int i = 0; i < rules.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.moveList.get(i).getC().getY());
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

		rules.setMoves();

		assertEquals(testArray.size(),rules.moveList.size());

		for (int i = 0; i < rules.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.moveList.get(i).getC().getY());
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

		rules.setMoves();

		assertEquals(testArray.size(),rules.moveList.size());

		for (int i = 0; i < rules.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.moveList.get(i).getC().getY());
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
		rules.performMove(cWorker0,c,0);
		assertEquals(c.getX(),cWorker0.getCoordinate().getX());
		assertEquals(c.getY(),cWorker0.getCoordinate().getY());
		assertTrue(board.getBlock(c).getOccupied());
		assertFalse(board.getBlock(cc0).getOccupied());
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
		rules.movedWorker=cWorker0;

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));

		rules.setBuild();

		assertEquals(testArray.size(),rules.buildList.size());

		for (int i = 0; i < rules.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.buildList.get(i).getC().getY());
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
		rules.movedWorker=cWorker0;

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));

		rules.setBuild();

		assertEquals(testArray.size(),rules.buildList.size());

		for (int i = 0; i < rules.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.buildList.get(i).getC().getY());
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
		rules.movedWorker=cWorker0;

		board.getBlock(new Coordinate(1,0)).setDome();
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));

		rules.setBuild();

		assertEquals(testArray.size(),rules.buildList.size());

		for (int i = 0; i < rules.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.buildList.get(i).getC().getY());
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
		rules.movedWorker=cWorker0;

		board.getBlock(new Coordinate(1,0)).setDome();
		board.getBlock(new Coordinate(1,1)).setDome();
		board.getBlock(new Coordinate(0,1)).setDome();
		ArrayList<Coordinate> testArray=new ArrayList<>();

		rules.setBuild();

		assertEquals(testArray.size(),rules.buildList.size());
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
		rules.movedWorker=cWorker0;
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(3,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));

		rules.setBuild();

		assertEquals(testArray.size(),rules.buildList.size());

		for (int i = 0; i < rules.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.buildList.get(i).getC().getY());
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
		rules.movedWorker=cWorker0;
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));

		rules.setBuild();

		assertEquals(testArray.size(),rules.buildList.size());

		for (int i = 0; i < rules.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.buildList.get(i).getC().getY());
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
		rules.movedWorker=cWorker0;
		ArrayList<Coordinate> testArray=new ArrayList<>();

		board.getBlock(new Coordinate(3,0)).setDome();
		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));

		rules.setBuild();

		assertEquals(testArray.size(),rules.buildList.size());

		for (int i = 0; i < rules.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.buildList.get(i).getC().getY());
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

		rules.showBuild(cWorker0);

		assertEquals(testArray.size(),rules.buildList.size());

		for (int i = 0; i < rules.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.buildList.get(i).getC().getY());
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

		rules.showBuild(cWorker0);

		assertEquals(testArray.size(),rules.buildList.size());

		for (int i = 0; i < rules.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.buildList.get(i).getC().getY());
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

		rules.showBuild(cWorker0);

		assertEquals(testArray.size(),rules.buildList.size());

		for (int i = 0; i < rules.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.buildList.get(i).getC().getY());
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

		rules.showBuild(cWorker0);

		assertEquals(testArray.size(),rules.buildList.size());

		for (int i = 0; i < rules.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.buildList.get(i).getC().getY());
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

		rules.showBuild(cWorker0);

		assertEquals(testArray.size(),rules.buildList.size());

		for (int i = 0; i < rules.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.buildList.get(i).getC().getY());
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

		rules.showBuild(cWorker0);

		assertEquals(testArray.size(),rules.buildList.size());

		for (int i = 0; i < rules.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), rules.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), rules.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void performBuild_CoordinateAtLevel0_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=0;
		board.getBlock(c).setLevel(level);
		rules.performBuild(c);
		assertEquals(level+1,board.getBlock(c).getLevel());
	}

	@Test
	public void performBuild_CoordinateAtLevel1_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=1;
		board.getBlock(c).setLevel(level);
		rules.performBuild(c);
		assertEquals(level+1,board.getBlock(c).getLevel());
	}

	@Test
	public void performBuild_CoordinateAtLevel2_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=2;
		board.getBlock(c).setLevel(level);
		rules.performBuild(c);
		assertEquals(level+1,board.getBlock(c).getLevel());
	}

	@Test
	public void performBuild_CoordinateAtLevel3_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=3;
		board.getBlock(c).setLevel(level);
		rules.performBuild(c);
		assertEquals(level+1,board.getBlock(c).getLevel());
		assertTrue(board.getBlock(c).getIsDome());
	}

	@Test
	public void performBuild_CoordinateAtLevel4_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=4;
		board.getBlock(c).setLevel(level);
		rules.performBuild(c);
		assertEquals(level,board.getBlock(c).getLevel());
	}

	@Test
	public void nextTurn_correctInput_shouldSetToNextTurn(){
		rules.nextTurn();
		assertEquals(StateNumber.settingMovement,rules.getState());
		assertEquals(StateNumber.nextTurn,rules.service.getType());
		assertEquals(0,rules.service.getAvailableCells().size());
		assertEquals(0,rules.service.getAvailableCells2().size());
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

		rules.setHasWon(false);
		board.getBlock(cc0).setLevel(0);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(0);
		rules.showMoves(cWorker0);
		rules.performMove(cWorker0,c,0);
		rules.CheckIfWin();

		assertFalse(rules.getHasWon());
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

		rules.setHasWon(false);
		board.getBlock(cc0).setLevel(0);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(1);
		rules.showMoves(cWorker0);
		rules.performMove(cWorker0,c,0);
		rules.CheckIfWin();

		assertFalse(rules.getHasWon());
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

		rules.setHasWon(false);
		board.getBlock(cc0).setLevel(1);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(2);
		rules.showMoves(cWorker0);
		rules.performMove(cWorker0,c,0);
		rules.CheckIfWin();

		assertFalse(rules.getHasWon());
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

		rules.setHasWon(false);
		board.getBlock(cc0).setLevel(3);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(3);
		rules.showMoves(cWorker0);
		rules.performMove(cWorker0,c,0);
		rules.CheckIfWin();

		assertFalse(rules.getHasWon());
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

		rules.setHasWon(false);
		board.getBlock(cc0).setLevel(2);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(3);
		rules.showMoves(cWorker0);
		rules.performMove(cWorker0,c,0);
		rules.CheckIfWin();

		assertTrue(rules.getHasWon());
		assertEquals(StateNumber.haswon,rules.service.getType());
		assertEquals(0,rules.service.getAvailableCells().size());
		assertEquals(0,rules.service.getAvailableCells2().size());
	}

	@Test
	public void lose_correctInput_correctOutput(){
		rules.lose();

		assertEquals(StateNumber.sendhaslose,rules.getState());
		assertEquals(StateNumber.haswon,rules.service.getType());
		assertEquals(0,rules.service.getAvailableCells().size());
		assertEquals(0,rules.service.getAvailableCells2().size());
	}

	@Test
	public void invertPlayer_correctInput_correctOutput(){
		Player tPlayer=new Player("tplayer",0,null,Color.ANSI_YELLOW);
		rules.thirdPlayer=tPlayer;

		rules.invertPlayer();

		assertEquals(Color.ANSI_YELLOW,rules.nextPlayer.getColor());
		assertEquals(Color.ANSI_BLUE,rules.thirdPlayer.getColor());
	}

	@Test
	public void CheckList_notEmptyList_shouldDoNothing(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		testArray.add(new LightBlock(new Coordinate(0,0)));
		rules.setIsalive(true);
		rules.CheckList(testArray);

		assertTrue(rules.getIsalive());
	}

	@Test
	public void CheckList_emptyListAndOtherPlayerIsNotAlive_stateShouldBeHasWon(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		rules.setIsalive(true);
		tPlayer.getAbstractRule().setIsalive(false);
		rules.CheckList(testArray);


		assertFalse(rules.getIsalive());
		assertEquals(StateNumber.haswon, rules.service.getType());
	}

	@Test
	public void CheckList_emptyListAndOtherPlayersAreAlive_stateShouldBeNextTurn(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		rules.setIsalive(true);
		rules.CheckList(testArray);


		assertFalse(rules.getIsalive());
		assertEquals(StateNumber.nextTurn, rules.service.getType());
	}

	@Test
	public void Win_correctInput_correctOutput(){
		rules.Win();

		assertEquals(StateNumber.haswon,rules.getState());
		assertTrue(rules.getHasWon());
		assertEquals(StateNumber.haswon,rules.service.getType());
		assertEquals(0,rules.service.getAvailableCells().size());
		assertEquals(0,rules.service.getAvailableCells2().size());
	}

	@Test
	public void getMoveList_correctInput_correctOutput(){
		LightBlock lb1=new LightBlock(new Coordinate(0,0));
		rules.moveList.add(lb1);
		assertEquals(0,rules.getMoveList().get(0).getC().getX());
		assertEquals(0,rules.getMoveList().get(0).getC().getY());
	}

	@Test
	public void getBuildList_correctInput_correctOutput(){
		LightBlock lb1=new LightBlock(new Coordinate(0,0));
		rules.buildList.add(lb1);
		assertEquals(0,rules.getBuildList().get(0).getC().getX());
		assertEquals(0,rules.getBuildList().get(0).getC().getY());
	}

	@Test
	public void getTurn_correctInput_correctOutput(){
		rules.turn=1;
		assertEquals(1,rules.getTurn());
	}

	@Test
	public void getCurrentPlayer_correctInput_correctOutput(){
		assertEquals(cPlayer,rules.getCurrentPlayer());
	}

	@Test
	public void getNextPlayer_correctInput_correctOutput(){
		assertEquals(nPlayer,rules.getNextPlayer());
	}

	@Test
	public void getThirdPlayer_correctInput_correctOutput(){
		assertEquals(tPlayer,rules.getThirdPlayer());
	}

	@Test
	public void turnHandler_stateIsSettingMovement_shouldBeMoving(){
		rules.setState(StateNumber.settingMovement);
		Service s = new Service(StateNumber.settingMovement,"codice","whatshouldido");
		rules.turnHandler_real(s);
		assertEquals(StateNumber.moving, rules.getState());
	}

	@Test
	public void turnHandler_stateIsMoving_shouldBeBuilding(){
		rules.setState(StateNumber.moving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		rules.turnHandler_real(s);
		assertEquals(StateNumber.building, rules.getState());
	}

	@Test
	public void turnHandler_stateIsSettingBuilding_shouldBeBuilding(){
		rules.setState(StateNumber.settingBuilding);
		Service s = new Service(StateNumber.settingBuilding,"codice","whatshouldido");
		rules.setMovedWorker(cPlayer.getSpecificWorker(0));
		rules.turnHandler_real(s);
		assertEquals(StateNumber.building, rules.getState());
	}

	@Test
	public void turnHandler_stateIsBuilding_shouldBeSettingMovement(){
		rules.setState(StateNumber.building);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		rules.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, rules.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyMoving_shouldBeOnlyMoving(){
		rules.setState(StateNumber.onlymoving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		rules.turnHandler_real(s);
		assertEquals(StateNumber.onlymoving, rules.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyBuilding_shouldBeOnlyBuilding(){
		rules.setState(StateNumber.onlybuilding);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		rules.turnHandler_real(s);
		assertEquals(StateNumber.onlybuilding, rules.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyNextTurn_shouldBeSettingMovement(){
		rules.setState(StateNumber.onlynextturn);
		Service s = new Service(StateNumber.onlynextturn,"codice","whatshouldido");
		rules.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, rules.getState());
	}

	@Test
	public void getLevelPrecPosition_correctInput_correctOutput(){
		rules.levelPrecPosition=1;
		assertEquals(1,rules.getLevelPrecPosition());
	}

	@Test
	public void setLevelPrecPosition_correctInput_correctOutput(){
		rules.setLevelPrecPosition(1);
		assertEquals(1,rules.getLevelPrecPosition());
	}

	@Test
	public void getMovedWorker_correctInput_correctOutput(){
		rules.movedWorker=cPlayer.getSpecificWorker(0);
		assertEquals(cPlayer.getSpecificWorker(0),rules.getMovedWorker());
	}

	@Test
	public void getStringParticular_correctInput_correctOutput(){
		String string= "testString";
		rules.stringParticular=string;
		assertEquals(string,rules.getStringParticular());
	}

	@Test
	public void setStringParticular_correctInput_correctOutput(){
		String string= "testString";
		rules.setStringParticular(string);
		assertEquals(string,rules.getStringParticular());
	}

	@Test
	public void getWorkerToSend_correctInput_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		LightBlock lb0=new LightBlock(cc0);
		LightBlock lb1=new LightBlock(cc1);
		rules.workerToSend.add(lb0);
		rules.workerToSend.add(lb1);

		ArrayList<LightBlock> testArray = new ArrayList<>();
		testArray.add(lb0);
		testArray.add(lb1);
		ArrayList<LightBlock> wTs=rules.getWorkerToSend();
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

		rules.setWorkerToSend(testArray);

		assertEquals(testArray.size(),rules.getWorkerToSend().size());

		for (int i = 0; i < rules.getWorkerToSend().size(); i++) {
			assertEquals(testArray.get(i),rules.getWorkerToSend().get(i));
		}
	}
}