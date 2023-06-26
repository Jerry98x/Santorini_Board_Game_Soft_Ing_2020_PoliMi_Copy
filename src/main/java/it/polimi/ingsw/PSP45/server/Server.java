package it.polimi.ingsw.PSP45.server;

import it.polimi.ingsw.PSP45.controller.Controller;
import it.polimi.ingsw.PSP45.controller.Rules;
import it.polimi.ingsw.PSP45.model.*;
import it.polimi.ingsw.PSP45.utils.*;
import it.polimi.ingsw.PSP45.view.RemoteView;
import it.polimi.ingsw.PSP45.view.View;
import it.polimi.ingsw.PSP45.controller.*;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Lorenzo Longaretti
 * Class that handles the creation of a match in all its phases.
 *
 */
public class Server {

    private static final int PORT = 12345;
    private ServerSocket serverSocket;
    private ExecutorService executor = Executors.newFixedThreadPool(128);
    private ArrayList<WaitingConnectionClass> waitingConnectionarray2= new ArrayList<>();
    private ArrayList<WaitingConnectionClass> waitingConnectionarray3= new ArrayList<>();
    private ArrayList<WaitingConnectionClass> waitingConnectionarraygod= new ArrayList<>();
    private int contatore = 0;
    private ArrayList<GodLobby_Class> listforlobbygod = new ArrayList<>();




    /**
     * method that disconnect the player of a match and the opponents
     * @param c
     */
    public synchronized void deregisterConnection(ClientConnection c) {
        Player playerhelper = new Player("default", 15, GodName.apollo, Color.ANSI_WHITE);
        Player playerhelper2 = new Player("default", 15,GodName.apollo,Color.ANSI_WHITE);


        for(GodLobby_Class godLobby_class : listforlobbygod){
            if(c == godLobby_class.getC1() || godLobby_class.getC2() == c || godLobby_class.getC3() == c){
                if(c == godLobby_class.getC1()){
                    godLobby_class.getC2().closeConnection();
                    if(null != godLobby_class.getC3()) {
                        godLobby_class.getC3().closeConnection();
                    }
                }
                else if (c == godLobby_class.getC2()){
                    godLobby_class.getC1().closeConnection();
                    if(null != godLobby_class.getC3()) {
                        godLobby_class.getC3().closeConnection();
                    }
                }
                else if(null != godLobby_class.getC3()){
                    if(c == godLobby_class.getC3()){
                        godLobby_class.getC1().closeConnection();
                        godLobby_class.getC2().closeConnection();
                    }
                }

            }
        }
    }

    /**
     * @author Lorenzo Longaretti
     * Returns the god's rules.
     *
     * @param rules
     * @param godName
     * @return
     */
    public AbstractRule setGodRules(Rules rules,GodName godName){
        switch (godName){
            case apollo: Apollo rulesApollo = new Apollo(rules); return rulesApollo;
            case minotaur: Minotaur rulesMinotaur = new Minotaur(rules); return rulesMinotaur;
            case hephaestus: Hephaestus rulesHephaestus = new Hephaestus(rules); return rulesHephaestus;
            case athena: Athena rulesAthena = new Athena(rules,true); return rulesAthena;
            case pan : Pan rulesPan = new Pan(rules); return rulesPan;
            case demeter:Demeter rulesDemeter = new Demeter(rules); return rulesDemeter;
            case artemis: Artemis rulesArtemis = new Artemis(rules); return rulesArtemis;
            case atlas: Atlas rulesAtlas = new Atlas(rules); return rulesAtlas;
            case prometheus: Prometheus rulesPrometheus = new Prometheus(rules); return rulesPrometheus;
        }

        return null;
    }

    /**
     * @author Lorenzo Longaretti
     * Checks there is a god with a "passive" effect.
     *
     * @param list
     * @return
     */
    public Boolean checkIfPassive (ArrayList<GodName> list){
        for(GodName name : list){
            if(name == GodName.athena){
                return true;
            }
        }
        return false;
    }

