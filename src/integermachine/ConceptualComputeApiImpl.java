package integermachine;

public class ConceptualComputeApiImpl implements ConceptualComputeApi {

    @Override
    public ComputeResult compute(int n) {
        int largestPrime = largestPrimeStrictlyBelow(n);
        return new ComputeResult(n, largestPrime);
    }

    private int largestPrimeStrictlyBelow(int n) {
        for (int candidate = n - 1; candidate >= 2; candidate--) {
            if (isPrime(candidate)) {
                return candidate;
            }
        }
        // For inputs <= 2, there is no prime strictly below it.
        // integration test expects 1 -> 1 (so "failure" becomes 1).
        return 1;
    }

    private boolean isPrime(int x) {
        if (x < 2) return false;
        if (x == 2) return true;
        if (x % 2 == 0) return false;

        for (int d = 3; d * d <= x; d += 2) {
            if (x % d == 0) return false;
        }
        return true;
    }
}
