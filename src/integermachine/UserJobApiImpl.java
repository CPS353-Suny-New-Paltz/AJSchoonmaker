package integermachine;

public class UserJobApiImpl implements UserJobApi {

    private final Orchestrator orchestrator;

    public UserJobApiImpl(Orchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @Override
    public String submitJob(JobConfig config) {
        // Validation 
        if (config == null) {
            return "ERROR: null JobConfig";
        }

        try {
            orchestrator.runJob(config);
            return "OK";
        } catch (Exception e) {
            // CP5 req #2: do not allow uncaught exceptions to cross network boundary
            return "ERROR: " + e.getClass().getSimpleName();
        }
    }
}
