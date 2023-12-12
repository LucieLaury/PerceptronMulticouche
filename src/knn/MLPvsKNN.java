package knn;

import mlp.MLP;
import mlp.Sigmoide;
import mlp.TangenteHyperbolique;
import mlp.TransferFunction;

import java.util.ArrayList;
import java.util.Arrays;

import static knn.Imagette.chargerImagettes;

public class MLPvsKNN {

    public static void main(String[] args) {

        Imagette[] imagettes_apprentissage;
        Imagette[] imagettes_tests;
        imagettes_apprentissage = chargerImagettes("files/train-images.idx3-ubyte","files/train-labels.idx1-ubyte");;
        imagettes_tests = chargerImagettes("files/t10k-images.idx3-ubyte","files/t10k-labels.idx1-ubyte");

        TransferFunction tf = null;
        switch (args[1]) {
            case "th":
                tf = new TangenteHyperbolique();
                break;
            case "sig":
                tf = new Sigmoide();
                break;
            default:
                tf = new Sigmoide();
                break;
        }

        // MLP

        int[] layers = new int[]{imagettes_apprentissage[0].getHeight()*imagettes_apprentissage[0].getWidth(),1,10};
        double learningRate = 0.1;
        MLP mlp = new MLP(layers, learningRate, tf);

        int i = 0;
        boolean valide = false;
        while (i < Integer.parseInt(args[0]) && !valide) {
            learn(mlp, imagettes_apprentissage);
            test(mlp, imagettes_tests);
        }
    }

    public static void learn(MLP mlp, Imagette[] imagettes) {
        double[] pixels = new double[]{};
        for (Imagette imagette : imagettes) {
            for (int i = 0 ; i < imagette.getHeight() ; i++) {
                for (int j = 0; j < imagette.getWidth() ; i++) {
                    if (i > 0) {
                        pixels[(i * imagette.getHeight()) + j] = imagette.getPixel(i, j);
                    } else {
                        pixels[j] = imagette.getPixel(i, j);
                    }
                }
            }
            double[] attendu = new double[]{0.,0.,0.,0.,0.,0.,0.,0.,0.,0.};
            attendu[imagette.getEtiquette()] = 1.;
            mlp.backPropagate(pixels,attendu);
        }
    }

    public static boolean test(MLP mlp, Imagette[] imagettes) {

        ArrayList<double[]> resultat = new ArrayList<>();
        ArrayList<double[]> resultat_attendu = new ArrayList<>();

        int compteur = 0;

        for (Imagette imagette : imagettes) {
            double[] pixels = new double[]{};
            for (int i = 0; i < imagette.getHeight(); i++) {
                for (int j = 0; j < imagette.getWidth(); i++) {
                    if (i > 0) {
                        pixels[(i * imagette.getHeight()) + j] = imagette.getPixel(i, j);
                    } else {
                        pixels[j] = imagette.getPixel(i, j);
                    }
                }
            }
            resultat.add(mlp.execute(pixels));
            double[] attendu = new double[]{0.,0.,0.,0.,0.,0.,0.,0.,0.,0.};
            attendu[imagette.getEtiquette()] = 1.;
            resultat_attendu.add(attendu);
        }
        // Vérification de chaque élément (approximation à 0.999 ou 0.001 / -0.001)
        for (int i = 0 ; i < resultat.size() ; i++) {
            boolean valide = true;
            for (int j = 0; j < resultat.get(i).length;i++) {
                double res_temp = resultat.get(i)[j];
                double res_att_temp = resultat_attendu.get(i)[j];
                if (res_att_temp == 0.) {
                    if (!(res_temp >= -0.001 || res_temp <= 0.001)) {
                        valide = false;
                        break;
                    }
                } else {
                    if (!(res_temp >= -0.001 || res_temp <= 0.001)) {
                        valide = false;
                        break;
                    }
                }
            }
        }

        return true;
    }
}
