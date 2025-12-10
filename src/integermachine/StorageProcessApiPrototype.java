package integermachine;

import project.annotations.ProcessAPIPrototype;

public class StorageProcessApiPrototype {

    @ProcessAPIPrototype
    public void prototype(StorageProcessApi api) {
        // Prototype call — does not run in production
        api.store("key", "value");
    }
}
