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
public class HephaestusTest {

	Hephaestus hephaestus =null;
	Rules rules =null;
	Player cPlayer=null;
	Player nPlayer=null;
	Player tPlayer=null;
	Board board=null;

	@Before
	public void setUp(){
		board=new Board();
		cPlayer=new Player("nome",0, GodName.hephaestus, Color.ANSI_RED);
		nPlayer=new Player("nome",0, GodName.apollo, Color.ANSI_BLUE);
		tPlayer=new Player("nome",0,GodName.apollo,Color.ANSI_PURPLE);
		rules =new Rules(1,board,cPlayer,nPlayer,tPlayer);
		hephaestus =new Hephaestus(rules);
		cPlayer.setAbstractRule(hephaestus);
	}

	@After
	public void tearDown(){
		hephaestus =null;
	}

	@Test
	public void turnHandler_real_stateIsSettingMovement_shouldBeMoving(){
		hephaestus.setState(StateNumber.settingMovement);
		Service s = new Service(StateNumber.settingMovement,"codice","whatshouldido");
		hephaestus.turnHandler_real(s);
		assertEquals(StateNumber.moving, hephaestus.getState());
	}

	@Test
	public void turnHandler_real_stateIsMoving_shouldBeBuilding(){
		hephaestus.setState(StateNumber.moving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		hephaestus.turnHandler_real(s);
		assertEquals(StateNumber.building, hephaestus.getState());
	}

	@Test
	public void turnHandler_real_stateIsSettingBuilding_shouldBeBuilding(){
		hephaestus.setState(StateNumber.settingBuilding);
		Service s = new Service(StateNumber.settingBuilding,"codice","whatshouldido");
		hephaestus.setMovedWorker(cPlayer.getSpecificWorker(0));
		hephaestus.turnHandler_real(s);
		assertEquals(StateNumber.building, hephaestus.getState());
	}

	@Test
	public void turnHandler_real_stateIsBuildingAndSecondBuildingIsTrueAndEndSecondBuildingIsFalse_shouldBeBuilding(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate c1=new Coordinate(1,1);
		hephaestus.buildC2=c1;
		hephaestus.buildC=cc0;
		hephaestus.setMovedWorker(cWorker0);
		hephaestus.setState(StateNumber.building);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		hephaestus.setSecondBuild(true);
		hephaestus.setEndSecondBuild(false);
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		hephaestus.turnHandler_real(s);
		assertEquals(StateNumber.building, hephaestus.getState());
	}

	@Test
	public void turnHandler_real_stateIsBuildingAndSecondBuildingIsFalseAndEndSecondBuildingIsTrue_shouldBeSettingMovement(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate c1=new Coordinate(1,1);
		hephaestus.buildC2=c1;
		hephaestus.buildC=cc0;
		hephaestus.setMovedWorker(cWorker0);
		hephaestus.setState(StateNumber.building);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		hephaestus.setSecondBuild(false);
		hephaestus.setEndSecondBuild(true);
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		hephaestus.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, hephaestus.getState());
	}

	@Test
	public void turnHandler_real_stateIsBuildingAndSecondBuildingIsFalseAndEndSecondBuildingIsFalse_shouldBeBuilding(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate c1=new Coordinate(1,1);
		hephaestus.buildC2=c1;
		hephaestus.buildC=cc0;
		hephaestus.setMovedWorker(cWorker0);
		hephaestus.setState(StateNumber.building);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		hephaestus.setSecondBuild(false);
		hephaestus.setEndSecondBuild(false);
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		hephaestus.turnHandler_real(s);
		assertEquals(StateNumber.building, hephaestus.getState());
	}

