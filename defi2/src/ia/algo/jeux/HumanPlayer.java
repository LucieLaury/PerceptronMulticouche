package ia.algo.jeux;


import ia.framework.common.Action;
import ia.framework.jeux.*;

/**
 * Définie un joueur Humain
 *
 */

public class HumanPlayer extends Player {

    /**
     * Crée un joueur human
     * @param g l'instance du jeux
     * @param p1 vrai si joueur 1
     */
    public HumanPlayer(Game g, boolean p1){
        super(g, p1);
        name = "Human";
    }
    
    /**
     * {@inheritDoc}
     * <p>Demande un coup au joueur humain</p>
     */
    public Action getMove(GameState state){
        return game.getHumanMove(state);
    }


}
