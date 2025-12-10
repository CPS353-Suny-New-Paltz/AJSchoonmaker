package integermachine;

public class ConceptualComputeApiImpl implements ConceptualComputeApi {

    @Override
    public ComputeResult compute(int n) {
        // Checkpoint 3: empty implementation.
        // Later this will compute the largest prime strictly smaller than n.
        // For now it is just returning a dummy result.
        return new ComputeResult(n, 1);
    }
}
