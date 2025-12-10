package integermachine;

import project.annotations.NetworkAPIPrototype;

public class UserJobApiPrototype {

    @NetworkAPIPrototype
    public void prototype(UserJobApi api) {
        // Prototype call — does not run in production
        api.submitJob(new JobConfig());
    }
}
