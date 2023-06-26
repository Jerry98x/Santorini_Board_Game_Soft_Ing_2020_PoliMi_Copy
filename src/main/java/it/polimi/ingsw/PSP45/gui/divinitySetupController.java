package it.polimi.ingsw.PSP45.gui;

import it.polimi.ingsw.PSP45.avviatori.ClientAppGUI;
import it.polimi.ingsw.PSP45.client.ClientGUI;
import it.polimi.ingsw.PSP45.utils.GodDescription;
import it.polimi.ingsw.PSP45.utils.GodName;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Lorenzo Longaretti
 * @author Andrea Gerosa
 * Controller class for the divinity setup scene of the gui.
 * It contains methods that handle mouse events on various nodes and the steps of the divinities selection phase.
 *
 *
 */
public class divinitySetupController {

	ClientGUI client = ClientAppGUI.getClient2();
	GodName godName;
	String godDescription;
	ArrayList<GodName> godNames = new ArrayList<>();
	ArrayList<String> godDescriptions = new ArrayList<>();
	int more = 0;

	@FXML
	Pane godsPane;
	@FXML
	VBox vb;
	@FXML
	Label topText;
	@FXML
	ImageView apollo;
	@FXML
	ImageView artemis;
	@FXML
	ImageView athena;
	@FXML
	ImageView atlas;
	@FXML
	ImageView demeter;
	@FXML
	ImageView hephaestus;
	@FXML
	ImageView minotaur;
	@FXML
	ImageView pan;
	@FXML
	ImageView prometheus;
	@FXML
	ImageView selectedGod;
	@FXML
	TextArea description;
	@FXML
	Button confirm;

	public void setGodName(GodName godName) {
		this.godName = godName;
	}

	/**
	 * @author Lorenzo Longaretti
	 * Method that sets visible all the chosen gods' ImageView whenever called.
	 *
	 */
	@FXML
	private void choose(){
		client = ClientAppGUI.getClient2();
		ArrayList<GodName> listGod = client.getGodName();
		for (GodName god : listGod) {
			switch (god){
				case apollo: apollo.setDisable(false); apollo.setOpacity(1); break;
				case artemis: artemis.setDisable(false); artemis.setOpacity(1); break;
				case athena: athena.setDisable(false); athena.setOpacity(1); break;
				case atlas: atlas.setDisable(false); atlas.setOpacity(1); break;
				case demeter: demeter.setDisable(false);demeter.setOpacity(1); break;
				case hephaestus: hephaestus.setDisable(false); hephaestus.setOpacity(1); break;
				case minotaur: minotaur.setDisable(false); minotaur.setOpacity(1); break;
				case pan: pan.setDisable(false);pan.setOpacity(1); break;
				case prometheus: prometheus.setDisable(false);prometheus.setOpacity(1); break;
				default: break;
			}
		}
	}


	/**
	 * @author  Lorenzo Longaretti
	 * Shows a bigger Image of the selected god (Apollo) and a description of its power.
	 * 
	 * @param event mouse click
	 */
	@FXML
	public void chosenApollo(MouseEvent event){

		selectedGod.setImage(apollo.getImage());

		description.setText(GodDescription.apolloDesc);
		description.setVisible(true);

		setGodName(GodName.apollo);
		confirm.setVisible(true);
	}

	/**
	 * @author  Lorenzo Longaretti
	 * Shows a bigger Image of the selected god (Artemis) and a description of its power.
	 *
	 * @param event mouse click
	 */
	@FXML
	public void chosenArtemis(MouseEvent event){

		selectedGod.setImage(artemis.getImage());

		description.setText(GodDescription.artemisDesc);
		description.setVisible(true);

		setGodName(GodName.artemis);
		confirm.setVisible(true);
	}

	/**
	 * @author  Lorenzo Longaretti
	 * Shows a bigger Image of the selected god (Athena) and a description of its power.
	 *
	 * @param event mouse click
	 */
	@FXML
	public void chosenAthena(MouseEvent event){

		selectedGod.setImage(athena.getImage());

		description.setText(GodDescription.athenaDesc);
		description.setVisible(true);

		setGodName(GodName.athena);
		confirm.setVisible(true);
	}
	
	/**
	 * @author  Lorenzo Longaretti
	 * Shows a bigger Image of the selected god (Atlas) and a description of its power.
	 *
	 * @param event mouse click
	 */
	@FXML
	public void chosenAtlas(MouseEvent event){

		selectedGod.setImage(atlas.getImage());

		description.setText(GodDescription.atlasDesc);
		description.setVisible(true);

		setGodName(GodName.atlas);
		confirm.setVisible(true);
	}

