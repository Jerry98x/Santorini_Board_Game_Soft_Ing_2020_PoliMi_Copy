package it.polimi.ingsw.PSP45.avviatori;

import it.polimi.ingsw.PSP45.server.Server;

import java.io.IOException;

/**
 * @author Lorenzo Longaretti
 *
 * Server launcher
 *
 */
public class ServerApp
{
    /**
     * @author Lorenzo Longaretti
     *
     * Main method that runs the server.
     *
     * @param args
     */
    public static void main( String[] args )
    {
        Server server;
        try {
            server = new Server();
            server.run();
        } catch (IOException e) {
            System.err.println("Impossible to initialize the server: " + e.getMessage() + "!");
        }
    }
}
