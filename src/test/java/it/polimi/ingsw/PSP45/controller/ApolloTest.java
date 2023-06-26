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
public class ApolloTest {
	Apollo apollo = null;
	Rules rules = null;
	Player cPlayer = null;
	Player nPlayer = null;
	Player tPlayer=null;
	Board board = null;

	@Before
	public void setUp() {
		board = new Board();
		cPlayer = new Player("nome", 0, GodName.apollo, Color.ANSI_RED);
		nPlayer = new Player("nome", 0, GodName.minotaur, Color.ANSI_BLUE);
		tPlayer=new Player("nome",0,GodName.apollo,Color.ANSI_PURPLE);
		rules = new Rules(1, board, cPlayer, nPlayer, tPlayer);
		apollo = new Apollo(rules);
		cPlayer.setAbstractRule(apollo);
	}

	@After
	public void tearDown() {
		apollo.moveList.clear();
		apollo = null;
	}

	@Test
	public void showMoves_workerInBlockAround_correctOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(0, 0);cWorker0.setCoordinate(cc0);board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(1, 0);nWorker0.setCoordinate(nc0);board.addWorker(nc0, 0, Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray = new ArrayList<>();

		testArray.add(new Coordinate(1, 0));
		testArray.add(new Coordinate(0, 1));
		testArray.add(new Coordinate(1, 1));
		apollo.showMoves(cWorker0);

		assertEquals(testArray.size(), apollo.moveList.size());

		for (int i = 0; i < apollo.moveList.size(); i++) {
			assertEquals(apollo.moveList.get(i).getC().getX(), testArray.get(i).getX());
			assertEquals(apollo.moveList.get(i).getC().getY(), testArray.get(i).getY());
		}
	}

	@Test
	public void showMoves_apolloInCenterWorkerInBlockAround_correctOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(2, 2);
		cWorker0.setCoordinate(cc0);
		board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(1, 2);
		nWorker0.setCoordinate(nc0);
		board.addWorker(nc0, 0, Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray = new ArrayList<>();
		testArray.add(new Coordinate(1, 2));
		testArray.add(new Coordinate(1, 1));
		testArray.add(new Coordinate(2, 1));
		testArray.add(new Coordinate(3, 1));
		testArray.add(new Coordinate(3, 2));
		testArray.add(new Coordinate(1, 3));
		testArray.add(new Coordinate(2, 3));
		testArray.add(new Coordinate(3, 3));

		apollo.showMoves(cWorker0);

		assertEquals(testArray.size(), apollo.moveList.size());

		for (int i = 0; i < apollo.moveList.size(); i++) {
			assertEquals(apollo.moveList.get(i).getC().getX(), testArray.get(i).getX());
			assertEquals(apollo.moveList.get(i).getC().getY(), testArray.get(i).getY());
		}

	}

	@Test
	public void showMoves_apolloInCenter2WorkerInBlockAround_correctOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(2, 2);
		cWorker0.setCoordinate(cc0);
		board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(1, 2);
		nWorker0.setCoordinate(nc0);
		board.addWorker(nc0, 0, Color.ANSI_BLUE);
		Coordinate nc1 = new Coordinate(2, 3);
		nWorker1.setCoordinate(nc1);
		board.addWorker(nc1, 1, Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray = new ArrayList<>();
		testArray.add(new Coordinate(1, 2));
		testArray.add(new Coordinate(2, 3));
		testArray.add(new Coordinate(1, 1));
		testArray.add(new Coordinate(2, 1));
		testArray.add(new Coordinate(3, 1));
		testArray.add(new Coordinate(3, 2));
		testArray.add(new Coordinate(1, 3));
		testArray.add(new Coordinate(3, 3));
		apollo.showMoves(cWorker0);

		assertEquals(testArray.size(), apollo.moveList.size());

		for (int i = 0; i < apollo.moveList.size(); i++) {
			assertEquals(apollo.moveList.get(i).getC().getX(), testArray.get(i).getX());
			assertEquals(apollo.moveList.get(i).getC().getY(), testArray.get(i).getY());
		}

	}

