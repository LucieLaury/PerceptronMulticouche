package knn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class KNN extends AlgoClassification{
    public int k;
    public Donnees donnees;
    public KNN(int k, Donnees donnees) throws IOException {
        super();
        this.k = k;
        this.donnees = donnees;
    }

    @Override
    public int predictLabel(Imagette img) {
        // recuperer les imagettes
        ArrayList<Imagette> data = donnees.getImagettesArrayList();
        TreeMap<Integer, Integer> mins = new TreeMap<>();
        for (int i = 0; i < this.k; i++) {
            Imagette imagette = data.get(i);
            int dist = imagette.diffCouleur(img);
            mins.put(dist, imagette.getEtiquette());
        }
        // calculer les distances et en faire une liste
        // trouver les k voisins les plus proches
        for (int i = this.k; i < data.size(); i++) {
            Imagette imagette = data.get(i);
            int dist = imagette.diffCouleur(img);
            int max = mins.lastKey();
            if (dist < max) {
                mins.remove(max);
                mins.put(dist, imagette.getEtiquette());
            }
        }
        // recuperer les labels des voisins
        ArrayList<Integer> labels = new ArrayList<>(mins.values());
        // savoir quel est le label le plus frequent
        int res = labels.get(0);
        int frequence = Collections.frequency(labels, res);
        for (Integer label : labels) {
            int nb = Collections.frequency(labels, label);
            if (nb > frequence) {
                res = label;
                frequence = nb;
            }
        }
        return res;
    }
}