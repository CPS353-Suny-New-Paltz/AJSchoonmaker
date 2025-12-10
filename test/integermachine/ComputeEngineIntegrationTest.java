package integermachine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;


public class ComputeEngineIntegrationTest {

    @Test
    void integration_largestPrimeBelow_forSampleInput() {
        // Input: [1, 10, 25], no delimiters specified
        InMemoryInputConfig inputConfig = new InMemoryInputConfig(List.of(1, 10, 25));
        InMemoryOutputConfig outputConfig = new InMemoryOutputConfig();

        StorageProcessApi storage = new InMemoryStorageProcessApi();
        ConceptualComputeApi compute = new ConceptualComputeApiImpl(); // empty impl for now
        Orchestrator orchestrator = new Orchestrator(storage, compute);

        JobConfig config = new JobConfig(inputConfig, outputConfig, null);

        orchestrator.runJob(config);

        // What we EXPECT once everything is implemented:
        // 1 -> 1 (no smaller prime), 10 -> 7, 25 -> 23
        assertEquals(
                List.of("1:1", "10:7", "25:23"),
                outputConfig.getOutputs()
        );
    }
}