    /**
     * @author Lorenzo Longaretti
     * method that create a match
     */
    public synchronized void handlelobby(GodLobby_Class godLobby_class){
        ArrayList<WaitingConnectionClass> waitingConnectionClass_handleLobby = godLobby_class.getList();
        if (waitingConnectionClass_handleLobby.size() == 2) {

            ClientConnection c1 = waitingConnectionClass_handleLobby.get(0).getC();
            ClientConnection c2 = waitingConnectionClass_handleLobby.get(1).getC();
            Player player1 = new Player(waitingConnectionClass_handleLobby.get(0).getPlayername(),15,godLobby_class.getC1god(),Color.ANSI_RED);
            Player player2 = new Player(waitingConnectionClass_handleLobby.get(1).getPlayername(), 16,godLobby_class.getC2god(),Color.ANSI_BLUE);

            View player1View = new RemoteView(player1, c1);
            View player2View = new RemoteView(player2, c2);
            Board board = new Board();
            ArrayList<Player> turnobject = new ArrayList<>();


            Rules rules1 = new Rules(1,board,player1,player2,null);
            AbstractRule rules1real = setGodRules(rules1,godLobby_class.getC1god());

            Rules rules2 = new Rules(2,board,player2,player1,null);
            AbstractRule rules2real = setGodRules(rules2,godLobby_class.getC2god());
            if(checkIfPassive(godLobby_class.getListgodused())){

                if(rules1real.getCurrentPlayer().getGod() == GodName.athena){
                    rules2real = new Athena(rules2real,false);
                    rules1real = new Athena(rules1real,true);
                }
                else{
                    rules1real = new Athena(rules1real,false);
                    rules2real = new Athena(rules2real,true);
                }
            }


            player1.setAbstractRule(rules1real);
            player2.setAbstractRule(rules2real);


            turnobject.add(player1);
            turnobject.add(player2);

            if(godLobby_class.getFirstPlayer() == 1){
                ArrayList<Coordinate> help = new ArrayList<>();
                help.add(godLobby_class.getCoordinates().get(2));
                help.add(godLobby_class.getCoordinates().get(3));
                help.add(godLobby_class.getCoordinates().get(1));
                help.add(godLobby_class.getCoordinates().get(0));

                godLobby_class.getCoordinates().clear();
                godLobby_class.setCoordinates(help);
            }
            Match model = new Match(board,turnobject,godLobby_class.getCoordinates());



            Controller controller = new Controller(model);

            rules1.addObserver(player1View);
            rules2.addObserver(player2View);

            rules2.addObserver(player1View);
            rules1.addObserver(player2View);

            player1View.addObserver(controller);
            player2View.addObserver(controller);


            waitingConnectionClass_handleLobby.clear();

            Service start = new Service(StateNumber.nextTurn,"Default", "Default");

            controller.getMatch().getTurnObject().get(godLobby_class.getFirstPlayer()).getAbstractRule().turnHandler(start);

        }
        else{

            ClientConnection c1 = waitingConnectionClass_handleLobby.get(0).getC();
            ClientConnection c2 = waitingConnectionClass_handleLobby.get(1).getC();
            ClientConnection c3 = waitingConnectionClass_handleLobby.get(2).getC();
            Player player1 = new Player(waitingConnectionClass_handleLobby.get(0).getPlayername(),15,godLobby_class.getC1god(),Color.ANSI_RED);
            Player player2 = new Player(waitingConnectionClass_handleLobby.get(1).getPlayername(), 16,godLobby_class.getC2god(),Color.ANSI_BLUE);
            Player player3 = new Player(waitingConnectionClass_handleLobby.get(2).getPlayername(), 17,godLobby_class.getC3god(),Color.ANSI_YELLOW);
            View player1View = new RemoteView(player1, c1);
            View player2View = new RemoteView(player2, c2);
            View player3View = new RemoteView(player3, c3);


            Board board = new Board();
            ArrayList<Player> turnobject = new ArrayList<>();
            Rules rules1 = new Rules(1,board,player1,player2,player3);
            AbstractRule rules1real = setGodRules(rules1,godLobby_class.getC1god());


            Rules rules2 = new Rules(2,board,player2,player3,player1);
            AbstractRule rules2real = setGodRules(rules2,godLobby_class.getC2god());


            Rules rules3 = new Rules(3,board,player3,player1,player2);
            AbstractRule rules3real = setGodRules(rules3,godLobby_class.getC3god());

            if(checkIfPassive(godLobby_class.getListgodused())){
                if(godLobby_class.getListgodused().get(0) == GodName.athena){
                    rules1real = new Athena(rules1real,true);
                    rules2real = new Athena(rules2real,false);
                    rules3real = new Athena(rules3real,false);
                }
                else if(godLobby_class.getListgodused().get(1) == GodName.athena){
                    rules1real = new Athena(rules1real,false);
                    rules2real = new Athena(rules2real,true);
                    rules3real = new Athena(rules3real,false);

                }
                else{
                    rules1real = new Athena(rules1real,false);
                    rules2real = new Athena(rules2real,false);
                    rules3real = new Athena(rules3real,true);
                }
            }

            player1.setAbstractRule(rules1real);
            player2.setAbstractRule(rules2real);
            player3.setAbstractRule(rules3real);
            turnobject.add(player1);
            turnobject.add(player2);
            turnobject.add(player3);


            if(godLobby_class.getFirstPlayer() == 1){
                ArrayList<Coordinate> help = new ArrayList<>();
                help.add(godLobby_class.getCoordinates().get(4));
                help.add(godLobby_class.getCoordinates().get(5));
                help.add(godLobby_class.getCoordinates().get(1));
                help.add(godLobby_class.getCoordinates().get(0));
                help.add(godLobby_class.getCoordinates().get(2));
                help.add(godLobby_class.getCoordinates().get(3));


                godLobby_class.getCoordinates().clear();
                godLobby_class.setCoordinates(help);
            }
            else if(godLobby_class.getFirstPlayer() == 2)
            {
                ArrayList<Coordinate> help = new ArrayList<>();
                help.add(godLobby_class.getCoordinates().get(2));
                help.add(godLobby_class.getCoordinates().get(3));
                help.add(godLobby_class.getCoordinates().get(4));
                help.add(godLobby_class.getCoordinates().get(5));
                help.add(godLobby_class.getCoordinates().get(1));
                help.add(godLobby_class.getCoordinates().get(0));

                godLobby_class.getCoordinates().clear();
                godLobby_class.setCoordinates(help);
            }

            Match model = new Match(board,turnobject,godLobby_class.getCoordinates());
            Controller controller = new Controller(model);

            rules1.addObserver(player1View);
            rules2.addObserver(player2View);
            rules3.addObserver(player3View);

            rules1.addObserver(player2View);
            rules2.addObserver(player3View);
            rules3.addObserver(player1View);

            rules1.addObserver(player3View);
            rules2.addObserver(player1View);
            rules3.addObserver(player2View);


            player1View.addObserver(controller);
            player2View.addObserver(controller);
            player3View.addObserver(controller);



            waitingConnectionClass_handleLobby.clear();

            Service start = new Service(StateNumber.active,"Default", "Default");
            controller.getMatch().getTurnObject().get(godLobby_class.getFirstPlayer()).getAbstractRule().turnHandler(start);

        }

    }






