package it.polimi.ingsw.PSP45.avviatori;

import it.polimi.ingsw.PSP45.client.ClientGUI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import it.polimi.ingsw.PSP45.gui.*;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * @author Lorenzo Longaretti
 * @author Andrea Gerosa
 *
 * Class called by the game launcher (gui) that runs the client and start the gui application.
 *
 */
public class ClientAppGUI extends Application {
    private static loginController lg = new loginController();
    static ClientGUI client2;

    public static void startGUI(String[] args) {

        launch(args);

    }

    /**
     * @author Andrea Gerosa
     *
     * Starts the gui application.
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));


        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Santorini");
        primaryStage.getIcons().add(new Image("graphicResources/title_boat_left.png"));
        primaryStage.setResizable(false);



        primaryStage.show();

        primaryStage.setOnCloseRequest((WindowEvent e) -> {
            Platform.exit();
            System.exit(0);
        });

    }

    /**
     * @author Andrea Gerosa
     * @author Lorenzo Longaretti
     * Runs the client.
     *
     * @param IP
     * @param port
     */
    public static void clientStart(String IP, String port) {

        lg.setIP(IP);
        lg.setPort(port);

        System.out.println(lg.getIP());
        System.out.println(lg.getPort());

        ClientGUI client = new ClientGUI(lg.getIP(), Integer.parseInt(lg.getPort()));

        setClient2(client);
        try{
            client.run();
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }

    }

    public static ClientGUI getClient2() {

        return client2;
    }

    public static void setClient2(ClientGUI client2) {
        ClientAppGUI.client2 = client2;
    }

}

