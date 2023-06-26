package it.polimi.ingsw.PSP45.controller;


import it.polimi.ingsw.PSP45.model.Coordinate;
import it.polimi.ingsw.PSP45.model.Match;
import it.polimi.ingsw.PSP45.observer.Observer;
import it.polimi.ingsw.PSP45.utils.StateNumber;
import it.polimi.ingsw.PSP45.utils.fromviewtocontroll;

/**
 * @author Lorenzo Longaretti
 * class that represent the controller that manages the sequence of events
 */
public class Controller implements Observer<fromviewtocontroll> {

    Match match;
    Coordinate coordinate = new Coordinate(0,0);
    int Contatore = 0;

    /**
     * class constructor
     * @param match match being played
     */
    public Controller(Match match) {
        this.match = match;
        this.Contatore =0;
        coordinate = match.getTurnObject().get(0).getWorkers().get(0).getCoordinate();
        match.getBoard().addWorker(coordinate,match.getTurnObject().get(0).getWorkers().get(0).getIdWorker(),match.getTurnObject().get(0).getWorkers().get(0).getColor());
        coordinate = match.getTurnObject().get(0).getWorkers().get(1).getCoordinate();
        match.getBoard().addWorker(coordinate,match.getTurnObject().get(0).getWorkers().get(1).getIdWorker(),match.getTurnObject().get(0).getWorkers().get(1).getColor());

        coordinate = match.getTurnObject().get(1).getWorkers().get(0).getCoordinate();
        match.getBoard().addWorker(coordinate,match.getTurnObject().get(1).getWorkers().get(0).getIdWorker(),match.getTurnObject().get(1).getWorkers().get(0).getColor());
        coordinate = match.getTurnObject().get(1).getWorkers().get(1).getCoordinate();
        match.getBoard().addWorker(coordinate,match.getTurnObject().get(1).getWorkers().get(1).getIdWorker(),match.getTurnObject().get(1).getWorkers().get(1).getColor());

        if(match.getTurnObject().size()==3){
            coordinate = match.getTurnObject().get(2).getWorkers().get(0).getCoordinate();
            match.getBoard().addWorker(coordinate,match.getTurnObject().get(2).getWorkers().get(0).getIdWorker(),match.getTurnObject().get(2).getWorkers().get(0).getColor());
            coordinate = match.getTurnObject().get(2).getWorkers().get(1).getCoordinate();
            match.getBoard().addWorker(coordinate,match.getTurnObject().get(2).getWorkers().get(1).getIdWorker(),match.getTurnObject().get(2).getWorkers().get(1).getColor());
        }
    }

    /**
     * @author Lorenzo Longaretti
     * method that call the handlemove method from the player who is playing
     * @param message
     */
    @Override
    public void update(fromviewtocontroll message) {

       if(message.getService().getType() == StateNumber.haswon){
           Contatore++;
           if(Contatore == match.getTurnObject().size()) {
               for (int i = 0; i < match.getTurnObject().size(); i++) {
                   if (match.getTurnObject().get(i) == message.getPlayer()) {
                       match.getTurnObject().get(i).getAbstractRule().Win();
                       break;
                   }
               }
           }
           else{
           }
       }
       else if(message.getService().getType() == StateNumber.ghost){
           if(match.getTurnObject().size() == 3){
               for (int i = 0 ; i < match.getTurnObject().size() ; i++)
               {
                   if (match.getTurnObject().get(i) != message.getPlayer()) {
                       match.getTurnObject().get(i).getAbstractRule().invertPlayer();
                       match.getTurnObject().get(i).getAbstractRule().nextTurn();
                   }
               }
           }
           else{
               for (int i = 0 ; i < match.getTurnObject().size() ; i++)
               {
                   if (match.getTurnObject().get(i) == message.getPlayer()) {
                   }
               }

           }
       }
       else if (message.getService().getType() == StateNumber.exit){
           for (int i = 0 ; i < match.getTurnObject().size() ; i++)
           {
               if (match.getTurnObject().get(i) == message.getPlayer()) {
                   match.getTurnObject().get(i).getAbstractRule().lose();
               }
           }
       }
       else
           {
           for (int i = 0 ; i < match.getTurnObject().size() ; i++)
           {
               if (match.getTurnObject().get(i) == message.getPlayer()) {
                   match.getTurnObject().get(i).getAbstractRule().turnHandler(message.getService());
               }
           }
       }
    }

    /**
     * @return the match being played
     */
    public Match getMatch() {
        return match;
    }
}
