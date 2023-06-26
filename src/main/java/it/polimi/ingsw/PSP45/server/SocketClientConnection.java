package it.polimi.ingsw.PSP45.server;

import it.polimi.ingsw.PSP45.model.Board;
import it.polimi.ingsw.PSP45.observer.Observable;
import it.polimi.ingsw.PSP45.utils.Service;
import it.polimi.ingsw.PSP45.utils.StateNumber;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Lorenzo Longaretti
 * Class that stores the connection between Sever and Client
 */
public class SocketClientConnection extends Observable<Service> implements ClientConnection, Runnable {

    private Socket socket;
    private ObjectOutputStream out;
    private Server server;
    private Service serviceBackup;



    private boolean active = true;
    private Object Board = new Board();

    public SocketClientConnection(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    private synchronized boolean isActive(){
        return active;
    }

    /**
     * Method that send message to client
     * @param message
     */
    private synchronized void send(Object message) {
        try {
            out.reset();
            out.writeObject(message);
            System.out.println("messaggio inviato");
            out.flush();
            serviceBackup = (Service) message;
        } catch(IOException e){
            System.out.println(e.getMessage());
            System.out.println(e.hashCode());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());

        }

    }

    @Override
    public synchronized void closeConnection() {

        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error when closing socket!");
        }
        active = false;
    }

    private void close() {
        closeConnection();
        System.out.println("Deregistering client...");
        server.deregisterConnection(this);
        System.out.println("Done!");
    }

    @Override
    public void asyncSend(final Object message){
        new Thread(new Runnable() {
            @Override
            public void run() {
                send(message);
            }
        }).start();


    }

    /**
     * @author Lorenzo Longaretti
     * Method that send the win/lose message to the client
     * @param service
     */
    @Override
    public void closeforwin(Service service) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                send(service);
                close();
            }
        }).start();
    }

    @Override
    public void run() {
        Service servizio = new Service(StateNumber.active,"come ti chiami","rispondi");
        servizio.setForNameAndNumber();
        Service serviziettoserver = new Service(StateNumber.active,"default","default");
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        asyncSend(servizio);

        try{
            try{
                InputStream in3 = socket.getInputStream();


                ObjectInputStream in2 = new ObjectInputStream(in3);
                socket.setSoTimeout(4000);
                while(isActive()) {
                    serviziettoserver = (Service) in2.readObject();

                    if(Check(serviziettoserver)){
                        if(serviziettoserver.getType() == StateNumber.exit) {

                        }
                        else if (serviziettoserver.getType() == StateNumber.setcoordinatereply){
                            server.handleCoordinate(this,serviziettoserver);
                        }
                        else if(serviziettoserver.getType() == StateNumber.replysetgod){
                                if(serviziettoserver.getIntservizio() != 0){
                                server.handleGodLobbyMore(this,serviziettoserver.getListGod());
                            }
                            else{
                                server.handleGodLobby(this,serviziettoserver.getListGod().get(0));
                            }


                        }
                        else if(serviziettoserver.getType() == StateNumber.firstplayerreceive){
                            server.handleFirst(this,serviziettoserver);
                        }
                        else if(serviziettoserver.getType() == StateNumber.nameResponse){
                            server.lobby(this, serviziettoserver.getWhatshouldido(),serviziettoserver.getIntservizio());

                        }
                        else if (serviziettoserver.getType() == StateNumber.ghost){

                        }

                        else{
                            if(serviziettoserver.getType() != StateNumber.pingreceive){
                                notify(serviziettoserver);
                            }
                            else if(serviziettoserver.getType() == StateNumber.pingreceive){

                            }
                        }
                    }
                    else{
                        send(serviceBackup);
                    }

                }

            } catch (IOException e) {
                System.out.println("a client is disconnected");
                server.Error(this);
                e.printStackTrace();
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }finally{
            System.out.println("client " + socket );
            close();
        }


    }

    /**
     * @author Lorenzo Longaretti
     * @author Andrea Gerosa
     *
     * Method that check the validity of the message from CLient
     * @param service
     * @return
     */
    public Boolean Check(Service service){
        switch(service.getType()){
            case nameResponse:
                if(!(service.getIntservizio() == 2 || service.getIntservizio() == 3) || service.getWhatshouldido() == null) {
                    return false;
                }
                break;
            case replysetgod:
                if(!(service.getIntservizio() == 0 || service.getIntservizio() == 3) || !(service.getListGod().size() <= 3)) {
                    return false;
                }
                break;
            case firstplayer:
                if(!(service.getIntservizio() == 0 || service.getIntservizio() == 1 || service.getIntservizio() == 2)) {
                    return false;
                }
            case readyToMove:
                if(!(service.getIntservizio() == 0 || service.getIntservizio() == 1) || service.getAvailableCells().size() != 1) {
                    return false;
                }
                break;
            case readyToBuild:
                if(service.getAvailableCells2().size() != 1) {
                    return false;
                }
                break;
            case pingreceive:
                return true;
            case setcoordinate:
                if(service.getAvailableCells().size() != 2) {
                    return false;
                }
                break;
        }

        return true;
    }
}
