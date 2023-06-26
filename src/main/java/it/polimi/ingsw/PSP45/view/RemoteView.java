package it.polimi.ingsw.PSP45.view;


import it.polimi.ingsw.PSP45.model.Player;
import it.polimi.ingsw.PSP45.observer.Observer;
import it.polimi.ingsw.PSP45.server.ClientConnection;
import it.polimi.ingsw.PSP45.utils.Service;
import it.polimi.ingsw.PSP45.utils.StateNumber;
import it.polimi.ingsw.PSP45.utils.frommodeltoview;
import it.polimi.ingsw.PSP45.utils.fromviewtocontroll;


/**
 * @author Lorenzo Longaretti
 * Class that represents the view of a client seen by the server.
 */
public class RemoteView extends View {


    /**
     * Class that receives the client's communication and redirects it to the remote view.
     */
    private class MessageReceiver implements Observer<Service> {


        @Override
        public void update(Service message) {

            try{
                handleMove(message);
            }catch(IllegalArgumentException e){
                clientConnection.asyncSend("Error!");
            }
        }


    }

    private ClientConnection clientConnection;

    /**
     * @author Lorenzo Longaretti
     * Class constructor
     *
     * @param player
     * @param c
     */
    public RemoteView(Player player, ClientConnection c) {
        super(player);
        this.clientConnection = c;
        c.addObserver(new MessageReceiver());
    }


    /**
     * @author Lorenzo Longaretti
     * Method that calls the method for sending messages to a server.
     * @param message
     */
    @Override
    protected void showMessage(Service message) {
        clientConnection.asyncSend(message);
    }


    /**
     * @author Lorenzo Longaretti
     * Method that handles the request from the match to redirect messages to a client or to the controll (if should change the turn).
     * @param message
     */
    @Override
    public void update(frommodeltoview message) {
        Service tosend = message.getServizio();
        Player playertosend = message.getPlayer();
        Player playerhelp = this.getPlayer();




        if (message.getServizio().getType() == StateNumber.nextTurn && message.getPlayer() != playerhelp) {
            fromviewtocontroll help = new fromviewtocontroll(message.getPlayer(), message.getServizio());

            notify(help);
        } else {

        }


        if (playerhelp != playertosend && message.getServizio().getType() != StateNumber.nextTurn && message.getServizio().getType() != StateNumber.haswon && message.getServizio().getType() != StateNumber.exit && message.getServizio().getType() != StateNumber.ghost) {
            Service dainviare = new Service(StateNumber.active, "def", "def");
            dainviare.sendWaitMessage(message.getServizio().getLightBoard());
            clientConnection.asyncSend(dainviare);
        }


        if (playerhelp == playertosend && message.getServizio().getType() != StateNumber.nextTurn && message.getServizio().getType() != StateNumber.haswon && message.getServizio().getType() != StateNumber.exit &&message.getServizio().getType() != StateNumber.ghost) {
            clientConnection.asyncSend(tosend);
        }

        // -----------------------------has won ----------------------------


        if (message.getServizio().getType() == StateNumber.haswon && message.getPlayer() != playerhelp) {
            Service dainviare = new Service(StateNumber.active, "def", "def");
            dainviare.sendLose();
            clientConnection.closeforwin(dainviare);
        } else if (message.getServizio().getType() == StateNumber.haswon) {
            Service dainviare = new Service(StateNumber.active, "def", "def");
            dainviare.sendHasWon();

            clientConnection.closeforwin(dainviare);
        }

        // lose -------------------------------


        if (message.getServizio().getType() == StateNumber.sendhaslose && message.getPlayer() == playerhelp) {

            Service dainviare = new Service(StateNumber.active, "def", "def");
            dainviare.sendHasWon();
            fromviewtocontroll help = new fromviewtocontroll(message.getPlayer(), dainviare);
            notify(help);
        }

        // Disconnection ------------------
        if(message.getServizio().getType() == StateNumber.disconnection && message.getPlayer() == playerhelp){
            clientConnection.asyncSend(message.getServizio());
            Service dainviare = new Service(StateNumber.active, "def", "def");
            dainviare.setNexTurn();
            fromviewtocontroll help6 = new fromviewtocontroll(message.getPlayer(), dainviare);
            notify(help6);
        }
    }

}
