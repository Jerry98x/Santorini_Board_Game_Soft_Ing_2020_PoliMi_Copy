package it.polimi.ingsw.PSP45.utils;

/**
 * @author Andrea Gerosa
 *
 * Class with all the types of messages that can be seen on screen, sent through the stream.
 *
 */
public class cliMessage {

    public static String nameMessage = "Insert your name.";
    public static String godSelection = "Choose two or three gods from the list.";
    public static String yourGodSelectionMessage = "Choose your god from the list.";
    public static String firstPlayerChoiceMessage = "Choose which player will start the match.";
    public static String positionWorkersMessage = "Position your workers where in the board.";
    public static String waitMessage = "Wait, it is not your turn.";
    public static String moveMessage = "Choose a worker (0 or 1).\nThen make your move, by inputting a coordinate.";
    public static String buildMessage = "Select the cell where you want to build, by inputting a coordinate.";
    public static String winMessage = "You won!";
    public static String loseMessage = "You lost!";
    public static String endGameMessage ="You can exit the game or play a new match.\nType 'yes' to do another match, 'no' to exit the game.";
    public static String disconnectMessage = "The other player can't be reached. Connection issues!\nYou can exit the game or play a new match.\nType 'yes' to do another match, 'no' to exit the game.";
    public static String wrongNumberOfPlayersMessage = "You can only play a 2-players or 3-players match. Insert the number again.";
    public static String wrongWorkerMessage = "You must choose worker 0 or worker 1! Try again!";
    public static String wrongLevelMessage = "You must build a floor from 1 to 4! Input it again!";
    public static String wrongGodMessage = "The chosen god doesn't exist! Try again!";
    public static String unavailableCellMessage = "This cell is not available at the moment, choose another one!";
    public static String impossibleCellMessage = "Coordinates must be in this range: (0,4). Select the cell again!";
    public static String wrongInputMessage = "Wrong input, you may have forgotten something or added something unnecessary. Try again!";
    public static String youAreAGhostMessage = "You lost, now you are a ghost in this match.";

}
