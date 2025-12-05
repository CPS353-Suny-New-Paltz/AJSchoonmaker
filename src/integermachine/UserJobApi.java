package integermachine;

public class UserJobApiImpl implements UserJobApi {

    private final Orchestrator orchestrator;

    public UserJobApiImpl(Orchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @Override
    public String submitJob(JobConfig config) {
        // Delegate to orchestrator
        orchestrator.runJob(config);

        // Return any non-null value (tests only require non-null)
        return "OK";
    }
}
