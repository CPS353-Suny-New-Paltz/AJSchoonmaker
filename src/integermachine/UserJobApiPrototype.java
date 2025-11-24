package integermachine;

import project.annotations.NetworkAPIPrototype;

public class UserJobApiPrototype implements UserJobApi {

    @NetworkAPIPrototype
    @Override
    public String submitJob(JobConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("config must not be null");
        }
        // For now just return a fake job ID.
        // Later this will call into the Orchestrator.
        return "job-" + System.currentTimeMillis();
    }
}
