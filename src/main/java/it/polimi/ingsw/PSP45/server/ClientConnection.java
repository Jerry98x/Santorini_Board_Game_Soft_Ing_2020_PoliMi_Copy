package it.polimi.ingsw.PSP45.server;

import it.polimi.ingsw.PSP45.observer.Observer;
import it.polimi.ingsw.PSP45.utils.Service;

/**
 * @author Lorenzo Longaretti
 *
 * Interface that allowed the conversation between client and server
 */
public interface ClientConnection{

    void closeConnection();

    void addObserver(Observer<Service> observer);

    void asyncSend(Object message);

    void closeforwin(Service service);
}
