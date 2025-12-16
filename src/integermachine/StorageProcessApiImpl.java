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

        try (Scanner scanner = new Scanner(new File(input.getUri()))) {
            while (scanner.hasNextInt()) {
                values.add(scanner.nextInt());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read input file", e);
        }

        return new IntStreamBatch(values);
    }

    @Override
    public void writeOutputs(OutputSinkRef output,
                             List<KeyValueResult> results,
                             Delimiters delimiters) {

        if (delimiters == null) {
            delimiters = Delimiters.defaults();
        }

        String pairDelim = delimiters.getPairDelimiter();
        String kvDelim = delimiters.getKeyValueDelimiter();

        try (FileWriter writer = new FileWriter(output.getUri())) {
            for (int i = 0; i < results.size(); i++) {
                KeyValueResult r = results.get(i);
                writer.write(r.getInput() + kvDelim + r.getResult());
                if (i < results.size() - 1) {
                    writer.write(pairDelim);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write output file", e);
        }
    }
}
