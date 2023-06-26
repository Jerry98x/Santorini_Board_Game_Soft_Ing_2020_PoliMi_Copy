package it.polimi.ingsw.PSP45.model;

import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author  Andrea Gerosa
 *
 */
public class LightBlockTest {

    Block b = null;
    //LightBlock lb = null;
    Coordinate c = null;

    @Before
    public void setUp(){
        c = new Coordinate(2,2);
        b = new Block(c);
    }

    @After
    public void tearDown(){}


    @Test
    public void setLightBlockX_correctinput_correctoutput() {
        Coordinate c1 = new Coordinate(0,0);
        LightBlock lb = new LightBlock(c1);

        lb.setLightBlock(b);

        assertEquals(lb.getC().getX(), b.getCoordinate().getX());

    }

    @Test
    public void setLightBlockY_correctinput_correctoutput() {
        Coordinate c1 = new Coordinate(0,0);
        LightBlock lb = new LightBlock(c1);

        lb.setLightBlock(b);

        assertEquals(lb.getC().getY(), b.getCoordinate().getY());

    }

    @Test
    public void setLightBlockLevel_correctinput_correctoutput() {
        Coordinate c1 = new Coordinate(0,0);
        LightBlock lb = new LightBlock(c1);

        lb.setLightBlock(b);

        assertEquals(lb.getLevel(), b.getLevel());

    }

    @Test
    public void setLightBlockOccupied_correctinput_correctoutput() {
        Coordinate c1 = new Coordinate(0,0);
        LightBlock lb = new LightBlock(c1);

        lb.setLightBlock(b);

        assertEquals(lb.getOccupied(), b.getOccupied());

    }

    @Test
    public void setLightBlockIsDome_correctinput_correctoutput() {
        Coordinate c1 = new Coordinate(0,0);
        LightBlock lb = new LightBlock(c1);

        lb.setLightBlock(b);

        assertEquals(lb.getIsDome(), b.getIsDome());

    }

    @Test
    public void setLightBlockWorker_correctinput_correctoutput() {
        Coordinate c1 = new Coordinate(0,0);
        LightBlock lb = new LightBlock(c1);

        lb.setLightBlock(b);

        assertEquals(lb.getIdWorker(), b.getWorker());

    }

    @Test
    public void getLightBlock_correctInput_correctOutput(){
        LightBlock lb=new LightBlock(new Coordinate(2,0));
        LightBlock lb1=lb.getLightBlock(b);

        assertEquals(lb1.getC().getX(),b.getCoordinate().getX());
        assertEquals(lb1.getC().getY(),b.getCoordinate().getY());

    }

}