    /**
     * @author Lorenzo Longaretti
     * Checks if it should create the lobby, or if it should add the player in the waiting list.
     *
     * @param c
     * @param name
     * @param number
     */
    public synchronized void lobby(ClientConnection c, String name, int number){
        WaitingConnectionClass toadd = new WaitingConnectionClass(name,number,c);

        boolean check = false;

        if (number == 2) {
            if (waitingConnectionarray2.size() == 1) {
                if (name.equals(waitingConnectionarray2.get(0).getPlayername())) {
                    check = true;
                }
            }
            if (!check) {
                waitingConnectionarray2.add(toadd);
            }

        } else {

            if (waitingConnectionarray2.size() == 1) {
                if (name.equals(waitingConnectionarray2.get(0).getPlayername())) {
                    check = true;
                }
            } else if (waitingConnectionarray2.size() == 2) {
                if (name.equals(waitingConnectionarray2.get(0).getPlayername()) || name.equals(waitingConnectionarray2.get(1).getPlayername())) {
                    check = true;
                }
            }

            if (!check) {
                waitingConnectionarray3.add(toadd);
            }

        }

        if (waitingConnectionarray2.size() >= 2 && !check) {

            int contatore2 = 0;
            for (WaitingConnectionClass wait : waitingConnectionarray2) {
                if (contatore2 == 0 || contatore2 == 1) {
                    waitingConnectionarraygod.add(wait.clone());
                    contatore2++;
                }
            }
            contatore++;
            for (int j = 0; j < 2; j++) {
                waitingConnectionarray2.remove(0);
            }
            GodLobby_Class godLobby_class = new GodLobby_Class(waitingConnectionarraygod, waitingConnectionarraygod.get(0).getC(), waitingConnectionarraygod.get(1).getC(), null);
            listforlobbygod.add(godLobby_class);
            Service service = new Service(StateNumber.setgod, "def", "def");
            service.sendGod(godLobby_class.getListgodnotused(), 2);
            waitingConnectionarraygod.clear();
            godLobby_class.getC1().asyncSend(service);



        } else if (waitingConnectionarray3.size() >= 3 && !check ) {
            int contatore2 = 0;
            for (WaitingConnectionClass wait : waitingConnectionarray3) {
                if (contatore2 == 0 || contatore2 == 1 || contatore2 == 2) {
                    waitingConnectionarraygod.add(wait.clone());
                    contatore2++;
                }
            }
            contatore = 0;
            for (int j = 0; j < 3; j++) {
                waitingConnectionarray3.remove(0);
            }
            listforlobbygod.add(new GodLobby_Class(waitingConnectionarraygod, waitingConnectionarraygod.get(0).getC(), waitingConnectionarraygod.get(1).getC(), waitingConnectionarraygod.get(2).getC()));
            GodLobby_Class godLobby_class = new GodLobby_Class(waitingConnectionarraygod, waitingConnectionarraygod.get(0).getC(), waitingConnectionarraygod.get(1).getC(), waitingConnectionarraygod.get(2).getC());
            Service service = new Service(StateNumber.setgod, "def", "def");
            service.sendGod(godLobby_class.getListgodnotused(), 3);
            godLobby_class.getC1().asyncSend(service);
            waitingConnectionarraygod.clear();
        } else if (check) {
            check = false;
            Service service2 = new Service(StateNumber.moving, "def", "def");
            service2.setForNameAndNumber();
            c.asyncSend(service2);
        }

    }