	@Test
	public void turnHandler_real_stateIsBuildingAndSecondBuildingIsTrueAndEndSecondBuildingIsTrue_shouldBeSettingMovement(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate c1=new Coordinate(1,1);
		hephaestus.buildC2=c1;
		hephaestus.buildC=cc0;
		hephaestus.setMovedWorker(cWorker0);
		hephaestus.setState(StateNumber.building);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		hephaestus.setSecondBuild(true);
		hephaestus.setEndSecondBuild(true);
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		hephaestus.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, hephaestus.getState());
	}

	@Test
	public void turnHandler_real_stateIsOnlyMoving_shouldBeOnlyMoving(){
		hephaestus.setState(StateNumber.onlymoving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		hephaestus.turnHandler_real(s);
		assertEquals(StateNumber.onlymoving, hephaestus.getState());
	}

	@Test
	public void turnHandler_real_stateIsOnlyBuilding_shouldBeOnlyBuilding(){
		hephaestus.setState(StateNumber.onlybuilding);
		Coordinate c1=new Coordinate(1,1);
		hephaestus.buildC2=c1;
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		hephaestus.turnHandler_real(s);
		assertEquals(StateNumber.onlybuilding, hephaestus.getState());
	}

	@Test
	public void turnHandler_real_stateIsOnlyNextTurn_shouldBeSettingMovement(){
		hephaestus.setState(StateNumber.onlynextturn);
		Service s = new Service(StateNumber.onlynextturn,"codice","whatshouldido");
		hephaestus.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, hephaestus.getState());
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

		hephaestus.showMoves(cWorker0);

		assertEquals(testArray.size(), hephaestus.moveList.size());

		for (int i = 0; i < hephaestus.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), hephaestus.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), hephaestus.moveList.get(i).getC().getY());
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

		hephaestus.showMoves(cWorker0);

		assertEquals(testArray.size(), hephaestus.moveList.size());

		for (int i = 0; i < hephaestus.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), hephaestus.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), hephaestus.moveList.get(i).getC().getY());
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

		hephaestus.showMoves(cWorker0);

		assertEquals(testArray.size(), hephaestus.moveList.size());

		for (int i = 0; i < hephaestus.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), hephaestus.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), hephaestus.moveList.get(i).getC().getY());
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

		hephaestus.showMoves(cWorker0);

		assertEquals(testArray.size(), hephaestus.moveList.size());

		for (int i = 0; i < hephaestus.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), hephaestus.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), hephaestus.moveList.get(i).getC().getY());
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

		hephaestus.showMoves(cWorker0);

		assertEquals(testArray.size(), hephaestus.moveList.size());

		for (int i = 0; i < hephaestus.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), hephaestus.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), hephaestus.moveList.get(i).getC().getY());
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

		hephaestus.showMoves(cWorker0);

		assertEquals(testArray.size(), hephaestus.moveList.size());

		for (int i = 0; i < hephaestus.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), hephaestus.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), hephaestus.moveList.get(i).getC().getY());
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

		hephaestus.showMoves(cWorker0);

		assertEquals(testArray.size(), hephaestus.moveList.size());

		for (int i = 0; i < hephaestus.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), hephaestus.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), hephaestus.moveList.get(i).getC().getY());
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

		hephaestus.setMoves();

		assertEquals(testArray.size(), hephaestus.moveList.size());

		for (int i = 0; i < hephaestus.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), hephaestus.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), hephaestus.moveList.get(i).getC().getY());
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

		hephaestus.setMoves();

		assertEquals(testArray.size(), hephaestus.moveList.size());

		for (int i = 0; i < hephaestus.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), hephaestus.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), hephaestus.moveList.get(i).getC().getY());
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

		hephaestus.setMoves();

		assertEquals(testArray.size(), hephaestus.moveList.size());

		for (int i = 0; i < hephaestus.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), hephaestus.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), hephaestus.moveList.get(i).getC().getY());
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

		hephaestus.setMoves();

		assertEquals(testArray.size(), hephaestus.moveList.size());

		for (int i = 0; i < hephaestus.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), hephaestus.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), hephaestus.moveList.get(i).getC().getY());
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
		hephaestus.performMove(cWorker0,c,0);
		assertEquals(c.getX(),cWorker0.getCoordinate().getX());
		assertEquals(c.getY(),cWorker0.getCoordinate().getY());
		assertTrue(board.getBlock(c).getOccupied());
		assertFalse(board.getBlock(cc0).getOccupied());
		assertEquals(c.getX(), hephaestus.buildC2.getX());
		assertEquals(c.getY(), hephaestus.buildC2.getY());
		assertEquals(0, hephaestus.getMoveList().size());
	}

	@Test
	public void setBuild_SecondBuildingIsFalseAndEndSecondBuildingIsFalse_shouldSetBuild(){
		hephaestus.setSecondBuild(false);
		hephaestus.setEndSecondBuild(false);
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		hephaestus.setMovedWorker(cWorker0);
		hephaestus.buildC=cc0;

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));

		hephaestus.setBuild();

		assertEquals(testArray.size(), hephaestus.buildList.size());

		for (int i = 0; i < hephaestus.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), hephaestus.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), hephaestus.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void setBuild_SecondBuildingIsTrueAndEndSecondBuildingIsFalse_shouldSetBuild(){
		hephaestus.setSecondBuild(true);
		hephaestus.setEndSecondBuild(false);
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		hephaestus.setMovedWorker(cWorker0);
		hephaestus.buildC=new Coordinate(1,0);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,0));

		hephaestus.setBuild();

		assertEquals(testArray.size(), hephaestus.buildList.size());

		for (int i = 0; i < hephaestus.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), hephaestus.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), hephaestus.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_secondBuildIsTrueAndLevelAfterBuildLessThan3_correctOutput(){
		hephaestus.setSecondBuild(true);
		hephaestus.setEndSecondBuild(false);
		hephaestus.setLevelAfterBuild(0);
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		hephaestus.buildC=new Coordinate(1,0);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,0));
		hephaestus.showBuild(cWorker0);
		assertEquals(testArray.size(), hephaestus.buildList.size());

		for (int i = 0; i < hephaestus.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), hephaestus.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), hephaestus.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_secondBuildIsFalseAndLevelAfterBuildLessThan3_correctOutput(){
		hephaestus.setSecondBuild(false);
		hephaestus.setEndSecondBuild(false);
		hephaestus.setLevelAfterBuild(0);
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		hephaestus.buildC=new Coordinate(1,0);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));
		hephaestus.showBuild(cWorker0);
		assertEquals(testArray.size(), hephaestus.buildList.size());

		for (int i = 0; i < hephaestus.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), hephaestus.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), hephaestus.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_secondBuildIsTrueAndLevelAfterBuildMoreThan3_correctOutput(){
		hephaestus.setSecondBuild(true);
		hephaestus.setEndSecondBuild(false);
		hephaestus.setLevelAfterBuild(4);
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		hephaestus.buildC=new Coordinate(1,0);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(0,0));
		hephaestus.showBuild(cWorker0);
		assertEquals(testArray.size(), hephaestus.buildList.size());

		for (int i = 0; i < hephaestus.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), hephaestus.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), hephaestus.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void performBuild_coordinatesAtLevel0_correctOutput(){
		hephaestus.setSecondBuild(false);
		hephaestus.setEndSecondBuild(false);
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		Coordinate c=new Coordinate(1,0);
		Coordinate c1=new Coordinate(1,1);
		hephaestus.buildC2=c1;
		board.getBlock(c).setLevel(0);
		int l=board.getBlock(c).getLevel();
		hephaestus.performBuild(c);
		assertEquals(l+1,board.getBlock(c).getLevel());
		assertEquals(l+1, hephaestus.getLevelAfterBuild());
		assertTrue(hephaestus.getSecondBuild());
		assertEquals(0, hephaestus.buildList.size());
	}

	@Test
	public void performBuild_coordinatesAtLevel1_correctOutput(){
		hephaestus.setSecondBuild(false);
		hephaestus.setEndSecondBuild(false);
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		Coordinate c=new Coordinate(1,0);
		Coordinate c1=new Coordinate(1,1);
		hephaestus.buildC2=c1;
		board.getBlock(c).setLevel(1);
		int l=board.getBlock(c).getLevel();
		hephaestus.performBuild(c);
		assertEquals(l+1,board.getBlock(c).getLevel());
		assertEquals(l+1, hephaestus.getLevelAfterBuild());
		assertTrue(hephaestus.getSecondBuild());
		assertEquals(0, hephaestus.buildList.size());
	}

	@Test
	public void performBuild_coordinatesAtLevel2_correctOutput(){
		hephaestus.setSecondBuild(false);
		hephaestus.setEndSecondBuild(false);
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		Coordinate c=new Coordinate(1,0);
		Coordinate c1=new Coordinate(1,1);
		hephaestus.buildC2=c1;
		board.getBlock(c).setLevel(2);
		int l=board.getBlock(c).getLevel();
		hephaestus.performBuild(c);
		assertEquals(l+1,board.getBlock(c).getLevel());
		assertEquals(l+1, hephaestus.getLevelAfterBuild());
		assertTrue(hephaestus.getSecondBuild());
		assertEquals(0, hephaestus.buildList.size());
	}

	@Test
	public void performBuild_coordinatesAtLevel3_correctOutput(){
		hephaestus.setSecondBuild(false);
		hephaestus.setEndSecondBuild(false);
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		Coordinate c=new Coordinate(1,0);
		Coordinate c1=new Coordinate(1,1);
		hephaestus.buildC2=c1;
		board.getBlock(c).setLevel(3);
		int l=board.getBlock(c).getLevel();
		hephaestus.performBuild(c);
		assertEquals(l+1,board.getBlock(c).getLevel());
		assertEquals(l+1, hephaestus.getLevelAfterBuild());
		assertTrue(hephaestus.getSecondBuild());
		assertTrue(board.getBlock(c).getIsDome());
		assertEquals(0, hephaestus.buildList.size());
	}

	@Test
	public void performBuild_coordinatesAtLevel4_correctOutput(){
		hephaestus.setSecondBuild(false);
		hephaestus.setEndSecondBuild(false);
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,2);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(0,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);
		Coordinate c=new Coordinate(1,0);
		Coordinate c1=new Coordinate(1,1);
		hephaestus.buildC2=c1;
		board.getBlock(c).setLevel(4);
		int l=board.getBlock(c).getLevel();
		hephaestus.performBuild(c);
		assertEquals(l,board.getBlock(c).getLevel());
		assertEquals(l, hephaestus.getLevelAfterBuild());
		assertTrue(hephaestus.getSecondBuild());
		assertEquals(0, hephaestus.buildList.size());
	}

	@Test
	public void nextTurn_correctInput_shouldSetToNextTurn(){
		hephaestus.nextTurn();
		assertEquals(StateNumber.settingMovement, hephaestus.getState());
		assertEquals(StateNumber.nextTurn, rules.service.getType());
		assertEquals(0, hephaestus.service.getAvailableCells().size());
		assertEquals(0, hephaestus.service.getAvailableCells2().size());
		assertFalse(hephaestus.getSecondBuild());
		assertFalse(hephaestus.getEndSecondBuild());
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

		hephaestus.setHasWon(false);
		board.getBlock(cc0).setLevel(0);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(0);
		hephaestus.showMoves(cWorker0);
		hephaestus.performMove(cWorker0,c,0);
		hephaestus.CheckIfWin();

		assertFalse(hephaestus.getHasWon());
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

		hephaestus.setHasWon(false);
		board.getBlock(cc0).setLevel(0);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(1);
		hephaestus.showMoves(cWorker0);
		hephaestus.performMove(cWorker0,c,0);
		hephaestus.CheckIfWin();

		assertFalse(hephaestus.getHasWon());
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

		hephaestus.setHasWon(false);
		board.getBlock(cc0).setLevel(1);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(2);
		hephaestus.showMoves(cWorker0);
		hephaestus.performMove(cWorker0,c,0);
		hephaestus.CheckIfWin();

		assertFalse(hephaestus.getHasWon());
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

		hephaestus.setHasWon(false);
		board.getBlock(cc0).setLevel(3);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(3);
		hephaestus.showMoves(cWorker0);
		hephaestus.performMove(cWorker0,c,0);
		hephaestus.CheckIfWin();

		assertFalse(hephaestus.getHasWon());
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

		hephaestus.setHasWon(false);
		board.getBlock(cc0).setLevel(2);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(3);
		hephaestus.showMoves(cWorker0);
		hephaestus.performMove(cWorker0,c,0);
		hephaestus.CheckIfWin();

		assertTrue(hephaestus.getHasWon());
		assertEquals(StateNumber.haswon, rules.service.getType());
		assertEquals(0, hephaestus.service.getAvailableCells().size());
		assertEquals(0, hephaestus.service.getAvailableCells2().size());
	}

	@Test
	public void lose_correctInput_correctOutput(){
		hephaestus.lose();

		assertEquals(StateNumber.sendhaslose, hephaestus.getState());
		assertEquals(StateNumber.haswon, rules.service.getType());
		assertEquals(0, hephaestus.service.getAvailableCells().size());
		assertEquals(0, hephaestus.service.getAvailableCells2().size());
	}

	@Test
	public void invertPlayer_correctInput_correctOutput(){
		Player tPlayer=new Player("tplayer",0,null,Color.ANSI_YELLOW);
		rules.thirdPlayer=tPlayer;

		hephaestus.invertPlayer();

		assertEquals(Color.ANSI_YELLOW, rules.nextPlayer.getColor());
		assertEquals(Color.ANSI_BLUE, rules.thirdPlayer.getColor());
	}

	@Test
	public void CheckList_notEmptyList_shouldDoNothing(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		testArray.add(new LightBlock(new Coordinate(0,0)));
		hephaestus.setIsalive(true);
		hephaestus.CheckList(testArray);

		assertTrue(hephaestus.getIsalive());
	}

	@Test
	public void CheckList_emptyListAndOtherPlayerIsNotAlive_stateShouldBeHasWon(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		hephaestus.setIsalive(true);
		tPlayer.getAbstractRule().setIsalive(false);
		hephaestus.CheckList(testArray);


		assertFalse(hephaestus.getIsalive());
		assertEquals(StateNumber.haswon, rules.service.getType());
	}

	@Test
	public void CheckList_emptyListAndOtherPlayersAreAlive_stateShouldBeNextTurn(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		hephaestus.setIsalive(true);
		hephaestus.CheckList(testArray);


		assertFalse(hephaestus.getIsalive());
		assertEquals(StateNumber.nextTurn, rules.service.getType());
	}

	@Test
	public void Win_correctInput_correctOutput(){
		hephaestus.Win();

		assertEquals(StateNumber.haswon, hephaestus.getState());
		assertTrue(hephaestus.getHasWon());
		assertEquals(StateNumber.haswon, rules.service.getType());
		assertEquals(0, hephaestus.service.getAvailableCells().size());
		assertEquals(0, hephaestus.service.getAvailableCells2().size());
	}

	@Test
	public void getMoveList_correctInput_correctOutput(){
		LightBlock lb1=new LightBlock(new Coordinate(0,0));
		hephaestus.moveList.add(lb1);
		assertEquals(0, hephaestus.getMoveList().get(0).getC().getX());
		assertEquals(0, hephaestus.getMoveList().get(0).getC().getY());
	}

	@Test
	public void getBuildList_correctInput_correctOutput(){
		LightBlock lb1=new LightBlock(new Coordinate(0,0));
		hephaestus.buildList.add(lb1);
		assertEquals(0, hephaestus.getBuildList().get(0).getC().getX());
		assertEquals(0, hephaestus.getBuildList().get(0).getC().getY());
	}

	@Test
	public void getTurn_correctInput_correctOutput(){
		hephaestus.turn=1;
		assertEquals(1, hephaestus.getTurn());
	}

	@Test
	public void getCurrentPlayer_correctInput_correctOutput(){
		assertEquals(cPlayer, hephaestus.getCurrentPlayer());
	}

	@Test
	public void getNextPlayer_correctInput_correctOutput(){
		assertEquals(nPlayer, hephaestus.getNextPlayer());
	}

	@Test
	public void getThirdPlayer_correctInput_correctOutput(){
		assertEquals(tPlayer, hephaestus.getThirdPlayer());
	}

	@Test
	public void turnHandler_stateIsSettingMovement_shouldBeMoving(){
		hephaestus.setState(StateNumber.settingMovement);
		Service s = new Service(StateNumber.settingMovement,"codice","whatshouldido");
		hephaestus.turnHandler_real(s);
		assertEquals(StateNumber.moving, hephaestus.getState());
	}

	@Test
	public void turnHandler_stateIsMoving_shouldBeBuilding(){
		hephaestus.setState(StateNumber.moving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		hephaestus.turnHandler_real(s);
		assertEquals(StateNumber.building, hephaestus.getState());
	}

	@Test
	public void turnHandler_stateIsSettingBuilding_shouldBeBuilding(){
		hephaestus.setState(StateNumber.settingBuilding);
		Service s = new Service(StateNumber.settingBuilding,"codice","whatshouldido");
		hephaestus.setMovedWorker(cPlayer.getSpecificWorker(0));
		hephaestus.turnHandler_real(s);
		assertEquals(StateNumber.building, hephaestus.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyMoving_shouldBeOnlyMoving(){
		hephaestus.setState(StateNumber.onlymoving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		hephaestus.turnHandler_real(s);
		assertEquals(StateNumber.onlymoving, hephaestus.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyNextTurn_shouldBeSettingMovement(){
		hephaestus.setState(StateNumber.onlynextturn);
		Service s = new Service(StateNumber.onlynextturn,"codice","whatshouldido");
		hephaestus.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, hephaestus.getState());
	}

	@Test
	public void getLevelPrecPosition_correctInput_correctOutput(){
		rules.levelPrecPosition=1;
		assertEquals(1, hephaestus.getLevelPrecPosition());
	}

	@Test
	public void setLevelPrecPosition_correctInput_correctOutput(){
		hephaestus.setLevelPrecPosition(1);
		assertEquals(1, hephaestus.getLevelPrecPosition());
	}

	@Test
	public void getMovedWorker_correctInput_correctOutput(){
		rules.movedWorker=cPlayer.getSpecificWorker(0);
		assertEquals(cPlayer.getSpecificWorker(0), hephaestus.getMovedWorker());
	}

	@Test
	public void getStringParticular_correctInput_correctOutput(){
		String string= "testString";
		hephaestus.stringParticular=string;
		assertEquals(string,hephaestus.getStringParticular());
	}

	@Test
	public void setStringParticular_correctInput_correctOutput(){
		String string= "testString";
		hephaestus.setStringParticular(string);
		assertEquals(string,hephaestus.getStringParticular());
	}

	@Test
	public void getWorkerToSend_correctInput_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		LightBlock lb0=new LightBlock(cc0);
		LightBlock lb1=new LightBlock(cc1);
		hephaestus.workerToSend.add(lb0);
		hephaestus.workerToSend.add(lb1);

		ArrayList<LightBlock> testArray = new ArrayList<>();
		testArray.add(lb0);
		testArray.add(lb1);
		ArrayList<LightBlock> wTs=hephaestus.getWorkerToSend();
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

		hephaestus.setWorkerToSend(testArray);

		assertEquals(testArray.size(),hephaestus.getWorkerToSend().size());

		for (int i = 0; i < hephaestus.getWorkerToSend().size(); i++) {
			assertEquals(testArray.get(i),hephaestus.getWorkerToSend().get(i));
		}
	}

}