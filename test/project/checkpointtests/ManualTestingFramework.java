package project.checkpointtests;

import integermachine.ConceptualComputeApi;
import integermachine.ConceptualComputeApiImpl;
import integermachine.Delimiters;
import integermachine.InputSourceRef;
import integermachine.JobConfig;
import integermachine.Orchestrator;
import integermachine.OutputSinkRef;
import integermachine.StorageProcessApi;
import integermachine.StorageProcessApiImpl;
import integermachine.UserJobApi;
import integermachine.UserJobApiImpl;

public class ManualTestingFramework {

    public static final String INPUT = "manualTestInput.txt";
    public static final String OUTPUT = "manualTestOutput.txt";

    public static void main(String[] args) {
        try {
            StorageProcessApi storage = new StorageProcessApiImpl();
            ConceptualComputeApi compute = new ConceptualComputeApiImpl();
            Orchestrator orchestrator = new Orchestrator(storage, compute);
            UserJobApi api = new UserJobApiImpl(orchestrator);

            JobConfig config = new JobConfig(
                    new InputSourceRef(INPUT),
                    new OutputSinkRef(OUTPUT),
                    new Delimiters(",", ":")
            );

            // Network boundary call — should never throw out to main()
            api.submitJob(config);
        } catch (Exception e) {
            // CP5 requirement: do NOT let exceptions propagate out of process/network boundaries.
            // main() is the outermost boundary for this manual test.
        }
    }
}
