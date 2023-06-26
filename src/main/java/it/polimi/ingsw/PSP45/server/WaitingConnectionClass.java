package it.polimi.ingsw.PSP45.server;


/**
 * @author Lorenzo Longaretti
 * Class that rappresent an object to store the connection and the players used for creating the match.
 *
 */
public class WaitingConnectionClass implements Cloneable {

    private String Playername;
    private int numberofplayer;
    private ClientConnection c;

    /**
     * Class constructor
     *
     * @param playername
     * @param numberofplayer
     * @param c
     */
    public WaitingConnectionClass(String playername, int numberofplayer, ClientConnection c) {
        Playername = playername;
        this.numberofplayer = numberofplayer;
        this.c = c;
    }
    public String getPlayername() {
        return Playername;
    }

    public ClientConnection getC() {
        return c;
    }

    @Override
    public WaitingConnectionClass clone(){
        WaitingConnectionClass waithelp = new WaitingConnectionClass(this.Playername,this.numberofplayer,this.c);
        return waithelp;
    }
}
