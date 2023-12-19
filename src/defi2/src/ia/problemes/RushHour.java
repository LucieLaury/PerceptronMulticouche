package ia.problemes;

import java.util.ArrayList;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.SearchProblem;

public class RushHour extends SearchProblem {
    
    // Les actions possible pour une voiture
       
    public static final Action FWD  = new Action("En avant");
    public static final Action BWD  = new Action("En arrière");
    
    /**
     * L'état but (la voiture à la sortie (cases 3,5 & 3,6 occupées par voiture rouge))
     */
    
    public static final RushHourState GOAL_STATE = 
        new RushHourState(); //todo
        // voiture rouge (v1, dans la premiere case du tableau)
        // position (3,5),(3,6)
        

    /**
     * Crée une instance du problème RushHour
     *
     */
    public RushHour(){

        // La liste des actions possibles  
        ACTIONS = new Action[] { FWD, BWD };
    }


    /**
     * {@inheritDoc}
     * <p>Chaque mouvement coûte 1</p>
     */
    public double getActionCost(State s, Action a){
        return 1.0;
    }

    /**
     * {@inheritDoc}
     * <p>Retourne uniquement celle qui sont possibles</p>
     */
    public ArrayList<Action> getActions(State s){
        ArrayList<Action> actions = new ArrayList<Action>();
        for (Action a : ACTIONS)
            if( ((RushHourState) s).isLegal(a))
                actions.add(a);
        return actions;
    }
    
    public State doAction(State s, Action a){

        // on copie l'état courant et on le modifie
        RushHourState b = (RushHourState) s.clone();
        if (a == FWD)
            b.moveFWD();
		else if (a == BWD)
            b.moveBWD();
		else
            throw new IllegalArgumentException("Invalid"+a);
        return b;
    }
        
    public boolean isGoalState(State s){
        return ((RushHourState)s).equals(GOAL_STATE);
    }
}
