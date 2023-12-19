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
        for (int i = 0; i < imagettes.length; i++) {
            System.out.println(i+1);
            final int etiquette = imagettes[i].getEtiquette();

            // Retourne un tableau d'entiers et incrémente le compteur si tous les éléments sont égaux à l'étiquette
            int[] temp = algo.kPlusProches(imagettes[i], distance);

            // Vérifie si tous les éléments de temp sont égaux à l'étiquette de l'imagette
            if (Arrays.stream(temp).allMatch(nb -> nb == etiquette)) {
                valides++; // Incrémente le compteur de valides
            }
        }
        System.out.println( ((double) valides / imagettes.length * 100)+"% de reussite avec le(s) "+distance+" plus proche(s) voisin(s)");
    }
}
