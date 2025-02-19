import csv.*;

import java.io.File;
import java.util.List;

public class TestApplication {
    public static void main(String[] args) {
        CSVFormat format = new CSVFormat();

        try (CSVReader reader = new CSVReader(new File("test.csv"), format)) {
            List<String> headers = reader.getHeaders();
            List<CSVRecord> records = reader.getRecords();

            System.out.print("The header values are: ");
            for (String header : headers) {
                System.out.print(header);
                System.out.print(" ");
            }
            System.out.println();

            CSVRecord record = records.get(0);

            System.out.print("Print Record toString: ");
            System.out.println(record);

            System.out.print("Check isSet by index: ");
            System.out.println(record.isSet(0));

            System.out.print("Check isSet by name: ");
            System.out.println(record.isSet("Heading 2"));

            System.out.print("Check get by index: ");
            System.out.println(record.get(0));

            System.out.print("Check get by name: ");
            System.out.println(record.get("Heading 2"));

            System.out.print("Record size: ");
            System.out.println(record.size());

            System.out.print("Check isConsistent returns true: ");
            System.out.println(record.isConsistent());
        } catch (Exception e) {
            System.out.println("Error opening file");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }

        String[] headers = {"", "Col 1", "Col 2", "Col 3"};
        try (CSVPrinter printer = new CSVPrinter("demo.csv", format, headers)) {
            printer.printRecord("Row 1", "abc", "def", "ghi");

            printer.print("Row 2");
            printer.print("123");
            printer.print("456");
            printer.print("789");
            printer.println();
        } catch (Exception e) {
            System.out.println("Error printing to file");
            System.exit(-1);
        }
    }
}
