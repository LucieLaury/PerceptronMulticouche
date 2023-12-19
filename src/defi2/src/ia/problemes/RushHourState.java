package defi2.src.ia.problemes;

import defi2.src.ia.framework.common.Action;
import defi2.src.ia.framework.common.State;
import defi2.src.ia.framework.jeux.Vehicule;

import java.util.Arrays;
import java.util.Collections; 
import java.util.Random;




/**
 * Représente l'état d'un jeu RushHour
 * <p>Les transition sont réalisées avec </p>  
 */

public class RushHourState extends State {

    // Le puzzle : tableau de 6x6 booléens
    // Si une case est occupé, alors le booléen correspondant est vrai
    private boolean[][] plateau = null;

    private Vehicule[] véhicules;
    
    /**
     * Générer état initial avec une difficultée choisie,
     */
    public void choisirDifficulté(String difficulté) {
        Vehicule[] tempvéhicules = new Vehicule[20];
        switch (difficulté) {
            case "beginner":
                Vehicule v1 = new Vehicule("horizontal", 2, new int[][]{{3, 1}, {3, 2}}, true);
                Vehicule v2 = new Vehicule("horizontal", 2, new int[][]{{1, 5}, {1, 6}}, false);
                Vehicule v3 = new Vehicule("horizontal", 3, new int[][]{{4, 1}, {4, 2}, {4, 3}}, false);
                Vehicule v4 = new Vehicule("vertical", 3, new int[][]{{1, 3}, {2, 3}, {3, 3}}, false);
                Vehicule v5 = new Vehicule("vertical", 3, new int[][]{{4, 6}, {5, 6}, {6, 6}}, false);
                véhicules[0] = v1;
                véhicules[1] = v2;
                véhicules[2] = v3;
                véhicules[3] = v4;
                véhicules[4] = v5;
                break;
            case "intermediate":
                v1 = new Vehicule("horizontal", 2, new int[][]{{3, 1}, {3, 2}}, true);
                v2 = new Vehicule("horizontal", 2, new int[][]{{6, 2}, {6, 3}}, false);
                v3 = new Vehicule("horizontal", 2, new int[][]{{4, 4}, {4, 5}}, false);
                v4 = new Vehicule("vertical", 2, new int[][]{{5, 5}, {6, 5}}, false);
                v5 = new Vehicule("vertical", 2, new int[][]{{3, 6}, {4, 6}}, false);
                Vehicule v6 = new Vehicule("vertical", 2, new int[][]{{1, 6}, {2, 6}}, false);
                Vehicule v7 = new Vehicule("vertical", 2, new int[][]{{1, 4}, {2, 4}}, false);
                Vehicule v8 = new Vehicule("horizontal", 3, new int[][]{{1, 1}, {1, 2}, {1, 3}}, false);
                Vehicule v9 = new Vehicule("horizontal", 3, new int[][]{{5, 1}, {5, 2}, {5, 3}}, false);
                Vehicule v10 = new Vehicule("vertical", 3, new int[][]{{2, 3}, {3, 3}, {4, 3}}, false);
                véhicules[0] = v1;
                véhicules[1] = v2;
                véhicules[2] = v3;
                véhicules[3] = v4;
                véhicules[4] = v5;
                véhicules[5] = v6;
                véhicules[6] = v7;
                véhicules[7] = v8;
                véhicules[8] = v9;
                véhicules[9] = v10;
                break;
            case "advanced":
                v1 = new Vehicule("horizontal", 2, new int[][]{{3, 2}, {3, 3}}, true);
                v2 = new Vehicule("horizontal", 2, new int[][]{{6, 1}, {6, 2}}, false);
                v3 = new Vehicule("horizontal", 2, new int[][]{{1, 1}, {1, 2}}, false);
                v4 = new Vehicule("vertical", 2, new int[][]{{5, 6}, {6, 6}}, false);
                v5 = new Vehicule("vertical", 2, new int[][]{{5, 5}, {6, 5}}, false);   
                v6 = new Vehicule("vertical", 2, new int[][]{{1, 3}, {2, 3}}, false);
                v7 = new Vehicule("horizontal", 3, new int[][]{{5, 1}, {5, 2}, {5, 3}}, false);
                v8 = new Vehicule("horizontal", 3, new int[][]{{4, 4}, {4, 5}, {4, 6}}, false);
                v9 = new Vehicule("vertical", 3, new int[][]{{1, 4}, {2, 4}, {3, 4}}, false);
                v10 = new Vehicule("vertical", 3, new int[][]{{2, 1}, {3, 1}, {4, 1}}, false);
                véhicules[0] = v1;
                véhicules[1] = v2;
                véhicules[2] = v3;
                véhicules[3] = v4;
                véhicules[4] = v5;
                véhicules[5] = v6;
                véhicules[6] = v7;
                véhicules[7] = v8;
                véhicules[8] = v9;
                véhicules[9] = v10;
                break;
            case "expert":
                v1 = new Vehicule("horizontal", 2, new int[][]{{3, 2}, {3, 3}}, true);
                v2 = new Vehicule("horizontal", 2, new int[][]{{4, 2}, {4, 3}}, false);
                v3 = new Vehicule("horizontal", 2, new int[][]{{6, 1}, {6, 2}}, false);
                v4 = new Vehicule("horizontal", 2, new int[][]{{1, 3}, {1, 4}}, false);
                v5 = new Vehicule("vertical", 2, new int[][]{{1, 1}, {2, 1}}, false);
                v6 = new Vehicule("vertical", 2, new int[][]{{1, 2}, {2, 2}}, false);
                v7 = new Vehicule("vertical", 2, new int[][]{{1, 5}, {2, 5}}, false);
                v8 = new Vehicule("vertical", 2, new int[][]{{1, 6}, {2, 6}}, false);
                v9 = new Vehicule("vertical", 2, new int[][]{{3, 6}, {4, 6}}, false);
                v10 = new Vehicule("vertical", 2, new int[][]{{3, 4}, {4, 4}}, false);
                Vehicule v11 = new Vehicule("vertical", 2, new int[][]{{5, 3}, {6, 3}}, false);
                Vehicule v12 = new Vehicule("horizontal", 3, new int[][]{{5, 4}, {5, 5}, {5, 6}}, false);
                Vehicule v13 = new Vehicule("horizontal", 3, new int[][]{{6, 4}, {6, 5}, {6, 6}}, false);
                Vehicule v14 = new Vehicule("vertical", 3, new int[][]{{3, 1}, {4, 1}, {5, 1}}, false);
                véhicules[0] = v1;
                véhicules[1] = v2;
                véhicules[2] = v3;
                véhicules[3] = v4;
                véhicules[4] = v5;
                véhicules[5] = v6;
                véhicules[6] = v7;
                véhicules[7] = v8;
                véhicules[8] = v9;
                véhicules[9] = v10;
                véhicules[10] = v11;
                véhicules[11] = v12;
                véhicules[12] = v13;
                véhicules[13] = v14;
                break;
        }
        this.véhicules = tempvéhicules;
    }


