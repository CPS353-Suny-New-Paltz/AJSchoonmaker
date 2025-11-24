package integermachine;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserJobApi {
    /**
     * User submits a job (input source, output sink, delimiters).
     * Returns a job ID.
     */
    String submitJob(JobConfig config);
}
