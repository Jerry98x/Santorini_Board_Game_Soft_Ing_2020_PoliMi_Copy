package it.polimi.ingsw.PSP45.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lorenzo Longaretti
 * Class that implements the observable-observable pattern
 *
 * @param <T>
 */
public class Observable<T> {

    private final List<Observer<T>> observers = new ArrayList<>();

    /**
     * @author Lorenzo Longaretti
     * Adds an observer to the obeservers list.
     *
     * @param observer
     */
    public void addObserver(Observer<T> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    /**
     * @author Lorenzo Longaretti
     * Removes an observer from the obeservers list.
     *
     * @param observer
     */
    public void removeObserver(Observer<T> observer){
        synchronized (observers) {
            observers.remove(observer);
        }
    }

    /**
     * @author Lorenzo Longaretti
     * Notifies an observer.
     *
     */
    protected void notify(T message){
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.update(message);
            }
        }
    }

}
