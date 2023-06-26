package it.polimi.ingsw.PSP45.gui;

import it.polimi.ingsw.PSP45.avviatori.ClientAppGUI;
import it.polimi.ingsw.PSP45.client.ClientGUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Andrea Gerosa
 * Controller class for the player setup scene of the gui.
 * It contains methods that handle mouse events on various nodes.
 *
 */
public class playerSetupController {


    ClientGUI client = ClientAppGUI.getClient2();

    @FXML
    Pane secondPane;

    @FXML
    TextField nameText;

    @FXML
    ToggleGroup name;

    @FXML
    RadioButton radio2;

    @FXML
    RadioButton radio3;

    @FXML
    Button nextButton;


    /**
     * @author Lorenzo Longaretti
     * @author Andrea Gerosa
     *
     * Checks name and number of players from the nodes and calls the service for the client.
     *
     * @param event mouse click
     * @throws IOException
     */
    @FXML
    public void mouseName(MouseEvent event) throws IOException {
        client = ClientAppGUI.getClient2();

        if((name.getSelectedToggle() == radio2 || name.getSelectedToggle() == radio3) && !nameText.getText().equals("")){

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int number;
                        if (radio2.isSelected()) {
                            number = 2;
                        }
                        else {
                            number = 3;
                        }
                        System.out.println("non prima di null");
                        System.out.println(nameText.getText());
                        client.setNameAndNumber(nameText.getText(), number);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }


                }
            });
            t.start();

            secondPane.setMouseTransparent(true);
        }

    }

    public void setClient(ClientGUI client) {
        this.client = client;
    }

    /**
     * @author Andrea Gerosa
     * Changes the scene to the player setup scene.
     *
     * @throws IOException
     */
    @FXML
    public void loadDivinitySetup() throws IOException {
        secondPane.setMouseTransparent(false);
        Pane pane = FXMLLoader.load(getClass().getResource("/FXML/divinitySetup.fxml"));
        secondPane.getChildren().setAll(pane);

    }

    /**
     * @author Lorenzo Longaretti
     * Sets this controller in the gui object just after the scene has loaded.
     *
     */
    public void initialize() {
        gui.setPlayerSetupController(this);
        secondPane.setMouseTransparent(false);
    }
}