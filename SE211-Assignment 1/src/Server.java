import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.util.Scanner;

public class Server {
    private static File logFile;
    private static FileWriter logWriter;
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Port must be specified");
            System.exit(-1);
        }
        int port = Integer.parseInt(args[0]);
        logFile = new File("log.txt");
        logWriter = null;
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        Scanner scanner = null;
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            scanner = new Scanner(clientSocket.getInputStream());
            logFile.createNewFile();
            logWriter = new FileWriter(logFile, true);
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + port);
            System.exit(-1);
        }
        String inputLine;
        while ((inputLine = scanner.nextLine()) != null) {
            if (processRequest(inputLine)) {
                break;
            }
        }
        scanner.close();
        try {
            clientSocket.close();
            serverSocket.close();
            logWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean processRequest(String clientInput) {
        int tokenEnd = clientInput.indexOf(' ');
        String command = tokenEnd == -1 ? clientInput : clientInput.substring(0, tokenEnd);
        System.out.println("Command: " + command);
        switch (command) {
            case "logMessage":
                try {
                    //Files.write(logFile.toPath(), clientInput.substring(tokenEnd + 1).getBytes());
                    logWriter.write(clientInput.substring(tokenEnd + 1));
                    logWriter.write('\n');
                    logWriter.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "printLog":
                try {
                    Scanner scanner = new Scanner(logFile);
                    while (scanner.hasNextLine()) {
                        System.out.println(scanner.nextLine());
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "printRecent":
                try {
                    Scanner scanner = new Scanner(logFile);
                    String line = "";
                    while (scanner.hasNextLine()) {
                        line = scanner.nextLine();
                    }
                    System.out.println(line);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "printText":
                try {
                    Scanner scanner = new Scanner(logFile);
                    String line = "";
                    while (scanner.hasNextLine()) {
                        line = scanner.nextLine();
                        if (line.contains(clientInput.substring(tokenEnd + 1))) {
                            System.out.println(line);
                        }
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "quit":
                return true;
            default:
                System.out.println("Invalid command");
                break;
        }
        return false;
    }
}