	/**
	 * @author  Lorenzo Longaretti
	 * Shows a bigger Image of the selected god (Demeter) and a description of its power.
	 *
	 * @param event mouse click
	 */
	@FXML
	public void chosenDemeter(MouseEvent event){

		selectedGod.setImage(demeter.getImage());

		description.setText(GodDescription.demeterDesc);
		description.setVisible(true);

		setGodName(GodName.demeter);
		confirm.setVisible(true);
	}

	/**
	 * @author  Lorenzo Longaretti
	 * Shows a bigger Image of the selected god (Hephaestus) and a description of its power.
	 *
	 * @param event mouse click
	 */
	@FXML
	public void chosenHephaestus(MouseEvent event){

		selectedGod.setImage(hephaestus.getImage());

		description.setText(GodDescription.hephaestusDesc);
		description.setVisible(true);

		setGodName(GodName.hephaestus);
		confirm.setVisible(true);
	}

	/**
	 * @author  Lorenzo Longaretti
	 * Shows a bigger Image of the selected god (Minotaur) and a description of its power.
	 *
	 * @param event mouse click
	 */
	@FXML
	public void chosenMinotaur(MouseEvent event){

		selectedGod.setImage(minotaur.getImage());

		description.setText(GodDescription.minotaurDesc);
		description.setVisible(true);

		setGodName(GodName.minotaur);
		confirm.setVisible(true);
	}

	/**
	 * @author  Lorenzo Longaretti
	 * Shows a bigger Image of the selected god (Pan) and a description of its power.
	 *
	 * @param event mouse click
	 */
	@FXML
	public void chosenPan(MouseEvent event){

		selectedGod.setImage(pan.getImage());

		description.setText(GodDescription.panDesc);
		description.setVisible(true);

		setGodName(GodName.pan);
		confirm.setVisible(true);
	}

	/**
	 * @author  Lorenzo Longaretti
	 * Shows a bigger Image of the selected god (Prometheus) and a description of its power.
	 *
	 * @param event mouse click
	 */
	@FXML
	public void chosenPrometheus(MouseEvent event){

		selectedGod.setImage(prometheus.getImage());

		description.setText(GodDescription.prometheusDesc);
		description.setVisible(true);

		setGodName(GodName.prometheus);
		confirm.setVisible(true);
	}

