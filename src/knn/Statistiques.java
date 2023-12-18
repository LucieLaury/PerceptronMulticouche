package knn;

public class Statistiques {

    public static void stats(AlgoClassification algo,Donnees donnees) {
        if (algo instanceof PlusProche) {
            Imagette[] imagettes = donnees.getImagettes();
            // Compte les occurrences de chaque chiffre
            int[] occurrences = new int[10];
            for (int i = 0 ; i < imagettes.length ; ) {
                // Retourne un entier entre 0 et 9 et incrémente le bon entier dans la liste d'occurrences
                occurrences[algo.predictLabel(imagettes[i])]++;
            }
            System.out.println(occurrences);
        } else if (algo instanceof KNN) {
            /// A FAIRE
            int compteur = 0;
            int nbValides = 0;
            // Vérifier que la liste d'entiers retournée par KNN soit composée uniquement? de l'entier attendu.

        }
    }
}