    public synchronized void handleGodLobby(ClientConnection c, GodName godName){
        for(GodLobby_Class godLobby_class : listforlobbygod){
            Boolean checkC3 = false;
            if(godLobby_class.getList().size()==3){
                if(godLobby_class.getC3().equals(c)){
                    checkC3=true;
                }
            }
            if(godLobby_class.getC1().equals(c) ||godLobby_class.getC2().equals(c) ||checkC3){
                godLobby_class.Updateusage();
                if(godLobby_class.getUsage() == 3){
                    if(godLobby_class.getC3()==null){
                        godLobby_class.setC1GodName(godName);
                        godLobby_class.removeGodName(godName);

                        Service service = new Service(StateNumber.setgod,"def","def");
                        String third = "1";
                        if(godLobby_class.getList().size() == 3){
                            third = godLobby_class.getList().get(2).getPlayername();
                        }
                        godLobby_class.sort();
                        service.sendFirstPlayer(godLobby_class.getList().get(0).getPlayername(),godLobby_class.getList().get(1).getPlayername(),third,godLobby_class.getListgodused());
                        godLobby_class.getC1().asyncSend(service);

                    }
                    else{
                        godLobby_class.setC1GodName(godName);
                        godLobby_class.removeGodName(godName);
                        Service service = new Service(StateNumber.setgod,"def","def");


                        Service service1 = new Service(StateNumber.setgod,"def","def");
                        String third = "1";
                        if(godLobby_class.getList().size() == 3){
                            third = godLobby_class.getList().get(2).getPlayername();
                        }
                        godLobby_class.sort();
                        service1.sendFirstPlayer(godLobby_class.getList().get(0).getPlayername(),godLobby_class.getList().get(1).getPlayername(),third,godLobby_class.getListgodused());
                        godLobby_class.getC1().asyncSend(service1);
                    }
                }
                else{
                    if(c.equals(godLobby_class.getC1())){


                        godLobby_class.setC1GodName(godName);
                        godLobby_class.removeGodName(godName);
                        Service service = new Service(StateNumber.setgod,"def","def");
                        service.sendGod(godLobby_class.getListgodnotused(),0);
                        godLobby_class.getC2().asyncSend(service);
                    }
                    else if(c.equals(godLobby_class.getC2())){
                        godLobby_class.setC2GodName(godName);
                        godLobby_class.removeGodName(godName);
                        Service service = new Service(StateNumber.setgod,"def","def");
                        service.sendGod(godLobby_class.getListgodnotused(),0);
                        if(godLobby_class.getC3()==null){
                            godLobby_class.getC1().asyncSend(service);
                        }
                        else{
                            godLobby_class.getC3().asyncSend(service);
                        }

                    }
                    else if(c.equals(godLobby_class.getC3())){
                        godLobby_class.setC3GodName(godName);
                        godLobby_class.removeGodName(godName);
                        Service service = new Service(StateNumber.setgod,"def","def");
                        service.sendGod(godLobby_class.getListgodnotused(),0);
                        godLobby_class.getC1().asyncSend(service);
                    }
                }

            }
            else{
            }
        }
    }

