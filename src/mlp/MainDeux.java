package mlp;

import java.util.Arrays;

public class MainDeux {

    public static void main(String[] args) {

        double[][] tableET = {{-1., 1., -1.}, {-1., -1., -1.}, {1., -1., -1.}, {1., 1., 1.}};
        double[][] tableOU = {{-1., -1., -1.}, {-1., 1., 1.}, {1., -1., 1.}, {1., 1., 1.}};
        double[][] tableXOR = {{-1, -1., -1.}, {-1., 1., 1.}, {1., -1., 1.}, {1., 1., 1.}};

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

        MLP mlp = new MLP(new int[]{1},0.05,new TangenteHyperbolique());

        double[] tableTaux = new double[]{Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE};
        int nb_iterations = Integer.parseInt(args[1]);
        double currentTaux;

        for (int i = 0; i < nb_iterations; i++) {
            for (int j = 0 ; j < tableTaux.length ; j++) {
                currentTaux =  mlp.backPropagate(Arrays.copyOfRange(currentTable[j],0,currentTable[j].length-1),
                        Arrays.copyOfRange(currentTable[j],currentTable[j].length-1,currentTable[j].length));
                if (tableTaux[j] > currentTaux)
                       tableTaux[j] = currentTaux;
            }
        }
    }
}
