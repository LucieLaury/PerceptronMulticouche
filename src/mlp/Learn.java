package mlp;

import java.util.Arrays;
import java.util.Random;

public class Learn {

    public static void main(String[] args) {

        double[][] tableET = {{-1., 1., -1.}, {-1., -1., -1.}, {1., -1., -1.}, {1., 1., 1.}};
        double[][] tableOU = {{-1., -1., -1.}, {-1., 1., 1.}, {1., -1., 1.}, {1., 1., 1.}};
        double[][] tableXOR = {{-1, -1., -1.}, {-1., 1., 1.}, {1., -1., 1.}, {1., 1., -1.}};

        double[][] currentTable;
        switch (args[0]) {
            case "et":
                currentTable = tableET.clone();
                break;
            case "ou":
                currentTable = tableOU.clone();
                break;
            case "xor":
                currentTable = tableXOR.clone();
                break;
            default:
                currentTable = tableET.clone();
                break;
        }


        // Variables nécessaires au bon fonctionnement du programme
        // Déclaration du MLP
        TransferFunction tf = null;
        switch (args[3]) {
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
        MLP mlp = new MLP(new int[]{1},Double.parseDouble(args[2]),tf);
        double[] tableTaux = new double[]{Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE};
        int nb_iterations = Integer.parseInt(args[1]);
        double currentTaux;
        int i = 0;

        while (i < nb_iterations && !verifyTabTaux(tableTaux)) {
            // Mélanger le tableau courant à tester
            Random rand = new Random();
            for (int it = currentTable.length - 1; i > 0; i--) {
                int index = rand.nextInt(it + 1);

                // Échanger les éléments à la position i et index
                double[] temp = currentTable[it];
                currentTable[it] = currentTable[index];
                currentTable[index] = temp;
            }

            // Pour chaque élément du tableau
            for (int j = 0 ; j < tableTaux.length ; j++) {
                currentTaux =  mlp.backPropagate(Arrays.copyOfRange(currentTable[j],0,currentTable[j].length-1),
                        Arrays.copyOfRange(currentTable[j],currentTable[j].length-1,currentTable[j].length));
                if (tableTaux[j] > currentTaux) {
                    System.out.println("Ancien taux de tableTaux["+j+"] : "+tableTaux[j]);
                    System.out.println("Nouveau taux : "+currentTaux);
                    tableTaux[j] = currentTaux;
                }

            }
            i++;
        }
    }

    public static boolean verifyTabTaux(double[] tab) {
        boolean retour = true;
        for (double d : tab) {
            if (d != 0.0) {
                retour = false;
                break;
            }
        }
        return retour;
    }
}
