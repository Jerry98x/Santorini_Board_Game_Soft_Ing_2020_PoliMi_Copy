package it.polimi.ingsw.PSP45.gui;

import it.polimi.ingsw.PSP45.avviatori.ClientAppGUI;
import it.polimi.ingsw.PSP45.client.ClientGUI;
import it.polimi.ingsw.PSP45.utils.GodName;
import it.polimi.ingsw.PSP45.utils.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 * @author Lorenzo Longaretti
 * @author Andrea Gerosa
 * Controller class for the choice of the first player scene of the gui.
 * It contains methods that handle mouse events on various nodes.
 *
 */
public class firstPlayerChoiceController {
    ClientGUI client = ClientAppGUI.getClient2();
    Service service;
    int dato = 6;

    @FXML
    AnchorPane firstPlayerPane;
    @FXML
    VBox vbox1;
    @FXML
    ImageView image1;
    @FXML
    Label label1;
    @FXML
    RadioButton radio1;
    @FXML
    VBox vbox2;
    @FXML
    ImageView image2;
    @FXML
    Label label2;
    @FXML
    RadioButton radio2;
    @FXML
    VBox vbox3;
    @FXML
    ImageView image3;
    @FXML
    Label label3;
    @FXML
    RadioButton radio3;
    @FXML
    VBox vbox4;
    @FXML
    ImageView image4;
    @FXML
    Label label4;
    @FXML
    RadioButton radio4;
    @FXML
    VBox vbox5;
    @FXML
    ImageView image5;
    @FXML
    Label label5;
    @FXML
    RadioButton radio5;
    @FXML
    ToggleGroup radioGroup;

    /**
     * @author Lorenzo Longaretti
     * Selects the first player (3-players game).
     *
     * @param event mouse click
     */
    @FXML
    public void select1(MouseEvent event){
        dato = 0;
    }

    /**
     * @author Lorenzo Longaretti
     * Selects the first player (2-players game).
     *
     * @param event mouse click
     */
    @FXML
    public void select2(MouseEvent event){
        dato = 0;
    }

    /**
     * @author Lorenzo Longaretti
     * Selects the second player (3-players game).
     *
     * @param event mouse click
     */
    @FXML
    public void select3(MouseEvent event){
        dato = 1;
    }

    /**
     * @author Lorenzo Longaretti
     * Selects the second player (2-players game).
     *
     * @param event mouse click
     */
    @FXML
    public void select4(MouseEvent event){
        dato = 1;
    }

    /**
     * @author Lorenzo Longaretti
     * Selects the third player (3-players game).
     *
     * @param event mouse click
     */
    @FXML
    public void select5(MouseEvent event){
        dato = 2;
    }


    /**
     * @author Lorenzo Longaretti
     * Sends the information about who will start the match to the server, checking which RadioButton is selected.
     *
     * @param event mouse click
     */
    @FXML
    public void check(MouseEvent event){

        if(radioGroup.getSelectedToggle() == radio1 || radioGroup.getSelectedToggle() == radio2 || radioGroup.getSelectedToggle() == radio3 || radioGroup.getSelectedToggle() == radio4 || radioGroup.getSelectedToggle() == radio5) {

            if (!(dato == 6)) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            client.sendFirstPlayer(dato);
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }

                    }
                });
                t.start();
                firstPlayerPane.setMouseTransparent(true);
            }

        }
    }


    /**
     * @author Lorenzo Longaretti
     * Sets this controller in the gui object just after the scene has loaded.
     * It also correctly and symmetrically displays the nodes depending on the number of players.
     *
     */
    public void initialize(){
        gui.setFirstPlayerChoiceController(this);
        service = client.getService();
        if(service.getListGod().size() == 2){
            image2.setImage(activateGod(service.getListGod().get(0)));
            image4.setImage(activateGod(service.getListGod().get(1)));

            label2.setText(service.getPlayersToChoose().get(0));
            label4.setText(service.getPlayersToChoose().get(1));

            vbox2.setVisible(true);
            vbox2.setDisable(false);

            vbox4.setVisible(true);
            vbox4.setDisable(false);
        }
        else{
            image1.setImage(activateGod(service.getListGod().get(0)));
            image3.setImage(activateGod(service.getListGod().get(1)));
            image5.setImage(activateGod(service.getListGod().get(2)));

            label1.setText(service.getPlayersToChoose().get(0));
            label3.setText(service.getPlayersToChoose().get(1));
            label5.setText(service.getPlayersToChoose().get(2));

            vbox1.setVisible(true);
            vbox1.setDisable(false);

            vbox3.setVisible(true);
            vbox3.setDisable(false);

            vbox5.setVisible(true);
            vbox5.setDisable(false);
        }
    }

    /**
     * @author Lorenzo Longaretti
     * Associates the correct Image to the god's name and returns it.
     *
     * @param godName the name of the god
     * @return the Image of the god
     */
    private Image activateGod(GodName godName){
        Image i = new Image("/graphicResources/godCards/apollo.png");
        switch (godName){
            case apollo: i = new Image("/graphicResources/godCards/apollo.png"); return i;
            case artemis: i = new Image("/graphicResources/godCards/artemis.png"); return i;
            case prometheus: i = new Image("/graphicResources/godCards/prometheus.png"); return i;
            case pan: i = new Image("/graphicResources/godCards/pan.png"); return i;
            case minotaur: i = new Image("/graphicResources/godCards/minotaur.png"); return i;
            case hephaestus: i = new Image("/graphicResources/godCards/hephaestus.png"); return i;
            case demeter: i = new Image("/graphicResources/godCards/demeter.png"); return i;
            case athena: i = new Image("/graphicResources/godCards/athena.png"); return i;
            case atlas: i = new Image("/graphicResources/godCards/atlas.png"); return i;
        }
        return i;
    }
}