    /**
     * @return Le plateau de cet état
     */
    public boolean[][] getPlateau(){
        return plateau;
    }

	public RushHourState cloneState() {
        return new RushHourState();
	}

	public boolean equalsState(State o) {
        return Arrays.equals(plateau,((RushHourState)o).getPlateau());
	}
    
	public int hashState() {
		return Arrays.hashCode(plateau);
	}

	@Override
	public String toString() {
        return Arrays.toString(plateau);
	}

    
    /**
     * Test la possibilité de faire une action dans cet état
     * @param a L'action à tester
     * @return Vrai si l'action est possible  
     */
    
    public boolean isLegal(Action a, Vehicule v, int mouvement){
        boolean legal = false;
        int[][] pos = v.getPosition();
        int position = 0;
		if (a == RushHour.FWD) {
            if (v.getOrientation() == "horizontal") {        
                if (v.getNbCasesOccupees() == 3 
                && (!(isOccupied(pos[2][0], position + mouvement)))) {
                    position = pos[2][1];
                } else if (v.getNbCasesOccupees() == 2 
                    && (!(isOccupied(pos[1][0], position + mouvement)))) {
                    position = pos[1][1];
                } 
                if (!(position + mouvement > 6 || position + mouvement < 1)) {
                    legal = true;
                }
            } else if (v.getOrientation() == "vertical" 
                && (!(isOccupied(position - mouvement, pos[0][1])))) {
                position = pos[0][0];
                if (!(position - mouvement > 6 || position - mouvement < 1)) {
                    legal = true;
                }
            }
        } else if (a == RushHour.BWD) {
            if (v.getOrientation() == "horizontal"
            && (!(isOccupied(pos[0][0], position - mouvement)))) {        
                position = pos[0][0];
                if (!(position - mouvement > 6 || position - mouvement < 1)) {
                    legal = true;
                }
            } else if (v.getOrientation() == "vertical") {
                if (v.getNbCasesOccupees() == 3
                && (!(isOccupied(position + mouvement, pos[2][1])))) {
                    position = pos[2][1];
                } else if (v.getNbCasesOccupees() == 2
                && (!(isOccupied(position + mouvement, pos[1][1])))) {
                    position = pos[1][1];
                } 
                if (!(position + mouvement > 6 || position + mouvement < 1)) {
                    legal = true;
                } // problème avec les cases occupées 
            }
        }    
        return legal;
    }

