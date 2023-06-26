package it.polimi.ingsw.PSP45.utils;

import it.polimi.ingsw.PSP45.model.Coordinate;
import it.polimi.ingsw.PSP45.model.LightBlock;
import it.polimi.ingsw.PSP45.model.LightBoard;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Lorenzo Longaretti
 * @author Andrea Gerosa
 *
 * Class that represents the object serialized and sent through the stream.
 * Service objects work as states of a finite-state machine and they consist mainly of three attributes:
 * a StateNumber to identify the state, an int number and a string for a text message.
 * A second string is available for necessity.
 *
 */
public class Service implements Serializable {

    private static final long serialVersionUID = 234226995;

    StateNumber type;
    String codice;
    String whatshouldido;
    int intservizio;
    private LightBoard lightBoard;
    private ArrayList<LightBlock> availableCells = new ArrayList<>();
    private ArrayList<LightBlock> availableCells2 = new ArrayList<>();
    private ArrayList<GodName> listgod = new ArrayList<>();
    private ArrayList<LightBlock> worker = new ArrayList<>();
    private ArrayList<String> playersToChoose = new ArrayList<>();

    /**
     * @author Lorenzo Longaretti
     * Class constructor
     *
     * @param type StateNumber
     * @param codice cliMessage
     * @param whatshouldido another useful String
     */
    public Service(StateNumber type, String codice, String whatshouldido) {
        this.type = type;
        this.codice = codice;
        this.whatshouldido = whatshouldido;
        this.intservizio = 0;

    }

    public StateNumber getType() {
        return type;
    }

    public String getCodice() {
        return codice;
    }

    public String getWhatshouldido() {
        return whatshouldido;
    }

    public int getIntservizio() {
        return intservizio;
    }

    public LightBoard getLightBoard() {
        return lightBoard;
    }

