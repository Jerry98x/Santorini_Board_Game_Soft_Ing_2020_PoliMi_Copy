package it.polimi.ingsw.PSP45.utils;

/**
 * @author Andrea Gerosa
 *
 * Some Unicode symbols and other characters for the board.
 *
 */
public enum Cell {
    dome("D"),
    workers("W"),
    upleftcorner("\u2554"),
    uprightcorner("\u2557"),
    downleftcorner("\u255A"),
    downrightcorner("\u255D"),
    horizontal("\u2550"),
    vertical("\u2551");


    private final String message;

    /**
     * @author Andrea Gerosa
     * Enum constructor
     *
     * @param message
     */
    Cell(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
