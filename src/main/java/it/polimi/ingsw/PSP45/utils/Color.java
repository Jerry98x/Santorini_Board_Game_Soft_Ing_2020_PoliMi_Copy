package it.polimi.ingsw.PSP45.utils;

/**
 * @author Andrea Gerosa
 *
 * Enum of colors used in messages and board depiction in the CLI.
 *
 */
public enum Color {
    ANSI_RED("\u001B[31m"), //for the first player workers
    ANSI_BLUE("\u001B[34m"),    // for the second player workers
    ANSI_GREEN("\u001B[32m"),   // for the third player workers
    ANSI_YELLOW("\u001B[33m"),  // for the buildings (all floors)
    ANSI_PURPLE("\u001B[35m"),  // for domes
    ANSI_WHITE("\u001B[37m");   // standard color


    static final String RESET = "\u001B[0m";

    public static String getRESET() {
        return RESET;
    }


    private String col;

    /**
     * @author Andrea Gerosa
     * Enum constructor
     *
     * @param col
     */
    Color(String col) {
        this.col = col;
    }


    public String getCol() {
        return col;
    }

    @Override
    public String toString() {
        return col;
    }

}
