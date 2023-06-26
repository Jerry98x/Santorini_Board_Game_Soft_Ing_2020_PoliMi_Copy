package it.polimi.ingsw.PSP45.model;

import it.polimi.ingsw.PSP45.utils.Color;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Lorenzo Longaretti
 *
 */
public class BlockTest {

    Block b = null;
    Coordinate c = null;

    @Before
    public void setUp(){
        c = new Coordinate(1,1);
        b = new Block(c);
    }

    @After
    public void tearDown(){}






    @Test
    public void levelUp_correctLevel_correctOutput(){
        int i= b.getLevel();
        b.levelUp();
        int j = b.getLevel();
        assertEquals(i+1,j);

    }

    @Test
    public void levelUp2_correctLevel_correctOutput(){
        b.levelUp();b.levelUp();b.levelUp();
        int j = b.getLevel();
        assertEquals(3,j);

    }

    @Test
    public void levelUp3_correctLevel_correctOutput(){
        b.levelUp();b.levelUp();b.levelUp();
        boolean j = b.getIsDome();
        assertEquals(false,j);

    }

    @Test
    public void levelUp4_correctLevel_correctOutput(){
        b.levelUp();b.levelUp();b.levelUp();b.levelUp();
        boolean j = b.getIsDome();
        assertEquals(true,j);

    }

    @Test
    public void levelUp5_correctLevel_correctOutput(){
        b.levelUp();b.levelUp();b.levelUp();b.levelUp();
        int j = b.getLevel();
        assertEquals(4,j);
    }



    //test su set occupied

    @Test
    public void setOccupied_correctInput_correctOutput(){
        b.setOccupied();
        boolean j = b.getOccupied();
        assertEquals(true,j);
    }

    @Test
    public void setOccupied_uncorrectinput_correctOutput(){
        b.setOccupied();  b.setOccupied();
        boolean j = b.getOccupied();
        assertEquals(true,j);
    }



    //test su setnotoccupied

    @Test
    public void setnotOccupied_correctinput_correctOutput(){
        b.setNotOccupied();
        boolean j = b.getOccupied();
        assertEquals(false,j);
    }

    @Test
    public void setnotOccupied_uncorrectinput_correctOutput(){
        b.setNotOccupied();  b.setNotOccupied();
        boolean j = b.getOccupied();
        assertEquals(false,j);
    }




    //test su setdome

    @Test
    public void setDome_correctinput_correctOutput(){
        b.setDome();
        boolean j = b.getIsDome();
        assertEquals(true,j);
    }

    @Test
    public void setDome_uncorrectinput_correctOutput(){
        b.setDome();b.setDome();
        boolean j = b.getIsDome();
        assertEquals(true,j);
    }


    //test su addworker

    @Test
    public void addWorker_correctinput_correctOutput(){
        Worker w = new Worker(0, Color.ANSI_RED);
        b.addWorker(w.getIdWorker(), w.getColor());
        int j = b.getWorker();
        assertEquals(0,j);
    }

    @Test
    public void addWorker2_correctinput_correctOutput(){
        Worker w = new Worker(0,Color.ANSI_RED); Worker w1 = new Worker(1,Color.ANSI_RED);
        b.addWorker(w.getIdWorker(), w.getColor()); b.addWorker(w1.getIdWorker(), w1.getColor());
        int j = b.getWorker();
        assertEquals(0,j);
    }

    //test su removeworker

    @Test
    public void removeWorker_correctinput_correctOutput(){
        Worker w = new Worker(0,Color.ANSI_RED);
        b.addWorker(w.getIdWorker(), w.getColor());
        b.removeWorker();
        boolean j = b.getOccupied();
        assertEquals(false,j);
    }

    @Test
    public void removeWorker2_correctinput_correctOutput(){
        Worker w = new Worker(0,Color.ANSI_RED);
        b.addWorker(w.getIdWorker(), w.getColor());
        b.removeWorker();
        b.removeWorker();
        boolean j = b.getOccupied();
        assertEquals(false,j);
    }

    @Test
    public void setLevel_correctInput_correctOutput(){
        b.setLevel(4);
        assertEquals(b.getLevel(),4);
    }


}