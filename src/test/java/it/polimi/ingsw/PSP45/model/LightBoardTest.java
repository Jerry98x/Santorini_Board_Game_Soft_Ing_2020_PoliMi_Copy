package it.polimi.ingsw.PSP45.model;

import it.polimi.ingsw.PSP45.utils.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * @author Andrea Gerosa
 *
 */
public class LightBoardTest {

    Board bo = null;
    LightBoard lbo = null;
    Coordinate c = null;

    @Before
    public void setUp() {
        bo = new Board();
        lbo = new LightBoard();

    }

    @After
    public void tearDown () {
    }


    @Test
    public void getLightBlockX_correctinput_correctoutput() {
        c = new Coordinate(3,2);
        assertEquals(3, lbo.getLightBlock(c).getC().getX());
    }

    @Test
    public void getLightBlockY_correctinput_correctoutput() {
        c = new Coordinate(2,0);
        assertEquals(0, lbo.getLightBlock(c).getC().getY());
    }


    @Test
    public void update_correctinput_correctoutput() {
        lbo.update(bo);

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                c = new Coordinate(i,j);
                assertEquals(lbo.getLightBlock(c).getIdWorker(), bo.getBlock(c).getWorker());
                assertEquals(lbo.getLightBlock(c).getOccupied(), bo.getBlock(c).getOccupied());
                assertEquals(lbo.getLightBlock(c).getIsDome(), bo.getBlock(c).getIsDome());
                assertEquals(lbo.getLightBlock(c).getLevel(), bo.getBlock(c).getLevel());
                assertEquals(lbo.getLightBlock(c).getColor(), bo.getBlock(c).getWorkerColor());
            }
        }
    }

    @Test
    public void update2_correctinput_correctoutput() {
        Coordinate c1 = new Coordinate(1,4);
        Coordinate c2 = new Coordinate(3,2);
        bo.addWorker(c1,0,Color.ANSI_RED);
        bo.addWorker(c2,0,Color.ANSI_BLUE);
        lbo.update(bo);

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                c = new Coordinate(i,j);
                assertEquals(lbo.getLightBlock(c).getIdWorker(), bo.getBlock(c).getWorker());
                assertEquals(lbo.getLightBlock(c).getOccupied(), bo.getBlock(c).getOccupied());
                assertEquals(lbo.getLightBlock(c).getIsDome(), bo.getBlock(c).getIsDome());
                assertEquals(lbo.getLightBlock(c).getLevel(), bo.getBlock(c).getLevel());
                assertEquals(lbo.getLightBlock(c).getColor(), bo.getBlock(c).getWorkerColor());
            }
        }
    }



    @Test
    public void printBoard_correctinput_correctoutput() {
        Coordinate c1 = new Coordinate(1,1);
        Coordinate c2 = new Coordinate(1,2);
        Coordinate c3 = new Coordinate(2,2);
        Coordinate c4 = new Coordinate(2,3);

        Coordinate c5 = new Coordinate(2,4);
        Coordinate c6 = new Coordinate(3,3);

        bo.addWorker(c1,0,Color.ANSI_RED);
        bo.addWorker(c2,1,Color.ANSI_RED);
        bo.addWorker(c3,0,Color.ANSI_BLUE);
        bo.addWorker(c4,1,Color.ANSI_BLUE);

        bo.getBlock(c5).levelUp();

        bo.getBlock(c2).levelUp();
        bo.getBlock(c2).levelUp();

        bo.getBlock(c3).levelUp();
        bo.getBlock(c3).levelUp();
        bo.getBlock(c3).levelUp();

        bo.getBlock(c6).setDome();

        lbo.update(bo);

        String s =
                "\r\n" +
                "      0          1          2          3          4      \r\n" +
                "  ╔════════╗ ╔════════╗ ╔════════╗ ╔════════╗ ╔════════╗ \r\n" +
                "  ║        ║ ║        ║ ║        ║ ║        ║ ║        ║ \r\n" +
                "0 ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ 0\r\n" +
                "  ║        ║ ║        ║ ║        ║ ║        ║ ║        ║ \r\n" +
                "  ╚════════╝ ╚════════╝ ╚════════╝ ╚════════╝ ╚════════╝ \r\n" +
                "  ╔════════╗ ╔════════╗ ╔════════╗ ╔════════╗ ╔════════╗ \r\n" +
                "  ║        ║ ║        ║ ║        ║ ║        ║ ║        ║ \r\n" +
                "1 ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ ║  "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"-"+Color.ANSI_RED+"W"+Color.getRESET()+Color.ANSI_RED+"0"+Color.getRESET()+"  ║ ║  "+Color.ANSI_YELLOW+"2"+Color.getRESET()+"-"+Color.ANSI_RED+"W"+Color.getRESET()+Color.ANSI_RED+"1"+ Color.getRESET()+"  ║ ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ 1\r\n" +
                "  ║        ║ ║        ║ ║        ║ ║        ║ ║        ║ \r\n" +
                "  ╚════════╝ ╚════════╝ ╚════════╝ ╚════════╝ ╚════════╝ \r\n" +
                "  ╔════════╗ ╔════════╗ ╔════════╗ ╔════════╗ ╔════════╗ \r\n" +
                "  ║        ║ ║        ║ ║        ║ ║        ║ ║        ║ \r\n" +
                "2 ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ ║  "+Color.ANSI_YELLOW+"3"+Color.getRESET()+"-"+Color.ANSI_BLUE+"W"+Color.getRESET()+Color.ANSI_BLUE+"0"+Color.getRESET()+"  ║ ║  "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"-"+Color.ANSI_BLUE+"W"+Color.getRESET()+Color.ANSI_BLUE+"1"+Color.getRESET()+"  ║ ║   "+Color.ANSI_YELLOW+"1"+Color.getRESET()+"    ║ 2\r\n" +
                "  ║        ║ ║        ║ ║        ║ ║        ║ ║        ║ \r\n" +
                "  ╚════════╝ ╚════════╝ ╚════════╝ ╚════════╝ ╚════════╝ \r\n" +
                "  ╔════════╗ ╔════════╗ ╔════════╗ ╔════════╗ ╔════════╗ \r\n" +
                "  ║        ║ ║        ║ ║        ║ ║        ║ ║        ║ \r\n" +
                "3 ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ ║   "+Color.ANSI_PURPLE+"D"+Color.getRESET()+"    ║ ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ 3\r\n" +
                "  ║        ║ ║        ║ ║        ║ ║        ║ ║        ║ \r\n" +
                "  ╚════════╝ ╚════════╝ ╚════════╝ ╚════════╝ ╚════════╝ \r\n" +
                "  ╔════════╗ ╔════════╗ ╔════════╗ ╔════════╗ ╔════════╗ \r\n" +
                "  ║        ║ ║        ║ ║        ║ ║        ║ ║        ║ \r\n" +
                "4 ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ ║   "+Color.ANSI_YELLOW+"0"+Color.getRESET()+"    ║ 4\r\n" +
                "  ║        ║ ║        ║ ║        ║ ║        ║ ║        ║ \r\n" +
                "  ╚════════╝ ╚════════╝ ╚════════╝ ╚════════╝ ╚════════╝ \r\n" +
                "      0          1          2          3          4      \r\n\r\n";



        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        lbo.printBoard();

        System.out.flush();
        System.setOut(old);

        assertEquals(baos.toString(), s);

    }

    @Test
    public void setLightBlock_correctInput_correctOutput(){
        LightBlock lb=new LightBlock(new Coordinate(2,0));
        lbo.setLightblock(lb);
        assertEquals(lb,lbo.getLightMat()[lb.getC().getX()][lb.getC().getY()]);
    }

}
