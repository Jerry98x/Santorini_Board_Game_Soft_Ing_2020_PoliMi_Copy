package it.polimi.ingsw.PSP45.gui;

import it.polimi.ingsw.PSP45.avviatori.ClientAppGUI;
import it.polimi.ingsw.PSP45.client.ClientGUI;
import it.polimi.ingsw.PSP45.model.Coordinate;
import it.polimi.ingsw.PSP45.model.LightBoard;
import it.polimi.ingsw.PSP45.utils.Color;
import it.polimi.ingsw.PSP45.utils.GodName;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * @author Lorenzo Longaretti
 * @author Andrea Gerosa
 * Controller class for the pre-game scene of the gui, where players position their workers.
 *
 */
public class preMainGameController {
    ClientGUI client;
    LightBoard lightBoard;
    Boolean b = false;
    int xSave;
    int ySave;

    @FXML
    StackPane st0_0;
    @FXML
    StackPane st0_1;
    @FXML
    StackPane st0_2;
    @FXML
    StackPane st0_3;
    @FXML
    StackPane st0_4;
    @FXML
    StackPane st1_0;
    @FXML
    StackPane st1_1;
    @FXML
    StackPane st1_2;
    @FXML
    StackPane st1_3;
    @FXML
    StackPane st1_4;
    @FXML
    StackPane st2_0;
    @FXML
    StackPane st2_1;
    @FXML
    StackPane st2_2;
    @FXML
    StackPane st2_3;
    @FXML
    StackPane st2_4;
    @FXML
    StackPane st3_0;
    @FXML
    StackPane st3_1;
    @FXML
    StackPane st3_2;
    @FXML
    StackPane st3_3;
    @FXML
    StackPane st3_4;
    @FXML
    StackPane st4_0;
    @FXML
    StackPane st4_1;
    @FXML
    StackPane st4_2;
    @FXML
    StackPane st4_3;
    @FXML
    StackPane st4_4;

