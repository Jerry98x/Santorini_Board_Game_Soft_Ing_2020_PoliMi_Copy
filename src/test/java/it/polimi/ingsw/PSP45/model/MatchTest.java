package it.polimi.ingsw.PSP45.model;

import it.polimi.ingsw.PSP45.utils.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Andrea Gerosa
 *
 */
public class MatchTest {

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
	}

	@After
	public void tearDown(){
		match=null;
	}

	@Test
	public void getTurnObject_correctInput_correctOutput(){
		ArrayList<Player> testArray=new ArrayList<>();
		testArray.add(player1);
		testArray.add(player2);

		assertEquals(testArray.size(),match.getTurnObject().size());
		for (int i = 0; i < match.getTurnObject().size(); i++) {
			assertEquals(testArray.get(i),match.getTurnObject().get(i));
		}
	}

	@Test
	public void getBoard_correctInput_correctOutput(){
		assertEquals(board,match.getBoard());
	}
}
