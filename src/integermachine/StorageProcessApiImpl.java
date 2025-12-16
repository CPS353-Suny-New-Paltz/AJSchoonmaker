package integermachine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StorageProcessApiImpl implements StorageProcessApi {

    @Override
    public IntStreamBatch readInputs(InputSourceRef input) {
        List<Integer> values = new ArrayList<>();

        if (input == null || input.getUri() == null) {
            return new IntStreamBatch(values);
        }

        try (Scanner scanner = new Scanner(new File(input.getUri()))) {
            while (scanner.hasNextInt()) {
                values.add(scanner.nextInt());
            }
        } catch (Exception e) {
            // CP5: do not propagate
            return new IntStreamBatch(values);
        }

        return new IntStreamBatch(values);
    }

    @Override
    public void writeOutputs(OutputSinkRef output,
                             List<KeyValueResult> results,
                             Delimiters delimiters) {

        if (output == null || output.getUri() == null) {
            return;
        }

        if (results == null) {
            results = List.of();
        }

        if (delimiters == null) {
            delimiters = Delimiters.defaults();
        }

        String pairDelimiter = delimiters.getPairDelimiter();
        String kvDelimiter = delimiters.getKeyValueDelimiter();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < results.size(); i++) {
            if (i > 0) {
                sb.append(pairDelimiter);
            }
            sb.append(results.get(i).getInput());
            sb.append(kvDelimiter);
            sb.append(results.get(i).getResult());
        }

        try (FileWriter writer = new FileWriter(output.getUri())) {
            writer.write(sb.toString());
        } catch (IOException e) {
            // CP5: swallow exception
        }
    }
}
