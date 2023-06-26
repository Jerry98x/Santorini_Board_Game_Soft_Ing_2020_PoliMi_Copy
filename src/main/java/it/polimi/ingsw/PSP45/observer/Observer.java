package it.polimi.ingsw.PSP45.observer;

/**
 * @author Lorenzo Longaretti
 *
 * Interface that implements the observer-observable pattern
 * @param <T>
 */
public interface Observer<T> {

    void update(T message);

}
