package it.polimi.ingsw.PSP45.gui;

import it.polimi.ingsw.PSP45.avviatori.ClientAppGUI;
import it.polimi.ingsw.PSP45.client.Client;
import it.polimi.ingsw.PSP45.client.ClientGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Andrea Gerosa
 * Controller class for the login scene of the gui.
 * It contains methods that handle mouse events on various nodes and a method that starts the client.
 *
 */
public class loginController {
	Stage stage;
	private String IP;
	private String port;
	private ImageView img = new ImageView();

	@FXML
	Pane rootPane;


	@FXML
	private ImageView btn_play;

	@FXML
	private TextField IPTextField;
	@FXML
	private TextField portTextField;


	@FXML
	private StackPane stack;


	public loginController(){
		this.img = btn_play;
	}


	public Stage getStage() {
		return stage;
	}

	public ImageView getBtn_play() {
		return btn_play;
	}

	public ImageView getImg() {
		return img;
	}

	public String getIP() {
		return IP;
	}

	public String getPort() {
		return port;
	}

	public void setIP(String IP) {
		this.IP = IP;
	}

	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @author Andrea Gerosa
	 * Sets the informations about IP address and port and calls a method that starts the client.
	 * Then calls the method used to change scene.
	 *
	 * @param event mouse click
	 * @throws IOException
	 */
	public void mouseClicked(MouseEvent event) throws IOException {
		System.out.println("Hello world!");

		IP = this.IPTextField.getText();
		port = this.portTextField.getText();

		System.out.println(IP);
		System.out.println(port);


		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ClientAppGUI.clientStart(IP, port);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
				}

			}
		});
		t.start();

		//loadPlayerSetup();

		System.out.println("ESCE DA LOGIN CONTROLLER");

	}


	/**
	 * @author Andrea Gerosa
	 * Method that expands the "play" button's size when pressed.
	 *
	 * @param event cursor entered the ImageView
	 */
	@FXML
	public void mousePressed(MouseEvent event) {
		this.btn_play.setLayoutX(320.0);
		this.btn_play.setLayoutY(310.0);
		this.btn_play.setFitHeight(180);
		this.btn_play.setFitWidth(240);
	}

	/**
	 * @author Andrea Gerosa
	 * Method that reduce the "play" button's size when released.
	 *
	 * @param event cursor exited the ImageView
	 */
	@FXML
	public void mouseReleased(MouseEvent event) {
		this.btn_play.setLayoutX(333.0);
		this.btn_play.setLayoutY(326.0);
		this.btn_play.setFitHeight(150);
		this.btn_play.setFitWidth(200);
	}


	/**
	 * @author Andrea Gerosa
	 * Changes the scene to the player setup scene.
	 *
	 * @throws IOException
	 */
	@FXML
	public void loadPlayerSetup() throws IOException {
		Pane pane = FXMLLoader.load(getClass().getResource("/FXML/playerSetup.fxml"));
		//playerSetupController help = FXMLLoader();
		rootPane.getChildren().setAll(pane);
	}

	/**
	 *
	 * @author Lorenzo Longaretti
	 * Changes the scene to the disconnection scene.
	 *
	 * @throws IOException
	 */
	@FXML
	public void loadDisconnection() throws IOException {
		Pane pane = FXMLLoader.load(getClass().getResource("/FXML/disconnection.fxml"));
		//playerSetupController help = FXMLLoader();
		rootPane.getChildren().setAll(pane);
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.setHeight(740);
		stage.setWidth(1280);
	}


	/**
	 * @author Lorenzo Longaretti
	 * Sets this controller in the gui object just after the scene has loaded.
	 *
	 */
	public void initialize() {
		gui.setLg(this);
	}


}