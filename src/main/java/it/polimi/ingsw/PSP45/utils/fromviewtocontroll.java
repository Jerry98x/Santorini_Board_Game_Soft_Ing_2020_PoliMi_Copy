package it.polimi.ingsw.PSP45.utils;

import it.polimi.ingsw.PSP45.model.Player;

/**
 * @author Lorenzo Longaretti
 * Class of objects that allow the comunication between the view and the controller.
 *
 */
public class fromviewtocontroll {

    //devo inviare il player id e il servizio che gli è arrivato

    Player player;
    Service service;

    /**
     * @author Lorenzo Longaretti
     * Class constructor
     *
     * @param player
     * @param service
     */
    public fromviewtocontroll(Player player, Service service) {
        this.player = player;
        this.service = service;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
