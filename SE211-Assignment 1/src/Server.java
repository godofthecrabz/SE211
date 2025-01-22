import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Port must be specified");
            System.exit(-1);
        }
        int port = Integer.parseInt(args[0]);
        File logFile = new File("log.txt");
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        Scanner scanner = null;
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            scanner = new Scanner(clientSocket.getInputStream());
            logFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + port);
            System.exit(-1);
        }
        String inputLine;
        while ((inputLine = scanner.nextLine()) != null) {
            System.out.print("The Client Printed: ");
            System.out.println(inputLine);
            if (inputLine.equals("quit")) {
                break;
            }
        }
        scanner.close();
        try {
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
