package it.polimi.ingsw.PSP45.model;

import it.polimi.ingsw.PSP45.utils.Color;

import java.util.ArrayList;

/**
 * @author Andrea Gerosa
 *
 * Class which represent the entire board of 25 blocks
 *
 */
public class Board {
     private Block[][] mat;

    /**
     * @author Andrea Gerosa
     * @author Lorenzo Longaretti
     *
     * Class constructor
     *
     */
    public Board() {
        mat = new Block[5][5];
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                Coordinate c = new Coordinate(i, j);
                mat[i][j]= new Block(c);
            }
        }
    }


    /**
     * @author Andrea Gerosa
     *
     * @param c coordinate
     * @return block, given a specific coordinate
     */
    public Block getBlock(Coordinate c) {
        return this.mat[c.getX()][c.getY()];
    }

    /**
     * @author Andrea Gerosa
     *
     * Adds a worker into a specific cell of the board.
     *
     * @param c coordinate
     * @param idWorker ID
     * @param color color of the player
     */
    public void addWorker(Coordinate c, int idWorker, Color color) {
        int x = c.getX();
        int y = c.getY();
        mat[x][y].addWorker(idWorker, color);

    }

    /**
     * @author Andrea Gerosa
     *
     * Removes a worker from a specific cell of the board.
     *
     * @param c coordinate
     */
    public void removeWorker(Coordinate c) {
        int x = c.getX();
        int y = c.getY();
        mat[x][y].removeWorker();


    }

    /**
     * @author Andrea Gerosa
     *
     * Shows all the adjacent blocks, where the worker can move, ignoring all the costraints.
     *
     * @param c worker's coordinate
     * @return Boolean[][] matrix of boolean values
     */
    public Boolean[][] showAroundSpaces(Coordinate c) {
        Boolean[][] ComeBack = new Boolean[3][3];
        int cX = c.getX();
        int cY = c.getY();

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                ComeBack[i][j] = false;
            }
        }

        int cSTD = 1;

        switch(cX) {
            case 0:
                if(cY == 0) {
                    ComeBack[cSTD][cSTD+1] = true;
                    ComeBack[cSTD+1][cSTD] = true;
                    ComeBack[cSTD+1][cSTD+1] = true;
                }
                else if(cY == 4) {
                    ComeBack[cSTD][cSTD-1] = true;
                    ComeBack[cSTD+1][cSTD] = true;
                    ComeBack[cSTD+1][cSTD-1] = true;
                }
                else {
                    ComeBack[cSTD][cSTD-1] = true;
                    ComeBack[cSTD][cSTD+1] = true;
                    ComeBack[cSTD+1][cSTD-1] = true;
                    ComeBack[cSTD+1][cSTD] = true;
                    ComeBack[cSTD+1][cSTD+1] = true;
                }
                break;
            case 1:
            case 2:
            case 3:
                if(cY == 0) {
                    ComeBack[cSTD-1][cSTD] = true;
                    ComeBack[cSTD-1][cSTD+1] = true;
                    ComeBack[cSTD][cSTD+1] = true;
                    ComeBack[cSTD+1][cSTD] = true;
                    ComeBack[cSTD+1][cSTD+1] = true;
                }
                else if(cY == 4) {
                    ComeBack[cSTD-1][cSTD-1] = true;
                    ComeBack[cSTD-1][cSTD] = true;
                    ComeBack[cSTD][cSTD-1] = true;
                    ComeBack[cSTD+1][cSTD-1] = true;
                    ComeBack[cSTD+1][cSTD] = true;
                }
                else {
                    for(int i=0; i<3; i++) {
                        for(int j=0; j<3; j++) {
                            ComeBack[i][j] = true;
                        }
                    }
                    ComeBack[cSTD][cSTD] = false;
                }
                break;
            case 4:
                if(cY == 0) {
                    ComeBack[cSTD-1][cSTD] = true;
                    ComeBack[cSTD-1][cSTD+1] = true;
                    ComeBack[cSTD][cSTD+1] = true;
                }
                else if(cY == 4) {
                    ComeBack[cSTD-1][cSTD-1] = true;
                    ComeBack[cSTD-1][cSTD] = true;
                    ComeBack[cSTD][cSTD-1] = true;
                }
                else {
                    ComeBack[cSTD-1][cSTD-1] = true;
                    ComeBack[cSTD-1][cSTD] = true;
                    ComeBack[cSTD-1][cSTD+1] = true;
                    ComeBack[cSTD][cSTD-1] = true;
                    ComeBack[cSTD][cSTD+1] = true;
                }
                break;
            default:
                break;
        }
        return ComeBack;
    }

    /**
     * @author Filippo Locatelli
     * Method that shows the spaces around a block, where a worker can actually move into, and puts them in an ArrayList
     * @param coordinate coordinates of the block
     * @return ArrayList containing the coordinates of the block surrounding a specific coordinates
     */
    public ArrayList<Coordinate> spacesAround(Coordinate coordinate) {
        ArrayList<Coordinate> freeSpacesList = new ArrayList<>();
        int cX=coordinate.getX();
        int cY=coordinate.getY();
        Boolean aroundSpaces[][]=showAroundSpaces(coordinate);
        Coordinate c=coordinate;
        if (aroundSpaces[0][0]==true) {
            Coordinate h1 = new Coordinate(cX - 1,cY - 1);

            freeSpacesList.add(h1);

        }
        if (aroundSpaces[1][0]==true) {

            Coordinate h2 = new Coordinate(cX + 0 ,cY - 1);
            freeSpacesList.add(h2);
        }
        if (aroundSpaces[2][0]==true) {
            Coordinate h3 = new Coordinate(cX + 1,cY - 1);
            freeSpacesList.add(h3);
        }
        if (aroundSpaces[0][1]==true) {
            Coordinate h4 = new Coordinate(cX - 1, cY +0 );
            freeSpacesList.add(h4);
        }
        if (aroundSpaces[2][1]==true) {
            Coordinate h5 = new Coordinate(cX + 1,cY + 0);
            freeSpacesList.add(h5);
        }
        if (aroundSpaces[0][2]==true) {
            Coordinate h6 = new Coordinate(cX - 1,cY + 1);
            freeSpacesList.add(h6);
        }
        if (aroundSpaces[1][2]==true) {

            Coordinate h7 = new Coordinate(cX +0,cY + 1);
            freeSpacesList.add(h7);
        }
        if (aroundSpaces[2][2]==true) {

            Coordinate h8 = new Coordinate(cX + 1,cY + 1);
            freeSpacesList.add(h8);
        }

        return freeSpacesList;
    }

    /**
     * @author Filippo Locatelli
     *
     * Method that, given an ArrayList of Coordinates, removes the ones that represents a block occupied by a worker
     * @param spacesAround ArrayList of coordinates
     * @return modified ArrayList
     */
    public ArrayList<Coordinate> whereIsOccupied(ArrayList<Coordinate> spacesAround){
        ArrayList<Coordinate> occupiedList=new ArrayList<>();
        for (int i = 0; i < spacesAround.size(); i++) {
            Coordinate coordinate=spacesAround.get(i);
            boolean isOccupied=getBlock(coordinate).getOccupied();
            if (isOccupied){
                occupiedList.add(getBlock(coordinate).getCoordinate());

            }
        }
        return occupiedList;
    }

    /**
     * @author Filippo Locatelli
     * Method that, given an ArrayList of Coordinates, remove the ones that represents a block occupied by a dome
     * @param spacesAround ArrayList of coordinates
     * @return modified ArrayList
     */
    public ArrayList<Coordinate> whereIsDome (ArrayList<Coordinate> spacesAround){
        ArrayList<Coordinate> domeList=new ArrayList<>();
        for (int i = 0; i < spacesAround.size(); i++) {
            Coordinate coordinate=spacesAround.get(i);
            boolean isDome=getBlock(coordinate).getIsDome();
            if (isDome){
                domeList.add(getBlock(coordinate).getCoordinate());
            }
        }
        return domeList;
    }

    /**
     * @author Filippo Locatelli
     * method that takes two arraylists of coordinates and remove from the first the element in the second
     * @param spacesAround first arraylist
     * @param occupiedList arraylist that contains a list of occupied blocks that have to be removed from the first
     * @return the updated list
     */
    public ArrayList<Coordinate> removeWhereOccupied(ArrayList<Coordinate> spacesAround, ArrayList<Coordinate> occupiedList){
        int olX;
        int olY;
        int saX;
        int saY;

        for (int i = 0; i < occupiedList.size(); i++) {
            olX=occupiedList.get(i).getX();
            olY=occupiedList.get(i).getY();
            for (int j = 0; j < spacesAround.size(); j++) {
                saX=spacesAround.get(j).getX();
                saY=spacesAround.get(j).getY();
                if (olX==saX && olY==saY) {
                    spacesAround.remove(j);
                    j--;
                }

            }

        }
        return spacesAround;
    }

    /**
     * @author Filippo Locatelli
     * method that takes two arraylists of coordinates and remove from the first the element in the second
     * @param spacesAround first arraylist
     * @param domeList arraylist that contains a list of dome blocks that have to be removed from the first
     * @return the updated list
     */
    public ArrayList<Coordinate> removeWhereDome(ArrayList<Coordinate> spacesAround, ArrayList<Coordinate> domeList){
        int dlX;
        int dlY;
        int saX;
        int saY;

        for (int i = 0; i < domeList.size(); i++) {
            dlX=domeList.get(i).getX();
            dlY=domeList.get(i).getY();
            for (int j = 0; j < spacesAround.size(); j++) {
                saX=spacesAround.get(j).getX();
                saY=spacesAround.get(j).getY();
                if (dlX==saX && dlY==saY) {
                    spacesAround.remove(j);
                    j--;
                }
            }
        }
        return spacesAround;
    }

    /**
     * @author Filippo Locatelli
     * method that returns a list of free blocks around a given block
     * @param coordinate coordinates of the central block
     * @return list of coordinates of free spaces around the given one
     */
    public ArrayList<Coordinate> freeSpaces(Coordinate coordinate){
        ArrayList<Coordinate> spacesAround = spacesAround(coordinate);
        ArrayList<Coordinate> occupiedList = whereIsOccupied(spacesAround);
        ArrayList<Coordinate> notOccupiedSpaces = removeWhereOccupied(spacesAround,occupiedList);
        ArrayList<Coordinate> domeList = whereIsDome(notOccupiedSpaces);
        ArrayList<Coordinate> freeSpacesList = removeWhereDome(notOccupiedSpaces,domeList);
        return freeSpacesList;
    }



    /**
     * @author Filippo Locatelli
     * method that returns a list of free blocks around a given block
     * @param coordinate coordinates of the central block
     * @return list of free LightBlocks around the given coordinates
     */
    public ArrayList<LightBlock> freeSpacesBuild(Coordinate coordinate){
        ArrayList<Coordinate> help =  freeSpaces(coordinate);
        ArrayList<LightBlock> help2 = new ArrayList<>();

        int i;
        for(i = 0; i < help.size() ; i++){
            LightBlock help3 = new LightBlock(help.get(i));
            help3.setLightBlock(getBlock(help.get(i)));
            help2.add(help3);


        }

        return help2;
    }

    /**
     * @author Filippo Locatelli
     * Method that returns a list of block that are at the right height to permit a move on that block
     * @param coordinate coordinates of the central block
     * @param spacesAround ArrayList of lightBlocks around the given one
     * @return modified ArrayList with only the blocks at the right height
     */
    public ArrayList<LightBlock> whereIsRightLevel(Coordinate coordinate, ArrayList<LightBlock> spacesAround){
        int levelOfStartingC=getBlock(coordinate).getLevel();
        Coordinate coordinateOfBlock=null;
        int levelOfBlock;
        ArrayList<LightBlock> rightLevelList=new ArrayList<>();
        LightBlock controlledBlock=null;
        for (int i = 0; i < spacesAround.size(); i++) {
            coordinateOfBlock=spacesAround.get(i).getC();
            levelOfBlock=getBlock(coordinateOfBlock).getLevel();
            if (levelOfBlock<=levelOfStartingC+1){
                controlledBlock=new LightBlock(coordinateOfBlock);
                rightLevelList.add(controlledBlock);
            }
        }
        return rightLevelList;
    }

    /**
     * method that says if two integers can represent the coordinates of a block on the board
     * @param cX x coordinate
     * @param cY y coordinate
     * @return true if the block of coordinates(cX,cY) is on the board, false otherwise
     */
    public boolean isOnBoard(int cX, int cY){
        if (cX<0 || cX>4)
            return false;
        else if (cY<0 || cY>4)
            return false;
        else
            return true;

    }
}