    /**
     * @author Lorenzo Longaretti
     * Sends to the client the list of the gods that can be chosed.
     *
     * @param c
     * @param godName
     */
    public synchronized void handleGodLobbyMore(ClientConnection c, ArrayList<GodName> godName){
        for(GodLobby_Class godLobby_class : listforlobbygod){
            Boolean checkC3 = false;

            if(c.equals(godLobby_class.getC1())){
                godLobby_class.setListgodnotused(godName);
                Service service = new Service(StateNumber.setgod,"def","def");
                service.sendGod(godLobby_class.getListgodnotused(),0);
                godLobby_class.getC2().asyncSend(service);
            }

            else if(c.equals(godLobby_class.getC2())){
                godLobby_class.setListgodnotused(godName);

                Service service = new Service(StateNumber.setgod,"def","def");
                service.sendGod(godLobby_class.getListgodnotused(),0);
                godLobby_class.getC3().asyncSend(service);
            }
            else if(c.equals(godLobby_class.getC3())){
                godLobby_class.setListgodnotused(godName);

                Service service = new Service(StateNumber.setgod,"def","def");
                service.sendGod(godLobby_class.getListgodnotused(),0);

            }
        }

    }

    /**
     * @author Lorenzo Longaretti
     * Sends the information about tha positioning phase to the player that will start the match.
     *
     * @param c
     * @param s
     */
    public synchronized void handleFirst(ClientConnection c, Service s){
        for(GodLobby_Class godLobby_class : listforlobbygod){
            Boolean checkC3 = false;
            if(godLobby_class.getList().size()==3){
                if(godLobby_class.getC3().equals(c)){
                    checkC3=true;
                }
            }
            if(godLobby_class.getC1().equals(c) ||godLobby_class.getC2().equals(c) ||checkC3){

                godLobby_class.setFirstPlayer (s.getIntservizio());

                godLobby_class.setToPutCoordinate (c) ;
                Service service = new Service(StateNumber.active,"def","def");

                service.setCoordindatePlayerSend(godLobby_class.getLightBoard(),godLobby_class.getListgodused());
                if(s.getIntservizio()==0){
                    godLobby_class.getC1().asyncSend(service);
                }
                else if(s.getIntservizio()==1){
                    godLobby_class.getC2().asyncSend(service);
                    godLobby_class.setToPutCoordinateInt (1);
                }
                else {
                    godLobby_class.getC3().asyncSend(service);
                    godLobby_class.setToPutCoordinateInt ( 2);
                }



            }
        }
    }

