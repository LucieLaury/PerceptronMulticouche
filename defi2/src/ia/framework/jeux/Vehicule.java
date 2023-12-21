package ia.framework.jeux;

public class Vehicule {
    
    private String orientation;
    private int nbCasesOccupees;
    private int[][] position;
    private boolean estRouge;

    public Vehicule(String orientation, int nbCasesOccupees, int[][] position, boolean estRouge) {
        this.orientation = orientation;
        this.nbCasesOccupees = nbCasesOccupees;
        this.position = position;
        this.estRouge = estRouge;
    }

    public String getOrientation() {
        return orientation;
    }

    public int getNbCasesOccupees() {
        return nbCasesOccupees;
    }

    public int[][] getPosition() {
        return position;
    }

    public boolean isRouge() {
        return estRouge;
    }
}
