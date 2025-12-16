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

        File f = new File(input.getUri());

      
        if (!f.exists() || !f.isFile()) {
            return new IntStreamBatch(values);
        }

        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    values.add(scanner.nextInt());
                } else {
                    // Skip non-integer tokens safely
                    scanner.next();
                }
            }
        } catch (Exception e) {
        
            throw new RuntimeException("Failed to read input file: " + input.getUri(), e);
        }

        return new IntStreamBatch(values);
    }

    @Override
    public void writeOutputs(OutputSinkRef output,
                             List<KeyValueResult> results,
                             Delimiters delimiters) {

        if (output == null || output.getUri() == null) {
            throw new IllegalArgumentException("OutputSinkRef (and uri) must not be null");
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
            KeyValueResult r = results.get(i);

            if (i > 0) {
                sb.append(pairDelimiter);
            }

            sb.append(r.getInput());
            sb.append(kvDelimiter);
            sb.append(r.getResult());
        }

        try (FileWriter writer = new FileWriter(output.getUri())) {
            writer.write(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write output file: " + output.getUri(), e);
        }
    }
}