    /**
     * @author Lorenzo Longaretti
     * Sends the information about the positioning phase to the other players and start the match.
     *
     * @param c
     * @param s
     */
    public synchronized void handleCoordinate(ClientConnection c , Service s){

        for(GodLobby_Class godLobby_class : listforlobbygod){
            Boolean checkC3 = false;

            if(godLobby_class.getList().size()==3){
                if(godLobby_class.getC3().equals(c)){
                    checkC3=true;
                }
            }
            if(godLobby_class.getC1().equals(c) ||godLobby_class.getC2().equals(c) ||checkC3){
                if(godLobby_class.getTosend() == 2){
                    godLobby_class.setFirstworker_thirdPlayerCoordinate(s.getAvailableCells().get(0).getC());
                    godLobby_class.setSecondworker_thirdPlayerCoordinate(s.getAvailableCells().get(1).getC());
                    godLobby_class.getCoordinates().add(godLobby_class.getFirstworker_thirdPlayerCoordinate());
                    godLobby_class.getCoordinates().add(godLobby_class.getSecondworker_thirdPlayerCoordinate());

                }
                if(godLobby_class.getTosend() == 0){
                    godLobby_class.setFirstworker_firstPlayerCoordinate(s.getAvailableCells().get(0).getC());
                    godLobby_class.setSecondworker_firstPlayerCoordinate(s.getAvailableCells().get(1).getC());
                    godLobby_class.getCoordinates().add( godLobby_class.getFirstworker_firstPlayerCoordinate());
                    godLobby_class.getCoordinates().add(godLobby_class.getSecondworker_firstPlayerCoordinate());
                }
                else if(godLobby_class.getTosend() == 1){
                    godLobby_class.setFirstworker_secondPlayerCoordinate(s.getAvailableCells().get(0).getC());
                    godLobby_class.setSecondworker_secondPlayerCoordinate(s.getAvailableCells().get(1).getC());
                    godLobby_class.getCoordinates().add(godLobby_class.getFirstworker_secondPlayerCoordinate());
                    godLobby_class.getCoordinates().add(godLobby_class.getSecondworker_secondPlayerCoordinate());

                }

                godLobby_class.setTosend(godLobby_class.getTosend()+1);
                Service service = new Service(StateNumber.active,"def","def");
                Block block = new Block(s.getAvailableCells().get(0).getC());
                block.setOccupied();

                Block block2 = new Block(s.getAvailableCells().get(1).getC());
                block2.setOccupied();
                LightBlock lb = new LightBlock(s.getAvailableCells().get(0).getC());

                if(godLobby_class.getTosend() == 3){
                    handlelobby(godLobby_class);
                }
                else{

                    if(godLobby_class.getToPutCoordinateInt() ==0){
                        if(godLobby_class.getC1().equals(c)){
                            block.addWorker(1,Color.ANSI_RED);
                            block2.addWorker(1,Color.ANSI_RED);
                            
                            godLobby_class.getLightBoard().setLightblock(lb.getLightBlock(block));
                            godLobby_class.getLightBoard().setLightblock(lb.getLightBlock(block2));
                            godLobby_class.getLightBoard().getLightBlock(block.getCoordinate()).setColor(Color.ANSI_RED);
                            godLobby_class.getLightBoard().getLightBlock(block2.getCoordinate()).setColor(Color.ANSI_RED);

                            godLobby_class.getLightBoard();
                            service.setCoordindatePlayerSend(godLobby_class.getLightBoard(),godLobby_class.getListgodused());
                            if(godLobby_class.getC3() == null){
                                godLobby_class.setTosend(2);
                            }
                            godLobby_class.getC2().asyncSend(service);
                        }
                        else if(godLobby_class.getC2().equals(c)){
                            block.addWorker(1,Color.ANSI_YELLOW);
                            block2.addWorker(1,Color.ANSI_YELLOW);
                            
                            godLobby_class.getLightBoard().setLightblock(lb.getLightBlock(block));
                            godLobby_class.getLightBoard().setLightblock(lb.getLightBlock(block2));
                            godLobby_class.getLightBoard().getLightBlock(block.getCoordinate()).setColor(Color.ANSI_YELLOW);
                            godLobby_class.getLightBoard().getLightBlock(block2.getCoordinate()).setColor(Color.ANSI_YELLOW);
                            service.setCoordindatePlayerSend(godLobby_class.getLightBoard(),godLobby_class.getListgodused());

                            if(checkC3){
                                block.addWorker(1,Color.ANSI_BLUE);
                                block2.addWorker(1,Color.ANSI_BLUE);
                                
                                godLobby_class.getLightBoard().setLightblock(lb.getLightBlock(block));
                                godLobby_class.getLightBoard().setLightblock(lb.getLightBlock(block2));
                                godLobby_class.getLightBoard().getLightBlock(block.getCoordinate()).setColor(Color.ANSI_BLUE);
                                godLobby_class.getLightBoard().getLightBlock(block2.getCoordinate()).setColor(Color.ANSI_BLUE);

                                
                                service.setCoordindatePlayerSend(godLobby_class.getLightBoard(),godLobby_class.getListgodused());
                                godLobby_class.getC3().asyncSend(service);
                            }
                            else{
                                godLobby_class.getC3().asyncSend(service);
                            }

                        }
                    }
                    else if(godLobby_class.getToPutCoordinateInt() == 1){
                        if(godLobby_class.getC1().equals(c)){
                            block.addWorker(1,Color.ANSI_RED);
                            block2.addWorker(1,Color.ANSI_RED);
                            
                            godLobby_class.getLightBoard().setLightblock(lb.getLightBlock(block));
                            godLobby_class.getLightBoard().setLightblock(lb.getLightBlock(block2));
                            godLobby_class.getLightBoard().getLightBlock(block.getCoordinate()).setColor(Color.ANSI_RED);
                            godLobby_class.getLightBoard().getLightBlock(block2.getCoordinate()).setColor(Color.ANSI_RED);

                            
                            service.setCoordindatePlayerSend(godLobby_class.getLightBoard(),godLobby_class.getListgodused());
                            godLobby_class.setTosend (2);
                            godLobby_class.getC1().asyncSend(service);
                        }
                        else if(godLobby_class.getC2().equals(c)){
                            block.addWorker(1,Color.ANSI_YELLOW);
                            block2.addWorker(1,Color.ANSI_YELLOW);
                            
                            godLobby_class.getLightBoard().setLightblock(lb.getLightBlock(block));
                            godLobby_class.getLightBoard().setLightblock(lb.getLightBlock(block2));
                            godLobby_class.getLightBoard().getLightBlock(block.getCoordinate()).setColor(Color.ANSI_YELLOW);
                            godLobby_class.getLightBoard().getLightBlock(block2.getCoordinate()).setColor(Color.ANSI_YELLOW);

                            
                            service.setCoordindatePlayerSend(godLobby_class.getLightBoard(),godLobby_class.getListgodused());
                            if( godLobby_class.getC3() != null){
                                godLobby_class.getC3().asyncSend(service);
                            }
                            else{
                                godLobby_class.getC1().asyncSend(service);
                                godLobby_class.setTosend (2);
                            }


                        }
                        else{
                            block.addWorker(1,Color.ANSI_BLUE);
                            block2.addWorker(1,Color.ANSI_BLUE);
                            godLobby_class.getLightBoard().setLightblock(lb.getLightBlock(block));
                            godLobby_class.getLightBoard().setLightblock(lb.getLightBlock(block2));
                            godLobby_class.getLightBoard().getLightBlock(block.getCoordinate()).setColor(Color.ANSI_BLUE);
                            godLobby_class.getLightBoard().getLightBlock(block2.getCoordinate()).setColor(Color.ANSI_BLUE);

                            service.setCoordindatePlayerSend(godLobby_class.getLightBoard(),godLobby_class.getListgodused());
                            godLobby_class.getC1().asyncSend(service);
                        }
                    }
                    else if(godLobby_class.getToPutCoordinateInt() == 2){
                        block.addWorker(1,Color.ANSI_BLUE);
                        block2.addWorker(1,Color.ANSI_BLUE);
                        godLobby_class.getLightBoard().setLightblock(lb.getLightBlock(block));
                        godLobby_class.getLightBoard().setLightblock(lb.getLightBlock(block2));
                        godLobby_class.getLightBoard().getLightBlock(block.getCoordinate()).setColor(Color.ANSI_BLUE);
                        godLobby_class.getLightBoard().getLightBlock(block2.getCoordinate()).setColor(Color.ANSI_BLUE);

                        service.setCoordindatePlayerSend(godLobby_class.getLightBoard(),godLobby_class.getListgodused());
                        if(godLobby_class.getC1() != c){
                            godLobby_class.getC1().asyncSend(service);
                        }
                        else
                        {
                            godLobby_class.getC2().asyncSend(service);
                        }
                    }
                }





            }


        }
    }

