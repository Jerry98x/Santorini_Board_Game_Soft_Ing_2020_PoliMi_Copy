package it.polimi.ingsw.PSP45.model;

import it.polimi.ingsw.PSP45.utils.Color;
import it.polimi.ingsw.PSP45.utils.GodName;
import javafx.scene.effect.Light;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {

    Player player=null;
    Worker worker=null;



    @Before
    public void setUp() throws Exception {
        player=new Player("player",0, GodName.apollo, Color.ANSI_RED);
        worker=new Worker(0,Color.ANSI_RED);
    }

    @After
    public void tearDown() throws Exception {
        player=null;
    }

    @Test
    public void getName_expectedOutput(){
        assertEquals("player",player.getName());
    }

    @Test
    public void getColor_expectedOutput(){
        assertEquals(Color.ANSI_RED,player.getColor());
    }

    @Test
    public void getEta_expectedOutput(){
        assertEquals(0,player.getEta());
    }

    @Test
    public void getGod_expectedOutput(){
        assertEquals("APOLLO", player.getGod().toString());
    }

    @Test
    public void isAlive_expectedTrue(){
        assertTrue(player.isAlive());
    }

    @Test
    public void isAlive_expectedFalse(){
        player.setAlive(false);
        assertFalse(player.isAlive());
    }

    @Test
    public void setAlive_TrueInput_expectedTrueOutput(){
        player.setAlive(true);
        assertEquals(true,player.isAlive());
    }

    @Test
    public void removeWorker_ExpectedInput_ExpectedOutput() {
        player.removeWorker(worker.getIdWorker());
        assertEquals(1,player.getWorkers().size());

    }

    @Test
    public void remove2Workers_ExpectedInput_ExpectedOutput() {
        player.removeWorker(worker.getIdWorker());
        player.removeWorker(worker.getIdWorker());
        assertEquals(0,player.getWorkers().size());
    }

    @Test
    public void removeAllWorkers(){
        player.removeAllWorkers();
        assertEquals(0,player.getWorkers().size());
    }

    @Test
    public void getCoordinateWorkers_correctInput_correctOutput(){
        Board board=new Board();
        Worker cWorker0=player.getSpecificWorker(0);
        Worker cWorker1=player.getSpecificWorker(1);
        Coordinate cc0=new Coordinate(0,0);cWorker0.setCoordinate(cc0);board.addWorker(cc0,0,Color.ANSI_RED);
        Coordinate cc1=new Coordinate(4,4);cWorker1.setCoordinate(cc1);board.addWorker(cc1,1,Color.ANSI_RED);

        ArrayList<LightBlock> testArray = new ArrayList<>();

        testArray.add(new LightBlock(cc0));
        testArray.add(new LightBlock(cc1));

        ArrayList<LightBlock> workers=player.getCoordinateWorkers();

        assertEquals(testArray.size(),workers.size());

        for (int i = 0; i < workers.size(); i++) {
            assertEquals(testArray.get(i).getC().getX(),workers.get(i).getC().getX());
            assertEquals(testArray.get(i).getC().getY(),workers.get(i).getC().getY());
        }
    }

    @Test
    public void getSpecificWorker_correctInput_correctOutput(){
        Worker cWorker0=player.getSpecificWorker(0);
        Worker testWorker=player.getWorkers().get(0);
        assertEquals(testWorker,cWorker0);
    }

}