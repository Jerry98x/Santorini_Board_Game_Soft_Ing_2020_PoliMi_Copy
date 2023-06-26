package it.polimi.ingsw.PSP45.utils;

import it.polimi.ingsw.PSP45.model.Player;

/**
 * @author Lorenzo Longaretti
 * Class of objects that allow the comunication between the model and the view.
 *
 */
public class frommodeltoview {


    Player player;
    Service Servizio;

    /**
     * @author Lorenzo Longaretti
     * Class constructor
     *
     * @param player
     * @param servizio
     */
    public frommodeltoview(Player player, Service servizio) {
        this.player = player;
        Servizio = servizio;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Service getServizio() {
        return Servizio;
    }

    public void setServizio(Service servizio) {
        Servizio = servizio;
    }
}
