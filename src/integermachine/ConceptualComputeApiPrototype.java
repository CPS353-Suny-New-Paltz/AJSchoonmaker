package integermachine;

import project.annotations.ConceptualAPIPrototype;

public class ConceptualComputeApiPrototype implements ConceptualComputeApi {

    @ConceptualAPIPrototype
    @Override
    public ComputeResult compute(int n) {
        // Spec: 1 < n < Integer.MAX_VALUE
        // For n <= 2, there is no prime strictly smaller than n; define result as 1.
        if (n <= 2) {
            return new ComputeResult(n, 1);
        }

        int candidate = n - 1; // strictly smaller than n
        while (candidate >= 2 && !isPrime(candidate)) {
            candidate--;
        }

        int result = (candidate >= 2) ? candidate : 1;
        return new ComputeResult(n, result);
    }

    private boolean isPrime(int x) {
        if (x < 2) {
        	return false;
        }
        if (x == 2) {
        	return true;
        }
        if (x % 2 == 0) {
        	return false;
        }

        int limit = (int) Math.sqrt(x);
        for (int d = 3; d <= limit; d += 2) {
            if (x % d == 0) {
                return false;
            }
        }
        return true;
    }
}
