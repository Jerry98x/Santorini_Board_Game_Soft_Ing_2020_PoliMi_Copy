package it.polimi.ingsw.PSP45.model;

import org.junit.*;
import static org.junit.Assert.assertEquals;


/**
 * @author Lorenzo Longaretti
 *
 */
public class CoordinateTest {


    Coordinate c = null;



    @Test(expected = IllegalArgumentException.class)
    public void constructorTest_uncorrectinput_throwsIllegalArgumentException(){
        c = new Coordinate(-1,3);
    }

    @Test
    public void costructorTest_validinput_validoutput(){
        c= new Coordinate(3,3);
        assertEquals(3,c.getX());

    }

    @Test
    public void clone_correctInput_correctOutput(){
        c=new Coordinate(2,0);
        Coordinate c1=c.clone();
        assertEquals(c.getX(),c1.getX());
        assertEquals(c.getY(),c1.getY());
    }

}
