package knn;

public class KNN extends AlgoClassification {
    private Donnees donnees;

    public KNN(Donnees donnees) {
        this.donnees = donnees;
    }

    public int[] kPlusProches(Imagette imagette, int k) {
        int[] kPP = new int[k];
        double[] val_kPP = new double[k];

        int width = imagette.getWidth();
        int height = imagette.getHeight();
        Imagette[] imagettes = donnees.getImagettes();
        int longueur = imagettes.length;
        Imagette current = null;
        double compteur = 0;

        for (int i = 0; i < longueur; i++) {
            compteur = 0;
            current = imagettes[i];

            for (int j = 0; j < height; j++) {
                for (int l = 0; l < width; l++) {
                    compteur += Math.pow((current.getPixel(j, l) - imagette.getPixel(j, l)), 2);
                }
            }

            // Revérifier
            for (int n = 0; n < val_kPP.length; n++) {
                if (compteur < val_kPP[n]) {

                    for (int m = k - 1; m > n; m--) {
                        val_kPP[m] = val_kPP[m - 1];
                        kPP[m] = kPP[m - 1];
                    }

                    val_kPP[n] = compteur;
                    kPP[n] = current.getEtiquette();
                    break;
                }
            }
        }

        return kPP;
    }
}
