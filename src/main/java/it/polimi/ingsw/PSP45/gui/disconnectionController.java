package it.polimi.ingsw.PSP45.gui;

import it.polimi.ingsw.PSP45.avviatori.ClientAppGUI;
import it.polimi.ingsw.PSP45.client.ClientGUI;
import it.polimi.ingsw.PSP45.model.Coordinate;
import it.polimi.ingsw.PSP45.model.LightBoard;
import it.polimi.ingsw.PSP45.utils.Color;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Lorenzo Longaretti
 * Controller class for the unexpected disconnection phase.
 *
 */
public class disconnectionController {


	@FXML
	AnchorPane disconnectionPane;
	@FXML
	Button exitButton;
	@FXML
	Button newGameButton;


	/**
	 * @author Andrea Gerosa
	 * @author Lorenzo Longaretti
	 * Handles the click on the "New Game" button.
	 *
	 * @param event mouse click
	 */
	@FXML
	public void newGameClicked(MouseEvent event) throws IOException {
		Pane pane = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
		disconnectionPane.getChildren().setAll(pane);
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.setHeight(520);
		stage.setWidth(800);
	}

	/**
	 * @author Andrea Gerosa
	 * Handles the click on the "Exit" button, closing the window.
	 *
	 * @param event mouse click
	 */
	@FXML
	public void exitClicked(MouseEvent event) {
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}
}