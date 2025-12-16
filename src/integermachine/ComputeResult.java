package integermachine;

public class ComputeResult {

    private final int input;
    private final int computedValue;

    public ComputeResult(int input, int computedValue) {
        this.input = input;
        this.computedValue = computedValue;
    }

    public int getInput() {
        return input;
    }

    public int getComputedValue() {
        return computedValue;
    }
}
