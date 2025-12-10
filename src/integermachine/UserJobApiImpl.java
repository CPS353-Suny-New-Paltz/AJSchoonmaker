package integermachine;

public class UserJobApiImpl implements UserJobApi {

    private final Orchestrator orchestrator;

    public UserJobApiImpl(Orchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @Override
    public String submitJob(JobConfig config) {
        // Checkpoint 3: empty implementation, just return a default.
        // We are NOT calling orchestrator yet.
        return "";
    }
}
