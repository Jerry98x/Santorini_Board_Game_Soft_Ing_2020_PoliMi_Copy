package it.polimi.ingsw.PSP45.model;

import it.polimi.ingsw.PSP45.utils.Color;
import it.polimi.ingsw.PSP45.utils.GodName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class WorkerTest {
    Worker worker=null;
    Player player=null;

    @Before
    public void setUp() {
        worker=new Worker(0, Color.ANSI_RED);
        player=new Player("prova",0, GodName.artemis,Color.ANSI_RED);
    }

    @After
    public void tearDown() {
        worker=null;
        player=null;
    }

    @Test (expected = IllegalArgumentException.class)
    public void worker_wrongInput_shouldThrowIException(){
        int id=5;
        worker=new Worker(id,Color.ANSI_PURPLE);
    }

    @Test
    public void getIdWorker_ExpectedOutput() {
        assertEquals(worker.getIdWorker(),0);
    }

    @Test
    public void getColor_ExpectedOutput() {
        assertEquals(Color.ANSI_RED,worker.getColor());
    }



}