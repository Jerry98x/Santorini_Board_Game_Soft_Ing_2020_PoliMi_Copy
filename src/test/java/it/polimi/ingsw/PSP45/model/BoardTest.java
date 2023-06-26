package it.polimi.ingsw.PSP45.model;

import it.polimi.ingsw.PSP45.utils.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.junit.Assert.*;


/**
 * @author Andrea Gerosa
 *
 */
public class BoardTest {

    Board bo = null;
    //Block[][] b = null;
    Coordinate c = null;

    @Before
    public void setUp() {
        bo = new Board();
        //b = new Block[5][5];
        //c = new Coordinate(1,1);
    }

    @After
    public void tearDown () {
    }

    @Test
    public void getBlockX_correctinput_correctoutput() {
        c = new Coordinate(1,2);
        assertEquals(1, bo.getBlock(c).getCoordinate().getX());
    }

    @Test
    public void getBlockY_correctinput_correctoutput() {
        c = new Coordinate(1,2);
        assertEquals(2, bo.getBlock(c).getCoordinate().getY());
    }


    @Test
    public void addWorker_correctinput_corretoutput() {
        Worker w = new Worker(0,Color.ANSI_RED);
        c = new Coordinate(1,1);
        int i = c.getX();
        int j = c.getY();

        //bo.getBlock(c).addWorker(0);
        bo.getBlock(c).addWorker(0, Color.ANSI_RED);
        assertEquals(0, bo.getBlock(c).getWorker());
    }

    @Test
    public void addWorker2_correctinput_correctoutput() {
        Worker w1 = new Worker(0,Color.ANSI_RED);
        Worker w2 = new Worker (1,Color.ANSI_RED);
        c = new Coordinate(1,1);
        int i = c.getX();
        int j = c.getY();
        bo.getBlock(c).addWorker(w1.getIdWorker(), w1.getColor());
        bo.getBlock(c).addWorker(w2.getIdWorker(), w1.getColor());
        assertEquals(0, bo.getBlock(c).getWorker());
    }


    @Test
    public void removeWorker_correctinput_correctoutput() {
        Worker w = new Worker(0,Color.ANSI_RED);
        c = new Coordinate(1,1);
        int i = c.getX();
        int j = c.getY();
        bo.getBlock(c).addWorker(0, Color.ANSI_RED);
        bo.removeWorker(c);
        boolean io = bo.getBlock(c).getOccupied();
        assertEquals(false, io);
    }

    @Test
    public void removeWorker2_correctinput_correctoutput() {
        Worker w= new Worker(0,Color.ANSI_RED);
        c = new Coordinate(1,1);
        int i = c.getX();
        int j = c.getY();
        bo.getBlock(c).addWorker(0, Color.ANSI_RED);
        bo.removeWorker(c);
        bo.removeWorker(c);
        boolean io = bo.getBlock(c).getOccupied();
        assertEquals(false, io);
    }






    @Test
    public void ShowSpaces_correctinput_correctoutput() {
        Boolean[][] test = new Boolean[3][3];
        test[0][0] = false;
        test[0][1] = false;
        test[0][2] = false;
        test[1][0] = false;
        test[1][1] = false;
        test[1][2] = true;
        test[2][0] = false;
        test[2][1] = true;
        test[2][2] = true;

        c = new Coordinate(0, 0);
        int i = c.getX();
        int j = c.getY();
        Worker w = new Worker(0, Color.ANSI_RED);
        w.setCoordinate(c);
        bo.getBlock(c).addWorker(0, Color.ANSI_RED);

        Boolean[][] cb;
        cb = bo.showAroundSpaces(w.getCoordinate());
        assertEquals(test, cb);

        /*assertEquals(test[0][0], cb[i-1][j-1]);
        assertEquals(test[0][1], cb[i-1][j]);
        assertEquals(test[0][2], cb[i-1][j+1]);
        assertEquals(test[1][0], cb[i][j-1]);
        assertEquals(test[1][1], cb[i][j]);
        assertEquals(test[1][2], cb[i][j+1]);
        assertEquals(test[2][0], cb[i+1][j-1]);
        assertEquals(test[2][1], cb[i+1][j]);
        assertEquals(test[2][2], cb[i+1][j+1]);*/
    }

    @Test
    public void ShowSpaces2_correctinput_correctoutput() {
        Boolean[][] test = new Boolean[3][3];
        test[0][0] = true;
        test[0][1] = true;
        test[0][2] = false;
        test[1][0] = true;
        test[1][1] = false;
        test[1][2] = false;
        test[2][0] = false;
        test[2][1] = false;
        test[2][2] = false;

        c = new Coordinate(4, 4);
        int i = c.getX();
        int j = c.getY();
        Worker w = new Worker(0, Color.ANSI_RED);
        w.setCoordinate(c);
        bo.getBlock(c).addWorker(0, Color.ANSI_RED);

        Boolean[][] cb;
        cb = bo.showAroundSpaces(w.getCoordinate());
        assertEquals(test, cb);

    }

    @Test
    public void ShowSpaces3_correctinput_correctoutput() {
        Boolean[][] test = new Boolean[3][3];
        test[0][0] = false;
        test[0][1] = false;
        test[0][2] = false;
        test[1][0] = true;
        test[1][1] = false;
        test[1][2] = false;
        test[2][0] = true;
        test[2][1] = true;
        test[2][2] = false;

        c = new Coordinate(0, 4);
        int i = c.getX();
        int j = c.getY();
        Worker w = new Worker(0, Color.ANSI_RED);
        w.setCoordinate(c);
        bo.getBlock(c).addWorker(0, Color.ANSI_RED);

        Boolean[][] cb;
        cb = bo.showAroundSpaces(w.getCoordinate());
        assertEquals(test, cb);

    }


    @Test
    public void ShowSpaces4_correctinput_correctoutput() {
        Boolean[][] test = new Boolean[3][3];
        test[0][0] = false;
        test[0][1] = true;
        test[0][2] = true;
        test[1][0] = false;
        test[1][1] = false;
        test[1][2] = true;
        test[2][0] = false;
        test[2][1] = false;
        test[2][2] = false;

        c = new Coordinate(4, 0);
        int i = c.getX();
        int j = c.getY();
        Worker w = new Worker(0, Color.ANSI_RED);
        w.setCoordinate(c);
        bo.getBlock(c).addWorker(0, Color.ANSI_RED);

        Boolean[][] cb;
        cb = bo.showAroundSpaces(w.getCoordinate());
        assertEquals(test, cb);

    }

