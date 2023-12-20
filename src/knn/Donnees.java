package knn;

import java.util.ArrayList;
import java.util.Arrays;

public class Donnees {
    private Imagette[] imagettes;

    public Donnees(Imagette[] imagettes) {
        this.imagettes = imagettes;
    }

    public Imagette[] getImagettes() {
        return imagettes;
    }

    public ArrayList<Imagette> getImagettesArrayList() {
        return new ArrayList<>(Arrays.asList(imagettes));
    }

    public void setImagettes(Imagette[] imagettes) {
        this.imagettes = imagettes;
    }
}
