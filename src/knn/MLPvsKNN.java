package knn;

import mlp.MLP;
import mlp.Sigmoide;
import mlp.TangenteHyperbolique;
import mlp.TransferFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static knn.Imagette.chargerImagettes;

public class MLPvsKNN {

    public static void main(String[] args) {

        Imagette[] imagettes;
        Imagette[] imagettes_tests;
        imagettes = chargerImagettes("files/chiffres/train-images.idx3-ubyte", "files/chiffres/train-labels.idx1-ubyte");
        imagettes_tests = chargerImagettes("files/chiffres/t10k-images.idx3-ubyte","files/chiffres/t10k-labels.idx1-ubyte");
        // imagettes = chargerImagettes("files/fashion/train-images.idx3-ubyte", "files/fashion/train-labels.idx1-ubyte");
        //imagettes_tests = chargerImagettes("files/fashion/t10k-images.idx3-ubyte","files/fashion/t10k-labels.idx1-ubyte");

        TransferFunction tf = switch (args[1]) {
            case "th" -> new TangenteHyperbolique();
            case "sig" -> new Sigmoide();
            default -> new Sigmoide();
        };

        // Afficher les résultats de KNN
        Statistiques.stat_knn(new KNN(new Donnees(imagettes)), imagettes_tests, 1);

        // MLP
        int[] layers = new int[]{imagettes[0].getHeight() * imagettes[0].getWidth(), 50, 50 ,10};
        double learningRate = 0.5;
        MLP mlp = new MLP(layers, learningRate, tf);

        int i = 0;
        boolean valide = false;
        while (i < Integer.parseInt(args[0]) && !valide) {
            learn(mlp, imagettes);
            valide = test(mlp, imagettes_tests);
            i++;
            shuffleArray(imagettes_tests);
            shuffleArray(imagettes);
        }
    }

    public static void learn(MLP mlp, Imagette[] imagettes) {

        double tauxErreur = 0.0;
        // 10 apprentissages (10 * nombre d'images)
        //for (int nbIt = 0; nbIt < 10; nbIt++) {
            // Pour chaque image
            for (Imagette imagette : imagettes) {
                // Pour chaque pixel de l'image
                double[] pixels = new double[imagette.getHeight() * imagette.getWidth()];
                for (int i = 0; i < imagette.getHeight(); i++) {
                    for (int j = 0; j < imagette.getWidth(); j++) {
                        if (i > 0) {
                            pixels[(i * imagette.getHeight()) + j] = (double) imagette.getPixel(i, j) / 255;
                        } else {
                            pixels[j] = (double) imagette.getPixel(i, j) / 255;
                        }
                    }
                }
                // On s'attend à ce que le résultat soit un tableau de 0.0 excepté à l'index correspondant
                // à l'étiquette qui est égal à 1
                double[] attendu = new double[]{0., 0., 0., 0., 0., 0., 0., 0., 0., 0.};
                attendu[imagette.getEtiquette()] = 1.;
                tauxErreur += mlp.backPropagate(pixels, attendu);

            }
        //}
        System.out.println("erreur : "+tauxErreur);
    }

    public static boolean test(MLP mlp, Imagette[] imagettes) {

        ArrayList<double[]> resultat = new ArrayList<>();
        ArrayList<double[]> resultat_attendu = new ArrayList<>();

        int compteurValide = 0;

        for (Imagette imagette : imagettes) {
            double[] pixels = new double[imagette.getHeight() * imagette.getWidth()];
            for (int i = 0; i < imagette.getHeight(); i++) {
                for (int j = 0; j < imagette.getWidth(); j++) {
                    if (i > 0) {
                        pixels[(i * imagette.getHeight()) + j] = (double) imagette.getPixel(i, j) / 255;
                    } else {
                        pixels[j] = (double) imagette.getPixel(i, j) / 255;
                    }
                }
            }
            resultat.add(mlp.execute(pixels));
            double[] attendu = new double[]{0., 0., 0., 0., 0., 0., 0., 0., 0., 0.};
            attendu[imagette.getEtiquette()] = 1.;
            resultat_attendu.add(attendu);
        }

        for (int i = 0; i < resultat.size(); i++) {
            int indice_pg = -1;
            double pg_double = Double.MIN_VALUE;
            for (int j = 0; j < resultat.get(i).length; j++) {
                // Retient l'indice avec la valeur la plus grande
                if (pg_double < resultat.get(i)[j]) {
                    indice_pg = j;
                    pg_double = resultat.get(i)[j];
                }
            }
            // Si le plus grand des doubles des 10 résultats à le même indice que la sortie attendue
            if (resultat_attendu.get(i)[indice_pg] == 1.) {
                compteurValide++;
            }
            // Condition d'arrêt 1 == si il y'a moins de 98% de réussite
            if (i - compteurValide > resultat.size() * 0.02) {
                System.out.println("Bonnes reponses : " + compteurValide + "/" + i);
                System.out.println("Il y'a moins de 98% de reussite donc c'est un ECHEC");
                return false;
            }
        }
        // Sinon programme réussi == Condition d'arrêt 2 (le perceptron a plus de 98% de réussite)
        System.out.println("REUSSITE");
        return true;
    }

    public static void shuffleArray(Imagette[] tab) {
        // Mélanger les données
        Random rand = new Random();
        for (int j = 0; j < tab.length - 1; j++) {
            // Générer un index aléatoire entre 0 et i inclus
            int indexAleatoire = rand.nextInt(tab.length);

            // Échanger les éléments à l'index actuel et à l'index aléatoire
            Imagette temp = tab[j];
            tab[j] = tab[indexAleatoire];
            tab[indexAleatoire] = temp;
        }
    }
}
