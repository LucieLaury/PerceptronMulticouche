package ia.problemes;

import defi2.src.ia.framework.common.Action;
import defi2.src.ia.framework.common.State;
import defi2.src.ia.framework.jeux.Vehicule;
import defi2.src.ia.framework.recherche.SearchProblem;

import java.util.ArrayList;



public class RushHour extends SearchProblem {
    
    // Les actions possible pour une voiture
       
    public static final Action FWD  = new Action("En avant");
    public static final Action BWD  = new Action("En arrière");
    
    /**
     * L'état but (la voiture à la sortie (cases 3,5 & 3,6 occupées par voiture rouge))
     */
    
    public int[][] GOAL_STATE;
        

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
     * @throws Exception
     */
    public ArrayList<Action> getActions(State s, Vehicule v, int mouvement) throws Exception{
        ArrayList<Action> actions = new ArrayList<Action>();
        for (Action a : ACTIONS)
            if( ((RushHourState) s).isLegal(a, v, mouvement))
                actions.add(a);
        return actions;
    }
    
    public State doAction(State s, Action a, Vehicule v, int mouvement) throws Exception {

        // on copie l'état courant et on le modifie
        RushHourState b = (RushHourState) s.clone();
        if (a == FWD)
            b.moveFWD(mouvement, v);
		else if (a == BWD)
            b.moveBWD(mouvement, v);
		else
            throw new IllegalArgumentException("Invalid"+a);
        return b;
    }
        
    public boolean isGoalState(State s){
        GOAL_STATE[0][0] = 3;
        GOAL_STATE[0][1] = 5;
        GOAL_STATE[1][0] = 3;
        GOAL_STATE[1][1] = 6;
        RushHourState rhstate = new RushHourState();
        rhstate = (RushHourState) s;
        return (rhstate.getPositionOfRouge() == GOAL_STATE);
    }


    @Override
    public ArrayList<Action> getActions(State s) {
        return null;
    }


    @Override
    public State doAction(State s, Action a) {
        return null;
    }
}
