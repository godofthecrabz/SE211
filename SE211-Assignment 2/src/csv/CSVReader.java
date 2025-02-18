package csv;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public CSVReader(File file, CSVFormat format) throws IOException {
        this(file.toPath(), format);
    }

    public CSVReader(Path path, CSVFormat format) throws IOException {
        this(new BufferedInputStream(Files.newInputStream(path)), format);
    }

    public CSVReader(String path, CSVFormat format) throws IOException {
        this(Path.of(path), format);
    }

    private void read() {
    }

    @Override
    public void close() throws Exception {
        in.close();
    }
}
