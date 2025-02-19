package csv;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class CSVReader implements AutoCloseable {

    private final CSVFormat format;
    private final List<String> headers;
    private final List<CSVRecord> records;
    private final Scanner in;

    public CSVReader(BufferedInputStream inputStream, CSVFormat format) {
        this.format = format.copy();
        this.headers = new ArrayList<>();
        this.records = new ArrayList<>();
        this.in = new Scanner(inputStream);
        read();
    }

    public CSVReader(InputStream inputStream, CSVFormat format) {
        this(new BufferedInputStream(inputStream), format);
    }

    public CSVReader(File file, CSVFormat format) throws IOException {
        this(file.toPath(), format);
    }

    public CSVReader(Path path, CSVFormat format) throws IOException {
        this(Files.newInputStream(path), format);
    }

    public CSVReader(String path, CSVFormat format) throws IOException {
        this(Path.of(path), format);
    }

    private void read() {
        long lineCount = 0;
        List<String> values = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            int start = 0;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) != format.getDelimiter()) {
                    continue;
                }
                String value = line.substring(start, i);
                if (format.shouldTrimSpace()) {
                    value = value.replaceAll(" ", "");
                }
                values.add(value);
                start = i + 1;
            }
            if (lineCount == 0 && format.hasHeader()) {
                headers.addAll(values);
            } else {
                if (format.shouldIgnoreEmptyLines()) {
                    boolean isEmpty = true;
                    for (String value : values) {
                        if (!value.isEmpty()) {
                            isEmpty = false;
                        }
                    }
                    if (!isEmpty) {
                        records.add(new CSVRecord(headers, values, format));
                    }
                } else {
                    records.add(new CSVRecord(headers, values, format));
                }
            }
            values = new ArrayList<>();
            lineCount++;
        }
    }

    public List<String> getHeaders() {
        return Collections.unmodifiableList(headers);
    }

    public List<CSVRecord> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public void close() throws Exception {
        in.close();
    }
}
