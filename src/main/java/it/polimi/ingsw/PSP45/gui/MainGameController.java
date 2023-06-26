package it.polimi.ingsw.PSP45.gui;

import it.polimi.ingsw.PSP45.avviatori.ClientAppGUI;
import it.polimi.ingsw.PSP45.client.ClientGUI;
import it.polimi.ingsw.PSP45.model.Coordinate;
import it.polimi.ingsw.PSP45.model.LightBlock;
import it.polimi.ingsw.PSP45.model.LightBoard;
import it.polimi.ingsw.PSP45.utils.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Andrea Gerosa
 * @author Lorenzo Longaretti
 * Controller class for the main scene of the gui, where the match takes place.
 * The board and all the information about the match are shown in this scene and it also shows the win and lose screens.
 *
 */
public class MainGameController {
	private ClientGUI client;
	private Boolean selectedPlayer = false;
	private LightBoard lightBoard;
	private LightBlock movedWorker;
	private ArrayList<LightBlock> whereToMove = new ArrayList<>();


	@FXML
	AnchorPane mainGamePane;

	@FXML
	StackPane stack0_0;
	@FXML
	StackPane stack0_1;
	@FXML
	StackPane stack0_2;
	@FXML
	StackPane stack0_3;
	@FXML
	StackPane stack0_4;
	@FXML
	StackPane stack1_0;
	@FXML
	StackPane stack1_1;
	@FXML
	StackPane stack1_2;
	@FXML
	StackPane stack1_3;
	@FXML
	StackPane stack1_4;
	@FXML
	StackPane stack2_0;
	@FXML
	StackPane stack2_1;
	@FXML
	StackPane stack2_2;
	@FXML
	StackPane stack2_3;
	@FXML
	StackPane stack2_4;
	@FXML
	StackPane stack3_0;
	@FXML
	StackPane stack3_1;
	@FXML
	StackPane stack3_2;
	@FXML
	StackPane stack3_3;
	@FXML
	StackPane stack3_4;
	@FXML
	StackPane stack4_0;
	@FXML
	StackPane stack4_1;
	@FXML
	StackPane stack4_2;
	@FXML
	StackPane stack4_3;
	@FXML
	StackPane stack4_4;


	@FXML
	VBox divVbox;

	@FXML
	GridPane board;
	@FXML
	AnchorPane wlPane;
	@FXML
	ImageView winlose;
	@FXML
	Label winloseText;
	@FXML
	Button newGameButton;
	@FXML
	Button exitButton;

	@FXML
	VBox instructionsVbox;
	@FXML
	TextArea instructions;
	@FXML
	HBox responseHbox;
	@FXML
	RadioButton radioDomeYes;
	@FXML
	RadioButton radioDomeNo;
	@FXML
	Button confirmButton;

	@FXML
	ImageView div1;
	@FXML
	ImageView div2;
	@FXML
	ImageView div3;
	@FXML
	TextArea desc1;
	@FXML
	TextArea desc2;
	@FXML
	TextArea desc3;
	@FXML
	Label label2ndPlayer;
	@FXML
	Label label3rdPlayer;


