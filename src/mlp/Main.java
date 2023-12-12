package mlp;

import java.util.Arrays;

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
        int[] layers = new int[]{2,1,2};
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
        }
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
        boolean isValid = true;
        for (double[] d : currentTable) {
            resultat = mlp.execute(Arrays.copyOfRange(d,0,d.length-nbSorties));
            resultat_attendu = Arrays.copyOfRange(d,d.length-nbSorties,d.length);
            if (!Arrays.equals(resultat,resultat_attendu)) {
                isValid = false;
                System.out.println("false == "+
                        Arrays.toString(Arrays.copyOfRange(d, 0, d.length - nbSorties))
                        + " devrait etre "+ Arrays.toString(resultat_attendu)+ " .... trouve == "+
                        Arrays.toString(resultat));
            } else {
                System.out.println("true");
            }
        }
        return isValid;
    }
}