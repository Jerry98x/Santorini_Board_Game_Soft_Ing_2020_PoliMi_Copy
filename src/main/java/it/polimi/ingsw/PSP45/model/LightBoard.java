package it.polimi.ingsw.PSP45.model;

import it.polimi.ingsw.PSP45.utils.Cell;
import it.polimi.ingsw.PSP45.utils.Color;

import java.io.Serializable;


/**
 * @author Andrea Gerosa
 *
 * Class which represent a reduced version of the entire board of 25 blocks, with only its essential
 *
 *
 */
public class LightBoard implements Serializable {
    private LightBlock[][] lightMat;

    /**
     * @author Andrea Gerosa
     *
     * Class constructor.
     *
     */
    public LightBoard() {
        lightMat = new LightBlock[5][5];
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                Coordinate c = new Coordinate(i, j);
                lightMat[i][j]= new LightBlock(c);
            }
        }
    }

    /**
     * sets the lightMat matrix of this lightBoard to the given one
     * @param lightMat
     */
    public void setLightMat(LightBlock[][] lightMat) {
        this.lightMat = lightMat;
    }

    /**
     * @return this lightBoard's lightMat matrix
     */
    public LightBlock[][] getLightMat() {
        return lightMat;
    }

    /**
     * @author Andrea Gerosa
     *
     * Takes the informations from the Board, creates the LightBlocks and sets the LightBoard with them.
     *
     * @param b the Board of the game
     * @return LightBlock[][]
     */
    public LightBlock[][] update(Board b) {
        Coordinate c = new Coordinate(0,0);
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                c.setX(i);
                c.setY(j);
                lightMat[i][j].setLightBlock(b.getBlock(c));
            }
        }
        return lightMat;
    }


    /**
     * @author Andrea Gerosa
     *
     * Prints every cell of a row, column by column.
     *
     * @param cX the row of the LightBoard
     */
    public void printBoardLine(int cX) {
        int x = cX;


        //first line of the set of 5 blocks
        System.out.print("  ");
        for (int i = 0; i < 5; i++) {
            System.out.print(Cell.upleftcorner);
            for (int j = 0; j < 8; j++) {
                System.out.print(Cell.horizontal);
            }
            System.out.print(Cell.uprightcorner);
            System.out.print(" ");
        }


        System.out.println();


        //second line of the set of 5 blocks
        System.out.print("  ");
        for (int i = 0; i < 5; i++) {
            System.out.print(Cell.vertical + "        " + Cell.vertical);
            System.out.print(" ");
        }


        System.out.println();


        //third line of the set of 5 blocks
        System.out.print(x);
        System.out.print(" ");
        for (int i = 0; i < 5; i++) {
            System.out.print(Cell.vertical + "  ");
            if (lightMat[x][i].getIsDome()) {
                System.out.print(" " + Color.ANSI_PURPLE + Cell.dome + Color.getRESET() + "  ");
            } else if (lightMat[x][i].getOccupied() == true) {
                System.out.print("" + Color.ANSI_YELLOW + lightMat[x][i].getLevel() + Color.getRESET());
                System.out.print("-");
                System.out.print("" + lightMat[x][i].getColor() + Cell.workers + Color.getRESET());
                System.out.print("" + lightMat[x][i].getColor() + lightMat[x][i].getIdWorker() + Color.getRESET());
            } else {
                System.out.print(" " + Color.ANSI_YELLOW + lightMat[x][i].getLevel() + Color.getRESET() + "  ");
            }
            System.out.print("  " + Cell.vertical);
            System.out.print(" ");
        }
        System.out.print(x);

        System.out.println();


        //fourth line of the set of 5 blocks
        System.out.print("  ");
        for (int i = 0; i < 5; i++) {
            System.out.print(Cell.vertical + "        " + Cell.vertical);
            System.out.print(" ");
        }


        System.out.println();


        //fifth line of the set of 5 blocks
        System.out.print("  ");
        for (int i = 0; i < 5; i++) {
            System.out.print(Cell.downleftcorner);
            for (int j = 0; j < 8; j++) {
                System.out.print(Cell.horizontal);
            }
            System.out.print(Cell.downrightcorner);
            System.out.print(" ");
        }


        System.out.println();

    }


    /**
     * @author Andrea Gerosa
     *
     * Prints the index of the rows and columns and the LightBoard itself, row by row.
     *
     */
    public void printBoard() {
        System.out.println();
        System.out.print("  ");
        for(int i = 0; i < 5; i++) {
            System.out.print("    " + i + "      ");
        }
        System.out.println();


        for(int i  = 0; i < 5; i++){
            printBoardLine(i);
        }

        System.out.print("  ");
        for(int i = 0; i < 5; i++) {
            System.out.print("    " + i + "      ");
        }
        System.out.println();
        System.out.println();

    }

    /**
     * method that returns the lightBlock with the coordinates given in input
     * @param c coordinates of the wanted lightBlock
     * @return the lightBlock of coordinates c
     */
    public LightBlock getLightBlock(Coordinate c) {
        return lightMat[c.getX()][c.getY()];
    }

    /**
     * sets a lightBlock in the lightBoard to the given one
     * @param lb lightBlock that will be set in the lightBoard
     */
    public void setLightblock(LightBlock lb){
        lightMat[lb.getC().getX()][lb.getC().getY()] = lb;
    }
}