    @Test
    public void ShowSpaces5_correctinput_correctoutput() {
        Boolean[][] test = new Boolean[3][3];
        test[0][0] = false;
        test[0][1] = false;
        test[0][2] = false;
        test[1][0] = true;
        test[1][1] = false;
        test[1][2] = true;
        test[2][0] = true;
        test[2][1] = true;
        test[2][2] = true;

        c = new Coordinate(0, 3);
        int i = c.getX();
        int j = c.getY();
        Worker w = new Worker(0, Color.ANSI_RED);
        w.setCoordinate(c);
        bo.getBlock(c).addWorker(0, Color.ANSI_RED);

        Boolean[][] cb;
        cb = bo.showAroundSpaces(w.getCoordinate());
        assertEquals(test, cb);

    }

    @Test
    public void ShowSpaces6_correctinput_correctoutput() {
        Boolean[][] test = new Boolean[3][3];
        test[0][0] = true;
        test[0][1] = true;
        test[0][2] = false;
        test[1][0] = true;
        test[1][1] = false;
        test[1][2] = false;
        test[2][0] = true;
        test[2][1] = true;
        test[2][2] = false;

        c = new Coordinate(2, 4);
        int i = c.getX();
        int j = c.getY();
        Worker w = new Worker(0, Color.ANSI_RED);
        w.setCoordinate(c);
        bo.getBlock(c).addWorker(0, Color.ANSI_RED);

        Boolean[][] cb;
        cb = bo.showAroundSpaces(w.getCoordinate());
        assertEquals(test, cb);

    }

    @Test
    public void ShowSpaces7_correctinput_correctoutput() {
        Boolean[][] test = new Boolean[3][3];
        test[0][0] = false;
        test[0][1] = true;
        test[0][2] = true;
        test[1][0] = false;
        test[1][1] = false;
        test[1][2] = true;
        test[2][0] = false;
        test[2][1] = true;
        test[2][2] = true;

        c = new Coordinate(3, 0);
        int i = c.getX();
        int j = c.getY();
        Worker w = new Worker(0, Color.ANSI_RED);
        w.setCoordinate(c);
        bo.getBlock(c).addWorker(0, Color.ANSI_RED);

        Boolean[][] cb;
        cb = bo.showAroundSpaces(w.getCoordinate());
        assertEquals(test, cb);

    }

    @Test
    public void ShowSpaces8_correctinput_correctoutput() {
        Boolean[][] test = new Boolean[3][3];
        test[0][0] = true;
        test[0][1] = true;
        test[0][2] = true;
        test[1][0] = true;
        test[1][1] = false;
        test[1][2] = true;
        test[2][0] = false;
        test[2][1] = false;
        test[2][2] = false;

        c = new Coordinate(4, 3);
        int i = c.getX();
        int j = c.getY();
        Worker w = new Worker(0, Color.ANSI_RED);
        w.setCoordinate(c);
        bo.getBlock(c).addWorker(0, Color.ANSI_RED);

        Boolean[][] cb;
        cb = bo.showAroundSpaces(w.getCoordinate());
        assertEquals(test, cb);

    }

    @Test
    public void ShowSpaces9_correctinput_correctoutput() {
        Boolean[][] test = new Boolean[3][3];
        test[0][0] = true;
        test[0][1] = true;
        test[0][2] = true;
        test[1][0] = true;
        test[1][1] = false;
        test[1][2] = true;
        test[2][0] = true;
        test[2][1] = true;
        test[2][2] = true;

        c = new Coordinate(1, 2);
        int i = c.getX();
        int j = c.getY();
        Worker w = new Worker(0, Color.ANSI_RED);
        w.setCoordinate(c);
        bo.getBlock(c).addWorker(0, Color.ANSI_RED);

        Boolean[][] cb;
        cb = bo.showAroundSpaces(w.getCoordinate());
        assertEquals(test, cb);

    }

    /**
     * @author Filippo Locatelli
     */

