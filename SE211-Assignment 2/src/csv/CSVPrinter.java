package csv;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CSVPrinter implements AutoCloseable {

    private final CSVFormat format;
    private final String[] headers;
    private final PrintStream printer;

    public CSVPrinter(BufferedOutputStream outputStream, CSVFormat format, String... headers) {
        this.format = format.copy();
        this.headers = headers;
        this.printer = new PrintStream(outputStream);
        printRecord(headers);
    }

    public CSVPrinter(OutputStream outputStream, CSVFormat format, String... headers) {
        this(new BufferedOutputStream(outputStream), format, headers);
    }

    public CSVPrinter(Path path, CSVFormat format, String... headers) throws IOException {
        this(Files.newOutputStream(path), format, headers);
    }

    public CSVPrinter(File file, CSVFormat format, String... headers) throws IOException {
        this(file.toPath(), format, headers);
    }

    public CSVPrinter(String path, CSVFormat format, String... headers) throws IOException {
        this(Path.of(path), format, headers);
    }

    public void printRecord(String... record) {
        for (String value : record) {
            print(value);
        }
        println();
    }

    public void print(String value) {
        printer.print(value);
        printer.print(format.getDelimiter());
    }

    public void println() {
        printer.println();
    }

    @Override
    public void close() throws Exception {
        printer.close();
    }
}
