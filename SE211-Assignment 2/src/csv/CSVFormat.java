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
        CSVFormat format = new CSVFormat();
        format.setDelimiter(this.delimiter);
        format.setTrimSpace(this.trimSpace);
        format.setIgnoreEmptyLines(this.ignoreEmptyLines);
        format.setHeaders(this.hasHeader);
        return format;
    }
}
