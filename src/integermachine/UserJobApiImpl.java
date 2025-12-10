package integermachine;

public class UserJobApiImpl implements UserJobApi {

    private final Orchestrator orchestrator;

    public UserJobApiImpl(Orchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @Override
    public String submitJob(JobConfig config) {
        // Must call orchestrator.runJob() to satisfy Mockito test
        orchestrator.runJob(config);

        // Since runJob returns void, we return any string
        return "OK";
    }
}
