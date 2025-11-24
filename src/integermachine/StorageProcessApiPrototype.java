package integermachine;

import java.util.List;

import project.annotations.ProcessAPIPrototype;

public class StorageProcessApiPrototype implements StorageProcessApi {

    @ProcessAPIPrototype
    @Override
    public IntStreamBatch readInputs(InputSourceRef input) {
        // Prototype: hard-coded example inputs for now.
        // Later we'll actually read from input.getUri().
        return new IntStreamBatch(List.of(10, 20));
    }

    @ProcessAPIPrototype
    @Override
    public void writeOutputs(OutputSinkRef output,
                             List<KeyValueResult> results,
                             Delimiters delimiters) {
        Delimiters d = (delimiters == null) ? Delimiters.defaults() : delimiters;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < results.size(); i++) {
            KeyValueResult kv = results.get(i);
            sb.append(kv.getInput())
              .append(d.getKeyValueDelimiter())
              .append(kv.getResult());
            if (i < results.size() - 1) {
                sb.append(d.getPairDelimiter());
            }
        }

        // Prototype: print instead of really writing.
        System.out.println("[StorageProcessApiPrototype] would write to "
                + output.getUri() + ": " + sb);
    }
}
