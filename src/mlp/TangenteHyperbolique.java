package mlp;

public class TangenteHyperbolique implements TransferFunction{
    @Override
    public double evaluate(double value) {
        return Math.tanh(value);
    }

    @Override
    public double evaluateDer(double value) {
        return 1 - Math.pow(value,2);
    }
}
