package knn;

public class PlusProche extends AlgoClassification {

    private Donnees donnees;

    public PlusProche(Donnees d) {
        this.donnees = d;
    }

    @Override
    public int predictLabel(Imagette imagette) {
        double min_valeur = Double.MAX_VALUE;
        int pp_etiquette = -1;
        int width = imagette.getWidth();
        int height = imagette.getHeight();
        Imagette[] imagettes = donnees.getImagettes();
        int longueur = imagettes.length;
        Imagette current = null;
        double compteur = 0;
        for (int i = 0 ; i < longueur ; i++) {
            compteur = 0;
            current = imagettes[i];
            for (int j = 0 ; j < height ; j++) {
                for (int k = 0 ; k < width ; k++) {
                    compteur += Math.pow((current.getPixel(j,k) - imagette.getPixel(j,k)),2);
                }
            }
            if (compteur < min_valeur) {
                min_valeur = compteur;
                pp_etiquette = current.getEtiquette();
            }

        }


        return pp_etiquette;
    }
}
