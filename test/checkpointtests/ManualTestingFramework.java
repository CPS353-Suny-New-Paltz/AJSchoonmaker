package project.checkpointtests;

package project.checkpointtests;

import integermachine.*;

public class ManualTestingFramework {

    public static final String INPUT = "manualTestInput.txt";
    public static final String OUTPUT = "manualTestOutput.txt";

    public static void main(String[] args) {

        // 1. Instantiate real implementations
        StorageProcessApi storage = new StorageProcessApiImpl();
        ConceptualComputeApi compute = new ConceptualComputeApiImpl();
        Orchestrator orchestrator = new Orchestrator(storage, compute);
        UserJobApi userApi = new UserJobApiImpl(orchestrator);

        // 2. Configure input/output
        InputSourceRef input = new InputSourceRef(INPUT);
        OutputSinkRef output = new OutputSinkRef(OUTPUT);
        Delimiters delimiters = new Delimiters(",", ":");

        JobConfig config = new JobConfig(input, output, delimiters);

        // 3. Run the job
        userApi.submitJob(config);
    }
}
