package knn;

import java.util.Arrays;

public class Statistiques {

    public static void stats_pp(PlusProche algo, Donnees donnees) {
        Imagette[] imagettes = donnees.getImagettes();
        // Compte les occurrences de chaque chiffre
        int[] occurrences = new int[10];
        for (int i = 0; i < imagettes.length; ) {
            // Retourne un entier entre 0 et 9 et incrémente le bon entier dans la liste d'occurrences
            occurrences[algo.predictLabel(imagettes[i])]++;
        }

    }

    public static void stat_knn(KNN algo, Imagette[] imagettes, int distance) {
        int valides = 0;
        /// A FAIRE
        for (int i = 0; i < imagettes.length; ) {
            // Retourne un entier entre 0 et 9 et incrémente le bon entier dans la liste d'occurrences
            int[] temp = algo.kPlusProches(imagettes[i],distance);
            if (Arrays.stream(temp).allMatch( nb -> nb == imagettes[i].getEtiquette())) {
                valides++;
            }

        }
        System.out.println((valides/ imagettes.length)+"% de reussite avec le(s) "+distance+" plus proche(s) voisin(s)");
    }
}
