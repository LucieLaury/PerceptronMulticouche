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

        Imagette[] imagettes_apprentissage;
        Imagette[] imagettes_tests;
        imagettes_apprentissage = chargerImagettes("files/train-images.idx3-ubyte","files/train-labels.idx1-ubyte");;
        imagettes_tests = chargerImagettes("files/t10k-images.idx3-ubyte","files/t10k-labels.idx1-ubyte");

        TransferFunction tf = switch (args[1]) {
            case "th" -> new TangenteHyperbolique();
            case "sig" -> new Sigmoide();
            default -> new Sigmoide();
        };

        // MLP

        int[] layers = new int[]{imagettes_apprentissage[0].getHeight()*imagettes_apprentissage[0].getWidth(),1,10};
        double learningRate = 0.1;
        MLP mlp = new MLP(layers, learningRate, tf);

        int i = 0;
        boolean valide = false;
        while (i < Integer.parseInt(args[0]) && !valide) {
            learn(mlp, imagettes_apprentissage);
            valide = test(mlp, imagettes_apprentissage);
            System.out.println("learn + test fini");
            i++;
            // Mélanger les données
            Random rand = new Random();
            for (int j = 0; j < imagettes_apprentissage.length - 1; j++) {
                // Générer un index aléatoire entre 0 et i inclus
                int indexAleatoire = rand.nextInt(imagettes_apprentissage.length);

                // Échanger les éléments à l'index actuel et à l'index aléatoire
                Imagette temp = imagettes_apprentissage[i];
                imagettes_apprentissage[i] = imagettes_apprentissage[indexAleatoire];
                imagettes_apprentissage[indexAleatoire] = temp;
            }

        }
    }

    public static void learn(MLP mlp, Imagette[] imagettes) {
        for (int nbIt = 0 ; nbIt < 10000 ; nbIt++) {
            for (Imagette imagette : imagettes) {
                double[] pixels = new double[imagette.getHeight() * imagette.getWidth()];
                for (int i = 0; i < imagette.getHeight(); i++) {
                    for (int j = 0; j < imagette.getWidth(); j++) {
                        if (i > 0) {
                            pixels[(i * imagette.getHeight()) + j] = imagette.getPixel(i, j) / 255;
                        } else {
                            pixels[j] = imagette.getPixel(i, j) / 255;
                        }
                    }
                }
                double[] attendu = new double[]{0., 0., 0., 0., 0., 0., 0., 0., 0., 0.};
                attendu[imagette.getEtiquette()] = 1.;
                mlp.backPropagate(pixels, attendu);

            }
        }
        System.out.println("learn fini");
    }

    public static boolean test(MLP mlp, Imagette[] imagettes) {

        ArrayList<double[]> resultat = new ArrayList<>();
        ArrayList<double[]> resultat_attendu = new ArrayList<>();

        int compteurValide = 0;

        for (Imagette imagette : imagettes) {
            double[] pixels = new double[imagette.getHeight()*imagette.getWidth()];
            for (int i = 0; i < imagette.getHeight(); i++) {
                for (int j = 0; j < imagette.getWidth(); j++) {
                    if (i > 0) {
                        pixels[(i * imagette.getHeight()) + j] = imagette.getPixel(i, j)/255;
                    } else {
                        pixels[j] = imagette.getPixel(i, j)/255;
                    }
                }
            }
            resultat.add(mlp.execute(pixels));
            double[] attendu = new double[]{0.,0.,0.,0.,0.,0.,0.,0.,0.,0.};
            attendu[imagette.getEtiquette()] = 1.;
            resultat_attendu.add(attendu);
        }
        // Vérification de chaque élément (approximation à 0.999 / 1.001 ou 0.001 / -0.001)
        for (int i = 0 ; i < resultat.size() ; i++) {
            int indice_pg = -1;
            double pg_double = Double.MIN_VALUE;
            for (int j = 0; j < resultat.get(i).length; j++) {
                double res_temp = resultat.get(i)[j];
                double res_att_temp = resultat_attendu.get(i)[j];
                // Retient l'indice le plus grand
                if (pg_double < resultat.get(i)[j]) {
                    indice_pg = j;
                    pg_double = resultat.get(i)[j];

                }
            }
            // Si le plus grand des doubles des 10 résultats à le même indice que la sortie attendue
            if (resultat_attendu.get(i)[indice_pg] == 1.) {
                //System.out.println("imagette VALID = "+ Arrays.toString(resultat.get(i)));
                compteurValide++;
            } else {
                //System.out.println("imagette INVALID = "+ Arrays.toString(resultat.get(i)));
            }
            // Condition d'arrêt == si il y'a moins de 95% de réussite
            if (i - compteurValide > resultat.size()*0.05) {
                System.out.println("nbValides == "+compteurValide+"/"+i);
                System.out.println(" - de 95% de reussite == ECHEC");
                return false;
            }
        }
        System.out.println("REUSSITE");
        return true;
    }
}
