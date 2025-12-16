package integermachine;

import java.util.ArrayList;
import java.util.List;

public class Orchestrator {

    private final StorageProcessApi storage;
    private final ConceptualComputeApi compute;

    public Orchestrator(StorageProcessApi storage,
                        ConceptualComputeApi compute) {
        this.storage = storage;
        this.compute = compute;
    }

    public void runJob(JobConfig config) {

        IntStreamBatch batch =
                storage.readInputs(config.getInputSource());

        List<KeyValueResult> results = new ArrayList<>();

        for (int value : batch.getValues()) {
            ComputeResult r = compute.compute(value);

            results.add(
                new KeyValueResult(
                    r.getInput(),
                    String.valueOf(r.getLargestPrimeBelow())

                )
            );
        }

        storage.writeOutputs(
            config.getOutputSink(),
            results,
            config.getDelimiters()
        );
    }
}
