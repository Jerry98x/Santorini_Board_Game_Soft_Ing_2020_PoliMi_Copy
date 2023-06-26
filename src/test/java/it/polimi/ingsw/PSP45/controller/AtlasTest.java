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
public class AtlasTest {
	Atlas atlas =null;
	Rules rules =null;
	Player cPlayer=null;
	Player nPlayer=null;
	Player tPlayer=null;
	Board board=null;
	Service s=null;

	@Before
	public void setUp(){
		board=new Board();
		cPlayer=new Player("cPlayer",0, GodName.atlas, Color.ANSI_RED);
		nPlayer=new Player("nPlayer",0,GodName.apollo,Color.ANSI_BLUE);
		tPlayer=new Player("nome",0,GodName.apollo,Color.ANSI_PURPLE);
		rules =new Rules(1,board,cPlayer,nPlayer,tPlayer);
		atlas =new Atlas(rules);
		cPlayer.setAbstractRule(atlas);
		s=new Service(StateNumber.readyToBuild,"codice","whatshouldido");
	}

	@After
	public void tearDown(){
		atlas =null;
	}

	@Test
	public void setBuild_cornerWorkerPosition_CorrectOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		atlas.setMovedWorker(cWorker0);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));

		atlas.setBuild();
		assertEquals(testArray.size(), atlas.buildList.size());

		for (int i = 0; i < atlas.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void setBuild_sideWorkerPosition_CorrectOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(3,3);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		atlas.setMovedWorker(cWorker0);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(3,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(3,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));

		atlas.setBuild();
		assertEquals(testArray.size(), atlas.buildList.size());

		for (int i = 0; i < atlas.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void setBuild_centerWorkerPosition_CorrectOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,2);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(0,0);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(4,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		atlas.setMovedWorker(cWorker0);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(1,2));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));
		testArray.add(new Coordinate(3,3));

		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(1,2));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));
		testArray.add(new Coordinate(3,3));

		atlas.setBuild();
		assertEquals(testArray.size(), atlas.buildList.size());

		for (int i = 0; i < atlas.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void setBuild_centerWorkerPositionAndOpponentAround_CorrectOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,2);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(0,0);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(4,4);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(3,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		atlas.setMovedWorker(cWorker0);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(1,2));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));

		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(1,2));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));

		atlas.setBuild();
		assertEquals(testArray.size(), atlas.buildList.size());

		for (int i = 0; i < atlas.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void setBuild_centerWorkerPositionAnd2OpponentsAround_CorrectOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,2);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(0,0);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,1);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(3,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		atlas.setMovedWorker(cWorker0);
		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(1,2));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));

		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(1,2));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));

		atlas.setBuild();
		assertEquals(testArray.size(), atlas.buildList.size());

		for (int i = 0; i < atlas.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_cornerWorkerPosition_CorrectOutput(){
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

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));

		atlas.showBuild(cWorker0);
		assertEquals(testArray.size(), atlas.buildList.size());

		for (int i = 0; i < atlas.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_sideWorkerPosition_CorrectOutput(){
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

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(3,0));
		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));

		atlas.showBuild(cWorker0);
		assertEquals(testArray.size(), atlas.buildList.size());

		for (int i = 0; i < atlas.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_centerWorkerPosition_CorrectOutput(){
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

		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(1,2));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));
		testArray.add(new Coordinate(3,3));

		atlas.showBuild(cWorker0);
		assertEquals(testArray.size(), atlas.buildList.size());

		for (int i = 0; i < atlas.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_centerWorkerPositionAndOpponentAround_CorrectOutput(){
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

		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(2,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(1,2));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));

		atlas.showBuild(cWorker0);
		assertEquals(testArray.size(), atlas.buildList.size());

		for (int i = 0; i < atlas.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void showBuild_centerWorkerPositionAnd2OpponentsAround_CorrectOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Worker nWorker0=nPlayer.getSpecificWorker(0);
		Worker nWorker1=nPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,2);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(0,0);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		Coordinate nc0=new Coordinate(2,1);nWorker0.setCoordinate(nc0);board.addWorker(nc0,0,Color.ANSI_BLUE);
		Coordinate nc1=new Coordinate(3,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(1,2));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));

		testArray.add(new Coordinate(1,1));
		testArray.add(new Coordinate(3,1));
		testArray.add(new Coordinate(1,2));
		testArray.add(new Coordinate(3,2));
		testArray.add(new Coordinate(1,3));
		testArray.add(new Coordinate(2,3));

		atlas.showBuild(cWorker0);
		assertEquals(testArray.size(), atlas.buildList.size());

		for (int i = 0; i < atlas.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void atlasBuild_correctCoordinatesInInput_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		atlas.atlasBuild(c);
		assertTrue(board.getBlock(c).getIsDome());
	}

	@Test
	public void performBuild_atlasPowerNotActivated_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		/*atlas.setState(StateNumber.building);
		s.setType(StateNumber.readyToBuild);
		LightBlock lb=new LightBlock(c);
		lb.setLevel(0);
		s.getAvailableCells2().add(0,lb);*/
		atlas.level=0;
		int l= atlas.level+1;
		atlas.performBuild(c);
		assertFalse(board.getBlock(c).getIsDome());
		assertEquals(l,board.getBlock(c).getLevel());
	}

	@Test
	public void performBuild_atlasPowerActivated_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		board.getBlock(c).setLevel(0);
		atlas.level=3;
		int l=board.getBlock(c).getLevel();
		atlas.performBuild(c);
		assertTrue(board.getBlock(c).getIsDome());
		assertEquals(l,board.getBlock(c).getLevel());
	}

	@Test
	public void turnHandler_real_stateIsSettingMovement_shouldBeMoving(){
		atlas.setState(StateNumber.settingMovement);
		Service s = new Service(StateNumber.settingMovement,"codice","whatshouldido");
		atlas.turnHandler_real(s);
		assertEquals(StateNumber.moving, atlas.getState());
	}

	@Test
	public void turnHandler_real_stateIsMoving_shouldBeBuilding(){
		atlas.setState(StateNumber.moving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		atlas.turnHandler_real(s);
		assertEquals(StateNumber.building, atlas.getState());
	}

	@Test
	public void turnHandler_real_stateIsSettingBuilding_shouldBeBuilding(){
		atlas.setState(StateNumber.settingBuilding);
		Service s = new Service(StateNumber.settingBuilding,"codice","whatshouldido");
		atlas.setMovedWorker(cPlayer.getSpecificWorker(0));
		atlas.turnHandler_real(s);
		assertEquals(StateNumber.building, atlas.getState());
	}

	@Test
	public void turnHandler_real_stateIsBuilding_shouldBeSettingMovement(){
		atlas.setState(StateNumber.building);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		atlas.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, atlas.getState());
	}

	@Test
	public void turnHandler_real_stateIsOnlyMoving_shouldBeOnlyMoving(){
		atlas.setState(StateNumber.onlymoving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		atlas.turnHandler_real(s);
		assertEquals(StateNumber.onlymoving, atlas.getState());
	}

	@Test
	public void turnHandler_real_stateIsOnlyBuilding_shouldBeOnlyBuilding(){
		atlas.setState(StateNumber.onlybuilding);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		atlas.turnHandler_real(s);
		assertEquals(StateNumber.onlybuilding, atlas.getState());
	}

	@Test
	public void turnHandler_real_stateIsOnlyNextTurn_shouldBeSettingMovement(){
		atlas.setState(StateNumber.onlynextturn);
		Service s = new Service(StateNumber.onlynextturn,"codice","whatshouldido");
		atlas.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, atlas.getState());
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

		atlas.showMoves(cWorker0);

		assertEquals(testArray.size(), atlas.moveList.size());

		for (int i = 0; i < atlas.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.moveList.get(i).getC().getY());
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

		atlas.showMoves(cWorker0);

		assertEquals(testArray.size(), atlas.moveList.size());

		for (int i = 0; i < atlas.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.moveList.get(i).getC().getY());
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

		atlas.showMoves(cWorker0);

		assertEquals(testArray.size(), atlas.moveList.size());

		for (int i = 0; i < atlas.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.moveList.get(i).getC().getY());
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

		atlas.showMoves(cWorker0);

		assertEquals(testArray.size(), atlas.moveList.size());

		for (int i = 0; i < atlas.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.moveList.get(i).getC().getY());
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

		atlas.showMoves(cWorker0);

		assertEquals(testArray.size(), atlas.moveList.size());

		for (int i = 0; i < atlas.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.moveList.get(i).getC().getY());
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

		atlas.showMoves(cWorker0);

		assertEquals(testArray.size(), atlas.moveList.size());

		for (int i = 0; i < atlas.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.moveList.get(i).getC().getY());
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

		atlas.showMoves(cWorker0);

		assertEquals(testArray.size(), atlas.moveList.size());

		for (int i = 0; i < atlas.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.moveList.get(i).getC().getY());
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

		atlas.setMoves();

		assertEquals(testArray.size(), atlas.moveList.size());

		for (int i = 0; i < atlas.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.moveList.get(i).getC().getY());
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

		atlas.setMoves();

		assertEquals(testArray.size(), atlas.moveList.size());

		for (int i = 0; i < atlas.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.moveList.get(i).getC().getY());
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

		atlas.setMoves();

		assertEquals(testArray.size(), atlas.moveList.size());

		for (int i = 0; i < atlas.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.moveList.get(i).getC().getY());
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

		atlas.setMoves();

		assertEquals(testArray.size(), atlas.moveList.size());

		for (int i = 0; i < atlas.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), atlas.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), atlas.moveList.get(i).getC().getY());
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
		atlas.performMove(cWorker0,c,0);
		assertEquals(c.getX(),cWorker0.getCoordinate().getX());
		assertEquals(c.getY(),cWorker0.getCoordinate().getY());
		assertTrue(board.getBlock(c).getOccupied());
		assertFalse(board.getBlock(cc0).getOccupied());
	}

	@Test
	public void nextTurn_correctInput_shouldSetToNextTurn(){
		atlas.nextTurn();
		assertEquals(StateNumber.settingMovement, atlas.getState());
		assertEquals(StateNumber.nextTurn, rules.service.getType());
		assertEquals(0, atlas.service.getAvailableCells().size());
		assertEquals(0, atlas.service.getAvailableCells2().size());
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

		atlas.setHasWon(false);
		board.getBlock(cc0).setLevel(0);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(0);
		atlas.showMoves(cWorker0);
		atlas.performMove(cWorker0,c,0);
		atlas.CheckIfWin();

		assertFalse(atlas.getHasWon());
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

		atlas.setHasWon(false);
		board.getBlock(cc0).setLevel(0);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(1);
		atlas.showMoves(cWorker0);
		atlas.performMove(cWorker0,c,0);
		atlas.CheckIfWin();

		assertFalse(atlas.getHasWon());
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

		atlas.setHasWon(false);
		board.getBlock(cc0).setLevel(1);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(2);
		atlas.showMoves(cWorker0);
		atlas.performMove(cWorker0,c,0);
		atlas.CheckIfWin();

		assertFalse(atlas.getHasWon());
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

		atlas.setHasWon(false);
		board.getBlock(cc0).setLevel(3);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(3);
		atlas.showMoves(cWorker0);
		atlas.performMove(cWorker0,c,0);
		atlas.CheckIfWin();

		assertFalse(atlas.getHasWon());
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

		atlas.setHasWon(false);
		board.getBlock(cc0).setLevel(2);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(3);
		atlas.showMoves(cWorker0);
		atlas.performMove(cWorker0,c,0);
		atlas.CheckIfWin();

		assertTrue(atlas.getHasWon());
		assertEquals(StateNumber.haswon, rules.service.getType());
		assertEquals(0, atlas.service.getAvailableCells().size());
		assertEquals(0, atlas.service.getAvailableCells2().size());
	}

	@Test
	public void lose_correctInput_correctOutput(){
		atlas.lose();

		assertEquals(StateNumber.sendhaslose, atlas.getState());
		assertEquals(StateNumber.haswon, rules.service.getType());
		assertEquals(0, atlas.service.getAvailableCells().size());
		assertEquals(0, atlas.service.getAvailableCells2().size());
	}

	@Test
	public void invertPlayer_correctInput_correctOutput(){
		Player tPlayer=new Player("tplayer",0,null,Color.ANSI_YELLOW);
		rules.thirdPlayer=tPlayer;

		atlas.invertPlayer();

		assertEquals(Color.ANSI_YELLOW, rules.nextPlayer.getColor());
		assertEquals(Color.ANSI_BLUE, rules.thirdPlayer.getColor());
	}

	@Test
	public void CheckList_notEmptyList_shouldDoNothing(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		testArray.add(new LightBlock(new Coordinate(0,0)));
		atlas.setIsalive(true);
		atlas.CheckList(testArray);

		assertTrue(atlas.getIsalive());
	}

	@Test
	public void CheckList_emptyListAndOtherPlayerIsNotAlive_stateShouldBeHasWon(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		atlas.setIsalive(true);
		tPlayer.getAbstractRule().setIsalive(false);
		atlas.CheckList(testArray);


		assertFalse(atlas.getIsalive());
		assertEquals(StateNumber.haswon, rules.service.getType());
	}

	@Test
	public void CheckList_emptyListAndOtherPlayersAreAlive_stateShouldBeNextTurn(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		atlas.setIsalive(true);
		atlas.CheckList(testArray);


		assertFalse(atlas.getIsalive());
		assertEquals(StateNumber.nextTurn, rules.service.getType());
	}

	@Test
	public void Win_correctInput_correctOutput(){
		atlas.Win();

		assertEquals(StateNumber.haswon, atlas.getState());
		assertTrue(atlas.getHasWon());
		assertEquals(StateNumber.haswon, rules.service.getType());
		assertEquals(0, atlas.service.getAvailableCells().size());
		assertEquals(0, atlas.service.getAvailableCells2().size());
	}

	@Test
	public void getMoveList_correctInput_correctOutput(){
		LightBlock lb1=new LightBlock(new Coordinate(0,0));
		atlas.moveList.add(lb1);
		assertEquals(0, atlas.getMoveList().get(0).getC().getX());
		assertEquals(0, atlas.getMoveList().get(0).getC().getY());
	}

	@Test
	public void getBuildList_correctInput_correctOutput(){
		LightBlock lb1=new LightBlock(new Coordinate(0,0));
		atlas.buildList.add(lb1);
		assertEquals(0, atlas.getBuildList().get(0).getC().getX());
		assertEquals(0, atlas.getBuildList().get(0).getC().getY());
	}

	@Test
	public void getTurn_correctInput_correctOutput(){
		atlas.turn=1;
		assertEquals(1, atlas.getTurn());
	}

	@Test
	public void getCurrentPlayer_correctInput_correctOutput(){
		assertEquals(cPlayer, atlas.getCurrentPlayer());
	}

	@Test
	public void getNextPlayer_correctInput_correctOutput(){
		assertEquals(nPlayer, atlas.getNextPlayer());
	}

	@Test
	public void getThirdPlayer_correctInput_correctOutput(){
		assertEquals(tPlayer, atlas.getThirdPlayer());
	}

	@Test
	public void turnHandler_stateIsSettingMovement_shouldBeMoving(){
		atlas.setState(StateNumber.settingMovement);
		Service s = new Service(StateNumber.settingMovement,"codice","whatshouldido");
		atlas.turnHandler_real(s);
		assertEquals(StateNumber.moving, atlas.getState());
	}

	@Test
	public void turnHandler_stateIsMoving_shouldBeBuilding(){
		atlas.setState(StateNumber.moving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		atlas.turnHandler_real(s);
		assertEquals(StateNumber.building, atlas.getState());
	}

	@Test
	public void turnHandler_stateIsSettingBuilding_shouldBeBuilding(){
		atlas.setState(StateNumber.settingBuilding);
		Service s = new Service(StateNumber.settingBuilding,"codice","whatshouldido");
		atlas.setMovedWorker(cPlayer.getSpecificWorker(0));
		atlas.turnHandler_real(s);
		assertEquals(StateNumber.building, atlas.getState());
	}

	@Test
	public void turnHandler_stateIsBuilding_shouldBeSettingMovement(){
		atlas.setState(StateNumber.building);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		atlas.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, atlas.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyMoving_shouldBeOnlyMoving(){
		atlas.setState(StateNumber.onlymoving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		atlas.turnHandler_real(s);
		assertEquals(StateNumber.onlymoving, atlas.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyBuilding_shouldBeOnlyBuilding(){
		atlas.setState(StateNumber.onlybuilding);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		atlas.turnHandler_real(s);
		assertEquals(StateNumber.onlybuilding, atlas.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyNextTurn_shouldBeSettingMovement(){
		atlas.setState(StateNumber.onlynextturn);
		Service s = new Service(StateNumber.onlynextturn,"codice","whatshouldido");
		atlas.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, atlas.getState());
	}

	@Test
	public void getLevelPrecPosition_correctInput_correctOutput(){
		rules.levelPrecPosition=1;
		assertEquals(1, atlas.getLevelPrecPosition());
	}

	@Test
	public void setLevelPrecPosition_correctInput_correctOutput(){
		atlas.setLevelPrecPosition(1);
		assertEquals(1, atlas.getLevelPrecPosition());
	}

	@Test
	public void getMovedWorker_correctInput_correctOutput(){
		rules.movedWorker=cPlayer.getSpecificWorker(0);
		assertEquals(cPlayer.getSpecificWorker(0), atlas.getMovedWorker());
	}

	@Test
	public void getStringParticular_correctInput_correctOutput(){
		String string= "testString";
		atlas.stringParticular=string;
		assertEquals(string,atlas.getStringParticular());
	}

	@Test
	public void setStringParticular_correctInput_correctOutput(){
		String string= "testString";
		atlas.setStringParticular(string);
		assertEquals(string,atlas.getStringParticular());
	}

	@Test
	public void getWorkerToSend_correctInput_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		LightBlock lb0=new LightBlock(cc0);
		LightBlock lb1=new LightBlock(cc1);
		atlas.workerToSend.add(lb0);
		atlas.workerToSend.add(lb1);

		ArrayList<LightBlock> testArray = new ArrayList<>();
		testArray.add(lb0);
		testArray.add(lb1);
		ArrayList<LightBlock> wTs=atlas.getWorkerToSend();
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

		atlas.setWorkerToSend(testArray);

		assertEquals(testArray.size(),atlas.getWorkerToSend().size());

		for (int i = 0; i < atlas.getWorkerToSend().size(); i++) {
			assertEquals(testArray.get(i),atlas.getWorkerToSend().get(i));
		}
	}
}