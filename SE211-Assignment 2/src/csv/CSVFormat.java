package csv;

public class CSVFormat {

    private char delimiter;
    private boolean trimSpace;
    private boolean ignoreEmptyLines;
    private boolean hasHeader;

    public CSVFormat() {
        delimiter = ',';
        trimSpace = false;
        ignoreEmptyLines = true;
        hasHeader = true;
    }

    public CSVFormat(char delimiter, boolean trimSpace, boolean ignoreEmptyLines, boolean hasHeader) {
        this.delimiter = delimiter;
        this.trimSpace = trimSpace;
        this.ignoreEmptyLines = ignoreEmptyLines;
        this.hasHeader = hasHeader;
    }

    public char getDelimiter() {
        return delimiter;
    }

    public boolean shouldTrimSpace() {
        return trimSpace;
    }

    public boolean shouldIgnoreEmptyLines() {
        return ignoreEmptyLines;
    }

    public boolean hasHeader() {
        return hasHeader;
    }

    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }

    public void setTrimSpace(boolean trimSpace) {
        this.trimSpace = trimSpace;
    }

    public void setIgnoreEmptyLines(boolean ignoreEmptyLines) {
        this.ignoreEmptyLines = ignoreEmptyLines;
    }

    public void setHeaders(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public CSVFormat copy() {
        return new CSVFormat(
                this.delimiter, this.trimSpace, this.ignoreEmptyLines, this.hasHeader);
    }
}