	/**
	 * @author Lorenzo Longaretti
	 * Handles the sending of the divinities from the player who chooses all of them and the other players.
	 *
	 * @param event mouse click
	 */
	@FXML
	public void sendDivinity(MouseEvent event){

		if(more == 0){
			if(godName != null)
			{

				lowerGodImageOpacity();
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							client = ClientAppGUI.getClient2();
							client.setGodName(godName);
						}
						catch (Exception e){
							System.err.println(e.getMessage());
						}

					}
				});
				t.start();

				gui.setYourGod(godName);

				for(GodName god : godNames) { //client.service. arraylist con tutte le divinità
					gui.addDivinity(god);
				}
				for(String description: godDescriptions) {
					gui.addGodDescription(description);
				}

				godsPane.setMouseTransparent(true);

			}
			else {
				System.out.println("godname is null");
			}
		}
		else{
			//----
			if(godName != null)
			{
				if(godNames.size() == 0 ){
					godNames.add(godName);
					godDescriptions.add(godDescription);
					disableGodImage();
				}
				else if(godNames.size() == 1 && client.getService().getIntservizio() == 3){
					godNames.add(godName);
					godDescriptions.add(godDescription);
					disableGodImage();
				}
				else{
					godNames.add(godName);
					godDescriptions.add(godDescription);
					disableGodImage();
					Thread t = new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								client = ClientAppGUI.getClient2();
								client.setGodsName(godNames);
							}
							catch (Exception e){
								System.err.println(e.getMessage());
							}

						}
					});
					t.start();


					godsPane.setMouseTransparent(true);

				}
				godName = null;
			}
			else {
				System.out.println("godname is null");
			}
		}

	}

	/**
	 * @author Lorenzo Longaretti
	 * Changes this scene to the one where the first player chooses who will start the game.
	 *
	 * @throws IOException
	 */
	@FXML
	public void loadFirstChoice() throws IOException {

		godsPane.setMouseTransparent(false);
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXML/firstPlayerChoice.fxml"));
		//playerSetupController help = FXMLLoader();
		godsPane.getChildren().setAll(pane);
		Stage stage = (Stage) pane.getScene().getWindow();

	}

	/**
	 * @author Lorenzo Longaretti
	 * Changes this scene to the one where the players position their workes on the board.
	 *
	 * @throws IOException
	 */
	@FXML
	public void loadCoordinateChoice() throws IOException {

		godsPane.setMouseTransparent(false);
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXML/preMainGame.fxml"));
		godsPane.getChildren().setAll(pane);
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.setHeight(750);
		stage.setWidth(1280);
	}


	/**
	 * @author Andrea Gerosa
	 * Changes this scene to the main scene of the game, with the board and other informations.
	 *
	 * @throws IOException
	 */
	@FXML
	public void loadMainGame() throws IOException {

		godsPane.setMouseTransparent(false);
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/FXML/mainGame.fxml"));
		godsPane.getChildren().setAll(pane);
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.setHeight(750);
		stage.setWidth(1280);
	}

	/**
	 * @author Lorenzo Longaretti
	 * Sets this controller in the gui object just after the scene has loaded.
	 * It also calls the choose method for showing the chosen gods and
	 * shows the most appropriate instruction for each step of this phase of the game.
	 *
	 */
	public void initialize(){

		godsPane.setMouseTransparent(false);
		disableOpaqueGod();
		choose();

		more = client.getService().getIntservizio();

		if(more != 0) {
			topText.setText("Choose the divinities for this match:");
			VBox.setMargin(topText, new Insets(0,0,-10,100));
		}
		else {
			topText.setText("Choose your divinity:");
			VBox.setMargin(topText, new Insets(0,0,-10,200));
		}

		gui.setDivinitySetupController(this);
	}

	/**
	 * @author Andrea Gerosa
	 * Sets the Image of the selected god not visible and disable.
	 *
	 */
	public void disableGodImage() {
		if(selectedGod.getImage() == apollo.getImage()) {
			apollo.setVisible(false);
			apollo.setDisable(true);
		}
		else if(selectedGod.getImage() == artemis.getImage()) {
			artemis.setVisible(false);
			artemis.setDisable(true);
		}
		else if(selectedGod.getImage() == athena.getImage()) {
			athena.setVisible(false);
			athena.setDisable(true);
		}
		else if(selectedGod.getImage() == atlas.getImage()) {
			atlas.setVisible(false);
			atlas.setDisable(true);
		}
		else if(selectedGod.getImage() == demeter.getImage()) {
			demeter.setVisible(false);
			demeter.setDisable(true);
		}
		else if(selectedGod.getImage() == hephaestus.getImage()) {
			hephaestus.setVisible(false);
			hephaestus.setDisable(true);
		}
		else if(selectedGod.getImage() == minotaur.getImage()) {
			minotaur.setVisible(false);
			minotaur.setDisable(true);
		}
		else if(selectedGod.getImage() == pan.getImage()) {
			pan.setVisible(false);
			pan.setDisable(true);
		}
		else if(selectedGod.getImage() == prometheus.getImage()) {
			prometheus.setVisible(false);
			prometheus.setDisable(true);
		}
	}

	/**
	 * @author Andrea Gerosa
	 * Sets the opacity of the gods' ImageViews down to 0.5 if it's higher.
	 *
	 */
	public void lowerGodImageOpacity() {
		if(apollo.getOpacity() > 0.5) {
			apollo.setOpacity(0.5);
		}
		if(artemis.getOpacity() > 0.5) {
			artemis.setOpacity(0.5);
		}
		if(athena.getOpacity() > 0.5) {
			athena.setOpacity(0.5);
		}
		if(atlas.getOpacity() > 0.5) {
			atlas.setOpacity(0.5);
		}
		if(demeter.getOpacity() > 0.5) {
			demeter.setOpacity(0.5);
		}
		if(hephaestus.getOpacity() > 0.5) {
			hephaestus.setOpacity(0.5);
		}
		if(minotaur.getOpacity() > 0.5) {
			minotaur.setOpacity(0.5);
		}
		if(pan.getOpacity() > 0.5) {
			pan.setOpacity(0.5);
		}
		if(prometheus.getOpacity() > 0.5) {
			prometheus.setOpacity(0.5);
		}
	}

	/**
	 * @author Andrea Gerosa
	 * Disables the gods' ImageViews whose opacity is 0.5.
	 *
	 */
	public void disableOpaqueGod() {
		if(apollo.getOpacity() == 0.5) {
			apollo.setDisable(true);
		}
		if(artemis.getOpacity() == 0.5) {
			artemis.setDisable(true);
		}
		if(athena.getOpacity() == 0.5) {
			athena.setDisable(true);
		}
		if(atlas.getOpacity() == 0.5) {
			atlas.setDisable(true);
		}
		if(demeter.getOpacity() == 0.5) {
			demeter.setDisable(true);
		}
		if(hephaestus.getOpacity() == 0.5) {
			hephaestus.setDisable(true);
		}
		if(minotaur.getOpacity() == 0.5) {
			minotaur.setDisable(true);
		}
		if(pan.getOpacity() == 0.5) {
			pan.setDisable(true);
		}
		if(prometheus.getOpacity() == 0.5) {
			prometheus.setDisable(true);
		}
	}

}
