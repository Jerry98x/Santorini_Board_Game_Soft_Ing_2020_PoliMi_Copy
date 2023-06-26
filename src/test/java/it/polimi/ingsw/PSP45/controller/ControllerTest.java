package it.polimi.ingsw.PSP45.controller;

import it.polimi.ingsw.PSP45.model.Board;
import it.polimi.ingsw.PSP45.model.Coordinate;
import it.polimi.ingsw.PSP45.model.Match;
import it.polimi.ingsw.PSP45.model.Player;
import it.polimi.ingsw.PSP45.utils.Color;
import it.polimi.ingsw.PSP45.utils.Service;
import it.polimi.ingsw.PSP45.utils.StateNumber;
import it.polimi.ingsw.PSP45.utils.fromviewtocontroll;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

	Board board=null;
	Player player1=null;
	Player player2=null;
	ArrayList<Player> playersList=null;
	Coordinate c1=null;
	Coordinate c2=null;
	Coordinate c3=null;
	Coordinate c4=null;
	ArrayList<Coordinate> cList=null;
	Match match=null;
	Controller controller=null;

	@Before
	public void setUp(){
		board=new Board();
		player1=new Player("nome",0,null, Color.ANSI_PURPLE);
		player2=new Player("nome",0,null, Color.ANSI_BLUE);
		playersList=new ArrayList<>();
		playersList.add(player1);
		playersList.add(player2);
		c1=new Coordinate(0,0);
		c2=new Coordinate(0,4);
		c3=new Coordinate(4,4);
		c4=new Coordinate(2,4);
		cList=new ArrayList<>();
		cList.add(c1);
		cList.add(c2);
		cList.add(c3);
		cList.add(c4);
		match=new Match(board,playersList,cList);
		controller=new Controller(match);
	}

	@After
	public void tearDown(){
		board=null;
		player1=null;
		player2=null;
		playersList=null;
		c1=null;
		c2=null;
		c3=null;
		c4=null;
		cList=null;
		match=null;
		controller=null;
	}

	@Test
	public void Controller_correctInput_correctOutput(){
		assertEquals(c1.getX(),player1.getSpecificWorker(0).getCoordinate().getX());
		assertEquals(c1.getY(),player1.getSpecificWorker(0).getCoordinate().getY());

		assertEquals(c2.getX(),player1.getSpecificWorker(1).getCoordinate().getX());
		assertEquals(c2.getY(),player1.getSpecificWorker(1).getCoordinate().getY());

		assertEquals(c3.getX(),player2.getSpecificWorker(0).getCoordinate().getX());
		assertEquals(c3.getY(),player2.getSpecificWorker(0).getCoordinate().getY());

		assertEquals(c4.getX(),player2.getSpecificWorker(1).getCoordinate().getX());
		assertEquals(c4.getY(),player2.getSpecificWorker(1).getCoordinate().getY());

		assertEquals(0,controller.Contatore);
	}

	@Test
	public void update_stateIsHasWon_correctOutput(){
		Service s =new Service(StateNumber.haswon,"hasWon","test");
		fromviewtocontroll fvtc=new fromviewtocontroll(player1,s);
		controller.Contatore=0;
		controller.update(fvtc);
		assertEquals(1,controller.Contatore);
	}

	@Test
	public void update_stateIsHasWonContatoreIsEqualTurnObjectSize_correctOutput(){
		Service s =new Service(StateNumber.haswon,"hasWon","test");
		fromviewtocontroll fvtc=new fromviewtocontroll(player1,s);
		controller.Contatore=2;
		controller.update(fvtc);
		assertEquals(3,controller.Contatore);
	}

	@Test
	public void update_stateIsGhostWith2Players_correctOutput() {
		Service s = new Service(StateNumber.ghost, "hasWon", "test");
		fromviewtocontroll fvtc = new fromviewtocontroll(player1, s);
		Rules rules = new Rules(1, board, player1, player2, null);
		player1.setAbstractRule(rules);
		controller.update(fvtc);

		assertEquals(Color.ANSI_PURPLE, player1.getColor());
		assertEquals(Color.ANSI_BLUE, player2.getColor());
	}

	@Test
	public void update_stateIsGhostWith3Players_correctOutput(){
		Player player3=new Player("name",0,null,Color.ANSI_YELLOW);
		playersList.add(player3);
		Coordinate c5=new Coordinate(2,2);
		Coordinate c6=new Coordinate(2,3);
		cList.add(c5);
		cList.add(c6);
		Match match1=new Match(board,playersList,cList);
		Controller c1=new Controller(match1);
		Service s =new Service(StateNumber.ghost,"hasWon","test");
		fromviewtocontroll fvtc=new fromviewtocontroll(player1,s);
		Rules rules=new Rules(1,board,player1,player2,player3);
		player1.setAbstractRule(rules);
		Service s1=new Service(StateNumber.settingMovement,"test","test");
		player1.getAbstractRule().service=s1;
		Rules rules1=new Rules(1,board,player2,player3,player1);
		player2.setAbstractRule(rules1);
		Service s2=new Service(StateNumber.settingMovement,"test","test");
		player2.getAbstractRule().service=s2;
		Rules rules2=new Rules(1,board,player3,player1,player2);
		player3.setAbstractRule(rules2);
		Service s3=new Service(StateNumber.settingMovement,"test","test");
		player3.getAbstractRule().service=s3;
		c1.update(fvtc);

		assertEquals(Color.ANSI_PURPLE,player1.getColor());
		assertEquals(Color.ANSI_BLUE,player2.getColor());
		assertEquals(Color.ANSI_YELLOW,player3.getColor());

		assertEquals(0,player2.getAbstractRule().service.getAvailableCells().size());
		assertEquals(0,player2.getAbstractRule().service.getAvailableCells2().size());
		assertEquals(0,player3.getAbstractRule().service.getAvailableCells().size());
		assertEquals(0,player3.getAbstractRule().service.getAvailableCells2().size());
	}

	@Test
	public void update_stateIsExit_correctOutput(){
		Service s =new Service(StateNumber.exit,"hasWon","test");
		fromviewtocontroll fvtc=new fromviewtocontroll(player1,s);
		Rules rules=new Rules(1,board,player1,player2,null);
		player1.setAbstractRule(rules);
		Service s1=new Service(StateNumber.settingMovement,"test","test");
		player1.getAbstractRule().service=s1;
		controller.update(fvtc);
		assertEquals(StateNumber.sendhaslose,player1.getAbstractRule().getState());
		assertEquals(StateNumber.haswon,player1.getAbstractRule().service.getType());
		assertEquals(0,player1.getAbstractRule().service.getAvailableCells().size());
		assertEquals(0,player1.getAbstractRule().service.getAvailableCells2().size());
	}

	@Test
	public void update_stateIsBuilding_correctOutput(){
		Service s =new Service(StateNumber.building,"hasWon","test");
		fromviewtocontroll fvtc=new fromviewtocontroll(player1,s);
		Rules rules=new Rules(1,board,player1,player2,null);
		player1.setAbstractRule(rules);
		controller.update(fvtc);
		assertEquals(StateNumber.moving,player1.getAbstractRule().getState());
	}

	@Test
	public void getMatch_correctInput_correctOutput(){
		assertEquals(controller.match,controller.getMatch());
	}
}