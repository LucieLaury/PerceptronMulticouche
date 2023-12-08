package mlp;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double[][] tableET = {{-1., 1., -1.}, {-1., -1., -1.}, {1., -1., -1.}, {1., 1., 1.}};
        double[][] tableOU = {{-1., -1., -1.}, {-1., 1., 1.}, {1., -1., 1.}, {1., 1., 1.}};
        double[][] tableXOR = {{-1, -1., -1.}, {-1., 1., 1.}, {1., -1., 1.}, {1., 1., 1.}};

        //args[0] = 5,3,2;
        String tailles_Couches = args[0];
        String[] couchesStr = tailles_Couches.split(",");
        int[] couches = new int[couchesStr.length];
        for(int i = 0; i<couchesStr.length; i++){
            couches[i] = Integer.parseInt(couchesStr[i]);
        }
        double tauxApprentissage = Double.parseDouble(args[1]);
        String fonction = args[2];

        MLP mlp;
        switch (fonction){
            case "th":
                mlp = new MLP(couches, tauxApprentissage, new TangenteHyperbolique());
                break;
            case "sig" :
            default:
                mlp = new MLP(couches, tauxApprentissage, new Sigmoide());
                break;
        }
        mlp.runPerceptron(10, tableET);



    }
}