	Image winImage = new Image("graphicResources/title_sky.png");
	Image loseImage = new Image("graphicResources/Hades_Lava.png");


	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (0,0).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickzerozero(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(0,0);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();

	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (0,1).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickzerone(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(0,1);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (0,2).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickzerotwo(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(0,2);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (0,3).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickzerothree(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(0,3);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (0,4).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickzerofour(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(0,4);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (1,0).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickonezero(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(1,0);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (1,1).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickoneone(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(1,1);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (1,2).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickonetwo(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(1,2);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (1,3).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickonethree(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(1,3);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (1,4).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickonefour(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(1,4);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (2,0).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clicktwozero(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(2,0);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (2,1).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clicktwoone(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(2,1);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (2,2).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clicktwotwo(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(2,2);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (2,3).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clicktwothree(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(2,3);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (2,4).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clicktwofour(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(2,4);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (3,0).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickthreezero(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(3,0);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (3,1).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickthreeone(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(3,1);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (3,2).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickthreetwo(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(3,2);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (3,3).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickthreethree(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(3,3);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (3,4).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickthreefour(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(3,4);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (4,0).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickfourzero(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(4,0);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (4,1).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickfourone(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(4,1);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (4,2).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickfourtwo(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(4,2);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (4,3).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickfourthree(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(4,3);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the click on cell (4,4).
	 *
	 * @param mouseEvent mouse click
	 */
	@FXML
	public void clickfourfour(MouseEvent mouseEvent) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					
					Handle(4,4);
				}
				catch (Exception e){
					System.err.println(e.getMessage());
					
				}

			}
		});
		t.start();
	}

	/**
	 * @author Lorenzo Longaretti
	 * Gets the correct element in the StackPane for every cell of the GridPane that represents the board
	 * and returns it.
	 *
	 * @param x
	 * @param j
	 * @return
	 */
	public StackPane getStack(int x, int j){
		StackPane toReturn = new StackPane();
		switch (x){
			case 0: switch (j){
				case 0: toReturn = stack0_0; break;
				case 1: toReturn = stack0_1; break;
				case 2: toReturn = stack0_2; break;
				case 3: toReturn = stack0_3; break;
				case 4: toReturn = stack0_4; break;
			} break;
			case 1: switch (j){
				case 0: toReturn = stack1_0; break;
				case 1: toReturn = stack1_1; break;
				case 2: toReturn = stack1_2; break;
				case 3: toReturn = stack1_3; break;
				case 4: toReturn = stack1_4; break;
			} break;
			case 2: switch (j){
				case 0: toReturn = stack2_0; break;
				case 1: toReturn = stack2_1; break;
				case 2: toReturn = stack2_2; break;
				case 3: toReturn = stack2_3; break;
				case 4: toReturn = stack2_4; break;
			} break;
			case 3: switch (j){
				case 0: toReturn = stack3_0; break;
				case 1: toReturn = stack3_1; break;
				case 2: toReturn = stack3_2; break;
				case 3: toReturn = stack3_3; break;
				case 4: toReturn = stack3_4; break;
			} break;
			case 4: switch (j){
				case 0: toReturn = stack4_0; break;
				case 1: toReturn = stack4_1; break;
				case 2: toReturn = stack4_2; break;
				case 3: toReturn = stack4_3; break;
				case 4: toReturn = stack4_4; break;
			} break;
		}
		return toReturn;
	}


	public LightBlock getMovedWorker() {
		return movedWorker;
	}

	public void setMovedWorker(LightBlock movedWorker) {
		this.movedWorker = movedWorker;
	}

	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Handles the coordinates of the chosen cell for every phase of the player's turn.
	 *
	 *
	 * @param x horizontal coordinate of the cell
	 * @param y vertical coordinate of the cell
	 * @throws IOException
	 */
	public void Handle(int x, int y) throws IOException {
		if (client.getState() == StateNumber.moveState && selectedPlayer == false ) {
			//Boolean found = false;
			availableWorkerUnset();
			whereToMove.clear();
			ArrayList<LightBlock> worker = client.getService().getWorker();
			for(LightBlock lb : worker){
				if(lb.getC().getY()== y && lb.getC().getX() == x) {
					//found = true;
					setMovedWorker(lb);
					whereToMove= client.setWhereToMove(movedWorker);
				}


			}
			whereToMoveSet(whereToMove);
			selectedPlayer = true;

		}
		else if(client.getState() == StateNumber.moveState && selectedPlayer == true){
			selectedPlayer = false;
			whereToMoveUnSet(whereToMove);
			ObservableList<Node> childs;
			ObservableList<Node> childs2;
			childs = getStack(x,y).getChildren();
			childs2 = getStack(movedWorker.getC().getX(),movedWorker.getC().getY()).getChildren();
			if(lightBoard.getLightBlock(movedWorker.getC().clone()).getColor() == Color.ANSI_RED){
				//setto il primo
				childs.get(5).setVisible(true);
				childs2.get(5).setVisible(false);
			}
			else{
				childs.get(6).setVisible(true);
				childs2.get(6).setVisible(false);
			}
			Coordinate c = new Coordinate(x,y);
			client.sendmoveGui(lightBoard.getLightBlock(c),movedWorker.getIdWorker());

			if(gui.getYourGod().toString().equals("ATLAS")) {
				instructionsVbox.setDisable(false);
				instructions.setVisible(true);
				instructions.setText(godAdditionalInstruction.atlasInstruction);
				responseHbox.setVisible(true);
				confirmButton.setVisible(true);
			}

		}else if(client.getState() == StateNumber.buildState) {
			Coordinate cBuild = new Coordinate(x,y);
			LightBlock toSend = lightBoard.getLightBlock(cBuild);
			ObservableList<Node> childs;
			childs = getStack(x,y).getChildren();
			Coordinate c = new Coordinate(x,y);

			if(!(client.getService().getLightBoard().getLightBlock(c).getOccupied())) {
				if (gui.getYourGod().toString().equals("ATLAS")) {

					if (confirmDome()) {
						childs.get(4).setVisible(true);
						toSend.setLevel(4);
					} else {
						switch (client.getService().getLightBoard().getLightBlock(c).getLevel()) {
							case 0:
								childs.get(1).setVisible(true);
								break;
							case 1:
								childs.get(2).setVisible(true);
								break;
							case 2:
								childs.get(3).setVisible(true);
								break;
							case 3:
								childs.get(4).setVisible(true);
								break;
						}
					}
				} else {
					switch (client.getService().getLightBoard().getLightBlock(c).getLevel()) {
						case 0:
							childs.get(1).setVisible(true);
							break;
						case 1:
							childs.get(2).setVisible(true);
							break;
						case 2:
							childs.get(3).setVisible(true);
							break;
						case 3:
							childs.get(4).setVisible(true);
							break;
					}
				}
			}
			availableBuildUnSet();

			client.sendBuildGui(toSend);

			if(gui.getYourGod().toString().equals("ATLAS")) {
				instructions.setVisible(false);
				instructions.setText(godAdditionalInstruction.atlasInstruction);
				responseHbox.setVisible(false);
				confirmButton.setVisible(false);
				instructionsVbox.setDisable(true);
			}
		}
	}



	/**
	 * @author Lorenzo Longaretti
	 * @author Andrea Gerosa
	 * Sets this controller in the gui object and sets some graphic elemts.
	 *
	 */
	public void initialize() {
		client = ClientAppGUI.getClient2();
		lightBoard = client.getService().getLightBoard();
		initializeBoard();
		addAdditionalInformations();
		availableWorker();
		setGodsAndDescriptions();

		if(gui.getGodUsedName().size() == 2) {
			label2ndPlayer.setVisible(true);
		}
		else if(gui.getGodUsedName().size() == 3) {
			label2ndPlayer.setVisible(true);
			label3rdPlayer.setVisible(true);
		}

		gui.setMainGameController(this);
	}


	/**
	 * @author Lorenzo Longaretti
	 * Sets clickable the cells on the board.
	 *
	 */
	public void availableWorker(){
		updateBoard();
		ObservableList<Node> childs;
		if(client.getState() == StateNumber.moveState){
			for (LightBlock lb: client.getService().getWorker() ) {
				childs = getStack(lb.getC().getX(),lb.getC().getY()).getChildren();
				getStack(lb.getC().getX(),lb.getC().getY()).setDisable(false);
				childs.get(0).setVisible(true);
			}
		}
		else
		{
			for (LightBlock lb: client.getService().getAvailableCells2() ) {
				lb.getC();
				childs = getStack(lb.getC().getX(),lb.getC().getY()).getChildren();
				getStack(lb.getC().getX(),lb.getC().getY()).setDisable(false);
				childs.get(0).setVisible(true);}

		}
	}

	/**
	 * @author Lorenzo Longaretti
	 *
	 *  Displays where the player can build.
	 *
	 */
	public void availableBuild(){

		ObservableList<Node> childs;
		if(client.getState() == StateNumber.buildState){
			for (LightBlock lb: client.getService().getAvailableCells2() ) {
				lb.getC();
				childs = getStack(lb.getC().getX(),lb.getC().getY()).getChildren();
				getStack(lb.getC().getX(),lb.getC().getY()).setDisable(false);
				childs.get(0).setVisible(true);
			}
		}
	}

	/**
	 * @author Lorenzo longaretti
	 * Sets disable and not visible the cells of the building phase.
	 *
	 */
	public void availableBuildUnSet(){

		ObservableList<Node> childs;
		if(client.getState() == StateNumber.buildState){
			for (LightBlock lb: client.getService().getAvailableCells2() ) {
				lb.getC();
				childs = getStack(lb.getC().getX(),lb.getC().getY()).getChildren();
				getStack(lb.getC().getX(),lb.getC().getY()).setDisable(true);
				childs.get(0).setVisible(false);
			}
		}
	}

	/**
	 * @author Lorenzo Longaretti
	 * Sets unclickable the cells on the board.
	 *
	 */
	public void availableWorkerUnset(){
		ObservableList<Node> childs;
		if(client.getState() == StateNumber.moveState){
			for (LightBlock lb: client.getService().getWorker() ) {
				lb.getC();
				childs = getStack(lb.getC().getX(),lb.getC().getY()).getChildren();
				getStack(lb.getC().getX(),lb.getC().getY()).setDisable(true);
				childs.get(0).setVisible(false);
				//childs.clear();
			}
		}
	}

	/**
	 * @author Lorenzo Longaretti
	 * Displays where the player can move.
	 *
	 * @param lb
	 */
	public void whereToMoveSet(ArrayList<LightBlock> lb){
		ObservableList<Node> childs;
		ObservableList<Node> childs2;
		for (LightBlock lb2: lb) {
			getStack(lb2.getC().getX(),lb2.getC().getY()).setDisable(false);
			childs = getStack(lb2.getC().getX(),lb2.getC().getY()).getChildren();
			childs2 = getStack(movedWorker.getC().getX(),movedWorker.getC().getY()).getChildren();
			childs.get(0).setVisible(true);

		}


	}

	/**
	 * @author Lorenzo Longaretti
	 * Sets disable and not visible the cells of the moving phase.
	 *
	 * @param lb
	 */
	public void whereToMoveUnSet(ArrayList<LightBlock> lb){
			ObservableList<Node> childs;
			ObservableList<Node> childs2;
			for (LightBlock lb2: lb) {
				getStack(lb2.getC().getX(),lb2.getC().getY()).setDisable(true);
				childs = getStack(lb2.getC().getX(),lb2.getC().getY()).getChildren();
				childs2 = getStack(movedWorker.getC().getX(),movedWorker.getC().getY()).getChildren();
				childs.get(0).setVisible(false);


			}
	}

	/**
	 * @author Lorenzo Longaretti
	 * Initializes the GridPane that represents the board.
	 *
	 */
	public void initializeBoard(){
		Coordinate c = new Coordinate(0,0);
		for(int i = 0 ; i<5 ; i++){
			for(int j = 0 ; j<5 ; j++){
				switch (i){
					case 0 : switch (j){ case 0:
						c.setX(i); c.setY(j);
						if(lightBoard.getLightBlock(c.clone()).getOccupied()){
							ObservableList<Node> childs = stack0_0.getChildren();
							if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
								childs.get(5).setVisible(true);
							}
							else{
								childs.get(6).setVisible(true);
							}
						} break;
						case 1:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack0_1.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
						case 2:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack0_2.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
						case 3:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack0_3.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
						case 4:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack0_4.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
					} break;


					case 1 : switch (j){ case 0:
						c.setX(i); c.setY(j);
						if(lightBoard.getLightBlock(c.clone()).getOccupied()){
							ObservableList<Node> childs = stack1_0.getChildren();
							if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
								//setto il primo
								childs.get(5).setVisible(true);

							}
							else{
								childs.get(6).setVisible(true);
							}
						} break;
						case 1:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack1_1.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									//setto il primo
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
						case 2:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack1_2.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									//setto il primo
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
						case 3:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack1_3.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									//setto il primo
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
						case 4:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack1_4.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									//setto il primo
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
					} break;
					// caso 2

					case 2 : switch (j){ case 0:
						c.setX(i); c.setY(j);
						if(lightBoard.getLightBlock(c.clone()).getOccupied()){
							ObservableList<Node> childs = stack2_0.getChildren();
							if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
								//setto il primo
								childs.get(5).setVisible(true);

							}
							else{
								childs.get(6).setVisible(true);
							}
						} break;
						case 1:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack2_1.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									//setto il primo
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
						case 2:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack2_2.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									//setto il primo
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
						case 3:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack2_3.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									//setto il primo
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
						case 4:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack2_4.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									//setto il primo
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
					} break;
					// caso 3

					case 3 : switch (j){ case 0:
						c.setX(i); c.setY(j);
						if(lightBoard.getLightBlock(c.clone()).getOccupied()){
							ObservableList<Node> childs = stack3_0.getChildren();
							if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
								//setto il primo
								childs.get(5).setVisible(true);

							}
							else{
								childs.get(6).setVisible(true);
							}
						} break;
						case 1:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack3_1.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									//setto il primo
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
						case 2:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack3_2.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									//setto il primo
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
						case 3:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack3_3.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									//setto il primo
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
						case 4:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack3_4.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									//setto il primo
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
					} break;
					// caso 4

					case 4 : switch (j){ case 0:
						c.setX(i); c.setY(j);
						if(lightBoard.getLightBlock(c.clone()).getOccupied()){
							ObservableList<Node> childs = stack4_0.getChildren();
							if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
								//setto il primo
								childs.get(5).setVisible(true);

							}
							else{
								childs.get(6).setVisible(true);
							}
						} break;
						case 1:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack4_1.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									//setto il primo
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
						case 2:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack4_2.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									//setto il primo
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
						case 3:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack4_3.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									//setto il primo
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
						case 4:
							c.setX(i); c.setY(j);
							if(lightBoard.getLightBlock(c.clone()).getOccupied()){
								ObservableList<Node> childs = stack4_4.getChildren();
								if(lightBoard.getLightBlock(c.clone()).getColor() == Color.ANSI_RED){
									//setto il primo
									childs.get(5).setVisible(true);

								}
								else{
									childs.get(6).setVisible(true);
								}
							} break;
					} break;
					// caso 1

				}
			}
		}
	}


	/**
	 * @author Lorenzo Longaretti
	 * Updates the board with all the graphic changes.
	 *
	 */
	public void updateBoard(){
		lightBoard = client.getService().getLightBoard();
		resetBoard(lightBoard);
		Coordinate cUpdate = new Coordinate(0,0);
		for(int i = 0; i<5; i++){
			for(int j = 0; j<5; j++){
				cUpdate.setX(i);
				cUpdate.setY(j);
				ObservableList<Node> childs;
				childs = getStack(i,j).getChildren();
				if(lightBoard.getLightBlock(cUpdate).getOccupied()){
					if(lightBoard.getLightBlock(cUpdate).getColor() == Color.ANSI_RED){
						childs.get(5).setVisible(true);
					}


					if(lightBoard.getLightBlock(cUpdate).getColor() == Color.ANSI_BLUE){
						childs.get(6).setVisible(true);
					}

					if(lightBoard.getLightBlock(cUpdate).getColor() == Color.ANSI_YELLOW){
						childs.get(7).setVisible(true);
					}
				}

				switch (lightBoard.getLightBlock(cUpdate).getLevel()){
					case 0 : break;
					case 1 : childs.get(1).setVisible(true); break;
					case 2 : childs.get(1).setVisible(true); childs.get(2).setVisible(true); break;
					case 3 : childs.get(1).setVisible(true); childs.get(2).setVisible(true); childs.get(3).setVisible(true); break;
					case 4 : childs.get(1).setVisible(true); childs.get(2).setVisible(true); childs.get(3).setVisible(true); childs.get(4).setVisible(true); break;
				}
				//dome
				if(lightBoard.getLightBlock(cUpdate).getIsDome()){
					childs.get(4).setVisible(true);
				}
			}
		}

	}

	/**
	 * @author Lorenzo Longaretti
	 * Resets the board, disabling and making invisible all the graphic elements.
	 *
	 * @param lightBoard the game's board
	 */
	public void resetBoard(LightBoard lightBoard){
		Coordinate cUpdate = new Coordinate(0,0);
		for(int i = 0; i<5; i++){
			for(int j = 0; j<5; j++){
				cUpdate.setX(i);
				cUpdate.setY(j);
				ObservableList<Node> childs;
				childs = getStack(i,j).getChildren();
				childs.get(0).setVisible(false);
				childs.get(1).setVisible(false);
				childs.get(2).setVisible(false);
				childs.get(3).setVisible(false);
				childs.get(4).setVisible(false);
				childs.get(5).setVisible(false);
				childs.get(6).setVisible(false);
				childs.get(7).setVisible(false);
				childs.get(0).setDisable(true);
				childs.get(1).setDisable(true);
				childs.get(2).setDisable(true);
				childs.get(3).setDisable(true);
				childs.get(4).setDisable(true);
				childs.get(5).setDisable(true);
				childs.get(6).setDisable(true);
				childs.get(7).setDisable(true);

			}
		}
	}


	/**
	 * @author Andrea Gerosa
	 * Sets all the graphic elements of the endgame phase (victory and loss)
	 *
	 */
	public void matchEnded() {
		if(client.getService().getType() == StateNumber.sendhaswon) {
			divVbox.setDisable(true);
			instructionsVbox.setDisable(true);
			board.setDisable(true);
			board.setVisible(false);
			wlPane.setDisable(false);
			wlPane.setVisible(true);
			winlose.setImage(winImage);
			winloseText.setText("You won! You can exit the game or play a new match.");
		}
		if(client.getService().getType() == StateNumber.sendhaslose) {
			divVbox.setDisable(true);
			instructionsVbox.setDisable(true);
			board.setDisable(true);
			board.setVisible(false);
			wlPane.setDisable(false);
			wlPane.setVisible(true);
			winlose.setImage(loseImage);
			winloseText.setText("You lost! You can exit the game or play a new match.");
		}

	}

	/**
	 * @author Andrea Gerosa
	 * Handles the click on the "New Game" button.
	 *
	 * @param event mouse click
	 */
	@FXML
	public void newGameClicked(MouseEvent event) throws IOException {
		Pane pane = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
		mainGamePane.getChildren().setAll(pane);
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


	/**
	 * @author Andrea Gerosa
	 * Adds informations about some divinities on the board's right.
	 *
	 */
	public void addAdditionalInformations() {

		switch(gui.getYourGod().toString()) {
			case "ARTEMIS" :
				instructions.setVisible(true);
				instructions.setText(godAdditionalInstruction.artemisInstruction);
				break;
			case "ATLAS" :
				instructions.setVisible(false);
				instructions.setText(godAdditionalInstruction.atlasInstruction);
				responseHbox.setVisible(false);
				confirmButton.setVisible(false);
				instructionsVbox.setDisable(true);
				break;
			case "DEMETER" :
				instructions.setVisible(true);
				instructions.setText(godAdditionalInstruction.demeterInstruction);
				break;
			case "HEPHAESTUS" :
				instructions.setVisible(true);
				instructions.setText(godAdditionalInstruction.hephaestusInstruction);
				break;
			case "PROMETHEUS" :
				instructions.setVisible(true);
				instructions.setText(godAdditionalInstruction.prometheusInstruction);
		}

	}

	/**
	 * @author Andrea Gerosa
	 * Checks which one is selected between two specific RadioButtons.
	 *
	 * @return a boolean value which represent the answer to the question on the dome
	 */
	@FXML
	public boolean confirmDome() {
		if(radioDomeYes.isSelected()) {
			return true;
		}
		else if(radioDomeNo.isSelected()) {
			return false;
		}
		else {
			return false;
		}

	}

	/**
	 * @author Andrea Gerosa
	 * Sets all the divinities and their respective descriptions, making them visible at the board's left.
	 *
	 */
	public void setGodsAndDescriptions() {
		div1.setImage(activateGodName(gui.getYourGod()));
		div1.setVisible(true);
		div1.setDisable(false);
		desc1.setVisible(true);
		desc1.setDisable(false);

		if(gui.getGodUsedName().size() == 2) {
			for (int i = 0; i < gui.getGodUsedName().size(); i++) {
				if (gui.getGodUsedName().get(i).equals(gui.getYourGod()) && gui.getGodUsedName().size() - 1 == i + 1) {
					desc1.setText(obtainGodDescription(gui.getGodUsedName().get(i)));
					div2.setImage(activateGodName(gui.getGodUsedName().get(i + 1)));
					div2.setVisible(true);
					div2.setDisable(false);
					desc2.setText(obtainGodDescription(gui.getGodUsedName().get(i + 1)));
					desc2.setVisible(true);
					desc2.setDisable(false);
				}
				else if(gui.getGodUsedName().get(i).equals(gui.getYourGod()) && gui.getGodUsedName().size() == i + 1) {
					desc1.setText(obtainGodDescription(gui.getGodUsedName().get(i)));
					div2.setImage(activateGodName(gui.getGodUsedName().get(i - 1)));
					div2.setVisible(true);
					div2.setDisable(false);
					desc2.setText(obtainGodDescription(gui.getGodUsedName().get(i - 1)));
					desc2.setVisible(true);
					desc2.setDisable(false);
				}
			}
		}
		else if(gui.getGodUsedName().size() == 3) {
			for (int i = 0; i < gui.getGodUsedName().size(); i++) {
				if(gui.getGodUsedName().get(i).equals(gui.getYourGod()) && gui.getGodUsedName().size() - 2 == i + 1 && gui.getGodUsedName().size() - 1 == i + 2) {
					desc1.setText(obtainGodDescription(gui.getGodUsedName().get(i)));
					div2.setImage(activateGodName(gui.getGodUsedName().get(i + 1)));
					div2.setVisible(true);
					div2.setDisable(false);
					desc2.setText(obtainGodDescription(gui.getGodUsedName().get(i + 1)));
					desc2.setVisible(true);
					desc2.setDisable(false);
					div3.setImage(activateGodName(gui.getGodUsedName().get(i + 2)));
					div3.setVisible(true);
					div3.setDisable(false);
					desc3.setText(obtainGodDescription(gui.getGodUsedName().get(i + 2)));
					desc3.setVisible(true);
					desc3.setDisable(false);
				}
				else if(gui.getGodUsedName().get(i).equals(gui.getYourGod()) && gui.getGodUsedName().size() - 1 == i + 1 && gui.getGodUsedName().size() == i + 2) {
					desc1.setText(obtainGodDescription(gui.getGodUsedName().get(i)));
					div2.setImage(activateGodName(gui.getGodUsedName().get(i + 1)));
					div2.setVisible(true);
					div2.setDisable(false);
					desc2.setText(obtainGodDescription(gui.getGodUsedName().get(i + 1)));
					desc2.setVisible(true);
					desc2.setDisable(false);
					div3.setImage(activateGodName(gui.getGodUsedName().get(i - 1)));
					div3.setVisible(true);
					div3.setDisable(false);
					desc3.setText(obtainGodDescription(gui.getGodUsedName().get(i - 1)));
					desc3.setVisible(true);
					desc3.setDisable(false);
				}
				else if(gui.getGodUsedName().get(i).equals(gui.getYourGod()) && gui.getGodUsedName().size() == i + 1) {
					desc1.setText(obtainGodDescription(gui.getGodUsedName().get(i)));
					div2.setImage(activateGodName(gui.getGodUsedName().get(i - 2)));
					div2.setVisible(true);
					div2.setDisable(false);
					desc2.setText(obtainGodDescription(gui.getGodUsedName().get(i - 2)));
					desc2.setVisible(true);
					desc2.setDisable(false);
					div3.setImage(activateGodName(gui.getGodUsedName().get(i - 1)));
					div3.setVisible(true);
					div3.setDisable(false);
					desc3.setText(obtainGodDescription(gui.getGodUsedName().get(i - 1)));
					desc3.setVisible(true);
					desc3.setDisable(false);
				}
			}
		}
	}


	/**
	 * @author Lorenzo Longaretti
	 * Associates the correct Image to the god's name and returns it.
	 *
	 * @param godName the name of the god
	 * @return the Image of the god
	 */
	private Image activateGodName(GodName godName){
		Image i = new Image("/graphicResources/godCards/apollo.png");
		switch (godName){
			case apollo: i = new Image("/graphicResources/godCards/apollo.png"); return i;
			case artemis: i = new Image("/graphicResources/godCards/artemis.png"); return i;
			case athena: i = new Image("/graphicResources/godCards/athena.png"); return i;
			case atlas: i = new Image("/graphicResources/godCards/atlas.png"); return i;
			case demeter: i = new Image("/graphicResources/godCards/demeter.png"); return i;
			case hephaestus: i = new Image("/graphicResources/godCards/hephaestus.png"); return i;
			case minotaur: i = new Image("/graphicResources/godCards/minotaur.png"); return i;
			case pan: i = new Image("/graphicResources/godCards/pan.png"); return i;
			case prometheus: i = new Image("/graphicResources/godCards/prometheus.png"); return i;
		}
		return i;
	}

	/**
	 * @author Andrea Gerosa
	 * Associates the correct GodDescription to the god's name and returns it.
	 *
	 * @param godName the name of the god
	 * @return the description of the god
	 */
	private String obtainGodDescription(GodName godName) {
		String gd = GodDescription.apolloDesc;
		switch (godName){
			case apollo: gd = GodDescription.apolloDesc; return gd;
			case artemis: gd = GodDescription.artemisDesc; return gd;
			case athena: gd = GodDescription.athenaDesc; return gd;
			case atlas: gd = GodDescription.atlasDesc; return gd;
			case demeter: gd = GodDescription.demeterDesc; return gd;
			case hephaestus: gd = GodDescription.hephaestusDesc; return gd;
			case minotaur: gd = GodDescription.minotaurDesc; return gd;
			case pan: gd = GodDescription.panDesc; return gd;
			case prometheus: gd = GodDescription.prometheusDesc; return gd;
		}
		return gd;
	}

}
