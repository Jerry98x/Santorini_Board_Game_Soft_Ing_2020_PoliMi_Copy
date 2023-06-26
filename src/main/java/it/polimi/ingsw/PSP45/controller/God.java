package it.polimi.ingsw.PSP45.controller;

import it.polimi.ingsw.PSP45.model.Worker;

/**
 * @author Lorenzo Longaretti
 *
 * abstract class that rappresent a god
 *
 */
public abstract class God extends AbstractRule {

     AbstractRule abstractRuleGod;

    /**
     * @author Lorenzo Longaretti
     *
     * class constructor
     * @param abstractRule abstractRule that will be extended
     */
    public God(AbstractRule abstractRule){
        super(abstractRule);
        abstractRuleGod = abstractRule;
    }

    @Override
    public void setMoves(){}

    /**
     * abstract method that shows the player in which blocks his/hers workers can be moved
     *
     * @param worker worker of which we want to kw where it can be moved
     */
    public abstract void showMoves(Worker worker);


}