    /**
     * @author Lorenzo Longaretti
     * Sends the disconnection informations to the clients.
     *
     * @param c
     */
    public void Error(ClientConnection c){
        Service service = new Service(StateNumber.active,"def","def");
        service.disconnection();
        for(GodLobby_Class godLobby_class : listforlobbygod){
            if(c == godLobby_class.getC1() || godLobby_class.getC2() == c || godLobby_class.getC3() == c){
                if(c == godLobby_class.getC1()){
                    godLobby_class.getC2().asyncSend(service);
                    if(null != godLobby_class.getC3()) {
                        godLobby_class.getC3().asyncSend(service);
                    }
                }
                else if (c == godLobby_class.getC2()){
                    godLobby_class.getC1().asyncSend(service);
                    if(null != godLobby_class.getC3()) {
                        godLobby_class.getC3().asyncSend(service);
                    }
                }
                else if(null != godLobby_class.getC3()){
                    if(c == godLobby_class.getC3()){
                        godLobby_class.getC1().asyncSend(service);
                        godLobby_class.getC2().asyncSend(service);
                    }
                }

            }
        }
    }

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }

    public void run() {
        while(true) {
            try {
                Socket newSocket = serverSocket.accept();
                SocketClientConnection socketConnection = new SocketClientConnection(newSocket, this);
                executor.submit(socketConnection);
            } catch (IOException e) {
                System.out.println("Connection Error!");
            }
        }
    }

}
