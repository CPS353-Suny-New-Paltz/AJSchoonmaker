package project.checkpointtests;

import integermachine.ConceptualComputeApi;
import integermachine.ConceptualComputeApiImpl;
import integermachine.Orchestrator;
import integermachine.StorageProcessApi;
import integermachine.StorageProcessApiImpl;
import integermachine.InputSourceRef;
import integermachine.OutputSinkRef;
import integermachine.Delimiters;
import integermachine.JobConfig;

public class ManualTestingFramework {

    public static final String INPUT = "manualTestInput.txt";
    public static final String OUTPUT = "manualTestOutput.txt";

    public static void main(String[] args) {
        StorageProcessApi storage = new StorageProcessApiImpl();
        ConceptualComputeApi compute = new ConceptualComputeApiImpl();
        Orchestrator orchestrator = new Orchestrator(storage, compute);

        JobConfig config = new JobConfig(
                new InputSourceRef(INPUT),
                new OutputSinkRef(OUTPUT),
                new Delimiters(",", ":")
        );

        orchestrator.runJob(config);
    }
}
