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
        String[] couches = tailles_Couches.split(",");
        List<Integer> couchesInt = Arrays.stream(couches).map(Integer::parseInt).toList();
        double tauxApprentissage = Double.parseDouble(args[1]);
        String fonction = args[2];

        switch (fonction){
            case "sig" :
                break;
            case "th":
                break;
        }




    }
}
