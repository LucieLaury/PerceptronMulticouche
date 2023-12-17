package mlp;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        double[][] tableET = { {0.0, 0.0, 0.0}, {0.0, 1., 0.0}, {1., 0.0, 0.0}, {1., 1., 1.}};
        double[][] tableOU = {{0.0, 0.0, 0.0}, {0.0, 1., 1.}, {1., 0.0, 1.}, {1., 1., 1.}};
        double[][] tableXOR = { {0.0, 0.0, 0.0}, {0.0, 1., 1.},{1., 0.0, 1.}, {1., 1., 0.0}};
        double[][] tableET2 = { {0.0, 0.0, 1.,0.0}, {0.0, 1., 1., 0.0}, {1., 0.0,1., 0.0}, {1., 1.,0.0, 1.}};
        double[][] tableOU2 = {{0.0, 0.0, 1.0, 0.0}, {0.0, 1.,0.0, 1.}, {1., 0.0,0.0, 1.}, {1., 1.,0.0, 1.}};
        double[][] tableXOR2 = { {0.0, 0.0,1.0, 0.0}, {0.0, 1.,0.0, 1.},{1., 0.0,0.0, 1.}, {1., 1.,1.0, 0.0}};

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
            case "et2":
                currentTable = tableET2.clone();
                break;
            case "ou2":
                currentTable = tableOU2.clone();
                break;
            case "xor2":
                currentTable = tableXOR2.clone();
                break;
            default:
                currentTable = tableET.clone();
                break;
        }
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

        // Par défaut, deux entrées, un de calcul et un de sortie
        // Ces valeurs seront modifiées pour certaines applications (XOR et les deux sorties notamment)
        int[] layers = new int[]{2,1,1};
        double learningRate = 0.1;
        MLP mlp = new MLP(layers,learningRate,tf);

        int nbIterations = Integer.parseInt(args[2]);
        int i = 0;
        boolean resultsFound = false;

        while (i < nbIterations && !resultsFound) {
            // Apprentissage
            learn(mlp,currentTable, layers[layers.length-1]);
            // Test sur les exemples
            resultsFound = test(mlp,currentTable, layers[layers.length-1]);
            i++;
            // Mélanger les données
            Random rand = new Random();
            for (int j = 0; j < currentTable.length - 1; j++) {
                // Générer un index aléatoire entre 0 et i inclus
                int indexAleatoire = rand.nextInt(currentTable.length-1);

                // Échanger les éléments à l'index actuel et à l'index aléatoire
                double[] temp = currentTable[j];
                currentTable[j] = currentTable[indexAleatoire];
                currentTable[indexAleatoire] = temp;
            }

        }
        System.out.println("Nombre d'iterations : "+i);
        if (resultsFound) System.out.println("Tous les exemples sont passes");
        //System.out.println("VALEURS MLP : ");
        //printMLP(mlp);
    }

    public static void learn(MLP mlp, double[][] currentTable, int nbSorties) {

        // APPRENTISSAGE
        for (int i = 0 ; i < 100000 ; i++) {
            // Pour chaque élément du tableau
            for (int j = 0 ; j < currentTable.length ; j++) {
                // On apprend pour chaque ligne de la table de vérité
                mlp.backPropagate(Arrays.copyOfRange(currentTable[j],0,currentTable[j].length-nbSorties),
                        Arrays.copyOfRange(currentTable[j],currentTable[j].length-nbSorties,currentTable[j].length));
            }
        }
    }

    public static boolean test(MLP mlp, double[][] currentTable, int nbSorties) {

        // Test sur un exemple de la table aléatoire
        double[] resultat;
        double[] resultat_attendu;
        for (double[] d : currentTable) {
            resultat = mlp.execute(Arrays.copyOfRange(d,0,d.length-nbSorties));
            resultat_attendu = Arrays.copyOfRange(d,d.length-nbSorties,d.length);
            for (int i = 0 ; i < resultat.length ; i++) {
                if (!(resultat_attendu[i]-0.001 < resultat[i] && resultat[i] < resultat_attendu[i]+0.001)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printMLP(MLP mlp) {

        // Parcours et affichage des couches et neurones de calculs ( i = 0 == entrées et i = length - 1 == sorties
        for (int i = 0 ; i < mlp.fLayers.length ; i++) {
            if (i == 0) {
                System.out.println("--------- NEURONES D'ENTREE ---------");
            } else if (i == mlp.fLayers.length - 1) {
                System.out.println("--------- NEURONES DE SORTIE ---------");
            } else {
                System.out.println("--------- NEURONES DE CALCUL DE LA COUCHE " + i+" ---------");
            }
            int nbNeuronesCouches = 1;
            for (Neuron n : mlp.fLayers[i].Neurons) {
                System.out.println("x"+nbNeuronesCouches+" = "+n.Value);
                System.out.println("w"+nbNeuronesCouches+" = "+ Arrays.toString(n.Weights));
                System.out.println("delta "+nbNeuronesCouches+" = "+n.Delta);
                System.out.println("biais "+nbNeuronesCouches+" = "+n.Bias);
                nbNeuronesCouches++;
            }
        }
    }
}