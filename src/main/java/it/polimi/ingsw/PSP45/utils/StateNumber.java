package it.polimi.ingsw.PSP45.utils;

/**
 * @author Andrea Gerosa
 *
 * This enum of int represents all the possible values for the state variables.
 *
 */
public enum StateNumber {
    active(0),
    nameAndNumberRequest(77),
    nameResponse(88),
    moveState(33),
    buildState(22),
    readyToMove(11),
    readyToBuild(44),
    settingMovement(10),
    moving(20),
    settingBuilding(30),
    building(40),
    onlymoving(50),
    onlybuilding(60),
    onlynextturn(70),
    wait(99),
    nextTurn(100),
    exit(55),
    haswon(1998),
    sendhaswon(1990),
    sendhaslose(1890),
    setgod(88),
    replysetgod(89),
    ghost(97),
    firstplayer(96),
    firstplayerreceive(95),
    pingsend(1),
    pingreceive(2),
    setcoordinate(3),
    setcoordinatereply(4),
    disconnection(5);

    private final int state;

    /**
     * @author Andrea Gerosa
     * Enum constructor
     *
     * @param state
     */
    StateNumber(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
