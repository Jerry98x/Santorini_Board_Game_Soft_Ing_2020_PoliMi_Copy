package it.polimi.ingsw.PSP45.view;

import it.polimi.ingsw.PSP45.model.Player;
import it.polimi.ingsw.PSP45.observer.Observable;
import it.polimi.ingsw.PSP45.observer.Observer;
import it.polimi.ingsw.PSP45.utils.Service;
import it.polimi.ingsw.PSP45.utils.frommodeltoview;
import it.polimi.ingsw.PSP45.utils.fromviewtocontroll;

/**
 * @author Lorenzo Longaretti
 *
 *
 */
public abstract class View extends Observable<fromviewtocontroll> implements Observer<frommodeltoview>
{

    private Player player;

    protected View(Player player){
        this.player = player;
    }

    protected Player getPlayer(){
        return player;
    }

    protected abstract void showMessage(Service message);

    /**
     * @author Lorenzo Longaretti
     * Method that redirects the message to the controller.
     * @param message
     */
    void handleMove(Service message) {
       fromviewtocontroll fornotify = new fromviewtocontroll(player,message);
        notify(fornotify);
    }

}
