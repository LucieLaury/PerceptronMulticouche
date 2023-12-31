package defi1.framework.recherche;

import defi1.framework.common.Action;
import defi1.framework.common.BaseProblem;
import defi1.framework.common.State;

/**
* Représente un problème de recherche. 
* <p> Cette classe hérite de
* en lui ajoutant le but à chercher et le coût des actions.  
*/

public abstract class SearchProblem extends BaseProblem {
    
    /** 
     * Test si état final (but)
     * @param s Un état à tester
     * @retrun Vrai si c'est un bu
     */
    public abstract boolean isGoalState(State s);

    /**
     * Retourne le coût de faire une action dans un état. L'action n'est pas exécutée. 
     * @param s L'état en question 
     * @param a L'action en question
     * @return Le coût 
     */
    public abstract double getActionCost(State s, Action a);
}
