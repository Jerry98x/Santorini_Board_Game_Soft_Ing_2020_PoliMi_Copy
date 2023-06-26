package it.polimi.ingsw.PSP45.gui;

import it.polimi.ingsw.PSP45.utils.GodName;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Lorenzo Longaretti
 *
 * Class for handling the events from server and storing informations for the gui.
 *
 */
public class gui {
	private static loginController loginController;
	private static playerSetupController playerSetupController;
	private static divinitySetupController divinitySetupController;
	private static MainGameController mainGameController;
	private static firstPlayerChoiceController firstPlayerChoiceController;

	private static ArrayList<GodName> godUsedName = new ArrayList<>();
	private static ArrayList<String> godUsedDescriptions = new ArrayList<>();

	private static GodName yourGod;


	public static void setLg(loginController loginController) {
		gui.loginController = loginController;
	}

	public static void setPlayerSetupController(it.polimi.ingsw.PSP45.gui.playerSetupController playerSetupController) {
		gui.playerSetupController = playerSetupController;
	}

	public static void setDivinitySetupController(it.polimi.ingsw.PSP45.gui.divinitySetupController divinitySetupController) {
		gui.divinitySetupController = divinitySetupController;
	}

	public static void setMainGameController(MainGameController mainGameController) {
		gui.mainGameController = mainGameController;
	}

	public static void setFirstPlayerChoiceController(firstPlayerChoiceController firstPlayerChoiceController) {
		gui.firstPlayerChoiceController = firstPlayerChoiceController;
	}

	public static void changeToPlayerSetup() throws IOException {
		loginController.loadPlayerSetup();
	}

	public static void changeToDivinity() throws IOException {
		playerSetupController.loadDivinitySetup();
	}
	public static void changeToFirstChoice() throws IOException {
		divinitySetupController.loadFirstChoice();
	}
	public static void changeToCoordinateChoice() throws IOException {
		divinitySetupController.loadCoordinateChoice();
	}
	public static void changeToMain() throws IOException {
		if(firstPlayerChoiceController != null){
			divinitySetupController.loadMainGame();
		}
		else{
			divinitySetupController.loadMainGame();
		}
	}
	public static void changeToDisconnect() throws IOException {
		loginController.loadDisconnection();
	}
	public static void goToMove(){
		mainGameController.updateBoard();
		mainGameController.availableWorker();
	}
	public static void goToBuild(){
		mainGameController.updateBoard();
		mainGameController.availableBuild();
	}
	public static void goToEnd(){
		mainGameController.matchEnded();
	}
	public static void updateBoard() throws IOException {
		if(mainGameController != null) {
			mainGameController.updateBoard();
		}
		else{
			changeToMain();
		}
	}

	public static void addDivinity(GodName god) {
		godUsedName.add(god);
	}
	public static void addGodDescription(String description) {
		godUsedDescriptions.add(description);
	}
	public static void clearDivinity(){
		godUsedName.clear();
		godUsedDescriptions.clear();
	}
	public static ArrayList<GodName> getGodUsedName() {
		return godUsedName;
	}
	public static ArrayList<String> getGodUsedDescriptions() {
		return godUsedDescriptions;
	}

	public static GodName getYourGod() {
		return yourGod;
	}

	public static void setYourGod(GodName yourGod) {
		gui.yourGod = yourGod;
	}

}
