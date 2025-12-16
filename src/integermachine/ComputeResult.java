package integermachine;

public class ComputeResult {
    private final int input;
    private final int result;

    public ComputeResult(int input, int result) {
        this.input = input;
        this.result = result;
    }

    public int getInput() {
        return input;
    }

    public int getResult() {
        return result;
    }

    // REQUIRED by TestConceptualComputeApi
    public int getLargestPrimeBelow() {
        return result;
    }
}