	@Test
	public void showMoves_apolloInCenterWorkerInBlockAroundDomeAround_correctOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(2, 2);
		cWorker0.setCoordinate(cc0);
		board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(1, 2);
		nWorker0.setCoordinate(nc0);
		board.addWorker(nc0, 0, Color.ANSI_BLUE);
		//Coordinate nc1=new Coordinate(2,3);nWorker1.setCoordinate(nc1);board.addWorker(nc1,1,Color.ANSI_BLUE);

		board.getBlock(new Coordinate(3, 3)).setDome();

		ArrayList<Coordinate> testArray = new ArrayList<>();
		testArray.add(new Coordinate(1, 2));
		testArray.add(new Coordinate(1, 1));
		testArray.add(new Coordinate(2, 1));
		testArray.add(new Coordinate(3, 1));
		testArray.add(new Coordinate(3, 2));
		testArray.add(new Coordinate(1, 3));
		testArray.add(new Coordinate(2, 3));
		testArray.add(new Coordinate(3, 3));
		apollo.showMoves(cWorker0);

		assertEquals(testArray.size() - 1, apollo.moveList.size());

		for (int i = 0; i < apollo.moveList.size(); i++) {
			assertEquals(apollo.moveList.get(i).getC().getX(), testArray.get(i).getX());
			assertEquals(apollo.moveList.get(i).getC().getY(), testArray.get(i).getY());
		}

	}

	@Test
	public void showMoves_workerNotInBlockAround_correctOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(0, 0);
		cWorker0.setCoordinate(cc0);
		board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate cc1 = new Coordinate(3, 3);
		cWorker1.setCoordinate(cc1);
		board.addWorker(cc1, 1, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(4, 4);
		nWorker0.setCoordinate(nc0);
		board.addWorker(nc0, 0, Color.ANSI_BLUE);
		Coordinate nc1 = new Coordinate(4, 3);
		nWorker1.setCoordinate(nc1);
		board.addWorker(nc1, 1, Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray = new ArrayList<>();

		testArray.add(new Coordinate(1, 0));
		testArray.add(new Coordinate(0, 1));
		testArray.add(new Coordinate(1, 1));
		apollo.showMoves(cWorker0);

		assertEquals(testArray.size(), apollo.moveList.size());

		for (int i = 0; i < apollo.moveList.size(); i++) {
			assertEquals(apollo.moveList.get(i).getC().getX(), testArray.get(i).getX());
			assertEquals(apollo.moveList.get(i).getC().getY(), testArray.get(i).getY());
		}
	}

	@Test
	public void showMoves_samePlayersWorkerInBlockAround_correctOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(0, 0);
		cWorker0.setCoordinate(cc0);
		board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate cc1 = new Coordinate(1, 1);
		cWorker1.setCoordinate(cc1);
		board.addWorker(cc1, 1, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(4, 4);
		nWorker0.setCoordinate(nc0);
		board.addWorker(nc0, 0, Color.ANSI_BLUE);
		Coordinate nc1 = new Coordinate(4, 3);
		nWorker1.setCoordinate(nc1);
		board.addWorker(nc1, 1, Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray = new ArrayList<>();

		testArray.add(new Coordinate(1, 0));
		testArray.add(new Coordinate(0, 1));

		apollo.showMoves(cWorker0);

		assertEquals(testArray.size(), apollo.moveList.size());

		for (int i = 0; i < apollo.moveList.size(); i++) {
			assertEquals(apollo.moveList.get(i).getC().getX(), testArray.get(i).getX());
			assertEquals(apollo.moveList.get(i).getC().getY(), testArray.get(i).getY());
		}
	}

	@Test
	public void showMoves_levelTooHigh_correctOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(0, 0);
		cWorker0.setCoordinate(cc0);
		board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate cc1 = new Coordinate(3, 3);
		cWorker1.setCoordinate(cc1);
		board.addWorker(cc1, 1, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(4, 4);
		nWorker0.setCoordinate(nc0);
		board.addWorker(nc0, 0, Color.ANSI_BLUE);
		Coordinate nc1 = new Coordinate(4, 3);
		nWorker1.setCoordinate(nc1);
		board.addWorker(nc1, 1, Color.ANSI_BLUE);
		board.getBlock(new Coordinate(0, 1)).setLevel(3);

		ArrayList<Coordinate> testArray = new ArrayList<>();
		testArray.add(new Coordinate(1, 0));
		testArray.add(new Coordinate(1, 1));
		apollo.showMoves(cWorker0);
		assertEquals(testArray.size(), apollo.moveList.size());

		for (int i = 0; i < apollo.moveList.size(); i++) {
			assertEquals(apollo.moveList.get(i).getC().getX(), testArray.get(i).getX());
			assertEquals(apollo.moveList.get(i).getC().getY(), testArray.get(i).getY());
		}
	}


	@Test
	public void performMove_finalCoordinateFree_correctOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(0, 0);
		cWorker0.setCoordinate(cc0);
		board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate cc1 = new Coordinate(2, 2);
		cWorker1.setCoordinate(cc1);
		board.addWorker(cc1, 1, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(4, 4);
		nWorker0.setCoordinate(nc0);
		board.addWorker(nc0, 0, Color.ANSI_BLUE);
		Coordinate nc1 = new Coordinate(4, 3);
		nWorker1.setCoordinate(nc1);
		board.addWorker(nc1, 1, Color.ANSI_BLUE);

		apollo.performMove(cWorker0, new Coordinate(1, 0), 0);

		assertEquals(1, cWorker0.getCoordinate().getX());
		assertEquals(0, cWorker0.getCoordinate().getY());
		assertEquals(cc1.getX(), cWorker1.getCoordinate().getX());
		assertEquals(cc1.getY(), cWorker1.getCoordinate().getY());
		assertEquals(nc0.getX(), nWorker0.getCoordinate().getX());
		assertEquals(nc0.getY(), nWorker0.getCoordinate().getY());
		assertEquals(nc1.getX(), nWorker1.getCoordinate().getX());
		assertEquals(nc1.getY(), nWorker1.getCoordinate().getY());
	}

	@Test
	public void performMove_finalCoordinateOccupied_correctOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(0, 0);
		cWorker0.setCoordinate(cc0);
		board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate cc1 = new Coordinate(2, 2);
		cWorker1.setCoordinate(cc1);
		board.addWorker(cc1, 1, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(1, 0);
		nWorker0.setCoordinate(nc0);
		board.addWorker(nc0, 0, Color.ANSI_BLUE);
		Coordinate nc1 = new Coordinate(4, 3);
		nWorker1.setCoordinate(nc1);
		board.addWorker(nc1, 1, Color.ANSI_BLUE);


		apollo.performMove(cWorker0, new Coordinate(1, 0), 0);

		assertEquals(1, cWorker0.getCoordinate().getX());
		assertEquals(0, cWorker0.getCoordinate().getY());
		assertEquals(cc1.getX(), cWorker1.getCoordinate().getX());
		assertEquals(cc1.getY(), cWorker1.getCoordinate().getY());
		assertEquals(0, nWorker0.getCoordinate().getX());
		assertEquals(0, nWorker0.getCoordinate().getY());
		assertEquals(nc1.getX(), nWorker1.getCoordinate().getX());
		assertEquals(nc1.getY(), nWorker1.getCoordinate().getY());
	}

	@Test
	public void setMove_worker1InCenterWorker2InDifferentCorner_correctOutput() {
		Worker cWorker0 = cPlayer.getSpecificWorker(0);
		Worker cWorker1 = cPlayer.getSpecificWorker(1);
		Worker nWorker0 = nPlayer.getSpecificWorker(0);
		Worker nWorker1 = nPlayer.getSpecificWorker(1);
		Coordinate cc0 = new Coordinate(2, 2);
		cWorker0.setCoordinate(cc0);
		board.addWorker(cc0, 0, Color.ANSI_RED);
		Coordinate cc1 = new Coordinate(4, 4);
		cWorker1.setCoordinate(cc1);
		board.addWorker(cc1, 1, Color.ANSI_RED);
		Coordinate nc0 = new Coordinate(2, 2);
		nWorker0.setCoordinate(nc0);
		board.addWorker(nc0, 0, Color.ANSI_BLUE);
		Coordinate nc1 = new Coordinate(0, 3);
		nWorker1.setCoordinate(nc1);
		board.addWorker(nc1, 1, Color.ANSI_BLUE);

		ArrayList<Coordinate> testArray = new ArrayList<>();
		testArray.add(new Coordinate(1, 1));
		testArray.add(new Coordinate(2, 1));
		testArray.add(new Coordinate(3, 1));
		testArray.add(new Coordinate(1, 2));
		testArray.add(new Coordinate(3, 2));
		testArray.add(new Coordinate(1, 3));
		testArray.add(new Coordinate(2, 3));
		testArray.add(new Coordinate(3, 3));
		testArray.add(new Coordinate(3, 3));
		testArray.add(new Coordinate(4, 3));
		testArray.add(new Coordinate(3, 4));

		apollo.setMoves();

		assertEquals(testArray.size(), apollo.moveList.size());

		for (int i = 0; i < apollo.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), apollo.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), apollo.moveList.get(i).getC().getY());
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

		apollo.setMoves();

		assertEquals(testArray.size(), apollo.moveList.size());

		for (int i = 0; i < apollo.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), apollo.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), apollo.moveList.get(i).getC().getY());
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

		apollo.setMoves();

		assertEquals(testArray.size(), apollo.moveList.size());

		for (int i = 0; i < apollo.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), apollo.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), apollo.moveList.get(i).getC().getY());
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

		apollo.setMoves();

		assertEquals(testArray.size(), apollo.moveList.size());

		for (int i = 0; i < apollo.moveList.size(); i++) {
			assertEquals(testArray.get(i).getX(), apollo.moveList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), apollo.moveList.get(i).getC().getY());
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
		rules.movedWorker=cWorker0;

		ArrayList<Coordinate> testArray=new ArrayList<>();

		testArray.add(new Coordinate(1,0));
		testArray.add(new Coordinate(0,1));
		testArray.add(new Coordinate(1,1));

		apollo.setBuild();

		assertEquals(testArray.size(), apollo.buildList.size());

		for (int i = 0; i < apollo.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), apollo.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), apollo.buildList.get(i).getC().getY());
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

		apollo.setBuild();

		assertEquals(testArray.size(), apollo.buildList.size());

		for (int i = 0; i < apollo.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), apollo.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), apollo.buildList.get(i).getC().getY());
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

		apollo.setBuild();

		assertEquals(testArray.size(), apollo.buildList.size());

		for (int i = 0; i < apollo.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), apollo.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), apollo.buildList.get(i).getC().getY());
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

		apollo.setBuild();

		assertEquals(testArray.size(), apollo.buildList.size());
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

		apollo.setBuild();

		assertEquals(testArray.size(), apollo.buildList.size());

		for (int i = 0; i < apollo.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), apollo.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), apollo.buildList.get(i).getC().getY());
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

		apollo.setBuild();

		assertEquals(testArray.size(), apollo.buildList.size());

		for (int i = 0; i < apollo.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), apollo.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), apollo.buildList.get(i).getC().getY());
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

		apollo.setBuild();

		assertEquals(testArray.size(), apollo.buildList.size());

		for (int i = 0; i < apollo.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), apollo.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), apollo.buildList.get(i).getC().getY());
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

		apollo.showBuild(cWorker0);

		assertEquals(testArray.size(), apollo.buildList.size());

		for (int i = 0; i < apollo.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), apollo.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), apollo.buildList.get(i).getC().getY());
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

		apollo.showBuild(cWorker0);

		assertEquals(testArray.size(), apollo.buildList.size());

		for (int i = 0; i < apollo.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), apollo.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), apollo.buildList.get(i).getC().getY());
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

		apollo.showBuild(cWorker0);

		assertEquals(testArray.size(), apollo.buildList.size());

		for (int i = 0; i < apollo.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), apollo.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), apollo.buildList.get(i).getC().getY());
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

		apollo.showBuild(cWorker0);

		assertEquals(testArray.size(), apollo.buildList.size());

		for (int i = 0; i < apollo.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), apollo.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), apollo.buildList.get(i).getC().getY());
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

		apollo.showBuild(cWorker0);

		assertEquals(testArray.size(), apollo.buildList.size());

		for (int i = 0; i < apollo.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), apollo.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), apollo.buildList.get(i).getC().getY());
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

		apollo.showBuild(cWorker0);

		assertEquals(testArray.size(), apollo.buildList.size());

		for (int i = 0; i < apollo.buildList.size(); i++) {
			assertEquals(testArray.get(i).getX(), apollo.buildList.get(i).getC().getX());
			assertEquals(testArray.get(i).getY(), apollo.buildList.get(i).getC().getY());
		}
	}

	@Test
	public void performBuild_CoordinateAtLevel0_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=0;
		board.getBlock(c).setLevel(level);
		apollo.performBuild(c);
		assertEquals(level+1,board.getBlock(c).getLevel());
	}

	@Test
	public void performBuild_CoordinateAtLevel1_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=1;
		board.getBlock(c).setLevel(level);
		apollo.performBuild(c);
		assertEquals(level+1,board.getBlock(c).getLevel());
	}

	@Test
	public void performBuild_CoordinateAtLevel2_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=2;
		board.getBlock(c).setLevel(level);
		apollo.performBuild(c);
		assertEquals(level+1,board.getBlock(c).getLevel());
	}

	@Test
	public void performBuild_CoordinateAtLevel3_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=3;
		board.getBlock(c).setLevel(level);
		apollo.performBuild(c);
		assertEquals(level+1,board.getBlock(c).getLevel());
		assertTrue(board.getBlock(c).getIsDome());
	}

	@Test
	public void performBuild_CoordinateAtLevel4_correctOutput(){
		Coordinate c=new Coordinate(0,0);
		int level=4;
		board.getBlock(c).setLevel(level);
		apollo.performBuild(c);
		assertEquals(level,board.getBlock(c).getLevel());
	}

	@Test
	public void nextTurn_correctInput_shouldSetToNextTurn(){
		apollo.nextTurn();
		assertEquals(StateNumber.settingMovement, apollo.getState());
		assertEquals(StateNumber.nextTurn, rules.service.getType());
		assertEquals(0, apollo.service.getAvailableCells().size());
		assertEquals(0, apollo.service.getAvailableCells2().size());
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

		apollo.setHasWon(false);
		board.getBlock(cc0).setLevel(0);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(0);
		apollo.showMoves(cWorker0);
		apollo.performMove(cWorker0,c,0);
		apollo.CheckIfWin();

		assertFalse(apollo.getHasWon());
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

		apollo.setHasWon(false);
		board.getBlock(cc0).setLevel(0);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(1);
		apollo.showMoves(cWorker0);
		apollo.performMove(cWorker0,c,0);
		apollo.CheckIfWin();

		assertFalse(apollo.getHasWon());
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

		apollo.setHasWon(false);
		board.getBlock(cc0).setLevel(1);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(2);
		apollo.showMoves(cWorker0);
		apollo.performMove(cWorker0,c,0);
		apollo.CheckIfWin();

		assertFalse(apollo.getHasWon());
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

		apollo.setHasWon(false);
		board.getBlock(cc0).setLevel(3);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(3);
		apollo.showMoves(cWorker0);
		apollo.performMove(cWorker0,c,0);
		apollo.CheckIfWin();

		assertFalse(apollo.getHasWon());
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

		apollo.setHasWon(false);
		board.getBlock(cc0).setLevel(2);
		Coordinate c=new Coordinate(1,0);
		board.getBlock(c).setLevel(3);
		apollo.showMoves(cWorker0);
		apollo.performMove(cWorker0,c,0);
		apollo.CheckIfWin();

		assertTrue(apollo.getHasWon());
		assertEquals(StateNumber.haswon, rules.service.getType());
		assertEquals(0, apollo.service.getAvailableCells().size());
		assertEquals(0, apollo.service.getAvailableCells2().size());
	}

	@Test
	public void lose_correctInput_correctOutput(){
		apollo.lose();

		assertEquals(StateNumber.sendhaslose, apollo.getState());
		assertEquals(StateNumber.haswon, rules.service.getType());
		assertEquals(0, rules.service.getAvailableCells().size());
		assertEquals(0, rules.service.getAvailableCells2().size());
	}

	@Test
	public void invertPlayer_correctInput_correctOutput(){
		Player tPlayer=new Player("tplayer",0,null,Color.ANSI_YELLOW);
		rules.thirdPlayer=tPlayer;

		apollo.invertPlayer();

		assertEquals(Color.ANSI_YELLOW, rules.nextPlayer.getColor());
		assertEquals(Color.ANSI_BLUE, rules.thirdPlayer.getColor());
	}

	@Test
	public void CheckList_notEmptyList_shouldDoNothing(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		testArray.add(new LightBlock(new Coordinate(0,0)));
		apollo.setIsalive(true);
		apollo.CheckList(testArray);

		assertTrue(apollo.getIsalive());
	}

	@Test
	public void CheckList_emptyListAndOtherPlayerIsNotAlive_stateShouldBeHasWon(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		apollo.setIsalive(true);
		tPlayer.getAbstractRule().setIsalive(false);
		apollo.CheckList(testArray);


		assertFalse(apollo.getIsalive());
		assertEquals(StateNumber.haswon, rules.service.getType());
	}

	@Test
	public void CheckList_emptyListAndOtherPlayersAreAlive_stateShouldBeNextTurn(){
		ArrayList<LightBlock> testArray=new ArrayList<>();
		apollo.setIsalive(true);
		apollo.CheckList(testArray);


		assertFalse(apollo.getIsalive());
		assertEquals(StateNumber.nextTurn, rules.service.getType());
	}

	@Test
	public void Win_correctInput_correctOutput(){
		apollo.Win();

		assertEquals(StateNumber.haswon, apollo.getState());
		assertTrue(apollo.getHasWon());
		assertEquals(StateNumber.haswon, rules.service.getType());
		assertEquals(0, rules.service.getAvailableCells().size());
		assertEquals(0, rules.service.getAvailableCells2().size());
	}

	@Test
	public void getMoveList_correctInput_correctOutput(){
		LightBlock lb1=new LightBlock(new Coordinate(0,0));
		apollo.moveList.add(lb1);
		assertEquals(0, apollo.getMoveList().get(0).getC().getX());
		assertEquals(0, apollo.getMoveList().get(0).getC().getY());
	}

	@Test
	public void getBuildList_correctInput_correctOutput(){
		LightBlock lb1=new LightBlock(new Coordinate(0,0));
		apollo.buildList.add(lb1);
		assertEquals(0, apollo.getBuildList().get(0).getC().getX());
		assertEquals(0, apollo.getBuildList().get(0).getC().getY());
	}

	@Test
	public void getTurn_correctInput_correctOutput(){
		apollo.turn=1;
		assertEquals(1, apollo.getTurn());
	}

	@Test
	public void getCurrentPlayer_correctInput_correctOutput(){
		assertEquals(cPlayer, apollo.getCurrentPlayer());
	}

	@Test
	public void getNextPlayer_correctInput_correctOutput(){
		assertEquals(nPlayer, apollo.getNextPlayer());
	}

	@Test
	public void getThirdPlayer_correctInput_correctOutput(){
		assertEquals(tPlayer, apollo.getThirdPlayer());
	}

	@Test
	public void turnHandler_stateIsSettingMovement_shouldBeMoving(){
		apollo.setState(StateNumber.settingMovement);
		Service s = new Service(StateNumber.settingMovement,"codice","whatshouldido");
		apollo.turnHandler_real(s);
		assertEquals(StateNumber.moving, apollo.getState());
	}

	@Test
	public void turnHandler_stateIsMoving_shouldBeBuilding(){
		apollo.setState(StateNumber.moving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		apollo.turnHandler_real(s);
		assertEquals(StateNumber.building, apollo.getState());
	}

	@Test
	public void turnHandler_stateIsSettingBuilding_shouldBeBuilding(){
		apollo.setState(StateNumber.settingBuilding);
		Service s = new Service(StateNumber.settingBuilding,"codice","whatshouldido");
		apollo.setMovedWorker(cPlayer.getSpecificWorker(0));
		apollo.turnHandler_real(s);
		assertEquals(StateNumber.building, apollo.getState());
	}

	@Test
	public void turnHandler_stateIsBuilding_shouldBeSettingMovement(){
		apollo.setState(StateNumber.building);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		apollo.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, apollo.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyMoving_shouldBeOnlyMoving(){
		apollo.setState(StateNumber.onlymoving);
		Service s = new Service(StateNumber.readyToMove,"codice","whatshouldido");
		s.getAvailableCells().add(new LightBlock(new Coordinate(0,0)));
		apollo.turnHandler_real(s);
		assertEquals(StateNumber.onlymoving, apollo.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyBuilding_shouldBeOnlyBuilding(){
		apollo.setState(StateNumber.onlybuilding);
		Service s = new Service(StateNumber.readyToBuild,"codice","whatshouldido");
		s.getAvailableCells2().add(new LightBlock(new Coordinate(0,0)));
		apollo.turnHandler_real(s);
		assertEquals(StateNumber.onlybuilding, apollo.getState());
	}

	@Test
	public void turnHandler_stateIsOnlyNextTurn_shouldBeSettingMovement(){
		apollo.setState(StateNumber.onlynextturn);
		Service s = new Service(StateNumber.onlynextturn,"codice","whatshouldido");
		apollo.turnHandler_real(s);
		assertEquals(StateNumber.settingMovement, apollo.getState());
	}

	@Test
	public void getLevelPrecPosition_correctInput_correctOutput(){
		rules.levelPrecPosition=1;
		assertEquals(1, apollo.getLevelPrecPosition());
	}

	@Test
	public void setLevelPrecPosition_correctInput_correctOutput(){
		apollo.setLevelPrecPosition(1);
		assertEquals(1, apollo.getLevelPrecPosition());
	}

	@Test
	public void getMovedWorker_correctInput_correctOutput(){
		rules.movedWorker=cPlayer.getSpecificWorker(0);
		assertEquals(cPlayer.getSpecificWorker(0), apollo.getMovedWorker());
	}

	@Test
	public void getStringParticular_correctInput_correctOutput(){
		String string= "testString";
		apollo.stringParticular=string;
		assertEquals(string,apollo.getStringParticular());
	}

	@Test
	public void setStringParticular_correctInput_correctOutput(){
		String string= "testString";
		apollo.setStringParticular(string);
		assertEquals(string,apollo.getStringParticular());
	}

	@Test
	public void getWorkerToSend_correctInput_correctOutput(){
		Worker cWorker0=cPlayer.getSpecificWorker(0);
		Worker cWorker1=cPlayer.getSpecificWorker(1);
		Coordinate cc0=new Coordinate(2,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
		Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);
		LightBlock lb0=new LightBlock(cc0);
		LightBlock lb1=new LightBlock(cc1);
		apollo.workerToSend.add(lb0);
		apollo.workerToSend.add(lb1);

		ArrayList<LightBlock> testArray = new ArrayList<>();
		testArray.add(lb0);
		testArray.add(lb1);
		ArrayList<LightBlock> wTs=apollo.getWorkerToSend();
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

		apollo.setWorkerToSend(testArray);

		assertEquals(testArray.size(),apollo.getWorkerToSend().size());

		for (int i = 0; i < apollo.getWorkerToSend().size(); i++) {
			assertEquals(testArray.get(i),apollo.getWorkerToSend().get(i));
		}
	}
}