    public void setType(StateNumber type) {
        this.type = type;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public void setIntservizio(int intservizio) {
        this.intservizio = intservizio;
    }

    public void setWhatshouldido(String whatshouldido) {
        this.whatshouldido = whatshouldido;
    }

    public ArrayList<LightBlock> getWorker() {
        return worker;
    }

    public void setWorker(ArrayList<LightBlock> worker) {
        this.worker = worker;
    }

    public ArrayList<LightBlock> getAvailableCells() {
        return availableCells;
    }

    public ArrayList<LightBlock> getAvailableCells2() {
        return availableCells2;
    }

    public ArrayList<GodName> getListGod() {
        return listgod;
    }

    public ArrayList<String> getPlayersToChoose() {
        return playersToChoose;
    }


    /**
     * @author Lorenzo longaretti
     * Sets the service for the name and number request phase.
     * It is sent from the server to the client.
     *
     */
    public void setForNameAndNumber(){
        setType(StateNumber.nameAndNumberRequest);
        setCodice(cliMessage.nameMessage);
        setIntservizio(78);
    }

    /**
     * @author Lorenzo longaretti
     * Sets the service for the name and number response phase.
     * It is sent from the client to the server.
     *
     */
    public void setResponseForName(String name,int number){
        setType(StateNumber.nameResponse);
        setWhatshouldido(name);
        setIntservizio(number);
    }

    /**
     * @author Lorenzo longaretti
     * Sets the service for waiting phase.
     * It is sent from the server to the client.
     *
     */
    public void sendWaitMessage(LightBoard b) {
        setType(StateNumber.wait);
        setCodice(cliMessage.waitMessage);
        lightBoard = b;
        setIntservizio(1);

    }

    /**
     * @author Lorenzo longaretti
     * Sets the service for the moving phase.
     * It is sent from the server to the client.
     *
     */
    public void sendMoveMessage(ArrayList<LightBlock> c, LightBoard b, ArrayList<LightBlock> worker, String s) { //array dove può muoversi
        setType(StateNumber.moveState);
        setCodice(cliMessage.moveMessage);
        availableCells = c;
        lightBoard = b;
        setWorker(worker);
        setWhatshouldido(s);


    }

    /**
     * @author Lorenzo longaretti
     * Sets the service for the building phase.
     * It is sent from the server to the client.
     *
     */
    public void sendBuildMessage(ArrayList<LightBlock> l, LightBoard b, int k, String s) {
        setType(StateNumber.buildState);
        setCodice(cliMessage.buildMessage);
        setIntservizio(k);
        availableCells2 = l;
        lightBoard = b;
        setWhatshouldido(s);
    }

    /**
     * @author Lorenzo longaretti
     * Sets the service for the moving phase.
     * It is sent from the client to the server.
     *
     */
    public void receiveMoveMessage(LightBlock lb, int k) {
        System.out.println("entra in receive move message");
        availableCells.clear();
        availableCells.add(lb);
        setType(StateNumber.readyToMove);
        setIntservizio(k);
    }

    /**
     * @author Lorenzo longaretti
     * Sets the service for the name and number request phase.
     * It is sent from the client to the server.
     *
     */
    public void receiveBuildMessage(LightBlock lb) {
        availableCells2.clear();
        availableCells2.add(lb);
        setType(StateNumber.readyToBuild);
    }

    /**
     * @author Lorenzo longaretti
     * Sets the service for the next turn phase, i.
     * It is sent from the server to the client.
     *
     */
    public void setNexTurn(){
        setType(StateNumber.nextTurn);
    }

    public void receiveExitMessage() {
        setType(StateNumber.exit);
    }

    /**
     * @author Lorenzo longaretti
     * Sets the service for the gods request phase.
     * It is sent from the server to the client.
     *
     */
    public void sendGod(ArrayList<GodName> listgod, int intservizio){
        setType(StateNumber.setgod);

        if(listgod.size() > 3) {
            setCodice(cliMessage.godSelection);
        }
        else {
            setCodice(cliMessage.yourGodSelectionMessage);
        }

        this.listgod = listgod;
        this.intservizio = intservizio;
    }

    /**
     * @author Lorenzo longaretti
     * Sets the service for the gods response phase, with the selected god.
     * It is sent from the client to the server.
     *
     */
    public void replyChosenGod(GodName god){
        setType(StateNumber.replysetgod);
        listgod.clear();
        listgod.add(god);
        setIntservizio(0);
    }

    /**
     * @author Lorenzo longaretti
     * Sets the service for the gods request phase, with all the selected gods.
     * It is sent from the client to server.
     *
     */
    public void replyChosenGod1(ArrayList<GodName> god){
        setType(StateNumber.replysetgod);
        listgod.clear();
        listgod = god;
        setIntservizio(3);
    }


    public void setHasWon(){
        setType(StateNumber.haswon);
    }

    /**
     * @author Lorenzo longaretti
     * Sets the service for winning the match.
     * It is sent from the server to the client.
     *
     */
    public void sendHasWon(){
        setType(StateNumber.sendhaswon);
        setCodice(cliMessage.winMessage);
    }

    /**
     * @author Lorenzo longaretti
     * Sets the service for losing the match.
     * It is sent from the server to the client.
     *
     */
    public void sendHasLose(){
        setType(StateNumber.haswon);
        setCodice(cliMessage.loseMessage);
    }

    public void sendLose(){
        setType(StateNumber.sendhaslose);
        setCodice(cliMessage.loseMessage);
    }

    public void sendNotMove(){
        setType(StateNumber.ghost);
        setCodice(cliMessage.youAreAGhostMessage);

    }

    /**
     * @author Lorenzo longaretti
     * Sets the service for the first player choice phase.
     * It is sent from the server to the client.
     *
     */
    public void sendFirstPlayer(String primo, String secondo, String terzo, ArrayList<GodName> godNames){
        setType(StateNumber.firstplayer);
        listgod = godNames;
        if(terzo.equals("1")) {
            setCodice(cliMessage.firstPlayerChoiceMessage + "\n" + primo + ": " + listgod.get(0) + "\n" + secondo + ": " + listgod.get(1) + "\n");
        }
        else {
            setCodice(cliMessage.firstPlayerChoiceMessage + "\n" + primo + ": " + listgod.get(0) + "\n" + secondo + ": " + listgod.get(1) + "\n" + terzo + ": " + listgod.get(2) + "\n");
        }

        playersToChoose.add(primo);
        playersToChoose.add(secondo);
        playersToChoose.add(terzo);
    }
    /**
     * @author Lorenzo longaretti
     * Sets the service for the first player response phase.
     * It is sent from the client to the server.
     *
     */
    public void receiveFirstPlayer(int i){
        setType(StateNumber.firstplayerreceive);
        setIntservizio(i);
    }

    /**
     * @author Lorenzo longaretti
     * Sets the service for the ping.
     * It is sent from the server to the client.
     *
     */
    public void sendPingMessage(){ setType(StateNumber.pingsend); }

    /**
     * @author Lorenzo longaretti
     * Sets the service for the ping.
     * It is sent from the client to the server.
     *
     */
    public void receivePingMessage(){ setType(StateNumber.pingreceive);}

    /**
     * @author Lorenzo longaretti
     * Sets the service for the positioning phase.
     * It is sent from the client to the server.
     *
     */
    public void setCoordindatePlayer(int x1, int y1 ,int x2, int y2) {
        setType(StateNumber.setcoordinatereply);
        availableCells.clear();
        availableCells.add(new LightBlock(new Coordinate(x1,y1)));
        availableCells.add(new LightBlock(new Coordinate(x2,y2)));
    }

    /**
     * @author Lorenzo longaretti
     * Sets the service for positioning phase.
     * It is sent from the server to the client.
     *
     */
    public void setCoordindatePlayerSend(LightBoard lb, ArrayList<GodName> godnames) {
        setType(StateNumber.setcoordinate);
        setCodice(cliMessage.positionWorkersMessage);
        lightBoard = lb;
        listgod.clear();
        listgod = godnames;
    }

    /**
     * @author Lorenzo Longaretti
     * Sets the service for notifying unexpected disconnections.
     * It is sent from the server to the client.
     *
     */
    public void disconnection(){
        setType(StateNumber.disconnection);
        setCodice(cliMessage.disconnectMessage);
    }
}