    /**
     * @author Lorenzo Longaretti
     * @author Andrea Gerosa
     * Handles the click on cell (0,0).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click00(MouseEvent mouseEvent) {
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
     * Handles the click on cell (1,0).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click10(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (2,0).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click20(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (3,0).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click30(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (4,0).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click40(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (0,1).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click01(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (1,1).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click11(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (2,1).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click21(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (3,1).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click31(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (4,1).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click41(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (0,2).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click02(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (1,2).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click12(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (2,2).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click22(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (3,2).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click32(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (4,2).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click42(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (0,3).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click03(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (1,3).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click13(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (2,3).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click23(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (3,3).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click33(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (4,3).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click43(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (0,4).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click04(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (1,4).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click14(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (2,4).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click24(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (3,4).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click34(MouseEvent mouseEvent) {
        
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
     * Handles the click on cell (4,4).
     *
     * @param mouseEvent mouse click
     */
    @FXML
    public void click44(MouseEvent mouseEvent) {
        
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
     * Handles the coordinates of the chosen cell and sends it to the server.
     *
     * @param x horizontal coordinate of the cell
     * @param y vertical coordinate of the cell
     */
    public void Handle(int x, int y){
        if(b == false){
            b = true;
            xSave = x;
            ySave = y;
            getStack(x,y).setDisable(true);
            getStack(x,y).setVisible(false);
        }
        else{
            getStack(x,y).setDisable(true);
            getStack(x,y).setVisible(false);
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        client = ClientAppGUI.getClient2();
                        client.sendCoordinate(xSave,ySave,x,y);

                    }
                    catch (Exception e){
                        System.err.println(e.getMessage());
                    }

                }
            });
            t.start();
            deleteAll();
        }
    }

    /**
     * @author Lorenzo Longaretti
     * Sets this controller in the gui object just after the scene has loaded.
     *
     */
    public void initialize() {
        client = ClientAppGUI.getClient2();
        lightBoard = client.getService().getLightBoard();
        updateBoard();
        gui.clearDivinity();
        for (GodName name: client.getService().getListGod()
        ) {
            gui.addDivinity(name);
        }
    }

    /**
     * @author Lorenzo Longaretti
     * Updates the board with all the graphic changes.
     *
     */
    public void updateBoard() {

        Image red = new Image("/graphicResources/heropower_active1.png");
        Image  blue = new Image("/graphicResources/heropower_active2.png");
        Image yellow = new Image("/graphicResources/heropower_active3.png");


        lightBoard = client.getService().getLightBoard();
        resetBoard(lightBoard);
        ImageView imageView;
        Coordinate cUpdate = new Coordinate(0, 0);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cUpdate.setX(i);
                cUpdate.setY(j);
                ObservableList<Node> childs;
                childs = getStack(i, j).getChildren();
                imageView = (ImageView) childs.get(0);
                if (lightBoard.getLightBlock(cUpdate).getOccupied()) {
                    getStack(i,j).setDisable(true);
                    imageView.setDisable(true);
                    if (lightBoard.getLightBlock(cUpdate).getColor() == Color.ANSI_RED) {
                        imageView.setImage(red);
                        imageView.setVisible(true);

                    }


                    if (lightBoard.getLightBlock(cUpdate).getColor() == Color.ANSI_BLUE) {
                        imageView.setImage(blue);
                        imageView.setVisible(true);
                    }

                    if (lightBoard.getLightBlock(cUpdate).getColor() == Color.ANSI_YELLOW) {
                        imageView.setImage(yellow);
                        imageView.setVisible(true);
                    }
                }
                else{
                    imageView.setDisable(true);
                    imageView.setVisible(true);

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
    public void resetBoard(LightBoard lightBoard) {
        Coordinate cUpdate = new Coordinate(0, 0);
        ImageView imageView ;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cUpdate.setX(i);
                cUpdate.setY(j);
                ObservableList<Node> childs;
                childs = getStack(i, j).getChildren();
                imageView = (ImageView) childs.get(0);
                imageView.setVisible(false);
                imageView.setDisable(true);
            }
        }


    }

    /**
     * @author Lorenzo Longaretti
     * Obtains a StackPane, given its coordinate.
     *
     * @param x horizontal coordinate of the GridPane cell
     * @param j vertical coordinate of the GridPane cell
     * @return a StackPane
     */
    public StackPane getStack(int x, int j){
        StackPane toReturn = new StackPane();
        switch (x){
            case 0: switch (j){
                case 0: toReturn = st0_0; break;
                case 1: toReturn = st0_1; break;
                case 2: toReturn = st0_2; break;
                case 3: toReturn = st0_3; break;
                case 4: toReturn = st0_4; break;
            } break;
            case 1: switch (j){
                case 0: toReturn = st1_0; break;
                case 1: toReturn = st1_1; break;
                case 2: toReturn = st1_2; break;
                case 3: toReturn = st1_3; break;
                case 4: toReturn = st1_4; break;
            } break;
            case 2: switch (j){
                case 0: toReturn = st2_0; break;
                case 1: toReturn = st2_1; break;
                case 2: toReturn = st2_2; break;
                case 3: toReturn = st2_3; break;
                case 4: toReturn = st2_4; break;
            } break;
            case 3: switch (j){
                case 0: toReturn = st3_0; break;
                case 1: toReturn = st3_1; break;
                case 2: toReturn = st3_2; break;
                case 3: toReturn = st3_3; break;
                case 4: toReturn = st3_4; break;
            } break;
            case 4: switch (j){
                case 0: toReturn = st4_0; break;
                case 1: toReturn = st4_1; break;
                case 2: toReturn = st4_2; break;
                case 3: toReturn = st4_3; break;
                case 4: toReturn = st4_4; break;
            } break;
        }
        return toReturn;
    }

    /**
     * @author Lorenzo Longaretti
     * Deletes every StackPane in the GridPane's cells.
     *
     */
    private void deleteAll(){
        for (int i = 0 ; i< 5 ; i++){
            for (int j = 0 ; j<5 ; j++) {
                getStack(i, j).setDisable(true);
                getStack(i, j).setVisible(false);
            }
        }
    }

}