    /**
     * Déplacer le véhicule vers l'avant
     */
    
    public void moveFWD(int mouvement, Vehicule v) {
        // avancer de nbCases (mouvement) vers l'avant
        // si horizontal, avancer vers la droite (+, y)
        // si vertical, avancer vers le haut (-, x)
		
        int[][] pos = v.getPosition();

        if (v.getOrientation() == "horizontal") {
            if (isLegal(RushHour.FWD, v, mouvement)) {
                    pos[0][1]+=mouvement;
                    pos[1][1]+=mouvement;
                if (v.getNbCasesOccupees() == 3) {
                    pos[2][1]+=mouvement;
               }
            }
        } else if (v.getOrientation() == "vertical") {
                if (isLegal(RushHour.FWD, v, mouvement)) {
                    pos[0][0]-=mouvement;
                    pos[1][0]-=mouvement;
                if (v.getNbCasesOccupees() == 3) {
                    pos[3][0]-=mouvement;
                }
            }// update les places occupées....
        }
	}

    /**
     * Déplacer le véhicule vers l'arrière 
     */
    
	public void moveBWD(int mouvement, Vehicule v) {
        // avancer de nbCases (mouvement) vers l'arrière
        // si horizontal, avancer vers la gauche (-, y)
        // si vertical, avancer vers le bas (+, x)
		
        int[][] pos = v.getPosition();

        if (v.getOrientation() == "horizontal") {
            if (isLegal(RushHour.FWD, v, mouvement)) {
                    pos[0][1]-=mouvement;
                    pos[1][1]-=mouvement;
                if (v.getNbCasesOccupees() == 3) {
                    pos[2][1]-=mouvement;
               }
            }
        } else if (v.getOrientation() == "vertical") {
                if (isLegal(RushHour.FWD, v, mouvement)) {
                    pos[0][0]+=mouvement;
                    pos[1][0]+=mouvement;
                if (v.getNbCasesOccupees() == 3) {
                    pos[3][0]+=mouvement;
                }
            }
        }
	}


    //
    // API privée, manipulation du jeux
    //
    
    // Calcule la distance entre le puzzle et la solution
    // https://en.wikipedia.org/wiki/Taxicab_geometry
    private double manathanDistance(){
        double result = 0;
		for (int val = 1; val <= 8; val++) {
			int locCurr = getPositionOf(val);
			int locGoal = EightPuzzle.GOAL_STATE.getPositionOf(val);
			result += Math.abs(getXCoord(locGoal)-getXCoord(locCurr));
			result += Math.abs(getYCoord(locGoal)-getYCoord(locCurr));
        }
        return result;
    }
    
	private boolean isOccupied(int x, int y) {
		return plateau[x][y];
	}

	private int[][] getPositionOfRouge() {
        int[][] position = null;
        for (Vehicule v : véhicules) {
            if (v.isRouge()) {
                position = v.getPosition();
                break;
            }
        }
        return position;
    }

	private void setOccupied(boolean occupé, int x, int y) {
		plateau[x][y] = occupé;
	}
    
}

    
}
