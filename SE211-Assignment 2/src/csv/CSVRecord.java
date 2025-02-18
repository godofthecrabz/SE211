package csv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class CSVRecord {

    private final CSVFormat format;
    private final List<String> headers;
    private final List<String> values;

    CSVRecord(List<String> headers, List<String> values, CSVFormat format) {
        this.format = format;
        this.headers = headers;
        this.values = values;
    }

    public String get(int i) {
        return values.get(i);
    }

    public String get(String name) {
        if (!headers.contains(name)) {
            return null;
        }
        int i = headers.indexOf(name);
        if (i >= values.size()) {
            return null;
        }
        return values.get(i);
    }

    public boolean isSet(int i) {
        return get(i) != null;
    }

    public boolean isSet(String name) {
        return get(name) != null;
    }

    public int size() {
        return values.size();
    }

    public boolean isConsistent() {
        return headers.size() == values.size();
    }

    public List<String> values() {
        return Collections.unmodifiableList(values);
    }

    public Iterator<String> iterator() {
        return values().iterator();
    }

    public Stream<String> stream() {
        return values().stream();
    }
}
