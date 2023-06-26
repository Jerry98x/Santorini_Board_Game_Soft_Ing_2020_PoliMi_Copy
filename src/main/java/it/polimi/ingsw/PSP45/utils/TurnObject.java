package it.polimi.ingsw.PSP45.utils;

import it.polimi.ingsw.PSP45.controller.AbstractRule;
import it.polimi.ingsw.PSP45.controller.Rules;
import it.polimi.ingsw.PSP45.model.Player;

/**
 * @author Lorenzo Longaretti
 *
 * Class that save the correlation between a player and his rules
 */
public class TurnObject {
    Player player;
    AbstractRule rules;

    /**
     * @author Lorenzo Longaretti
     * Class constructor
     *
     * @param player
     * @param rules
     */
    public TurnObject(Player player, AbstractRule rules) {
        this.player = player;
        this.rules = rules;
    }

    public AbstractRule getRules() {
        return rules;
    }

    public Player getPlayer() {
        return player;
    }
}
