package it.polimi.ingsw.PSP45.utils;

/**
 * @author Andrea Gerosa
 *
 * A simple enum for the name of the divinities.
 *
 */
public enum GodName {
    apollo("APOLLO"),
    artemis("ARTEMIS"),
    athena("ATHENA"),
    atlas("ATLAS"),
    demeter("DEMETER"),
    hephaestus("HEPHAESTUS"),
    minotaur("MINOTAUR"),
    pan("PAN"),
    prometheus("PROMETHEUS");

    private final String godName;

    /**
     * @author Andrea Gerosa
     * Enum constructor
     *
     * @param godName
     */
    GodName(String godName) {
        this.godName = godName;
    }

    @Override
    public String toString() {
        return godName;
    }

    

}
