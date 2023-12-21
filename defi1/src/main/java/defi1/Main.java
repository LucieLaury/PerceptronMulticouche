package defi1;

import defi1.framework.common.ArgParse;
import defi1.framework.common.State;
import defi1.framework.recherche.TreeSearch;
import defi1.probleme.Ville;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // créer un problem, un état intial et un  algo
        Ville p = (Ville) ArgParse.makeProblem("Hyères","Saint-Malo", "vitesse");
        State s = ArgParse.makeInitialState(p);
        TreeSearch algo = ArgParse.makeAlgo("astar", p, s);

        // resoudre
        System.out.println("Calcul de l'itinéraire...");
        long startTime = System.nanoTime();
       boolean algoSolve = algo.solve();
       long endTime = System.nanoTime();
        System.out.println("algo.solves : " + algoSolve);
        if( algoSolve)
            algo.printSolution();
        System.out.println("temps d'exécution : " + (endTime-startTime));
    }






}