    @Test
    public void spacesAround_cornerCoordinate_correctOutput(){
        //coordinate in 0,0
        c=new Coordinate(0,0);
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,0));
        testArray.add(new Coordinate(0,1));
        testArray.add(new Coordinate(1,1));

        ArrayList<Coordinate> spacesAround= bo.spacesAround(c);
        assertEquals(spacesAround.size(),testArray.size());
        for (int i = 0; i < spacesAround.size(); i++) {
            assertEquals(spacesAround.get(i).getX(),testArray.get(i).getX());
            assertEquals(spacesAround.get(i).getY(),testArray.get(i).getY());
        }
    }

    @Test
    public void spacesAround_upperSideCoordinate_correctOutput(){
        //coordinate in 0,2
        c=new Coordinate(0,2);
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(0,1));
        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(0,3));
        testArray.add(new Coordinate(1,3));

        ArrayList<Coordinate> spacesAround= bo.spacesAround(c);
        assertEquals(spacesAround.size(),testArray.size());
        for (int i = 0; i < spacesAround.size(); i++) {
            assertEquals(spacesAround.get(i).getX(),testArray.get(i).getX());
            assertEquals(spacesAround.get(i).getY(),testArray.get(i).getY());
        }
    }

    @Test
    public void spacesAround_leftSideCoordinate_correctOutput(){
        //coordinate in 2,0
        c=new Coordinate(2,0);
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,0));
        testArray.add(new Coordinate(3,0));
        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));

        ArrayList<Coordinate> spacesAround= bo.spacesAround(c);
        assertEquals(spacesAround.size(),testArray.size());
        for (int i = 0; i < spacesAround.size(); i++) {
            assertEquals(spacesAround.get(i).getX(),testArray.get(i).getX());
            assertEquals(spacesAround.get(i).getY(),testArray.get(i).getY());
        }
    }

    @Test
    public void spacesAround_centerCoordinate_correctOutput(){
        //coordinate in 2,2
        c=new Coordinate(2,2);
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        ArrayList<Coordinate> spacesAround= bo.spacesAround(c);
        assertEquals(spacesAround.size(),testArray.size());
        for (int i = 0; i < spacesAround.size(); i++) {
            assertEquals(spacesAround.get(i).getX(),testArray.get(i).getX());
            assertEquals(spacesAround.get(i).getY(),testArray.get(i).getY());
        }
    }

    @Test
    public void whereIsOccupied_listNotOccupied_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        for (int i = 0; i < testArray.size() ; i++) {
            bo.getBlock(testArray.get(i)).setNotOccupied();
        }

        ArrayList<Coordinate> whereIsOccupied=bo.whereIsOccupied(testArray);
        assertEquals(whereIsOccupied.size(),0);

    }

    @Test
    public void whereIsOccupied_list1Occupied_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        Coordinate c=new Coordinate(3,1);

        bo.getBlock(c).setOccupied();

        ArrayList<Coordinate> whereIsOccupied=bo.whereIsOccupied(testArray);
        assertEquals(whereIsOccupied.size(),1);

        for (int i = 0; i < whereIsOccupied.size(); i++) {
            assertEquals(whereIsOccupied.get(i).getX(),c.getX());
            assertEquals(whereIsOccupied.get(i).getY(),c.getY());
        }
    }

    @Test
    public void whereIsOccupied_list2Occupied_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        ArrayList<Coordinate> listOfOccupied=new ArrayList<>();
        listOfOccupied.add(new Coordinate(3,1));
        listOfOccupied.add(new Coordinate(1,2));

        for (int i = 0; i < listOfOccupied.size() ; i++) {
            bo.getBlock(listOfOccupied.get(i)).setOccupied();
        }

        ArrayList<Coordinate> whereIsOccupied=bo.whereIsOccupied(testArray);

        assertEquals(whereIsOccupied.size(),listOfOccupied.size());

        for (int i = 0; i < whereIsOccupied.size(); i++) {
            assertEquals(whereIsOccupied.get(i).getX(),listOfOccupied.get(i).getX());
            assertEquals(whereIsOccupied.get(i).getY(),listOfOccupied.get(i).getY());
        }
    }

    @Test
    public void whereIsOccupied_list3Occupied_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        ArrayList<Coordinate> listOfOccupied=new ArrayList<>();
        listOfOccupied.add(new Coordinate(3,1));
        listOfOccupied.add(new Coordinate(1,2));
        listOfOccupied.add(new Coordinate(2,3));

        for (int i = 0; i < listOfOccupied.size() ; i++) {
            bo.getBlock(listOfOccupied.get(i)).setOccupied();
        }

        ArrayList<Coordinate> whereIsOccupied=bo.whereIsOccupied(testArray);

        assertEquals(whereIsOccupied.size(),listOfOccupied.size());

        for (int i = 0; i < whereIsOccupied.size(); i++) {
            assertEquals(whereIsOccupied.get(i).getX(),listOfOccupied.get(i).getX());
            assertEquals(whereIsOccupied.get(i).getY(),listOfOccupied.get(i).getY());
        }
    }

    @Test
    public void whereIsOccupied_list4Occupied_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        ArrayList<Coordinate> listOfOccupied=new ArrayList<>();
        listOfOccupied.add(new Coordinate(1,1));
        listOfOccupied.add(new Coordinate(3,1));
        listOfOccupied.add(new Coordinate(1,2));
        listOfOccupied.add(new Coordinate(2,3));


        for (int i = 0; i < listOfOccupied.size() ; i++) {
            bo.getBlock(listOfOccupied.get(i)).setOccupied();
        }

        ArrayList<Coordinate> whereIsOccupied=bo.whereIsOccupied(testArray);

        assertEquals(whereIsOccupied.size(),listOfOccupied.size());

        for (int i = 0; i < whereIsOccupied.size(); i++) {
            assertEquals(whereIsOccupied.get(i).getX(),listOfOccupied.get(i).getX());
            assertEquals(whereIsOccupied.get(i).getY(),listOfOccupied.get(i).getY());
        }
    }

    @Test
    public void whereIsOccupied_list5Occupied_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        ArrayList<Coordinate> listOfOccupied=new ArrayList<>();
        listOfOccupied.add(new Coordinate(1,1));
        listOfOccupied.add(new Coordinate(2,1));
        listOfOccupied.add(new Coordinate(3,1));
        listOfOccupied.add(new Coordinate(1,2));
        listOfOccupied.add(new Coordinate(2,3));


        for (int i = 0; i < listOfOccupied.size() ; i++) {
            bo.getBlock(listOfOccupied.get(i)).setOccupied();
        }

        ArrayList<Coordinate> whereIsOccupied=bo.whereIsOccupied(testArray);

        assertEquals(whereIsOccupied.size(),listOfOccupied.size());

        for (int i = 0; i < whereIsOccupied.size(); i++) {
            assertEquals(whereIsOccupied.get(i).getX(),listOfOccupied.get(i).getX());
            assertEquals(whereIsOccupied.get(i).getY(),listOfOccupied.get(i).getY());
        }
    }

    @Test
    public void whereIsOccupied_list6Occupied_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        ArrayList<Coordinate> listOfOccupied=new ArrayList<>();
        listOfOccupied.add(new Coordinate(1,1));
        listOfOccupied.add(new Coordinate(2,1));
        listOfOccupied.add(new Coordinate(3,1));
        listOfOccupied.add(new Coordinate(1,2));
        listOfOccupied.add(new Coordinate(3,2));
        listOfOccupied.add(new Coordinate(2,3));


        for (int i = 0; i < listOfOccupied.size() ; i++) {
            bo.getBlock(listOfOccupied.get(i)).setOccupied();
        }

        ArrayList<Coordinate> whereIsOccupied=bo.whereIsOccupied(testArray);

        assertEquals(whereIsOccupied.size(),listOfOccupied.size());

        for (int i = 0; i < whereIsOccupied.size(); i++) {
            assertEquals(whereIsOccupied.get(i).getX(),listOfOccupied.get(i).getX());
            assertEquals(whereIsOccupied.get(i).getY(),listOfOccupied.get(i).getY());
        }
    }

    @Test
    public void whereIsOccupied_list7Occupied_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        ArrayList<Coordinate> listOfOccupied=new ArrayList<>();
        listOfOccupied.add(new Coordinate(1,1));
        listOfOccupied.add(new Coordinate(2,1));
        listOfOccupied.add(new Coordinate(3,1));
        listOfOccupied.add(new Coordinate(1,2));
        listOfOccupied.add(new Coordinate(3,2));
        listOfOccupied.add(new Coordinate(2,3));
        listOfOccupied.add(new Coordinate(3,3));

        for (int i = 0; i < listOfOccupied.size() ; i++) {
            bo.getBlock(listOfOccupied.get(i)).setOccupied();
        }

        ArrayList<Coordinate> whereIsOccupied=bo.whereIsOccupied(testArray);

        assertEquals(whereIsOccupied.size(),listOfOccupied.size());

        for (int i = 0; i < whereIsOccupied.size(); i++) {
            assertEquals(whereIsOccupied.get(i).getX(),listOfOccupied.get(i).getX());
            assertEquals(whereIsOccupied.get(i).getY(),listOfOccupied.get(i).getY());
        }
    }

    @Test
    public void whereIsOccupied_list8Occupied_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        ArrayList<Coordinate> listOfOccupied=new ArrayList<>();
        listOfOccupied.add(new Coordinate(1,1));
        listOfOccupied.add(new Coordinate(2,1));
        listOfOccupied.add(new Coordinate(3,1));
        listOfOccupied.add(new Coordinate(1,2));
        listOfOccupied.add(new Coordinate(3,2));
        listOfOccupied.add(new Coordinate(1,3));
        listOfOccupied.add(new Coordinate(2,3));
        listOfOccupied.add(new Coordinate(3,3));


        for (int i = 0; i < listOfOccupied.size() ; i++) {
            bo.getBlock(listOfOccupied.get(i)).setOccupied();
        }

        ArrayList<Coordinate> whereIsOccupied=bo.whereIsOccupied(testArray);

        assertEquals(whereIsOccupied.size(),listOfOccupied.size());

        for (int i = 0; i < whereIsOccupied.size(); i++) {
            assertEquals(whereIsOccupied.get(i).getX(),listOfOccupied.get(i).getX());
            assertEquals(whereIsOccupied.get(i).getY(),listOfOccupied.get(i).getY());
        }
    }

    @Test
    public void whereIsDome_listNotDome_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));


        ArrayList<Coordinate> whereIsOccupied=bo.whereIsDome(testArray);
        assertEquals(whereIsOccupied.size(),0);

    }

    @Test
    public void whereIsDome_list1Dome_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        Coordinate c=new Coordinate(3,1);

        bo.getBlock(c).setDome();

        ArrayList<Coordinate> whereIsDome=bo.whereIsDome(testArray);
        assertEquals(whereIsDome.size(),1);

        for (int i = 0; i < whereIsDome.size(); i++) {
            assertEquals(whereIsDome.get(i).getX(),c.getX());
            assertEquals(whereIsDome.get(i).getY(),c.getY());
        }
    }

    @Test
    public void whereIsDome_list2Dome_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        ArrayList<Coordinate> listOfDome=new ArrayList<>();
        listOfDome.add(new Coordinate(3,1));
        listOfDome.add(new Coordinate(1,2));

        for (int i = 0; i < listOfDome.size() ; i++) {
            bo.getBlock(listOfDome.get(i)).setDome();
        }

        ArrayList<Coordinate> whereIsDome=bo.whereIsDome(testArray);

        assertEquals(whereIsDome.size(),listOfDome.size());

        for (int i = 0; i < whereIsDome.size(); i++) {
            assertEquals(whereIsDome.get(i).getX(),listOfDome.get(i).getX());
            assertEquals(whereIsDome.get(i).getY(),listOfDome.get(i).getY());
        }
    }

    @Test
    public void whereIsDome_list3Dome_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        ArrayList<Coordinate> listOfDome=new ArrayList<>();
        listOfDome.add(new Coordinate(3,1));
        listOfDome.add(new Coordinate(1,2));
        listOfDome.add(new Coordinate(2,3));

        for (int i = 0; i < listOfDome.size() ; i++) {
            bo.getBlock(listOfDome.get(i)).setDome();
        }

        ArrayList<Coordinate> whereIsDome=bo.whereIsDome(testArray);

        assertEquals(whereIsDome.size(),listOfDome.size());

        for (int i = 0; i < whereIsDome.size(); i++) {
            assertEquals(whereIsDome.get(i).getX(),listOfDome.get(i).getX());
            assertEquals(whereIsDome.get(i).getY(),listOfDome.get(i).getY());
        }
    }

    @Test
    public void whereIsDome_list4Dome_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        ArrayList<Coordinate> listOfDome=new ArrayList<>();
        listOfDome.add(new Coordinate(1,1));
        listOfDome.add(new Coordinate(3,1));
        listOfDome.add(new Coordinate(1,2));
        listOfDome.add(new Coordinate(2,3));


        for (int i = 0; i < listOfDome.size() ; i++) {
            bo.getBlock(listOfDome.get(i)).setDome();
        }

        ArrayList<Coordinate> whereIsDome=bo.whereIsDome(testArray);

        assertEquals(whereIsDome.size(),listOfDome.size());

        for (int i = 0; i < whereIsDome.size(); i++) {
            assertEquals(whereIsDome.get(i).getX(),listOfDome.get(i).getX());
            assertEquals(whereIsDome.get(i).getY(),listOfDome.get(i).getY());
        }
    }

    @Test
    public void whereIsDome_list5Dome_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        ArrayList<Coordinate> listOfDome=new ArrayList<>();
        listOfDome.add(new Coordinate(1,1));
        listOfDome.add(new Coordinate(2,1));
        listOfDome.add(new Coordinate(3,1));
        listOfDome.add(new Coordinate(1,2));
        listOfDome.add(new Coordinate(2,3));


        for (int i = 0; i < listOfDome.size() ; i++) {
            bo.getBlock(listOfDome.get(i)).setDome();
        }

        ArrayList<Coordinate> whereIsDome=bo.whereIsDome(testArray);

        assertEquals(whereIsDome.size(),listOfDome.size());

        for (int i = 0; i < whereIsDome.size(); i++) {
            assertEquals(whereIsDome.get(i).getX(),listOfDome.get(i).getX());
            assertEquals(whereIsDome.get(i).getY(),listOfDome.get(i).getY());
        }
    }

    @Test
    public void whereIsDome_list6Dome_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        ArrayList<Coordinate> listOfDome=new ArrayList<>();
        listOfDome.add(new Coordinate(1,1));
        listOfDome.add(new Coordinate(2,1));
        listOfDome.add(new Coordinate(3,1));
        listOfDome.add(new Coordinate(1,2));
        listOfDome.add(new Coordinate(3,2));
        listOfDome.add(new Coordinate(2,3));


        for (int i = 0; i < listOfDome.size() ; i++) {
            bo.getBlock(listOfDome.get(i)).setDome();
        }

        ArrayList<Coordinate> whereIsDome=bo.whereIsDome(testArray);

        assertEquals(whereIsDome.size(),listOfDome.size());

        for (int i = 0; i < whereIsDome.size(); i++) {
            assertEquals(whereIsDome.get(i).getX(),listOfDome.get(i).getX());
            assertEquals(whereIsDome.get(i).getY(),listOfDome.get(i).getY());
        }
    }

    @Test
    public void whereIsDome_list7Dome_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        ArrayList<Coordinate> listOfDome=new ArrayList<>();
        listOfDome.add(new Coordinate(1,1));
        listOfDome.add(new Coordinate(2,1));
        listOfDome.add(new Coordinate(3,1));
        listOfDome.add(new Coordinate(1,2));
        listOfDome.add(new Coordinate(3,2));
        listOfDome.add(new Coordinate(2,3));
        listOfDome.add(new Coordinate(3,3));

        for (int i = 0; i < listOfDome.size() ; i++) {
            bo.getBlock(listOfDome.get(i)).setDome();
        }

        ArrayList<Coordinate> whereIsDome=bo.whereIsDome(testArray);

        assertEquals(whereIsDome.size(),listOfDome.size());

        for (int i = 0; i < whereIsDome.size(); i++) {
            assertEquals(whereIsDome.get(i).getX(),listOfDome.get(i).getX());
            assertEquals(whereIsDome.get(i).getY(),listOfDome.get(i).getY());
        }
    }

    @Test
    public void whereIsDome_list8Dome_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        ArrayList<Coordinate> listOfDome=new ArrayList<>();
        listOfDome.add(new Coordinate(1,1));
        listOfDome.add(new Coordinate(2,1));
        listOfDome.add(new Coordinate(3,1));
        listOfDome.add(new Coordinate(1,2));
        listOfDome.add(new Coordinate(3,2));
        listOfDome.add(new Coordinate(1,3));
        listOfDome.add(new Coordinate(2,3));
        listOfDome.add(new Coordinate(3,3));


        for (int i = 0; i < listOfDome.size() ; i++) {
            bo.getBlock(listOfDome.get(i)).setDome();
        }

        ArrayList<Coordinate> whereIsDome=bo.whereIsDome(testArray);

        assertEquals(whereIsDome.size(),listOfDome.size());

        for (int i = 0; i < whereIsDome.size(); i++) {
            assertEquals(whereIsDome.get(i).getX(),listOfDome.get(i).getX());
            assertEquals(whereIsDome.get(i).getY(),listOfDome.get(i).getY());
        }
    }

    @Test
    public void removeWhereOccupied_emptyOccupiedList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        ArrayList<Coordinate> occupiedList=new ArrayList<>();

        ArrayList<Coordinate> removedList=new ArrayList<>();

        removedList=bo.removeWhereOccupied(testArray,occupiedList);

        assertEquals(removedList.size(),testArray.size()-occupiedList.size());

        for (int i = 0; i < removedList.size(); i++) {
            assertEquals(removedList.get(i).getX(),testArray.get(i).getX());
            assertEquals(removedList.get(i).getY(),testArray.get(i).getY());
        }
    }

    @Test
    public void removeWhereOccupied_1elementInOccupiedList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();

        ArrayList<Coordinate> occupiedList=new ArrayList<>();
        occupiedList.add(c8);
        int oSize=occupiedList.size();

        ArrayList<Coordinate> removedList=bo.removeWhereOccupied(testArray,occupiedList);

        assertEquals(removedList.size(),tSize-oSize);

        assertEquals(removedList.get(0).getX(),c1.getX());
        assertEquals(removedList.get(0).getY(),c1.getY());

        assertEquals(removedList.get(1).getX(),c2.getX());
        assertEquals(removedList.get(1).getY(),c2.getY());

        assertEquals(removedList.get(2).getX(),c3.getX());
        assertEquals(removedList.get(2).getY(),c3.getY());

        assertEquals(removedList.get(3).getX(),c4.getX());
        assertEquals(removedList.get(3).getY(),c4.getY());

        assertEquals(removedList.get(4).getX(),c5.getX());
        assertEquals(removedList.get(4).getY(),c5.getY());

        assertEquals(removedList.get(5).getX(),c6.getX());
        assertEquals(removedList.get(5).getY(),c6.getY());

        assertEquals(removedList.get(6).getX(),c7.getX());
        assertEquals(removedList.get(6).getY(),c7.getY());

    }

    @Test
    public void removeWhereOccupied_2elementInOccupiedList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();

        ArrayList<Coordinate> occupiedList=new ArrayList<>();
        occupiedList.add(c8);
        occupiedList.add(c7);
        int oSize=occupiedList.size();

        ArrayList<Coordinate> removedList=bo.removeWhereOccupied(testArray,occupiedList);

        assertEquals(removedList.size(),tSize-oSize);

        assertEquals(removedList.get(0).getX(),c1.getX());
        assertEquals(removedList.get(0).getY(),c1.getY());

        assertEquals(removedList.get(1).getX(),c2.getX());
        assertEquals(removedList.get(1).getY(),c2.getY());

        assertEquals(removedList.get(2).getX(),c3.getX());
        assertEquals(removedList.get(2).getY(),c3.getY());

        assertEquals(removedList.get(3).getX(),c4.getX());
        assertEquals(removedList.get(3).getY(),c4.getY());

        assertEquals(removedList.get(4).getX(),c5.getX());
        assertEquals(removedList.get(4).getY(),c5.getY());

        assertEquals(removedList.get(5).getX(),c6.getX());
        assertEquals(removedList.get(5).getY(),c6.getY());


    }

    @Test
    public void removeWhereOccupied_3elementInOccupiedList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();

        ArrayList<Coordinate> occupiedList=new ArrayList<>();
        occupiedList.add(c8);
        occupiedList.add(c7);
        occupiedList.add(c6);
        int oSize=occupiedList.size();

        ArrayList<Coordinate> removedList=bo.removeWhereOccupied(testArray,occupiedList);

        assertEquals(removedList.size(),tSize-oSize);

        assertEquals(removedList.get(0).getX(),c1.getX());
        assertEquals(removedList.get(0).getY(),c1.getY());

        assertEquals(removedList.get(1).getX(),c2.getX());
        assertEquals(removedList.get(1).getY(),c2.getY());

        assertEquals(removedList.get(2).getX(),c3.getX());
        assertEquals(removedList.get(2).getY(),c3.getY());

        assertEquals(removedList.get(3).getX(),c4.getX());
        assertEquals(removedList.get(3).getY(),c4.getY());

        assertEquals(removedList.get(4).getX(),c5.getX());
        assertEquals(removedList.get(4).getY(),c5.getY());

    }

    @Test
    public void removeWhereOccupied_4elementInOccupiedList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();

        ArrayList<Coordinate> occupiedList=new ArrayList<>();
        occupiedList.add(c8);
        occupiedList.add(c7);
        occupiedList.add(c6);
        occupiedList.add(c5);
        int oSize=occupiedList.size();

        ArrayList<Coordinate> removedList=bo.removeWhereOccupied(testArray,occupiedList);

        assertEquals(removedList.size(),tSize-oSize);

        assertEquals(removedList.get(0).getX(),c1.getX());
        assertEquals(removedList.get(0).getY(),c1.getY());

        assertEquals(removedList.get(1).getX(),c2.getX());
        assertEquals(removedList.get(1).getY(),c2.getY());

        assertEquals(removedList.get(2).getX(),c3.getX());
        assertEquals(removedList.get(2).getY(),c3.getY());

        assertEquals(removedList.get(3).getX(),c4.getX());
        assertEquals(removedList.get(3).getY(),c4.getY());

    }

    @Test
    public void removeWhereOccupied_5elementInOccupiedList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();

        ArrayList<Coordinate> occupiedList=new ArrayList<>();
        occupiedList.add(c8);
        occupiedList.add(c7);
        occupiedList.add(c6);
        occupiedList.add(c5);
        occupiedList.add(c4);
        int oSize=occupiedList.size();

        ArrayList<Coordinate> removedList=bo.removeWhereOccupied(testArray,occupiedList);

        assertEquals(removedList.size(),tSize-oSize);

        assertEquals(removedList.get(0).getX(),c1.getX());
        assertEquals(removedList.get(0).getY(),c1.getY());

        assertEquals(removedList.get(1).getX(),c2.getX());
        assertEquals(removedList.get(1).getY(),c2.getY());

        assertEquals(removedList.get(2).getX(),c3.getX());
        assertEquals(removedList.get(2).getY(),c3.getY());

    }

    @Test
    public void removeWhereOccupied_6elementInOccupiedList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();

        ArrayList<Coordinate> occupiedList=new ArrayList<>();
        occupiedList.add(c8);
        occupiedList.add(c7);
        occupiedList.add(c6);
        occupiedList.add(c5);
        occupiedList.add(c4);
        occupiedList.add(c3);
        int oSize=occupiedList.size();

        ArrayList<Coordinate> removedList=bo.removeWhereOccupied(testArray,occupiedList);

        assertEquals(removedList.size(),tSize-oSize);

        assertEquals(removedList.get(0).getX(),c1.getX());
        assertEquals(removedList.get(0).getY(),c1.getY());

        assertEquals(removedList.get(1).getX(),c2.getX());
        assertEquals(removedList.get(1).getY(),c2.getY());

    }


    @Test
    public void removeWhereOccupied_7elementInOccupiedList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();

        ArrayList<Coordinate> occupiedList=new ArrayList<>();
        occupiedList.add(c8);
        occupiedList.add(c7);
        occupiedList.add(c6);
        occupiedList.add(c5);
        occupiedList.add(c4);
        occupiedList.add(c3);
        occupiedList.add(c2);
        int oSize=occupiedList.size();

        ArrayList<Coordinate> removedList=bo.removeWhereOccupied(testArray,occupiedList);

        assertEquals(removedList.size(),tSize-oSize);

        assertEquals(removedList.get(0).getX(),c1.getX());
        assertEquals(removedList.get(0).getY(),c1.getY());

    }

    @Test
    public void removeWhereOccupied_8elementInOccupiedList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();

        ArrayList<Coordinate> occupiedList=new ArrayList<>();
        occupiedList.add(c8);
        occupiedList.add(c7);
        occupiedList.add(c6);
        occupiedList.add(c5);
        occupiedList.add(c4);
        occupiedList.add(c3);
        occupiedList.add(c2);
        occupiedList.add(c1);
        int oSize=occupiedList.size();

        ArrayList<Coordinate> removedList=bo.removeWhereOccupied(testArray,occupiedList);

        assertEquals(removedList.size(),tSize-oSize);
    }

    @Test
    public void removeWhereDome_emptyDomeList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        testArray.add(new Coordinate(1,1));
        testArray.add(new Coordinate(2,1));
        testArray.add(new Coordinate(3,1));
        testArray.add(new Coordinate(1,2));
        testArray.add(new Coordinate(3,2));
        testArray.add(new Coordinate(1,3));
        testArray.add(new Coordinate(2,3));
        testArray.add(new Coordinate(3,3));

        ArrayList<Coordinate> domeList=new ArrayList<>();

        ArrayList<Coordinate> removedList=new ArrayList<>();

        removedList=bo.removeWhereDome(testArray,domeList);

        assertEquals(removedList.size(),testArray.size()-domeList.size());

        for (int i = 0; i < removedList.size(); i++) {
            assertEquals(removedList.get(i).getX(),testArray.get(i).getX());
            assertEquals(removedList.get(i).getY(),testArray.get(i).getY());
        }
    }

    @Test
    public void removeWhereDome_1elementInDomeList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();

        ArrayList<Coordinate> domeList=new ArrayList<>();
        domeList.add(c8);
        int dSize=domeList.size();

        ArrayList<Coordinate> removedList=bo.removeWhereDome(testArray,domeList);

        assertEquals(removedList.size(),tSize-dSize);

        assertEquals(removedList.get(0).getX(),c1.getX());
        assertEquals(removedList.get(0).getY(),c1.getY());

        assertEquals(removedList.get(1).getX(),c2.getX());
        assertEquals(removedList.get(1).getY(),c2.getY());

        assertEquals(removedList.get(2).getX(),c3.getX());
        assertEquals(removedList.get(2).getY(),c3.getY());

        assertEquals(removedList.get(3).getX(),c4.getX());
        assertEquals(removedList.get(3).getY(),c4.getY());

        assertEquals(removedList.get(4).getX(),c5.getX());
        assertEquals(removedList.get(4).getY(),c5.getY());

        assertEquals(removedList.get(5).getX(),c6.getX());
        assertEquals(removedList.get(5).getY(),c6.getY());

        assertEquals(removedList.get(6).getX(),c7.getX());
        assertEquals(removedList.get(6).getY(),c7.getY());

    }

    @Test
    public void removeWhereDome_2elementInDomeList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();

        ArrayList<Coordinate> domeList=new ArrayList<>();
        domeList.add(c8);
        domeList.add(c7);
        int oSize=domeList.size();

        ArrayList<Coordinate> removedList=bo.removeWhereDome(testArray,domeList);

        assertEquals(removedList.size(),tSize-oSize);

        assertEquals(removedList.get(0).getX(),c1.getX());
        assertEquals(removedList.get(0).getY(),c1.getY());

        assertEquals(removedList.get(1).getX(),c2.getX());
        assertEquals(removedList.get(1).getY(),c2.getY());

        assertEquals(removedList.get(2).getX(),c3.getX());
        assertEquals(removedList.get(2).getY(),c3.getY());

        assertEquals(removedList.get(3).getX(),c4.getX());
        assertEquals(removedList.get(3).getY(),c4.getY());

        assertEquals(removedList.get(4).getX(),c5.getX());
        assertEquals(removedList.get(4).getY(),c5.getY());

        assertEquals(removedList.get(5).getX(),c6.getX());
        assertEquals(removedList.get(5).getY(),c6.getY());


    }

    @Test
    public void removeWhereDome_3elementInDomeList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();

        ArrayList<Coordinate> domeList=new ArrayList<>();
        domeList.add(c8);
        domeList.add(c7);
        domeList.add(c6);
        int oSize=domeList.size();

        ArrayList<Coordinate> removedList=bo.removeWhereDome(testArray,domeList);

        assertEquals(removedList.size(),tSize-oSize);

        assertEquals(removedList.get(0).getX(),c1.getX());
        assertEquals(removedList.get(0).getY(),c1.getY());

        assertEquals(removedList.get(1).getX(),c2.getX());
        assertEquals(removedList.get(1).getY(),c2.getY());

        assertEquals(removedList.get(2).getX(),c3.getX());
        assertEquals(removedList.get(2).getY(),c3.getY());

        assertEquals(removedList.get(3).getX(),c4.getX());
        assertEquals(removedList.get(3).getY(),c4.getY());

        assertEquals(removedList.get(4).getX(),c5.getX());
        assertEquals(removedList.get(4).getY(),c5.getY());

    }

    @Test
    public void removeWhereDome_4elementInDomeList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();

        ArrayList<Coordinate> domeList=new ArrayList<>();
        domeList.add(c8);
        domeList.add(c7);
        domeList.add(c6);
        domeList.add(c5);
        int oSize=domeList.size();

        ArrayList<Coordinate> removedList=bo.removeWhereDome(testArray,domeList);

        assertEquals(removedList.size(),tSize-oSize);

        assertEquals(removedList.get(0).getX(),c1.getX());
        assertEquals(removedList.get(0).getY(),c1.getY());

        assertEquals(removedList.get(1).getX(),c2.getX());
        assertEquals(removedList.get(1).getY(),c2.getY());

        assertEquals(removedList.get(2).getX(),c3.getX());
        assertEquals(removedList.get(2).getY(),c3.getY());

        assertEquals(removedList.get(3).getX(),c4.getX());
        assertEquals(removedList.get(3).getY(),c4.getY());

    }

    @Test
    public void removeWhereDome_5elementInDomeList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();

        ArrayList<Coordinate> domeList=new ArrayList<>();
        domeList.add(c8);
        domeList.add(c7);
        domeList.add(c6);
        domeList.add(c5);
        domeList.add(c4);
        int oSize=domeList.size();

        ArrayList<Coordinate> removedList=bo.removeWhereDome(testArray,domeList);

        assertEquals(removedList.size(),tSize-oSize);

        assertEquals(removedList.get(0).getX(),c1.getX());
        assertEquals(removedList.get(0).getY(),c1.getY());

        assertEquals(removedList.get(1).getX(),c2.getX());
        assertEquals(removedList.get(1).getY(),c2.getY());

        assertEquals(removedList.get(2).getX(),c3.getX());
        assertEquals(removedList.get(2).getY(),c3.getY());

    }

    @Test
    public void removeWhereDome_6elementInDomeList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();

        ArrayList<Coordinate> domeList=new ArrayList<>();
        domeList.add(c8);
        domeList.add(c7);
        domeList.add(c6);
        domeList.add(c5);
        domeList.add(c4);
        domeList.add(c3);
        int oSize=domeList.size();

        ArrayList<Coordinate> removedList=bo.removeWhereDome(testArray,domeList);

        assertEquals(removedList.size(),tSize-oSize);

        assertEquals(removedList.get(0).getX(),c1.getX());
        assertEquals(removedList.get(0).getY(),c1.getY());

        assertEquals(removedList.get(1).getX(),c2.getX());
        assertEquals(removedList.get(1).getY(),c2.getY());

    }


    @Test
    public void removeWhereDome_7elementInDomeList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();

        ArrayList<Coordinate> domeList=new ArrayList<>();
        domeList.add(c8);
        domeList.add(c7);
        domeList.add(c6);
        domeList.add(c5);
        domeList.add(c4);
        domeList.add(c3);
        domeList.add(c2);
        int oSize=domeList.size();

        ArrayList<Coordinate> removedList=bo.removeWhereDome(testArray,domeList);

        assertEquals(removedList.size(),tSize-oSize);

        assertEquals(removedList.get(0).getX(),c1.getX());
        assertEquals(removedList.get(0).getY(),c1.getY());

    }

    @Test
    public void removeWhereDome_8elementInDomeList_correctOutput(){
        ArrayList<Coordinate> testArray=new ArrayList<>();

        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();

        ArrayList<Coordinate> domeList=new ArrayList<>();
        domeList.add(c8);
        domeList.add(c7);
        domeList.add(c6);
        domeList.add(c5);
        domeList.add(c4);
        domeList.add(c3);
        domeList.add(c2);
        domeList.add(c1);
        int oSize=domeList.size();

        ArrayList<Coordinate> removedList=bo.removeWhereDome(testArray,domeList);

        assertEquals(removedList.size(),tSize-oSize);
    }

    @Test
    public void freeSpaces_allFree_correctOutput(){
        Coordinate c=new Coordinate(2,2);

        ArrayList<Coordinate> testArray=new ArrayList<>();
        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);

        ArrayList<Coordinate> freeSpaces=new ArrayList<>();
        freeSpaces=bo.freeSpaces(c);

        assertEquals(freeSpaces.size(),testArray.size());

        for (int i = 0; i < freeSpaces.size(); i++) {
            assertEquals(freeSpaces.get(i).getX(),testArray.get(i).getX());
            assertEquals(freeSpaces.get(i).getY(),testArray.get(i).getY());
        }
    }

    @Test
    public void freeSpaces_1Occupied1Dome_correctOutput(){
        Coordinate c=new Coordinate(2,2);

        ArrayList<Coordinate> testArray=new ArrayList<>();
        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();
        bo.getBlock(c7).setOccupied();
        bo.getBlock(c8).setDome();

        ArrayList<Coordinate> freeSpaces=new ArrayList<>();
        freeSpaces=bo.freeSpaces(c);

        assertEquals(freeSpaces.size(),tSize-2);


        assertEquals(freeSpaces.get(0).getX(),c1.getX());
        assertEquals(freeSpaces.get(0).getY(),c1.getY());

        assertEquals(freeSpaces.get(1).getX(),c2.getX());
        assertEquals(freeSpaces.get(1).getY(),c2.getY());

        assertEquals(freeSpaces.get(2).getX(),c3.getX());
        assertEquals(freeSpaces.get(2).getY(),c3.getY());

        assertEquals(freeSpaces.get(3).getX(),c4.getX());
        assertEquals(freeSpaces.get(3).getY(),c4.getY());

        assertEquals(freeSpaces.get(4).getX(),c5.getX());
        assertEquals(freeSpaces.get(4).getY(),c5.getY());

        assertEquals(freeSpaces.get(5).getX(),c6.getX());
        assertEquals(freeSpaces.get(5).getY(),c6.getY());

    }

    @Test
    public void freeSpacesBuild_allFree_correctOutput(){
        Coordinate c=new Coordinate(2,2);

        ArrayList<Coordinate> testArray=new ArrayList<>();
        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);

        ArrayList<LightBlock> freeSpacesBuild=new ArrayList<>();
        freeSpacesBuild=bo.freeSpacesBuild(c);

        assertEquals(freeSpacesBuild.size(),testArray.size());

        for (int i = 0; i < freeSpacesBuild.size(); i++) {
            assertEquals(freeSpacesBuild.get(i).getC().getX(),testArray.get(i).getX());
            assertEquals(freeSpacesBuild.get(i).getC().getY(),testArray.get(i).getY());
        }
    }

    @Test
    public void freeSpacesBuild_1Occupied1Dome_correctOutput(){
        Coordinate c=new Coordinate(2,2);

        ArrayList<Coordinate> testArray=new ArrayList<>();
        Coordinate c1=new Coordinate(1,1);
        Coordinate c2=new Coordinate(2,1);
        Coordinate c3=new Coordinate(3,1);
        Coordinate c4=new Coordinate(1,2);
        Coordinate c5=new Coordinate(3,2);
        Coordinate c6=new Coordinate(1,3);
        Coordinate c7=new Coordinate(2,3);
        Coordinate c8=new Coordinate(3,3);

        testArray.add(c1);
        testArray.add(c2);
        testArray.add(c3);
        testArray.add(c4);
        testArray.add(c5);
        testArray.add(c6);
        testArray.add(c7);
        testArray.add(c8);
        int tSize=testArray.size();
        bo.getBlock(c7).setOccupied();
        bo.getBlock(c8).setDome();

        ArrayList<LightBlock> freeSpacesBuild=new ArrayList<>();
        freeSpacesBuild=bo.freeSpacesBuild(c);

        assertEquals(freeSpacesBuild.size(),tSize-2);


        assertEquals(freeSpacesBuild.get(0).getC().getX(),c1.getX());
        assertEquals(freeSpacesBuild.get(0).getC().getY(),c1.getY());

        assertEquals(freeSpacesBuild.get(1).getC().getX(),c2.getX());
        assertEquals(freeSpacesBuild.get(1).getC().getY(),c2.getY());

        assertEquals(freeSpacesBuild.get(2).getC().getX(),c3.getX());
        assertEquals(freeSpacesBuild.get(2).getC().getY(),c3.getY());

        assertEquals(freeSpacesBuild.get(3).getC().getX(),c4.getX());
        assertEquals(freeSpacesBuild.get(3).getC().getY(),c4.getY());

        assertEquals(freeSpacesBuild.get(4).getC().getX(),c5.getX());
        assertEquals(freeSpacesBuild.get(4).getC().getY(),c5.getY());

        assertEquals(freeSpacesBuild.get(5).getC().getX(),c6.getX());
        assertEquals(freeSpacesBuild.get(5).getC().getY(),c6.getY());

    }

    @Test
    public void whereIsRightLevel_allAtRightLevel_correctOutput(){
        Coordinate c=new Coordinate(2,2);
        LightBlock lc=new LightBlock(c);
        lc.setLevel(0);

        LightBlock l1=new LightBlock(new Coordinate(1,1));
        LightBlock l2=new LightBlock(new Coordinate(2,1));
        LightBlock l3=new LightBlock(new Coordinate(3,1));
        LightBlock l4=new LightBlock(new Coordinate(1,2));
        LightBlock l5=new LightBlock(new Coordinate(3,2));
        LightBlock l6=new LightBlock(new Coordinate(1,3));
        LightBlock l7=new LightBlock(new Coordinate(2,3));
        LightBlock l8=new LightBlock(new Coordinate(3,3));

        ArrayList<LightBlock> testArray=new ArrayList<>();
        testArray.add(l1);
        testArray.add(l2);
        testArray.add(l3);
        testArray.add(l4);
        testArray.add(l5);
        testArray.add(l6);
        testArray.add(l7);
        testArray.add(l8);

        for (int i = 0; i < testArray.size(); i++) {
            testArray.get(i).setLevel(0);
        }
        ArrayList<LightBlock> rightLevel = bo.whereIsRightLevel(c,testArray);
        assertEquals(rightLevel.size(),testArray.size());

        for (int i = 0; i < rightLevel.size(); i++) {
            assertEquals(rightLevel.get(i).getC().getX(),testArray.get(i).getC().getX());
            assertEquals(rightLevel.get(i).getC().getY(),testArray.get(i).getC().getY());
        }
    }

    @Test
    public void whereIsRightLevel_1atWrongLevel_correctOutput(){
        Coordinate c=new Coordinate(2,2);
        LightBlock lc=new LightBlock(c);
        lc.setLevel(0);

        LightBlock l1=new LightBlock(new Coordinate(1,1));
        LightBlock l2=new LightBlock(new Coordinate(2,1));
        LightBlock l3=new LightBlock(new Coordinate(3,1));
        LightBlock l4=new LightBlock(new Coordinate(1,2));
        LightBlock l5=new LightBlock(new Coordinate(3,2));
        LightBlock l6=new LightBlock(new Coordinate(1,3));
        LightBlock l7=new LightBlock(new Coordinate(2,3));
        LightBlock l8=new LightBlock(new Coordinate(3,3));

        ArrayList<LightBlock> testArray=new ArrayList<>();
        testArray.add(l1);
        testArray.add(l2);
        testArray.add(l3);
        testArray.add(l4);
        testArray.add(l5);
        testArray.add(l6);
        testArray.add(l7);
        testArray.add(l8);
        int tSize=testArray.size();

        bo.getBlock(l8.getC()).setLevel(4);

        ArrayList<LightBlock> rightLevel = bo.whereIsRightLevel(c,testArray);
        assertEquals(rightLevel.size(),tSize-1);

        IntStream.range(0, rightLevel.size()).forEach(i -> {
            assertEquals(rightLevel.get(i).getC().getX(), testArray.get(i).getC().getX());
            assertEquals(rightLevel.get(i).getC().getY(), testArray.get(i).getC().getY());
        });
    }

    @Test
    public void whereIsRightLevel_4atWrongLevel_correctOutput(){
        Coordinate c=new Coordinate(2,2);
        LightBlock lc=new LightBlock(c);
        lc.setLevel(0);

        LightBlock l1=new LightBlock(new Coordinate(1,1));
        LightBlock l2=new LightBlock(new Coordinate(2,1));
        LightBlock l3=new LightBlock(new Coordinate(3,1));
        LightBlock l4=new LightBlock(new Coordinate(1,2));
        LightBlock l5=new LightBlock(new Coordinate(3,2));
        LightBlock l6=new LightBlock(new Coordinate(1,3));
        LightBlock l7=new LightBlock(new Coordinate(2,3));
        LightBlock l8=new LightBlock(new Coordinate(3,3));

        ArrayList<LightBlock> testArray=new ArrayList<>();
        testArray.add(l1);
        testArray.add(l2);
        testArray.add(l3);
        testArray.add(l4);
        testArray.add(l5);
        testArray.add(l6);
        testArray.add(l7);
        testArray.add(l8);
        int tSize=testArray.size();

        bo.getBlock(l8.getC()).setLevel(4);
        bo.getBlock(l7.getC()).setLevel(4);
        bo.getBlock(l6.getC()).setLevel(4);
        bo.getBlock(l5.getC()).setLevel(4);


        ArrayList<LightBlock> rightLevel = bo.whereIsRightLevel(c,testArray);
        assertEquals(rightLevel.size(),tSize-4);

        IntStream.range(0, rightLevel.size()).forEach(i -> {
            assertEquals(rightLevel.get(i).getC().getX(), testArray.get(i).getC().getX());
            assertEquals(rightLevel.get(i).getC().getY(), testArray.get(i).getC().getY());
        });
    }

    @Test
    public void isOnBoard_correctCoordinates_correctOutput(){
        assertTrue(bo.isOnBoard(0,0));
    }

    @Test
    public void isOnBoard_incorrectCoordinates_correctOutput(){
        assertFalse(bo.isOnBoard(5,0));
    }
}